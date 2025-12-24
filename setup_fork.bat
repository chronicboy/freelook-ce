@echo off
REM Setup script for creating a fork of btw-freelook

echo ========================================
echo Freelook CE Fork Setup
echo ========================================
echo.

REM Check if git is available
git --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Git is not installed or not in PATH
    echo Please install Git from https://git-scm.com/
    pause
    exit /b 1
)

echo Step 1: Initializing git repository...
cd /d "%~dp0"
git init
if errorlevel 1 (
    echo ERROR: Failed to initialize git repository
    pause
    exit /b 1
)

echo.
echo Step 2: Adding remote repositories...
git remote add upstream https://github.com/jeffinitup/btw-freelook.git
if errorlevel 1 (
    echo WARNING: Could not add upstream (may already exist)
)

echo Please enter your GitHub username (or press Enter to skip):
set /p GITHUB_USER=
if "%GITHUB_USER%"=="" (
    echo Skipping origin remote setup
    echo You can add it later with: git remote add origin https://github.com/YOUR_USERNAME/freelook-ce.git
) else (
    git remote add origin https://github.com/%GITHUB_USER%/freelook-ce.git
    if errorlevel 1 (
        echo WARNING: Could not add origin (may already exist)
    )
)

echo.
echo Step 3: Staging all files...
git add .
if errorlevel 1 (
    echo ERROR: Failed to stage files
    pause
    exit /b 1
)

echo.
echo Step 4: Creating initial commit...
git commit -m "Initial commit: Freelook CE 1.0.0 for BTW CE 3.0.0

A port of the popular Freelook mod for BTW CE 3.0.0 with enhanced features.

Based on: https://github.com/jeffinitup/btw-freelook

Enhanced Features:
- Full 360° rotation by default
- Faster zoom transitions (2x speed)
- Instant classic zoom mode
- Both third-person views enabled
- Default zoom key: C
- Default zoom: 1000%% (Eagle Eyed)
- Fixed NullPointerException in config GUI
- Updated for BTW CE 3.0.0 API"

if errorlevel 1 (
    echo ERROR: Failed to create commit
    pause
    exit /b 1
)

echo.
echo ========================================
echo Setup Complete!
echo ========================================
echo.
echo Your repository is ready. Next steps:
echo.
echo 1. Create a new repository on GitHub named 'freelook-ce'
echo 2. Push your code with:
echo    git push -u origin main
echo.
echo Or if you want to push to a different branch:
echo    git branch -M main
echo    git push -u origin main
echo.
pause

