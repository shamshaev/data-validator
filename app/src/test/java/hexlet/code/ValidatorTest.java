package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class ValidatorTest {
    @Test
    public void testString() {
        var validator = new Validator();
        var stringSchema = validator.string();

        var actual = stringSchema.getClass();

        assertEquals(StringSchema.class, actual);
    }

    @Test
    public void testNullAndEmpty() {
        var validator = new Validator();
        var stringSchema = validator.string();

        var actual1 = stringSchema.isValid(null);
        var actual2 = stringSchema.isValid("");

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testRequired() {
        var validator = new Validator();
        var stringSchema = validator.string().required();

        var actual1 = stringSchema.isValid(null);
        var actual2 = stringSchema.isValid("");
        var actual3 = stringSchema.isValid("what does the fox say");

        assertFalse(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
    }

    @Test
    public void testMinLength() {
        var validator = new Validator();
        var stringSchema = validator.string();

        var actual1 = stringSchema.minLength(10).isValid("Hexlet");
        var actual2 = stringSchema.minLength(10).minLength(4).isValid("Hexlet");

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testContains() {
        var validator = new Validator();
        var stringSchema = validator.string();

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
