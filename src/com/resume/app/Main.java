package com.resume.app;

import java.io.File;
import java.io.IOException;


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
}
