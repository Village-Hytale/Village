package dev.gevail.village.core;

import com.hypixel.hytale.server.core.event.events.BootEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import dev.gevail.village.core.log.Banner;

import javax.annotation.Nonnull;

/**
 * Entry point of Village-Core, the game engine.
 *
 * This class only wires the plugin together; the actual logic lives in dedicated classes.
 * Other Village modules access it through {@link #get()}.
 */
public class VillageCorePlugin extends JavaPlugin {

    public static final String MODULE_NAME = "Village-Core";

    private static VillageCorePlugin instance;

    private String version;

    public VillageCorePlugin(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
    }

    /** Active Village-Core instance, or {@code null} if the plugin is not loaded. */
    public static VillageCorePlugin get() {
        return instance;
    }

    public String getVersion() {
        return version;
    }

    @Override
    protected void setup() {
        version = getManifest().getVersion().toString();

        // Printed once the server has fully booted, so the banner is the last thing in the startup log
        getEventRegistry().register(BootEvent.class, event ->
                Banner.print(getLogger(), "Werewolf plugin version " + version + " loaded!"));
    }

    @Override
    protected void shutdown() {
        // Stop everything running here (games, timers...) so that /plugin reload stays clean
        getLogger().atInfo().log("%s %s unloaded.", MODULE_NAME, version);
        instance = null;
    }
}
