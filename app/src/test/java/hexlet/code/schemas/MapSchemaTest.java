package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MapSchemaTest {
    private MapSchema schema;

    @BeforeEach
    public void beforeEach() {
        var v = new Validator();
        schema = v.map();
    }

    @Test
    public void testNoConstraints() {
        var actual1 = schema.isValid(null);
        var actual2 = schema.isValid(new HashMap<>());

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testRequired() {
        schema.required();

        var actual1 = schema.isValid(null);
        var actual2 = schema.isValid(new HashMap<>());

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testSizeOf() {
        schema.required().sizeof(2);

        var data = new HashMap<String, String>();

        data.put("key1", "value1");
        var actual1 = schema.isValid(data);

        data.put("key2", "value2");
        var actual2 = schema.isValid(data);

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testShape() {
        var v = new Validator();

        Map<String, BaseSchema<String>> schemas1 = new HashMap<>();
        schemas1.put("firstName", v.string().required());
        schemas1.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas1);

        Map<String, String> human1 = new HashMap<>(Map.of("firstName", "John", "lastName", "Smith"));
        Map<String, String> human2 = new HashMap<>(Map.of("firstName", "John"));
        human2.put("lastName", null);
        Map<String, String> human3 = new HashMap<>(Map.of("firstName", "John", "lastName", "B"));

        var actual1 = schema.isValid(human1);
        var actual2 = schema.isValid(human2);
        var actual3 = schema.isValid(human3);

        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);

        Map<Number, BaseSchema<Integer>> schemas2 = new HashMap<>();
        schemas2.put(8, v.number().positive());
        schemas2.put(5, v.number().required().range(5, 9));

        schema.shape(schemas2);

        Map<Number, Number> numbers1 = new HashMap<>(Map.of(8, 4, 5, 5));
        Map<Number, Number> numbers2 = new HashMap<>(Map.of(8, -3, 5, 8));

        var actual4 = schema.isValid(numbers1);
        var actual5 = schema.isValid(numbers2);

        assertTrue(actual4);
        assertFalse(actual5);
    }
}
