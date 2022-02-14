package com.resume.app.util;

public class DeadLock {
    public static final Object lockOne = new Object();
    public static final Object lockTwo = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println("Try to catch lockOne by thread-0");
            synchronized (lockOne) {
                System.out.println("lockOne caught by thread-0");
                System.out.println("Try to catch lockTwo by thread-0");
                synchronized ((lockTwo)) {
                    System.out.println("lockOne and lockTwo caught by thread-0");
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println("Try to catch lockTwo by thread-1");
            synchronized (lockTwo) {
                System.out.println("lockTwo caught by thread-1");
                System.out.println("Try to catch lockOne by thread-1");
                synchronized ((lockOne)) {
                    System.out.println("lockTwo and lockOne caught by thread-1");
                }
            }
        }).start();

    }
}

