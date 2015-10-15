package cworks.json.spi;

import cworks.json.JsonArray;
import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonObject;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface JsonWriter {
    public String asJson(Object object) throws JsonException;
    public <T> String asJson(Object object, Class<T> objectType) throws JsonException;
    public String asJson(Object[] objects) throws JsonException;
    public <T> String asJson(Object[] objects, Class<T> arrayType) throws JsonException;

    public String asJson(List object) throws JsonException;
    public <T> String asJson(List objects, Class<T> listType) throws JsonException;

    public String asJson(Map object) throws JsonException;
    public <T> String asJson(Map object, Class<T> mapType) throws JsonException;

    public void asJson(Object object, Writer output) throws JsonException;
    public <T> void asJson(Object object, Class<T> objectType, Writer output) throws JsonException;
    public void asJson(Object[] objects, Writer output) throws JsonException;
    public <T> void asJson(Object[] objects, Class<T> arrayType, Writer output) throws JsonException;

    public void asJson(Object object, File output) throws JsonException;
    public <T> void asJson(Object object, Class<T> objectType, File output) throws JsonException;
    public void asJson(Object[] objects, File output) throws JsonException;
    public <T> void asJson(Object[] objects, Class<T> arrayType, File output) throws JsonException;

    public void asJson(Object object, OutputStream output) throws JsonException;
    public <T> void asJson(Object object, Class<T> objectType, OutputStream output) throws JsonException;
    public void asJson(Object[] objects, OutputStream output) throws JsonException;
    public <T> void asJson(Object[] objects, Class<T> objectType, OutputStream output) throws JsonException;

    public void asJson(Object object, Path output) throws JsonException;
    public <T> void asJson(Object object, Class<T> objectType, Path output) throws JsonException;
    public void asJson(Object[] objects, Path output) throws JsonException;
    public <T> void asJson(Object[] objects, Class<T> objectType, Path output) throws JsonException;

    public String asJson(JsonElement element) throws JsonException;
    public void asJson(JsonElement element, Writer output) throws JsonException;
    public void asJson(JsonElement element, File output) throws JsonException;
    public void asJson(JsonElement element, OutputStream output) throws JsonException;
    public void asJson(JsonElement element, Path output) throws JsonException;

    public String asJson(JsonObject object) throws JsonException;
    public void asJson(JsonObject object, Writer output) throws JsonException;
    public void asJson(JsonObject object, File output) throws JsonException;
    public void asJson(JsonObject object, OutputStream output) throws JsonException;
    public void asJson(JsonObject object, Path output) throws JsonException;

    public String asJson(JsonArray array) throws JsonException;
    public void asJson(JsonArray array, Writer output) throws JsonException;
    public void asJson(JsonArray array, File output) throws JsonException;
    public void asJson(JsonArray array, OutputStream output) throws JsonException;
    public void asJson(JsonArray array, Path output) throws JsonException;
}
