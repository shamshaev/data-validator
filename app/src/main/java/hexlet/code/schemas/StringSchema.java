package hexlet.code.schemas;

public class StringSchema {
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
        if (isRequired) {
            return stringToValidate != null && !stringToValidate.isEmpty();
        }
        if (isMinLength) {
            return stringToValidate.length() >= minLength;
        }
        if (isContains) {
            return stringToValidate.contains(stringToContain);
        }
        return true;
    }
}
