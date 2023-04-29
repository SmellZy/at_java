package itstep_test.task_8;

import itstep.task_2.Primitives;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ParametrizedTask8_tests {

    @Parameters({ "a", "b" })
    @Test
    public void testIntToString(String a, int b) {
        assertEquals(a, Primitives.intToString(b));
        assertEquals(a, Primitives.intToString(b));
        assertEquals(a, Primitives.intToString(b));
    }
}
