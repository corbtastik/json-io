package cworks.json.impl.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import cworks.json.IO;
import cworks.json.JsonArray;
import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonObject;
import cworks.json.parser.jackson.ParserDelegate;
import cworks.json.spi.JsonReader;
import cworks.json.streaming.StreamHandler;
import cworks.json.streaming.Token;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JacksonReader implements JsonReader {

    /**
     * Heart of the Jackson processor 
     */
    private final ObjectMapper mapper;

    /**
     * Create this JsonReader with the powerful Jackson ObjectMapper
     * @param mapper
     */
    public JacksonReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonElement asElement(String input) throws JsonException {

        JsonElement element = null;

        try {
            if (input.trim().startsWith("[")) {
                JsonArray list = asObject(input, JsonArray.class);
                element = list;
            } else if (input.trim().startsWith("{")) {
                JsonObject map = asObject(input, JsonObject.class);
                element = map;
            }
        } catch(Exception ex) {
            throw new JsonException(ex);
        }

        if(element == null) {
            throw new JsonException("json argument isn't valid Json");
        }

        return element;
    }

    /**
     * Tested 
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonObject asObject(String input) throws JsonException {
        
        if(!input.trim().startsWith("{")) {
            throw new JsonException("Json input isn't valid json object.");
        }
        
        JsonElement element = asElement(input);
        if(element.isObject()) {
            return element.asObject();
        }
        
        return null;
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonArray asArray(String input) throws JsonException {
        
        if(!input.trim().startsWith("[")) {
            throw new JsonException("Json input isn't valid json array.");
        }

        JsonElement element = asElement(input);
        if(element.isArray()) {
            return element.asArray();
        }

        return null;
    }

    /**
     * Tested
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    @Override
    public <T> T asObject(String input, Class<T> objectType) throws JsonException {
        T object;
        try {
            object = mapper.readValue(wrapper(input), objectType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    /**
     * Tested
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    @Override
    public <T> T[] asArray(String input, Class<T> arrayType) throws JsonException {
        T[] array;

        try {
            ArrayType arrType = mapper.getTypeFactory().constructArrayType(arrayType);
            array = mapper.readValue(wrapper(input), arrType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return array;
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonElement asElement(StringBuffer input) throws JsonException {
        return asElement(input.toString());
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonObject asObject(StringBuffer input) throws JsonException {
        return asObject(input.toString());
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonArray asArray(StringBuffer input) throws JsonException {
        return asArray(input.toString());
    }

    /**
     * Tested
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    @Override
    public <T> T asObject(StringBuffer input, Class<T> objectType) throws JsonException {
        return asObject(input.toString(), objectType);
    }

    /**
     * Tested
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    @Override
    public <T> T[] asArray(StringBuffer input, Class<T> arrayType) throws JsonException {
        return asArray(input.toString(), arrayType);
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonElement asElement(StringBuilder input) throws JsonException {
        return asElement(input.toString());
    }

    @Override
    public JsonObject asObject(StringBuilder input) throws JsonException {
        return asObject(input.toString());
    }

    @Override
    public JsonArray asArray(StringBuilder input) throws JsonException {
        return asArray(input.toString());
    }
    
    @Override
    public <T> T asObject(StringBuilder input, Class<T> objectType) throws JsonException {
        return asObject(input.toString(), objectType);
    }
    
    @Override
    public <T> T[] asArray(StringBuilder input, Class<T> arrayType) throws JsonException {
        return asArray(input.toString(), arrayType);
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonElement asElement(final Reader input) throws JsonException {
        JsonElement element;
        
        try {
            String json = IO.asString(input);
            element = asElement(json);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            IO.closeQuietly(input);
        }

        return element;
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonObject asObject(final Reader input) throws JsonException {
        JsonObject object;

        try {
            String json = IO.asString(input);
            object = asObject(json);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            IO.closeQuietly(input);
        }

        return object;
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonArray asArray(final Reader input) throws JsonException {
        JsonArray array;
        
        try {
            String json = IO.asString(input);
            array = asArray(json);
        } catch(IOException ex) {
            throw new JsonException(ex);
        } finally {
            IO.closeQuietly(input);
        }
        
        return array;
    }

    /**
     * Tested
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    @Override
    public <T> T asObject(final Reader input, Class<T> objectType) throws JsonException {
        T object;
        
        try {
            String json = IO.asString(input);
            object = asObject(json, objectType);
        } catch(IOException ex) {
            throw new JsonException(ex);
        } finally {
            IO.closeQuietly(input);
        }
        
        return object;
    }

    /**
     * Tested
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    @Override
    public <T> T[] asArray(Reader input, Class<T> arrayType) throws JsonException {
        T[] array;
        
        try {
            String json = IO.asString(input);
            array = asArray(json, arrayType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            IO.closeQuietly(input);
        }

        return array;
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonElement asElement(File input) throws JsonException {
        JsonElement element;
        
        try {
            String json = IO.asString(input);
            element = asElement(json);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
        
        return element;
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonObject asObject(File input) throws JsonException {
        JsonObject object;

        try {
            String json = IO.asString(input);
            object = asObject(json);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonArray asArray(File input) throws JsonException {
        JsonArray array;

        try {
            String json = IO.asString(input);
            array = asArray(json);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return array;
    }

    /**
     * Tested
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    @Override
    public <T> T asObject(File input, Class<T> objectType) throws JsonException {
        T object;
        try {
            String json = IO.asString(input);
            object = asObject(json, objectType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    /**
     * Tested
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    @Override
    public <T> T[] asArray(File input, Class<T> arrayType) throws JsonException {
        T[] array;

        try {
            String json = IO.asString(input);
            array = asArray(json, arrayType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return array;
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonElement asElement(InputStream input) throws JsonException {
        JsonElement element;
        
        try {
            String json = IO.asString(input);
            element = asElement(json);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return element;
    }

    
    @Override
    public JsonObject asObject(InputStream input) throws JsonException {
        JsonObject object;

        try {
            String json = IO.asString(input);
            object = asObject(json);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    @Override
    public JsonArray asArray(InputStream input) throws JsonException {
        JsonArray array;

        try {
            String json = IO.asString(input);
            array = asArray(json);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return array;
    }

    /**
     * Tested 
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    @Override
    public <T> T asObject(InputStream input, Class<T> objectType) throws JsonException {
        T object;

        try {
            String json = IO.asString(input);
            object = asObject(json, objectType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    @Override
    public <T> T[] asArray(InputStream input, Class<T> arrayType) throws JsonException {
        T[] array;

        try {
            String json = IO.asString(input);
            array = asArray(json, arrayType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return array;
    }

    /**
     * Tested
     * @param input
     * @return
     * @throws JsonException
     */
    @Override
    public JsonElement asElement(Path input) throws JsonException {
        JsonElement element;

        try {
            Reader reader = Files.newBufferedReader(input);
            element = asElement(reader);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return element;
    }

    @Override
    public JsonObject asObject(Path input) throws JsonException {
        JsonObject object;

        try {
            Reader reader = Files.newBufferedReader(input);
            object = asObject(reader);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    @Override
    public JsonArray asArray(Path input) throws JsonException {
        JsonArray array;

        try {
            Reader reader = Files.newBufferedReader(input);
            array = asArray(reader);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return array;
    }

    @Override
    public <T> T asObject(Path input, Class<T> objectType) throws JsonException {
        T object;

        try {
            Reader reader = Files.newBufferedReader(input);
            object = asObject(reader, objectType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    @Override
    public <T> T[] asArray(Path input, Class<T> arrayType) throws JsonException {
        T[] array;

        try {
            Reader reader = Files.newBufferedReader(input);
            array = asArray(reader, arrayType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return array;
    }

    @Override
    public List<JsonObject> asList(String input) throws JsonException {
        return asList(input, JsonObject.class);
    }

    @Override
    public List<JsonObject> asList(StringBuffer input) throws JsonException {
        return asList(input.toString(), JsonObject.class);
    }

    @Override
    public List<JsonObject> asList(StringBuilder input) throws JsonException {
        return asList(input.toString(), JsonObject.class);
    }

    @Override
    public List<JsonObject> asList(File input) throws JsonException {
        return asList(input, JsonObject.class);
    }

    @Override
    public List<JsonObject> asList(Path input) throws JsonException {
        return asList(input, JsonObject.class);
    }

    @Override
    public List<JsonObject> asList(Reader input) throws JsonException {
        return asList(input, JsonObject.class);
    }

    @Override
    public List<JsonObject> asList(InputStream input) throws JsonException {
        return asList(input, JsonObject.class);
    }

    @Override
    public <T> List<T> asList(String input, Class<T> listType) throws JsonException {
        List<T> list;

        try {
            CollectionType collectionType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, listType);
            list = mapper.readValue(wrapper(input), collectionType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return list;
    }

    @Override
    public <T> List<T> asList(StringBuffer input, Class<T> listType) throws JsonException {
        return asList(input.toString(), listType);
    }

    @Override
    public <T> List<T> asList(StringBuilder input, Class<T> listType) throws JsonException {
        return asList(input.toString(), listType);
    }

    @Override
    public <T> List<T> asList(File input, Class<T> listType) throws JsonException {
        List<T> list;

        try {
            String json = IO.asString(input);
            list = asList(json, listType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return list;
    }

    @Override
    public <T> List<T> asList(Path input, Class<T> listType) throws JsonException {
        List<T> list;

        try {
            String json = IO.asString(input);
            list = asList(json, listType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return list;
    }

    @Override
    public <T> List<T> asList(Reader input, Class<T> listType) throws JsonException {
        List<T> list;

        try {
            String json = IO.asString(input);
            list = asList(json, listType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return list;
    }

    @Override
    public <T> List<T> asList(InputStream input, Class<T> listType) throws JsonException {
        List<T> list;

        try {
            String json = IO.asString(input);
            list = asList(json, listType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return list;
    }

    @Override
    public Map<String, Object> asMap(String input) throws JsonException {
        Map map = asMap(input, Object.class);
        return map;
    }

    @Override
    public Map<String, Object> asMap(StringBuffer input) throws JsonException {
        Map map = asMap(input.toString(), Object.class);
        return map;
    }

    @Override
    public Map<String, Object> asMap(StringBuilder input) throws JsonException {
        Map map = asMap(input.toString(), Object.class);
        return map;
    }

    @Override
    public Map<String, Object> asMap(File input) throws JsonException {
        Map map;
        
        try {
            String json = IO.asString(input);
            map = asMap(json, Object.class);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return map;
    }

    @Override
    public Map<String, Object> asMap(Path input) throws JsonException {
        Map map;
        
        try {
            String json = IO.asString(input);
            map = asMap(json, Object.class);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return map;
    }

    @Override
    public Map<String, Object> asMap(Reader input) throws JsonException {
        Map map;
        
        try {
            String json = IO.asString(input);
            map = asMap(json, Object.class);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return map;
    }

    @Override
    public Map<String, Object> asMap(InputStream input) throws JsonException {
        Map map;
        
        try {
            String json = IO.asString(input);
            map = asMap(json, Object.class);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return map;
    }

    @Override
    public <T> Map<String, T> asMap(String input, Class<T> valueType) throws JsonException {
        Map<String, T> map;
        
        try {
            MapType mt = mapper.getTypeFactory().constructMapType(Map.class, String.class, valueType);
            map = mapper.readValue(wrapper(input), mt);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return map;
    }

    @Override
    public <T> Map<String, T> asMap(StringBuffer input, Class<T> valueType) throws JsonException {
        return asMap(input.toString(), valueType);
    }

    @Override
    public <T> Map<String, T> asMap(StringBuilder input, Class<T> valueType) throws JsonException {
        return asMap(input.toString(), valueType);
    }

    @Override
    public <T> Map<String, T> asMap(File input, Class<T> valueType) throws JsonException {
        Map map;
        
        try {
            String json = IO.asString(input);
            map = asMap(json, valueType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
        
        return map;
    }

    @Override
    public <T> Map<String, T> asMap(Path input, Class<T> valueType) throws JsonException {
        Map map;

        try {
            String json = IO.asString(input);
            map = asMap(json, valueType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return map;
    }

    @Override
    public <T> Map<String, T> asMap(Reader input, Class<T> valueType) throws JsonException {
        Map map;

        try {
            String json = IO.asString(input);
            map = asMap(json, valueType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return map;
    }

    @Override
    public <T> Map<String, T> asMap(InputStream input, Class<T> valueType) throws JsonException {
        Map map;

        try {
            String json = IO.asString(input);
            map = asMap(json, valueType);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return map;
    }

    @Override
    public void asStream(final InputStream input, StreamHandler<Token> handler) throws JsonException {

        try {
            Iterator<Token> iterator = new JacksonIterator(wrapper(input));
            while(iterator.hasNext()) {
                handler.handle(iterator.next());
            }

        } catch(IOException ex) {
            throw new JsonException(ex);
        }
    }

    @Override
    public Stream<Token> asStream(final InputStream input) {

        Stream stream;
        
        try {
            Iterator<Token> iterator = new JacksonIterator(wrapper(input));
            stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                    iterator, Spliterator.ORDERED | Spliterator.NONNULL), false);
            
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
        
        return stream;
    }

    @Override
    public void asStream(final Reader input, StreamHandler<Token> handler) throws JsonException {
        
        try {
            Iterator<Token> iterator = new JacksonIterator(wrapper(input));
            while(iterator.hasNext()) {
                handler.handle(iterator.next());
            }

        } catch(IOException ex) {
            throw new JsonException(ex);
        }
        
    }

    @Override
    public Stream<Token> asStream(final Reader input) throws JsonException {
        Stream stream;

        try {
            Iterator<Token> iterator = new JacksonIterator(wrapper(input));
            stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                    iterator, Spliterator.ORDERED | Spliterator.NONNULL), false);

        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return stream;
        
    }

    @Override
    public void asStream(final File input, final StreamHandler<Token> handler) throws JsonException {

        try {
            Reader reader = Files.newBufferedReader(input.toPath());
            Iterator<Token> iterator = new JacksonIterator(wrapper(reader));
            while(iterator.hasNext()) {
                handler.handle(iterator.next());
            }

        } catch(IOException ex) {
            throw new JsonException(ex);
        }        
        
    }

    @Override
    public Stream<Token> asStream(final File input) throws JsonException {
        Stream stream;

        try {
            Reader reader = Files.newBufferedReader(input.toPath());
            Iterator<Token> iterator = new JacksonIterator(wrapper(reader));
            stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                    iterator, Spliterator.ORDERED | Spliterator.NONNULL), false);

        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return stream;
    }

    @Override
    public void asStream(final Path input, final StreamHandler<Token> handler) throws JsonException {
        
        try {
            Reader reader = Files.newBufferedReader(input);
            Iterator<Token> iterator = new JacksonIterator(wrapper(reader));
            while(iterator.hasNext()) {
                handler.handle(iterator.next());
            }

        } catch(IOException ex) {
            throw new JsonException(ex);
        }
    }

    @Override
    public Stream<Token> asStream(final Path input) throws JsonException {
        Stream stream;

        try {
            Reader reader = Files.newBufferedReader(input);
            Iterator<Token> iterator = new JacksonIterator(wrapper(reader));
            stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                    iterator, Spliterator.ORDERED | Spliterator.NONNULL), false);

        } catch (IOException ex) {
            throw new JsonException(ex);
        }

        return stream;
    }

    /**
     * Create a wrapper around the Jackson parser so that we can tweak some features
     * to our liking
     * *
     * @param input json String
     * @return jackson parser
     * @throws java.io.IOException
     */
    private com.fasterxml.jackson.core.JsonParser wrapper(String input) throws IOException {
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(input);

        return new ParserDelegate(parser);
    }

    /**
     * Create a wrapper around the Jackson parser so that we can tweak some features
     * to our liking
     * *
     * @param input json InputStream
     * @return jackson parser
     * @throws java.io.IOException
     */
    private com.fasterxml.jackson.core.JsonParser wrapper(InputStream input) throws IOException {
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(input);

        return new ParserDelegate(parser);
    }

    /**
     * Create a wrapper around the Jackson parser so that we can tweak some features
     * to our liking
     * *
     * @param input json Reader
     * @return jackson parser
     * @throws java.io.IOException
     */
    private com.fasterxml.jackson.core.JsonParser wrapper(Reader input) throws IOException {
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(input);

        return new ParserDelegate(parser);
    }
}
