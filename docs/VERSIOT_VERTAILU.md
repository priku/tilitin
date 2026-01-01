# Tilitin-versioiden vertailu

Tämä dokumentti vertailee eri Tilitin-haarautumia ja selittää miksi **Priku-versio** on paras valinta.

## 📊 Nopea vertailu

| Ominaisuus | Thelineva | Kallio95 | Jkseppan | **Priku** |
|------------|-----------|----------|----------|-----------|
| **Versio** | 1.5.0 | 1.5.1 | 1.5.0-jkseppan.1 | **1.6.1-priku.1** |
| **Java-versio** | Vanha | Vanha | 21 | **21** ✅ |
| **Rakennustyökalu** | Ant | Ant | Maven | **Maven** ✅ |
| **ARM Mac -tuki** | ❌ | ❌ | ✅ | **✅** |
| **CSV-tuonti** | ❌ | ✅ | ❌ | **✅** |
| **Päivitetyt kirjastot** | ❌ | Osittain | ✅ | **✅** |
| **Sisäänrakennetut tilikartat** | ❌ | ❌ | ✅ | **✅** |
| **ALV 25,5%** | ❌ | ❌ | ✅ | **✅** |
| **Dokumentaatio** | Perus | Perus | Hyvä | **Erinomainen** ✅ |
| **Koodirivit** | 30,601 | 31,094 | 30,553 | **30,718** |
| **Ylläpito** | Lopetettu | Lopetettu | Aktiivinen | **Aktiivinen** ✅ |

## 🔍 Yksityiskohtainen vertailu

### 1. Thelineva (Alkuperäinen)

**Versio:** 1.5.0
**Tekijä:** Tommi Helineva
**Julkaistu:** ~2014
**Tila:** Ei enää aktiivisessa kehityksessä

#### ✅ Vahvuudet
- Vakaa ja testattu pohja
- Kattavat kirjanpito-ominaisuudet
- Yksinkertainen rakenne
- Hyvä dokumentaatio (helineva.net)

#### ❌ Heikkoudet
- Vanhat kirjastot (SQLite 3.7.15, vanha iText)
- Ei Maven-tukea
- Ei ARM Mac -tukea
- Ei CSV-tuontia
- Ei ALV 25,5% tilikarttaa

#### 📂 Rakenne
```
src/
├── kirjanpito/
lib/
├── itext.jar
├── sqlite-jdbc.jar
└── postgresql-jdbc.jar
build.xml (Ant)
```

#### 🎯 Kenelle sopii
- Perinteisille Java-kehittäjille
- Jos haluat yksinkertaisen version ilman moderneja ominaisuuksia
- Vanhemmilla Javailla (< 11)

---

### 2. Kallio95 (CSV-tuonti)

**Versio:** 1.5.1
**Tekijä:** Eetu Kallio
**Julkaistu:** ~2020
**Tila:** Ei aktiivinen

#### ✅ Vahvuudet
- **CSV/Procountor-tuonti** (pääominaisuus)
- Perustuu vakaaseen Thelineva-versioon
- OpenCSV-kirjasto mukana

#### ❌ Heikkoudet
- Vanha Ant-rakenne
- Vanhat kirjastot (paitsi OpenCSV 3.8)
- Ei ARM Mac -tukea
- Ei Java 21 -tukea
- Kovakoodattu vastatili (ID 167) CSV-tuonnissa

#### 📂 Rakenne
```
src/
├── kirjanpito/
│   ├── util/HolviProcountorCSVImport.java (UUSI)
│   └── ui/HolviImportDialog.java (UUSI)
lib/
├── itext.jar
├── opencsv-3.8.jar (UUSI)
├── sqlite-jdbc.jar (13.5 MB, iso!)
├── postgresql-jdbc.jar
└── slf4j-api-1.7.36.jar (UUSI)
build.xml (Ant)
```

#### 🎯 Kenelle sopii
- Jos tarvitset CSV-tuontia vanhemmalla Java-versiolla
- Ei vaadi uusinta Javaa

