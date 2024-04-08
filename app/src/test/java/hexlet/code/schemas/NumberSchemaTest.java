package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NumberSchemaTest {
    @Test
    public void testNoConstraints() {
        var numberSchema = new NumberSchema();

        var actual1 = numberSchema.isValid(null);
        var actual2 = numberSchema.isValid(5);

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testRequired() {
        var numberSchema = new NumberSchema();
        numberSchema.required();

        var actual1 = numberSchema.isValid(null);
        var actual2 = numberSchema.isValid(10);

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testPositive() {
        var numberSchema = new NumberSchema();
        numberSchema.positive();

        var actual1 = numberSchema.isValid(null);
        numberSchema.required();
        var actual2 = numberSchema.isValid(null);
        var actual3 = numberSchema.isValid(-10);
        var actual4 = numberSchema.isValid(0);
        var actual5 = numberSchema.isValid(10);

        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
        assertFalse(actual4);
        assertTrue(actual5);
    }

    @Test
    public void testRange() {
        var numberSchema = new NumberSchema();
        numberSchema.range(5, 10);

        var actual1 = numberSchema.isValid(5);
        var actual2 = numberSchema.isValid(10);
        var actual3 = numberSchema.isValid(4);
        var actual4 = numberSchema.isValid(11);

        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
        assertFalse(actual4);
    }
}
