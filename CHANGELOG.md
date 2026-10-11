# Changelog

All notable changes to Village are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- Village-Core module: plugin entry point with a clean setup/start/shutdown lifecycle, safe for `/plugin reload`.
- Village-Roles module, depending on Village-Core and registering into it.
- ASCII banner printed once the server has booted, listing the loaded modules.
- Module registry with reload detection: a "reloaded" banner is printed when a module is reloaded while the server runs.
- Administrator notices in chat (`village.admin.notify` permission, granted to operators): plugin status on join and module reloads.
- Native translations through Hytale language files: English (native) and French.
- Village logo and mod icon.
- GitHub Actions build for every module, with the jars uploaded as an artifact.