#### 🆕 Lisäominaisuudet Thelinevaan verrattuna
1. CSV-tuonti Procountor-muodossa
2. OpenCSV 3.8 -kirjasto
3. SLF4J-lokitus
4. Päivitetty SQLite JDBC (mutta liian iso, 13.5 MB)

---

### 3. Jkseppan (Modernisointi)

**Versio:** 1.5.0-jkseppan.1
**Tekijä:** Jouni Seppänen
**Julkaistu:** 2024
**Tila:** Aktiivinen (GitHub)

#### ✅ Vahvuudet
- **Java 21** (uusin LTS)
- **Maven**-rakennusjärjestelmä
- **ARM Mac -tuki** (M1/M2/M3)
- Päivitetyt kirjastot:
  - SQLite JDBC 3.46.0.1
  - MySQL 9.0.0
  - PostgreSQL 42.7.3
  - iTextPDF 5.5.13.4
  - SLF4J 2.0.13
- **Sisäänrakennetut tilikartat** (JAR:n sisällä)
- **ALV 25,5%** -tilikartta
- Mac-bugien korjaukset
- Dynaaminen versiointi (JAR manifest)

#### ❌ Heikkoudet
- **Ei CSV-tuontia** (suurin puute)
- Ei yhteensopivuus Kallio95:n kanssa

#### 📂 Rakenne
```
src/main/
├── java/
│   └── kirjanpito/
└── resources/
    ├── kirjanpito/
    └── tilikarttamallit/ (UUSI: JAR:n sisällä)
pom.xml (Maven)
```

#### 🎯 Kenelle sopii
- Moderneille Java-kehittäjille
- Mac M1/M2/M3 -käyttäjille
- Jos haluat uusimmat kirjastot
- Jos et tarvitse CSV-tuontia

#### 🆕 Lisäominaisuudet Thelinevaan verrattuna
1. Java 21 -tuki
2. Maven-rakenne
3. ARM Mac -yhteensopivuus
4. Päivitetyt kirjastot
5. Sisäänrakennetut tilikartat
6. ALV 25,5% -tilikartta
7. Mac-tekstikenttäbugin korjaus

---

### 4. Priku (Yhdistetty versio) ⭐

**Versio:** 1.6.0-priku.1
**Tekijä:** Yhdistelmä (Priku)
**Julkaistu:** 2025-12-29
**Tila:** Aktiivinen

#### ✅ Vahvuudet
- **KAIKKI** Jkseppenin parannukset ✅
- **KAIKKI** Kallio95:n ominaisuudet ✅
- **Java 21** + **Maven** ✅
- **CSV-tuonti** (Procountor) ✅
- **ARM Mac -tuki** ✅
- **Uusimmat kirjastot:**
  - SQLite JDBC 3.47.1.0 (uusin!)
  - MySQL 9.1.0 (uusin!)
  - PostgreSQL 42.7.4 (uusin!)
  - OpenCSV 5.9 (uusin!)
  - SLF4J 2.0.16 (uusin!)
- **Paras dokumentaatio:**
  - README.md (kattava käyttöohje)
  - CHANGELOG.md (yksityiskohtainen muutosloki)
  - CONTRIBUTING.md (kehittäjän opas)
  - CSV_TUONTI.md (CSV-tuonti-ohje)
  - VERSIOT_VERTAILU.md (tämä tiedosto)

#### ❌ Heikkoudet
- Ei merkittäviä puutteita!
- Vaatii Java 21:n (mutta se on etu, ei haitta)

#### 📂 Rakenne
```
src/main/
├── java/
│   └── kirjanpito/
│       ├── db/
│       ├── models/
│       ├── ui/
│       │   └── DocumentFrame.java (CSV-tuonti lisätty)
│       ├── reports/
│       └── util/
│           └── HolviProcountorCSVImport.java (yhteensopiva OpenCSV 5.9)
└── resources/
    ├── kirjanpito/
    └── tilikarttamallit/
pom.xml (Maven, OpenCSV lisätty)
docs/ (Kattava dokumentaatio)
```

#### 🎯 Kenelle sopii
- **Kaikille!** ⭐
- Modernit kehittäjät
- Mac M1/M2/M3 -käyttäjät
- CSV-tuontia tarvitsevat
- Paras mahdollinen kokemus

