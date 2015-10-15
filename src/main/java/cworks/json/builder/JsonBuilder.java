/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Baked with love by comartin
 * Package: net.cworks.json.builder
 * User: comartin
 * Created: 8/11/2014 11:20 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json.builder;

import cworks.json.JsonDeserializer;
import cworks.json.JsonErrorHandler;
import cworks.json.JsonObject;
import cworks.json.JsonSerializer;
import cworks.json.parser.JsonParser;

public abstract class JsonBuilder {

    private JsonConfigurationStep configurationBuilder;
    private JsonStep jsonStep;

    public JsonBuilder parser(JsonParser parser) {
        return this;
    }

    public JsonBuilder serializer(JsonSerializer serializer) {
        return this;
    }

    public JsonBuilder deserializer(JsonDeserializer deserializer) {
        return this;
    }

    public JsonBuilder errorHandler(JsonErrorHandler handler) {

        return this;
    }


    public JsonBuilder pretty() {
        return this;
    }

    // Terminal
    public JsonObject toObject(String json) {
        return null;
    }

    // Terminal
    public String toString(JsonObject object) {
        return null;
    }
}
