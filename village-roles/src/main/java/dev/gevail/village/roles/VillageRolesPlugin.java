package dev.gevail.village.roles;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import dev.gevail.village.core.VillageCorePlugin;

import javax.annotation.Nonnull;

/**
 * Entry point of Village-Roles: the classic Werewolf roles.
 * Depends on Village-Core (declared in the manifest), which is therefore loaded first.
 */
public class VillageRolesPlugin extends JavaPlugin {

    public static final String MODULE_NAME = "Village-Roles";

    public VillageRolesPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        // Roles will be registered into Village-Core here
    }

    @Override
    protected void start() {
        String version = getManifest().getVersion().toString();

        VillageCorePlugin core = VillageCorePlugin.get();
        if (core == null) {
            getLogger().atSevere().log("%s %s: Village-Core not found, nothing registered.", MODULE_NAME, version);
            return;
        }

        core.registerModule(MODULE_NAME, version);
    }

    @Override
    protected void shutdown() {
        VillageCorePlugin core = VillageCorePlugin.get();
        if (core != null) {
            core.unregisterModule(MODULE_NAME);
        }
        getLogger().atInfo().log("%s unloaded.", MODULE_NAME);
    }
}
