package io.github.ibuildthecloud.dstack.object.postinit;

import io.github.ibuildthecloud.dstack.util.type.Priority;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class SetPropertiesPostInstantiationHandler implements ObjectPostInstantiationHandler, Priority {

    @Override
    public <T> T postProcess(T obj, Class<T> clz, Map<String, Object> properties) {
        try {
            BeanUtils.copyProperties(obj, properties);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e);
        }

        return obj;
    }

    @Override
    public int getPriority() {
        return Priority.DEFAULT;
    }

}
