package io.github.ibuildthecloud.dstack.engine.process.impl;

import io.github.ibuildthecloud.dstack.engine.process.ExitReason;
import io.github.ibuildthecloud.dstack.engine.process.ProcessPhase;
import io.github.ibuildthecloud.dstack.engine.process.ProcessState;
import io.github.ibuildthecloud.dstack.json.JsonMapper;

import java.util.Map;

public abstract class AbstractStatesBasedProcessState implements ProcessState {

    ResourceStatesDefinition statesDefinition;
    JsonMapper jsonMapper;
    ProcessPhase phase;

    public AbstractStatesBasedProcessState(JsonMapper jsonMapper, ResourceStatesDefinition statesDef) {
        this.statesDefinition = statesDef;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public String setTransitioning() {
        String oldState = getState();
        String newState = statesDefinition.getTransitioningState(oldState);

        if ( ! setState(true, oldState, newState) ) {
            throw new ProcessExecutionExitException(ExitReason.STATE_CHANGED);
        }

        return newState;
    }

    protected abstract boolean setState(boolean transitioning, String oldState, String newState);

    @Override
    public String setDone() {
        String oldState = getState();
        String newState = statesDefinition.getDoneState(oldState);

        if ( ! setState(false, oldState, newState) ) {
            throw new ProcessExecutionExitException(ExitReason.STATE_CHANGED);
        }

        return newState;
    }

    @Override
    public boolean shouldCancel() {
        return ! statesDefinition.isValidState(getState());
    }

    @Override
    public boolean isDone(boolean schedule) {
        String state = getState();
        if ( statesDefinition.isDone(state) ) {
            if ( schedule && statesDefinition.isStartAndDone(state) ) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean isStart() {
        return statesDefinition.isStart(getState());
    }

    @Override
    public boolean isTransitioning() {
        return statesDefinition.isTransitioning(getState());
    }

    public ResourceStatesDefinition getStatesDefinition() {
        return statesDefinition;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> convertData(Object data) {
        if ( data instanceof Map ) {
            return convertMap((Map<Object,Object>)data);
        } else {
            return jsonMapper.convertValue(data, Map.class);
        }
    }

    protected abstract Map<String, Object> convertMap(Map<Object,Object> data);

    public JsonMapper getJsonMapper() {
        return jsonMapper;
    }

    @Override
    public ProcessPhase getPhase() {
        return phase;
    }

    @Override
    public void setPhase(ProcessPhase phase) {
        this.phase = phase;
    }

}
