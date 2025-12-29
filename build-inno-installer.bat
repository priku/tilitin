@echo off
setlocal enabledelayedexpansion

echo ============================================
echo   Tilitin 1.6.0 - Installer Builder
echo ============================================
echo.

REM Check if jPackage output exists
if not exist "dist\windows\Tilitin\Tilitin.exe" (
    echo [ERROR] jPackage output not found!
    echo Please run build-windows.bat first.
    echo.
    pause
    exit /b 1
)

REM Find Inno Setup compiler
set "ISCC="
if exist "%ProgramFiles(x86)%\Inno Setup 6\ISCC.exe" (
    set "ISCC=%ProgramFiles(x86)%\Inno Setup 6\ISCC.exe"
) else if exist "%ProgramFiles%\Inno Setup 6\ISCC.exe" (
    set "ISCC=%ProgramFiles%\Inno Setup 6\ISCC.exe"
) else if exist "%LocalAppData%\Programs\Inno Setup 6\ISCC.exe" (
    set "ISCC=%LocalAppData%\Programs\Inno Setup 6\ISCC.exe"
)

if "!ISCC!"=="" (
    echo [ERROR] Inno Setup 6 not found!
    echo Please install Inno Setup from: https://jrsoftware.org/isinfo.php
    echo.
    pause
    exit /b 1
)

echo [INFO] Found Inno Setup: !ISCC!
echo.

REM Create output directory
if not exist "dist\installer" mkdir "dist\installer"

REM Compile the installer
echo [BUILD] Compiling installer...
echo.
"!ISCC!" /Q "installer\tilitin.iss"

if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Installer compilation failed!
    pause
    exit /b 1
)

echo.
echo ============================================
echo   BUILD SUCCESSFUL!
echo ============================================
echo.
echo Installer created: dist\installer\Tilitin-1.6.0-setup.exe
echo.
pause
