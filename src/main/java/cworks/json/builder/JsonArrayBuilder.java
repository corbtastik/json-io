/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Package: net.cworks.json
 * User: corbett
 * Created: 09/10/2013 13:30
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json.builder;

import cworks.json.JsonArray;
import cworks.json.JsonElement;
import cworks.json.JsonObject;

public class JsonArrayBuilder {

    private JsonArray ja = null;

    public JsonArrayBuilder() {
        ja = new JsonArray();
    }

    public JsonArrayBuilder add(String text) {
        ja.addString(text);
        return this;
    }

    public JsonArrayBuilder add(String ... texts) {
        for(String text : texts) {
            add(text);
        }
        return this;
    }

    public JsonArrayBuilder add(JsonObject object) {
        ja.addObject(object);
        return this;
    }

    public JsonArrayBuilder add(JsonObject ... objects) {
        for(JsonObject object : objects) {
            add(object);
        }
        return this;
    }

    public JsonArrayBuilder add(JsonArray array) {
        ja.addArray(array);
        return this;
    }

    public JsonArrayBuilder add(JsonArray ... arrays) {
        for(JsonArray array : arrays) {
            add(array);
        }
        return this;
    }

    public JsonArrayBuilder add(JsonElement element) {
        ja.addElement(element);
        return this;
    }

    public JsonArrayBuilder add(JsonElement ... elements) {
        for(JsonElement element : elements) {
            add(element);
        }
        return this;
    }

    public JsonArrayBuilder add(Number number) {
        ja.addNumber(number);
        return this;
    }

    public JsonArrayBuilder add(Number ... numbers) {
        for(Number number : numbers) {
            add(number);
        }
        return this;
    }

    public JsonArrayBuilder add(Boolean bool) {
        ja.addBoolean(bool);
        return this;
    }

    public JsonArrayBuilder add(Boolean ... bools) {
        for(Boolean bool : bools) {
            add(bool);
        }
        return this;
    }

    public JsonArrayBuilder add(byte[] binary) {
        ja.addBinary(binary);
        return this;
    }

    public JsonArrayBuilder add(byte[] ... binaries) {
        for(byte[] binary : binaries) {
            add(binary);
        }
        return this;
    }

    public JsonArrayBuilder add(Object object) {
        ja.add(object);
        return this;
    }

    public JsonArrayBuilder add(Object ... objects) {
        for(Object object : objects) {
            add(object);
        }
        return this;
    }

    public JsonArray build() {
        return this.ja;
    }
}
