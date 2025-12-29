# Muutosloki - Tilitin Priku

## [1.6.0] - 2025-12-29

### 🆕 Uudet ominaisuudet

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
