@echo off
REM ====================================================================
REM Tilitin 1.6.0 - Windows Build Script
REM Luo natiivin Windows-sovelluksen jPackage-työkalulla
REM ====================================================================

echo.
echo ========================================
echo  Tilitin 1.6.0 Windows Build
echo ========================================
echo.

REM Tarkista että Java on asennettu
java -version >nul 2>&1
if errorlevel 1 (
    echo [VIRHE] Java ei löydy! Asenna Java 21 tai uudempi.
    pause
    exit /b 1
)

echo [1/3] Rakennetaan JAR-paketti Mavenilla...
echo.
call mvn clean package
if errorlevel 1 (
    echo [VIRHE] Maven build epäonnistui!
    pause
    exit /b 1
)

echo.
echo [2/3] Luodaan natiivi Windows-sovellus jPackage:lla...
echo.

REM Luo dist-hakemisto jos ei ole olemassa
if not exist "dist" mkdir dist
if not exist "dist\windows" mkdir dist\windows

REM Hae versio pom.xml:stä
for /f "tokens=3 delims=<>" %%v in ('findstr /r "<version>.*</version>" pom.xml ^| findstr /n . ^| findstr "^1:"') do set VERSION=%%v
echo Versio: %VERSION%

REM Suorita jPackage
jpackage ^
  --input target ^
  --name "Tilitin" ^
  --main-jar tilitin-%VERSION%.jar ^
  --main-class kirjanpito.ui.Kirjanpito ^
  --type app-image ^
  --app-version 1.6.0 ^
  --vendor "Tilitin Project" ^
  --description "Ilmainen kirjanpito-ohjelma" ^
  --copyright "GPL v3" ^
  --dest dist\windows ^
  --java-options "-Dfile.encoding=UTF-8" ^
  --win-console

if errorlevel 1 (
    echo [VIRHE] jPackage epäonnistui!
    pause
    exit /b 1
)

echo.
echo [3/3] Valmis!
echo.
echo Windows-sovellus luotu: dist\windows\Tilitin\
echo.
echo Seuraava vaihe: Aja build-inno-installer.bat luodaksesi asennusohjelman.
echo.
pause
