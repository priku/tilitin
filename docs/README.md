# Tilitin Priku - Dokumentaatio

Tervetuloa Tilitin Prikun dokumentaatioon! Tämä kansio sisältää kattavat ohjeet sekä käyttäjille että kehittäjille.

## 📚 Dokumentit

### Käyttäjille

#### 🚀 [ASENNUS.md](ASENNUS.md)
**Aloita tästä!** Kattava asennusohje Javasta Tilittimeen.

- Java 21 -asennus (Windows/Mac/Linux)
- Tilittimen asennus ja käynnistys
- Ensimmäiset askeleet
- Yleisten ongelmien ratkaisu

#### 📥 [CSV_TUONTI.md](CSV_TUONTI.md)
**CSV-tuonti-opas** - Tuo tilitapahtumat verkkopankista.

- Tuetut formaatit (Procountor)
- CSV-tiedoston valmistelu
- Tuonnin suorittaminen vaihe vaiheelta
- Yleisten ongelmien ratkaisu
- Tekninen kuvaus

#### 🔄 [VERSIOT_VERTAILU.md](VERSIOT_VERTAILU.md)
**Versiovertailu** - Miksi Priku on paras valinta?

- Nopea vertailutaulukko
- Yksityiskohtainen vertailu eri versioista:
  - Thelineva (alkuperäinen)
  - Kallio95 (CSV-tuonti)
  - Jkseppan (modernisointi)
  - **Priku (yhdistetty)** ⭐
- Migraatiopolut
- Kirjastoversiot

### Kehittäjille

#### 🛠️ [../CONTRIBUTING.md](../CONTRIBUTING.md)
**Kehittäjän opas** - Aloita kehittäminen.

- Kehitysympäristön asennus
- Projektin rakenne
- Maven-komennot
- IDE-asetukset (IntelliJ, Eclipse, VS Code)
- Koodityyli ja Git-käytännöt
- Uuden ominaisuuden lisääminen
- Debuggaus
- Pull Request -prosessi

#### 🏗️ [TEKNINEN_DOKUMENTAATIO.md](TEKNINEN_DOKUMENTAATIO.md)
**Arkkitehtuuriopas** - Syväsukellus koodiin.

- Kerrosarkkitehtuuri
- Tietokantakerros (DAO-pattern)
- Sovelluslogiikka (Model-luokat)
- Käyttöliittymä (Swing)
- Raporttigeneraattorit (PDF)
- Apuluokat
- Riippuvuudet
- Tietoturva ja suorituskyky

#### 📝 [../CHANGELOG.md](../CHANGELOG.md)
**Muutosloki** - Mitä on muuttunut?

- Versio 1.6.0-priku.1 (uusimmat muutokset)
- Uudet ominaisuudet
- Parannukset
- Bugien korjaukset
- Aikaisemmat versiot

## 🗂️ Dokumentaation rakenne

```
docs/
├── README.md                      # Tämä tiedosto
├── ASENNUS.md                     # Asennusohje
├── CSV_TUONTI.md                  # CSV-tuonti-opas
├── VERSIOT_VERTAILU.md            # Versiovertailu
└── TEKNINEN_DOKUMENTAATIO.md      # Arkkitehtuuriopas

Juurihakemisto:
├── README.md                      # Pääopas (aloita tästä!)
├── CHANGELOG.md                   # Muutosloki
├── CONTRIBUTING.md                # Kehittäjän opas
├── COPYING                        # GPL-lisenssi
└── pom.xml                        # Maven-konfiguraatio
```

## 🎯 Pikaohjeet

### "Haluan vain asentaa Tilittimen"
→ Lue [ASENNUS.md](ASENNUS.md)

### "Haluan tuoda CSV-tiedoston"
→ Lue [CSV_TUONTI.md](CSV_TUONTI.md)

### "Mikä versio minun pitäisi valita?"
→ Lue [VERSIOT_VERTAILU.md](VERSIOT_VERTAILU.md)

### "Haluan kehittää Tilittimeä"
→ Lue [../CONTRIBUTING.md](../CONTRIBUTING.md)

### "Haluan ymmärtää koodin rakenteen"
→ Lue [TEKNINEN_DOKUMENTAATIO.md](TEKNINEN_DOKUMENTAATIO.md)

### "Mitä uutta versiossa 1.6.0?"
→ Lue [../CHANGELOG.md](../CHANGELOG.md)

## 📖 Suositeltu lukujärjestys

### Uudet käyttäjät

1. [../README.md](../README.md) - Yleiskatsaus
2. [ASENNUS.md](ASENNUS.md) - Asenna Tilitin
3. [CSV_TUONTI.md](CSV_TUONTI.md) - Tuo tilitapahtumat (valinnainen)

### Vaihtavat toisesta versiosta

1. [VERSIOT_VERTAILU.md](VERSIOT_VERTAILU.md) - Vertaa versioita
2. [../CHANGELOG.md](../CHANGELOG.md) - Katso muutokset
3. [ASENNUS.md](ASENNUS.md) - Asenna ja migroi

### Uudet kehittäjät

