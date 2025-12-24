# Setting Up Your Fork

Since you don't have the original repository cloned, here's how to set up a proper fork:

## Option 1: Clone Original and Apply Changes (Recommended)

This creates a proper fork with git history:

```bash
# Navigate to parent directory
cd "C:\Users\Spurty McGoo\Desktop\Cursor\BTW CE 3.0.0"

# Clone the original repository
git clone https://github.com/jeffinitup/btw-freelook.git freelook-ce-original

# Copy your enhanced version over the original
# (This will overwrite with your changes)
xcopy /E /I /Y "Freelook CE\*" "freelook-ce-original\"

# Navigate to the cloned repo
cd freelook-ce-original

# Initialize git (if not already)
git init

# Add the original as upstream
git remote add upstream https://github.com/jeffinitup/btw-freelook.git

# Add your fork as origin (replace with your GitHub username)
git remote add origin https://github.com/chronicboy31/freelook-ce.git

# Stage all changes
git add .

# Commit your changes
git commit -m "Port to BTW CE 3.0.0 with enhanced features

- Ported to BTW CE 3.0.0 API
- Added full 360° rotation by default
- Faster zoom transitions (2x speed)
- Instant classic zoom mode
- Both third-person views enabled
- Default zoom key changed to C
- Default zoom set to 1000% (Eagle Eyed)
- Fixed NullPointerException in config GUI
- Updated author and repository links"

# Push to your fork
git push -u origin main
```

## Option 2: Initialize Current Directory as New Repo

If you prefer to start fresh (no original git history):

```bash
# Navigate to your Freelook CE directory
cd "C:\Users\Spurty McGoo\Desktop\Cursor\BTW CE 3.0.0\Freelook CE"

# Initialize git
git init

# Add the original as upstream (for reference)
git remote add upstream https://github.com/jeffinitup/btw-freelook.git

# Add your fork as origin
git remote add origin https://github.com/chronicboy31/freelook-ce.git

# Stage all files
git add .

# Create initial commit
git commit -m "Initial commit: Freelook CE 1.0.0 for BTW CE 3.0.0

A port of the popular Freelook mod for BTW CE 3.0.0 with enhanced features.

Based on: https://github.com/jeffinitup/btw-freelook

Enhanced Features:
- Full 360° rotation by default
- Faster zoom transitions (2x speed)
- Instant classic zoom mode
- Both third-person views enabled
- Default zoom key: C
- Default zoom: 1000% (Eagle Eyed)
- Fixed NullPointerException in config GUI"

# Push to your fork
git push -u origin main
```

## After Setup

Once pushed, you can:
1. Go to GitHub and create a Pull Request from your fork to the original (if desired)
2. Continue making changes and pushing to your fork
3. Keep upstream synced: `git fetch upstream` and `git merge upstream/main`

## Important Notes

- The `.gitignore` will exclude all build artifacts automatically
- Only source files in `src/` and configuration files will be committed
- The `build_BTW/` directory should be set up by users (not committed)
- Your enhanced features are all in the source code and ready to commit

