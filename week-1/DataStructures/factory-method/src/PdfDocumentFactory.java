public class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument(String filename) {
        return new PdfDocument(filename);
    }
}
