//main factory method handles other classes
public class FactoryMethod {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===");

        String[] files = {
            "Report.docx", "Slides.pdf", "Data.xlsx", "Notes.txt", "Summary.PDF"
        };

        for (String filename : files) {
            DocumentFactory factory = getFactoryFor(filename);
            if (factory == null) {
                System.out.println("Skipping unsupported file: " + filename);
                continue;
            }
            Document doc = factory.createDocument(filename);
            doc.open();
            doc.printInfo();
            System.out.println();
        }
    }

    private static DocumentFactory getFactoryFor(String filename) {
        int idx = filename.lastIndexOf('.');
        if (idx < 0 || idx == filename.length() - 1) return null;
        String ext = filename.substring(idx + 1).toLowerCase();

        switch (ext) {
            case "doc": case "docx":
                return new WordDocumentFactory();
            case "pdf":
                return new PdfDocumentFactory();
            case "xls": case "xlsx":
                return new ExcelDocumentFactory();
            default:
                return null;
        }
    }
}
