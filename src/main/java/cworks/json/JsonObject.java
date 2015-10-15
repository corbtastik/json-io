/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Baked with love by corbett
 * Package: net.cworks.json
 * User: corbett
 * Created: 09/10/2013 13:30
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json;

import org.apache.commons.codec.binary.Base64;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonObject extends JsonElement {

    /**
     * internal map of properties
     */
    protected Map<String, Object> map;

    /**
     * Create a JSON object based on the specified Map
     *
     * @param map
     */
    public JsonObject(Map<String, Object> map) {
        this.map = map;
    }

    /**
     * Create an empty JSON object
     */
    public JsonObject() {
        this.map = new LinkedHashMap<String, Object>();
    }

    /**
     * Create a JSON object from a string form of a JSON object
     *
     * @param json string form of a JSON object
     */
    public JsonObject(String json) {
        map = Json.io().asObject(json, Map.class);
    }

    public JsonObject setString(String field, String value) {
        map.put(field, value);
        return this;
    }

    public JsonObject setObject(String field, JsonObject value) {
        map.put(field, value == null ? null : value.map);
        return this;
    }

    public JsonObject setArray(String field, JsonArray value) {
        map.put(field, value.list);
        return this;
    }

    public JsonObject setElement(String field, JsonElement value) {
        if (value.isArray()) {
            return this.setArray(field, value.asArray());
        }
        return this.setObject(field, value.asObject());
    }

    public JsonObject setNumber(String field, Number value) {
        map.put(field, value);
        return this;
    }

    public JsonObject setBoolean(String field, Boolean value) {
        map.put(field, value);
        return this;
    }

    public JsonObject setBinary(String field, byte[] binary) {
        map.put(field, Base64.encodeBase64String(binary));
        return this;
    }

    public JsonObject setValue(String field, Object value) {
        if (value instanceof JsonObject) {
            setObject(field, (JsonObject) value);
        } else if (value instanceof JsonArray) {
            setArray(field, (JsonArray) value);
        } else {
            map.put(field, value);
        }
        return this;
    }

    public String getString(String field) {
        return (String) map.get(field);
    }

    @SuppressWarnings("unchecked")
    public JsonObject getObject(String field) {
        Map<String, Object> m = (Map<String, Object>) map.get(field);
        return m == null ? null : new JsonObject(m);
    }

    @SuppressWarnings("unchecked")
    public JsonArray getArray(String field) {
        List<Object> l = (List<Object>) map.get(field);
        return l == null ? null : new JsonArray(l);
    }

    public JsonElement getElement(String field) {
        Object element = map.get(field);
        if (element instanceof Map<?,?>){
            return getObject(field);
        }
        if (element instanceof List<?>){
            return getArray(field);
        }
        throw new ClassCastException();
    }

    public Number getNumber(String field) {
        return (Number)map.get(field);
    }

    public Long getLong(String field) {
        Number num = (Number) map.get(field);
        return num == null ? null : num.longValue();
    }

    public Integer getInteger(String field) {
        Number num = (Number) map.get(field);
        return num == null ? null : num.intValue();
    }

    public Boolean getBoolean(String field) {
        return (Boolean) map.get(field);
    }

    public byte[] getBinary(String field) {
        String encoded = (String) map.get(field);
        return Base64.decodeBase64(encoded);
    }

    public String getString(String field, String def) {
        String str = getString(field);
        return str == null ? def : str;
    }

    public JsonObject getObject(String field, JsonObject def) {
        JsonObject obj = getObject(field);
        return obj == null ? def : obj;
    }

    public JsonArray getArray(String field, JsonArray def) {
        JsonArray arr = getArray(field);
        return arr == null ? def : arr;
    }

    public JsonElement getElement(String field, JsonElement def) {
        JsonElement elem = getElement(field);
        return elem == null ? def : elem;
    }

    public boolean getBoolean(String field, boolean def) {
        Boolean b = getBoolean(field);
        return b == null ? def : b;
    }

    public Number getNumber(String field, int def) {
        Number n = getNumber(field);
        return n == null ? def : n;
    }

    public Long getLong(String field, long def) {
        Number num = (Number) map.get(field);
        return num == null ? def : num.longValue();
    }

    public Integer getInteger(String field, int def) {
        Number num = (Number) map.get(field);
        return num == null ? def : num.intValue();
    }

    public byte[] getBinary(String field, byte[] def) {
        byte[] b = getBinary(field);
        return b == null ? def : b;
    }

    public Set<String> getFieldNames() {
        return map.keySet();
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(String field) {
        Object obj = map.get(field);
        if (obj != null) {
            if (obj instanceof Map) {
                obj = new JsonObject((Map)obj);
            } else if (obj instanceof List) {
                obj = new JsonArray((List)obj);
            }
        }
        return (T)obj;
    }

    @SuppressWarnings("unchecked")
    public <T> T getField(String field) {
        Object obj = map.get(field);
        if (obj instanceof Map) {
            obj = new JsonObject((Map)obj);
        } else if (obj instanceof List) {
            obj = new JsonArray((List)obj);
        }
        return (T)obj;
    }

    public Object removeField(String field) {
        return map.remove(field);
    }

    public int size() {
        return map.size();
    }

    public JsonObject merge(JsonObject other) {
        map.putAll(other.map);
        return this;
    }

    public String asString() {
        return Json.asJson(this.map);
    }

    public JsonObject copy() {
        Map<String, Object> copiedMap = cloneMap(map);
        JsonObject copy = new JsonObject(copiedMap);

        return copy;
    }

    @Override
    public String toString() {
        return asString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        JsonObject that = (JsonObject)o;

        if(this.map.size() != that.map.size()) {
            return false;
        }

        for (Map.Entry<String, Object> entry : this.map.entrySet()) {
            Object val = entry.getValue();
            if (val == null) {
                if (that.map.get(entry.getKey()) != null) {
                    return false;
                }
            } else {
                if (!entry.getValue().equals(that.map.get(entry.getKey()))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Return the internal map...TODO I hate this!  Consider returning copy
     * @return
     */
    public Map<String, Object> toMap() {
        return map;
    }

    @SuppressWarnings("unchecked")
    static Map<String, Object> cloneMap(Map<String, Object> source) {

        Map<String, Object> target = new LinkedHashMap<String, Object>(source.size());

        for (Map.Entry<String, Object> entry : source.entrySet()) {
            Object value = entry.getValue();

            if (value instanceof Map) {
                Map<String, Object> map = (Map<String, Object>)value;
                target.put(entry.getKey(), cloneMap(map));
            } else if (value instanceof List) {
                List<Object> list = (List<Object>)value;
                target.put(entry.getKey(), JsonArray.cloneList(list));
            } else {
                target.put(entry.getKey(), value);
            }
        }

        return target;
    }

}

