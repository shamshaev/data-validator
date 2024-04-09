package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testShape() {
        var v = new Validator();
        var mapSchema = v.map();
        Map<String, StringSchema> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        mapSchema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        var actual1 = mapSchema.isValid(human1);
        assertTrue(actual1);

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        var actual2 = mapSchema.isValid(human2);
        assertFalse(actual2);

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        var actual3 = mapSchema.isValid(human3);
        assertFalse(actual3);
    }
}
