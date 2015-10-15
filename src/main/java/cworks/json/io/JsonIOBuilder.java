package cworks.json.io;

import cworks.json.JsonLib;
import cworks.json.impl.gson.GsonIO;
import cworks.json.impl.jackson.JacksonIO;

public class JsonIOBuilder {

    /**
     * Default Json library we're going to put to use 
     */
    private JsonLib impl = JsonLib.JACKSON;

    /**
     * Default Json context 
     */
    private AbstractJsonIO.DefaultJsonContext context = new AbstractJsonIO.DefaultJsonContext();

    /**
     * Use static creation method 
     * @param impl
     */
    private JsonIOBuilder(JsonLib impl) {
        this.impl = impl;
    }

    /**
     * Make a default JsonIO instance
     * @return
     */
    public static JsonIOBuilder io() {

        return io(JsonLib.JACKSON);
    }

    /**
     * Make a supported JsonIO instance
     * @param jsonLib
     * @return
     */
    public static JsonIOBuilder io(final JsonLib jsonLib) {
        
        return new JsonIOBuilder(jsonLib);
    }

    public JsonIOBuilder pretty() {
        this.context.pretty(true);
        return this;
    }
    
    public JsonIOBuilder pretty(boolean val) {
        this.context.pretty(val);
        return this;
    }

    public JsonIOBuilder dateFormat(String format) {
        this.context.dateFormat(format);
        return this;
    }

    public JsonIOBuilder allowComments() {
        this.context.allowComments(true);
        return this;
    }
    
    public JsonIOBuilder allowComments(boolean val) {
        this.context.allowComments(val);
        return this;
    }

    public AbstractJsonIO build() {


        AbstractJsonIO jsonIO;
        switch(this.impl) {
            case JACKSON:
                jsonIO = new JacksonIO(this.context);
                break;
            case GSON:
                jsonIO = new GsonIO(this.context);
                break;
            default:
                jsonIO = new JacksonIO(this.context);
        }

        return jsonIO;
    }

}


