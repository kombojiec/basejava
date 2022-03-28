package com.resume.app;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        File root =  new File("src/");
        printFilesHierarchy(root, 0);


    }

    public static void printFilesHierarchy(File src, int depth) {
        if(null == src) {
            return;
        }
        for(File file: src.listFiles()) {
            if(file.isDirectory()) {
                System.out.print(file.toString().indent(depth + 4));
                printFilesHierarchy(file, depth + 4);
            } else {
                System.out.print(file.toString().indent(depth + 4));
            }
        }
    }

    public static int minValue(int[] values) {
        Optional<Integer> result = Arrays.stream(values)
                .distinct()
                .boxed()
                .sorted()
                .reduce((a, b) -> (a * 10) + b);
        if(result.isPresent()) {
            return result.get();
        } else throw new IllegalArgumentException("Incorrect data");
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int result = integers.stream().reduce(Integer::sum).get();

        return integers.stream()
                .filter(el -> (result % 2 == 0 && el % 2 != 0) || (result % 2 != 0 && el % 2 == 0))
                .collect(Collectors.toList());
    }

}
