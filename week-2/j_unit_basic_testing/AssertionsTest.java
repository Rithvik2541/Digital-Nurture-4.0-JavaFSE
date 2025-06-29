
import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    @Test
    public void testAssertions() {
        assertEquals(5, 2 + 3);
        assertTrue(100 > 50);
        assertFalse(100 < 50);
        assertNull(null);
        assertNotNull("Amit");
    }
}
