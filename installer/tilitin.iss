; Tilitin 1.6.0 - Inno Setup Script
; Windows installer for Tilitin accounting software (Priku version)

#define MyAppName "Tilitin"
#define MyAppVersion "1.6.0"
#define MyAppPublisher "Tilitin Project"
#define MyAppURL "https://github.com/priku/tilitin-masterPriku"
#define MyAppExeName "Tilitin.exe"
#define MyAppDescription "Ilmainen kirjanpito-ohjelma yrityksille ja yhdistyksille"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
AppId={{B2C3D4E5-F6A7-8901-BCDE-F12345678901}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}/releases
DefaultDirName={autopf}\{#MyAppName}
DefaultGroupName={#MyAppName}
AllowNoIcons=yes
; License file
LicenseFile=..\COPYING
; Output settings
OutputDir=..\dist\installer
OutputBaseFilename=Tilitin-{#MyAppVersion}-setup
; Compression
Compression=lzma2/ultra64
SolidCompression=yes
LZMAUseSeparateProcess=yes
; Windows version requirements
MinVersion=10.0
; Installation privileges
PrivilegesRequired=lowest
PrivilegesRequiredOverridesAllowed=dialog
; Uninstaller settings
UninstallDisplayIcon={app}\{#MyAppExeName}
UninstallDisplayName={#MyAppName}
; Modern appearance
WizardStyle=modern
WizardSizePercent=120

[Languages]
Name: "finnish"; MessagesFile: "compiler:Languages\Finnish.isl"
Name: "english"; MessagesFile: "compiler:Default.isl"

[CustomMessages]
finnish.LaunchProgram=Käynnistä {#MyAppName}
finnish.CreateDesktopIcon=Luo pikakuvake työpöydälle
finnish.CreateStartMenuEntry=Luo pikakuvake Käynnistä-valikkoon
english.LaunchProgram=Launch {#MyAppName}
english.CreateDesktopIcon=Create a desktop shortcut
english.CreateStartMenuEntry=Create a Start Menu shortcut

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked
Name: "startmenuicon"; Description: "{cm:CreateStartMenuEntry}"; GroupDescription: "{cm:AdditionalIcons}"

[Files]
; Main application files (from jPackage output)
Source: "..\dist\windows\Tilitin\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{group}\{cm:UninstallProgram,{#MyAppName}}"; Filename: "{uninstallexe}"
Name: "{autodesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram}"; Flags: nowait postinstall skipifsilent

[Code]
// Custom code for additional functionality

// Etsi ja poista aiempi Tilitin-asennus
function GetUninstallString(): String;
var
  sUnInstPath: String;
  sUnInstallString: String;
begin
  sUnInstPath := 'Software\Microsoft\Windows\CurrentVersion\Uninstall\{#SetupSetting("AppId")}_is1';
  sUnInstallString := '';
  
  // Tarkista HKCU (per-user asennus)
  if not RegQueryStringValue(HKCU, sUnInstPath, 'UninstallString', sUnInstallString) then
    // Tarkista HKLM (kaikille käyttäjille)
    RegQueryStringValue(HKLM, sUnInstPath, 'UninstallString', sUnInstallString);
  
  Result := sUnInstallString;
end;

function IsUpgrade(): Boolean;
begin
  Result := (GetUninstallString() <> '');
end;

function UninstallOldVersion(): Integer;
var
  sUnInstallString: String;
  iResultCode: Integer;
begin
  Result := 0;
  sUnInstallString := GetUninstallString();
  if sUnInstallString <> '' then begin
    sUnInstallString := RemoveQuotes(sUnInstallString);
    if Exec(sUnInstallString, '/SILENT /NORESTART /SUPPRESSMSGBOXES', '', SW_HIDE, ewWaitUntilTerminated, iResultCode) then
      Result := 3
    else
      Result := 2;
  end else
    Result := 1;
end;

procedure CurStepChanged(CurStep: TSetupStep);
begin
  if (CurStep = ssInstall) then begin
    if IsUpgrade() then begin
      UninstallOldVersion();
    end;
  end;
end;
