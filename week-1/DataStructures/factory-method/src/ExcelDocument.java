import java.util.Random;

public class ExcelDocument implements Document {
    private final String name;
    private final int sheets;

    public ExcelDocument(String name) {
        this.name = name;
        // Simulate sheet count
        this.sheets = new Random().nextInt(5) + 1;
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
