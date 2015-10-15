/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Baked with love by corbett
 * Package: net.cworks.json
 * User: corbett
 * Created: 09/10/2013 13:30
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json;

public abstract class JsonElement {

    public boolean isArray() {
        return this instanceof JsonArray;
    }

    public boolean isObject() {
        return this instanceof JsonObject;
    }

    public JsonArray asArray() {
        return (JsonArray)this;
    }

    public JsonObject asObject() {
        return (JsonObject)this;
    }
}
