public class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument(String filename) {
        return new ExcelDocument(filename);
    }
}
