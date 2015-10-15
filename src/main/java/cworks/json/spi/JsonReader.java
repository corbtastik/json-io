package cworks.json.spi;

import cworks.json.JsonArray;
import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonObject;
import cworks.json.streaming.StreamHandler;
import cworks.json.streaming.Token;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface JsonReader {
    
    public void asStream(InputStream input, StreamHandler<Token> handler) throws JsonException;
    public Stream<Token> asStream(InputStream input) throws JsonException;
    public void asStream(Reader input, StreamHandler<Token> handler) throws JsonException;
    public Stream<Token> asStream(Reader input) throws JsonException;
    public void asStream(File input, StreamHandler<Token> handler) throws JsonException;
    public Stream<Token> asStream(File input) throws JsonException;
    public void asStream(Path input, StreamHandler<Token> handler) throws JsonException;
    public Stream<Token> asStream(Path input) throws JsonException;
    
    public JsonElement asElement(String input) throws JsonException;
    public JsonObject asObject(String input) throws JsonException;
    public JsonArray asArray(String input) throws JsonException;
    
    public <T> T asObject(String input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(String input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(StringBuffer input) throws JsonException;
    public JsonObject  asObject(StringBuffer input) throws JsonException;
    public JsonArray   asArray(StringBuffer input) throws JsonException;

    public <T> T asObject(StringBuffer input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(StringBuffer input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(StringBuilder input) throws JsonException;
    public JsonObject  asObject(StringBuilder input) throws JsonException;
    public JsonArray   asArray(StringBuilder input) throws JsonException;

    public <T> T asObject(StringBuilder input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(StringBuilder input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(Reader input) throws JsonException;
    public JsonObject asObject(Reader input) throws JsonException;
    public JsonArray asArray(Reader input) throws JsonException;

    public <T> T asObject(Reader input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(Reader input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(File input) throws JsonException;
    public JsonObject asObject(File input) throws JsonException;
    public JsonArray asArray(File input) throws JsonException;

    public <T> T asObject(File input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(File input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(InputStream input) throws JsonException;
    public JsonObject asObject(InputStream input) throws JsonException;
    public JsonArray asArray(InputStream input) throws JsonException;

    public <T> T asObject(InputStream input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(InputStream input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(Path input) throws JsonException;
    public JsonObject asObject(Path input) throws JsonException;
    public JsonArray asArray(Path input) throws JsonException;

    public <T> T asObject(Path input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(Path input, Class<T> arrayType) throws JsonException;

    public List<JsonObject> asList(String input) throws JsonException;
    public List<JsonObject> asList(StringBuffer input) throws JsonException;
    public List<JsonObject> asList(StringBuilder input) throws JsonException;
    public List<JsonObject> asList(File input) throws JsonException;
    public List<JsonObject> asList(Path input) throws JsonException;
    public List<JsonObject> asList(Reader input) throws JsonException;
    public List<JsonObject> asList(InputStream input) throws JsonException;

    public <T> List<T> asList(String input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(StringBuffer input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(StringBuilder input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(File input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(Path input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(Reader input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(InputStream input, Class<T> listType) throws JsonException;

    public Map<String, Object> asMap(String input) throws JsonException;
    public Map<String, Object> asMap(StringBuffer input) throws JsonException;
    public Map<String, Object> asMap(StringBuilder input) throws JsonException;
    public Map<String, Object> asMap(File input) throws JsonException;
    public Map<String, Object> asMap(Path input) throws JsonException;
    public Map<String, Object> asMap(Reader input) throws JsonException;
    public Map<String, Object> asMap(InputStream input) throws JsonException;

    public <T> Map<String, T> asMap(String input, Class<T> mapType) throws JsonException;
    public <T> Map<String, T> asMap(StringBuffer input, Class<T> mapType) throws JsonException;
    public <T> Map<String, T> asMap(StringBuilder input, Class<T> mapType) throws JsonException;
    public <T> Map<String, T> asMap(File input, Class<T> mapType) throws JsonException;
    public <T> Map<String, T> asMap(Path input, Class<T> mapType) throws JsonException;
    public <T> Map<String, T> asMap(Reader input, Class<T> mapType) throws JsonException;
    public <T> Map<String, T> asMap(InputStream input, Class<T> mapType) throws JsonException;

}
