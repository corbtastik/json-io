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

public class JsonObjectBuilder {

    private JsonObject jo = null;

    public JsonObjectBuilder() {
        this.jo = new JsonObject();
    }

    public JsonObjectBuilder string(String field, String value) {
        jo.setString(field, value);
        return this;
    }

    public JsonObjectBuilder object(String field, JsonObject value) {
        jo.setObject(field, value);
        return this;
    }

    public JsonObjectBuilder array(String field, JsonArray value) {
        jo.setArray(field, value);
        return this;
    }

    public JsonObjectBuilder element(String field, JsonElement value) {
        jo.setElement(field, value);
        return this;
    }

    public JsonObjectBuilder number(String field, Number value) {
        jo.setNumber(field, value);
        return this;
    }

    public JsonObjectBuilder bool(String field, Boolean value) {
        jo.setBoolean(field, value);
        return this;
    }

    public JsonObjectBuilder binary(String field, byte[] binary) {
        jo.setBinary(field, binary);
        return this;
    }

    public JsonObjectBuilder value(String field, Object value) {
        jo.setValue(field, value);
        return this;
    }

    public JsonObjectBuilder merge(JsonObject other) {
        jo.merge(other);
        return this;
    }

    public JsonObject build() {
        return this.jo;
    }
}

