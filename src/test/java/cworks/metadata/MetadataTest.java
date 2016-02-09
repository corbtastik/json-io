package cworks.metadata;

import cworks.json.Json;
import org.junit.Test;

public class MetadataTest {

    @Test
    public void testMetadata() {

        MetaObject meta = new MetaObject();
        meta.setString("name", "Bucky");
        meta.setString("type", "dog");
        meta.setNumber("age", 5);
        meta.setArray("likes", new MetaArray()
            .addString("popcorn")
            .addString("bones")
            .addString("ham"));

        System.out.println(Json.asJson(meta.toMap()));

    }
}
