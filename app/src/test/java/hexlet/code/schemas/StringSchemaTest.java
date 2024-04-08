package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StringSchemaTest {
    @Test
    public void testNoConstraints() {
        var stringSchema = new StringSchema();

        var actual1 = stringSchema.isValid(null);
        var actual2 = stringSchema.isValid("");

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testRequired() {
        var stringSchema = new StringSchema();
        stringSchema.required();

        var actual1 = stringSchema.isValid(null);
        var actual2 = stringSchema.isValid("");
        var actual3 = stringSchema.isValid("what does the fox say");

        assertFalse(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
    }

    @Test
    public void testMinLength() {
        var stringSchema = new StringSchema();

        var actual1 = stringSchema.minLength(10).isValid("Hexlet");
        var actual2 = stringSchema.minLength(10).minLength(4).isValid("Hexlet");

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testContains() {
        var stringSchema = new StringSchema();

        var actual1 = stringSchema.contains("wh").isValid("what does the fox say");
        var actual2 = stringSchema.contains("what").isValid("what does the fox say");
        var actual3 = stringSchema.contains("whatthe").isValid("what does the fox say");
        var actual4 = stringSchema.isValid("what does the fox say");

        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
        assertFalse(actual4);
    }
}
