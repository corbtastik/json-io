package cworks.json.parser.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import cworks.json.JsonJavaUtils;

import java.io.IOException;

public class ParserDelegate extends JsonParserDelegate {

    public ParserDelegate(JsonParser delegate) {
        super(delegate);
    }

    public String getCurrentName() throws IOException {
        //if(this.getCurrentToken() == JsonToken.FIELD_NAME) {
            return JsonJavaUtils.toMethodName(delegate.getCurrentName());
        //}

        //return delegate.getCurrentName();
    }
}
