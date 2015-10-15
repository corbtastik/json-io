package cworks.json.streaming;

import cworks.json.Json;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JsonStreamingTest {

    
    @Test
    public void testLargeObject() throws IOException {
        
        InputStream in = new FileInputStream("src/test/resources/large_object.json");
        Json.asStream(in, token -> {
            System.out.println(token.toString());
        });
    }

    
    @Test
    public void testSmallObjectArray() throws IOException {
        
        InputStream in = new FileInputStream("src/test/resources/small_object_array.json");
        Json.asStream(in, token -> {
            System.out.println(token.toString());
        });
    }
    
    @Test
    public void testNumberArray() throws IOException {
        
        InputStream in = new FileInputStream("src/test/resources/number_array.json");
        Json.asStream(in, token -> {
            System.out.println(token.toString());
        });
    }
    
    @Test
    public void testSmallObject() throws IOException {

        InputStream in = new FileInputStream("src/test/resources/small_object.json");
        Json.asStream(in, token -> {
            System.out.println(token.toString());
        });
    }
    
    @Test
    public void testMixedArray() throws IOException {
        
        InputStream in = new FileInputStream("src/test/resources/mixed_array.json");
        Json.asStream(in, token -> {
            System.out.println(token.toString());
        });
    }

}
