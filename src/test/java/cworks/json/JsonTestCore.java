package cworks.json;

import org.junit.Assert;

public class JsonTestCore {
    
    protected void assertGoodSmallObjectArray(JsonArray array) {
        /*
        195
        784
        414
        973
        958
        854
        756
        62
        836
        281
        843
        879
        83
        */
        Assert.assertEquals(1000L, array.size());
        Assert.assertEquals(195, array.objectAt(194).getNumber("id"));
        Assert.assertEquals("Stephen", array.objectAt(194).getString("firstName"));
        Assert.assertEquals("Mason", array.objectAt(194).getString("lastName"));
        Assert.assertEquals("smason5e@barnesandnoble.com", array.objectAt(194).getString("email"));
        Assert.assertEquals("Ukraine", array.objectAt(194).getString("country"));
        Assert.assertEquals("54.49.166.253", array.objectAt(194).getString("ipAddress"));
    }
    
    protected void assertGoodLargeObject(JsonObject object) {

        Assert.assertEquals(1L, object.getLong("id").longValue());
        Assert.assertEquals("Fred", object.getString("firstName"));
        Assert.assertEquals("Lynch", object.getString("lastName"));
        Assert.assertEquals("flynch0@latimes.com", object.getString("email"));
        Assert.assertEquals("China", object.getString("country"));
        Assert.assertEquals("25.210.64.183", object.getString("ipAddress"));

        Assert.assertEquals("18318 Namekagon Street",
                object.getArray("priorAddresses").objectAt(0).getString("street"));
        Assert.assertEquals("Flint",
                object.getArray("priorAddresses").objectAt(0).getString("city"));
        Assert.assertEquals("Michigan",
                object.getArray("priorAddresses").objectAt(0).getString("state"));
        Assert.assertEquals("48550",
                object.getArray("priorAddresses").objectAt(0).getString("zipcode"));

        Assert.assertEquals("8038 Cherokee Point",
                object.getArray("priorAddresses").objectAt(1).getString("street"));
        Assert.assertEquals("Baltimore",
                object.getArray("priorAddresses").objectAt(1).getString("city"));
        Assert.assertEquals("Maryland",
                object.getArray("priorAddresses").objectAt(1).getString("state"));
        Assert.assertEquals("21281",
                object.getArray("priorAddresses").objectAt(1).getString("zipcode"));

        Assert.assertEquals("1070 Randy Terrace",
                object.getArray("priorAddresses").objectAt(2).getString("street"));
        Assert.assertEquals("Lynchburg",
                object.getArray("priorAddresses").objectAt(2).getString("city"));
        Assert.assertEquals("Virginia",
                object.getArray("priorAddresses").objectAt(2).getString("state"));
        Assert.assertEquals("24503",
                object.getArray("priorAddresses").objectAt(2).getString("zipcode"));

        Assert.assertEquals("86476 Arkansas Point",
                object.getArray("priorAddresses").objectAt(3).getString("street"));
        Assert.assertEquals("Kansas City",
                object.getArray("priorAddresses").objectAt(3).getString("city"));
        Assert.assertEquals("Missouri",
                object.getArray("priorAddresses").objectAt(3).getString("state"));
        Assert.assertEquals("64125",
                object.getArray("priorAddresses").objectAt(3).getString("zipcode"));

        Assert.assertEquals(96.72, object.getArray("numbers").doubleAt(0), 0);
        Assert.assertEquals(20.76, object.getArray("numbers").doubleAt(1), 0);
        Assert.assertEquals(12.99, object.getArray("numbers").doubleAt(2), 0);

        Assert.assertEquals("2014-11-14T23:03:12Z", object.getString("birthdate"));
        Assert.assertEquals("0001", object.getObject("items").getArray("item").objectAt(0).getString("id"));
        Assert.assertEquals("donut", object.getObject("items").getArray("item").objectAt(0).getString("type"));
        Assert.assertEquals("Cake", object.getObject("items").getArray("item").objectAt(0).getString("name"));
        Assert.assertEquals(0.55, object.getObject("items").getArray("item").objectAt(0).getNumber("ppu"));

        Assert.assertEquals("1001", object.getObject("items").getArray("item").objectAt(0).getObject("batters").getArray("batter").objectAt(0).getString("id"));
        Assert.assertEquals("Regular", object.getObject("items").getArray("item").objectAt(0).getObject("batters").getArray("batter").objectAt(0).getString("type"));

        Assert.assertEquals("1002", object.getObject("items").getArray("item").objectAt(0).getObject("batters").getArray("batter").objectAt(1).getString("id"));
        Assert.assertEquals("Chocolate", object.getObject("items").getArray("item").objectAt(0).getObject("batters").getArray("batter").objectAt(1).getString("type"));

        Assert.assertEquals("1003", object.getObject("items").getArray("item").objectAt(0).getObject("batters").getArray("batter").objectAt(2).getString("id"));
        Assert.assertEquals("Blueberry", object.getObject("items").getArray("item").objectAt(0).getObject("batters").getArray("batter").objectAt(2).getString("type"));

        Assert.assertEquals("1004", object.getObject("items").getArray("item").objectAt(0).getObject("batters").getArray("batter").objectAt(3).getString("id"));
        Assert.assertEquals("Devil's Food", object.getObject("items").getArray("item").objectAt(0).getObject("batters").getArray("batter").objectAt(3).getString("type"));

        Assert.assertEquals("5001", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(0).getString("id"));
        Assert.assertEquals("None", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(0).getString("type"));

        Assert.assertEquals("5002", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(1).getString("id"));
        Assert.assertEquals("Glazed", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(1).getString("type"));

        Assert.assertEquals("5005", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(2).getString("id"));
        Assert.assertEquals("Sugar", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(2).getString("type"));

        Assert.assertEquals("5007", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(3).getString("id"));
        Assert.assertEquals("Powdered Sugar", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(3).getString("type"));

        Assert.assertEquals("5006", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(4).getString("id"));
        Assert.assertEquals("Chocolate with Sprinkles", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(4).getString("type"));

        Assert.assertEquals("5003", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(5).getString("id"));
        Assert.assertEquals("Chocolate", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(5).getString("type"));

        Assert.assertEquals("5004", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(6).getString("id"));
        Assert.assertEquals("Maple", object.getObject("items").getArray("item").objectAt(0).getArray("topping").objectAt(6).getString("type"));
        
    }
}
