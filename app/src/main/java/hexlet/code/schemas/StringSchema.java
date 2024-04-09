package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private boolean isRequired;
    private boolean isMinLength;
    private int minLength;
    private boolean isContains;
    private String stringToContain;

    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        isMinLength = true;
        minLength = length;
        return this;
    }
    public StringSchema contains(String string) {
        isContains = true;
        stringToContain = string;
        return this;
    }
    public boolean isValid(String stringToValidate) {
        if (isRequired && (stringToValidate == null || stringToValidate.isEmpty())) {
            return false;
        }
        if (isMinLength && stringToValidate.length() < minLength) {
            return false;
        }
        if (isContains && !stringToValidate.contains(stringToContain)) {
            return false;
        }
        return true;
    }
}
