package cworks.json;

import cworks.json.builder.JsonArrayBuilder;
import cworks.json.builder.JsonObjectBuilder;
import cworks.json.io.AbstractJsonIO;
import cworks.json.io.JsonIOBuilder;
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

public class Json {

    private static final boolean PRETTY = true;
    private static final boolean UGLY   = !PRETTY;
    
    /**
     * Create the default JsonParser which does not pretty print but allows
     * comments in JSON and has yyyy-MM-dd'T'HH:mm:ssz formatted date strings.
     *
     * @return JsonParser
     */
    static AbstractJsonIO io() {
        JsonObject config = new JsonObject();
        return io(config);
    }

    /**
     * Create a pretty print JsonParser which is same as default io
     * except that it pretty prints Json text.
     *
     * @return JsonParser
     */
    static AbstractJsonIO prettyIO() {
        JsonObject config = new JsonObject();
        config.setBoolean("pretty", true);
        return io(config);
    }

    /**
     * Create a default JsonParser but with some extra config options.  By
     * default Jackson io suite is used.
     *
     * @param config JsonIO config options
     * @return JsonParser
     */
    static AbstractJsonIO io(JsonObject config) {

        String name = System.getProperty("json.io", "JACKSON").toUpperCase();

        AbstractJsonIO io = JsonIOBuilder.io(JsonLib.valueOf(name))
                .dateFormat(config.getString("dateFormat", "yyyy-MM-dd'T'HH:mm:ssz"))
                .pretty(config.getBoolean("pretty", false))
                .allowComments(config.getBoolean("allowComments", true))
                .build();

        return io;
    }

    /**
     * Builder method for quickly creating a JsonObject
     *
     * @return JsonObjectBuilder
     */
    public static JsonObjectBuilder object() {
        return new JsonObjectBuilder();
    }

    /**
     * Builder method for quickly creating a JsonArray
     *
     * @return JsonArrayBuilder
     */
    public static JsonArrayBuilder array() {
        return new JsonArrayBuilder();
    }

    /**
     * asStream 
     * @param input JSON input JSON input
     * @param handler JSON stream handler
     * @throws JsonException
     */
    public static void asStream(InputStream input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        throwIfNull(handler);
        io().asStream(input, handler);
    }

    /**
     * asStream
     * @param input JSON input JSON input
     * @return JSON token stream
     * @throws JsonException
     */
    public static Stream<Token> asStream(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }

    /**
     * asStream
     * @param input JSON input JSON input
     * @param handler JSON stream handler
     * @throws JsonException
     */
    public static void asStream(Reader input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        throwIfNull(handler);
        io().asStream(input, handler);
    }

    /**
     * asStream 
     * @param input JSON input JSON input
     * @return JSON token stream
     * @throws JsonException
     */
    public static Stream<Token> asStream(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }

    /**
     * asStream 
     * @param input JSON input JSON input
     * @param handler JSON stream handler
     * @throws JsonException
     */
    public static void asStream(File input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        throwIfNull(handler);
        io().asStream(input, handler);
    }

    /**
     * asStream 
     * @param input JSON input JSON input
     * @return JSON token stream
     * @throws JsonException
     */
    public static Stream<Token> asStream(File input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }

    /**
     * asStream
     * @param input JSON input JSON input
     * @param handler JSON stream handler
     * @throws JsonException
     */
    public static void asStream(Path input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        throwIfNull(handler);
        io().asStream(input, handler);
    }

    /**
     * asStream 
     * @param input JSON input JSON input
     * @return JSON token stream
     * @throws JsonException
     */
    public static Stream<Token> asStream(Path input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }

    /**
     * asElement 
     * @param input JSON input JSON input
     * @return JsonElement instance
     * @throws JsonException
     */
    public static JsonElement asElement(String input) throws JsonException {
        throwIfBad(input);
        return io().asElement(input);
    }

    /**
     * asObject 
     * @param input JSON input JSON input
     * @return JsonObject instance
     * @throws JsonException
     */
    public static JsonObject asObject(String input) throws JsonException {
        throwIfBad(input);
        return io().asObject(input);
    }

