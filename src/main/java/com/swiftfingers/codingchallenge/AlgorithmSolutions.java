package com.swiftfingers.codingchallenge;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class AlgorithmSolutions {

    /*
    * The mergeIntervals method takes a list of intervals as input and returns a list of merged
    * intervals.
    * */
    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        List<Interval> mergedIntervals = new LinkedList<>(); //to preserve order of insertion

        //edge case - check for empty input list
        if (intervals == null || intervals.isEmpty()) {
            log.info("Input list is empty. There are no intervals to merge.");
            return mergedIntervals;
        }

        //Initialize a currentInterval with the first interval in the list
        Interval currentInterval = intervals.get(0);

        //Iterate through the remaining intervals starting from the second one.
        for (int i = 1; i < intervals.size(); i++) {

            Interval nextInterval = intervals.get(i); //Get the next interval.

            //Check if the current interval and the next interval overlap.
            if (currentInterval.getEnd() >= nextInterval.getStart()) {
                // Overlapping intervals, merge them
                currentInterval.setEnd(Math.max(currentInterval.getEnd(), nextInterval.getEnd()));
            } else {
                // Non-overlapping intervals, add the merged interval to the result and reset the current interval
                mergedIntervals.add(currentInterval);
                currentInterval = nextInterval;
            }
        }

        // Add the last merged interval
        mergedIntervals.add(currentInterval);

        return mergedIntervals;
    }


    /*
    * Code implementation to find the maximum XOR value of a subarray in a given integer array. The XOR
    * (exclusive OR) operation is a bitwise operation that returns 1 for each bit where the operands have
    * different values and 0 when the operands have the same values
    * */

    public static int findMaximumXORSubArray (int[] arr) {
        int maxXOR = Integer.MIN_VALUE; // to store the max XOR value

        // Loop through to get subarrays and take starting points
        for (int i = 0; i < arr.length; i++) {

            // to store XOR of current subarray
            int currentXOR = 0;

            // Pick ending points of subarrays starting with i
            for (int j = i; j < arr.length; j++) {
                currentXOR = currentXOR ^ arr[j];
                maxXOR = Math.max(maxXOR, currentXOR);
            }
        }
        return maxXOR;
    }
}


