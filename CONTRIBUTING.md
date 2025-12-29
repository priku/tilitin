# Kehittäjän opas - Tilitin Priku

Tervetuloa kehittämään Tilittimeä! Tämä opas auttaa sinua pääsemään alkuun projektin kehittämisessä.

## 🛠️ Kehitysympäristön asennus

### Vaatimukset

1. **Java Development Kit (JDK) 21 tai uudempi**
   - [Azul Zulu JDK 21](https://www.azul.com/downloads/#zulu) (suositeltu)
   - [Eclipse Adoptium JDK 21](https://adoptium.net/)
   - [Oracle JDK 21](https://www.oracle.com/java/technologies/downloads/)

2. **Apache Maven 3.6 tai uudempi**
   - [Maven](https://maven.apache.org/download.cgi)
   - Tai asenna package managerilla:
     - Windows: `choco install maven`
     - Mac: `brew install maven`
     - Linux: `sudo apt install maven`

3. **IDE (valinnainen, mutta suositeltu)**
   - [IntelliJ IDEA](https://www.jetbrains.com/idea/) (suositeltu)
   - [Eclipse](https://www.eclipse.org/downloads/)
   - [VS Code](https://code.visualstudio.com/) + Java Extension Pack

### Projektin kloonaus

```bash
git clone <repository-url>
cd tilitin-masterPriku
```

### Ensimmäinen käännös

```bash
# Lataa riippuvuudet ja käännä projekti
mvn clean compile

# Luo ajettava JAR-paketti
mvn clean package
```

## 📂 Projektin rakenne

```
tilitin-masterPriku/
├── src/main/java/
│   └── kirjanpito/
│       ├── db/              # Tietokantakerros
│       │   ├── Account.java
│       │   ├── AccountDAO.java
│       │   ├── Document.java
│       │   ├── Entry.java
│       │   ├── sqlite/      # SQLite-toteutus
│       │   ├── mysql/       # MySQL-toteutus
│       │   └── postgresql/  # PostgreSQL-toteutus
│       ├── models/          # Sovelluslogiikka
│       │   ├── DocumentModel.java
│       │   ├── COAModel.java
│       │   └── ...
│       ├── ui/              # Käyttöliittymä (Swing)
│       │   ├── Kirjanpito.java    # Pääohjelma
│       │   ├── DocumentFrame.java # Pääikkuna
│       │   └── ...
│       ├── reports/         # Raporttigeneraattorit
│       │   ├── PrintModel.java
│       │   └── ...
│       └── util/            # Apuluokat
│           ├── AppSettings.java
│           └── HolviProcountorCSVImport.java
│
├── src/main/resources/
│   ├── kirjanpito/
│   │   ├── db/              # SQL-skemat
│   │   │   ├── sqlite/database.sql
│   │   │   ├── mysql/database.sql
│   │   │   └── postgresql/database.sql
│   │   ├── reports/         # Raporttien otsakkeet
│   │   └── ui/resources/    # Kuvat ja ikonit
│   └── tilikarttamallit/    # Tilikarttamallit (JAR-paketit)
│
├── pom.xml                  # Maven-konfiguraatio
├── README.md                # Käyttöopas
├── CHANGELOG.md             # Muutosloki
└── CONTRIBUTING.md          # Tämä tiedosto
```

## 🔨 Maven-komennot

### Peruskäännös

```bash
# Käännä lähdekoodit
mvn compile

# Puhdista ja käännä
mvn clean compile

# Luo JAR-paketti (kaikki riippuvuudet mukana)
mvn package

# Puhdista target-hakemisto
mvn clean
```

### Testaus

```bash
# Aja testit (kun testejä lisätään)
mvn test

# Käännä ja aja testit
mvn clean test
```

### Kehitys

```bash
# Tarkista koodin tyyli
mvn checkstyle:check

# Näytä riippuvuuspuu
mvn dependency:tree

# Päivitä riippuvuudet
mvn versions:display-dependency-updates

# Näytä ulkopuoliset riippuvuudet
mvn dependency:analyze
```

## 💻 IDE-asetukset

### IntelliJ IDEA (suositeltu)

1. **Avaa projekti**
   - File → Open → Valitse `pom.xml`
   - IDEA tunnistaa Maven-projektin automaattisesti

2. **Konfiguroi JDK**
   - File → Project Structure → Project
   - Project SDK: Java 21
   - Language level: 21

3. **Maven-asetukset**
   - File → Settings → Build, Execution, Deployment → Build Tools → Maven
   - Varmista että Maven home on oikein

4. **Ajokonfiguraatio**
   - Run → Edit Configurations → Add New → Application
   - Main class: `kirjanpito.ui.Kirjanpito`
   - Module: tilitin-masterPriku

### Eclipse

1. **Tuo projekti**
   - File → Import → Existing Maven Projects
   - Valitse projektin juurihakemisto

2. **Päivitä projekti**
   - Oikea klikkaus projektilla → Maven → Update Project

### VS Code

1. **Asenna laajennukset**
   - Extension Pack for Java
   - Maven for Java

2. **Avaa kansio**
   - File → Open Folder → Valitse projektin juurihakemisto

## 🎯 Kehitysohjeet

### Koodityyli

- **Sisennys**: Välilyönnit (ei tabulaattoreita)
- **Merkkikoodaus**: UTF-8
- **Rivinvaihto**: LF (Unix-tyyli)
- **Kiinteä leveys**: 120 merkkiä per rivi (suositus)

### Kommentointi

```java
/**
 * JavaDoc-kommentti julkisille metodeille ja luokille.
 *
 * @param parameter Parametrin kuvaus
 * @return Paluuarvon kuvaus
 * @throws Exception Poikkeuksen kuvaus
 */
public void exampleMethod(String parameter) throws Exception {
    // Yksiriviset kommentit koodin sisällä
    // Selitä MIKSI, ei MITÄ koodi tekee
}
```

### Git-käytännöt

#### Commit-viestit

```
<tyyppi>: <lyhyt kuvaus>

<pidempi kuvaus tarvittaessa>

<footer: viittaukset issueisiin tms.>
```

**Tyypit:**
- `feat`: Uusi ominaisuus
- `fix`: Bugien korjaus
- `docs`: Dokumentaation muutokset
- `style`: Koodin muotoilun muutokset (ei toiminnallisia)
- `refactor`: Koodin uudelleenjärjestely
- `test`: Testien lisäys tai muutos
- `chore`: Ylläpitotehtävät (riippuvuudet, build-skriptit)

**Esimerkkejä:**

```
feat: Lisää CSV-tuonti Nordea-muodolle

Lisätty tuki Nordean CSV-formaatille.
Käyttäjä voi nyt valita CSV-tuonti-ikkunassa tiedostomuodon.

Fixes #123
```

```
fix: Korjaa ALV-laskenta pyöristysvirheiden osalta

BigDecimal-laskennassa oli pyöristysongelma,
joka aiheutti senttien eroja ALV-ilmoituksessa.
```

## 🧪 Testaus

### Manuaalinen testaus

1. **Käännä projekti**
   ```bash
   mvn clean package
   ```

2. **Aja ohjelma**
   ```bash
   java -jar target/tilitin-1.6.0-priku.1.jar
   ```

3. **Testaa toiminnallisuudet**
   - Luo uusi tietokanta
   - Lisää tositteita
   - Kokeile CSV-tuontia
   - Tulosta raportteja

### Yksikkötestien kirjoittaminen

```java
package kirjanpito.db;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void testAccountCreation() {
        Account account = new Account();
        account.setNumber("1000");
        account.setName("Testiti");

        assertEquals("1000", account.getNumber());
        assertEquals("Testiti", account.getName());
    }
}
```

## 📝 Uuden ominaisuuden lisääminen

### Esimerkki: Uusi raporttityyppi

1. **Luo raporttimalli**
   ```java
   // src/main/java/kirjanpito/reports/MyNewReportModel.java
   public class MyNewReportModel extends PrintModel {
       // Toteutus
   }
   ```

2. **Lisää UI-elementti**
   ```java
   // DocumentFrame.java
   JMenuItem myReportMenuItem = SwingUtils.createMenuItem(
       "Uusi raportti", null, 'U', null, myReportListener
   );
   reportsMenu.add(myReportMenuItem);
   ```

3. **Toteuta listener**
   ```java
   private ActionListener myReportListener = new ActionListener() {
       public void actionPerformed(ActionEvent e) {
           showMyReport();
       }
   };
   ```

4. **Testaa**
   ```bash
   mvn clean package
   java -jar target/tilitin-1.6.0-priku.1.jar
   ```

## 🐛 Debuggaus

### IntelliJ IDEA

1. Aseta breakpoint koodiin (klikkaa rivinumeroa)
2. Run → Debug 'Kirjanpito'
3. Käytä debuggerin työkaluja (Step Over, Step Into, jne.)

### Lokitus

```java
import java.util.logging.Logger;
import java.util.logging.Level;

private static final Logger logger = Logger.getLogger(Kirjanpito.LOGGER_NAME);

// Käyttö
logger.log(Level.INFO, "Käyttäjä avasi tietokannan");
logger.log(Level.WARNING, "Epäilyttävä summa: " + amount);
logger.log(Level.SEVERE, "Virhe tietokannassa", exception);
```

## 🔍 Yleisiä ongelmia

### "Package does not exist"

```bash
# Puhdista ja lataa riippuvuudet uudelleen
mvn clean install -U
```

### "Java version mismatch"

Varmista että käytät Java 21:
```bash
java -version
mvn -version
```

### "OutOfMemoryError"

Lisää Mavenin muistia:
```bash
export MAVEN_OPTS="-Xmx1024m"
mvn clean package
```

## 📚 Lisäresurssit

- [Maven-dokumentaatio](https://maven.apache.org/guides/)
- [Java 21 -dokumentaatio](https://docs.oracle.com/en/java/javase/21/)
- [Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [iText PDF -dokumentaatio](https://kb.itextpdf.com/home/it5kb)

## 🤝 Yhteistyö

### Pull Request -prosessi

1. Forkkaa repositorio
2. Luo feature-branch (`git checkout -b feat/amazing-feature`)
3. Committaa muutoksesi (`git commit -m 'feat: Add amazing feature'`)
4. Pushaa branchisi (`git push origin feat/amazing-feature`)
5. Avaa Pull Request

### Code Review

- Kaikki muutokset tarvitsevat yhden hyväksynnän
- Varmista että koodi kääntyy (`mvn clean package`)
- Kirjoita selkeä kuvaus muutoksista
- Linkitä liittyvät issuesit

## 📧 Yhteystiedot

Jos tarvitset apua:
- Luo issue GitHubissa
- Katso alkuperäisen Tilittimen dokumentaatio: https://helineva.net/tilitin/

---

**Kiitos että kehität Tilittimeä paremmaksi!** 🎉
