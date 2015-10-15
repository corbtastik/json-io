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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class JsonArray extends JsonElement implements Iterable<Object> {

    protected List<Object> list;

    public JsonArray(List<Object> array) {
        this.list = array;
    }

    public JsonArray(Object[] array) {
        this.list = Arrays.asList(array);
    }

    public JsonArray() {
        this.list = new ArrayList<Object>();
    }

    public JsonArray(String jsonString) {
        list = Json.asObject(jsonString, List.class);
    }

    public JsonArray addString(String str) {
        list.add(str);
        return this;
    }

    public JsonArray addObject(JsonObject value) {
        list.add(value.map);
        return this;
    }
    
    public JsonArray addArray(JsonArray value) {
        list.add(value.list);
        return this;
    }

    public JsonArray addElement(JsonElement value) {
        if (value.isArray()) {
            return addArray(value.asArray());
        }
        return addObject(value.asObject());
    }
    
    public JsonArray addElements(JsonElement...values) {
        for(JsonElement v : values) {
            addElement(v);
        }
        return this;
    }

    public JsonArray addNumber(Number value) {
        list.add(value);
        return this;
    }

    public JsonArray addBoolean(Boolean value) {
        list.add(value);
        return this;
    }

    public JsonArray addBinary(byte[] value) {
        String encoded = Base64.encodeBase64String(value);
        list.add(encoded);
        return this;
    }

    public JsonArray add(Object obj) {

        if (obj instanceof JsonObject) {
            obj = ((JsonObject) obj).map;
        } else if (obj instanceof JsonArray) {
            obj = ((JsonArray) obj).list;
        }
        list.add(obj);
        return this;
    }

    public int size() {
        return list.size();
    }

    public <T> T get(final int index) {
        return toObject(list.get(index));
    }
    
    public JsonObject objectAt(final int index) {
        
        JsonElement element = toObject(list.get(index));
        if(element.isObject()) {
            return element.asObject();
        }
        
        return element.asArray().asObject();
    }
    
    public Double doubleAt(final int index) {
        Double value = (Double)list.get(index);
        return value;
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {

            Iterator<Object> iter = list.iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public Object next() {
                return toObject(iter.next());
            }

            @Override
            public void remove() {
                iter.remove();
            }
        };
    }

    public boolean contains(Object value) {
        return list.contains(value);
    }

    public String asString() throws JsonException {
        return Json.asJson(this.list);
    }

    public JsonArray copy() {

        List<Object> copiedList = cloneList(list);
        JsonArray copy = new JsonArray(copiedList);

        return copy;
    }

    @Override
    public String toString() {
        return asString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        JsonArray that = (JsonArray) o;

        if (this.list.size() != that.list.size())
            return false;

        Iterator<?> iter = that.list.iterator();
        for (Object entry : this.list) {
            Object other = iter.next();
            if (!entry.equals(other)) {
                return false;
            }
        }
        return true;
    }

    public Object[] toArray() {
        return cloneList(list).toArray();
    }

    @SuppressWarnings("unchecked")
    static List<Object> cloneList(List<?> list) {

        List<Object> objects = new ArrayList<Object>(list.size());

        for (Object obj : list) {
            if (obj instanceof Map) {
                objects.add(JsonObject.cloneMap((Map<String, Object>) obj));
            } else if (obj instanceof JsonObject) {
                objects.add(((JsonObject) obj).toMap());
            } else if (obj instanceof List) {
                objects.add(cloneList((List<?>)obj));
            } else {
                objects.add(obj);
            }
        }

        return objects;
    }

    @SuppressWarnings("unchecked")
    private static <T> T toObject(final Object obj) {

        Object value = obj;
        if (obj != null) {
            if (obj instanceof List) {
                value = new JsonArray((List<Object>)obj);
            } else if (obj instanceof Map) {
                value = new JsonObject((Map<String, Object>)obj);
            }
        }

        return (T)value;
    }
}
