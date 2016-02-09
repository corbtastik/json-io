package cworks.metadata;

import cworks.json.JsonArray;
import cworks.json.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MetaArray extends MetaElement implements Iterable<Object> {

    protected JsonArray metaArray;

    public MetaArray(List<Object> array) {
        this.metaArray = new JsonArray(array);
    }

    public MetaArray(Object[] array) {
        this.metaArray = new JsonArray(array);
    }

    public MetaArray() {
        this.metaArray = new JsonArray();
    }

    public MetaArray addString(String str) {
        metaArray.addString(str);
        return this;
    }

    public MetaArray addObject(MetaObject value) {
        metaArray.addObject(toJsonObject(value));
        return this;
    }

    public MetaArray addArray(MetaArray value) {
        metaArray.addArray(toJsonArray(value));
        return this;
    }

    public MetaArray addElement(MetaElement value) {
        if (value.isArray()) {
            return addArray(value.asArray());
        }
        return addObject(value.asObject());
    }

    public MetaArray addElements(MetaElement...values) {
        for(MetaElement v : values) {
            addElement(v);
        }
        return this;
    }

    public MetaArray addNumber(Number value) {
        metaArray.addNumber(value);
        return this;
    }

    public MetaArray addBoolean(Boolean value) {
        metaArray.addBoolean(value);
        return this;
    }

    public MetaArray addBinary(byte[] value) {
        metaArray.addBinary(value);
        return this;
    }

    public MetaArray add(Object obj) {
        metaArray.add(obj);
        return this;
    }

    public int size() {
        return metaArray.size();
    }

    public <T> T get(final int index) {
        return metaArray.get(index);
    }

    public MetaObject objectAt(final int index) {
        return toMetaObject(metaArray.objectAt(index));
    }

    public Double doubleAt(final int index) {
        return metaArray.doubleAt(index);
    }

    @Override
    public Iterator<Object> iterator() {
        return metaArray.iterator();
    }

    public boolean contains(Object value) {
        return metaArray.contains(value);
    }

    public MetaArray copy() {
        return toMetaArray(metaArray.copy());
    }

    @Override
    public boolean equals(Object o) {
        return metaArray.equals(o);
    }

    public Object[] toArray() {
        return metaArray.toArray();
    }

    private JsonObject toJsonObject(MetaObject value) {
        return new JsonObject(value.toMap());
    }

    private JsonArray toJsonArray(MetaArray value) {
        return new JsonArray(value.toArray());
    }

    private MetaArray toMetaArray(JsonArray value) {
        return new MetaArray(value.toArray());
    }

    private MetaObject toMetaObject(JsonObject value) {
        return new MetaObject(value.toMap());
    }

    @SuppressWarnings("unchecked")
    static List<Object> cloneList(List<?> list) {

        List<Object> objects = new ArrayList<Object>(list.size());

        for (Object obj : list) {
            if (obj instanceof Map) {
                objects.add(MetaObject.cloneMap((Map<String, Object>) obj));
            } else if (obj instanceof MetaObject) {
                objects.add(((MetaObject) obj).toMap());
            } else if (obj instanceof List) {
                objects.add(cloneList((List<?>)obj));
            } else {
                objects.add(obj);
            }
        }

        return objects;
    }

}
