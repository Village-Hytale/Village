package dev.gevail.village.core;

/**
 * Permission nodes used by Village.
 * Operators (OP group) hold every permission through the "*" wildcard.
 */
public final class VillagePermissions {

    /** Receives administration notices in chat (plugin status on join, module reloads). */
    public static final String ADMIN_NOTIFY = "village.admin.notify";

    private VillagePermissions() {
    }
}
