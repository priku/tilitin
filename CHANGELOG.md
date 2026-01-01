# Muutosloki - Tilitin Priku

## [1.6.1] - 2025-12-30

### 🔄 Riippuvuuksien päivitykset

**Tietoturva- ja vakauspäivitykset:**
- **PostgreSQL JDBC**: 42.7.4 → 42.7.8 (turvapäivitys)
- **MySQL Connector**: 9.1.0 → 9.5.0 (vakauspäivitys)
- **SQLite JDBC**: 3.47.1.0 → 3.51.1.0 (vakauspäivitys)
- **OpenCSV**: 5.9 → 5.12.0 (vakauspäivitys)

**Maven plugin-päivitykset:**
- **maven-jar-plugin**: 3.4.2 → 3.5.0 (bugikorjauksia)
- **maven-shade-plugin**: 3.6.0 → 3.6.1 (bugikorjauksia)

**Säilytetty:**
- Java 21 LTS (tuki syyskuuhun 2029)
- iTextPDF 5.5.13.4 (vakaa, päivitys vaatisi koodimuutoksia)
- maven-compiler-plugin 3.13.0 (uusin stable)

**Tekniset huomiot:**
- Kaikki päivitykset yhteensopivia ilman koodimuutoksia
- JDBC-ajurit käyttävät standardirajapintaa
- OpenCSV API pysynyt yhteensopivana
- Build testattu - toimii normaalisti

---

## [1.6.0] - 2025-12-29

### 🔧 Kriittiset korjaukset

#### Tilikarttamallien lataus
- **Korjattu: Tilikarttamallivalikko ei auennut** - Tilikarttamallit puuttuivat kokonaan projektista
- **Lisätty 10 sisäänrakennettua tilikarttamallia** JAR:n sisään resources-hakemistoon:
  - Elinkeinotoiminta (ALV 22%, 23%, 24%, 25,5%)
  - Yhdistys, Tiekunta, Asunto-osakeyhtiö
  - Yksityistalous, Yhteisen vesialueen osakaskunta
- **Korvattu tilikarttamallien latauslogiikka** - Luetaan mallit JAR-resursseista tiedostojärjestelmän sijaan
- **Päivitetty DataSourceInitializationModel** - Käyttää Java Properties -tiedostoa mallien luettelointiin
- **Päivitetty DataSourceInitializationWorker & DatabaseUpgradeUtil** - Yhteensopiva uuden mallisysteemin kanssa

#### macOS-kriittiset korjaukset
- **Korjattu: Valikot eivät toimineet macOS:ssä** - Siirretty system properties asetus ennen AWT/Swing-initialisointia
- **ARM Mac -DMG**: Erillinen `.dmg` Apple Silicon -Maceille (M1/M2/M3)
- **Intel Mac -DMG**: Erillinen `.dmg` Intel-Maceille
- **GitHub Actions**: Rakentaa molemmat DMG-versiot automaattisesti (macos-13 Intel, macos-14 ARM)
- **jpackage-parametrit**: Lisätty `-Dapple.laf.useScreenMenuBar=true` ja `-Xdock:name=Tilitin`

### 🆕 Uudet ominaisuudet

#### Vapaamuotoiset ALV-prosentit
- **Tietokantapäivitys v13 → v14**: Lisätty `vat_percentage` sarake tilin ALV-prosentin tallennukseen
- **Tuki mille tahansa ALV-prosentille**: Ei enää rajoitettu kiinteisiin arvoihin (esim. 25,5%, 14%, 10%)
- **⚠️ Yhteensopivuushuomio**: Päivitettyä tietokantaa ei voi avata vanhemmilla Tilitin-versioilla (< 1.6.0)

#### Multi-platform julkaisu
- **GitHub Actions CI/CD** - Automaattinen buildaus ja julkaisu
- **Windows Installer** - `.exe` asennusohjelma (jPackage + Inno Setup)
- **macOS DMG** - `.dmg` levykuva natiivi Mac-asennus
- **Linux DEB** - `.deb` paketti Debian/Ubuntu-jakeluille
- **Linux RPM** - `.rpm` paketti Fedora/Red Hat -jakeluille
- **CHANGELOG-integraatio** - Release notes luetaan automaattisesti

#### CSV/Procountor-tuonti (Kallio95)
- **CSV-tiedostojen tuonti**: Mahdollisuus tuoda tilitapahtumat Procountor-yhteensopivasta CSV-tiedostosta
- **Käyttöliittymä**: Uusi "CSV-tuonti (Procountor)" -valikkovaihtoehto kohdassa Muokkaa
- **Tiedostopolun muistaminen**: Sovellus muistaa viimeksi käytetyn tiedostopolun
- **Virheenkäsittely**: Kattava virheenkäsittely puuttuvien tilien ja virheellisten tiedostojen varalle
- **OpenCSV 5.9**: Moderni CSV-käsittelykirjasto

