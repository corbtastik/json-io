package cworks.json.streaming;

public interface Token {
    public String asString();
    public int asInteger();
    public double asDouble();
    public boolean asBoolean();
    public String asJson();
    public String name();
    public String type();
}
