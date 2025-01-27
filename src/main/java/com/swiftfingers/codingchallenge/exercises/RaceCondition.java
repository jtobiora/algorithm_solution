package com.swiftfingers.codingchallenge.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Counter counter = new Counter();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> counter.increment());
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        System.out.println("Final count is " + counter.getCount());

    }



    static class Counter {
        int count = 0;

        public void increment() {
            count = count + 1;
        }

        public int getCount() {
            return count;
        }
    }


}
