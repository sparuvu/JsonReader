package com.sparuvu.json;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Test
public class JsonFlattenerTest {
    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = "input cannot be null")
    public void testFlattenWithNullInput() {
        JsonFlattener.flatten(null);
    }

    @Test
    public void testFlattenWithoutNestedObjects() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", 2);

        Map<String, ?> actual = JsonFlattener.flatten(map);
        assertEquals(actual.size(), 2);
        for (Map.Entry<String, ?> entry : actual.entrySet()) {
            String key = entry.getKey();
            assertEquals(entry.getValue(), map.get(key));
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "a value of unknown type has been encountered")
    public void testFlattenWithUnSupportedValues() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", new int[]{1});

        JsonFlattener.flatten(map);
    }

    @Test
    public void testFlatten() {
        Map<String, Object> innerMap = new HashMap<>();
        innerMap.put("d", true);
        innerMap.put("e", false);

        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", 2);
        map.put("c", innerMap);

        Map<String, ?> actual = JsonFlattener.flatten(map);
        assertEquals(actual.size(), 4);
        assertEquals(actual.get("a"), map.get("a"));
        assertEquals(actual.get("b"), map.get("b"));
        assertEquals(actual.get("c.d"), ((HashMap<String, Object>)map.get("c")).get("d"));
        assertEquals(actual.get("c.e"), ((HashMap<String, Object>)map.get("c")).get("e"));
    }
}
