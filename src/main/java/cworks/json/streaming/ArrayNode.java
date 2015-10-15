package cworks.json.streaming;

public class ArrayNode extends Node {

    private int i = 0;
    
    public ArrayNode(String id) {
        super(id);
        isArray = true;
        isObject = false;
        isRoot = true;
    }

    public ArrayNode(Node parent) {
        super(parent);
        this.id = parent.id + "." + id;
        isArray = true;
        isObject = false;
    }

    public ArrayNode(String id, Node parent) {
        super(parent);
        this.id = parent.id + "." + id;
        this.isArray = true;
        this.isObject = false;
    }

    public int nextIndex() {
        return i++;
    }
}
