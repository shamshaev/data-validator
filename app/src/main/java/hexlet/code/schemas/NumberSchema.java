package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean isRequired;
    private boolean isPositive;
    private boolean isRange;
    private int minRange;
    private int maxRange;

    public NumberSchema required() {
        isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        isPositive = true;
        return this;
    }
    public NumberSchema range(int min, int max) {
        isRange = true;
        minRange = min;
        maxRange = max;
        return this;
    }
    public boolean isValid(Integer numberToValidate) {
        if (!isRequired && numberToValidate == null) {
            return true;
        }
        if (isRequired && numberToValidate == null) {
            return false;
        }
        if (isPositive && numberToValidate <= 0) {
            return false;
        }
        if (isRange && !(numberToValidate >= minRange && numberToValidate <= maxRange)) {
            return false;
        }
        return true;
    }
}
