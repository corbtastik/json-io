package cworks.json.impl.jackson;

import cworks.json.IO;
import cworks.json.Json;
import cworks.json.JsonArray;
import cworks.json.JsonContext;
import cworks.json.JsonElement;
import cworks.json.JsonObject;
import cworks.json.JsonTestCore;
import cworks.json.JsonTestData;
import cworks.json.TestUser;
import cworks.json.spi.JsonReader;
import cworks.json.spi.JsonWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class JacksonWriterTest extends JsonTestCore {

    private static final JsonContext PRETTY_CONTEXT = new JsonContext() {
        @Override
        public boolean isPretty() {
            return true;
        }

        @Override
        public boolean allowComments() {
            return true;
        }

        @Override
        public String dateFormat() {
            return "yyyy-MM-dd'T'HH:mm:ssz";
        }
    };

    private static final JsonContext UGLY_CONTEXT = new JsonContext() {
        @Override
        public boolean isPretty() {
            return false;
        }

        @Override
        public boolean allowComments() {
            return true;
        }

        @Override
        public String dateFormat() {
            return "yyyy-MM-dd'T'HH:mm:ssz";
        }
    };

    @Test
    public void testAsJson_Pretty() throws IOException {
        JsonWriter writer = new JacksonIO(PRETTY_CONTEXT);
        JsonObject smallUser = Json.asObject(IO.asString(
                new File("src/test/resources/small_object.json")));
        writer.asJson(smallUser, new File("build/tmp/testAsJson_Pretty.json"));
        Assert.assertEquals(8, IO.countLines(new File("build/tmp/testAsJson_Pretty.json")));
    }

    @Test
    public void testAsJson_Ugly() throws IOException {
        JsonWriter writer = new JacksonIO(UGLY_CONTEXT);
        JsonObject smallUser = Json.asObject(IO.asString(
                new File("src/test/resources/small_object.json")));
        writer.asJson(smallUser, new File("build/tmp/testAsJson_Ugly.json"));
        Assert.assertEquals(1, IO.countLines(new File("build/tmp/testAsJson_Ugly.json")));
    }

    @Test
    public void testAsJson_Object() {
        JsonWriter writer = new JacksonIO(UGLY_CONTEXT);
        TestUser user1 = Json.asObject(JsonTestData.USER, TestUser.class);
        String json = writer.asJson(user1);
        TestUser user2 = Json.asObject(json, TestUser.class);
        Assert.assertEquals(user1, user2);
    }
    
    @Test
    public void testAsJson_ObjectArray() {
        JsonWriter writer = new JacksonIO(UGLY_CONTEXT);
        TestUser[] users1 = Json.asArray(JsonTestData.USERS_ARRAY, TestUser.class);
        String json = writer.asJson(users1);
        TestUser[] users2 = Json.asArray(json, TestUser.class);
        Assert.assertArrayEquals(users1, users2);
    }

    @Test
    public void testAsJson_Object_Class() {
        JsonWriter writer = new JacksonIO(UGLY_CONTEXT);
        TestUser user1 = Json.asObject(JsonTestData.USER, TestUser.class);
        String json = writer.asJson(user1, TestUser.class);
        TestUser user2 = Json.asObject(json, TestUser.class);
        Assert.assertEquals(user1, user2);
    }
    
    @Test
    public void testAsJson_ObjectArray_Class() {
        JsonWriter writer = new JacksonIO(UGLY_CONTEXT);
        Integer[] numbers1 = new Integer[]{0,1,2,3,4,5,6,7,8,9};
        String json = writer.asJson(numbers1, Integer.class);
        Integer[] numbers2 = Json.asArray(json, Integer.class);
        Assert.assertArrayEquals(numbers1, numbers2);
    }

    @Test
    public void testAsJson_List() {
        JsonWriter writer = new JacksonIO(UGLY_CONTEXT);
        List<Integer> numbers1 = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        String json = writer.asJson(numbers1);
        List<Integer> numbers2 = Json.asList(json, Integer.class);
        Assert.assertEquals(numbers1, numbers2);
    }

    @Test
    public void testAsJson_JsonElement() throws IOException {
        String json1 = IO.asString(new File("src/test/resources/large_object.json"));
        JsonReader reader = new JacksonIO().getReader();
        JsonElement element = reader.asElement(json1);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());

        JsonWriter writer = new JacksonIO().getWriter();
        String json2 = writer.asJson(element);

        Assert.assertEquals(Json.asObject(json1), Json.asObject(json2));
    }

    @Test
    public void testAsJson_JsonObject_Writer() throws IOException {
        JsonObject user = Json.asObject(JsonTestData.USER);
        JsonWriter writer = new JacksonIO().getWriter();
        Writer fileWriter = IO.asWriter("build/tmp/testAsJson_JsonObject_Writer.json");
        writer.asJson(user, fileWriter);
    }

    @Test
    public void testAsJson_JsonObject_OutputStream() throws IOException {
        JsonObject user = Json.asObject(JsonTestData.USER);
        JsonWriter writer = new JacksonIO().getWriter();
        OutputStream fileStream = IO.asOutputStream(
            "build/tmp/testAsJson_JsonObject_OutputStream.json");
        writer.asJson(user, fileStream);
    }

    @Test
    public void testAsJson_JsonObject_File() throws IOException {
        JsonObject user1 = Json.asObject(JsonTestData.USER);
        JsonWriter writer = new JacksonIO().getWriter();
        writer.asJson(user1, new File("build/tmp/testAsJson_JsonObject_File.json"));
        String json = IO.asString(new File("build/tmp/testAsJson_JsonObject_File.json"));
        JsonObject user2 = Json.asObject(json);
        Assert.assertEquals(user1, user2);
    }

    @Test
    public void testAsJson_JsonArray_Writer() throws IOException {
        JsonWriter writer = new JacksonIO().getWriter();
        Writer fileWriter = IO.asWriter("build/tmp/testAsJson_JsonArray_Writer.json");
        JsonArray array1 = Json.asArray(JsonTestData.USERS_ARRAY);
        writer.asJson(array1, fileWriter);
        JsonArray array2 = Json.asArray(IO.asString(
            new File("build/tmp/testAsJson_JsonArray_Writer.json")));
        Assert.assertEquals(array1, array2);
    }
    
    @Test
    public void testAsJson_JsonArray_File() throws IOException {
        JsonWriter writer = new JacksonIO().getWriter();
        JsonArray array1 = Json.asArray(IO.asString(
            new File("src/test/resources/number_array.json")));
        writer.asJson(array1, new File("build/tmp/testAsJson_JsonArray_File.json"));
        
        JsonArray array2 = Json.asArray(IO.asString(
            new File("build/tmp/testAsJson_JsonArray_File.json")));
        Assert.assertEquals(array1, array2);
    }
}