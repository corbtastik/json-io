package cworks.metadata;

public abstract class MetaElement {

    public boolean isArray() {
        return this instanceof MetaArray;
    }

    public boolean isObject() {
        return this instanceof MetaObject;
    }

    public MetaArray asArray() {
        return (MetaArray)this;
    }

    public MetaObject asObject() {
        return (MetaObject)this;
    }
}
