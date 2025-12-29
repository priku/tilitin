# Tekninen dokumentaatio - Tilitin Priku

Tämä dokumentti kuvaa Tilitin Prikun teknisen arkkitehtuurin, komponentit ja toteutuksen.

## 📋 Sisällysluettelo

- [Arkkitehtuuri](#arkkitehtuuri)
- [Tietokantakerros](#tietokantakerros)
- [Sovelluslogiikka](#sovelluslogiikka)
- [Käyttöliittymä](#käyttöliittymä)
- [Raporttigeneraattorit](#raporttigeneraattorit)
- [Apuluokat](#apuluokat)
- [Riippuvuudet](#riippuvuudet)

## 🏛️ Arkkitehtuuri

Tilitin noudattaa **kerrosarkkitehtuuria** (Layered Architecture):

```
┌─────────────────────────────────────┐
│     Käyttöliittymä (UI Layer)       │
│         kirjanpito.ui.*             │
│   - DocumentFrame                   │
│   - COADialog                       │
│   - AboutDialog, jne.               │
└─────────────────────────────────────┘
              ↓ ↑
┌─────────────────────────────────────┐
│   Sovelluslogiikka (Business)       │
│       kirjanpito.models.*           │
│   - DocumentModel                   │
│   - COAModel                        │
│   - EntryTemplateModel, jne.        │
└─────────────────────────────────────┘
              ↓ ↑
┌─────────────────────────────────────┐
│   Tietokantakerros (Data Access)    │
│         kirjanpito.db.*             │
│   - Account, Document, Entry        │
│   - AccountDAO, DocumentDAO, etc.   │
│   - sqlite/, mysql/, postgresql/    │
└─────────────────────────────────────┘
              ↓ ↑
┌─────────────────────────────────────┐
│         Tietokanta                  │
│   SQLite / MySQL / PostgreSQL       │
└─────────────────────────────────────┘
```

### Kerroksien vastuut

1. **UI Layer** - Käyttöliittymä
   - Swing-komponentit
   - Käyttäjän syötteiden käsittely
   - Näkymien päivitys

2. **Business Layer** - Sovelluslogiikka
   - Liiketoimintasäännöt
   - Validointi
   - Datan muunnokset

3. **Data Access Layer** - Tietokantakerros
   - CRUD-operaatiot
   - SQL-kyselyt
   - Transaktiot

4. **Database** - Tietokanta
   - Datan tallennus
   - Referentiaali-eheys

## 💾 Tietokantakerros

### DAO-pattern (Data Access Object)

Jokaiselle pääentiteetille on oma DAO-rajapinta ja toteutukset:

```
kirjanpito.db/
├── Account.java              # Tili-entity
├── AccountDAO.java           # DAO-rajapinta
├── sqlite/
│   └── SQLiteAccountDAO.java # SQLite-toteutus
├── mysql/
│   └── MySQLAccountDAO.java  # MySQL-toteutus
└── postgresql/
    └── PSQLAccountDAO.java   # PostgreSQL-toteutus
```

### Pääentiteetit

#### 1. Account (Tili)

```java
public class Account {
    private int id;
    private String number;      // Tilinumero (esim. "1910")
    private String name;        // Tilin nimi
    private int type;           // Tyyppi (1=Vastaavaa, 2=Vastattavaa, etc.)
    private int headingId;      // Otsikon ID
    private boolean vatAccount; // ALV-tili?
    private int flags;          // Liput
}
```

**Tärkeät metodit:**
```java
public interface AccountDAO {
    void save(Account account);
    void delete(int accountId);
    List<Account> getAll();
}
```

#### 2. Document (Tosite)

```java
public class Document {
    private int id;
    private int periodId;       // Tilikauden ID
    private int number;         // Tositenumero
    private Date date;          // Päivämäärä
    private int typeId;         // Tositetyypin ID
}
```

**Tärkeät metodit:**
```java
public interface DocumentDAO {
    Document create(int periodId, int typeId, int number);
    void save(Document document);
    void delete(int documentId);
    List<Document> getAll(int periodId);
}
```

#### 3. Entry (Vienti)

```java
public class Entry {
    private int id;
    private int documentId;     // Tositteen ID
    private int accountId;      // Tilin ID
    private boolean debit;      // Debet/Kredit
    private BigDecimal amount;  // Määrä
    private String description; // Selite
    private int rowNumber;      // Rivinumero
    private int flags;          // Liput
}
```

**Tärkeät metodit:**
```java
public interface EntryDAO {
    void save(Entry entry);
    void delete(int entryId);
    List<Entry> getByDocument(int documentId);
}
```

#### 4. Period (Tilikausi)

```java
public class Period {
    private int id;
    private Date startDate;     // Alkupäivä
    private Date endDate;       // Loppupäivä
    private boolean locked;     // Lukittu?
}
```

#### 5. DocumentType (Tositetyyppi)

```java
public class DocumentType {
    private int id;
    private String name;        // Nimi (esim. "Myyntilasku")
    private int numberStart;    // Numerointi alkaa
    private int numberEnd;      // Numerointi päättyy
}
```

### Tietokantaskeema

#### SQLite (oletus)

```sql
-- Tilit
CREATE TABLE account (
    id INTEGER PRIMARY KEY,
    number TEXT NOT NULL,
    name TEXT NOT NULL,
    type INTEGER NOT NULL,
    heading_id INTEGER,
    vat_account INTEGER DEFAULT 0,
    flags INTEGER DEFAULT 0
);

-- Tositteet
CREATE TABLE document (
    id INTEGER PRIMARY KEY,
    period_id INTEGER NOT NULL,
    number INTEGER NOT NULL,
    date TEXT NOT NULL,
    type_id INTEGER,
    FOREIGN KEY (period_id) REFERENCES period(id),
    FOREIGN KEY (type_id) REFERENCES document_type(id)
);

-- Viennit
CREATE TABLE entry (
    id INTEGER PRIMARY KEY,
    document_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    debit INTEGER NOT NULL,
    amount TEXT NOT NULL,
    description TEXT,
    row_number INTEGER NOT NULL,
    flags INTEGER DEFAULT 0,
    FOREIGN KEY (document_id) REFERENCES document(id),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

-- Tilikaudet
CREATE TABLE period (
    id INTEGER PRIMARY KEY,
    start_date TEXT NOT NULL,
    end_date TEXT NOT NULL,
    locked INTEGER DEFAULT 0
);

-- Tositetyypit
CREATE TABLE document_type (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    number_start INTEGER,
    number_end INTEGER
);
```

### Tietokannan valinta

Tietokanta valitaan DataSourceFactory:llä:

```java
public class DataSourceFactory {
    public static DataSource create(String url, String username, String password) {
        if (url.startsWith("jdbc:sqlite:")) {
            return new SQLiteDataSource(url);
        }
        else if (url.startsWith("jdbc:mysql:")) {
            return new MySQLDataSource(url, username, password);
        }
        else if (url.startsWith("jdbc:postgresql:")) {
            return new PSQLDataSource(url, username, password);
        }
        throw new IllegalArgumentException("Unsupported database");
    }
}
```

## 🧠 Sovelluslogiikka

### Model-luokat

Model-luokat sisältävät liiketoimintalogiikan ja toimivat siltana UI:n ja DAO:n välillä.

#### DocumentModel

**Vastuu:** Tositteiden hallinta

```java
public class DocumentModel {
    private Registry registry;
    private List<Document> documents;
    private int position;

    public void nextDocument();     // Seuraava tosite
    public void previousDocument(); // Edellinen tosite
    public void save();             // Tallenna nykyinen
    public void delete();           // Poista nykyinen
    public List<Entry> getEntries(); // Hae viennit
}
```

#### COAModel

**Vastuu:** Tilikartan hallinta

```java
public class COAModel {
    private Registry registry;
    private List<Account> accounts;
    private List<COAHeading> headings;

    public void save(Account account);
    public void delete(int accountId);
    public List<Account> getAccountsByType(int type);
}
```

#### EntryTemplateModel

**Vastuu:** Vientimallien hallinta

```java
public class EntryTemplateModel {
    private List<EntryTemplate> templates;

    public void saveTemplate(String name, List<Entry> entries);
    public List<Entry> applyTemplate(int templateId);
}
```

### Registry-pattern

**Registry** on keskitetty rekisteri, joka pitää kirjaa:
- Nykyisestä tietokannasta (DataSource)
- Nykyisestä tilikaudesta (Period)
- Asetuksista (Settings)

```java
public class Registry {
    private DataSource dataSource;
    private Period period;
    private Settings settings;
    private List<RegistryListener> listeners;

    public void setDataSource(DataSource ds);
    public void setPeriod(Period period);
    public void fireDocumentChanged();
}
```

**Listener-pattern:** Komponentit kuuntelevat muutoksia Registryssä

```java
public interface RegistryListener {
    void documentChanged();
    void entryChanged();
    void accountsChanged();
}
```

## 🖥️ Käyttöliittymä

### Swing-arkkitehtuuri

Käyttöliittymä rakennettu Java Swing:llä:

```
JFrame (DocumentFrame)
├── JMenuBar
│   ├── Tiedosto
│   ├── Muokkaa
│   │   └── CSV-tuonti (Procountor) ← UUSI!
│   ├── Siirry
│   ├── Tositelaji
│   ├── Tulosteet
│   ├── Työkalut
│   └── Ohje
├── JToolBar
│   ├── Edellinen/Seuraava
│   ├── Uusi tosite
│   └── Lisää/Poista vienti
├── JPanel (Document info)
│   ├── Päivämäärä
│   ├── Tositelaji
│   └── Tositenumero
└── JTable (Entries)
    ├── Tili
    ├── Debet
    ├── Kredit
    └── Selite
```

### Pääikkunat

#### 1. DocumentFrame (Pääikkuna)

**Vastuu:**
- Tositteiden näyttäminen ja muokkaus
- Vientien hallinta
- Valikot ja työkalupalkki

**Tärkeimmät metodit:**
```java
public class DocumentFrame extends JFrame {
    public void create();                    // Luo UI-komponentit
    public void updateDocument();            // Päivitä tosite
    public void updateEntries();             // Päivitä viennit
    public void showCSVImport();             // CSV-tuonti ← UUSI!
    private int importFromCSV(...);          // CSV-tuonti logiikka ← UUSI!
}
```

#### 2. COADialog (Tilikartta)

**Vastuu:**
- Tilikartan näyttäminen
- Tilien lisäys/muokkaus/poisto

#### 3. HolviImportDialog (CSV-tuonti)

**Vastuu:**
- CSV-tiedoston polun kysyminen
- Yksinkertainen UI CSV-tuonnille

```java
public class HolviImportDialog extends JDialog {
    private JTextField urlTextField;
    private int result;

    public void create();
    public String getURL();
    public void setURL(String url);
    public int getResult(); // OK tai CANCEL
}
```

### Taulukon muokkauskäsittely

Vientitaulukko käyttää **TableModel**-patternia:

```java
public class EntryTableModel extends AbstractTableModel {
    private List<Entry> entries;
    private COAModel coaModel;

    public int getRowCount();
    public int getColumnCount();
    public Object getValueAt(int row, int col);
    public void setValueAt(Object value, int row, int col);
    public boolean isCellEditable(int row, int col);
}
```

**Custom Cell Editors:**
- **AccountCellEditor:** Tilin valinta dropdown:sta
- **AmountCellEditor:** Määrän syöttö BigDecimal-muodossa
- **DescriptionCellEditor:** Selitteen syöttö

## 📊 Raporttigeneraattorit

### Raporttimoottori

Kaikki raportit perivät `PrintModel`-luokasta:

```java
public abstract class PrintModel {
    protected Registry registry;
    protected Settings settings;

    public abstract void run();              // Generoi raportti
    public abstract PrintCanvas getCanvas(); // Palauta canvas
}
```

### PrintCanvas-toteutukset

1. **AWTCanvas** - Näytölle esikatselu
2. **PDFCanvas** - PDF-tiedosto (iTextPDF)

### Pääraportit

#### 1. GeneralJournalModel (Päiväkirja)

```java
public class GeneralJournalModel extends PrintModel {
    private Date startDate;
    private Date endDate;

    @Override
    public void run() {
        // 1. Hae tositteet aikaväliltä
        // 2. Järjestä päivämäärän mukaan
        // 3. Tulosta tositenumero, päivä, selite, debet, kredit
    }
}
```

#### 2. GeneralLedgerModel (Pääkirja)

```java
public class GeneralLedgerModel extends PrintModel {
    private Account account;
    private Date startDate;
    private Date endDate;

    @Override
    public void run() {
        // 1. Hae tilin kaikki viennit
        // 2. Laske alkusaldo
        // 3. Tulosta viennit kronologisesti
        // 4. Laske loppusaldo
    }
}
```

#### 3. FinancialStatementModel (Tase/Tuloslaskelma)

```java
public class FinancialStatementModel extends PrintModel {
    private ReportStructure structure;
    private Date startDate;
    private Date endDate;

    @Override
    public void run() {
        // 1. Hae raporttirakenne
        // 2. Laske saldot jokaiselle tilille
        // 3. Summaa otsikot
        // 4. Tulosta hierarkkisesti
    }
}
```

#### 4. VATReportModel (ALV-ilmoitus)

```java
public class VATReportModel extends PrintModel {
    private Date startDate;
    private Date endDate;

    @Override
    public void run() {
        // 1. Hae ALV-tilit
        // 2. Laske ostojen ALV
        // 3. Laske myyntien ALV
        // 4. Laske maksettava/palautettava ALV
    }
}
```

### PDF-generointi

```java
// PDFCanvas.java
public class PDFCanvas implements PrintCanvas {
    private Document pdfDoc;
    private PdfWriter writer;
    private PdfContentByte cb;

    public void drawText(int x, int y, String text);
    public void drawLine(int x1, int y1, int x2, int y2);
    public void setFont(String name, int style, int size);
}
```

## 🛠️ Apuluokat

### AppSettings

**Vastuu:** Sovellusasetusten tallennus ja lataus

```java
public class AppSettings {
    private Properties properties;
    private static AppSettings instance;

    public static AppSettings getInstance();
    public void load(File file);
    public void save(File file);
    public String getString(String key, String defaultValue);
    public void set(String key, String value);
}
```

**Tallennuspaikka:**
- Windows: `%APPDATA%\Tilitin\asetukset.properties`
- Mac/Linux: `~/.tilitin/asetukset.properties`

### SwingUtils

**Vastuu:** UI-apufunktiot

```java
public class SwingUtils {
    public static JMenuItem createMenuItem(String text, String icon,
                                          char mnemonic, KeyStroke accelerator,
                                          ActionListener listener);

    public static void showErrorMessage(Component parent, String message);
    public static void showInformationMessage(Component parent, String message);
}
```

### HolviProcountorCSVImport

**Vastuu:** CSV-tuonti (vanha toteutus, ei enää käytössä)

Uusi toteutus on suoraan `DocumentFrame.importFromCSV()` -metodissa.

## 📦 Riippuvuudet

### Maven Dependencies

```xml
<dependencies>
    <!-- PDF-generointi -->
    <dependency>
        <groupId>com.itextpdf</groupId>
        <artifactId>itextpdf</artifactId>
        <version>5.5.13.4</version>
    </dependency>

    <!-- Tietokannat -->
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.47.1.0</version>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>9.1.0</version>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.4</version>
    </dependency>

    <!-- CSV-käsittely -->
    <dependency>
        <groupId>com.opencsv</groupId>
        <artifactId>opencsv</artifactId>
        <version>5.9</version>
    </dependency>

    <!-- Lokitus -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.16</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>2.0.16</version>
    </dependency>
</dependencies>
```

### Riippuvuuspuu

```
tilitin
├── iTextPDF 5.5.13.4
├── SQLite JDBC 3.47.1.0
├── MySQL Connector 9.1.0
├── PostgreSQL JDBC 42.7.4
├── OpenCSV 5.9
│   ├── Commons Lang3 3.13.0
│   ├── Commons Text 1.11.0
│   ├── Commons BeanUtils 1.9.4
│   └── Commons Collections4 4.4
└── SLF4J 2.0.16
    ├── slf4j-api 2.0.16
    └── slf4j-simple 2.0.16
```

## 🔐 Tietoturva

### SQL Injection -suojaus

Kaikki SQL-kyselyt käyttävät **PreparedStatement**ia:

```java
// HYVÄ ✅
PreparedStatement stmt = conn.prepareStatement(
    "SELECT * FROM account WHERE number = ?");
stmt.setString(1, accountNumber);

// HUONO ❌ (ei käytössä!)
Statement stmt = conn.createStatement();
stmt.executeQuery("SELECT * FROM account WHERE number = '" + accountNumber + "'");
```

### Lukitusten hallinta

Tilikaudet voidaan lukita estämään muokkaukset:

```java
if (period.isLocked()) {
    throw new DataAccessException("Tilikausi on lukittu");
}
```

## 🚀 Suorituskyky

### Indeksit

Tietokantatauluissa on indeksit:

```sql
CREATE INDEX idx_document_period ON document(period_id);
CREATE INDEX idx_entry_document ON entry(document_id);
CREATE INDEX idx_entry_account ON entry(account_id);
```

### Transaktiot

Useita muutoksia tehdään transaktioissa:

```java
session.beginTransaction();
try {
    documentDAO.save(document);
    for (Entry entry : entries) {
        entryDAO.save(entry);
    }
    session.commit();
} catch (Exception e) {
    session.rollback();
    throw e;
}
```

### Lazy Loading

Viennit ladataan vasta kun tarvitaan:

```java
public List<Entry> getEntries(int documentId) {
    // Ladataan vain tämän tositteen viennit
    return entryDAO.getByDocument(documentId);
}
```

## 📚 Lisälukemista

- [DAO Pattern](https://www.baeldung.com/java-dao-pattern)
- [Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [iText Documentation](https://kb.itextpdf.com/home/it5kb)
- [OpenCSV Guide](http://opencsv.sourceforge.net/)

---

**Kysymyksiä arkkitehtuurista?** Katso [CONTRIBUTING.md](../CONTRIBUTING.md) tai luo issue GitHubissa.
