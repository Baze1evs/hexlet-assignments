package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> map = storage.toMap();

        map.forEach((key, value) -> {
            storage.set(value, key);
            storage.unset(key);
        });
    }
}
// END
