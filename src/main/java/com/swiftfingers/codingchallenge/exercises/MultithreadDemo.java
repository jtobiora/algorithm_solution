package com.swiftfingers.codingchallenge.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        System.out.println("Creating a runnable task");
        Runnable runnable = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());
        };

        service.submit(runnable);

        service.shutdownNow();
    }
}
