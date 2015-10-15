/**
 * Created by comartin on 2/18/2015.
 */
package cworks.json;

public interface JsonHandler<T> {
    
    public void complete(JsonContext context);
    public void handle(T something, JsonContext context);
}
