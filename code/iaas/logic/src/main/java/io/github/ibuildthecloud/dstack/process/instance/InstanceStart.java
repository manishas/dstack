package io.github.ibuildthecloud.dstack.process.instance;

import io.github.ibuildthecloud.dstack.archaius.util.ArchaiusUtil;
import io.github.ibuildthecloud.dstack.core.constants.InstanceConstants;
import io.github.ibuildthecloud.dstack.core.dao.GenericMapDao;
import io.github.ibuildthecloud.dstack.core.model.Instance;
import io.github.ibuildthecloud.dstack.core.model.InstanceHostMap;
import io.github.ibuildthecloud.dstack.core.model.Volume;
import io.github.ibuildthecloud.dstack.engine.handler.HandlerResult;
import io.github.ibuildthecloud.dstack.engine.process.ProcessInstance;
import io.github.ibuildthecloud.dstack.engine.process.ProcessState;
import io.github.ibuildthecloud.dstack.eventing.exception.EventExecutionException;
import io.github.ibuildthecloud.dstack.object.process.StandardProcess;
import io.github.ibuildthecloud.dstack.object.util.DataAccessor;
import io.github.ibuildthecloud.dstack.object.util.TransitioningUtils;
import io.github.ibuildthecloud.dstack.process.base.AbstractDefaultProcessHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.config.DynamicIntProperty;

@Named
public class InstanceStart extends AbstractDefaultProcessHandler {

    private static final DynamicIntProperty COMPUTE_TRIES = ArchaiusUtil.getInt("instance.compute.tries");
    private static final Logger log = LoggerFactory.getLogger(InstanceStart.class);

    GenericMapDao mapDao;

    @Override
    public HandlerResult handle(ProcessState state, ProcessInstance process) {
        final Instance instance = (Instance)state.getResource();

        Map<String,Object> resultData = new ConcurrentHashMap<String,Object>();
        HandlerResult result = new HandlerResult(resultData);

        String stage = "find placement";
        try {
            allocate(instance);

            stage = "create storage";
            storage(instance);
        } catch ( EventExecutionException e ) {
            log.error("Failed to {} for instance [{}]", stage, instance.getId());
            return stopOrRemove(state, instance, e);
        }

        try {
            stage = "create compute";
            compute(instance);
        } catch ( EventExecutionException e ) {
            log.error("Failed to {} for instance [{}]", stage, instance.getId());
            if ( incrementComputeTry(state) >= getMaxComputeTries(instance) ) {
                return stopOrRemove(state, instance, e);
            }
            throw e;
        }

        return result;
    }

    protected int getMaxComputeTries(Instance instance) {
        Integer tries = DataAccessor.fromDataFieldOf(instance)
                .withScope(InstanceStart.class)
                .withKey("computeTries")
                .as(Integer.class);

        if ( tries != null && tries > 0 ) {
            return tries;
        }

        return COMPUTE_TRIES.get();
    }

    protected HandlerResult stopOrRemove(ProcessState state, Instance instance, EventExecutionException e) {
        if ( InstanceCreate.isCreateStart(state) ) {
            getObjectProcessManager().scheduleStandardProcess(StandardProcess.REMOVE, instance, null);
        } else {
            getObjectProcessManager().scheduleProcessInstance(InstanceConstants.PROCESS_STOP, instance, null);
        }

        return new HandlerResult(TransitioningUtils.getTransitioningData(e));
    }

    protected int incrementComputeTry(ProcessState state) {
        DataAccessor accessor = DataAccessor.fromMap(state.getData())
                                    .withScope(InstanceStart.class)
                                    .withKey("computeTry");

        Integer computeTry = accessor.as(Integer.class);
        if ( computeTry == null ) {
            computeTry = 0;
        }

        computeTry++;

        accessor.set(computeTry);

        return computeTry;
    }

    protected void allocate(Instance instance) {
        execute("instance.allocate", instance, null);
    }

    protected void storage(Instance instance) {
        List<Volume> volumes = getObjectManager().children(instance, Volume.class);

        for ( Volume volume : volumes ) {
            activate(volume, null);
        }
    }

    protected void compute(Instance instance) {
        for ( InstanceHostMap map : mapDao.findNonRemoved(InstanceHostMap.class, Instance.class, instance.getId()) ) {
            activate(map, null);
        }
    }

    public GenericMapDao getMapDao() {
        return mapDao;
    }

    @Inject
    public void setMapDao(GenericMapDao mapDao) {
        this.mapDao = mapDao;
    }

}
