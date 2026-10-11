package dev.gevail.village.core.listener;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.gevail.village.core.VillageCorePlugin;
import dev.gevail.village.core.notify.AdminNotifier;

/**
 * When a player joins: administrators get a one-line status of the Village plugin. Other players see nothing.
 */
public final class PlayerReadyListener {

    private final VillageCorePlugin plugin;

    public PlayerReadyListener(VillageCorePlugin plugin) {
        this.plugin = plugin;
    }

    public void onPlayerReady(PlayerReadyEvent event) {
        Ref<EntityStore> ref = event.getPlayerRef();
        if (!ref.isValid()) {
            return;
        }
        PlayerRef player = ref.getStore().getComponent(ref, PlayerRef.getComponentType());
        if (player == null) {
            return;
        }

        AdminNotifier.send(player, Message.translation("village.core.status")
                .param("version", plugin.getVersion())
                .param("modules", plugin.getModules().describe()));
    }
}
