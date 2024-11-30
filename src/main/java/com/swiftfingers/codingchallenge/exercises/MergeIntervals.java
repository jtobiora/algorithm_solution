package com.swiftfingers.codingchallenge.exercises;

import java.util.ArrayList;
import java.util.List;

public class MergeIntervals {
    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        List<Interval> mergedIntervals = new ArrayList<>();

        if (intervals == null || intervals.isEmpty()) {
            return mergedIntervals;
        }

        Interval currentInterval = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval nextInterval = intervals.get(i);


            if (currentInterval.end >= nextInterval.start) {
                // Overlapping intervals, merge them
                currentInterval.end = Math.max(currentInterval.end, nextInterval.end);
            } else {
                // Non-overlapping intervals, add the merged interval to the result
                mergedIntervals.add(currentInterval);
                currentInterval = nextInterval;
            }
        }

        // Add the last merged interval
        mergedIntervals.add(currentInterval);

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(15, 18));

        List<Interval> result = mergeIntervals(intervals);

        // Print the merged intervals
        for (Interval interval : result) {
           System.out.println("(" + interval.start + ", " + interval.end + ")");
        }
    }
}
