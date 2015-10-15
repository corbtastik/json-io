package cworks.json.streaming;

@FunctionalInterface
public interface StreamHandler<T> {
    public void handle(T item);
}
