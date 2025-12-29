# CSV-tuonti - Käyttöohje

Tilitin Priku tukee tilitapahtumien tuomista Procountor-yhteensopivasta CSV-tiedostosta. Tämä ominaisuus on erityisen hyödyllinen, kun haluat siirtää tapahtumia verkkopankista Tilittimeen.

## 📋 Sisällysluettelo

- [Tuetut formaatit](#tuetut-formaatit)
- [CSV-tiedoston valmistelu](#csv-tiedoston-valmistelu)
- [Tuonnin suorittaminen](#tuonnin-suorittaminen)
- [Yleisiä ongelmia](#yleisiä-ongelmia)
- [Tekninen kuvaus](#tekninen-kuvaus)

## 🎯 Tuetut formaatit

### Procountor CSV

Tilitin tukee Procountor-muotoista CSV-tiedostoa, jossa:
- **Erotin**: Puolipiste (`;`)
- **Merkistö**: UTF-8
- **Päivämäärä**: `YYYY-MM-DD` muoto (esim. `2025-12-29`)
- **Desimaalierottaja**: Pilkku (`,`) tai piste (`.`)

### CSV-tiedoston rakenne

CSV-tiedostossa tulee olla vähintään seuraavat sarakkeet (0-indeksoitu):

| Indeksi | Sarake | Kuvaus | Esimerkki |
|---------|--------|--------|-----------|
| 2 | Päivämäärä | Tapahtuman päivämäärä | `2025-12-29` |
| 5 | Määrä | Summa (+ tai -) | `150,00` tai `-75,50` |
| 7 | Flag | Tyhjä = ohitetaan rivi | ei tyhjä |
| 8 | Selite | Tapahtuman kuvaus | `Lasku 123` |
| 13 | Tilinumero | Vastatili | `1910` |

**Huom:** Rivi tuodaan vain jos sarake [7] ei ole tyhjä.

### Esimerkki CSV-tiedostosta

```csv
001;Type;2025-12-29;Ref123;Category;150,00;EUR;X;Lasku asiakkaalta;;;;;1910
002;Type;2025-12-28;Ref124;Category;-75,50;EUR;X;Ostos kaupasta;;;;;1910
```

## 📝 CSV-tiedoston valmistelu

### 1. Lataa tiedosto verkkopankista

**Nordea:**
1. Kirjaudu verkkopankkiin
2. Valitse tili → Tapahtumat
3. Valitse aikaväli
4. Lataa CSV (Procountor-muoto)

**OP:**
1. Kirjaudu verkkopankkiin
2. Tilit → Tapahtumat
3. Vie → CSV (Procountor)

**Danske Bank:**
1. Kirjaudu verkkopankkiin
2. Tilitapahtumat
3. Lataa → Procountor CSV

### 2. Varmista tiedoston muoto

Avaa tiedosto tekstieditorissa (Notepad++, VS Code) ja tarkista:

✅ **Oikein:**
```
001;Type;2025-12-29;Ref;Cat;150,00;EUR;X;Lasku;;;;;1910
```

❌ **Väärin (tabulaattori-erotin):**
```
001    Type    2025-12-29    Ref    Cat    150,00
```

❌ **Väärin (pilkku-erotin):**
```
001,Type,2025-12-29,Ref,Cat,150,00,EUR
```

### 3. Varmista että tilit löytyvät Tilittimestä

**TÄRKEÄÄ:** Ennen tuontia tarkista että kaikki CSV:ssä esiintyvät tilinumerot löytyvät Tilittimestä!

1. Avaa Tilitin
2. Valitse **Muokkaa → Tilikartta**
3. Tarkista että tilinumerot vastaavat CSV-tiedostossa olevia numeroita

**Esimerkki:**
- Jos CSV:ssä on tilinumero `1910` (Pankki), varmista että Tilittimessä on tili `1910`
- Jos tilinumeroa ei löydy, lisää se ennen tuontia

## 🚀 Tuonnin suorittaminen

### Vaihe 1: Avaa CSV-tuonti

1. Käynnistä Tilitin
2. Avaa tietokanta tai luo uusi
3. Valitse **Muokkaa → CSV-tuonti (Procountor)**

![CSV-tuonti valikko](images/csv-import-menu.png)

### Vaihe 2: Valitse tiedosto

1. Syötä CSV-tiedoston polku

**Windows:**
```
C:\Users\käyttäjä\Downloads\tapahtumat.csv
```

**Mac/Linux:**
```
/Users/käyttäjä/Downloads/tapahtumat.csv
```

2. Klikkaa **OK**

### Vaihe 3: Tarkista tulos

Onnistuneesta tuonnista näkyy ilmoitus:
```
Tuonti onnistui!
25 tilitapahtumaa tuotiin.
```

Jos tuonti epäonnistuu, näet virheilmoituksen:
```
Virhe CSV-tuonnissa.
Tarkista CSV-tiedosto, poista tilikausi ja yritä tuontia uudelleen.
Virhe: Tiliä numerolla 1910 ei löydy!
```

### Vaihe 4: Tarkista tuodut tapahtumat

1. Selaa tositteita nuolinäppäimillä tai Page Up/Down
2. Tarkista että:
   - Päivämäärät ovat oikein
   - Summat täsmäävät
   - Tilit ovat oikein
   - Selitteet näkyvät

## ⚠️ Tärkeät huomiot

### Ennen tuontia

1. **📊 Testaa ensin uudella tilikaudella**
   - Luo testikausi
   - Tuo tapahtumat sinne
   - Tarkista että kaikki näyttää oikealta
   - Poista testikausi tarvittaessa

2. **💾 Varmuuskopioi tietokanta**
   ```
   Kopioi tilitin.db tiedosto turvaan ennen tuontia!
   ```

3. **🔍 Tarkista tilikartta**
   - Varmista että kaikki tarvittavat tilit löytyvät
   - Lisää puuttuvat tilit tarvittaessa

### Tuonnin aikana

- ⏳ Tuonti voi kestää hetken suurilla tiedostoilla
- 🚫 Älä sulje ohjelmaa kesken tuonnin
- 📝 Konsoliin tulostuu debug-tietoa (tarvittaessa)

### Tuonnin jälkeen

1. **Tarkista tositteet**
   - Selaa läpi tuodut tositteet
   - Varmista että kaikki näyttää oikealta

2. **Korjaa virheet**
   - Jos löydät virheitä, korjaa ne manuaalisesti
   - Tai poista virheelliset tositteet ja tuo uudelleen

3. **Tallenna**
   - Tietokanta tallentuu automaattisesti
   - Sulje ohjelma normaalisti (ei Ctrl+C)

## 🐛 Yleisiä ongelmia

### Virhe: "Tiliä numerolla X ei löydy!"

**Syy:** CSV-tiedostossa on tilinumero, jota ei löydy Tilittimestä.

**Ratkaisu:**
1. Avaa **Muokkaa → Tilikartta**
2. Lisää puuttuva tili:
   - Numero: `X` (sama kuin virheessä)
   - Nimi: Tilin nimi (esim. "Nordea tili")
   - Tyyppi: Valitse sopiva
3. Tallenna
4. Yritä tuontia uudelleen

### Virhe: "no suitable constructor found for CSVReader"

**Syy:** Vanha OpenCSV-versio.

**Ratkaisu:**
Tämä ei pitäisi tapahtua Priku-versiossa. Jos kuitenkin tapahtuu:
```bash
mvn clean package
```

### Tuonti onnistuu, mutta tositteita ei näy

**Syy:** Tositteet tuotu väärän tilikaudelle.

**Ratkaisu:**
1. Tarkista aktiivinen tilikausi (**Muokkaa → Perustiedot**)
2. Vaihda oikea tilikausi
3. Tai tuo uudelleen oikealle tilikaudelle

### CSV-tiedosto ei avaudu

**Syy:** Väärä merkistökoodaus (esim. ISO-8859-1 UTF-8:n sijaan)

**Ratkaisu:**
1. Avaa CSV Notepad++:ssa tai VS Codessa
2. Valitse **Encoding → Convert to UTF-8**
3. Tallenna
4. Yritä tuontia uudelleen

### Desimaalit väärin (esim. 150 € → 15000 €)

**Syy:** Virheellinen desimaalierottaja CSV:ssä.

**Tarkista:**
- Oikein: `150,00` tai `150.00`
- Väärin: `15000` (ilman desimaaleja)

**Ratkaisu:**
1. Muokkaa CSV-tiedostoa tekstieditorissa
2. Varmista että kaikki summat ovat muodossa `X,XX` tai `X.XX`
3. Tallenna ja tuo uudelleen

## 🔧 Tekninen kuvaus

### Mitä tuonti tekee?

1. **Lukee CSV-tiedoston** rivi kerrallaan
2. **Tarkistaa rivin** (sarake [7] ei saa olla tyhjä)
3. **Luo tositteen** (Document) päivämäärällä sarake [2]
4. **Luo kaksi vientiä** (Entry):
   - **Vienti 1**: Vastatili (ID 167, kovakoodattu)
   - **Vienti 2**: Tili CSV:stä (sarake [13])
5. **Tallentaa** tietokantaan

### Kovakoodatut arvot

⚠️ **Huomio:** Ensimmäinen vienti käyttää aina tiliä ID `167`!

```java
entry.setAccountId(167); // TODO: Muuta oletusvastatilit parametriseksi
```

**Tämä tarkoittaa:**
- Kaikki tuodut tapahtumat käyttävät tiliä ID 167 vastatilina
- Jos haluat muuttaa tämän, muokkaa lähdekoodia:
  - `DocumentFrame.java` rivi ~3653
  - Vaihda `167` haluamaksesi tilin ID:ksi

### CSV-sarakkeiden käyttö

```java
nextLine[2]  // Päivämäärä (YYYY-MM-DD)
nextLine[5]  // Summa (esim. "150,00")
nextLine[7]  // Flag (tyhjä = ohita)
nextLine[8]  // Selite
nextLine[13] // Tilinumero
```

### Virheenkäsittely

Tuonti **keskeyttää** jos:
- CSV-tiedosto puuttuu tai ei avaudu
- Tilinumeroa ei löydy tietokannasta
- Päivämäärä on virheellisessä muodossa
- Summa ei ole numero

Tuonti **jatkaa** jos:
- Rivi on tyhjä (sarake [7] tyhjä)
- Riviä ei ole (EOF)

## 📚 Lisätietoja

### Lähdekoodi

CSV-tuonti toteutettu tiedostoissa:
- `DocumentFrame.java` - Päälogiikka (rivit 3548-3717)
- `HolviImportDialog.java` - Käyttöliittymä
- `HolviProcountorCSVImport.java` - Vanha toteutus (ei käytössä)

### Riippuvuudet

- **OpenCSV 5.9**: CSV-tiedostojen lukeminen
- **Java SimpleDateFormat**: Päivämäärien parsinta
- **BigDecimal**: Tarkka rahanlaskenta

### Kehitysideoita

Tulevaisuudessa voisi lisätä:
- [ ] Konfiguroitava vastatili (ei kovakoodattu 167)
- [ ] Tuki useammille CSV-formaateille (Nordea, OP, Danske)
- [ ] Esikatselunäkymä ennen tuontia
- [ ] Duplikaattien tunnistus
- [ ] Tiliöintisääntöjen automatiikka
- [ ] Batch-tuonti (useita tiedostoja kerralla)

---

**Kysymyksiä?** Luo issue GitHubissa tai katso [CONTRIBUTING.md](../CONTRIBUTING.md).
