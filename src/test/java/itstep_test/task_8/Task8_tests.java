package itstep_test.task_8;

import itstep.task_2.Primitives;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class Task8_tests {

    @Test
    public void testIntToString() {
        assertEquals("42", Primitives.intToString(42));
        assertEquals("0", Primitives.intToString(0));
        assertEquals("-123", Primitives.intToString(-123));
    }

    @Test
    public void testStringToInt() {
        assertEquals(42, Primitives.stringToInt("42"));
        assertEquals(0, Primitives.stringToInt("0"));
        assertEquals(-123, Primitives.stringToInt("-123"));
    }

    @Test
    public void testIntToDouble() {
        assertEquals(42.0, Primitives.intToDouble(42), 0.0001);
        assertEquals(0.0, Primitives.intToDouble(0), 0.0001);
        assertEquals(-123.0, Primitives.intToDouble(-123), 0.0001);
    }

    @Test
    public void testDoubleToInt() {
        assertEquals(42, Primitives.doubleToInt(42.0));
        assertEquals(0, Primitives.doubleToInt(0.0));
        assertEquals(-123, Primitives.doubleToInt(-123.45));
    }
}
