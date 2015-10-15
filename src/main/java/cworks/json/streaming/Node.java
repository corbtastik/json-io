package cworks.json.streaming;

import cworks.json.JsonNull;

public abstract class Node {
    
    protected Node parent = null;
    protected String id;
    protected Object value = new JsonNull();
    protected boolean isArray = false;
    protected boolean isObject = false;
    protected boolean isRoot = false;

    public Node(String id) {
        this.id = id;

    }
    public Node(String id, Object value) {
        this.id = id;
        this.value = value;
    }
    public Node(Node parent) {
        this.parent = parent;
    }

    public String toString() {
        return "(" + id + ", " + value.toString() + ")";
    }

    public boolean isArray() {
        return this.isArray;
    }

    public boolean isObject() {
        return this.isObject;
    }

    public boolean isRoot() {
        return this.isRoot;
    }
}
