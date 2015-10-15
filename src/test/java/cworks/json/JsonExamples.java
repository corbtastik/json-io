/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Package: net.cworks.json
 * User: corbett
 * Created: 09/10/2013 13:30
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json;

import cworks.json.builder.JsonArrayBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JsonExamples {

    private static final Random rand = new Random(System.currentTimeMillis());

    @Test
    public void testAsObject() {

        JsonObject user = Json.asObject(JsonTestData.USER);

        Assert.assertEquals(Integer.valueOf(1), user.getInteger("id"));
        Assert.assertEquals("Justin", user.getString("firstName"));
        Assert.assertEquals("Harrison", user.getString("lastName"));
        Assert.assertEquals("jharrison0@diigo.com", user.getString("email"));
        Assert.assertEquals("China", user.getString("country"));
        Assert.assertEquals("205.174.198.42", user.getString("ipAddress"));

    }

    /**
     * This tests the ability of Json to deal with inconsistent field names
     * @see JsonTestData
     */
    @Test
    public void testJackedUserAsObject() {

        JsonObject user = Json.asObject(JsonTestData.JACKED_USER);

        Assert.assertEquals(Integer.valueOf(1), user.getInteger("id"));
        Assert.assertEquals("Justin", user.getString("firstName"));
        Assert.assertEquals("Harrison", user.getString("lastName"));
        Assert.assertEquals("jharrison0@diigo.com", user.getString("email"));
        Assert.assertEquals("China", user.getString("country"));
        Assert.assertEquals("205.174.198.42", user.getString("ipAddress"));
    }

    @Test
    public void testAsObjectToJavaInstance() {

        TestUser user = Json.asObject(JsonTestData.USER, TestUser.class);

        Assert.assertEquals("Justin", user.getFirstName());
        Assert.assertEquals("Harrison", user.getLastName());
        Assert.assertEquals("jharrison0@diigo.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("205.174.198.42", user.getIpAddress());

    }

    @Test
    public void testJackedAsObjectToJavaInstance() {

        TestUser user = Json.asObject(JsonTestData.JACKED_USER, TestUser.class);

        Assert.assertEquals("Justin", user.getFirstName());
        Assert.assertEquals("Harrison", user.getLastName());
        Assert.assertEquals("jharrison0@diigo.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("205.174.198.42", user.getIpAddress());
    }

    @Test
    public void randomExamples() {

        JsonObject jo = Json.object()
            .array("puppies", Json.array().add("Bucky").add("Nacho").build())
            .build();

        System.out.println(Json.asJson(jo));

        JsonArray ja = Json.array().add("Red", "Green", "Blue").build();
        System.out.println(Json.asJson(ja));

        ja = Json.array().add(new String[]{ "One", "Two", "Three" }).build();
        System.out.println(Json.asJson(ja));

        jo = Json.object().build();
        System.out.println(Json.asJson(jo));

        ja = Json.array().build();
        System.out.println(Json.asJson(ja));

        jo = Json.object()
            .string("name", "Chuck Norris")
            .binary("picture", JsonTestData.CHUCK_NORRIS_IMAGE.getBytes())
            .build();
        System.out.println(Json.asJson(jo));

        ja = Json.array().add(true)
            .add("Nacho")
            .add(100)
            .add(Json.object().string("address", "1 easy street").build())
            .build();
        System.out.println(Json.asJson(ja));

        JsonElement je = Json.object().string("name", "Bucky")
            .array("eyes", Json.array()
                .add(Json.object().string("position", "left" ).string("color", "blue" ).build())
                .add(Json.object().string("position", "right").string("color", "brown").build())
                .build())
            .build();

        System.out.println(Json.asJson(je));

        JsonArrayBuilder builder = Json.array();
        for(int i = 0; i < 100; i++) {
            builder.add(rand.nextInt());
        }
        ja = builder.build();
        System.out.println(Json.asJson(ja));

        JsonArray jsonArray = Json.asArray("[true,\"Nacho\",100,{\"my_address\":\"1 easy street\"}]");
        Boolean bool = jsonArray.get(0);
        String str   = jsonArray.get(1);
        Integer num  = jsonArray.get(2);
        JsonObject object = jsonArray.get(3);

        Assert.assertTrue(bool);
        Assert.assertEquals("Nacho", str);
        Assert.assertEquals(100L, num.longValue());
        Assert.assertEquals("1 easy street", object.getString("myAddress"));
    }

    @Test
    public void simpleUse() {

        JsonObject bucky = Json.object()
            .string("name", "Bucky Martin")
            .number("age", 4)
            .build();
        System.out.println(Json.asJson(bucky));

        JsonObject lola = Json.object()
            .string("name", "Lola")
            .number("age", 6)
            .build();
        System.out.println(lola.asString());
    }
    
    @Test
    public void simpleArray() {
        JsonArray arr = Json.array().add(
                Json.object().string("street", "18318 Namekagon Street")
                        .string("city", "Flint")
                        .string("state", "Michigan")
                        .string("zipcode", "48550").build()).add(
                Json.object().string("street", "8038 Cherokee Point")
                        .string("city", "Baltimore")
                        .string("state", "Maryland")
                        .string("zipcode", "21281").build()).add(
                Json.object().string("street", "1070 Randy Terrace")
                        .string("city", "Lynchburg")
                        .string("state", "Virginia")
                        .string("zipcode", "24503").build()).build();

        System.out.println(Json.asPrettyJson(arr));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void buildParser() {

        Map data = new HashMap<String, Object>();
        data.put("name", "Nacho Libre");
        data.put("year", 2008);
        data.put("boxOffice", "10 million");

        System.out.println(Json.asJson(data));
        System.out.println(Json.asPrettyJson(data));

    }
    
    @Test
    public void testIsNotObject() {
        // user is missing beginning {
        String user = " \"id\": 1,\n" +
                "    \"firstName\": \"Justin\",\n" +
                "    \"lastName\": \"Harrison\",\n" +
                "    \"email\": \"jharrison0@diigo.com\",\n" +
                "    \"country\": \"China\",\n" +
                "    \"ipAddress\": \"205.174.198.42\"\n" +
                "  }";
        
        try {
            Json.asObject(user);
            Assert.fail("User was an invalid Json object and asObject(json) should of raised exception.");
        } catch(JsonException ex) {
            Assert.assertNotNull(ex);
        }

        try {
            Json.asObject(user, TestUser.class);
            Assert.fail("User was an invalid Json object and asObject(json, clazz) should of raised exception.");
        } catch(JsonException ex) {
            Assert.assertNotNull(ex);
        }
    }
    
    @Test
    public void testIsNotArray() {

        // users is missing beginning [
        String users = " \"id\": 1,\n" +
                "    \"firstName\": \"Justin\",\n" +
                "    \"lastName\": \"Harrison\",\n" +
                "    \"email\": \"jharrison0@diigo.com\",\n" +
                "    \"country\": \"China\",\n" +
                "    \"ipAddress\": \"205.174.198.42\"\n" +
                "  }";

        try {
            Json.asArray(users);
            Assert.fail("Users was an invalid Json array and asArray(json) should of raised exception.");
        } catch(JsonException ex) {
            Assert.assertNotNull(ex);
        }

        try {
            Json.asArray(users, TestUser.class);
            Assert.fail("Users was an invalid Json array and asArray(json, clazz) should of raised exception.");
        } catch(JsonException ex) {
            Assert.assertNotNull(ex);
        }
        
    }
    
    @Test
    public void testArgs() {
        
        String home = getArgs(new String[] {"--treefs.home","/home/corbett"});
        Assert.assertEquals("/home/corbett", home);

        home = getArgs(new String[] {"--treefs.home"});
        Assert.assertNull(home);

        home = getArgs(new String[] {"--hello", "spongeBob", "--treefs.home","/home/corbett", "applePie"});
        Assert.assertEquals("/home/corbett", home);
        
    }
    
    public String getArgs(String[] args) {
        String home = null;
        for(int i = 0; i < args.length; i++) {
            if("--treefs.home".equalsIgnoreCase(args[i])) {
                if(args.length > (i+1)) {
                    home = args[i+1];
                }
            }
        }
        return home;
    }
}

