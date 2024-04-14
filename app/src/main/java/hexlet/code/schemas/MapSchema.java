package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<?, ?>> {
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

    private boolean isSizeofValid(Map<?, ?> map) {
        return !isSizeOfActive || map.size() == sizeConstraint;
    }

    @SuppressWarnings("unchecked")
    private boolean isShapeValid(Map<?, ?> map) {
        return !isShapeActive || map.size() == map.keySet().stream()
                .filter(key -> {
                    var schema = mapConstraint.get(key);
                    T valueMap;
                    try {
                        valueMap = (T) map.get(key);
                    } catch (ClassCastException e) {
                        throw new ClassCastException();
                    }
                    return schema.isValid(valueMap);
                })
                .count();
    }

    @Override
    public boolean isValid(Map<?, ?> map) {
        var condition1 = isRequiredValidWithNull(map);
        var condition2 = isRequiredValidWithNotNull(map) && isSizeofValid(map) && isShapeValid(map);

        return condition1 || condition2;
    }
}
