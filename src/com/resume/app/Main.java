package com.resume.app;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File root =  new File("src/");
        printPacage(root);
    }

    public static void printPacage(File folder) throws IOException {
        System.out.println("Directory: " + folder.getName());
        File[] files =  folder.listFiles();
        if(files != null) {
            for(File file: files) {
                if(file.isDirectory()) {
                    printPacage(file);
                } else {
                    System.out.println("\tFile: " + file.getName());
                }
            }
        }
    }

}
