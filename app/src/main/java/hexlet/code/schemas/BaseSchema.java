package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequiredActive;

    protected final boolean isRequiredValidWithNull(T object) {
        return !isRequiredActive && object == null;
    }

    protected final boolean isRequiredValidWithNotNull(T object) {
        return object != null;
    }

    public abstract boolean isValid(T object);
}
