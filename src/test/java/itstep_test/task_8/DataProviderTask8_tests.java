package itstep_test.task_8;

import itstep.course_tasks.task_2.Primitives;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DataProviderTask8_tests {

    private Primitives primitives = new Primitives();

    @DataProvider
    private Object[][] intToStringProvider(){
        int n = 3;
        int m = 2;
        Object[][] res = new Object[n][m];
        res[0] = new Object[]{"42", 42};
        res[1] = new Object[]{"0", 0};
        res[2] = new Object[]{"-123", -123};
        return res;
    }

    @DataProvider
    private Object[][] stringToIntProvider(){
        int n = 3;
        int m = 2;
        Object[][] res = new Object[n][m];
        res[0] = new Object[]{42, "42"};
        res[1] = new Object[]{0, "0"};
        res[2] = new Object[]{-123, "-123"};
        return res;
    }

    @DataProvider
    private Object[][] intToDoubleProvider(){
        int n = 3;
        int m = 3;
        Object[][] res = new Object[n][m];
        res[0] = new Object[]{42.0, 42, 0.0001};
        res[1] = new Object[]{0.0, 0, 0.0001};
        res[2] = new Object[]{-123.0, -123, 0.0001};
        return res;
    }

    @DataProvider
    private Object[][] doubleToIntProvider(){
        int n = 3;
        int m = 2;
        Object[][] res = new Object[n][m];
        res[0] = new Object[]{42, 42.0};
        res[1] = new Object[]{0, 0.0};
        res[2] = new Object[]{-123, -123.45};
        return res;
    }

    @Test (dataProvider = "intToStringProvider")
    public void testIntToString( String a, int b) {
        assertEquals(a, Primitives.intToString(b));
        assertEquals(a, Primitives.intToString(b));
        assertEquals(a, Primitives.intToString(b));
    }

    @Test (dataProvider = "stringToIntProvider")
    public void testStringToInt(int a, String b) {
        assertEquals(a, Primitives.stringToInt(b));
        assertEquals(a, Primitives.stringToInt(b));
        assertEquals(a, Primitives.stringToInt(b));
    }

    @Test (dataProvider = "intToDoubleProvider")
    public void testIntToDouble(double a, int b, double c) {
        assertEquals(a, Primitives.intToDouble(b), c);
        assertEquals(a, Primitives.intToDouble(b), c);
        assertEquals(a, Primitives.intToDouble(b), c);
    }

    @Test (dataProvider = "doubleToIntProvider")
    public void testDoubleToInt(int a, double b) {
        assertEquals(a, Primitives.doubleToInt(b));
        assertEquals(a, Primitives.doubleToInt(b));
        assertEquals(a, Primitives.doubleToInt(b));
    }
}
