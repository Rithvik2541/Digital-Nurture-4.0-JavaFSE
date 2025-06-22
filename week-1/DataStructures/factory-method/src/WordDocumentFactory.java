public class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument(String filename) {
        return new WordDocument(filename);
    }
}
