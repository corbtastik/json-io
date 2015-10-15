/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Baked with love by comartin
 * Package: net.cworks.json.parser
 * User: comartin
 * Created: 8/13/2014 10:26 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json.parser;

import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonObject;
import cworks.json.streaming.StreamHandler;
import cworks.json.streaming.Token;

import java.io.InputStream;
import java.util.List;

public abstract class JsonParser {

    /**
     * to build json pretty or not
     */
    protected Boolean pretty;

    /**
     * Date format to use by default 
     */
    protected String dateFormat;

    /**
     * to allow comments or not 
     */
    protected Boolean allowComments;

    /**
     * Create a parser 
     */
    protected JsonParser() {
        pretty(false);
        dateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        allowComments(true);
    }

    // String to Java Object methods
    public abstract JsonElement toObject(String json) throws JsonException;

    public abstract <T> T toObject(String json, final Class<T> clazz) throws JsonException;
    
    // String to Java array methods
    public abstract <T> T[] toArray(String json, final Class<T> clazz) throws JsonException;

    // String to List
    public abstract List<JsonObject> toList(String json) throws JsonException;
    public abstract <T> List<T> toList(String json, Class<T> clazz) throws JsonException;


    // Java instance methods to json String methods
    public abstract String toJson(JsonElement element) throws JsonException;

    public abstract String toJson(Object obj) throws JsonException;

    // Read methods
    public abstract void read(InputStream in, StreamHandler<Token> handler) throws JsonException;
    public abstract <T> void read(InputStream in, Class<T> clazz, StreamHandler<T> handler) throws JsonException;
    
    
    protected void pretty(boolean val) {
        this.pretty = val;
    }

    protected void dateFormat(String val) {
        this.dateFormat = val;
    }

    protected void allowComments(boolean val) {
        this.allowComments = val;
    }




}
