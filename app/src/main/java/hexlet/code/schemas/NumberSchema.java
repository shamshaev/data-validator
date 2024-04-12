package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Number> {
    private boolean isPositiveActive;
    private boolean isRangeActive;
    private double minRange;
    private double maxRange;

    public NumberSchema required() {
        isRequiredActive = true;
        return this;
    }

    public NumberSchema positive() {
        isPositiveActive = true;
        return this;
    }
    public NumberSchema range(double min, double max) {
        isRangeActive = true;
        minRange = min;
        maxRange = max;
        return this;
    }

    private boolean isPositiveValid(Number number) {
        return !isPositiveActive || number.doubleValue() > 0;
    }

    private boolean isRangeValid(Number number) {
        return !isRangeActive || number.doubleValue() >= minRange && number.doubleValue() <= maxRange;
    }

    @Override
    public boolean isValid(Number number) {
        var numberDouble = (number != null) ? number.doubleValue() : null;

        var condition1 = isRequiredValidWithNull(number);
        var condition2 = isRequiredValidWithNotNull(number) && isPositiveValid(numberDouble)
                && isRangeValid(numberDouble);

        return condition1 || condition2;
    }
}
