# Tilitin - Yhdistetty versio v1.6.1

Tilitin on ilmainen kirjanpito-ohjelma suomalaisille yrityksille ja yhdistyksille. Tämä on **yhdistetty versio**, joka sisältää parhaat ominaisuudet eri kehittäjien versioista.

---

## 🆕 Tilitin 2.1 saatavilla!

**Uusi versio modernilla JavaFX-käyttöliittymällä:** [priku/tilitin-v2](https://github.com/priku/tilitin-v2)

- ✨ 27 uutta JavaFX-dialogia
- 🎨 Tumma ja vaalea teema
- 📎 PDF-liitteet tositteisiin
- 📊 CSV-tuonti pankkitiliotteista
- ⚙️ Vapaamuotoiset ALV-prosentit (25,5%, 14% jne.)

---

## ⚠️ Tietokantayhteensopivuus

### Tärkeää tietää versiosta v1.6.2+

Versiot 1.6.2 ja uudemmat päivittävät tietokannan **versiosta 13 → 14** ensimmäisellä avauksella. Tämä mahdollistaa vapaamuotoiset ALV-prosentit.

| Suunta | Toimii | Selitys |
|--------|--------|---------|
| Helineva 1.6.1 → v1.6.2+ | ✅ Kyllä | Päivittyy automaattisesti |
| v1.6.2+ → Helineva 1.6.1 | ❌ Ei | Vanha versio ei ymmärrä uutta skeemaa |

**💾 Ota varmuuskopio ennen ensimmäistä avausta uudemmalla versiolla!**

---

## 🎯 Mitä tämä versio sisältää?

Tämä **tilitin-masterPriku** -versio yhdistää:

### ✅ Tommi Helinevan alkuperäinen Tilitin (v1.5.0)
- Vakaa ja testattu pohja
- Täydet kirjanpito-ominaisuudet

### ✅ Jouni Seppäsen (jkseppan) modernisoinnit
- **Java 21** -tuki (uusin LTS-versio)
- **ARM Mac -tuki** (toimii uusilla M1/M2/M3-Maceilla)
- **Maven-rakennusjärjestelmä** (modernimpi kuin Ant)
- **Päivitetyt kirjastot**:
  - iTextPDF 5.5.13.4
  - SQLite JDBC 3.47.1.0
  - MySQL Connector 9.1.0
  - PostgreSQL JDBC 42.7.4
  - SLF4J 2.0.16
- **Sisäänrakennetut tilikartat** (ei tarvitse kopioida erikseen)
- **Mac-bugien korjaukset** (tekstikentän ensimmäinen merkki ei enää katoa)
- **Dynaaminen versiointi** (versio luetaan JAR-manifestista)

### ✅ Eetu Kallion (Kallio95) lisäominaisuudet
- **CSV/Procountor-tuonti** - tuo tilitapahtumia suoraan verkkopankista
- **OpenCSV 5.9** -kirjasto CSV-käsittelyyn

## 📦 Asennus

### Lataa valmis paketti (suositeltu)

Lataa käyttöjärjestelmällesi sopiva asennuspaketti [Releases-sivulta](https://github.com/priku/tilitin/releases/latest):

| Käyttöjärjestelmä | Tiedosto | Kuvaus |
|-------------------|----------|--------|
| 🪟 **Windows** | `Tilitin-X.X.X-Setup.exe` | Sisältää Javan, ei vaadi asennuksia |
| 🍎 **macOS (Apple Silicon)** | `Tilitin-X.X.X-arm64.dmg` | M1/M2/M3/M4 - Sisältää Javan |
| 🍎 **macOS (Intel)** | `Tilitin-X.X.X.dmg` | Sisältää Javan, ei vaadi asennuksia |
| 🐧 **Ubuntu/Debian** | `tilitin_X.X.X-1_amd64.deb` | Sisältää Javan, ei vaadi asennuksia |
| 🐧 **Fedora/Red Hat** | `tilitin-X.X.X-1.x86_64.rpm` | Sisältää Javan, ei vaadi asennuksia |
| ☕ **Kaikki (JAR)** | `tilitin-X.X.X-priku.1.jar` | **Vaatii Java 21+** (katso alta) |

> **💡 Vinkki:** DMG/EXE/DEB/RPM-paketit ovat **valmiita käyttöön** - ne sisältävät Java-ajon mukana eikä vaadi mitään asennuksia. JAR-versio on vaihtoehto jos haluat käyttää omaa Java-asennustasi.

#### macOS-käyttäjille: "Vahingollinen"-varoitus

Jos macOS valittaa että sovellus on "vahingollinen" tai "ei voida avata", toimi näin:

**Vaihtoehto A - Järjestelmäasetukset (suositeltu):**
1. Yritä avata Tilitin.app
2. Avaa **Järjestelmäasetukset** → **Tietosuoja ja turvallisuus**
3. Vieritä alas, näet ilmoituksen _"Tilitin" estettiin_
4. Klikkaa **Avaa silti** -painiketta
5. Vahvista **Avaa**

**Vaihtoehto B - Pikanäppäin:**
1. **Control** + klikkaa Tilitin.app
2. Valitse **Avaa** valikosta
3. Vahvista **Avaa**

**Vaihtoehto C - Terminaali:**
```bash
xattr -cr /Applications/Tilitin.app
```

**Vaihtoehto D - Käytä JAR-versiota:**
- JAR-tiedosto ohittaa macOS Gatekeeperin kokonaan
- **Vaatii Java 21:n asennuksen** (katso ohjeet alta)
- Toimii varmasti kaikilla alustoilla

> **Miksi tämä tapahtuu?** Tilitin on avoimen lähdekoodin ohjelma ilman maksullista Apple Developer -allekirjoitusta ($99/vuosi). Ohjelma on täysin turvallinen - lähdekoodi on julkisesti tarkasteltavissa GitHubissa.

---

### Vaihtoehtoinen asennus: JAR-tiedosto

**Käytä JAR-versiota jos:**
- DMG ei toimi koneellasi
- Haluat käyttää omaa Java-asennustasi
- Tarvitset täyden hallinnan Java-ajoympäristöön

#### 1. Asenna Java 21

**JAR-versio vaatii** Java 21:n tai uudemman. Suosittelen OpenJDK-versiota:
- [Azul Zulu JDK 21](https://www.azul.com/downloads/#zulu) (suositeltu)
- [Eclipse Adoptium JDK 21](https://adoptium.net/)

**Tarkista asennus:**
```bash
java -version
```

#### 2. Lataa JAR-tiedosto

Lataa `tilitin-1.6.1-priku.1.jar` [Releases-sivulta](https://github.com/priku/tilitin/releases/latest)

#### 3. Käynnistä ohjelma

**Windows:**
```bash
java -jar tilitin-1.6.1-priku.1.jar
```

**Mac/Linux:**
```bash
java -jar tilitin-1.6.1-priku.1.jar
```

### Kehittäjille: Käännä projekti itse

```bash
mvn clean package
```

Tämä luo `target/tilitin-1.6.1-priku.1.jar` -tiedoston.

**Mac-käyttäjille:** Jos saat varoituksen epäilyttävästä ohjelmistosta:
1. Klikkaa JAR-tiedostoa hiiren oikealla painikkeella (Ctrl + klikkaus)
2. Valitse "Avaa"
3. Vahvista avaaminen

## 🚀 Pääominaisuudet

### Kirjanpito
- ✅ Täysi kaksinkertainen kirjanpito
- ✅ Useita tilikausia
- ✅ Vientimallit nopeaan kirjaukseen
- ✅ Tositteiden hallinta
- ✅ ALV-laskenta ja -raportointi

### Tietokannat
- ✅ SQLite (oletus, ei asennusta vaadi)
- ✅ MySQL/MariaDB
- ✅ PostgreSQL

### Raportit
- ✅ Tase
- ✅ Tuloslaskelma
- ✅ Päiväkirja
- ✅ Pääkirja
- ✅ Tililuettelo
- ✅ ALV-ilmoitus
- ✅ PDF-vienti

### 🆕 CSV/Procountor-tuonti (UUSI!)

Voit tuoda tilitapahtumat suoraan verkkopankin Procountor-yhteensopivasta CSV-tiedostosta.

**Käyttö:**
1. Lataa CSV-tiedosto verkkopankistasi (Procountor-muoto)
2. Valitse Tilittimestä: **Muokkaa → CSV-tuonti (Procountor)**
3. Syötä tiedostopolku (esim. `C:\Users\käyttäjä\Desktop\tuonti.csv`)
4. Paina OK

**HUOM:**
- Varmista että kaikki CSV:ssä olevat tilit löytyvät Tilittimestä
- Testaa tuontia ensin uudella tilikaudella
- Kaikki tapahtumat tuodaan nykyiselle tilikaudelle

## 📂 Tilikartat

Mukana tulee valmiit tilikartat:
- 📊 Asunto-osakeyhtiö
- 📊 Elinkeinotoiminta (ALV 22%, 23%, 24%)
- 📊 Tiekunta
- 📊 Yhdistys
- 📊 Yhteisen vesialueen osakaskunta

## 🛠️ Kehittäjille

### CI/CD ja julkaisut

Projekti käyttää **GitHub Actions** -automaatiota:

- ✅ **Automaattinen buildaus** jokaisesta commitista
- ✅ **Multi-platform release** - kaikki paketit luodaan automaattisesti
- ✅ **Release notes** luetaan automaattisesti CHANGELOG.md:stä

#### Release-prosessi

1. Päivitä versio `pom.xml`:ssä
2. Lisää muutokset `CHANGELOG.md`:hen
3. Luo ja pushaa tagi: `git tag -a v1.6.1 -m "Release 1.6.1" && git push origin v1.6.1`
4. GitHub Actions rakentaa automaattisesti kaikki paketit

### Rakennusjärjestelmä
Projekti käyttää **Maven 3.6+** -rakennusjärjestelmää.

### Rakenne
```
tilitin-masterPriku/
├── src/
│   └── main/
│       ├── java/          # Java-lähdekoodit
│       │   └── kirjanpito/
│       └── resources/     # Resurssit (kuvat, tilikartat, SQL)
│           ├── kirjanpito/
│           └── tilikarttamallit/
├── pom.xml               # Maven-konfiguraatio
└── README.md
```

### Maven-komennot

```bash
# Käännä projekti
mvn compile

# Aja testit
mvn test

# Luo JAR-paketti
mvn package

# Puhdista build-hakemisto
mvn clean

# Käännä ja luo JAR yhdellä komennolla
mvn clean package
```

### IDE-asetukset
- **IntelliJ IDEA**: Avaa `pom.xml` projektina
- **Eclipse**: Import → Existing Maven Projects
- **VS Code**: Asenna Java Extension Pack

## 📄 Lisenssi

Tämä on vapaa ohjelma: tätä ohjelmaa saa levittää edelleen ja muuttaa **GNU General Public License (GPL) version 3** ehtojen mukaisesti.

Tätä ohjelmaa levitetään siinä toivossa, että se olisi hyödyllinen, mutta **ilman mitään takuuta**; edes hiljaista takuuta kaupallisesti hyväksyttävästä laadusta tai soveltuvuudesta tiettyyn tarkoitukseen.

Katso [COPYING](COPYING) -tiedostosta lisätietoja.

## 🙏 Kiitokset

- **Tommi Helineva** - Alkuperäinen Tilitin (https://helineva.net/tilitin/)
- **Jouni Seppänen (jkseppan)** - Java 21 -päivitys, Mac-tuki, Maven-siirto
- **Eetu Kallio (Kallio95)** - CSV/Procountor-tuonti

## 🐛 Bugit ja ominaisuuspyynnöt

Jos löydät bugin tai haluat ehdottaa uutta ominaisuutta, luo issue GitHubissa.

## 📚 Dokumentaatio

### Priku-dokumentaatio

- 📘 [Asennusohje](docs/ASENNUS.md) - Yksityiskohtaiset asennusohjeet
- 📥 [CSV-tuonti-opas](docs/CSV_TUONTI.md) - Tuo tilitapahtumat verkkopankista
- 🔄 [Versiovertailu](docs/VERSIOT_VERTAILU.md) - Miksi Priku on paras valinta?
- 🛠️ [Kehittäjän opas](CONTRIBUTING.md) - Aloita kehittäminen
- 🏗️ [Tekninen dokumentaatio](docs/TEKNINEN_DOKUMENTAATIO.md) - Arkkitehtuuri ja toteutus
- 📝 [Muutosloki](CHANGELOG.md) - Versiohistoria

### Alkuperäinen dokumentaatio

Tommi Helinevan sivuilta:
- https://helineva.net/tilitin/
- https://helineva.net/tilitin/ohjeet/

## ⚡ Pikaohjeet

### Uuden kirjanpidon aloittaminen
1. Käynnistä Tilitin
2. Tiedosto → Uusi tietokanta
3. Valitse tilikartta
4. Täytä yrityksen perustiedot
5. Aloita kirjaaminen!

### Tositteen luominen
1. Klikkaa "Uusi tosite" (tai paina Insert)
2. Valitse päivämäärä
3. Lisää viennit (Debet ja Kredit tasapainoon)
4. Tallenna

### Raportin tulostaminen
1. Tulosteet → Valitse raportti
2. Valitse aikaväli
3. Esikatsele tai tulosta PDF:ksi

---

**Versio:** 1.6.0-priku.1
**Java-versio:** 21+
**Käännöstyökalu:** Maven 3.6+
**Viimeisin päivitys:** 2025-12-29
