package hexlet.code.schemas;

import java.util.Map;

public class MapSchema {
    private boolean isRequired;
    private boolean isSizeOf;
    private int sizeOfMap;

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeOf(int size) {
        isSizeOf = true;
        sizeOfMap = size;
        return this;
    }
    public boolean isValid(Map<?, ?> mapToValidate) {
        if (!isRequired && mapToValidate == null) {
            return true;
        }
        if (isRequired && mapToValidate == null) {
            return false;
        }
        if (isSizeOf && mapToValidate.size() != sizeOfMap) {
            return false;
        }
        return true;
    }
}
