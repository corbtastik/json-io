package cworks.json.impl.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import cworks.json.JsonArray;

import java.io.IOException;

public class JacksonJsonArrayDeserializer extends StdDeserializer<JsonArray> {
    
    public JacksonJsonArrayDeserializer() {
        super(JsonArray.class);
    }
    
    @Override
    public JsonArray deserialize(JsonParser parser, DeserializationContext context)
        throws IOException, JsonProcessingException {

        JsonArray array = new JsonArray();
        JsonToken token;
        while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
            switch (token) {
                case START_ARRAY:
                    array.addArray(deserialize(parser, context));
                    continue;
                case START_OBJECT:
                    array.addObject(new JacksonJsonObjectDeserializer().deserialize(parser, context));
                    continue;
                case VALUE_STRING:
                    array.addString(parser.getText());
                    continue;
                case VALUE_NULL:
                    array.add(null);
                    continue;
                case VALUE_TRUE:
                    array.addBoolean(Boolean.TRUE);
                    continue;
                case VALUE_FALSE:
                    array.addBoolean(Boolean.FALSE);
                    continue;
                case VALUE_NUMBER_INT:
                    array.addNumber(parser.getNumberValue());
                    continue;
                case VALUE_NUMBER_FLOAT:
                    array.addNumber(parser.getNumberValue());
                    continue;
                case VALUE_EMBEDDED_OBJECT:
                    array.add(parser.getEmbeddedObject());
                    continue;
                default:
                    throw context.mappingException("Unrecognized JsonToken type: " + token);
            }
        }
        return array;
    }
}
