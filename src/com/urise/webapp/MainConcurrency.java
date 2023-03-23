package com.urise.webapp;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainConcurrency {
//    public static final Object LOCK = new Object();
//    public static final Lock lock = new ReentrantLock();
    public static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    public static final Lock READ_LOCK = reentrantReadWriteLock.readLock();
    public static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();

    public static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        }
    };

    public static final int THREADS_NUMBER = 10000;
    private static int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " " + getState());
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState())
        ).start();

        System.out.println(thread0.getState());


        final MainConcurrency mainConcurrency = new MainConcurrency();

        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
//            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")));
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                }
                latch.countDown();
                return 5;
            });
//            System.out.println(future.isDone());
//            System.out.println(future.get());
//            thread.start();
//            threads.add(thread);
        }
//        threads.forEach(thread -> {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(counter);
        System.out.println(mainConcurrency.atomicCounter.get());
    }

    private void inc() {
//        double cos = Math.cos(13);
//        synchronized (LOCK) {
//            lock.lock();
//            try {
//                counter++;
//            } finally {
//                lock.unlock();
//            }
//
////        }
        atomicCounter.incrementAndGet();
    }
}
