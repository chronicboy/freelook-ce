# Freelook CE - Better Than Wolves Community Edition

<p align="center">
  <img src="branding/clip-0.gif?raw=true" alt="Player punching a tree, while looking around" width="500"/>
  <img src="branding/clip-1.gif?raw=true" alt="Player punching a tree, while looking around" width="500"/>
</p>

A port of the popular Freelook mod for **BTW CE 3.0.0** with enhanced features!

Based on the original mod by [jeffinitup](https://github.com/jeffinitup/btw-freelook)

## ✨ Enhanced Features in this version:

• **Full 360° rotation** - Look behind you, straight up, and straight down! Complete "head on a swivel" freedom by default
• **Faster zoom** - Zoom transitions are twice as fast and more responsive
• **Instant classic zoom** - Zero delay snap zoom (like Optifine)
• **Both third-person views** - Front and back views both work when enabled

## 📋 Default Settings:

• **Zoom Key**: C | **Zoom Type**: Classic (instant) | **Zoom**: 1000% (Eagle Eyed)
• **Freelook Key**: Left Alt | **Range**: 180° (full rotation)
• **Third Person**: Enabled (both front and back)

## 🎮 Features

### Freelook
The core feature of the addon. Freelook is bound to **Left Alt** by default. Freelook lets you look at your surroundings while performing any task, whether it is moving in a direction or mining a block.

You can tweak the degrees of freedom, which is how far your neck can turn. By default, you have **full 360° rotation** enabled. Freelook works with third-person as well, however degrees of freedom are ignored in third-person mode.

### Zoom
<p align="center">
  <img src="branding/clip-2.gif?raw=true" alt="Player zooming onto a tree" width="500"/>
</p>

Zoom is bound to the **C key** by default. Zoom has three modes that you can use:
- **"Realistic"**: Features a squinting overlay, along with zoom interpolation
- **Interpolated**: Fast, interpolated zooming (2x faster than original)
- **Classic**: Instant snap zoom, similar to Optifine (default)

You can also tweak the percentage amount of the zoom, from 200% to 1000% (Eagle Eyed). Mouse sensitivity is adjusted on zoom, to maintain ease of control. Smooth camera can be toggled, however it is not enabled by default.

## ⚙️ Configuration
<p align="center">
  <img src="branding/clip-3.gif?raw=true" alt="Player navigating the freelook options menu" width="500"/>
</p>

Configuration is done through the new Freelook Options menu, accessible within the vanilla options menu. It is designed to be intuitive and easy to use, all of the addon's settings are contained in this one menu.

## 🔧 Building

1. Clone this repository
2. Run `./gradlew build` (or `gradlew.bat build` on Windows)
3. The built jar will be in `build/libs/freelook-ce-1.0.0.jar`

**Note**: You will need to set up BTW CE 3.0.0 dependencies. See the [BTW CE example mod](https://github.com/BTW-Community/BTW-gradle-fabric-example-CE-3.0.0) for setup instructions.

## 📦 Installation

1. Download the latest release from the [Releases](https://github.com/chronicboy31/freelook-ce/releases) page
2. Place `freelook-ce-1.0.0.jar` in your Minecraft `mods` folder
3. Requires BTW CE 3.0.0 and Fabric Loader 0.14.19+

## 🤝 Compatibility

This addon has been tested with BTW CE 3.0.0. It may conflict with addons that mess with viewmodel rendering or add their own zoom functions. Let me know of any potential incompatibilities and I will look into it.

## 📝 License

This addon is available under the CC0 license. Based on the original work by jeffinitup.

This project incorporates:
* A precompiled version of [Tiny Remapper](https://github.com/FabricMC/tiny-remapper) (LGPL-3.0)

## 🙏 Credits

- **Original mod**: [jeffinitup](https://github.com/jeffinitup/btw-freelook)
- **Port & Enhancements**: chronicboy31
- **Better Than Wolves Community Edition**: [BTW Community](https://github.com/BTW-Community)
