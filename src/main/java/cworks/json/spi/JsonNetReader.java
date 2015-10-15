package cworks.json.spi;

import cworks.json.JsonArray;
import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonObject;

import java.net.URL;

public interface JsonNetReader extends JsonReader {
    public JsonElement asElement(URL input) throws JsonException;
    public JsonObject asObject(URL input) throws JsonException;
    public JsonArray asArray(URL input) throws JsonException;
    public <T> T asObject(URL input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(URL input, Class<T> arrayType) throws JsonException;
}
