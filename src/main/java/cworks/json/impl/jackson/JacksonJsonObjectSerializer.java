package cworks.json.impl.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import cworks.json.JsonArray;
import cworks.json.JsonObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;

public class JacksonJsonObjectSerializer extends StdSerializer<JsonObject> {

    public JacksonJsonObjectSerializer() {
        super(JsonObject.class);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        return createSchemaNode("object", true);
    }

    @Override
    public void serialize(JsonObject value, JsonGenerator generator, SerializerProvider provider)
        throws IOException {
        generator.writeStartObject();
        _serialize(value, generator, provider);
        generator.writeEndObject();
    }
    
    @Override
    public void serializeWithType(JsonObject value, JsonGenerator generator, SerializerProvider provider,
        TypeSerializer typeSerializer) throws IOException {
        typeSerializer.writeTypePrefixForObject(value, generator);
        _serialize(value, generator, provider);
        typeSerializer.writeTypeSuffixForObject(value, generator);
    }
    
    private void _serialize(JsonObject value, JsonGenerator generator, SerializerProvider provider)
        throws IOException {

        Iterator<?> it = value.toMap().keySet().iterator();
        while(it.hasNext()) {
            String key = (String)it.next();
            Object object = value.getValue(key);
            if(object == null) {
                if (provider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {
                    generator.writeNullField(key);
                }
                continue;
            }
            generator.writeFieldName(key);
            Class<?> clazz = object.getClass();
            if (clazz == JsonObject.class) {
                serialize((JsonObject) object, generator, provider);
            } else if (clazz == JsonArray.class) {
                new JacksonJsonArraySerializer().serialize((JsonArray) object, generator, provider);
            } else  if (clazz == String.class) {
                generator.writeString((String)object);
            } else  if (clazz == Integer.class) {
                generator.writeNumber(((Integer)object).intValue());
            } else  if (clazz == Long.class) {
                generator.writeNumber(((Long)object).longValue());
            } else  if (clazz == Boolean.class) {
                generator.writeBoolean((Boolean)object);
            } else  if (clazz == Double.class) {
                generator.writeNumber((Double)object);
            } else if (clazz == JsonArray.class) {
                new JacksonJsonArraySerializer().serialize((JsonArray)object, generator, provider);
            } else if (JsonObject.class.isAssignableFrom(clazz)) { // sub-class
                serialize((JsonObject)object, generator, provider);
            } else if (JsonArray.class.isAssignableFrom(clazz)) {  // sub-class
                new JacksonJsonArraySerializer().serialize((JsonArray)object, generator, provider);
            } else {
                provider.defaultSerializeValue(object, generator);
            }
        }
    }
}
