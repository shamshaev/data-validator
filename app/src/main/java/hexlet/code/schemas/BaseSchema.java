package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequiredActive;

    protected boolean isRequiredValidWithNull(T object) {
        return !isRequiredActive && object == null;
    }

    protected boolean isRequiredValidWithNotNull(T object) {
        return object != null;
    }

    public abstract boolean isValid(T object);
}
