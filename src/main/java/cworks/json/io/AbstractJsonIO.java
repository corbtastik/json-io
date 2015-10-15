package cworks.json.io;

import cworks.json.JsonContext;
import cworks.json.spi.JsonReader;
import cworks.json.spi.JsonWriter;

public abstract class AbstractJsonIO implements JsonWriter, JsonReader {
    
    /**
     * Contextual settings for this JsonIO instance
     */
    protected JsonContext context;
    
    public static class DefaultJsonContext implements JsonContext {
        private boolean pretty = false;
        private boolean allowComments = true;
        private String dateFormat = "yyyy-MM-dd'T'HH:mm:ssz";
        @Override
        public boolean isPretty() {
            return this.pretty;
        }
        public void pretty(boolean value) {
            this.pretty = value;
        }
        @Override
        public boolean allowComments() {
            return this.allowComments;
        }
        public void allowComments(boolean value) {
            this.allowComments = value;
        }
        @Override
        public String dateFormat() {
            return this.dateFormat;
        }
        public void dateFormat(String value) {
            this.dateFormat = value;
        }
    }

    /**
     * Create a JsonIO instance with default settings 
     */
    protected AbstractJsonIO() {
        this(new DefaultJsonContext());
    }

    /**
     * Create a JsonIO instance with the specific JsonContext
     * @param context
     */
    protected AbstractJsonIO(JsonContext context) {
        this.context = context;
    }

    /**
     * Abstraction that must be implemented by concrete classes.  Implementations
     * should return concrete JsonReader that can be used to translate json input
     * into Java types. 
     * @return
     */
    public abstract JsonReader getReader();

    /**
     * Abstraction that must be implemented by concrete classes.  Implementations
     * should return concrete JsonWriter that can be used to translate Java types
     * into json output.
     * @return
     */
    public abstract JsonWriter getWriter();

}
