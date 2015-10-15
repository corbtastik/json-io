package cworks.json.impl.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import cworks.json.JsonArray;
import cworks.json.JsonObject;

import java.io.IOException;
import java.lang.reflect.Type;

public class JacksonJsonArraySerializer extends StdSerializer<JsonArray> {

    public JacksonJsonArraySerializer() {
        super(JsonArray.class);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        return createSchemaNode("array", true);
    }
    
    @Override
    public void serialize(JsonArray value, JsonGenerator generator, SerializerProvider provider)
        throws IOException {
        generator.writeStartArray();
        _serialize(value, generator, provider);
        generator.writeEndArray();
    }

    @Override
    public void serializeWithType(JsonArray value, JsonGenerator jgen, SerializerProvider provider,
        TypeSerializer typeSer) throws IOException {
        typeSer.writeTypePrefixForArray(value, jgen);
        _serialize(value, jgen, provider);
        typeSer.writeTypeSuffixForArray(value, jgen);
    }

    private void _serialize(JsonArray value, JsonGenerator generator, SerializerProvider provider)
        throws IOException {

            for (int i = 0, len = value.size(); i < len; ++i) {
            Object ob = value.get(i);
            if(ob == null) {
                generator.writeNull();
                continue;
            }
            Class<?> cls = ob.getClass();
            if(cls == JsonObject.class) {
                new JacksonJsonObjectSerializer().serialize((JsonObject) ob, generator, provider);
            } else if (cls == JsonArray.class) {
                serialize((JsonArray) ob, generator, provider);
            } else  if (cls == String.class) {
                generator.writeString((String) ob);
            } else  if (cls == Integer.class) {
                generator.writeNumber(((Integer) ob).intValue());
            } else  if (cls == Long.class) {
                generator.writeNumber(((Long) ob).longValue());
            } else  if (cls == Boolean.class) {
                generator.writeBoolean((Boolean) ob);
            } else  if (cls == Double.class) {
                generator.writeNumber((Double) ob);
            } else if (JsonObject.class.isAssignableFrom(cls)) { // sub-class
                new JacksonJsonObjectSerializer().serialize((JsonObject) ob, generator, provider);
            } else if (JsonArray.class.isAssignableFrom(cls)) {  // sub-class
                serialize((JsonArray) ob, generator, provider);
            } else if (JsonArray.class.isAssignableFrom(cls)) {  // sub-class
                new JacksonJsonArraySerializer().serialize((JsonArray) ob, generator, provider);
            } else {
                provider.defaultSerializeValue(ob, generator);
            }
        }

    }

        
}
