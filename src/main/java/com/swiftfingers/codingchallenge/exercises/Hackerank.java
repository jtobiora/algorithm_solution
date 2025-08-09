package com.swiftfingers.codingchallenge.exercises;



import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Hackerank {

    public static void main(String[] args) {
        binaryNumbers(125);
        compareTriplets(List.of(11,20,32), List.of(21,33,12));
        System.out.println( divisibleSumPairs(6, 3, List.of(1, 3, 2, 6, 1, 2)));
        sockMerchant(9, List.of(10, 20, 20, 10, 10, 30, 50, 10, 20));
        miniMaxSum(Arrays.asList(1, 2, 3, 4, 5));
        gradingStudents(List.of(73, 67, 38, 33));
        saveThePrisoner(7, 19, 2);
        beautifulDays(20, 23, 6);
        minimumDistances(new int[]{7, 1, 3, 4, 1, 7});
        equalizeArray(Arrays.asList(3, 3, 2, 1, 3));
        consecutive1sInBinary(13);
        strangeCounter(7);
        adjacentList();
        superReducedString("aaabccddd");
        plusMinus(Arrays.asList(-4, 3, -9, 0, 4, 1));
        timeConversion("12:00:00am");
        matchingStrings(Arrays.asList("ab","ab","abc"), Arrays.asList("ab","abc","bc"));
        diagonalDifference();
        checkPangrams("the quick brown fox jumps over the lazy dog");
        rotateLeft(2, Arrays.asList(1,2,3,4,5));
        closestNumbers(Arrays.asList(5,2,3,1,4));
        minimumAbsoluteDifference(Arrays.asList(5,2,3,1,4));
        isStringSorted("abtsfefhs");
        gridChallenge(Arrays.asList("abc","ade", "efg"));
        balancedSums(Arrays.asList(5,6,8,11));
        findMissingNumbers(Arrays.asList(203,204,205,206,207,208,203,204,205,206), Arrays.asList(203,204,204,205,206,207,205,208,203,206,205,206,204));
        gamingArray(Arrays.asList(5,2,6,3,4));
        superDigit("9875", 4);
        counterGame(6);
        rotateLeft(Arrays.asList(1,2,3,4,5), 2);
        sherlockAndAnagrams("abba");
        maximumToys(Arrays.asList(1, 12, 5, 111, 200, 1000, 10), 50);
        minimumLoss(Arrays.asList(20, 15, 8, 2, 12));
        commonChild("HARRY", "SALLY");
        specialSubstringCount(8, "mnonopoo");
        isValid("abcc");
        minDeletions("AAABBB");
        triplets(Arrays.asList(3,5,7), Arrays.asList(3,6), Arrays.asList(4,6,9));
        birthdayCakeCandles(List.of(4,4,1,3));
        missingNumbers(Arrays.asList(203,204,205,206,207,208,203,204,205,206), Arrays.asList(203, 204, 204, 205, 206, 207, 205, 208, 203, 206, 205, 206, 204));
        breakingRecords(Arrays.asList(3,4,21,36,10,28,35,5,24,42));
        birthday(Arrays.asList(1,1,1,1,1,1), 3, 2);
        migratoryBirds(Arrays.asList(1,2,3,4,5,4,3,2,1,3,4));
        bonAppetit(Arrays.asList(3,10,2,9),1,7);
        climbingLeaderboard(Arrays.asList(100,100,50,40,40,20,10), Arrays.asList(5,25,50,120));
        hurdleRace(4, Arrays.asList(1,6,3,5,2));
        circularArrayRotation();
        findDigits(124);
        squares(24, 49);
    }

    public static int reverseInt(int num) {
        boolean negative = num < 0;
        String reversed = new StringBuilder(String.valueOf(Math.abs(num)))
                .reverse()
                .toString();
        int result = Integer.parseInt(reversed);
        return negative ? -result : result;
    }

    /**
     * Task
     * Given a base-10 integer, convert it to binary (base-2). Then find and print the base- integer denoting the maximum number of
     * consecutive 1's in 's binary representation. When working with different bases, it is common to show the base as a subscript.
     * The binary representation of  125 is 1111101. In base 10, there are 5 and 1   consecutive ones in two groups. Print the maximum.
     * The binary representation of  13 is 1101, so the maximum number of consecutive 1's is 2.
     * */
    public static void binaryNumbers (int num) {
        char[] binary = Integer.toBinaryString(num).toCharArray(); //convert to binary
        int count = 0;
        List<Integer> listCounter = new ArrayList<>();
        for (int x =0; x < binary.length; x++) {
            char c = binary[x];
            if (Integer.parseInt(String.valueOf(c)) != 1) {
                if (count != 0) {
                    listCounter.add(count); //add the count to the list
                    count = 0; //clear count
                }
            } else {
                count++;
            }
        }

        System.out.println("Binary numbers count of consecutive 1's >>>>>> " + listCounter);
    }

//    Alice and Bob each created one problem for HackerRank. A reviewer rates the two challenges, awarding points on a scale from
//    1 to 100 for three categories: problem clarity, originality, and difficulty.
//    The rating for Alice's challenge is the triplet a = (a[0], a[1], a[2]), and the rating for Bob's challenge is the triplet b = (b[0], b[1], b[2]).
//    The task is to find their comparison points by comparing a[0] with b[0], a[1] with b[1], and a[2] with b[2].
//    If a[i] > b[i], then Alice is awarded 1 point.
//    If a[i] < b[i], then Bob is awarded 1 point.
//    If a[i] = b[i], then neither person receives a point.
//    Comparison points is the total points a person earned.
    public static List<Integer> compareTriplets(List<Integer> aliceList, List<Integer> bobList) {
        int aliceScore = 0;
        int bobScore = 0;

        for (int x = 0; x < aliceList.size(); x++) {
            if (aliceList.get(x) > bobList.get(x))
                aliceScore++;
            else if (aliceList.get(x) < bobList.get(x))
                bobScore++;
        }

        return Arrays.asList(aliceScore, bobScore);
    }

   // Given an array ar of integers and a positive integer k, determine the number of (i,j) pairs where i < j and ar[i] + ar[j]  is divisible by k.
    public static int divisibleSumPairs (int n, int k, List<Integer> ar) {
        int pairCount = 0;
        for (int x = 0; x < ar.size(); x++) {
            for (int y = x + 1; y < ar.size(); y++) {
               if (x < y) {
                   double res = (ar.get(x) + ar.get(y)) % k;
                   if (res == 0) {
                       pairCount++;
                   }
               }
            }
        }

        return pairCount;
    }

    //There is a large pile of socks that must be paired by color. Given an array of integers representing the color of each sock,
    // determine how many pairs of socks with matching colors there are.
    public static int sockMerchant(int n, List<Integer> ar) {
        // Write your code here
        Map<Integer, Integer> sockMap = new HashMap<>();
        int pairsCounter = 0;
        int result = 0;
        for (Integer i: ar) {
            sockMap.put(i, sockMap.containsKey(i) ? sockMap.get(i) + 1 : 1);
        }

        for (Map.Entry<Integer, Integer> m : sockMap.entrySet()) {
            Integer value = m.getValue();
            if (value > 1) {
                result = value /2;
                pairsCounter  += result;
            }
        }

        System.out.println("The number of pairs is " + pairsCounter);

        return pairsCounter;
    }

    //https://www.hackerrank.com/challenges/three-month-preparation-kit-mini-max-sum/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-one
    //Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the
    // five integers. Then print the respective minimum and maximum values as a single line of two space-separated long integers.
    public static void miniMaxSum(List<Integer> arr) {
        List<Integer> numList = new ArrayList<>();
        for (int x = 0; x < arr.size(); x++) {
            int finalX = x;
            int sum = arr.stream()
                    .filter(value -> !Objects.equals(value, arr.get(finalX))) // Exclude the current element
                    .mapToInt(Integer::intValue)
                    .sum();

            numList.add(sum);
        }

        Collections.sort(numList);
        System.out.println("Minimum Element " + numList.get(0) + " , " + " Maximum element " + numList.get(arr.size() - 1));

//        Collections.sort(arr);
//        int max = arr.get(arr.size() - 1);
//        int min = arr.get(0);
//
//        int sum = arr.stream().mapToInt(Integer::intValue).sum();
//        int maxSum = sum - min;
//        int minSum = sum - max;
//
//        System.out.println("Minimum Element = " + minSum + " , " + " Maximum element = " + maxSum);
    }

//HackerLand University has the following grading policy:
//Every student receives a grade in the inclusive range from 0 to 100.
//Any grade less than 40 is a failing grade.
//Sam is a professor at the university and likes to round each student's grade according to these rules:
//If the difference between the grade and the next multiple of 5 is less than 3, round grade up to the next multiple of 5.
//If the value of grade is less than 38, no rounding occurs as the result will still be a failing grade.
    public static List<Integer> gradingStudents(List<Integer> grades) {
        List<Integer> newNumList = new ArrayList<>();
        for (int gr : grades) {
            if (gr < 38) //failing grade .. no rounding occurs
                newNumList.add(gr);
            else {
               int div = gr / 5;
               int nextMultipleOf5 = (div + 1) * 5; // int nextMultipleOf5 = (int) (Math.ceil(gr / 5.0) * 5);
               int sub = nextMultipleOf5 - gr;
               if (sub < 3) {
                   newNumList.add(nextMultipleOf5);
               } else {
                   newNumList.add(gr);
               }
            }
        }
        return newNumList;
    }

    public static List<Integer> gradingStudents2(List<Integer> grades) {
        List<Integer> roundedGrades = new ArrayList<>();

        for (int grade : grades) {
            if (grade < 38) {
                // No rounding for failing grades (< 38)
                roundedGrades.add(grade);
            } else {
                int nextMultipleOf5 = (int) (Math.ceil(grade / 5.0) * 5);
                if (nextMultipleOf5 - grade < 3) {
                    roundedGrades.add(nextMultipleOf5);
                } else {
                    roundedGrades.add(grade);
                }
            }
        }

        return roundedGrades;
    }

    //https://www.hackerrank.com/challenges/save-the-prisoner/problem
    public static int saveThePrisoner(int prisoners, int numberOfSweets, int startPosition) {
        List<Integer> numsList = new ArrayList<>();
        int[] arrSweets = new int[numberOfSweets];
        int[] arrPrisoners = IntStream.rangeClosed(1, prisoners).toArray();
        startPosition = startPosition - 1;
        for (int x = 0; x < arrSweets.length; x++) {
            if (startPosition > arrPrisoners.length - 1) {
                startPosition = 0;
            }
            numsList.add(arrPrisoners[startPosition]);
            startPosition++;
        }

        return numsList.get(numsList.size() - 1);
    }

    //https://www.hackerrank.com/challenges/beautiful-days-at-the-movies/problem
    public static int beautifulDays(int startDay, int endDay, int divisor) {
        //generate the days inclusive
        List<Integer> beautifulDays = new ArrayList<>();
        int[] days = IntStream.rangeClosed(startDay, endDay).toArray(); //get the days inclusive
        for (int num : days) {
            int reversedNum = reverseNum(num);

            int div = Math.abs(num - reversedNum) % divisor; //check if there is a remainder
            if (div == 0) { //no remainder
                //it is a beautiful day
                beautifulDays.add(num);
            }
        }
        System.out.println("Beautiful days " + beautifulDays);
        return beautifulDays.size();
    }

    //https://www.hackerrank.com/challenges/minimum-distances/problem
    public static int minimumDistances(int[] arr) {
        Map<Integer, Integer> lastSeenIndex = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (lastSeenIndex.containsKey(arr[i])) {
                int prevIndex = lastSeenIndex.get(arr[i]);
                minDistance = Math.min(minDistance, i - prevIndex);
            }
            lastSeenIndex.put(arr[i], i); // update last seen index
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    //https://www.hackerrank.com/challenges/equality-in-a-array/problem
    public static int equalizeArray(List<Integer> arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Find the maximum frequency
        int maxFrequency = 0;
       // int max = frequencyMap.values().stream().mapToInt(i -> i).max().getAsInt();
        for (int freq : frequencyMap.values()) {
            maxFrequency = Math.max(maxFrequency, freq);
        }


        // Minimum deletions = total elements - frequency of the most common element
        return arr.size() - maxFrequency;

    }

    //https://www.hackerrank.com/challenges/linkedin-practice-binary-numbers/problem
    public static void consecutive1sInBinary (int num) {
        char[] charArray = Integer.toBinaryString(num).toCharArray();
        int maxCount = 0;
        int counter  = 0;
        for (char c : charArray) {
            int i = Integer.parseInt(String.valueOf(c));
            if (i == 1) {
                counter++;
            } else {
                if (maxCount < counter) {
                    maxCount = counter;
                }
                counter = 0;
            }
        }

        System.out.println("Maximum number of consecutive 1's is = " + maxCount);
    }

    //https://www.hackerrank.com/challenges/strange-code/problem
    public static long strangeCounter(long t) {
       int loopChecker = 1;
       int counterDisplay = 3;
       int displayTracker = counterDisplay;
       while (loopChecker < t)  {
           if (counterDisplay == 1) {
               counterDisplay = 2 * displayTracker;
               displayTracker = counterDisplay;
           }
           counterDisplay--;
           loopChecker++;

           if (loopChecker == t) {
               System.out.println("Counter display " + counterDisplay);
           }
       }

       return 0;
    }

    //Remove all adjacent numbers in a list whose difference is equal to 1
    public static void adjacentList() {
        List<Integer> list = Arrays.asList(0, 1, 2, 11); // Input list
        List<Integer> result = new ArrayList<>(); // Auxiliary list to store the filtered result

        int i = 0;
        while (i < list.size()) {
            // Check if the current element and the next element have a difference of 1
            if (i + 1 < list.size() && Math.abs(list.get(i) - list.get(i + 1)) == 1) {
                // Skip both elements
                i += 2;
            } else {
                // Add the current element to the result
                result.add(list.get(i));
                i++;
            }
        }

        System.out.println("Filtered result :::: " +result);
    }

    //https://www.hackerrank.com/challenges/reduced-string/problem
    public static String superReducedString(String s) {
        // Convert the input string to a mutable StringBuilder
        StringBuilder str = new StringBuilder(s);

        int i = 0;
        while (i < str.length() - 1) {
            // If a pair of adjacent characters match, remove them
            if (str.charAt(i) == str.charAt(i + 1)) {
                str.delete(i, i + 2); // Remove the matching pair
                // Move back one step to check for new adjacent matches
                i = Math.max(0, i - 1);
            } else {
                // Move to the next character if no match
                i++;
            }
        }

        // Return "Empty String" if the result is empty, otherwise the reduced string
        return str.length() == 0 ? "Empty String" : str.toString();
    }

    //https://www.hackerrank.com/challenges/reduced-string/problem
    public static String superReducedString2(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop(); // Remove the matching pair
            } else {
                stack.push(ch);
            }
        }

        // Build the result from the stack
        StringBuilder result = new StringBuilder();
        for (char ch : stack) {
            result.append(ch);
        }

        return result.length() == 0 ? "Empty String" : result.toString();
    }

    //https://www.hackerrank.com/challenges/three-month-preparation-kit-plus-minus/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-one
    public static void plusMinus(List<Integer> arr) {
       Map<Integer, Integer> map = new HashMap<>();
       int positiveCount = 0;
       int zeroCount = 0;
       int negativeCount = 0;
       for (Integer l: arr) {
          if (l > 0)
              positiveCount++;
          else if (l < 0)
              negativeCount++;
          else
              zeroCount++;
       }

        double positiveProportion = BigDecimal.valueOf(positiveCount)
                .divide(BigDecimal.valueOf(arr.size()), 6, RoundingMode.HALF_UP)
                .doubleValue();
        double negativeProportion = BigDecimal.valueOf(negativeCount)
                .divide(BigDecimal.valueOf(arr.size()), 6, RoundingMode.HALF_UP)
                .doubleValue();
        double zeroProportion = BigDecimal.valueOf(zeroCount)
                .divide(BigDecimal.valueOf(arr.size()), 6, RoundingMode.HALF_UP)
                .doubleValue();
        System.out.printf("%.6f%n", positiveProportion);
        System.out.printf("%.6f%n", negativeProportion);
        System.out.printf("%.6f%n", zeroProportion);

    }

    //Given a time in 12-hour AM/PM format, convert it to military (24-hour) time.
    //
    //Note: - 12:00:00AM on a 12-hour clock is 00:00:00 on a 24-hour clock.
    //- 12:00:00PM on a 12-hour clock is 12:00:00 on a 24-hour clock.
    public static String timeConversion(String time) {
        int baseAMTime = 0;
        int basePMTime = 12;
        String newTime = null;
        String s = time.split(":")[0];
        if (time.endsWith("am")) {
            int i = baseAMTime + Integer.parseInt(s);
            newTime = i + time.substring(2);
        } else if (time.endsWith("pm")) {
            int i = basePMTime + Integer.parseInt(s);
            newTime =i + time.substring(2);
        }

        System.out.println("New Time >>>>>>>>>>>>>>> " + newTime);
        return newTime;
    }

    //https://www.hackerrank.com/challenges/three-month-preparation-kit-sparse-arrays/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-one
    public static List<Long> matchingStrings(List<String> strings, List<String> queries) {
        //group the string
        Map<String, Long> groupedString = strings.stream().collect(Collectors.groupingBy(
                num -> num,
                Collectors.counting()
        ));
        List<Long> occurrences = new ArrayList<>();
        for (String q : queries) {
            if (groupedString.get(q) == null) {
                occurrences.add(0L);
                continue;
            }
            occurrences.add(groupedString.get(q));
        }

        System.out.println("to hex " +  Integer.toUnsignedString(9, 10));
        System.out.println("The number of occurences is " +occurrences);
        return occurrences;
    }

    //Given a square matrix, calculate the absolute difference between the sums of its diagonals.
    public static int diagonalDifference() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int primaryDiagonalSum = 0;
        int secondaryDiagonalSum = 0;
        int n = matrix.length; // Size of the square matrix

        for (int i = 0; i < n; i++) {
            primaryDiagonalSum += matrix[i][i]; // Sum for primary diagonal
            secondaryDiagonalSum += matrix[i][n - i - 1]; // Sum for secondary diagonal
        }
        return Math.abs(primaryDiagonalSum - secondaryDiagonalSum);
    }

    public static int diagonalDifference2(List<List<Integer>> arr) {
        int size = arr.size();
        int count = 1;
        int primaryDiagonal = 0;
        int secondaryDiagonal = 0;

        for (int i = 0; i < size; i++) {
//            primaryDiagonal = primaryDiagonal + arr.get(i).get(i);
//            secondaryDiagonal = secondaryDiagonal + arr.get(i).get(size - i - 1);

            primaryDiagonal = primaryDiagonal + arr.get(i).get(count - 1);
            secondaryDiagonal = secondaryDiagonal + arr.get(i).get(size - 1);

            count++;
            size--;
        }
        return Math.abs(primaryDiagonal - secondaryDiagonal);
    }

    public static void checkPangrams(String s) {
        Set<Character> alphabets = new HashSet<>();
        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                alphabets.add(c);
            }
        }

        if (alphabets.size() == 26) {
            System.out.println("The string is pangram");
        } else {
            System.out.println("The string is not a pangram");
        }
    }

    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
       List<Integer> newList =  new ArrayList<>();
       for (int x =0; x < arr.size(); x++) {
           if (x == d)
               break;
           newList.add(arr.get(x));
       }

       List<Integer> sublist = arr.subList(d, arr.size());
       newList.addAll(0, sublist);
       return newList;
    }

    //https://www.hackerrank.com/challenges/three-month-preparation-kit-closest-numbers/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-four
    //Sorting is useful as the first step in many different tasks. The most common task is to make finding things easier, but
    // there are other uses as well. In this case, it will make it easier to determine which pair or pairs of elements have the
    // smallest absolute difference between them.
    public static List<Integer> closestNumbers(List<Integer> arr) {
        Collections.sort(arr);
        int min = Math.abs(arr.get(0) - arr.get(1));
        List<List<Integer>> integerList = new ArrayList<>();
        for (int x = 0; x < arr.size(); x++) {
            for (int y = x + 1; y < arr.size(); y++) {
                int val = Math.abs(arr.get(x) - arr.get(y));
                if (val < min) {
                    min = val;
                    integerList.clear();
                    integerList.add(List.of(arr.get(x), arr.get(y)));
                } else if (val == min) {
                    integerList.add(List.of(arr.get(x), arr.get(y)));
                }
            }
        }

        System.out.println("List of numbers " + integerList);
        return integerList.stream().flatMap(List::stream).collect(Collectors.toList());

    }

    //The absolute difference is the positive difference between two values a and b, is written |a -b| or |b - a| and they are equal.
    // Given an array of integers, find the minimum absolute difference between any two elements in the array.
    public static int minimumAbsoluteDifference(List<Integer> arr) {
            Collections.sort(arr);

            int minDifference = Integer.MAX_VALUE;
            for (int i = 1; i < arr.size(); i++) {
                int difference = Math.abs(arr.get(i) - arr.get(i - 1));
                minDifference = Math.min(minDifference, difference);
            }

            return minDifference;
    }

    //https://www.hackerrank.com/challenges/three-month-preparation-kit-missing-numbers/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-five
