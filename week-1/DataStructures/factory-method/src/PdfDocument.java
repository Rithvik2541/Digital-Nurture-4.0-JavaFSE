import java.util.Random;

public class PdfDocument implements Document {
    private final String name;
    private final int pages;

    public PdfDocument(String name) {
        this.name = name;
        // Simulate page count
        this.pages = new Random().nextInt(50) + 1;
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
