package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema<T> extends BaseSchema<Map<?, ?>> {

    public MapSchema() {
        addCheck(
                "allowed",
                Objects::isNull
        );
    }

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(
                "sizeof",
                value -> value.size() == size
        );
        return this;
    }

    public MapSchema shape(Map<?, BaseSchema<T>> map) {
        addCheck(
                "shape",
                value -> value.keySet().stream()
                        .allMatch(key -> map.get(key).isValid((T) value.get(key)))
        );
        return this;
    }
}
