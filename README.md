# GlyphAdapter

A root android service that lets third party apps using nothing's glyph-sdk to work on flashed phones

## Why?

Nothing phone's glyph interface integrations rely on a system service which is included with NothingOS. Because of this, 3rd party apps that use [Glyph SDK](https://github.com/Nothing-Developer-Programme/Glyph-Developer-Kit) can't access the glyph on phones flashed with custom ROMs like LineageOS, GrapheneOS, etc.

This is a userspace reimplementation of nothing's service which lets 3rd party apps control the glyph LEDs. Glyph Adapter spoofs nothing's original service by using `com.nothing.thirdparty` package name and receiving intents sent by the SDK.

## Building

Build with latest android studio

## Installation

> [!IMPORTANT]
> 1. Your device has to be [rooted](https://github.com/topjohnwu/Magisk).
> 2. Install the adapter with `adb --force-queryable` flag for apps to be able to send Intents to the service

## Contributing

Current implementation is pretty hacky, so any contribuions are appreciated.

---

Uses parts of the code from https://github.com/LineageOS/android_hardware_nothing licensed under Apache 2.0
