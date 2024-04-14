package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<?, T>> {
    private boolean isSizeOfActive;
    private int sizeConstraint;
    private boolean isShapeActive;
    private Map<?, BaseSchema<T>> mapConstraint;

    public MapSchema<T> required() {
        isRequiredActive = true;
        return this;
    }

    public MapSchema<T> sizeof(int size) {
        isSizeOfActive = true;
        sizeConstraint = size;
        return this;
    }

    public MapSchema<T> shape(Map<?, BaseSchema<T>> map) {
        isShapeActive = true;
        mapConstraint = new HashMap<>(map);
        return this;
    }

    private boolean isSizeofValid(Map<?, T> map) {
        return !isSizeOfActive || map.size() == sizeConstraint;
    }

    private boolean isShapeValid(Map<?, T> map) {
        return !isShapeActive || map.size() == map.keySet().stream()
                .filter(key -> {
                    var schema = mapConstraint.get(key);
                    return schema.isValid(map.get(key));
                })
                .count();
    }

    @Override
    public boolean isValid(Map<?, T> map) {
        var condition1 = isRequiredValidWithNull(map);
        var condition2 = isRequiredValidWithNotNull(map) && isSizeofValid(map) && isShapeValid(map);

        return condition1 || condition2;
    }
}
