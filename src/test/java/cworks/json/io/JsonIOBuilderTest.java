package cworks.json.io;

import cworks.json.impl.jackson.JacksonIO;
import org.junit.Assert;
import org.junit.Test;

public class JsonIOBuilderTest {
    
    @Test
    public void testBuilding() {
        
        AbstractJsonIO jsonIO = JsonIOBuilder.io().pretty().build();
        Assert.assertEquals(JacksonIO.class, jsonIO.getClass());
        
    }
}
