package com.sparuvu.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * A static utility class that flattens the given json in the HashMap format.
 *
 *
 * <Strong>Note:</Strong> Since the input is consumed in the HashMap format, the duplicate keys are overridden by the
 * latest value.
 *
 * Examples:
 * {
 *      "abc" : "def"
 * }
 *  =>
 * {
 *      "abc" : "def"
 * }  # no change in the input
 *
 *
 * {
 *      "a": 1,
 *      "b": true,
 *      "c": {
 *          "d": 3,
 *          "e": "test"
 *      }
 * }
 * # "{\"a\": 1,  \"b\": true, \"c\": {\"d\": 3,\"e\": \"test\"  }  }"
 *
 *  =>
 *  {
 *      "a": 1,
 *      "b": true,
 *      "c.d": 3,
 *      "c.e": "test"
 *  }
 *  # {"a":1,"b":true,"c.d":3,"c.e":"test"}
 */
public class JsonFlattener {
    /**
     * A static utility method that flattens the given Hashmap.
     *
     * @param input a hashmap (json) potentially nested that needs to be flattened.
     * @return a flattened Hashmap with only one level of depth
     */
    public static Map<String, ?> flatten(Map<String, ?> input) {
        Map<String, Object> output = new HashMap<>();
        flatten(input, output, null);
        return output;
    }

    private static Map<String, ?> flatten(Map<String, ?> input, Map<String, Object> output, String prefix) {
        Objects.requireNonNull(input, "input cannot be null");

        for(Map.Entry<String, ?> entry: input.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof Map) {
                flatten((HashMap<String, Object>)value, output, key);
            } else if (isValidType(value)){
                output.put(buildkey(key, prefix), value);
            } else {
                throw new IllegalArgumentException("a value of unknown type has been encountered");
            }
        }
        return output;
    }

    private static boolean isValidType(Object value) {
        if(Objects.isNull(value)) {
            return true;
        }
        return (value instanceof Integer)
        || (value instanceof String)
        || (value instanceof Boolean);
    }

    private static String buildkey(String key, String prefix) {
        return Optional.ofNullable(prefix)
                .map(p -> p + "." + key)
                .orElse(key);
    }
}
