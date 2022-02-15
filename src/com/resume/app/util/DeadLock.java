package com.resume.app.util;

public class DeadLock {
    public static final Object lockOne = new Object();
    public static final Object lockTwo = new Object();

    public static void main(String[] args) {

        new Thread(new ThreadOne(), "threadOne").start();
        new Thread(new ThreadTwo(), "threadTwo").start();

    }
}

class ThreadOne implements Runnable {
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("Try to catch lockOne by " + thread.getName());
        synchronized (DeadLock.lockOne) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lockOne caught by " + thread.getName());
            System.out.println("Try to catch lockTwo by " + thread.getName());
            synchronized ((DeadLock.lockTwo)) {
                System.out.println("lockOne and lockTwo caught by " + thread.getName());
            }
        }
    }
}

class ThreadTwo implements Runnable {
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("Try to catch lockTwo by " + thread.getName());
        synchronized (DeadLock.lockTwo) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lockTwo caught by " + thread.getName());
            System.out.println("Try to catch lockOne by " + thread.getName());
            synchronized ((DeadLock.lockOne)) {
                System.out.println("lockTwo and lockOne caught by " + thread.getName());
            }
        }
    }
}

