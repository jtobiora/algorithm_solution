package com.swiftfingers.codingchallenge.exercises;

import org.w3c.dom.css.Counter;

import java.util.Arrays;
import java.util.PriorityQueue;
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

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(9);
        queue.add(6);
        queue.add(7);

        System.out.println(queue.peek());

        firstMissingPositive();
    }

    //Given an unsorted integer array nums, return the smallest missing positive integer.
    public static int firstMissingPositive () {
        int [] nums = {1,-1,5,4,2};
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int lowest = 0;
        int lowestIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1) continue;

            if (i + 1 < nums.length) {
                int currentElement = nums[i];
                int nextElement = nums[i + 1];

                int sub = nextElement - currentElement;
                System.out.println("sub element = " + sub);
                if (sub < min){
                    min = sub;
                    lowestIndex = i;
                }
            }
        }

        System.out.println("First missing positive number: " + (nums[lowestIndex + 1]));
        return 1;
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
