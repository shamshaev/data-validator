package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Number> {
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

    private boolean isPositiveValid(Double numberDouble) {
        return !isPositiveActive || numberDouble > 0;
    }

    private boolean isRangeValid(Double numberDouble) {
        return !isRangeActive || numberDouble >= minRange && numberDouble <= maxRange;
    }

    @Override
    public boolean isValid(Number number) {
        Double numberDouble = (number != null) ? number.doubleValue() : null;

        var condition1 = isRequiredValidWithNull(numberDouble);
        var condition2 = isRequiredValidWithNotNull(numberDouble) && isPositiveValid(numberDouble)
                && isRangeValid(numberDouble);

        return condition1 || condition2;
    }
}
