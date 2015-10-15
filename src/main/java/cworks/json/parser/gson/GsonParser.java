/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: json
 * Package: net.cworks.json.parser
 * Class: GsonParser
 * Created: 8/1/14 3:40 PM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json.parser.gson;

import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonObject;
import cworks.json.parser.JsonParser;
import cworks.json.streaming.StreamHandler;
import cworks.json.streaming.Token;

import java.io.InputStream;
import java.util.List;

public class GsonParser extends JsonParser {

    @Override
    public JsonElement toObject(String json) throws JsonException {
        return null;
    }

    @Override
    public <T> T toObject(String json, Class<T> clazz) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] toArray(String json, Class<T> clazz) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> toList(String json) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> toList(String json, Class<T> clazz) throws JsonException {
        return null;
    }

    @Override
    public String toJson(JsonElement element) throws JsonException {
        return null;
    }

    @Override
    public String toJson(Object obj) throws JsonException {
        return null;
    }

    @Override
    public void read(InputStream in, StreamHandler<Token> handler) throws JsonException {

    }

    @Override
    public <T> void read(InputStream in, Class<T> clazz, StreamHandler<T> handler) throws JsonException {

    }
}
