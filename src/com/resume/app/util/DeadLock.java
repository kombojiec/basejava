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
            String threadName = Thread.currentThread().getName();
            System.out.printf("Try to catch %s by %s\n", lockOne.toString(), threadName);
            synchronized (lockOne) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s caught by %s\n", lockOne.toString(), threadName);
                System.out.printf("Try to catch %s by %s\n", lockTwo.toString(), threadName);
                synchronized ((lockTwo)) {
                    System.out.printf("%s and %s caught by %s\n", lockOne.toString(), lockTwo.toString(), threadName);
                }
            }
        }).start();
    }

}



