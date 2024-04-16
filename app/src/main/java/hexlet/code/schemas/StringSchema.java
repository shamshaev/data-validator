package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema() {
        addCheck(
                "allowed",
                value -> value == null || value.isEmpty()
        );
    }

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(
                "minLength",
                value -> value.length() >= length
        );
        return this;
    }

    public StringSchema contains(String string) {
        addCheck(
                "contains",
                value -> value.contains(string)
        );
        return this;
    }
}
