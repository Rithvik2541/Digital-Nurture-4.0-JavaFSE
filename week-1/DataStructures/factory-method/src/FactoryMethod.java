/**
 * Simple Factory Method Pattern demo for a Document Management System.
 * 
 * Directory: factory-method/src/Main.java
 */
import java.util.*;

/** 1) The Product interface */
interface Document {
    /** Open the document (e.g. load from disk or show UI) */
    void open();

    /** Print some metadata about this document */
    void printInfo();
}

/** 2) Concrete Products */

/** A Word document */
class WordDocument implements Document {
    private final String name;
    private final int wordCount;

    public WordDocument(String name) {
        this.name = name;
        // In a real system you'd parse the file; here we simulate
        this.wordCount = new Random().nextInt(900) + 100; // 100–1000 words
    }

    @Override
    public void open() {
        System.out.println("Opening Word document: " + name);
    }

    @Override
    public void printInfo() {
        System.out.printf("WordDoc: name=\"%s\", words=%d%n", name, wordCount);
    }
}

/** A PDF document */
class PdfDocument implements Document {
    private final String name;
    private final int pages;

    public PdfDocument(String name) {
        this.name = name;
        this.pages = new Random().nextInt(50) + 1; // 1–50 pages
    }

    @Override
    public void open() {
        System.out.println("Opening PDF document: " + name);
    }

    @Override
    public void printInfo() {
        System.out.printf("PdfDoc:  name=\"%s\", pages=%d%n", name, pages);
    }
}

/** An Excel spreadsheet */
class ExcelDocument implements Document {
    private final String name;
    private final int sheets;

    public ExcelDocument(String name) {
        this.name = name;
        this.sheets = new Random().nextInt(5) + 1; // 1–5 sheets
    }

    @Override
    public void open() {
        System.out.println("Opening Excel document: " + name);
    }

    @Override
    public void printInfo() {
        System.out.printf("ExcelDoc: name=\"%s\", sheets=%d%n", name, sheets);
    }
}

/** 3) The Creator (abstract factory) */
abstract class DocumentFactory {
    /** Factory Method */
    public abstract Document createDocument(String filename);
}

/** 4) Concrete Factories */

class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument(String filename) {
        return new WordDocument(filename);
    }
}

class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument(String filename) {
        return new PdfDocument(filename);
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument(String filename) {
        return new ExcelDocument(filename);
    }
}

/** 5) Client / Demo */
public class FactoryMethod {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Demo ===\n");

        // List of example file names
        List<String> files = Arrays.asList(
            "Report.docx", "Slides.pdf", "Data.xlsx", "Notes.txt", "Budget.xlsx", "Summary.pdf"
        );

        for (String filename : files) {
            DocumentFactory factory = getFactoryFor(filename);
            if (factory == null) {
                System.out.printf("Skipping unsupported file type: %s%n%n", filename);
                continue;
            }
            // Use the factory to create the right Document
            Document doc = factory.createDocument(filename);

            // “Real-world” steps
            doc.open();
            doc.printInfo();
            System.out.println();
        }
    }

    /**
     * Chooses the appropriate factory based on file extension.
     */
    private static DocumentFactory getFactoryFor(String filename) {
        String ext = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) ext = filename.substring(i + 1).toLowerCase();

        switch (ext) {
            case "doc":
            case "docx":
                return new WordDocumentFactory();
            case "pdf":
                return new PdfDocumentFactory();
            case "xls":
            case "xlsx":
                return new ExcelDocumentFactory();
            default:
                return null;
        }
    }
}
