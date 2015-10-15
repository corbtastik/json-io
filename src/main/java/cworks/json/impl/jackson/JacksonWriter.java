package cworks.json.impl.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import cworks.json.IO;
import cworks.json.JsonArray;
import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonObject;
import cworks.json.spi.JsonWriter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static cworks.json.IO.closeQuietly;

public class JacksonWriter implements JsonWriter {
    
    private final ObjectMapper mapper;
    
    public JacksonWriter(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    
    @Override
    public String asJson(Object object) throws JsonException {
        String json;
        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public <T> String asJson(Object object, Class<T> objectType) throws JsonException {
        String json;
        try {
            json = mapper.writerWithType(objectType).writeValueAsString(object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public String asJson(Object[] objects) throws JsonException {
        String json;
        try {
            json = mapper.writeValueAsString(objects);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public <T> String asJson(Object[] objects, Class<T> arrayType) throws JsonException {
        String json;
        try {
            json = mapper.writerWithType(
                mapper.getTypeFactory().constructArrayType(arrayType))
                    .writeValueAsString(objects);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public String asJson(List object) throws JsonException {
        String json;
        try {
            json = mapper.writerWithType(List.class).writeValueAsString(object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public <T> String asJson(List objects, Class<T> listType) throws JsonException {
        String json;
        try {
            json = mapper.writerWithType(
                mapper.getTypeFactory()
                    .constructCollectionType(List.class, listType))
                    .writeValueAsString(objects);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
        return json;
    }

    @Override
    public String asJson(Map object) throws JsonException {
        String json;
        try {
            json = mapper.writerWithType(Map.class).writeValueAsString(object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public <T> String asJson(Map object, Class<T> mapType) throws JsonException {
        String json;
        try {
            json = mapper.writerWithType(
                mapper.getTypeFactory().constructMapType(Map.class, String.class, mapType))
                    .writeValueAsString(object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
        return json;
    }

    @Override
    public void asJson(Object object, Writer output) throws JsonException {
        try {
            mapper.writeValue(output, object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, Writer output) throws JsonException {
        try {
            mapper.writerWithType(objectType).writeValue(output, object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public void asJson(Object[] objects, Writer output) throws JsonException {
        try {
            mapper.writeValue(output, objects);
        } catch (Exception ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> arrayType, Writer output) throws JsonException {
        try {
            mapper.writerWithType(
                mapper.getTypeFactory().constructArrayType(arrayType))
                    .writeValue(output, objects);
        } catch (Exception ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public void asJson(Object object, File output) throws JsonException {
        Writer writer = null;
        try {
            writer = IO.asWriter(output);
            asJson(object, writer);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(writer);
        }
    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, File output) throws JsonException {
        Writer writer = null;
        try {
            writer = IO.asWriter(output);
            asJson(object, objectType, writer);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(writer);
        }
    }

    @Override
    public void asJson(Object[] objects, File output) throws JsonException {
        Writer writer = null;
        try {
            writer = IO.asWriter(output);
            asJson(objects, writer);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(writer);
        }
    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> arrayType, File output) throws JsonException {
        Writer writer = null;
        try {
            writer = IO.asWriter(output);
            asJson(objects, arrayType, writer);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(writer);
        }
    }

    @Override
    public void asJson(Object object, OutputStream output) throws JsonException {
        try {
            mapper.writeValue(output, object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, OutputStream output) throws JsonException {
        try {
            mapper.writerWithType(objectType).writeValueAsString(object);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public void asJson(Object[] objects, OutputStream output) throws JsonException {
        try {
            mapper.writeValue(output, objects);
        } catch (Exception ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> objectType, OutputStream output) throws JsonException {
        try {
            mapper.writerWithType(
                mapper.getTypeFactory().constructArrayType(objectType))
                    .writeValue(output, objects);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public void asJson(Object object, Path output) throws JsonException {
        asJson(object, output.toFile());
    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, Path output) throws JsonException {
        asJson(object, objectType, output.toFile());
    }

    @Override
    public void asJson(Object[] objects, Path output) throws JsonException {
        asJson(objects, output.toFile());
    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> objectType, Path output) throws JsonException {
        asJson(objects, objectType, output.toFile());
    }

    @Override
    public String asJson(JsonElement element) throws JsonException {
        if(element.isObject()) {
            return asJson(element.asObject());
        } else if(element.isArray()) {
            return asJson(element.asArray());
        }

        return "";
    }

    @Override
    public void asJson(JsonElement element, Writer output) throws JsonException {
        try {
            if(element.isObject()) {
                asJson(element.asObject(), output);
            } else if(element.isArray()) {
                asJson(element.asArray(), output);
            }
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public void asJson(JsonElement element, File output) throws JsonException {
        if(element.isObject()) {
            asJson(element.asObject(), output);
        } else if(element.isArray()) {
            asJson(element.asArray(), output);
        }
    }

    @Override
    public void asJson(JsonElement element, OutputStream output) throws JsonException {
        if(element.isObject()) {
            asJson(element.asObject(), output);
        } else if(element.isArray()) {
            asJson(element.asArray(), output);
        }
    }

    @Override
    public void asJson(JsonElement element, Path output) throws JsonException {
        asJson(element, output.toFile());
    }

    @Override
    public String asJson(JsonObject object) throws JsonException {
        String json;
        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public void asJson(JsonObject object, Writer output) throws JsonException {
        try {
            mapper.writeValue(output, object);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public void asJson(JsonObject object, File output) throws JsonException {
        Writer writer = null;
        try {
            writer = IO.asWriter(output);
            asJson(object, writer);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(writer);
        }
    }

    @Override
    public void asJson(JsonObject object, OutputStream output) throws JsonException {
        try {
            mapper.writeValue(output, object);
        } catch(IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public void asJson(JsonObject object, Path output) throws JsonException {
        asJson(object, output.toFile());
    }

    @Override
    public String asJson(JsonArray array) throws JsonException {
        String json;
        try {
            json = mapper.writeValueAsString(array);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
        return json;
    }

    @Override
    public void asJson(JsonArray array, Writer output) throws JsonException {
        try {
            mapper.writeValue(output, array);
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public void asJson(JsonArray array, File output) throws JsonException {
        Writer writer = null;
        try {
            writer = IO.asWriter(output);
            asJson(array, writer);
        } catch(IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(writer);
        }
    }

    @Override
    public void asJson(JsonArray array, OutputStream output) throws JsonException {
        try {
            mapper.writeValue(output, array);
        } catch(IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public void asJson(JsonArray array, Path output) throws JsonException {
        asJson(array, output.toFile());
    }
}
