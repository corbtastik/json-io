package cworks.metadata;

import cworks.json.JsonArray;
import cworks.json.JsonElement;
import cworks.json.JsonObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MetaObject extends MetaElement {

    private JsonObject metaObject;

    public MetaObject(Map<String, Object> map) {
        metaObject = new JsonObject(map);
    }

    public MetaObject() {
        metaObject = new JsonObject();
    }

    public MetaObject setString(String field, String value) {
        metaObject.setString(field, value);
        return this;
    }

    public MetaObject setObject(String field, MetaObject value) {
        metaObject.setObject(field, toJsonObject(value));
        return this;
    }

    public MetaObject setArray(String field, MetaArray value) {
        metaObject.setArray(field, toJsonArray(value));
        return this;
    }

    public MetaObject setElement(String field, MetaElement value) {
        if (value.isArray()) {
            return this.setArray(field, value.asArray());
        }
        return this.setObject(field, value.asObject());
    }

    public MetaObject setNumber(String field, Number value) {
        metaObject.setNumber(field, value);
        return this;
    }

    public MetaObject setBoolean(String field, Boolean value) {
        metaObject.setBoolean(field, value);
        return this;
    }

    public MetaObject setBinary(String field, byte[] binary) {
        metaObject.setBinary(field, binary);
        return this;
    }

    public MetaObject setValue(String field, Object value) {
        metaObject.setValue(field, value);
        return this;
    }

    public String getString(String field) {
        return metaObject.getString(field);
    }

    @SuppressWarnings("unchecked")
    public MetaObject getObject(String field) {
        return toMetaObject(metaObject.getObject(field));
    }

    @SuppressWarnings("unchecked")
    public MetaArray getArray(String field) {
        return toMetaArray(metaObject.getArray(field));
    }

    public MetaElement getElement(String field) {
        return toMetaElement(metaObject.getElement(field));
    }

    public Number getNumber(String field) {
        return metaObject.getNumber(field);
    }

    public Long getLong(String field) {
        return metaObject.getLong(field);
    }

    public Integer getInteger(String field) {
        return metaObject.getInteger(field);
    }

    public Boolean getBoolean(String field) {
        return metaObject.getBoolean(field);
    }

    public byte[] getBinary(String field) {
        return metaObject.getBinary(field);
    }

    public String getString(String field, String def) {
        return metaObject.getString(field, def);
    }

    public MetaObject getObject(String field, MetaObject def) {
        return toMetaObject(metaObject.getObject(field, toJsonObject(def)));
    }

    public MetaArray getArray(String field, MetaArray def) {
        return toMetaArray(metaObject.getArray(field, toJsonArray(def)));
    }

    public MetaElement getElement(String field, MetaElement def) {
        return toMetaElement(metaObject.getElement(field, toJsonElement(def)));
    }

    public boolean getBoolean(String field, boolean def) {
        return metaObject.getBoolean(field, def);
    }

    public Number getNumber(String field, int def) {
        return metaObject.getNumber(field, def);
    }

    public Long getLong(String field, long def) {
        return metaObject.getLong(field, def);
    }

    public Integer getInteger(String field, int def) {
        return metaObject.getInteger(field, def);
    }

    public byte[] getBinary(String field, byte[] def) {
        return metaObject.getBinary(field, def);
    }

    public Set<String> getFieldNames() {
        return metaObject.getFieldNames();
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(String field) {
        return metaObject.getValue(field);
    }

    @SuppressWarnings("unchecked")
    public <T> T getField(String field) {
        return metaObject.getField(field);
    }

    public Object removeField(String field) {
        return metaObject.removeField(field);
    }

    public int size() {
        return metaObject.size();
    }

    public MetaObject merge(MetaObject other) {
        return toMetaObject(metaObject.merge(toJsonObject(other)));
    }

    public MetaObject copy() {
        return toMetaObject(metaObject.copy());
    }

    @Override
    public boolean equals(Object o) {
        return metaObject.equals(o);
    }

    /**
     * Return the internal map...TODO I hate this!  Consider returning copy
     * @return
     */
    public Map<String, Object> toMap() {
        return metaObject.toMap();
    }

    private static MetaArray toMetaArray(JsonArray array) {
        return new MetaArray(array.toArray());
    }

    private static MetaElement toMetaElement(JsonElement element) {
        if(element.isArray()) {
            return toMetaArray(element.asArray());
        }

        return toMetaObject(element.asObject());
    }

    private static JsonElement toJsonElement(MetaElement element) {
        if(element.isArray()) {
            return toJsonArray(element.asArray());
        }

        return toJsonObject(element.asObject());
    }

    private static JsonArray toJsonArray(MetaArray array) {
        return new JsonArray(array.toArray());
    }

    private static MetaObject toMetaObject(JsonObject object) {
        return new MetaObject(object.toMap());
    }

    private static JsonObject toJsonObject(MetaObject object) {
        return new JsonObject(object.toMap());
    }

    @SuppressWarnings("unchecked")
    static Map<String, Object> cloneMap(Map<String, Object> source) {

        Map<String, Object> target = new LinkedHashMap<>(source.size());

        for (Map.Entry<String, Object> entry : source.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                Map<String, Object> map = (Map<String, Object>)value;
                target.put(entry.getKey(), cloneMap(map));
            } else if (value instanceof List) {
                List<Object> list = (List<Object>)value;
                target.put(entry.getKey(), MetaArray.cloneList(list));
            } else {
                target.put(entry.getKey(), value);
            }
        }

        return target;
    }

}

