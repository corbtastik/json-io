package cworks.json.streaming;

public class TokenFactory {
    
    public static Token createToken(Node node) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode);
        }
        return token;
    }

    public static Token createToken(Node node, String value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, value);
        }
        return token;
    }

    public static Token createToken(Node node, String name, String value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, name, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, name, value);
        }
        return token;    }

    public static Token createToken(Node node, int value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, value);
        }
        return token;
    }

    public static Token createToken(Node node, String name, int value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, name, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, name, value);
        }
        return token;
    }

    public static Token createToken(Node node, double value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, value);
        }
        return token;
    }

    public static Token createToken(Node node, String name, double value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, name, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, name, value);
        }
        return token;
    }

    public static Token createToken(Node node, boolean value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, value);
        }
        return token;
    }

    public static Token createToken(Node node, String name, boolean value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, name, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, name, value);
        }
        return token;
    }

    public static Token createToken(ArrayNode node, String value) {

        String id = String.format("%s[%d]", node.id, node.nextIndex());
        return new StreamToken(id, value, "string");
    }

    public static Token createToken(ArrayNode node, String name, String value) {

        String id = String.format("%s[%d].%s", node.id, node.nextIndex(), name);
        return new StreamToken(id, value, "string");
    }

    public static Token createToken(ArrayNode node, int value) {

        String id = String.format("%s[%d]", node.id, node.nextIndex());
        return new StreamToken(id, value, "int");
    }

    public static Token createToken(ArrayNode node, String name, int value) {

        String id = String.format("%s[%d].%s", node.id, node.nextIndex(), name);
        return new StreamToken(id, value, "int");
    }

    public static Token createToken(ArrayNode node, double value) {

        String id = String.format("%s[%d]", node.id, node.nextIndex());
        return new StreamToken(id, value, "int");
    }

    public static Token createToken(ArrayNode node, String name, double value) {

        String id = String.format("%s[%d].%s", node.id, node.nextIndex(), name);
        return new StreamToken(id, value, "double");
    }

    public static Token createToken(ArrayNode node, boolean value) {

        String id = String.format("%s[%d]", node.id, node.nextIndex());
        return new StreamToken(id, value, "boolean");
    }

    public static Token createToken(ArrayNode node, String name, boolean value) {

        String id = String.format("%s[%d].%s", node.id, node.nextIndex(), name);
        return new StreamToken(id, value, "boolean");
    }

    public static Token createToken(ArrayNode node) {

        String id = String.format("%s[%d]", node.id, node.nextIndex());
        return new StreamToken(id);
    }

    public static Token createToken(ObjectNode node) {

        return new StreamToken(node.id);
    }

    public static Token createToken(ObjectNode node, String value) {

        return new StreamToken(node.id, value, "string");
    }

    public static Token createToken(ObjectNode node, String name, String value) {
        String id = String.format("%s.%s", node.id, name);
        return new StreamToken(id, value, "string");
    }

    public static Token createToken(ObjectNode node, int value) {

        return new StreamToken(node.id, value, "int");
    }

    public static Token createToken(ObjectNode node, String name, int value) {
        String id = String.format("%s.%s", node.id, name);
        return new StreamToken(id, value, "int");
    }

    public static Token createToken(ObjectNode node, double value) {

        return new StreamToken(node.id, value, "double");
    }

    public static Token createToken(ObjectNode node, String name, double value) {
        String id = String.format("%s.%s", node.id, name);
        return new StreamToken(id, value, "double");
    }

    public static Token createToken(ObjectNode node, boolean value) {

        return new StreamToken(node.id, value, "boolean");
    }

    public static Token createToken(ObjectNode node, String name, boolean value) {
        String id = String.format("%s.%s", node.id, name);
        return new StreamToken(id, value, "boolean");
    }
}
