# Asennusohje - Tilitin Priku

Tämä opas auttaa sinua asentamaan Tilitin Prikun järjestelmääsi.

## 📋 Sisällysluettelo

- [Järjestelmävaatimukset](#järjestelmävaatimukset)
- [Java-asennus](#java-asennus)
- [Tilittimen asennus](#tilittimen-asennus)
- [Ensimmäinen käynnistys](#ensimmäinen-käynnistys)
- [Ongelmien ratkaisu](#ongelmien-ratkaisu)

## 💻 Järjestelmävaatimukset

### Vähimmäisvaatimukset

- **Käyttöjärjestelmä:**
  - Windows 10 tai uudempi
  - macOS 10.15 (Catalina) tai uudempi
  - Linux (Ubuntu 20.04+, Fedora 35+, tai vastaava)

- **Java:** Version 21 tai uudempi (LTS)

- **Muisti:** Vähintään 512 MB RAM (suositus 1 GB)

- **Levytila:** ~100 MB vapaata tilaa

- **Näyttö:** Vähintään 1024x768 resoluutio

### Suositettu kokoonpano

- **Java:** Version 21 (uusin LTS)
- **Muisti:** 2 GB RAM tai enemmän
- **Levytila:** 500 MB vapaata tilaa
- **Näyttö:** 1920x1080 tai suurempi

## ☕ Java-asennus

Tilitin vaatii **Java 21** tai uudemman version toimiakseen.

### 1. Tarkista nykyinen Java-versio

Avaa komentorivi (Terminal/PowerShell/Command Prompt) ja suorita:

```bash
java -version
```

**Jos näet:**
```
java version "21.0.1" 2023-10-17 LTS
```
→ Java 21 on asennettu! Voit siirtyä [Tilittimen asennukseen](#tilittimen-asennus).

**Jos näet:**
```
java version "11.0.x" tai "17.0.x"
```
→ Sinulla on vanhempi versio. Päivitä Java 21:een.

**Jos näet:**
```
'java' is not recognized as an internal or external command
```
→ Javaa ei ole asennettu. Asenna Java 21.

### 2. Lataa Java 21

Valitse alla olevista vaihtoehdoista itsellesi sopivin:

#### Vaihtoehto A: Azul Zulu JDK 21 (Suositeltu)

✅ **Suositeltu** - Ilmainen, tukee kaikkia alustoja, hyvä yhteensopivuus

1. Siirry osoitteeseen: https://www.azul.com/downloads/#zulu
2. Valitse:
   - **Java Version:** Java 21 (LTS)
   - **Operating System:** Oma käyttöjärjestelmäsi
   - **Architecture:**
     - Windows/Linux: x86 64-bit
     - Mac (Intel): x86 64-bit
     - Mac (M1/M2/M3): ARM 64-bit
3. Lataa **.exe** (Windows), **.dmg** (Mac), tai **.deb/.rpm** (Linux)
4. Asenna lataamasi paketti

#### Vaihtoehto B: Eclipse Adoptium (Temurin)

✅ Hyvä vaihtoehto - Ilmainen, laajasti käytetty

1. Siirry: https://adoptium.net/
2. Valitse **Java 21 (LTS)**
3. Lataa ja asenna

#### Vaihtoehto C: Oracle JDK 21

⚠️ Huomio: Kaupalliseen käyttöön voi vaatia lisenssin

1. Siirry: https://www.oracle.com/java/technologies/downloads/#java21
2. Valitse oma alustasi
3. Lataa ja asenna

### 3. Asennusohjeet käyttöjärjestelmittäin

#### Windows

1. **Lataa** .exe -asennusohjelma (esim. `zulu21.xx.xx-ca-jdk21.0.x-win_x64.exe`)
2. **Tuplaklikkaa** ladattua tiedostoa
3. **Seuraa** asennusohjelman ohjeita
4. **Hyväksy** oletusasetukset (Java asentuu `C:\Program Files\Zulu\`)
5. **Varmista** että "Set JAVA_HOME variable" on valittuna
6. **Viimeistele** asennus

**Tarkista asennus:**
```powershell
java -version
```

#### macOS

##### Intel Mac

1. **Lataa** .dmg -tiedosto (esim. `zulu21.xx.xx-ca-jdk21.0.x-macosx_x64.dmg`)
2. **Tuplaklikkaa** ladattua tiedostoa
3. **Vedä** Java-kuvake Applications-kansioon
4. **Avaa** Terminal
5. **Aseta** JAVA_HOME:

```bash
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 21)' >> ~/.zshrc
source ~/.zshrc
```

##### Apple Silicon Mac (M1/M2/M3)

1. **Lataa** ARM-versio (esim. `zulu21.xx.xx-ca-jdk21.0.x-macosx_aarch64.dmg`)
2. **Tuplaklikkaa** ladattua tiedostoa
3. **Seuraa** samoja ohjeita kuin Intel Macilla
4. **Tärkeää:** Varmista että lataat ARM64-version!

**macOS-turvallisuus:**

Jos saat virheilmoituksen "ei voi avata, koska kehittäjää ei voida varmentaa":
1. Klikkaa oikealla hiiren painikkeella .dmg-tiedostoa
2. Valitse "Avaa"
3. Vahvista avaaminen

**Tarkista asennus:**
```bash
java -version
```

#### Linux (Ubuntu/Debian)

```bash
# Päivitä pakettiluettelo
sudo apt update

# Asenna OpenJDK 21
sudo apt install openjdk-21-jdk

# Aseta JAVA_HOME
echo 'export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64' >> ~/.bashrc
source ~/.bashrc
```

#### Linux (Fedora/RHEL)

```bash
# Asenna OpenJDK 21
sudo dnf install java-21-openjdk-devel

# Aseta JAVA_HOME
echo 'export JAVA_HOME=/usr/lib/jvm/java-21-openjdk' >> ~/.bashrc
source ~/.bashrc
```

**Tarkista asennus:**
```bash
java -version
```

## 📥 Tilittimen asennus

### Vaihtoehto 1: Lataa valmis JAR (Suositeltu käyttäjille)

1. **Lataa** viimeisin `tilitin-1.6.0-priku.1.jar` GitHubin releases-osiosta
2. **Luo kansio** Tilittimelle (esim. `C:\Tilitin` tai `~/Tilitin`)
3. **Kopioi** JAR-tiedosto kansioon
4. **Valmis!** Siirry [Ensimmäinen käynnistys](#ensimmäinen-käynnistys)

### Vaihtoehto 2: Käännä lähdekoodista (Kehittäjille)

#### Edellytykset

- Java 21 JDK
- Maven 3.6 tai uudempi
- Git (valinnainen)

#### Asennus

```bash
# 1. Kloonaa repositorio (tai lataa ZIP)
git clone <repository-url>
cd tilitin-masterPriku

# 2. Käännä projekti
mvn clean package

# 3. JAR-tiedosto löytyy target-hakemistosta
ls -lh target/tilitin-1.6.0-priku.1.jar
```

**Windows:**
```powershell
dir target\tilitin-1.6.0-priku.1.jar
```

## 🚀 Ensimmäinen käynnistys

### Windows

#### Graafinen käynnistys

1. **Etsi** `tilitin-1.6.0-priku.1.jar` Resurssienhallinnasta
2. **Tuplaklikkaa** JAR-tiedostoa

Jos tuplaklikkaus ei toimi:

#### Komentorivin kautta

1. **Avaa** PowerShell tai Command Prompt
2. **Siirry** Tilitin-kansioon:
   ```powershell
   cd C:\Tilitin
   ```
3. **Käynnistä:**
   ```powershell
   java -jar tilitin-1.6.0-priku.1.jar
   ```

### macOS

#### Terminal-käynnistys

1. **Avaa** Terminal
2. **Siirry** Tilitin-kansioon:
   ```bash
   cd ~/Tilitin
   ```
3. **Käynnistä:**
   ```bash
   java -jar tilitin-1.6.0-priku.1.jar
   ```

#### Graafinen käynnistys (tuplaklikkaus)

macOS estää oletuksena tuntemattomien kehittäjien ohjelmien suorittamisen.

**Ensimmäinen käynnistys:**
1. **Klikkaa hiiren oikealla** JAR-tiedostoa
2. **Valitse** "Avaa"
3. **Vahvista** avaaminen

**Tämän jälkeen** voit tuplaklikkaa normaalisti.

### Linux

1. **Avaa** Terminal
2. **Siirry** Tilitin-kansioon:
   ```bash
   cd ~/Tilitin
   ```
3. **Käynnistä:**
   ```bash
   java -jar tilitin-1.6.0-priku.1.jar
   ```

## 🏁 Ensimmäiset askeleet

### 1. Luo uusi kirjanpito

1. **Valitse** "Tiedosto → Uusi tietokanta"
2. **Valitse** tallennuspaikka (esim. `kirjanpito.db`)
3. **Valitse** tilikartta:
   - **Elinkeinotoiminta (24%)** - Yritystoiminnalle
   - **Yhdistys** - Yhdistyksille
   - **Asunto-osakeyhtiö** - Taloyhtiöille
4. **Täytä** perustiedot:
   - Yrityksen nimi
   - Y-tunnus
   - Osoite
5. **Valitse** ensimmäinen tilikausi:
   - Alkupäivä (esim. 1.1.2025)
   - Loppupäivä (esim. 31.12.2025)
6. **Tallenna**

### 2. Luo ensimmäinen tosite

1. **Paina** "Uusi tosite" (tai Insert-näppäin)
2. **Valitse** päivämäärä
3. **Lisää vienti**:
   - Tili (esim. 1910 Nordea)
   - Debet: 1000,00
   - Selite: "Alkupääoma"
4. **Lisää vastakki vienti**:
   - Tili (esim. 2000 Oma pääoma)
   - Kredit: 1000,00
   - Selite: "Alkupääoma"
5. **Varmista** että Debet = Kredit
6. **Tallenna** (tosite tallentuu automaattisesti)

### 3. Tulosta ensimmäinen raportti

1. **Valitse** "Tulosteet → Päiväkirja → Kaikki tositteet"
2. **Valitse** aikaväli
3. **Esikatsele** tai **Vie PDF:ksi**

🎉 **Onneksi olkoon!** Olet nyt käyttänyt Tilittimeä ensimmäisen kerran!

## 🐛 Ongelmien ratkaisu

### "Java not found" tai "java: command not found"

**Syy:** Java ei ole asennettu tai PATH-muuttuja ei ole oikein.

**Ratkaisu:**
1. Asenna Java 21 ([katso ohjeet](#java-asennus))
2. Tarkista PATH:
   - Windows: Lisää `C:\Program Files\Zulu\zulu-21\bin` PATH:iin
   - Mac/Linux: Aseta JAVA_HOME ([katso ohjeet](#java-asennus))

### "Unsupported class file major version 65"

**Syy:** Yrität ajaa Java 21:llä käännettyä ohjelmaa vanhemmalla Javalla.

**Ratkaisu:**
```bash
# Tarkista Java-versio
java -version

# Päivitä Java 21:een
```

### "Could not find or load main class"

**Syy:** JAR-tiedosto on vioittunut tai käynnistyskomento on väärä.

**Ratkaisu:**
1. Lataa JAR uudelleen
2. Varmista komento:
   ```bash
   java -jar tilitin-1.6.0-priku.1.jar
   ```
   (Ei: `java tilitin-1.6.0-priku.1.jar`)

### Tuplaklikkaus ei toimi (Windows)

**Syy:** JAR-tiedostot eivät ole yhdistetty javaan.

**Ratkaisu:**
1. **Klikkaa oikealla** JAR-tiedostoa
2. **Valitse** "Avaa sovelluksella → Valitse toinen sovellus"
3. **Etsi** Java:
   ```
   C:\Program Files\Zulu\zulu-21\bin\javaw.exe
   ```
4. **Valitse** "Käytä aina tätä sovellusta"

### macOS: "Damaged and can't be opened"

**Syy:** macOS Gatekeeper estää tuntemattomien kehittäjien ohjelmia.

**Ratkaisu:**
```bash
# Poista kaarantainimerkintä
xattr -d com.apple.quarantine tilitin-1.6.0-priku.1.jar

# TAI klikkaa oikealla → Avaa
```

### Linux: "Permission denied"

**Syy:** JAR-tiedostolla ei ole suoritusoikeuksia.

**Ratkaisu:**
```bash
# Anna suoritusoikeudet (ei välttämätön JAR:ille)
chmod +x tilitin-1.6.0-priku.1.jar

# Käynnistä
java -jar tilitin-1.6.0-priku.1.jar
```

### Ohjelma kaatuu käynnistyksessä

**Tarkista:**

1. **Java-versio:**
   ```bash
   java -version  # Pitää olla 21 tai uudempi
   ```

2. **Käynnistä verbose-tilassa:**
   ```bash
   java -jar tilitin-1.6.0-priku.1.jar -verbose
   ```

3. **Tarkista lokit:**
   - Windows: `%APPDATA%\Tilitin\tilitin.log`
   - Mac/Linux: `~/.tilitin/tilitin.log`

### Fonttiongelmat (tekstit näkyvät väärin)

**Ratkaisu:**
```bash
# Aseta järjestelmän locale
# Windows: Ohjauspaneeli → Alue ja kieli
# Mac: System Preferences → Language & Region
# Linux:
export LANG=fi_FI.UTF-8
java -jar tilitin-1.6.0-priku.1.jar
```

## 🆘 Lisäapu

### Dokumentaatio

- [README.md](../README.md) - Yleiskatsaus
- [CHANGELOG.md](../CHANGELOG.md) - Versiohistoria
- [CSV_TUONTI.md](CSV_TUONTI.md) - CSV-tuonti-ohje
- [CONTRIBUTING.md](../CONTRIBUTING.md) - Kehittäjän opas

### Yhteisö

- **GitHub Issues** - Raportoi bugeja tai pyydä ominaisuuksia
- **Alkuperäinen dokumentaatio** - https://helineva.net/tilitin/

### Vianmääritys

Jos ongelma jatkuu:

1. **Luo issue** GitHubissa
2. **Liitä mukaan:**
   - Käyttöjärjestelmä ja versio
   - Java-versio (`java -version`)
   - Virheilmoitus kokonaisuudessaan
   - Vaiheet ongelman toistamiseen

---

**Hyvää kirjanpitoa Tilittimellä!** 📊✨
