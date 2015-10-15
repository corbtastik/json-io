package cworks.json.parser.jackson;

import cworks.json.JsonContext;
import cworks.json.JsonHandler;
import cworks.json.JsonObject;
import cworks.json.JsonTestData;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JacksonParserTest {

    private static final String JSON_DATA = "[\n" +
            "  {\"id\":1,\"first_name\":\"Roy\",\"last_name\":\"Watkins\",\"email\":\"rwatkins0@studiopress.com\",\"country\":\"China\",\"ip_address\":\"10.242.205.116\"},\n" +
            "  {\"id\":2,\"first_name\":\"Terry\",\"last_name\":\"Kim\",\"email\":\"tkim1@nydailynews.com\",\"country\":\"China\",\"ip_address\":\"59.208.196.189\"},\n" +
            "  {\"id\":3,\"first_name\":\"Justin\",\"last_name\":\"Ward\",\"email\":\"jward2@phpbb.com\",\"country\":\"Indonesia\",\"ip_address\":\"185.116.168.195\"}]";

    //@Test
    public void testLargeRead() throws IOException {
        
        JacksonParser parser = new JacksonParser();
        InputStream in = new FileInputStream("src/test/resources/small_object_array.json");
        
        parser.read(JsonTestData.PEOPLE_WITH_ADDRESSES, new JsonHandler<JsonObject>() {
            @Override
            public void complete(JsonContext context) {
                System.out.println("read complete!");
            }

            @Override
            public void handle(JsonObject object, JsonContext context) {
                System.out.println(object.asString());
            }
        });
        
        in.close();
    }
    
    @Test
    public void testJacksonStreaming() throws IOException {

        InputStream in = new FileInputStream("src/test/resources/number_array.json");
        JacksonParser parser = new JacksonParser();

        parser.read(in).filter(token -> {
            return token.asInteger() >= 15000 && token.asInteger() < 16000;
        }).forEach(odd -> {
            System.out.println(odd.asJson());
        });
        

    }
}
