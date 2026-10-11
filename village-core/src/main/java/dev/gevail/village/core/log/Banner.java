package dev.gevail.village.core.log;

import com.hypixel.hytale.logger.HytaleLogger;

/**
 * ASCII banner written to the server log when Village starts or when one of its modules is reloaded.
 */
public final class Banner {

    private static final String TEMPLATE = """

               __      ___ _ _
               \\ \\    / (_) | |
                \\ \\  / / _| | | __ _  __ _  ___
                 \\ \\/ / | | | |/ _` |/ _` |/ _ \\
                  \\  /  | | | | (_| | (_| |  __/
                   \\/   |_|_|_|\\__,_|\\__, |\\___|
                                      __/ |
                                     |___/
            ==================================================
              %s
              Modules: %s
              Trust no one after dark.
            ==================================================
            """;

    private Banner() {
    }

    /**
     * @param headline first line under the logo, e.g. "Werewolf plugin version 1.0.0 loaded!"
     * @param modules  loaded modules, e.g. "Village-Core 1.0.0, Village-Roles 1.0.0"
     */
    public static void print(HytaleLogger logger, String headline, String modules) {
        logger.atInfo().log(TEMPLATE, headline, modules);
    }
}
