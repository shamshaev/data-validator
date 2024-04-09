package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean isRequired;
    private boolean isSizeOf;
    private int sizeOfMap;
    private boolean isShape;
    private Map<String, StringSchema> mapConstraintTotal;

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeOf(int size) {
        isSizeOf = true;
        sizeOfMap = size;
        return this;
    }

    public MapSchema shape(Map<String, StringSchema> mapConstraint) {
        isShape = true;
        mapConstraintTotal = new HashMap<>(mapConstraint);
        return this;
    }

    public boolean isValid(Map<String, String> mapToValidate) {
        if (!isRequired && mapToValidate == null) {
            return true;
        }
        if (isRequired && mapToValidate == null) {
            return false;
        }
        if (isSizeOf && mapToValidate.size() != sizeOfMap) {
            return false;
        }
        if (isShape) {
            for (var key : mapToValidate.keySet()) {
                if (mapConstraintTotal.containsKey(key)) {
                    var v = mapConstraintTotal.get(key);
                    if (!v.isValid(mapToValidate.get(key))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
