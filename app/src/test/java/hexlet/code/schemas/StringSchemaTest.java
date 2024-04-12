package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hexlet.code.Validator;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StringSchemaTest {
    private StringSchema schema;

    @BeforeEach
    public void beforeEach() {
        var v = new Validator();
        schema = v.string();
    }

    @Test
    public void testNoConstraints() {
        var actual1 = schema.isValid(null);
        var actual2 = schema.isValid("");

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testRequired() {
        schema.required();

        var actual1 = schema.required().isValid(null);
        var actual2 = schema.isValid("");
        var actual3 = schema.isValid("hexlet");

        assertFalse(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
    }

    @Test
    public void testMinLength() {
        schema.minLength(10);

        var actual1 = schema.isValid("Hexlet");
        var actual2 = schema.isValid(null);
        var actual3 = schema.minLength(4).isValid("Hexlet");

        assertFalse(actual1);
        assertTrue(actual2);
        assertTrue(actual3);
    }

    @Test
    public void testContains() {
        var actual1 = schema.contains("wh").isValid("what does the fox say");
        var actual2 = schema.contains("whatthe").isValid("what does the fox say");
        var actual3 = schema.isValid("what does the fox say");

        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
    }
}
