package cworks.json.impl.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import cworks.json.JsonJavaUtils;
import cworks.json.JsonObject;

import java.io.IOException;

public class JacksonJsonObjectDeserializer extends StdDeserializer<JsonObject> {

    public JacksonJsonObjectDeserializer() {
        super(JsonObject.class);
    }
    
    @Override
    public JsonObject deserialize(JsonParser parser, DeserializationContext context)
        throws IOException, JsonProcessingException {
        
        JsonObject object = new JsonObject();
        JsonToken token = parser.getCurrentToken();
        
        if(token == JsonToken.START_OBJECT) {
            token = parser.nextToken();
        }

        for (; token == JsonToken.FIELD_NAME; token = parser.nextToken()) {
            String fieldName = JsonJavaUtils.toMethodName(parser.getCurrentName());
            token = parser.nextToken();
                switch (token) {
                    case START_ARRAY:
                        object.setArray(fieldName, new JacksonJsonArrayDeserializer().deserialize(parser, context));
                        continue;
                    case START_OBJECT:
                        object.setObject(fieldName, deserialize(parser, context));
                        continue;
                    case VALUE_STRING:
                        object.setString(fieldName, parser.getText());
                        continue;
                    case VALUE_NULL:
                        object.setValue(fieldName, null);
                        continue;
                    case VALUE_TRUE:
                        object.setBoolean(fieldName, Boolean.TRUE);
                        continue;
                    case VALUE_FALSE:
                        object.setBoolean(fieldName, Boolean.FALSE);
                        continue;
                    case VALUE_NUMBER_INT:
                        object.setNumber(fieldName, parser.getNumberValue());
                        continue;
                    case VALUE_NUMBER_FLOAT:
                        object.setNumber(fieldName, parser.getNumberValue());
                        continue;
                    case VALUE_EMBEDDED_OBJECT:
                        object.setValue(fieldName, parser.getEmbeddedObject());
                        continue;
                    default:
                        throw context.mappingException("Unrecognized JsonToken type: " + token);
                }
        }
        
        return object;
    }
    
}
