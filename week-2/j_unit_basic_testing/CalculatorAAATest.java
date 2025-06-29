
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Namaste! Starting the test setup.");
    }

    @After
    public void tearDown() {
        System.out.println("Shukriya! Test completed.");
    }

    @Test
    public void testMultiply() {
        // Arrange
        int x = 6, y = 9;

        // Act
        int result = calculator.multiply(x, y);

        // Assert
        assertEquals(54, result);
    }

    @Test
    public void testDivide() {
        // Arrange
        int x = 100, y = 4;

        // Act
        int result = calculator.divide(x, y);

        // Assert
        assertEquals(25, result);
    }
}
