package dev.gevail.village.core.notify;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import dev.gevail.village.core.VillagePermissions;

/**
 * Sends "[Village] ..." notices to administrators, i.e. players holding {@link VillagePermissions#ADMIN_NOTIFY}.
 */
public final class AdminNotifier {

    private static final String PREFIX_COLOR = "#C0392B";
    private static final String TEXT_COLOR = "#E8C26B";

    private AdminNotifier() {
    }

    /** Sends the notice to every connected administrator. */
    public static void broadcast(Message message) {
        for (PlayerRef player : Universe.get().getPlayers()) {
            send(player, message);
        }
    }

    /** Sends the notice to this player if they are an administrator. */
    public static void send(PlayerRef player, Message message) {
        if (player.hasPermission(VillagePermissions.ADMIN_NOTIFY)) {
            player.sendMessage(Message.join(
                    Message.raw("[Village] ").color(PREFIX_COLOR).bold(true),
                    message.color(TEXT_COLOR)));
        }
    }
}
