package cworks.json.streaming;

public class ObjectNode extends Node {

    public ObjectNode(String id) {
        super(id);
        isArray = false;
        isObject = true;
        isRoot = true;
    }

    /**
     * Anonymous object (i.e. null field name)
     * @param parent
     */
    public ObjectNode(Node parent) {
        super(parent);
        if(parent.isArray()) {
            ArrayNode parentArray = (ArrayNode)parent;
            this.id = parent.id + "[" + parentArray.nextIndex() + "]";
        } else if(parent.isObject()) {
            ObjectNode parentObject = (ObjectNode)parent;
            this.id = parentObject.id;
        }
        isArray = false;
        isObject = true;
    }

    /**
     * Named object (i.e. has a real field name)
     * @param id
     * @param parent
     */
    public ObjectNode(String id, Node parent) {
        super(parent);
        this.id = parent.id + "." + id;
        this.isArray = false;
        this.isObject = true;
    }
}
