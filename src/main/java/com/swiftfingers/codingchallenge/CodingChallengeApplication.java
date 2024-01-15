package com.swiftfingers.codingchallenge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class CodingChallengeApplication {

	public static void main(String[] args) {

		List<Interval> intervalsList = new ArrayList<>();
		intervalsList.add(new Interval(1, 3));
		intervalsList.add(new Interval(2, 6));
		intervalsList.add(new Interval(8, 10));
		intervalsList.add(new Interval(15, 18));

		List<Interval> mergedIntervalsList = AlgorithmSolutions.mergeIntervals(intervalsList);

		    // Print the merged intervals
		log.info("Printing merged intervals");
		mergedIntervalsList.stream().forEach(interval -> System.out.println("(" + interval.getStart() + ", " + interval.getEnd() + ")"));



		int[] arr =  {1, 2, 3, 4};
		int maximumXOR = AlgorithmSolutions.findMaximumXORSubArray(arr);
		log.info("The Maximum XOR of the subarray is : {}" ,maximumXOR);
	}

}