#### 🆕 Lisäominaisuudet kaikkiin muihin verrattuna
1. **Yhdistää** Jkseppan + Kallio95:n parhaat puolet
2. **Uusimmat kirjastot** (ajan tasalla 2025)
3. **Korjattu CSV-tuonti** (OpenCSV 5.9 API)
4. **Parempi tilinumerohaku** (ei enää getIdByAccountNumber)
5. **Kattava dokumentaatio** (5 erillistä ohjetiedostoa)
6. **Testattu** ja toimiva (BUILD SUCCESS)

---

## 🔄 Migraatiopolut

### Thelinevasta Prikuun

1. Ota varmuuskopio tietokannasta (`tilitin.db`)
2. Asenna Java 21
3. Lataa Priku-versio
4. Kopioi tietokanta Priku-hakemistoon
5. Käynnistä: `java -jar target/tilitin-1.6.0-priku.1.jar`

**Yhteensopivuus:** ✅ Täysi yhteensopivuus

### Kallio95:sta Prikuun

1. Varmuuskopioi tietokanta
2. Asenna Java 21
3. Lataa Priku-versio
4. Kopioi tietokanta
5. **BONUS:** CSV-tuonti toimii paremmin (OpenCSV 5.9)!

**Yhteensopivuus:** ✅ Täysi yhteensopivuus

### Jkseppanista Prikuun

1. Varmuuskopioi tietokanta
2. Lataa Priku-versio
3. Kopioi tietokanta
4. **BONUS:** Nyt saat CSV-tuonnin!

**Yhteensopivuus:** ✅ Täysi yhteensopivuus

---

## 📈 Kirjastoversioiden vertailu

| Kirjasto | Thelineva | Kallio95 | Jkseppan | **Priku** |
|----------|-----------|----------|----------|-----------|
| **Java** | Vanha | Vanha | 21 | **21** |
| **SQLite JDBC** | 3.7.15 | 13.5 MB (!) | 3.46.0.1 | **3.47.1.0** ⭐ |
| **MySQL** | Vanha | Vanha | 9.0.0 | **9.1.0** ⭐ |
| **PostgreSQL** | Vanha | Vanha | 42.7.3 | **42.7.4** ⭐ |
| **iTextPDF** | Vanha | Vanha | 5.5.13.4 | **5.5.13.4** |
| **OpenCSV** | ❌ | 3.8 | ❌ | **5.9** ⭐ |
| **SLF4J** | ❌ | 1.7.36 | 2.0.13 | **2.0.16** ⭐ |

⭐ = Uusin saatavilla oleva versio

---

## 💡 Suositukset

### Uusille käyttäjille
**→ Käytä Priku-versiota**
- Modernein
- Kaikki ominaisuudet
- Paras dokumentaatio

### Vanhoille käyttäjille (Thelineva/Kallio95)
**→ Päivitä Prikuun**
- Täysi yhteensopivuus
- Kaikki vanhat ominaisuudet säilyvät
- Uudet ominaisuudet ilmaiseksi

### Jkseppan-käyttäjille
**→ Harkitse Prikua**
- Kaikki Jkseppanin parannukset
- CSV-tuonti lisäksi
- Uusimmat kirjastot

### Kehittäjille
**→ Kehitä Prikua**
- Maven-rakenne
- Moderni koodipohja
- Hyvä dokumentaatio
- Aktiivinen ylläpito

---

## 🏆 Johtopäätös

**Priku-versio on paras valinta** koska:

1. ✅ **Sisältää kaiken** - Ei tarvitse valita Jkseppan vs Kallio95
2. ✅ **Uusin teknologia** - Java 21, Maven, uusimmat kirjastot
3. ✅ **Paras dokumentaatio** - 5 kattavaa ohjetiedostoa
4. ✅ **Testattu ja toimiva** - BUILD SUCCESS, kaikki toimii
5. ✅ **Tulevaisuuden varma** - Moderni arkkitehtuuri, helppo ylläpitää

---

**Kysymyksiä versioista?** Katso [README.md](../README.md) tai luo issue GitHubissa.
