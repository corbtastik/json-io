package cworks.json.impl.gson;

import cworks.json.JsonArray;
import cworks.json.JsonContext;
import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonObject;
import cworks.json.io.AbstractJsonIO;
import cworks.json.spi.JsonReader;
import cworks.json.spi.JsonWriter;
import cworks.json.streaming.StreamHandler;
import cworks.json.streaming.Token;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GsonIO extends AbstractJsonIO {

    public GsonIO() {
        super();
    }

    public GsonIO(JsonContext context) {
        super(context);
    }
    
    @Override
    public JsonReader getReader() {
        return null;
    }

    @Override
    public JsonWriter getWriter() {
        return null;
    }

    @Override
    public void asStream(InputStream in, StreamHandler<Token> handler) throws JsonException {

    }

    @Override
    public Stream<Token> asStream(InputStream input) throws JsonException {
        return null;
    }

    @Override
    public void asStream(Reader input, StreamHandler<Token> handler) throws JsonException {

    }

    @Override
    public Stream<Token> asStream(Reader input) throws JsonException {
        return null;
    }

    @Override
    public void asStream(File input, StreamHandler<Token> handler) throws JsonException {

    }

    @Override
    public Stream<Token> asStream(File input) throws JsonException {
        return null;
    }

    @Override
    public void asStream(Path input, StreamHandler<Token> handler) throws JsonException {

    }

    @Override
    public Stream<Token> asStream(Path input) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(String input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(String input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(String input) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T asObject(String input, Class<T> objectType) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T[] asArray(String input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(StringBuffer input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(StringBuffer input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(StringBuffer input) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T asObject(StringBuffer input, Class<T> objectType) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T[] asArray(StringBuffer input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(StringBuilder input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(StringBuilder input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(StringBuilder input) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T asObject(StringBuilder input, Class<T> objectType) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T[] asArray(StringBuilder input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(Reader input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(Reader input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(Reader input) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T asObject(Reader input, Class<T> objectType) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T[] asArray(Reader input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(File input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(File input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(File input) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T asObject(File input, Class<T> objectType) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T[] asArray(File input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(InputStream input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(InputStream input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(InputStream input) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T asObject(InputStream input, Class<T> objectType) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T[] asArray(InputStream input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(Path input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(Path input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(Path input) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T asObject(Path input, Class<T> objectType) throws JsonException {
        return null;
    }
    
    @Override
    public <T> T[] asArray(Path input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(String input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(StringBuffer input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(StringBuilder input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(File input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(Path input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(Reader input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(InputStream input) throws JsonException {
        return null;
    }
    
    @Override
    public <T> List<T> asList(String input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(StringBuffer input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(StringBuilder input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(File input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(Path input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(Reader input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(InputStream input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public Map<String, Object> asMap(String input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, Object> asMap(StringBuffer input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, Object> asMap(StringBuilder input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, Object> asMap(File input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, Object> asMap(Path input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, Object> asMap(Reader input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, Object> asMap(InputStream input) throws JsonException {
        return null;
    }
    
    @Override
    public <T> Map<String, T> asMap(String input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, T> asMap(StringBuffer input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, T> asMap(StringBuilder input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, T> asMap(File input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, T> asMap(Path input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, T> asMap(Reader input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, T> asMap(InputStream input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Object object) throws JsonException {
        return null;
    }

    @Override
    public <T> String asJson(Object object, Class<T> objectType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Object[] objects) throws JsonException {
        return null;
    }

    @Override
    public <T> String asJson(Object[] objects, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(List object) throws JsonException {
        return null;
    }

    @Override
    public <T> String asJson(List objects, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Map object) throws JsonException {
        return null;
    }

    @Override
    public <T> String asJson(Map object, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public void asJson(Object object, Writer output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Writer output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> arrayType, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object object, File output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, File output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, File output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> arrayType, File output) throws JsonException {

    }

    @Override
    public void asJson(Object object, OutputStream output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, OutputStream output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> objectType, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Path output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, Path output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Path output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> objectType, Path output) throws JsonException {

    }

    @Override
    public String asJson(JsonElement element) throws JsonException {
        return null;
    }

    @Override
    public void asJson(JsonElement element, Writer output) throws JsonException {
    }

    @Override
    public void asJson(JsonElement element, File output) throws JsonException {
    }

    @Override
    public void asJson(JsonElement element, OutputStream output) throws JsonException {
    }

    @Override
    public void asJson(JsonElement element, Path output) throws JsonException {
    }

    @Override
    public String asJson(JsonObject object) throws JsonException {
        return null;
    }

    @Override
    public void asJson(JsonObject object, Writer output) throws JsonException {
    }

    @Override
    public void asJson(JsonObject object, File output) throws JsonException {
    }

    @Override
    public void asJson(JsonObject object, OutputStream output) throws JsonException {
    }

    @Override
    public void asJson(JsonObject object, Path output) throws JsonException {
    }

    @Override
    public String asJson(JsonArray array) throws JsonException {
        return null;
    }

    @Override
    public void asJson(JsonArray array, Writer output) throws JsonException {
    }

    @Override
    public void asJson(JsonArray array, File output) throws JsonException {
    }

    @Override
    public void asJson(JsonArray array, OutputStream output) throws JsonException {
    }

    @Override
    public void asJson(JsonArray array, Path output) throws JsonException {
    }
}
