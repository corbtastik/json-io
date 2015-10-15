package cworks.json.impl.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import cworks.json.streaming.ArrayNode;
import cworks.json.streaming.Node;
import cworks.json.streaming.ObjectNode;
import cworks.json.streaming.Token;
import cworks.json.streaming.TokenFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class JacksonIterator implements Iterator<Token> {
    
    private Token nextToken = null;
    private final JsonParser parser;
    private final Queue<Node> stack;

    public JacksonIterator(final JsonParser parser) {
        this.parser = parser;
        this.stack = Collections.asLifoQueue(new ArrayDeque<>());
    }

    @Override
    public boolean hasNext() {
        if (nextToken != null) {
            return true;
        } else {
            try {
                nextToken = readNext(parser, stack);
                return (nextToken != null);
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
    }

    @Override
    public Token next() {
        if (nextToken != null || hasNext()) {
            Token line = nextToken;
            nextToken = null;
            return line;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Return the next value JsonToken from the stream.  This method does not
     * emit json Object's or json Array's, rather only the json primitives.  The
     * name for each value is the flattened field name from the json structure.
     *
     * @param parser
     * @param stack
     * @return
     * @throws IOException
     */
    private Token readNext(JsonParser parser, Queue<Node> stack) throws IOException {

        while(parser.nextToken() != null) {
            JsonToken currentToken = parser.getCurrentToken();
            String currentName = parser.getCurrentName();
            if (currentToken == JsonToken.START_OBJECT) {
                // top-level object (the root)
                if (stack.isEmpty()) {
                    stack.add(new ObjectNode("root"));
                } else {
                    // anonymous object
                    if (isNullOrEmpty(currentName)) {
                        stack.add(new ObjectNode(stack.peek()));
                    } else {
                        // named object
                        stack.add(new ObjectNode(currentName, stack.peek()));
                    }
                }
            } else if (currentToken == JsonToken.END_OBJECT) {
                stack.remove();
            } else if (currentToken == JsonToken.START_ARRAY) {
                // top-level array (the root)
                if (stack.isEmpty()) {
                    stack.add(new ArrayNode("root"));
                } else {
                    // anonymous array
                    if (isNullOrEmpty(currentName)) {
                        stack.add(new ArrayNode(stack.peek()));
                    } else {
                        // named object
                        stack.add(new ArrayNode(currentName, stack.peek()));
                    }
                }
            } else if (currentToken == JsonToken.END_ARRAY) {
                stack.remove();
            } else if (currentToken == JsonToken.VALUE_STRING) {
                Node parent = stack.peek();
                return TokenFactory.createToken(parent, parser.getValueAsString());
            } else if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                Node parent = stack.peek();
                return TokenFactory.createToken(parent, parser.getIntValue());
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                Node parent = stack.peek();
                return TokenFactory.createToken(parent, parser.getDoubleValue());
            } else if (currentToken == JsonToken.VALUE_FALSE || currentToken == JsonToken.VALUE_TRUE) {
                Node parent = stack.peek();
                return TokenFactory.createToken(parent, parser.getBooleanValue());
            } else if (currentToken == JsonToken.VALUE_NULL) {
                Node parent = stack.peek();
                return TokenFactory.createToken(parent);
            } else if (currentToken == JsonToken.FIELD_NAME) {
                // push on to the value token
                JsonToken valueToken = parser.nextToken();
                if (valueToken == JsonToken.START_OBJECT) {
                    stack.add(new ObjectNode(parser.getCurrentName(), stack.peek()));
                } else if (valueToken == JsonToken.START_ARRAY) {
                    stack.add(new ArrayNode(parser.getCurrentName(), stack.peek()));
                } else if (valueToken == JsonToken.VALUE_FALSE || valueToken == JsonToken.VALUE_TRUE) {
                    Node parent = stack.peek();
                    return TokenFactory.createToken(parent, parser.getCurrentName(), parser.getBooleanValue());
                } else if (valueToken == JsonToken.VALUE_STRING) {
                    Node parent = stack.peek();
                    return TokenFactory.createToken(parent, parser.getCurrentName(), parser.getValueAsString());
                } else if (valueToken == JsonToken.VALUE_NUMBER_INT) {
                    Node parent = stack.peek();
                    return TokenFactory.createToken(parent, parser.getCurrentName(), parser.getIntValue());
                } else if (valueToken == JsonToken.VALUE_NUMBER_FLOAT) {
                    Node parent = stack.peek();
                    return TokenFactory.createToken(parent, parser.getCurrentName(), parser.getDoubleValue());
                } else if (valueToken == JsonToken.VALUE_NULL) {
                    Node parent = stack.peek();
                    return TokenFactory.createToken(parent, parser.getCurrentName(), null);
                }
            }
        }

        return null;
    }

    private boolean isNullOrEmpty(String text) {
        return text == null || text.trim().length() == 0;
    }
}
