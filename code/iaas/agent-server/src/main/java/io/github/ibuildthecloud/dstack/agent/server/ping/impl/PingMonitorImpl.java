package io.github.ibuildthecloud.dstack.agent.server.ping.impl;

import static com.google.common.util.concurrent.Futures.*;
import io.github.ibuildthecloud.dstack.agent.AgentLocator;
import io.github.ibuildthecloud.dstack.agent.RemoteAgent;
import io.github.ibuildthecloud.dstack.agent.server.ping.PingMonitor;
import io.github.ibuildthecloud.dstack.agent.server.ping.dao.PingDao;
import io.github.ibuildthecloud.dstack.agent.server.util.AgentConnectionUtils;
import io.github.ibuildthecloud.dstack.agent.util.AgentUtils;
import io.github.ibuildthecloud.dstack.archaius.util.ArchaiusUtil;
import io.github.ibuildthecloud.dstack.core.constants.AgentConstants;
import io.github.ibuildthecloud.dstack.core.constants.CommonStatesConstants;
import io.github.ibuildthecloud.dstack.core.model.Agent;
import io.github.ibuildthecloud.dstack.engine.process.ExitReason;
import io.github.ibuildthecloud.dstack.engine.process.ProcessInstanceException;
import io.github.ibuildthecloud.dstack.framework.event.Ping;
import io.github.ibuildthecloud.dstack.lock.LockDelegator;
import io.github.ibuildthecloud.dstack.lock.definition.LockDefinition;
import io.github.ibuildthecloud.dstack.object.ObjectManager;
import io.github.ibuildthecloud.dstack.object.process.ObjectProcessManager;
import io.github.ibuildthecloud.dstack.task.Task;
import io.github.ibuildthecloud.dstack.task.TaskOptions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.netflix.config.DynamicLongProperty;

public class PingMonitorImpl implements PingMonitor, Task, TaskOptions {

    private static final DynamicLongProperty BAD_PINGS = ArchaiusUtil.getLong("agent.ping.reconnect.after.failed.count");
    private static final DynamicLongProperty PING_TIMEOUT = ArchaiusUtil.getLong("agent.ping.timeout.seconds");
    private static final DynamicLongProperty PING_UNMANAGED_EVERY = ArchaiusUtil.getLong("agent.ping.unmanaged.every");
    private static final DynamicLongProperty PING_STATS_EVERY = ArchaiusUtil.getLong("agent.ping.stats.every");
    private static final DynamicLongProperty PING_RESOURCES_EVERY = ArchaiusUtil.getLong("agent.ping.resources.every");
    private static final DynamicLongProperty PING_SCHEDULE = ArchaiusUtil.getLong("task.agent.ping.schedule");

    private static final Logger log = LoggerFactory.getLogger(PingMonitorImpl.class);

    ObjectProcessManager processManager;
    ObjectManager objectManager;
    int interation = 0;
    PingDao pingDao;
    LockDelegator lockDelegator;
    AgentLocator agentLocator;
    ListeningExecutorService executorService;
    LoadingCache<Long, PingStatus> status = CacheBuilder
            .newBuilder()
            .expireAfterAccess(PING_SCHEDULE.get() * 3, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, PingStatus>() {
                @Override
                public PingStatus load(Long key) throws Exception {
                    return new PingStatus(key);
                }
            });


    protected void handleUnownwed(Agent agent) {
        if ( isInterval(PING_UNMANAGED_EVERY.get()) ) {
            RemoteAgent remoteAgent = agentLocator.lookupAgent(agent);
            remoteAgent.publish(AgentUtils.newPing(agent));
        }
    }

    protected void handleOwnwed(Agent agent) {
        Ping ping = AgentUtils.newPing(agent);

        if ( isInterval(PING_STATS_EVERY.get()) ) {
            ping.setOption(Ping.STATS, true);
        }

        if ( isInterval(PING_RESOURCES_EVERY.get()) ) {
            ping.setOption(Ping.RESOURCES, true);
        }

        doPing(agent, ping);
    }

    protected boolean isInterval(long every) {
        return interation % every == 0;
    }

    protected void ping(Agent agent) {
        LockDefinition lockDef = AgentConnectionUtils.getConnectionLock(agent);
        if ( lockDelegator.isLocked(lockDef) ) {
            handleOwnwed(agent);
        } else {
            handleUnownwed(agent);
        }
    }

    protected void doPing(final Agent agent, Ping ping) {
        RemoteAgent remoteAgent = agentLocator.lookupAgent(agent);

        addCallback(remoteAgent.call(ping, Ping.class, PING_TIMEOUT.get() * 1000), new FutureCallback<Ping>() {
            @Override
            public void onSuccess(Ping pong) {
                pingSuccess(agent, pong);
            }

            @Override
            public void onFailure(Throwable t) {
                pingFailure(agent);
            }
        });
    }

    protected void pingSuccess(Agent agent, Ping pong) {
        status.getUnchecked(agent.getId()).success();
    }

    protected void pingFailure(Agent agent) {
        long count = status.getUnchecked(agent.getId()).failed();
        log.error("Failed to get ping from agent [{}] count [{}]", agent.getId(), count);
        if ( count >= BAD_PINGS.get() ) {
            try {
                agent = objectManager.reload(agent);
                if ( CommonStatesConstants.ACTIVE.equals(agent.getState()) ) {
                    log.error("Scheduling reconnect for [{}]", agent.getId());
                    processManager.scheduleProcessInstance(AgentConstants.PROCESS_RECONNECT, agent, null);
                }
            } catch ( ProcessInstanceException e ) {
                if ( e.getExitReason() != ExitReason.CANCELED ) {
                    throw e;
                }
            }
        }
    }

    @Override
    public void run() {
        for ( Agent agent : pingDao.findAgentsToPing() ) {
            ping(agent);
        }
        interation++;
    }

    @Override
    public boolean isShouldRecord() {
        return false;
    }

    @Override
    public boolean isShouldLock() {
        return false;
    }

    @Override
    public String getName() {
        return "agent.ping";
    }

    public PingDao getPingDao() {
        return pingDao;
    }

    @Inject
    public void setPingDao(PingDao pingDao) {
        this.pingDao = pingDao;
    }

    public LockDelegator getLockDelegator() {
        return lockDelegator;
    }

    @Inject
    public void setLockDelegator(LockDelegator lockDelegator) {
        this.lockDelegator = lockDelegator;
    }

    public AgentLocator getAgentLocator() {
        return agentLocator;
    }

    @Inject
    public void setAgentLocator(AgentLocator agentLocator) {
        this.agentLocator = agentLocator;
    }

    public ListeningExecutorService getExecutorService() {
        return executorService;
    }

    @Inject
    public void setExecutorService(ListeningExecutorService executorService) {
        this.executorService = executorService;
    }

    public ObjectProcessManager getProcessManager() {
        return processManager;
    }

    @Inject
    public void setProcessManager(ObjectProcessManager processManager) {
        this.processManager = processManager;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    @Inject
    public void setObjectManager(ObjectManager objectManager) {
        this.objectManager = objectManager;
    }

}
