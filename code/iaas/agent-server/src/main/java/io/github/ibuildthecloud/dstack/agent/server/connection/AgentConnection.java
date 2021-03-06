package io.github.ibuildthecloud.dstack.agent.server.connection;

import io.github.ibuildthecloud.dstack.eventing.model.Event;

import com.google.common.util.concurrent.ListenableFuture;

public interface AgentConnection {

    long getAgentId();

    String getUri();

    /**
     * The future that is returned is expected to always complete, either success or failure.
     * It it up to the implementation of the AgentConnection to ensure that the future
     * is cancelled or set to an exception in the situation of a timeout.  In other words,
     * The AgentConnection is responsible for handling the timeout logic.
     *
     * @param event
     * @return
     */
    ListenableFuture<Event> execute(Event event);

    void close();

    boolean isOpen();

}
