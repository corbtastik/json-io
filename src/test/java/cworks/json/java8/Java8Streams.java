package cworks.json.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Java8Streams {
    
    @Test
    public void basicExample() {
        
        List<String> names = Arrays.asList("Bucky", "Nacho", "Gracie", "Lola", "Charlie");
        names.stream()
            .filter(s -> s.contains("ie"))
            .map(String::toLowerCase)
            .sorted()
            .forEach(System.out::println);
                
    }
    
    @Test
    public void streamOfExample() {
        
        Stream.of("Bucky", "Nacho", "Gracie", "Lola", "Charlie")
            .filter(s -> s.contains("ie"))
            .map(String::toLowerCase)
            .sorted()
            .forEach(System.out::println);
        
    }
    
    @Test
    public void processingOrder() {

        // filter will not execute because terminal method is absent
        Stream.of("Bucky", "Nacho", "Gracie", "Lola", "Charlie")
            .filter(s -> {
                System.out.println("filter: " + s);
                return true;
            });

        // the filter code runs when the forEach terminal method is present
        Stream.of("Bucky", "Nacho", "Gracie", "Lola", "Charlie")
            .filter(s -> {
                System.out.println("filter: " + s);
                return true;
            })
            .forEach(s -> {
                System.out.println("forEach: " + s);
            });
    }

}
