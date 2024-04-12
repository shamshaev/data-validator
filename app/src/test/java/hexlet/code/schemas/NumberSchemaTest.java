package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hexlet.code.Validator;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    public void beforeEach() {
        var v = new Validator();
        schema = v.number();
    }

    @Test
    public void testNoConstraints() {
        var actual1 = schema.isValid(null);
        var actual2 = schema.isValid(5);

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testRequired() {
        schema.required();

        var actual1 = schema.isValid(null);
        var actual2 = schema.isValid(10);

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testPositive() {
        schema.positive();
        var actual1 = schema.isValid(null);

        schema.required();
        var actual2 = schema.isValid(null);
        var actual3 = schema.isValid(-10);
        var actual4 = schema.isValid(0);
        var actual5 = schema.isValid(10);

        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
        assertFalse(actual4);
        assertTrue(actual5);
    }

    @Test
    public void testRange() {
        schema.range(5, 10);

        var actual1 = schema.isValid(7);
        var actual2 = schema.isValid(5);
        var actual3 = schema.isValid(2);
        var actual4 = schema.isValid(11);

        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
        assertFalse(actual4);
    }
}
