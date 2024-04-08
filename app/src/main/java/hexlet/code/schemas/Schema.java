package hexlet.code.schemas;

public interface Schema {
    Schema required();
    Schema minLength(Object minLength);
    Schema contains(Object objToContain);
    boolean isValid(Object objToValidate);
}
