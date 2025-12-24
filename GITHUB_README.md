# GitHub Repository Setup Guide

## What to Commit

Your repository is ready for GitHub! The `.gitignore` file will automatically exclude build artifacts. Here's what will be committed:

### Source Files (will be committed):
- `src/` - All Java source code and resources
- `build.gradle` - Build configuration
- `gradle.properties` - Project properties
- `settings.gradle` - Gradle settings
- `gradlew` / `gradlew.bat` - Gradle wrapper scripts
- `gradle/wrapper/` - Gradle wrapper files
- `README.md` - Updated with enhanced features
- `.gitignore` - Git ignore rules
- `branding/` - GIF files for README
- `libs/tiny-remapper-0.8.6+local-fat.jar` - Required remapper tool
- `mavenRepo/` - Maven repository with mappings
- `install.bat` / `install.sh` - Installation scripts

### Build Artifacts (automatically excluded by .gitignore):
- `build/` - Build output directory
- `bin/` - Compiled classes
- `build_BTW/` - BTW dependencies (should be set up by users)
- `.gradle/` - Gradle cache
- IDE files (`.idea/`, `.vscode/`, etc.)

## Differences from Original

The original `btw-freelook-CE-3.0.0` appears to have fewer folders because:
1. **Build artifacts are excluded** - The `.gitignore` prevents `build/`, `bin/`, and `build_BTW/` from being committed
2. **No extracted dependencies** - The `build_BTW/temp_extract/` folder in your version is a build artifact that won't be committed
3. **Same source structure** - Both have the same `src/` directory structure

## Quick Start Commands

```bash
# Initialize git repository (if not already done)
git init

# Add all files (build artifacts will be ignored)
git add .

# Commit
git commit -m "Initial commit: Freelook CE 1.0.0 for BTW CE 3.0.0"

# Add remote (replace with your GitHub repo URL)
git remote add origin https://github.com/chronicboy31/freelook-ce.git

# Push
git push -u origin main
```

## Repository Structure

```
freelook-ce/
├── src/
│   └── main/
│       ├── java/                    # All Java source code
│       │   └── btw/community/jeffyjamzhd/freelook/
│       │       ├── event/           # Camera event handling
│       │       ├── gui/             # Configuration GUI
│       │       ├── mixin/           # Mixin classes
│       │       └── FreeLookAddon.java
│       └── resources/               # Resources and assets
│           ├── fabric.mod.json      # Mod metadata
│           ├── freelook.mixins.json # Mixin configuration
│           └── assets/              # Textures, lang files, etc.
├── branding/                        # GIF files for README
├── build.gradle                     # Build configuration
├── gradle.properties                # Project properties
├── settings.gradle                  # Gradle settings
├── README.md                        # Project documentation
├── .gitignore                       # Git ignore rules
└── libs/                            # Required libraries
```

## Notes

- The `build_BTW/` directory should be created by users when they run the install script
- Users need to provide BTW CE 3.0.0 zip file to set up dependencies
- All source code is in `src/` and will be committed
- Build outputs are automatically excluded