#### Tekninen modernisointi (Jkseppan)
- **Java 21 -tuki**: Päivitetty käyttämään uusinta Java LTS -versiota
- **Maven-rakennusjärjestelmä**: Korvattu Ant Maven 3.9+ -järjestelmällä
- **ARM Mac -yhteensopivuus**: Toimii natiivisti Apple Silicon -Maceilla (M1/M2/M3)
- **Dynaaminen versiointi**: Versio luetaan automaattisesti JAR-manifestista
- **Sisäänrakennetut tilikartat**: Tilikartat pakattu JAR-tiedoston sisään

### 🔧 Parannukset

#### Riippuvuuksien päivitykset
- **iTextPDF**: 5.5.13.4 (entinen versio vanhempi)
- **SQLite JDBC**: 3.47.1.0 (entinen 3.7.15)
- **MySQL Connector**: 9.1.0 (entinen vanhempi versio)
- **PostgreSQL JDBC**: 42.7.4 (entinen vanhempi versio)
- **SLF4J**: 2.0.16 (uusi, entinen 1.7.36)
- **OpenCSV**: 5.9 (uusi lisäys)

#### Koodin laatu
- **Modernit API:t**: Päivitetty käyttämään OpenCSV:n uutta API:a (CSVReaderBuilder)
- **Parempi virheenkäsittely**: CSV-tuonnissa kattavampi virheenkäsittely
- **Koodin siisteys**: Poistettu käyttämätön koodi ja kommentit

### 🐛 Korjaukset

#### Mac-yhteensopivuus
- **Tekstikentän bugi**: Korjattu ongelma, jossa tekstikentän ensimmäinen merkki katosi Macilla
- **ARM-tuki**: SQLite JDBC päivitetty tukemaan ARM-arkkitehtuuria

#### PDF-generointi
- **iText API**: Päivitetty käyttämään uuden iTextPDF:n API:a
- **Fonttien käsittely**: Korjattu BaseFont-viittaukset

#### CSV-tuonti
- **Tilinumeroiden haku**: Korjattu tilinumeroiden haku käyttämään AccountDAO.getAll()-metodia
- **CSV-erottimen käsittely**: Parannettu puolipisteen käsittelyä CSV-tiedostoissa
- **Virheilmoitukset**: Selkeämmät virheilmoitukset puuttuvista tileistä

### 📁 Projektirakenne

#### Uusi Maven-rakenne
```
tilitin-masterPriku/
├── src/
│   └── main/
│       ├── java/                    # Java-lähdekoodit
│       │   └── kirjanpito/
│       └── resources/               # Resurssit
│           ├── kirjanpito/
│           └── tilikarttamallit/
├── pom.xml                          # Maven-konfiguraatio
├── README.md                        # Pääohje
├── CHANGELOG.md                     # Tämä tiedosto
└── COPYING                          # GPL-lisenssi
```

Vanha Ant-rakenne (poistettu):
- `build.xml` → Korvattu `pom.xml`:llä
- `lib/` → Riippuvuudet hallinnoidaan Mavenilla
- `src/` → `src/main/java/`

### 📚 Dokumentaatio

- **README.md**: Kattava käyttöopas suomeksi
- **CHANGELOG.md**: Yksityiskohtainen muutosloki
- **pom.xml**: Selkeästi kommentoitu Maven-konfiguraatio

### 🙏 Kiitokset

Tämä versio yhdistää useiden kehittäjien työn:

- **Tommi Helineva** - Alkuperäinen Tilitin (v1.5.0)
- **Jouni Seppänen (jkseppan)** - Java 21, Maven, ARM Mac -tuki
- **Eetu Kallio (Kallio95)** - CSV/Procountor-tuonti
- **Priku** - Versioiden yhdistäminen ja dokumentointi

### 🔗 Lähteet

- Alkuperäinen Tilitin: https://helineva.net/tilitin/
- Jkseppan-versio: https://github.com/jkseppan/tilitin
- Kallio95-versio: www.ekallio.fi/share/

---

## Aikaisemmat versiot

### Versio 1.5.1 (Kallio95)
- Lisätty CSV/Procountor-tuonti
- Päivitetty SQLite JDBC 13.5 MB versioon
- Lisätty OpenCSV 3.8

### Versio 1.5.0 (Helineva)
- Alkuperäinen vakaa versio
- Täydet kirjanpito-ominaisuudet
- Tuki SQLite, MySQL ja PostgreSQL -tietokannoille

---

**Huomio**: Versio 1.6.0-priku.1 on ensimmäinen yhdistetty versio, joka sisältää kaikki edellä mainitut parannukset yhtenäisessä paketissa.