1. [../README.md](../README.md) - Yleiskatsaus
2. [../CONTRIBUTING.md](../CONTRIBUTING.md) - Kehitysympäristö
3. [TEKNINEN_DOKUMENTAATIO.md](TEKNINEN_DOKUMENTAATIO.md) - Arkkitehtuuri
4. [../CHANGELOG.md](../CHANGELOG.md) - Muutoshistoria

## 🔗 Ulkoiset resurssit

### Alkuperäinen Tilitin

- **Kotisivu:** https://helineva.net/tilitin/
- **Ohjeet:** https://helineva.net/tilitin/ohjeet/
- **Tekijä:** Tommi Helineva

### Java ja Maven

- **Java 21 JDK:** https://www.azul.com/downloads/#zulu
- **Maven:** https://maven.apache.org/
- **Java Tutorial:** https://docs.oracle.com/javase/tutorial/

### Kirjastot

- **iTextPDF:** https://kb.itextpdf.com/home/it5kb
- **OpenCSV:** http://opencsv.sourceforge.net/
- **SQLite:** https://www.sqlite.org/docs.html

## ❓ Kysymyksiä?

### Usein kysytyt kysymykset (FAQ)

**K: Tarvitsenko Mavenin käyttääkseni Tilittimeä?**
V: Ei. Maven tarvitaan vain jos haluat kääntää ohjelman lähdekoodista. Valmis JAR-tiedosto toimii ilman Mavenia.

**K: Toimiiko Tilitin M1/M2/M3 Macilla?**
V: Kyllä! Priku-versio tukee ARM Mac -arkkitehtuuria täysin.

**K: Voinko tuoda tilitapahtumia Nordean verkkopankista?**
V: Kyllä, jos verkkopankkisi tukee Procountor CSV -formaattia. Katso [CSV_TUONTI.md](CSV_TUONTI.md).

**K: Onko Tilitin ilmainen?**
V: Kyllä, Tilitin on täysin ilmainen ja avoimen lähdekoodin ohjelma (GPL v3).

**K: Voinko käyttää MySQL:ää SQLiten sijaan?**
V: Kyllä, Tilitin tukee SQLiteä, MySQL:ää ja PostgreSQL:ää.

**K: Mihin tietokanta tallennetaan?**
V: SQLite-tietokanta tallentuu valitsemaasi paikkaan (esim. `kirjanpito.db`). Voit siirtää sitä vapaasti.

### Lisäapu

Jos et löydä vastausta kysymykseesi:

1. **Tarkista dokumentaatio** - Etsi tältä sivulta sopiva opas
2. **Lue CHANGELOG** - Ongelma saattaa olla korjattu uudemmassa versiossa
3. **Luo issue GitHubissa** - Kysy yhteisöltä tai raportoi bugi

## 📝 Dokumentaation ylläpito

### Kehittäjille: Dokumentaation päivittäminen

Kun teet muutoksia koodiin, muista päivittää myös dokumentaatio:

**Uusi ominaisuus:**
1. Päivitä [../README.md](../README.md) - Lisää ominaisuus pääoppaaseen
2. Päivitä [../CHANGELOG.md](../CHANGELOG.md) - Kirjaa muutos
3. Jos tarvitaan, luo uusi opas (esim. `docs/UUSI_OMINAISUUS.md`)

**Bugien korjaus:**
1. Päivitä [../CHANGELOG.md](../CHANGELOG.md) - Kirjaa korjaus
2. Jos bugi koski asennusta, päivitä [ASENNUS.md](ASENNUS.md)

**Riippuvuuksien päivitys:**
1. Päivitä [../CHANGELOG.md](../CHANGELOG.md) - Kirjaa versiot
2. Päivitä [VERSIOT_VERTAILU.md](VERSIOT_VERTAILU.md) - Päivitä vertailutaulukko

## 🌟 Parhaat käytännöt

### Dokumentaation kirjoittaminen

- ✅ **Selkeä rakenne** - Käytä otsikoita ja sisällysluetteloa
- ✅ **Käytännön esimerkit** - Näytä konkreettisia esimerkkejä
- ✅ **Kuvat** - Lisää kuvakaappauksia kun mahdollista
- ✅ **Linkit** - Linkitä muihin dokumentteihin
- ✅ **Emoji** - Käytä kohtuudella parantamaan luettavuutta
- ✅ **Koodiesimerkit** - Käytä syntax highlightingia

### Markdown-tyyli

```markdown
# Pääotsikko (H1) - Vain yksi per dokumentti

## Osio (H2)

### Alaosio (H3)

**Lihavointi** tärkeille asioille

`koodi` inline-koodille

\```bash
# Koodilohko
koodi tähän
\```

- Lista
- Kohta

1. Numeroitu
2. Lista

> Lainaus tai huomio

[Linkki](../README.md)
```

## 🎉 Kiitos!

Kiitos että käytät Tilitin Prikua! Toivottavasti nämä ohjeet auttavat sinua pääsemään alkuun.

Jos huomaat puutteita dokumentaatiossa, älä epäröi:
- Luo issue GitHubissa
- Tee pull request parannuksella
- Ehdota uusia ohjeita

**Hyvää kirjanpitoa!** 📊✨

---

_Dokumentaatio päivitetty: 2025-12-29_
_Tilitin Priku versio: 1.6.0-priku.1_
