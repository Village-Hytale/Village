package dev.gevail.village.core.module;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * Keeps track of the Village modules currently loaded (name -> version).
 *
 * Reload detection: the first time a module registers, a JVM system property is set. Unlike static fields,
 * which are recreated with the plugin's class loader, it survives "/plugin reload", so a module registering
 * again can be told apart from a first load.
 */
public final class ModuleRegistry {

    private static final String LOADED_PROPERTY_PREFIX = "village.loaded.";

    private final Map<String, String> modules = new ConcurrentSkipListMap<>();

    /**
     * @return {@code true} if this module had already been loaded by this server (i.e. it is being reloaded)
     */
    public boolean register(String name, String version) {
        modules.put(name, version);

        String property = LOADED_PROPERTY_PREFIX + name;
        boolean loadedBefore = System.getProperty(property) != null;
        System.setProperty(property, version);
        return loadedBefore;
    }

    public void unregister(String name) {
        modules.remove(name);
    }

    public void clear() {
        modules.clear();
    }

    /** Human-readable list, e.g. "Village-Core 1.0.0, Village-Roles 1.0.0". */
    public String describe() {
        return modules.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}
