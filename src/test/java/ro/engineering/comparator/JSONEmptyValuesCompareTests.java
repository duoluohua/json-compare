package ro.engineering.comparator;

import org.junit.Test;

public class JSONEmptyValuesCompareTests {

    @Test
    public void compareWithEmptyValue() {
        String expected = "{\"a\":\"\"}";
        String actual = "{\"a\":\"\"}";
        JSONCompare.assertEquals(expected, actual);
    }

    @Test
    public void compareWithEmptyValue_negative() {
        String expected = "{\"a\":\"sometext\"}";
        String actual = "{\"a\":\"\"}";
        JSONCompare.assertNotEquals(expected, actual);
    }

    @Test
    public void compareEmptyAndNullValue_negative() {
        String expected = "{\"a\":\"\"}";
        String actual = "{\"a\":null}";
        JSONCompare.assertNotEquals(expected, actual);
    }
}
