package kirjanpito.util;

import com.opencsv.CSVReader;

import kirjanpito.db.Entry;
import kirjanpito.db.EntryDAO;
import kirjanpito.db.Period;
import kirjanpito.db.AccountDAO;
import kirjanpito.db.Document;
import kirjanpito.db.DocumentDAO;

import java.io.FileReader;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

import javax.swing.text.DocumentFilter;

public class HolviProcountorCSVImport {
    
    public static boolean ImportFromHolvi(String url, AccountDAO accDAO, DocumentDAO docDAO, EntryDAO entryDAO, Period period) {
        
        CSVReader reader = null;
        ArrayList<Document> documents = new ArrayList<Document>();
        ArrayList<Entry> entries = new ArrayList<Entry>();
        
        Document doc = null;
        Entry entry = null;

        try {
            reader = new com.opencsv.CSVReaderBuilder(new FileReader(url))
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build())
                .build();
            
            String[] nextLine;

            String dateString;
            Date docDate;
            int docId;
            boolean debet;
            int accountId;

            while ((nextLine = reader.readNext()) != null) {
                
                if (nextLine[7].length() > 0) {
                    printToConsole(nextLine);

                    // Create Document
                    doc = docDAO.create(period.getId(), 1, Integer.MAX_VALUE);

                    dateString = nextLine[2].substring(0, Math.min(nextLine[2].length(), 10));
                    docDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                    doc.setDate(docDate);

                    // Save document to DB
                    docDAO.save(doc);


                    // First Entry
                    // Get document ID
                    docId = doc.getId();

                    entry = new Entry();
                    entry.setDocumentId(docId);
                    entry.setAccountId(167); // TODO Remove hard code

                    BigDecimal amount = new BigDecimal(nextLine[5].replace(',', '.'));
                    BigDecimal absAmount = amount.abs();
                    if (amount.signum() < 0) {
                        debet = false;
                    } else {
                        debet = true;
                    }

                    entry.setDebit(debet);
                    entry.setAmount(absAmount);
                    entry.setRowNumber(1);
                    entry.setFlags(0);
                    entry.setDescription(nextLine[8]);

                    entryDAO.save(entry);
                    
                    // Second Entry
                    entry = new Entry();

                    // Find account by number
                    int targetAccountNumber = Integer.parseInt(nextLine[13]);
                    accountId = -1;
                    for (kirjanpito.db.Account acc : accDAO.getAll()) {
                        if (acc.getNumber().equals(String.valueOf(targetAccountNumber))) {
                            accountId = acc.getId();
                            break;
                        }
                    }

                    if (accountId == -1) {
                        throw new Exception("Account with number " + targetAccountNumber + " not found!");
                    }

                    entry.setDocumentId(docId);
                    entry.setAccountId(accountId);
                    entry.setDebit(!debet);
                    entry.setAmount(absAmount);
                    entry.setRowNumber(2);
                    entry.setFlags(0);
                    entry.setDescription(nextLine[8]);
                    
                    entryDAO.save(entry);

                    // DONE

                }
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }

        return false;
    }

    private static void printToConsole(String[] line) {
        System.out.print(
            line[2].substring(0, Math.min(line[2].length(), 10)) + " - " + 
            line[5] + " - " + 
            line[13] + " - " +
            line[8] 
        );
        System.out.print("\n");
    }

}
