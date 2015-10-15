package cworks.json.streaming;

import cworks.json.JsonObject;

class StreamToken implements Token {

    /**
     * field name in the json document 
     */
    private String name;

    /**
     * type name of value, possible values are: int, double, boolean, string
     */
    private String type;

    /**
     * value of the field in the parsed json document, only one of these will be set 
     */
    private String  stringValue;
    private Integer intValue;
    private Double  doubleValue;
    private Boolean booleanValue;

    public StreamToken(String name) {
        this.name = name;
    }

    public StreamToken(String name, String value, String type) {
        this.name = name;
        this.stringValue = value;
    }

    public StreamToken(String name, int value, String type) {
        this.name = name;
        this.intValue = value;
    }

    public StreamToken(String name, double value, String type) {
        this.name = name;
        this.doubleValue = value;
    }

    public StreamToken(String name, boolean value, String type) {
        this.name = name;
        this.booleanValue = value;
    }

    @Override
    public String asString() {
        return this.stringValue;
    }

    public int asInteger() {
        return this.intValue;
    }

    public double asDouble() {
        return this.doubleValue;
    }

    public boolean asBoolean() {
        return this.booleanValue;
    }

    /**
     * This token as a Json string {name: [the name], value: [the value], type: [the type]}
     * @return
     */
    public String asJson() {
        
        JsonObject me = new JsonObject();
        me.setString("name", name());
        
        if(this.stringValue != null) {
            me.setString("value", this.stringValue).setString("type", "string");
        } else if(this.intValue != null) {
            me.setNumber("value", this.intValue).setString("type", "int");
        } else if(this.doubleValue != null) {
            me.setNumber("value", this.doubleValue).setString("type", "double");
        } else if(this.booleanValue != null) {
            me.setBoolean("value", this.booleanValue).setString("type", "boolean");
        } else {
            me.setString("value", null).asString();
        }
        
        return me.asString();
    }

    @Override
    public String name() {
        if(this.name.startsWith("root.")) {
            return this.name.substring(5);
        } else if(this.name.startsWith("root")) {
            return this.name.substring(4);
        }

        return this.name;
    }

    @Override
    public String type() {
        return this.type;
    }

    private String valueToString() {
        if(this.stringValue != null) {
            return this.stringValue;
        } else if(this.intValue != null) {
            return String.valueOf(this.intValue);
        } else if(this.doubleValue != null) {
            return String.valueOf(this.doubleValue);
        } else if(this.booleanValue != null) {
            return String.valueOf(this.booleanValue);
        } else {
            return "(null)";
        }
    }

    @Override
    public String toString() {
        
        return this.name() + "," + valueToString();
    }
}
