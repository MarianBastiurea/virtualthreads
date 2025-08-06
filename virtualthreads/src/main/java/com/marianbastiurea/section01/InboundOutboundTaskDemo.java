package com.marianbastiurea.section01;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {
    private static final int MAX_PLATFORM = 10;
    private static final int MAX_VIRTUAL = 20;

    public static void main(String[] args) throws InterruptedException {
        virtualThreadDemo();
    }

    /*
    to create a simple Java thread
     */
    public static void platformThreadDemo1() {
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = new Thread(() -> Task.ioIntensive(j));
            thread.start();
        }
    }

    /*
    to create threads using Thread.builder
     */
    public static void platformThreadDemo2() {
        var builder = Thread.ofPlatform().name("Marian", 1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> Task.ioIntensive(j));
            thread.start();
        }
    }

    /*
    to create daemon threads using Thread.builder
     */
    public static void platformThreadDemo3() throws InterruptedException {
        var latch = new CountDownLatch(MAX_PLATFORM);
        var builder = Thread.ofPlatform().daemon().name("Marian", 1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = builder.unstarted(
                    () -> {
                        Task.ioIntensive(j);
                        latch.countDown();
                    });
            thread.start();
        }
        latch.await();
    }

    /*
    to create virtual threads using Thread.builder
    virtual threads are daemon threads by default
    virtual threads doesn't have any default name
     */
    public static void virtualThreadDemo() throws InterruptedException {
        var latch = new CountDownLatch(MAX_VIRTUAL);
        var builder = Thread.ofVirtual().name("virtual-",1);
        for (int i = 0; i < MAX_VIRTUAL; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }
}
