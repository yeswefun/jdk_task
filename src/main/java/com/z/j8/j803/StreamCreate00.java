package com.z.j8.j803;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class StreamCreate00 {

    public static void main(String[] args) {
        createStreamFromCollection().forEach(System.out::println);

        System.out.println("------------");
        createStreamFromValues().forEach(System.out::println);

        System.out.println("------------");
        createStreamFromArrays().forEach(System.out::println);

        //System.out.println("------------");
        //stream has already been operated upon or closed
        //createStreamFromFile().forEach(System.out::println);
        System.out.println(createStreamFromFile());
    }

    private static Stream<String> createStreamFromCollection() {
        List<String> strings = Arrays.asList("C/C++", "Java", "Kotlin", "Python");
        return strings.stream();
    }

    private static Stream<String> createStreamFromValues() {
        return Stream.of("C/C++", "Java", "Kotlin", "Python");
    }

    private static Stream<String> createStreamFromArrays() {
        String[] strings = {"C/C++", "Java", "Kotlin", "Python"};
        return Arrays.stream(strings);
    }

    private static Stream<String> createStreamFromFile() {
        Path path = Paths.get("E:/k/task/src/main/java/com/z/j8/j804/StreamCreate00.java");
        try (Stream<String> lines = Files.lines(path)) {
            System.out.println("------------");
            lines.forEach(System.out::println);
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e); // Unchecked Exception
        }
    }
}
