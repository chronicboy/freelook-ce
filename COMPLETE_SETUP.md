# Complete GitHub Fork Setup Guide

Your repository is almost ready! You just need to configure git and push to GitHub.

## Step 1: Configure Git Identity

Run these commands (replace with your actual GitHub email and name):

```bash
cd "C:\Users\Spurty McGoo\Desktop\Cursor\BTW CE 3.0.0\Freelook CE"

# Set your git identity (use your GitHub email and name)
git config user.email "your-email@example.com"
git config user.name "chronicboy31"
```

Or set it globally for all repositories:
```bash
git config --global user.email "your-email@example.com"
git config --global user.name "chronicboy31"
```

## Step 2: Create the Initial Commit

The files are already staged. Just commit:

```bash
cd "C:\Users\Spurty McGoo\Desktop\Cursor\BTW CE 3.0.0\Freelook CE"

git commit -m "Initial commit: Freelook CE 1.0.0 for BTW CE 3.0.0

A port of the popular Freelook mod for BTW CE 3.0.0 with enhanced features.

Based on: https://github.com/jeffinitup/btw-freelook

Enhanced Features:
- Full 360° rotation by default
- Faster zoom transitions (2x speed)
- Instant classic zoom mode
- Both third-person views enabled

Default Settings:
- Zoom Key: C | Zoom Type: Classic | Zoom: 1000% Eagle Eyed
- Freelook Key: Left Alt | Range: 180° full rotation
- Third Person: Enabled

Technical Changes:
- Ported to BTW CE 3.0.0 API
- Fixed NullPointerException in config GUI
- Updated build configuration for BTW CE 3.0.0"
```

## Step 3: Create Repository on GitHub

1. Go to https://github.com/new
2. Repository name: `freelook-ce`
3. Description: "A port of the popular Freelook mod for BTW CE 3.0.0 with enhanced features"
4. Set to Public (or Private if you prefer)
5. **DO NOT** initialize with README, .gitignore, or license (we already have these)
6. Click "Create repository"

## Step 4: Add Your Fork as Origin and Push

```bash
cd "C:\Users\Spurty McGoo\Desktop\Cursor\BTW CE 3.0.0\Freelook CE"

# Add your fork as origin (replace chronicboy31 with your GitHub username)
git remote add origin https://github.com/chronicboy31/freelook-ce.git

# Rename branch to main (if needed)
git branch -M main

# Push to your fork
git push -u origin main
```

## Step 5: Verify

1. Go to your repository: https://github.com/chronicboy31/freelook-ce
2. Verify all files are there
3. Check that the README displays correctly

## Repository Structure

Your repository will contain:
- ✅ All source code in `src/`
- ✅ Build configuration files
- ✅ README with enhanced features
- ✅ .gitignore (excludes build artifacts)
- ✅ Branding GIFs
- ✅ Gradle wrapper files

**Excluded** (by .gitignore):
- ❌ `build/` directory
- ❌ `bin/` directory  
- ❌ `build_BTW/` directory
- ❌ `.gradle/` cache

## Next Steps

After pushing:
1. Add a description to your GitHub repository
2. Create a release with the built jar file
3. Optionally create a Pull Request to the original repo (if you want to contribute back)

## Keeping Up with Original

To sync with the original repository:

```bash
# Fetch updates from original
git fetch upstream

# Merge updates (if needed)
git merge upstream/main

# Push to your fork
git push origin main
```

## Quick Reference

**Upstream (Original)**: https://github.com/jeffinitup/btw-freelook  
**Your Fork**: https://github.com/chronicboy31/freelook-ce

