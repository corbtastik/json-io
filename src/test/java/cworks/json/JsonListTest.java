package cworks.json;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class JsonListTest {
    
    @Test
    public void testToListBasic() {
        List<JsonObject> users = Json.asList(new File("src/test/resources/small_object_array.json"));
        Assert.assertEquals(1000, users.size());
    }

    @Test
    public void testToListWithSubLists() {
        
        List<JsonObject> people = Json.asList(JsonTestData.PEOPLE_WITH_ADDRESSES);
        Assert.assertEquals(10, people.size());
        
        Assert.assertEquals(1, people.get(0).getNumber("id"));
        Assert.assertEquals("Sandra", people.get(0).getString("firstName"));
        Assert.assertEquals("Russell", people.get(0).getString("lastName"));
        Assert.assertEquals("srussell0@ycombinator.com", people.get(0).getString("email"));
        Assert.assertEquals("China", people.get(0).getString("country"));
        Assert.assertEquals("182.144.153.254", people.get(0).getString("ipAddress"));
        Assert.assertEquals(3, people.get(0).getArray("priorAddresses").size());
        
        Assert.assertEquals("26494 Carioca Circle",
            people.get(0).getArray("priorAddresses").objectAt(0).getString("street"));
        Assert.assertEquals("Tampa",
            people.get(0).getArray("priorAddresses").objectAt(0).getString("city"));
        Assert.assertEquals("Florida",
            people.get(0).getArray("priorAddresses").objectAt(0).getString("state"));
        Assert.assertEquals("33605",
            people.get(0).getArray("priorAddresses").objectAt(0).getString("zip"));

        Assert.assertEquals("0257 Burning Wood Circle",
                people.get(0).getArray("priorAddresses").objectAt(1).getString("street"));
        Assert.assertEquals("Fort Myers",
                people.get(0).getArray("priorAddresses").objectAt(1).getString("city"));
        Assert.assertEquals("Florida",
                people.get(0).getArray("priorAddresses").objectAt(1).getString("state"));
        Assert.assertEquals("33906",
                people.get(0).getArray("priorAddresses").objectAt(1).getString("zip"));
        
        Assert.assertEquals("8 Schmedeman Alley",
                people.get(0).getArray("priorAddresses").objectAt(2).getString("street"));
        Assert.assertEquals("Albuquerque",
                people.get(0).getArray("priorAddresses").objectAt(2).getString("city"));
        Assert.assertEquals("New Mexico",
                people.get(0).getArray("priorAddresses").objectAt(2).getString("state"));
        Assert.assertEquals("87115",
                people.get(0).getArray("priorAddresses").objectAt(2).getString("zip"));
                
    }

    @Test
    public void testToTypedList() {
        
        List<TestUser> users = Json.asList(JsonTestData.USERS_ARRAY, TestUser.class);
        Assert.assertEquals(10, users.size());
        
        Assert.assertEquals("Alice", users.get(0).getFirstName());
        Assert.assertEquals("Timothy", users.get(1).getFirstName());
        Assert.assertEquals("Janet", users.get(2).getFirstName());
        Assert.assertEquals("Laura", users.get(3).getFirstName());
        Assert.assertEquals("Andrew", users.get(4).getFirstName());
        Assert.assertEquals("Maria", users.get(5).getFirstName());
        Assert.assertEquals("Marie", users.get(6).getFirstName());
        Assert.assertEquals("Sharon", users.get(7).getFirstName());
        Assert.assertEquals("Dennis", users.get(8).getFirstName());
        Assert.assertEquals("Tina", users.get(9).getFirstName());
    }

}
