package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private boolean isMinLengthActive;
    private int minLength;
    private boolean isContainsActive;
    private String stringToContain;

    public StringSchema required() {
        isRequiredActive = true;
        return this;
    }

    public StringSchema minLength(int length) {
        isMinLengthActive = true;
        minLength = length;
        return this;
    }
    public StringSchema contains(String string) {
        isContainsActive = true;
        stringToContain = string;
        return this;
    }

    private boolean isMinLengthValid(String string) {
        return !isMinLengthActive || string.length() >= minLength;
    }

    private boolean isContainsValid(String string) {
        return !isContainsActive || string.contains(stringToContain);
    }

    @Override
    public boolean isValid(String string) {
        var stringNullAndEmptySameOut = (string != null && string.isEmpty()) ? null : string;

        var condition1 = isRequiredValidWithNull(stringNullAndEmptySameOut);
        var condition2 = isRequiredValidWithNotNull(stringNullAndEmptySameOut) && isMinLengthValid(string)
                && isContainsValid(string);

        return condition1 || condition2;
    }
}
