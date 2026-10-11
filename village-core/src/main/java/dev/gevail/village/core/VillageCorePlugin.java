package dev.gevail.village.core;

import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.BootEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import dev.gevail.village.core.listener.PlayerReadyListener;
import dev.gevail.village.core.log.Banner;
import dev.gevail.village.core.module.ModuleRegistry;
import dev.gevail.village.core.notify.AdminNotifier;

import javax.annotation.Nonnull;

/**
 * Entry point of Village-Core, the game engine.
 *
 * This class only wires the plugin together; the actual logic lives in dedicated classes.
 * Other Village modules access it through {@link #get()} and declare themselves with {@link #registerModule}.
 *
 * Player-facing texts are translation keys ("village.*") defined in
 * resources/Server/Languages/{language}/village.lang (en-US is the native language).
 */
public class VillageCorePlugin extends JavaPlugin {

    public static final String MODULE_NAME = "Village-Core";

    private static VillageCorePlugin instance;

    private final ModuleRegistry modules = new ModuleRegistry();

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

    public ModuleRegistry getModules() {
        return modules;
    }

    /**
     * Called by every Village module from its start() method.
     *
     * During server startup nothing is printed here: a single banner is printed once the server has booted.
     * Afterwards (/plugin reload or /plugin load), a banner is printed and administrators are notified.
     */
    public void registerModule(String name, String moduleVersion) {
        boolean reloaded = modules.register(name, moduleVersion);
        if (!HytaleServer.get().isBooted()) {
            return;
        }

        String state = reloaded ? "reloaded" : "loaded";
        Banner.print(getLogger(), name + " " + moduleVersion + " " + state + "!", modules.describe());
        AdminNotifier.broadcast(Message.translation("village.core.module." + state)
                .param("module", name)
                .param("version", moduleVersion));
    }

    /** Called by every Village module from its shutdown() method. */
    public void unregisterModule(String name) {
        modules.unregister(name);
    }

    @Override
    protected void setup() {
        version = getManifest().getVersion().toString();

        // Printed once the server has fully booted, so the banner is the last thing in the startup log
        getEventRegistry().register(BootEvent.class, event ->
                Banner.print(getLogger(), "Werewolf plugin version " + version + " loaded!", modules.describe()));

        PlayerReadyListener playerReadyListener = new PlayerReadyListener(this);
        getEventRegistry().registerGlobal(PlayerReadyEvent.class, playerReadyListener::onPlayerReady);
    }

    @Override
    protected void start() {
        registerModule(MODULE_NAME, version);
    }

    @Override
    protected void shutdown() {
        // Stop everything running here (games, timers...) so that /plugin reload stays clean
        getLogger().atInfo().log("%s %s unloaded.", MODULE_NAME, version);
        modules.clear();
        instance = null;
    }
}
