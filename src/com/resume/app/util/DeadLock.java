package com.resume.app.util;

public class DeadLock {
    public static final Object lockOne = new Object();
    public static final Object lockTwo = new Object();

    public static void main(String[] args) {

        initThread(lockOne, lockTwo);
        initThread(lockTwo, lockOne);

    }

    public static void initThread(Object lockOne, Object lockTwo) {
        new Thread(() -> {
            Thread thread = Thread.currentThread();
            System.out.println("Try to catch lockOne by " + thread.getName());
            synchronized (lockOne) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lockOne caught by " + thread.getName());
                System.out.println("Try to catch lockTwo by " + thread.getName());
                synchronized ((lockTwo)) {
                    System.out.println("lockOne and lockTwo caught by " + thread.getName());
                }
            }
        }).start();
    }

}



