import java.util.Random;
public class WordDocument implements Document {
    private final String name;
    private final int wordCount;

    public WordDocument(String name) {
        this.name = name;
        // Simulate loading word count
        this.wordCount = new Random().nextInt(900) + 100;
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
