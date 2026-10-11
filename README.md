<p align="center">
  <img src="branding/logo-256.png" alt="Village logo" width="160">
</p>

# Village

A Werewolf social deduction minigame for Hytale: secret roles, deadly nights, village debates and votes.
*Trust no one after dark.*

> **Status:** early development. The project foundation is in place; game features are coming.

## Modules

| Module | Jar | Purpose |
|---|---|---|
| `village-core` | `Village-Core-x.y.z.jar` | Game engine: games, phases (lobby → night → day → vote → end), players, votes, role registry. |
| `village-roles` | `Village-Roles-x.y.z.jar` | Classic roles (Werewolf, Villager, Seer, Witch, Hunter, Cupid...). Requires Village-Core. |

Village-Roles declares `Gevail:Village-Core` as a manifest dependency, so the server always loads Core first.
It compiles against Core (`compileOnly`) without bundling it: Core is provided at runtime by its own jar.

### Village-Core layout

```
dev.gevail.village.core
├─ VillageCorePlugin    Entry point: wires everything together, no logic
├─ VillagePermissions   Permission nodes
├─ listener/            Hytale event listeners
├─ log/                 Server log output (banner)
├─ module/              Registry of loaded Village modules
└─ notify/              Chat notices to administrators
```

Planned packages: `game/`, `player/`, `role/`, `vote/`, `api/`, `config/`.
Game rules will stay plain Java (testable without the game); everything touching the Hytale API stays at the edges.

## Requirements

- Java 25 (e.g. [Temurin](https://adoptium.net/))
- Hytale server 0.6.x

## Building

```bash
./gradlew build                      # builds every module (jars in village-*/build/libs)
./gradlew updateAllPluginManifests   # regenerates manifest.json files from the build scripts
./gradlew runAllMods                 # local server with every module
```

On Windows, use `gradlew.bat`.

### Local setup

Machine-specific settings go in your user Gradle properties (`~/.gradle/gradle.properties`), never in the repository:

```properties
# Path to the Assets.zip of your Hytale installation, used by runServer / runAllMods
hytaleHomeOverride=C:/path/to/Hytale/install/release/package/game/latest/Assets.zip
```

## Configuration

- `gradle.properties`: values shared by every module (version, Java, Hytale, author).
- `<module>/build.gradle.kts`: identity of each module (mod id, main class, description, dependencies).

## Translations

Player-facing texts use Hytale's native translation system. Language files live in
`village-core/src/main/resources/Server/Languages/<language>/village.lang`:

```
core.module.reloaded = {module} {version} reloaded.
```

English (`en-US`) is the native language and the fallback; French (`fr-FR`) is included.
To add a language, copy `en-US/village.lang` into a new folder (e.g. `de-DE/`) and translate the text after `=`.

## Permissions

| Node | Purpose |
|---|---|
| `village.admin.notify` | Receives administration notices: plugin status on join, module reloads. Granted to operators. |

## Development

Reload a module without restarting the server:

```
/plugin reload Gevail:Village-Roles
/plugin reload Gevail:Village-Core      (unloads Village-Roles, then: /plugin load Gevail:Village-Roles)
```

Every module must stop everything it started in `shutdown()` so reloads stay clean.

## Contact

Questions or ideas? Reach out on Discord (**Gevail**), directly or through the official Hytale Discord.

## License

[MIT](LICENSE)
