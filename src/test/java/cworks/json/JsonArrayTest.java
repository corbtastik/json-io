package cworks.json;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class JsonArrayTest {
    
    @Test
    public void testToTypedArray() {
        
        TestUser[] array = Json.asArray(JsonTestData.USERS_ARRAY, TestUser.class);
        Assert.assertEquals(10, array.length);
    }
    
    @Test
    public void testToArrayBasicFromFile() {
        
        JsonArray array = Json.asArray(new File("src/test/resources/small_object_array.json"));
        Assert.assertEquals(1000, array.size());
    }
    
    @Test
    public void testToTypedArrayFromFile() {
        
        TestUser[] array = Json.asArray(new File("src/test/resources/small_object_array.json"), TestUser.class);
        Assert.assertEquals(1000, array.length);
    }
    
    @Test
    public void testAsArray() {

        JsonArray users = Json.asArray(JsonTestData.USERS_ARRAY);

        JsonObject user1  = users.get(0);
        JsonObject user2  = users.get(1);
        JsonObject user10 = users.get(9);

        Assert.assertEquals("Alice",   user1.getString("firstName"));
        Assert.assertEquals("Timothy", user2.getString("firstName"));
        Assert.assertEquals("Tina",    user10.getString("firstName"));

        Assert.assertEquals(10, users.size());
    }
    
}
