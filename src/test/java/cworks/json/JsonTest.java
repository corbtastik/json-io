package cworks.json;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class JsonTest {
    
    @Test
    public void testAsStream() {
        
        Json.asStream(new File("src/test/resources/large_object.json")).forEach(token -> {
            System.out.println(token.asJson());
        });

        long n = Json.asStream(new File("src/test/resources/large_object.json")).count();
        Assert.assertEquals(52, n);
    }
    
    @Test
    public void testAsStreamBattingAverages() {
        Json.asStream(new File("src/test/resources/batting_averages.json")).forEach(token -> {
            System.out.println(token.asJson());
        });
        
        long n = Json.asStream(new File("src/test/resources/batting_averages.json")).count();
        Assert.assertEquals(5, n);
    }
    
    @Test
    public void testPrettiness() {

        JsonObject wrapper = Json.object()
            .string("response", "A text response")
            .number("status", 200).build();

        System.out.println(Json.asPrettyJson(wrapper));

    }
    
}
