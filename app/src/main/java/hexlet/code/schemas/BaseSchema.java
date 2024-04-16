package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public final boolean isValid(T value) {
        var condition1 = !required && checks.get("allowed").test(value);
        var condition2 = !checks.get("allowed").test(value) && checks.values().stream()
                .skip(1)
                .allMatch(predicate -> predicate.test(value));

        return condition1 || condition2;
    }
}
