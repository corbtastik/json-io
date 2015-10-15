package cworks.json.impl.jackson;

import cworks.json.IO;
import cworks.json.JsonArray;
import cworks.json.JsonElement;
import cworks.json.JsonObject;
import cworks.json.JsonTestCore;
import cworks.json.TestUser;
import cworks.json.spi.JsonReader;
import cworks.json.streaming.Token;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JacksonReaderTest extends JsonTestCore {
    
    @Test
    public void testAsElement_String() throws IOException {
        String json = IO.asString(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(json);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }

    @Test
    public void testAsObject_String() throws IOException {
        String json = IO.asString(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonObject object = jackson.asObject(json);
        Assert.assertNotNull(object);
        assertGoodLargeObject(object);
    }

    @Test
    public void testAsArray_String() throws IOException {
        String json = IO.asString(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonArray array = jackson.asArray(json);
        Assert.assertNotNull(array);
        assertGoodSmallObjectArray(array);
    }
    
    @Test
    public void testAsObject_String_Class() throws IOException {
        
        String json = IO.asString(new File("src/test/resources/small_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        
        TestUser user = jackson.asObject(json, TestUser.class);
        Assert.assertEquals("Roy", user.getFirstName());
        Assert.assertEquals("Watkins", user.getLastName());
        Assert.assertEquals("rwatkins0@studiopress.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("10.242.205.116", user.getIpAddress());
    }
    
    @Test
    public void testAsArray_String_Class() throws IOException {

        String json = IO.asString(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();
        
        TestUser[] users = jackson.asArray(json, TestUser.class);
        Assert.assertEquals(1000, users.length);
        Assert.assertEquals(195, users[194].getId().intValue());
        Assert.assertEquals("Stephen", users[194].getFirstName());
        Assert.assertEquals("Mason", users[194].getLastName());
        Assert.assertEquals("smason5e@barnesandnoble.com", users[194].getEmail());
        Assert.assertEquals("Ukraine", users[194].getCountry());
        Assert.assertEquals("54.49.166.253", users[194].getIpAddress());
    }
    
    @Test
    public void testAsElement_StringBuffer() throws IOException {
        StringBuffer json = IO.asStringBuffer(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(json);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }
    
    @Test
    public void testAsObject_StringBuffer() throws IOException {
        StringBuffer json = IO.asStringBuffer(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonObject object = jackson.asObject(json);
        Assert.assertNotNull(object);
        assertGoodLargeObject(object);
    }
    
    @Test
    public void testAsArray_StringBuffer() throws IOException {
        StringBuffer json = IO.asStringBuffer(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonArray array = jackson.asArray(json);
        Assert.assertNotNull(array);
        assertGoodSmallObjectArray(array);
    }
    
    @Test
    public void testAsObject_StringBuffer_Class() throws IOException {
        StringBuffer json = IO.asStringBuffer(new File("src/test/resources/small_object.json"));
        JsonReader jackson = new JacksonIO().getReader();

        TestUser user = jackson.asObject(json, TestUser.class);
        Assert.assertEquals("Roy", user.getFirstName());
        Assert.assertEquals("Watkins", user.getLastName());
        Assert.assertEquals("rwatkins0@studiopress.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("10.242.205.116", user.getIpAddress());
    }
    
    @Test
    public void testAsArray_StringBuffer_Class() throws IOException {
        StringBuffer json = IO.asStringBuffer(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();

        TestUser[] users = jackson.asArray(json, TestUser.class);
        Assert.assertEquals(1000, users.length);
        Assert.assertEquals(195, users[194].getId().intValue());
        Assert.assertEquals("Stephen", users[194].getFirstName());
        Assert.assertEquals("Mason", users[194].getLastName());
        Assert.assertEquals("smason5e@barnesandnoble.com", users[194].getEmail());
        Assert.assertEquals("Ukraine", users[194].getCountry());
        Assert.assertEquals("54.49.166.253", users[194].getIpAddress());
    }
    
    @Test
    public void testAsElement_StringBuilder() throws IOException {
        StringBuilder json = IO.asStringBuilder(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(json);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }
    
    @Test
    public void testAsElement_Reader() throws IOException {
        Reader reader = new FileReader("src/test/resources/large_object.json");
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(reader);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }
    
    @Test
    public void testAsObject_Reader() throws IOException {
        Reader reader = new FileReader("src/test/resources/large_object.json");
        JsonReader jackson = new JacksonIO().getReader();
        JsonObject object = jackson.asObject(reader);
        Assert.assertTrue(object.isObject());
        assertGoodLargeObject(object.asObject());
    }
    
    @Test
    public void testAsArray_Reader() throws IOException {
        Reader reader = new FileReader("src/test/resources/small_object_array.json");
        JsonReader jackson = new JacksonIO().getReader();
        JsonArray array = jackson.asArray(reader);
        Assert.assertNotNull(array);
        assertGoodSmallObjectArray(array);
    }
    
    @Test
    public void testAsObject_Reader_Class() throws IOException {
        Reader reader = new FileReader(new File("src/test/resources/small_object.json"));
        JsonReader jackson = new JacksonIO().getReader();

        TestUser user = jackson.asObject(reader, TestUser.class);
        Assert.assertEquals("Roy", user.getFirstName());
        Assert.assertEquals("Watkins", user.getLastName());
        Assert.assertEquals("rwatkins0@studiopress.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("10.242.205.116", user.getIpAddress());
        
    }
    
    @Test
    public void testAsArray_Reader_Class() throws IOException {
        Reader reader = new FileReader(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();

        TestUser[] users = jackson.asArray(reader, TestUser.class);
        Assert.assertEquals(1000, users.length);
        Assert.assertEquals(195, users[194].getId().intValue());
        Assert.assertEquals("Stephen", users[194].getFirstName());
        Assert.assertEquals("Mason", users[194].getLastName());
        Assert.assertEquals("smason5e@barnesandnoble.com", users[194].getEmail());
        Assert.assertEquals("Ukraine", users[194].getCountry());
        Assert.assertEquals("54.49.166.253", users[194].getIpAddress());
    }
    
    @Test
    public void testAsElement_File() throws IOException {
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(new File("src/test/resources/large_object.json"));
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }

    @Test
    public void testAsObject_File() throws IOException {
        JsonReader jackson = new JacksonIO().getReader();
        JsonObject object = jackson.asObject(new File("src/test/resources/large_object.json"));
        Assert.assertTrue(object.isObject());
        assertGoodLargeObject(object.asObject());
    }

    @Test
    public void testAsArray_File() throws IOException {
        JsonReader jackson = new JacksonIO().getReader();
        JsonArray array = jackson.asArray(new File("src/test/resources/small_object_array.json"));
        Assert.assertNotNull(array);
        assertGoodSmallObjectArray(array);
    }

    @Test
    public void testAsObject_File_Class() throws IOException {
        JsonReader jackson = new JacksonIO().getReader();

        TestUser user = jackson.asObject(new File("src/test/resources/small_object.json"), TestUser.class);
        Assert.assertEquals("Roy", user.getFirstName());
        Assert.assertEquals("Watkins", user.getLastName());
        Assert.assertEquals("rwatkins0@studiopress.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("10.242.205.116", user.getIpAddress());
    }
    
    @Test
    public void testAsArray_File_Class() throws IOException {
        JsonReader jackson = new JacksonIO().getReader();

        TestUser[] users = jackson.asArray(new File("src/test/resources/small_object_array.json"), TestUser.class);
        Assert.assertEquals(1000, users.length);
        Assert.assertEquals(195, users[194].getId().intValue());
        Assert.assertEquals("Stephen", users[194].getFirstName());
        Assert.assertEquals("Mason", users[194].getLastName());
        Assert.assertEquals("smason5e@barnesandnoble.com", users[194].getEmail());
        Assert.assertEquals("Ukraine", users[194].getCountry());
        Assert.assertEquals("54.49.166.253", users[194].getIpAddress());
        
    }
    
    @Test
    public void testAsElement_InputStream() throws IOException {
        InputStream input = new FileInputStream("src/test/resources/large_object.json");
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(input);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
        
    }
    
    @Test
    public void testAsObject_InputStream_Class() throws IOException {
        InputStream input = new FileInputStream("src/test/resources/small_object.json");
        JsonReader jackson = new JacksonIO().getReader();

        TestUser user = jackson.asObject(input, TestUser.class);
        Assert.assertEquals("Roy", user.getFirstName());
        Assert.assertEquals("Watkins", user.getLastName());
        Assert.assertEquals("rwatkins0@studiopress.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("10.242.205.116", user.getIpAddress());
    }
    
    @Test
    public void testAsElement_Path() throws IOException {
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(Paths.get("src/test/resources/large_object.json"));
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }
    
    @Test
    public void testAsList_String() throws IOException {
        String json = IO.asString(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();
        List<JsonObject> list = jackson.asList(json);
        Assert.assertNotNull(list);

        Assert.assertEquals(1000L, list.size());
        Assert.assertEquals(195, list.get(194).getNumber("id"));
        Assert.assertEquals("Stephen", list.get(194).getString("firstName"));
        Assert.assertEquals("Mason", list.get(194).getString("lastName"));
        Assert.assertEquals("smason5e@barnesandnoble.com", list.get(194).getString("email"));
        Assert.assertEquals("Ukraine", list.get(194).getString("country"));
        Assert.assertEquals("54.49.166.253", list.get(194).getString("ipAddress"));
    }

    @Test
    public void testAsList_Path() throws IOException {
        JsonReader jackson = new JacksonIO().getReader();
        List<JsonObject> list = jackson.asList(Paths.get("src/test/resources/small_object_array.json"));
        Assert.assertNotNull(list);

        Assert.assertEquals(1000L, list.size());
        Assert.assertEquals(195, list.get(194).getNumber("id"));
        Assert.assertEquals("Stephen", list.get(194).getString("firstName"));
        Assert.assertEquals("Mason", list.get(194).getString("lastName"));
        Assert.assertEquals("smason5e@barnesandnoble.com", list.get(194).getString("email"));
        Assert.assertEquals("Ukraine", list.get(194).getString("country"));
        Assert.assertEquals("54.49.166.253", list.get(194).getString("ipAddress"));
    }

    @Test
    public void testAsList_String_Class() throws IOException {
        String json = IO.asString(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();
        List<TestUser> list = jackson.asList(json, TestUser.class);
        Assert.assertNotNull(list);

        Assert.assertEquals(1000L, list.size());
        Assert.assertEquals(195, list.get(194).getId().intValue());
        Assert.assertEquals("Stephen", list.get(194).getFirstName());
        Assert.assertEquals("Mason", list.get(194).getLastName());
        Assert.assertEquals("smason5e@barnesandnoble.com", list.get(194).getEmail());
        Assert.assertEquals("Ukraine", list.get(194).getCountry());
        Assert.assertEquals("54.49.166.253", list.get(194).getIpAddress());
    }
    
    @Test
    public void testAsMap_Object() throws IOException {
        String json = IO.asString(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        Map<String, Object> map = jackson.asMap(json);
        assertGoodLargeObject(new JsonObject(map));
    }
    
    @Test
    public void testAsMap_Double() throws IOException {
        String json = IO.asString(new File("src/test/resources/batting_averages.json"));
        JsonReader jackson = new JacksonIO().getReader();
        Map<String, Double> map = jackson.asMap(json, Double.class);
        Assert.assertEquals(0.36636, map.get("baTCobb"), 0.0);
        Assert.assertEquals(0.3585, map.get("baRHornsby"), 0.0);
        Assert.assertEquals(0.35575, map.get("baJJackson"), 0.0);
        Assert.assertEquals(0.3459, map.get("baEDelahanty"), 0.0);
        Assert.assertEquals(0.34468, map.get("baTSpeaker"), 0.0);
    }

    @Test
    public void testLargeObject() throws IOException {

        InputStream in = new FileInputStream("src/test/resources/large_object.json");
        JsonReader jackson = new JacksonIO().getReader();
        jackson.asStream(in, token -> {
            if(token.name().contains("state")) {
                Assert.assertTrue("Michigan".equals(token.asString()) ||
                        "Maryland".equals(token.asString()) ||
                        "Virginia".equals(token.asString()) ||
                        "Missouri".equals(token.asString()));
            }
        });
    }


    @Test
    public void testAsStream_InputStream_Handler_1() throws IOException {

        InputStream in = new FileInputStream("src/test/resources/small_object_array.json");
        JsonReader jackson = new JacksonIO().getReader();
        jackson.asStream(in, token -> {
            System.out.println(token.toString());
        });
    }

    @Test
    public void testAsStream_InputStream_Handler_2() throws IOException {

        InputStream in = new FileInputStream("src/test/resources/small_object_array.json");
        JsonReader jackson = new JacksonIO().getReader();
        jackson.asStream(in, token -> {
            if(token.name().startsWith("[13].id")) {
                Assert.assertEquals(14, token.asInteger());
            } else if(token.name().startsWith("[13].firstName")) {
                Assert.assertEquals("Jacqueline", token.asString());
            } else if(token.name().startsWith("[13].lastName")) {
                Assert.assertEquals("Kim", token.asString());
            } else if(token.name().startsWith("[13].email")) {
                Assert.assertEquals("jkimd@sogou.com", token.asString());
            }
        });
    }
    
    @Test
    public void testAsStream_Emails() throws IOException {

        InputStream input = new FileInputStream("src/test/resources/small_object_array.json");
        JsonReader jackson = new JacksonIO().getReader();
        
        List<String> emails = jackson.asStream(input)
            .filter(token -> token.name().contains("email"))
            .map(Token::asString)
            .sorted()
            .collect(Collectors.toList());
        
        Assert.assertEquals(1000, emails.size());
        Assert.assertEquals("aalleng1@webeden.co.uk", emails.get(0));
        Assert.assertEquals("wwood7e@princeton.edu", emails.get(999));
    }
    
    @Test
    public void testArrayOfPrimitives() throws IOException {
        
        JsonReader jackson = new JacksonIO().getReader();
        JsonArray array = jackson.asArray("[true,\"Nacho\",100,{\"my_address\":\"1 easy street\"}]");

        Boolean bool = array.get(0);
        String str   = array.get(1);
        Integer num  = array.get(2);
        JsonObject object = array.get(3);

        Assert.assertTrue(bool);
        Assert.assertEquals("Nacho", str);
        Assert.assertEquals(100L, num.longValue());
        Assert.assertEquals("1 easy street", object.getString("myAddress"));

    }

}
