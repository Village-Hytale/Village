package dev.gevail.village.core.log;

import com.hypixel.hytale.logger.HytaleLogger;

/**
 * ASCII banner written to the server log when Village starts.
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
              Trust no one after dark.
            ==================================================
            """;

    private Banner() {
    }

    /**
     * @param headline first line under the logo, e.g. "Werewolf plugin version 1.0.0 loaded!"
     */
    public static void print(HytaleLogger logger, String headline) {
        logger.atInfo().log(TEMPLATE, headline);
    }
}