//    Given two arrays of integers, find which elements in the second array are missing from the first array.
//    If a number occurs multiple times in the lists, you must ensure that the frequency of that number in both lists is the
//     same. If that is not the case, then it is also a missing number.
//    Return the missing numbers sorted ascending.
//    Only include a missing number once, even if it is missing multiple times.
    public static List<Integer> findMissingNumbers(List<Integer> arr1, List<Integer> arr2) {
        Map<Integer, Integer> freq1 = new HashMap<>();
        Map<Integer, Integer> freq2 = new HashMap<>();

        // Count frequency in arr1
        for (int num : arr1) {
            freq1.put(num, freq1.getOrDefault(num, 0) + 1);
        }

        // Count frequency in arr2
        for (int num : arr2) {
            freq2.put(num, freq2.getOrDefault(num, 0) + 1);
        }

        List<Integer> missing = new ArrayList<>();

        for (int num : freq2.keySet()) {
            int countInArr2 = freq2.get(num);
            int countInArr1 = freq1.getOrDefault(num, 0);
            if (countInArr2 > countInArr1) {
                missing.add(num);
            }
        }

        Collections.sort(missing);
        return missing;
    }

    public static boolean isStringSorted(String str) {
        // Iterate through the string and check if each character is less than or equal to the next one
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) > str.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    //https://www.hackerrank.com/challenges/three-month-preparation-kit-grid-challenge/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-five
    //Given a square grid of characters in the range ascii[a-z], rearrange elements of each row alphabetically, ascending.
    // Determine if the columns are also in ascending alphabetical order, top to bottom. Return YES if they are or NO if they are not.
    public static String gridChallenge(List<String> grid) {
        int n = grid.size();
        int m = grid.get(0).length();

        // Step 1: Sort each row alphabetically
        List<String> sortedGrid = new ArrayList<>();
        for (String row : grid) {
            char[] rowArray = row.toCharArray();
            Arrays.sort(rowArray);  // Sort the row alphabetically
            sortedGrid.add(new String(rowArray));  // Convert back to string
        }

        // Step 2: Check if each column is sorted
        //move through the columns
        for (int col = 0; col < m; col++) {
            //move through the rows and compare the character of the column of each row
            for (int row = 1; row < n; row++) {
                if (sortedGrid.get(row).charAt(col) < sortedGrid.get(row - 1).charAt(col)) {
                    return "NO";  // If column is not sorted, return "NO"
                }
            }
        }

        // If all columns are sorted, return "YES"
        return "YES";
    }

    //https://www.hackerrank.com/challenges/three-month-preparation-kit-sherlock-and-array/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-six
    //Watson gives Sherlock an array of integers. His challenge is to find an element of the array such that the sum of all
    // elements to the left is equal to the sum of all elements to the right.
    public static String balancedSums (List<Integer> arr) {
       //loop through the array
        for (int x = 0; x < arr.size(); x++) {
            if (x == 0) continue; //skip the first element
            if (x ==  arr.size() - 1) return "NO"; //if the last element is reached, no more comparison
            int leftSum = arr.subList(0, x).stream().mapToInt(Integer::intValue).sum(); //get sum of the left from the current index
            int rightSum = arr.subList(x + 1, arr.size()).stream().mapToInt(Integer::intValue).sum(); //get sum of the right from the current index

            if (leftSum == rightSum) {
                return "YES";
            }
        }

        return null;
    }

    //https://www.hackerrank.com/challenges/three-month-preparation-kit-an-interesting-game-1/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-six
    public static String gamingArray(List<Integer> arr) {
        int playTurns = 1; //Bob is the first to play so he will always have an odd count. If the playTurns returned is odd, then
        //Bob wins else Andy wins (Bob will have 1,3,5,7, etc turns i.e odd turns. Andy will have 2,4,6, etc turns i.e even turns
        while (arr.size() > 1) {
            int max = arr.stream().mapToInt(Integer::intValue).max().getAsInt();
            int index = arr.indexOf(max);
            List<Integer> subList = arr.subList(0, index);
                //index of the max is the last item
            if (index == arr.size() - 1) {
               subList = arr.subList(0, index - (arr.size() - 1));
            }
            arr = subList;
            playTurns++;
        }

        String winner = playTurns % 2 == 0 ? "ANDY" : "BOB";
        System.out.println("Winner of the game " + winner);
        return winner;
    }

    //https://www.hackerrank.com/challenges/three-month-preparation-kit-recursive-digit-sum/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-six
    public static int superDigit (String n, int k) {
        StringBuilder builder = new StringBuilder();
//        builder.append(String.valueOf(n).repeat(Math.max(0, k)));
        for (int x =0; x< k; x++) {
            builder.append(n);
        }
        int superDigit = builder.toString().length();
        String val = builder.toString();
        while (superDigit > 1) {
            int sum = 0;
            for (char c : val.toCharArray()) {
                sum += Integer.parseInt(String.valueOf(c));
            }
            val = String.valueOf(sum);
            superDigit = val.length();
        }

        return Integer.parseInt(val);
    }

    //https://www.hackerrank.com/challenges/three-month-preparation-kit-counter-game/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-six
    //Louise and Richard have developed a numbers game. They pick a number and check to see if it is a power of .
    // If it is, they divide it by . If not, they reduce it by the next lower number which is a power of . Whoever reduces the number to  wins the game. Louise always starts.
    public static String counterGame(long n) {
        long counter = n;
        if (n == 1) return "Richard"; //Richard wins

        while (counter != 1) {
            if (isPowerOfTwo(counter)) {
                //if counter is a power of 2, then divide the counter by 2 and assign back to counter
                counter = counter / 2;
            } else {
               //if counter is NOT a power of 2, then get the power of 2 before the counter
                long tracker = 1;
                int power = 1;
                while (Math.pow(2, power) < counter) {
                    long pow = (long) Math.pow(2, power);
                    tracker = pow;
                    power++;
                }
                long num = counter - tracker;
                counter = num;
            }
        }

        return null;
    }

    //https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
    public static List<Integer> rotateLeft(List<Integer> arr, int d) {
        List<Integer> result = new ArrayList<>();

        // Get the sublist from the position 'd' to the end
        List<Integer> listPart1 = arr.subList(d, arr.size());

        // Get the sublist from the start to the position 'd'
        List<Integer> listPart2 = arr.subList(0, d);

        result.addAll(listPart1);
        result.addAll(listPart2);
        return result;
    }

    //https://www.hackerrank.com/challenges/two-strings/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
    //Given two strings, determine if they share a common substring. A substring may be as small as one character.
    public static String commonSubstring(String s1, String s2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : s1.toCharArray()) {
            set1.add(c);
        }

        for (char c : s2.toCharArray()) {
            set2.add(c);
        }

        // Check if there is any intersection (common character) between the two sets
        //retainAll() modifies the calling collection (set1) by removing all elements that are not present in the specified collection.
        set1.retainAll(set2);

        return set1.isEmpty() ? "NO" : "YES";
    }

    //https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
    //Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string. Given a
    // string, find the number of pairs of substrings of the string that are anagrams of each other.
    public static int sherlockAndAnagrams(String s) {
        Map<String, List<Integer>> map = new HashMap<>();
        Set<String> anagramsList = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (map.containsKey(sub)) {
                    map.get(sub).add(i);
                } else {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(i);
                    map.put(sub, newList);
                }
            }
        }

        for (Map.Entry<String, List<Integer>> entrySet : map.entrySet()) {
            String key = entrySet.getKey();
            List<Integer> value = entrySet.getValue();
            if (key.length() == 1 && value.size() == 2) {
                //consider the string as an Anagram
                anagramsList.add(key);
            }

            if (key.length() > 1) {
                //reverse the key
                String reversedKey = String.valueOf(new StringBuilder(key).reverse());
                List<Integer> reversedKeyValue = map.get(reversedKey);
                if (reversedKeyValue != null) {
//                    //check if both strings are Anagrams
                    if(isAnagram(key, reversedKey) && key.length() != s.length()){
                        anagramsList.add(key);
                        anagramsList.add(reversedKey);
                    }
                }
            }
        }

        System.out.println("The list of pairs of strings that are Anagrams is " + anagramsList);
        return 0;
    }

    //https://www.hackerrank.com/challenges/mark-and-toys/problem
    public static int maximumToys(List<Integer> prices, int budget) {
        // Step 1: Sort the prices in ascending order
        Collections.sort(prices);

        int count = 0; // Number of toys Mark can buy
        int totalCost = 0; // Running total cost

        // Step 2: Buy toys within the budget
        for (int price : prices) {
            if (totalCost + price <= budget) {
                totalCost += price;
                count++;
            } else {
                break; // Stop buying when the budget is exceeded
            }
        }

        return count;
    }

    //Lauren has a chart of distinct projected prices for a house over the next several years. She must buy the house
    // in one year and sell it in another, and she must do so at a loss. She wants to minimize her financial loss.
    //https://www.hackerrank.com/challenges/minimum-loss/problem
    public static int minimumLoss(List<Integer> price) {
        int minimumLoss = Integer.MAX_VALUE;
        for (int x = 0; x < price.size(); x++) {
            for (int y = x +1; y < price.size(); y++) {
                if (price.get(x) > price.get(y)) {
                    int diff = price.get(x) - price.get(y);
                    if (diff < minimumLoss) {
                        minimumLoss = diff;
                    }
                }

            }
        }

        System.out.println("The minimum loss is " + minimumLoss);
        return minimumLoss;
    }

    //https://www.hackerrank.com/challenges/common-child/problem
    //A string is said to be a child of a another string if it can be formed by deleting 0 or more characters from the
    // other string. Letters cannot be rearranged. Given two strings of equal length, what's the longest string that
    // can be constructed such that it is a child of both?
    public static int commonChild(String s1, String s2) {
        Set<Character> set = new HashSet<>();
        List<Character> listS1 = s1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        for (char c: s2.toCharArray()) {
            if (listS1.contains(c)) {
                set.add(c);
            }
        }

        System.out.println("The common child is of length = " + set.size());
        return set.size();
    }

    //https://www.hackerrank.com/contests/w32/challenges/special-substrings
    // A string is said to be a special string if either of two conditions is met:
    //All of the characters are the same, e.g. aaa.
    //All characters except the middle one are the same, e.g. aadaa.
    //A special substring is any substring of a string which meets one of those criteria. Given a string, determine
    // how many special substrings can be
    private static long specialSubstringCount(int n, String s) {
        Set<String> specialSubstrings = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub =  s.substring(i, j);
                if (sub.length() != s.length()) {
                    if(isPalindrome(sub)) {
                        specialSubstrings.add(sub);
                    }
                }
            }
        }

        System.out.println("Special substrings are ::: " + specialSubstrings);
        return specialSubstrings.size();
    }

    //https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem#:~:text=Sherlock%20considers%20a%20string%20to,the%20same%20number%20of%20times.
    //Sherlock considers a string to be valid if all characters of the string appear the same number of times. It is
    // also valid if he can remove just  character at  index in the string, and the remaining characters will occur the same
    // number of times. Given a string , determine if it is valid. If so, return YES, otherwise return NO.
    public static String isValid(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        List<Integer> list = new ArrayList<>(frequencyMap.values().stream().toList());
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) continue;
            int curr = list.get(i);
            int prev = list.get(i - 1);
            if (curr - prev > 1) {
                int newValue = curr - prev;
                if (newValue > 1) {
                    System.out.println("String is not a valid one");
                    return "NO";
                }
            }
        }

        System.out.println("String is valid");
        return "YES";
    }

    public static boolean isPalindrome (String s) {
        int low = 0;
        int high = s.length() - 1;

        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }

        return true;
    }

    //https://www.hackerrank.com/challenges/alternating-characters/problem
    //You are given a string containing characters A and B only. Your task is to change it into a string such that
    //there are no matching adjacent characters. To do this, you are allowed to delete zero or more characters in the
    // string. Your task is to find the minimum number of required deletions.
    public static int minDeletions(String s) {
        int deletions = 0;

        // Iterate through the string
        //You just need to count how many times a character is the same as the one before it — that means it's a duplicate and
        // should be deleted. Loop through the string from the second character.
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                deletions++;
            }
        }

        System.out.println("Minimum deletions is " + deletions);

        return deletions;
    }

    //https://www.hackerrank.com/challenges/triple-sum/problem
    public static long triplets(List<Integer> pArr, List<Integer> qArr, List<Integer> rArr) {
       Set<List<Integer>> set = new HashSet<>();
        for (Integer value : pArr) {
            for (Integer integer : qArr) {
                for (Integer item : rArr) {
                    int pVal = value;
                    int qVal = integer;
                    int rVal = item;
                    if (pVal <= qVal && qVal >= rVal) {
                        set.add(List.of(pVal, qVal, rVal));
                    }
                }
            }
        }

        System.out.println("The unique triplets are: " +set);
       return 0;
    }

    private static boolean isAnagram (String s1, String s2) {
        List<Character> list1 = s1.chars().mapToObj(c -> (char) c).sorted().collect(Collectors.toList());
        List<Character> list2 = s2.chars().mapToObj(c -> (char) c).sorted().collect(Collectors.toList());
        return list1.equals(list2);
    }

    private static boolean isPowerOfTwo (long n) {
        if (n <= 0) return false;

        while (n % 2 == 0) {
            n = n / 2;
        }

        return n == 1; //if n != 1 then it is not a power of 2 else it is
    }


    private static Map<Integer, Integer> getFrequencyMap (List<Integer> list) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : list) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        return frequencyMap;
    }

    private static int reverseNum (int num) {
        StringBuilder builder = new StringBuilder();
        char[] c = String.valueOf(num).toCharArray();
        for (int x = c.length -1; x >= 0; x--) {
            builder.append(c[x]);
        }
        return Integer.parseInt(builder.toString());
    }

    public static int birthdayCakeCandles(List<Integer> candles) {
        // Write your code here
        Map<Integer, Integer> map = new HashMap<>();
        int max = candles.stream().mapToInt(Integer::intValue).max().getAsInt();
        for (int num: candles) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int tallestCount = map.get(max);

        System.out.println("The tallest birthday count is " + tallestCount);
        return tallestCount;
    }

    //Given two arrays of integers, find which elements in the second array are missing from the first array.
    //Notes
    //--- If a number occurs multiple times in the lists, you must ensure that the frequency of that number in both lists is the same.
    // If that is not the case, then it is also a missing number.
    //--- Return the missing numbers sorted ascending.
    //--- Only include a missing number once, even if it is missing multiple times.
    //--- The difference between the maximum and minimum numbers in the original list is less than or equal to .
    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
         TreeSet<Integer> set = new TreeSet<>();
         Map<Integer, Integer> map = new HashMap<>();

        for (int element : brr) {
            map.put(element, map.getOrDefault(element, 0) + 1);
        }

         for (Integer key : map.keySet()) {
             if (!arr.contains(key)) {
                 set.add(key);
             }

             Integer value = map.get(key);
             long count = arr.stream().filter(f -> f.equals(key)).count();
             if (value != count) {
                 set.add(key);
             }
         }

        System.out.println("Printing out the missing numbers " + set);
         return null;
    }

    //https://www.hackerrank.com/challenges/breaking-best-and-worst-records/problem?isFullScreen=true
    public static List<Integer> breakingRecords(List<Integer> scores) {
        List<Integer> maxScoreBuilder = new ArrayList<>();
        List<Integer> minScoreBuilder = new ArrayList<>();
        int maxScore = scores.get(0);
        int minScore = scores.get(0);

        for (int x = 0; x < scores.size(); x++) {
            int score = scores.get(x);
            if (score > maxScore) {
                maxScore = score;
                maxScoreBuilder.add(x);
            }
            if (score < minScore) {
                minScore = score;
                minScoreBuilder.add(x);
            }
        }

        System.out.println("Maria broke her best and worst records in " +Arrays.asList(maxScoreBuilder.size(), minScoreBuilder.size()) + " times");
        return Arrays.asList(maxScoreBuilder.size(), minScoreBuilder.size());
    }

    //https://www.hackerrank.com/challenges/the-birthday-bar/problem?isFullScreen=true
    public static int birthday(List<Integer> s, int d, int m) {
        int criteriaCount =  0;
        for (int i = 0; i < s.size(); i++) {
            int nextCounter = i + (m -1);

            if (nextCounter < s.size()) {
                int sum = s.subList(i, nextCounter + 1).stream().reduce(0, Integer::sum);
                if (sum == d) {
                    criteriaCount++;
                }
            }
        }

        System.out.println("The criteria count is = " + criteriaCount);
        return criteriaCount;
    }

    //https://www.hackerrank.com/challenges/migratory-birds/problem?isFullScreen=true
    public static int migratoryBirds(List<Integer> arr) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int x = 0; x < arr.size(); x++) {
            map.put(arr.get(x), map.getOrDefault(arr.get(x), 0) + 1);
        }

        int maxValue = Integer.MIN_VALUE;
        int resultKey = -1;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if (value > maxValue) {
                maxValue = value;
                resultKey = key;
            }
        }
        System.out.println("Key with highest value (and lowest among ties): " + resultKey);
        return resultKey;
    }

    //https://www.hackerrank.com/challenges/bon-appetit/problem?isFullScreen=true
    public static void bonAppetit(List<Integer> bill, int itemNotConsumed, int annaContribution) {

       int leftSum = bill.subList(0, itemNotConsumed).stream().reduce(0, Integer::sum);
       int rightSum = bill.subList(itemNotConsumed + 1, bill.size()).stream().reduce(0, Integer::sum);

       long amountCharged = (leftSum + rightSum) / 2;
       if (annaContribution == amountCharged) {
           System.out.println("Bon Appetit");
       } else {
           //The actual amount to be refunded to Anna is
           long refundAmount = annaContribution - amountCharged;
           System.out.println("Refund amount: " + refundAmount);
       }
    }

    //https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem?isFullScreen=true
    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Step 1: Maintain order
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
        for (int rank : ranked) {
            List<Integer> list = map.getOrDefault(rank, new ArrayList<>());
            list.add(rank);
            map.put(rank, list);
        }

        // Convert map to a List for index access
        List<Map.Entry<Integer, List<Integer>>> entries = new ArrayList<>(map.entrySet());
        List<Integer> leaderboard = new ArrayList<>();
        for (int playerScore : player) {
            for (int i = 0; i < map.size(); i++) {
                int value = entries.get(i).getValue().get(0); //get the first item in the list
                if (i == 0 && playerScore > value) { //start of index
                    leaderboard.add(i + 1);
                    break;
                }

                if (i == map.size() - 1 && playerScore < value) {
                    leaderboard.add(map.size() + 1);
                    break;
                }

                if (value <= playerScore) {
                    leaderboard.add(i + 1);
                    break;
                }
            }
        }

        System.out.println("Leader scores: " + leaderboard);
        return null;

    }

    //https://www.hackerrank.com/challenges/the-hurdle-race/problem?isFullScreen=true
    public static int hurdleRace(int k, List<Integer> height) {
        // Write your code here
        int max = Collections.max(height);
        if (k >= max) return 0;

        if (k < max) return max - k;

        return 0;
    }

    //https://www.hackerrank.com/challenges/circular-array-rotation/problem?isFullScreen=true
    public static List<Integer> circularArrayRotation() {
        int k = 2;
        List<Integer> list = new ArrayList<>(Arrays.asList(3,4,5));
        List<Integer> queries = new ArrayList<>(Arrays.asList(1, 2));

        Collections.rotate(list, k);
//        for (int i = 0; i < k; i++) {
//            int lastNum = list.remove(list.size() - 1); // remove last element
//            list.add(0, lastNum); // insert it at the front
//        }

        List<Integer> circularArray = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            Integer q = queries.get(i);
            circularArray.add(list.get(q));
        }

        System.out.println("Circular Array is given as: " + circularArray);
        return circularArray;

    }

    //https://www.hackerrank.com/challenges/find-digits/problem?isFullScreen=true
    public static int findDigits(int n) {
        // Write your code here
        int divisorCount = 0;
        List<Character> list = String.valueOf(n).chars().mapToObj(c ->(char)c).toList();

        for (Character character : list) {
            int num = character - '0';
            if (num == 0) continue;
            if (n % num == 0) {
                divisorCount++;
            }
        }

        System.out.println("The number of divisors occurring within the integer list is: " + divisorCount);
        return divisorCount;
    }

    //https://www.hackerrank.com/challenges/sherlock-and-squares/problem?isFullScreen=true
    public static int squares(int a, int b) {
//        for (int i = a; i <= b; i++) {
//            int sqrt = (int) Math.sqrt(i);
//            if (sqrt * sqrt == i) {
//                System.out.println(i + " is a perfect square (" + sqrt + "²)");
//            }
//        }

        int sqrtStart = (int) Math.ceil(Math.sqrt(24));
        int sqrtEnd = (int) Math.floor(Math.sqrt(49));

        for (int i = sqrtStart; i <= sqrtEnd; i++) {
            System.out.println(i * i);
        }

        return 0;
    }

}
