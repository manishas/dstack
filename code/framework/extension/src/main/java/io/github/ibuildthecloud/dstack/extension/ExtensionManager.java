package io.github.ibuildthecloud.dstack.extension;

import java.util.List;

public interface ExtensionManager {

    <T> T first(String key, String typeString);

    <T> T first(String key, Class<T> type);

    List<?> list(String key);

    <T> List<T> getExtensionList(Class<T> type);

    <T> List<T> getExtensionList(String key, Class<T> type);

    List<ExtensionPoint> getExtensions();

    ExtensionPoint getExtensionPoint(Class<?> type);

    ExtensionPoint getExtensionPoint(String key, Class<?> type);

}
