package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MapSchemaTest {
    @Test
    public void testNoConstraints() {
        var mapSchema = new MapSchema();

        var actual1 = mapSchema.isValid(null);
        var actual2 = mapSchema.isValid(new HashMap<>());

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testRequired() {
        var mapSchema = new MapSchema();
        mapSchema.required();

        var actual1 = mapSchema.isValid(null);
        var actual2 = mapSchema.isValid(new HashMap<>());

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testSizeOf() {
        var mapSchema = new MapSchema();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        var actual1 = mapSchema.isValid(data);
        mapSchema.sizeOf(2);
        var actual2 = mapSchema.isValid(data);
        data.put("key2", "value2");
        var actual3 = mapSchema.isValid(data);

        assertTrue(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
    }
}