    /**
     * asArray 
     * @param input JSON input JSON input
     * @return JsonArray instance
     * @throws JsonException
     */
    public static JsonArray asArray(String input) throws JsonException {
        throwIfBad(input);
        return io().asArray(input);
    }

    /**
     * asObject 
     * @param input JSON input JSON input
     * @param objectType class of instance type to create
     * @param <T> type of instance type of instance
     * @return object instance
     * @throws JsonException
     */
    public static <T> T asObject(String input, Class<T> objectType) throws JsonException {
        throwIfBad(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray
     * @param input JSON input JSON input
     * @param arrayType class of types in array
     * @param <T> type of instance type of instance
     * @return object instance
     * @throws JsonException
     */
    public static <T> T[] asArray(String input, Class<T> arrayType) throws JsonException {
        throwIfBad(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement 
     * @param input JSON input JSON input
     * @return JsonElement instance
     * @throws JsonException
     */
    public static JsonElement asElement(StringBuffer input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }

    /**
     * asObject 
     * @param input JSON input JSON input
     * @return JsonObject instance
     * @throws JsonException
     */
    public static JsonObject asObject(StringBuffer input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray 
     * @param input JSON input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(StringBuffer input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input JSON input JSON input
     * @param objectType class of instance type to create
     * @param <T> type of instance type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(StringBuffer input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray
     * @param input JSON input JSON input
     * @param arrayType
     * @param <T> type of instance type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(StringBuffer input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement
     * @param input JSON input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(StringBuilder input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }

    /**
     * asObject
     * @param input JSON input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(StringBuilder input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray
     * @param input JSON input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(StringBuilder input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input JSON input JSON input
     * @param objectType class of instance type to create
     * @param <T> type of instance type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(StringBuilder input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray 
     * @param input JSON input JSON input
     * @param arrayType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(StringBuilder input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement
     * @param input JSON input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }

    /**
     * asObject 
     * @param input JSON input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray
     * @param input JSON input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input JSON input JSON input
     * @param objectType class of instance type to create
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(Reader input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray
     * @param input JSON input JSON input
     * @param arrayType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(Reader input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement 
     * @param input JSON input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(File input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }

    /**
     * asObject 
     * @param input JSON input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(File input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(File input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input JSON input
     * @param objectType class of instance type to create
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(File input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray 
     * @param input JSON input
     * @param arrayType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(File input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }

    /**
     * asObject
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input JSON input
     * @param objectType class of instance type to create
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(InputStream input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray
     * @param input JSON input
     * @param arrayType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(InputStream input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(Path input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject 
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(Path input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray 
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(Path input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input JSON input
     * @param objectType class of instance class of instance
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(Path input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray 
     * @param input JSON input
     * @param arrayType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(Path input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asList 
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(String input) throws JsonException {
        throwIfBad(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(StringBuffer input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(StringBuilder input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(File input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(Path input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input JSON input
     * @param listType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(String input, Class<T> listType) throws JsonException {
        throwIfBad(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList
     * @param input JSON input
     * @param listType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(StringBuffer input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList
     * @param input JSON input
     * @param listType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(StringBuilder input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList
     * @param input JSON input
     * @param listType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(File input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList
     * @param input JSON input
     * @param listType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(Path input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList
     * @param input JSON input
     * @param listType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(Reader input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList 
     * @param input JSON input
     * @param listType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(InputStream input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asMap
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(String input) throws JsonException {
        throwIfBad(input);
        return io().asMap(input);
    }

    /**
     * asMap 
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(StringBuffer input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap 
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(StringBuilder input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(File input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap 
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(Path input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap
     * @param input JSON input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap 
     * @param input JSON input
     * @param mapType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(String input, Class<T> mapType) throws JsonException {
        throwIfBad(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input JSON input
     * @param mapType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(StringBuffer input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input JSON input
     * @param mapType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(StringBuilder input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input JSON input
     * @param mapType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(File input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input JSON input
     * @param mapType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(Path input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input JSON input
     * @param mapType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(Reader input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input JSON input
     * @param mapType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(InputStream input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asJson
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asJson(Object object) throws JsonException {
        return asJson(object, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param pretty 
     * @return
     * @throws JsonException
     */
    public static String asJson(Object object, boolean pretty) throws JsonException {
        throwIfNull(object);
        if(pretty) {
            return prettyIO().asJson(object);
        }
        return io().asJson(object);
    }

    /**
     * asPrettyJson
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asPrettyJson(Object object) throws JsonException {
        return asJson(object, PRETTY);
    }

    /**
     * asJson 
     * @param object
     * @param objectType class of instance class of instance
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(Object object, Class<T> objectType) throws JsonException {
        return asJson(object, objectType, UGLY);
    }

    /**
     * asJson 
     * @param object
     * @param objectType class of instance
     * @param pretty 
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(Object object, Class<T> objectType, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(objectType);
        if(pretty) {
            return prettyIO().asJson(object, objectType);
        }
        return io().asJson(object, objectType);
    }

    /**
     * asPrettyJson
     * @param object
     * @param objectType class of instance
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> String asPrettyJson(Object object, Class<T> objectType) throws JsonException {
        return asJson(object, objectType, PRETTY);
    }

    /**
     * asJson 
     * @param objects
     * @return
     * @throws JsonException
     */
    public static String asJson(Object[] objects) throws JsonException {
        return asJson(objects, UGLY);
    }

    /**
     * asJson 
     * @param objects
     * @param pretty  
     * @return
     * @throws JsonException
     */
    public static String asJson(Object[] objects, boolean pretty) throws JsonException {
        throwIfNull(objects);
        if(pretty) {
            return prettyIO().asJson(objects);
        }
        return io().asJson(objects);
    }

    /**
     * asPrettyJson
     * @param objects
     * @return
     * @throws JsonException
     */
    public static String asPrettyJson(Object[] objects) throws JsonException {
        return asJson(objects, PRETTY);
    }

    /**
     * asJson
     * @param objects
     * @param arrayType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(Object[] objects, Class<T> arrayType) throws JsonException {
        return asJson(objects, arrayType, UGLY);
    }

    /**
     * asJson
     * @param objects
     * @param arrayType
     * @param <T> type of instance
     * @param pretty     
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(Object[] objects, Class<T> arrayType, boolean pretty) throws JsonException {
        throwIfNull(objects);
        throwIfNull(arrayType);
        if(pretty) {
            return prettyIO().asJson(objects, arrayType);
        }
        return io().asJson(objects, arrayType);
    }

    /**
     * asPrettyJson
     * @param objects
     * @param arrayType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> String asPrettyJson(Object[] objects, Class<T> arrayType) throws JsonException {
        return asJson(objects, arrayType, PRETTY);
    }

    /**
     * asJson 
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asJson(List object) throws JsonException {
        return asJson(object, UGLY);
    }

    /**
     * asJson 
     * @param object
     * @param pretty 
     * @return
     * @throws JsonException
     */
    public static String asJson(List object, boolean pretty) throws JsonException {
        throwIfNull(object);
        if(pretty) {
            return prettyIO().asJson(object);
        }
        return io().asJson(object);
    }

    /**
     * asPrettyJson
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asPrettyJson(List object) throws JsonException {
        return asJson(object, PRETTY);
    }

    /**
     * asJson
     * @param objects
     * @param listType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(List objects, Class<T> listType) throws JsonException {
        return asJson(objects, listType, UGLY);
    }

    /**
     * asJson
     * @param objects
     * @param listType
     * @param <T> type of instance
     * @param pretty     
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(List objects, Class<T> listType, boolean pretty) throws JsonException {
        throwIfNull(objects);
        throwIfNull(listType);
        if(pretty) {
            return prettyIO().asJson(objects, listType);
        }
        return io().asJson(objects, listType);
    }

    /**
     * asPrettyJson
     * @param objects
     * @param listType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> String asPrettyJson(List objects, Class<T> listType) throws JsonException {
        return asJson(objects, listType, PRETTY);
    }

    /**
     * asJson
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asJson(Map object) throws JsonException {
        return asJson(object, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param pretty 
     * @return
     * @throws JsonException
     */
    public static String asJson(Map object, boolean pretty) throws JsonException {
        throwIfNull(object);
        if(pretty) {
            return prettyIO().asJson(object);
        }
        return io().asJson(object);
    }

    /**
     * asJson
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asPrettyJson(Map object) throws JsonException {
        return asJson(object, PRETTY);
    }
    
    /**
     * asJson
     * @param object
     * @param mapType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(Map object, Class<T> mapType) throws JsonException {
        return asJson(object, mapType, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param mapType
     * @param <T> type of instance
     * @param pretty
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(Map object, Class<T> mapType, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(mapType);
        if(pretty) {
            return prettyIO().asJson(object, mapType);
        }
        return io().asJson(object, mapType);
    }

    /**
     * asPrettyJson
     * @param object
     * @param mapType
     * @param <T> type of instance
     * @return
     * @throws JsonException
     */
    public static <T> String asPrettyJson(Map object, Class<T> mapType) throws JsonException {
        return asJson(object, mapType, PRETTY);
    }
    
    /**
     * asJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object object, Writer output) throws JsonException {
        asJson(object, output, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(Object object, Writer output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, output);
            return;
        }
        io().asJson(object, output);
    }

    /**
     * asPrettyJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(Object object, Writer output) throws JsonException {
        asJson(object, output, PRETTY);
    }

    /**
     * asJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, Writer output) throws JsonException {
        asJson(object, objectType, output, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @param pretty
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, Writer output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(objectType);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, objectType, output);
            return;
        }
        io().asJson(object, objectType, output);
    }

    /**
     * asPrettyJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asPrettyJson(Object object, Class<T> objectType, Writer output) throws JsonException {
        asJson(object, objectType, output, PRETTY);
    }

    /**
     * asJson
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object[] objects, Writer output) throws JsonException {
        asJson(objects, output, UGLY);
    }

    /**
     * asJson
     * @param objects
     * @param output
     * @param pretty 
     * @throws JsonException
     */
    public static void asJson(Object[] objects, Writer output, boolean pretty) throws JsonException {
        throwIfNull(objects);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(objects, output);
            return;
        }
        io().asJson(objects, output);
    }

    /**
     * asPrettyJson
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(Object[] objects, Writer output) throws JsonException {
        asJson(objects, output, PRETTY);
    }
    
    /**
     * asJson 
     * @param objects
     * @param arrayType
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> arrayType, Writer output) throws JsonException {
        asJson(objects, arrayType, output, UGLY);
    }

    /**
     * asJson 
     * @param objects
     * @param arrayType
     * @param output
     * @param <T> type of instance
     * @param pretty
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> arrayType, Writer output, boolean pretty) throws JsonException {
        throwIfNull(objects);
        throwIfNull(arrayType);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(objects, arrayType, output);
            return;
        }
        io().asJson(objects, arrayType, output);
    }

    /**
     * asPrettyJson 
     * @param objects
     * @param arrayType
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asPrettyJson(Object[] objects, Class<T> arrayType, Writer output) throws JsonException {
        asJson(objects, arrayType, output, PRETTY);
    }
    
    /**
     * asJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object object, File output) throws JsonException {
        asJson(object, output, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object object, File output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, output);
            return;
        }
        io().asJson(object, output);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(Object object, File output) throws JsonException {
        asJson(object, output, PRETTY);
    }

    /**
     * asJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, File output) throws JsonException {
        asJson(object, objectType, output, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @param pretty
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, File output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(objectType);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, objectType, output);
            return;
        }
        io().asJson(object, objectType, output);
    }

    /**
     * asJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asPrettyJson(Object object, Class<T> objectType, File output) throws JsonException {
        asJson(object, objectType, output, PRETTY);
    }

    /**
     * asJson
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object[] objects, File output) throws JsonException {
        asJson(objects, output, UGLY);
    }

    /**
     * asJson
     * @param objects
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(Object[] objects, File output, boolean pretty) throws JsonException {
        throwIfNull(objects);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(objects, output);
            return;
        }
        io().asJson(objects, output);
    }

    /**
     * asPrettyJson
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(Object[] objects, File output) throws JsonException {
        asJson(objects, output, PRETTY);
    }

    /**
     * asJson
     * @param objects
     * @param arrayType
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> arrayType, File output) throws JsonException {
        asJson(objects, arrayType, output, UGLY);
    }

    /**
     * asJson
     * @param objects
     * @param arrayType
     * @param output
     * @param <T> type of instance
     * @param pretty      
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> arrayType, File output, boolean pretty) throws JsonException {
        throwIfNull(objects);
        throwIfNull(arrayType);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(objects, arrayType, output);
            return;
        }
        io().asJson(objects, arrayType, output);
    }

    /**
     * asPrettyJson
     * @param objects
     * @param arrayType
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asPrettyJson(Object[] objects, Class<T> arrayType, File output) throws JsonException {
        asJson(objects, arrayType, output, PRETTY);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object object, OutputStream output) throws JsonException {
        asJson(object, output, UGLY);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @param pretty  
     * @throws JsonException
     */
    public static void asJson(Object object, OutputStream output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, output);
            return;
        }
        io().asJson(object, output);
    }

    /**
     * asPrettyJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(Object object, OutputStream output) throws JsonException {
        asJson(object, output, PRETTY);
    }

    /**
     * asJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, OutputStream output) throws JsonException {
        asJson(object, objectType, output, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @param pretty     
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, OutputStream output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(objectType);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, objectType, output);
            return;
        }
        io().asJson(object, objectType, output);
    }

    /**
     * asPrettyJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asPrettyJson(Object object, Class<T> objectType, OutputStream output) throws JsonException {
        asJson(object, objectType, output, PRETTY);
    }

    /**
     * asJson
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object[] objects, OutputStream output) throws JsonException {
        asJson(objects, output, UGLY);
    }

    /**
     * asJson
     * @param objects
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(Object[] objects, OutputStream output, boolean pretty) throws JsonException {
        throwIfNull(objects);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(objects, output);
            return;
        }
        io().asJson(objects, output);
    }

    /**
     * asPrettyJson
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(Object[] objects, OutputStream output) throws JsonException {
        asJson(objects, output, PRETTY);
    }

    /**
     * asJson
     * @param objects
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> objectType, OutputStream output) throws JsonException {
        asJson(objects, objectType, output, UGLY);
    }

    /**
     * asJson
     * @param objects
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @param pretty     
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> objectType, OutputStream output, boolean pretty) throws JsonException {
        throwIfNull(objects);
        throwIfNull(objectType);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(objects, objectType, output);
            return;
        }
        io().asJson(objects, objectType, output);
    }

    /**
     * asPrettyJson
     * @param objects
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asPrettyJson(Object[] objects, Class<T> objectType, OutputStream output) throws JsonException {
        asJson(objects, objectType, output, PRETTY);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object object, Path output) throws JsonException {
        asJson(object, output, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @param pretty 
     * @throws JsonException
     */
    public static void asJson(Object object, Path output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, output);
            return;
        }
        io().asJson(object, output);
    }

    /**
     * asPrettyJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(Object object, Path output) throws JsonException {
        asJson(object, output, PRETTY);
    }

    /**
     * asJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, Path output) throws JsonException {
        asJson(object, objectType, output, PRETTY);
    }

    /**
     * asJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, Path output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(objectType);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, objectType, output);
            return;
        }
        io().asJson(object, objectType, output);
    }

    /**
     * asPrettyJson
     * @param object
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asPrettyJson(Object object, Class<T> objectType, Path output) throws JsonException {
        asJson(object, objectType, output, UGLY);
    }

    /**
     * asJson 
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object[] objects, Path output) throws JsonException {
        asJson(objects, output, UGLY);
    }

    /**
     * asJson 
     * @param objects
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(Object[] objects, Path output, boolean pretty) throws JsonException {
        throwIfNull(objects);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(objects, output);
            return;
        }
        io().asJson(objects, output);
    }

    /**
     * asPrettyJson 
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(Object[] objects, Path output) throws JsonException {
        asJson(objects, output, PRETTY);
    }

    /**
     * asJson
     * @param objects
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> objectType, Path output) throws JsonException {
        asJson(objects, objectType, output, UGLY);
    }

    /**
     * asJson
     * @param objects
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @param pretty
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> objectType, Path output, boolean pretty) throws JsonException {
        throwIfNull(objects);
        throwIfNull(objectType);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(objects, objectType, output);
            return;
        }
        io().asJson(objects, objectType, output);
    }

    /**
     * asPrettyJson
     * @param objects
     * @param objectType class of instance
     * @param output
     * @param <T> type of instance
     * @throws JsonException
     */
    public static <T> void asPrettyJson(Object[] objects, Class<T> objectType, Path output) throws JsonException {
        asJson(objects, objectType, output, PRETTY);
    }

    /**
     * asJson
     * @param element
     * @return
     * @throws JsonException
     */
    public static String asJson(JsonElement element) throws JsonException {
        return asJson(element, UGLY);
    }

    /**
     * asJson
     * @param element
     * @param pretty 
     * @return
     * @throws JsonException
     */
    public static String asJson(JsonElement element, boolean pretty) throws JsonException {
        throwIfNull(element);
        if(pretty) {
            return prettyIO().asJson(element);
        }
        return io().asJson(element);
    }

    /**
     * asPrettyJson
     * @param element
     * @return
     * @throws JsonException
     */
    public static String asPrettyJson(JsonElement element) throws JsonException {
        return asJson(element, PRETTY);
    }
    
    /**
     * asJson
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, Writer output) throws JsonException {
        asJson(element, output, UGLY);
    }

    /**
     * asJson
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, Writer output, boolean pretty) throws JsonException {
        throwIfNull(element);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(element, output);
            return;
        }
        io().asJson(element, output);
    }

    /**
     * asPrettyJson
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonElement element, Writer output) throws JsonException {
        asJson(element, output, PRETTY);
    }

    /**
     * asJson 
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, File output) throws JsonException {
        asJson(element, output, UGLY);
    }

    /**
     * asJson 
     * @param element
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(JsonElement element, File output, boolean pretty) throws JsonException {
        throwIfNull(element);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(element, output);
            return;
        }
        io().asJson(element, output);
    }

    /**
     * asPrettyJson 
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonElement element, File output) throws JsonException {
        asJson(element, output, PRETTY);
    }
    
    /**
     * asJson 
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, OutputStream output) throws JsonException {
        asJson(element, output, UGLY);
    }

    /**
     * asJson 
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, OutputStream output, boolean pretty) throws JsonException {
        throwIfNull(element);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(element, output);
            return;
        }
        io().asJson(element, output);
    }

    /**
     * asPrettyJson 
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonElement element, OutputStream output) throws JsonException {
        asJson(element, output, PRETTY);
    }

    /**
     * asJson
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, Path output) throws JsonException {
        asJson(element, output, UGLY);
    }

    /**
     * asJson
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, Path output, boolean pretty) throws JsonException {
        throwIfNull(element);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(element, output);
            return;
        }
        io().asJson(element, output);
    }

    /**
     * asPrettyJson
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonElement element, Path output) throws JsonException {
        asJson(element, output, PRETTY);
    }

    /**
     * asJson
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asJson(JsonObject object) throws JsonException {
        return asJson(object, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param pretty
     * @return
     * @throws JsonException
     */
    public static String asJson(JsonObject object, boolean pretty) throws JsonException {
        throwIfNull(object);
        if(pretty) {
            return prettyIO().asJson(object);
        }
        return io().asJson(object);
    }

    /**
     * asPrettyJson
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asPrettyJson(JsonObject object) throws JsonException {
        return asJson(object, PRETTY);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonObject object, Writer output) throws JsonException {
        asJson(object, output, UGLY);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(JsonObject object, Writer output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, output);
            return;
        }
        io().asJson(object, output);
    }

    /**
     * asPrettyJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonObject object, Writer output) throws JsonException {
        asJson(object, output, PRETTY);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonObject object, File output) throws JsonException {
        asJson(object, output, UGLY);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(JsonObject object, File output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, output);
            return;
        }
        io().asJson(object, output);
    }

    /**
     * asPrettyJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonObject object, File output) throws JsonException {
        asJson(object, output, PRETTY);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonObject object, OutputStream output) throws JsonException {
        asJson(object, output, UGLY);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(JsonObject object, OutputStream output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, output);
            return;
        }
        io().asJson(object, output);
    }

    /**
     * asPrettyJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonObject object, OutputStream output) throws JsonException {
        asJson(object, output, PRETTY);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonObject object, Path output) throws JsonException {
        asJson(object, output, UGLY);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(JsonObject object, Path output, boolean pretty) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(object, output);
            return;
        }
        io().asJson(object, output);
    }

    /**
     * asPrettyJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonObject object, Path output) throws JsonException {
        asJson(object, output, PRETTY);
    }

    /**
     * asJson
     * @param array
     * @return
     * @throws JsonException
     */
    public static String asJson(JsonArray array) throws JsonException {
        return asJson(array, UGLY);
    }

    /**
     * asJson
     * @param array
     * @param pretty 
     * @return
     * @throws JsonException
     */
    public static String asJson(JsonArray array, boolean pretty) throws JsonException {
        throwIfNull(array);
        if(pretty) {
            return prettyIO().asJson(array);
        }
        return io().asJson(array);
    }

    /**
     * asPrettyJson
     * @param array
     * @return
     * @throws JsonException
     */
    public static String asPrettyJson(JsonArray array) throws JsonException {
        return asJson(array, PRETTY);
    }

    /**
     * asJson 
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonArray array, Writer output) throws JsonException {
        asJson(array, output, UGLY);
    }

    /**
     * asJson 
     * @param array
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(JsonArray array, Writer output, boolean pretty) throws JsonException {
        throwIfNull(array);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(array, output);
            return;
        }
        io().asJson(array, output);
    }

    /**
     * asPrettyJson 
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonArray array, Writer output) throws JsonException {
        asJson(array, output, PRETTY);
    }

    /**
     * asJson 
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonArray array, File output) throws JsonException {
        asJson(array, output, UGLY);
    }

    /**
     * asJson 
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonArray array, File output, boolean pretty) throws JsonException {
        throwIfNull(array);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(array, output);
            return;
        }
        io().asJson(array, output);
    }

    /**
     * asPrettyJson 
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonArray array, File output) throws JsonException {
        asJson(array, output, PRETTY);
    }

    /**
     * asJson 
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonArray array, OutputStream output) throws JsonException {
        asJson(array, output, UGLY);
    }

    /**
     * asJson 
     * @param array
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(JsonArray array, OutputStream output, boolean pretty) throws JsonException {
        throwIfNull(array);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(array, output);
            return;
        }
        io().asJson(array, output);
    }

    /**
     * asPrettyJson
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonArray array, OutputStream output) throws JsonException {
        asJson(array, output, PRETTY);
    }

    /**
     * asJson
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonArray array, Path output) throws JsonException {
        asJson(array, output, UGLY);
    }

    /**
     * asJson
     * @param array
     * @param output
     * @param pretty
     * @throws JsonException
     */
    public static void asJson(JsonArray array, Path output, boolean pretty) throws JsonException {
        throwIfNull(array);
        throwIfNull(output);
        if(pretty) {
            prettyIO().asJson(array, output);
            return;
        }
        io().asJson(array, output);
    }

    /**
     * asJson
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asPrettyJson(JsonArray array, Path output) throws JsonException {
        asJson(array, output, PRETTY);
    }

    /**
     * Internal class method that will throw an IllegalArgumentException if input
     * argument is null.
     * @param input JSON input
     */
    private static void throwIfNull(Object input) {
        if(input == null) {
            throw new IllegalArgumentException("input argument cannot be null.");
        }
    }

    /**
     * Internal class method that will throw an IllegalArgumentException if json is jacked up
     * @param json
     */
    private static void throwIfBad(String json) {
        if(json == null) {
            throw new IllegalArgumentException("Json argument cannot be null.");
        }

        if(json.trim().length() == 0) {
            throw new IllegalArgumentException("Json argument cannot be an empty-string.");
        }
    }

    /**
     * Internal class method that will throw an IllegalArgumentException if json is not an array 
     * @param json a Json array
     */
    private static void throwIfNotArray(String json) {
        throwIfBad(json);
        if(json.trim().charAt(0) != '[') {
            throw new IllegalArgumentException("Json argument is not an array.");
        }
    }

    /**
     * Internal class method that will throw an IllegalArgumentException if json is not an object 
     * @param json a Json object
     */
    private static void throwIfNotObject(String json) {
        throwIfBad(json);
        if(json.trim().charAt(0) != '{') {
            throw new IllegalArgumentException("Json argument is not an object.");
        }
    }
}
