package com.swiftfingers.codingchallenge.exercises;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hackerank {

    public static void main(String[] args) {
        binaryNumbers(125);
        compareTriplets(List.of(11,20,32), List.of(21,33,12));
        System.out.println( divisibleSumPairs(6, 3, List.of(1, 3, 2, 6, 1, 2)));
        sockMerchant(9, List.of(10, 20, 20, 10, 10, 30, 50, 10, 20));
        miniMaxSum(List.of(1, 2, 3, 4, 5));
        gradingStudents(List.of(73, 67, 38, 33));
        saveThePrisoner(7, 19, 2);
        beautifulDays(20, 23, 6);
        minimumDistances(Arrays.asList(7, 1, 3, 4, 1, 7));
        equalizeArray(Arrays.asList(3, 3, 2, 1, 3));
        consecutive1sInBinary(13);
        strangeCounter(7);
        adjacentList();
        superReducedString("aaabccddd");
        plusMinus(Arrays.asList(-4, 3, -9, 0, 4, 1));
        timeConversion("07:01:00pm");
        matchingStrings(Arrays.asList("ab","ab","abc"), Arrays.asList("ab","abc","bc"));
        diagonalDifference();
        checkPangrams("the quick brown fox jumps over the lazy dog");
        rotateLeft(2, Arrays.asList(1,2,3,4,5));
        closestNumbers(Arrays.asList(5,2,3,1,4));
        minimumAbsoluteDifference(Arrays.asList(5,2,3,1,4));
        isStringSorted("abtsfefhs");
        gridChallenge(Arrays.asList("abc","ade", "efg"));
        balancedSums(Arrays.asList(5,6,8,11));
        missingNumbers(Arrays.asList(203,204,205,206,207,208,203,204,205,206), Arrays.asList(203,204,204,205,206,207,205,208,203,206,205,206,204));
        gamingArray(Arrays.asList(5,2,6,3,4));
        superDigit("9875", 4);
        counterGame(6);
        rotateLeft(Arrays.asList(1,2,3,4,5), 2);
    }

    /**
     * Task
     * Given a base- integer, , convert it to binary (base-). Then find and print the base- integer denoting the maximum number of
     * consecutive 's in 's binary representation. When working with different bases, it is common to show the base as a subscript.
     * The binary representation of  125 is 1111101. In base 10, there are 5 and 1   consecutive ones in two groups. Print the maximum.
     * The binary representation of  13 is 1101, so the maximum number of consecutive 1's is 2.
     * */
    public static void binaryNumbers (int num) {
        char[] binary = Integer.toBinaryString(num).toCharArray();
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

   // Given an array ar of integers and a positive integer k, determine the number of (i,j) pairs where i ,j and ar[i] + ar[j]  is divisible by k.
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
        for (Integer i: ar) {
            sockMap.put(i, sockMap.containsKey(i) ? sockMap.get(i) + 1 : 1);
        }

        for (Map.Entry<Integer, Integer> m : sockMap.entrySet()) {
            Integer value = m.getValue();
            if (value > 1) {
                pairsCounter  += value/2;
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
            if (gr < 38)
                newNumList.add(gr);
            else {
               int div = gr / 5;
               int res = (div + 1) * 5;
               int sub = res - gr;
               if (sub < 3) {
                   newNumList.add(res);
               } else {
                   newNumList.add(gr);
               }
            }
        }
        return newNumList;
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
        System.out.println("Beuatiful days " + beautifulDays);
        return beautifulDays.size();
    }

    //https://www.hackerrank.com/challenges/minimum-distances/problem
    public static int minimumDistances (List<Integer> numList) {
       Map<Integer, List<Integer>> map = new HashMap<>();
       List<Integer> count = new ArrayList<>();
       for (int x = 0; x < numList.size(); x++) {
           int pos = numList.get(x);
           if (map.containsKey(pos)) {
               map.get(pos).add(x);
           } else {
               List<Integer> newList = new ArrayList<>();
               newList.add(x);
               map.put(pos, newList);
           }
       }

        for (Map.Entry<Integer, List<Integer>> entrySet : map.entrySet()) {
            List<Integer> value = entrySet.getValue();
            Collections.sort(value);
            if (value.size() > 1) {
                int first = value.get(0);
                int last = value.get(1);
                int min = Math.abs(first - last);
                count.add(min);
            }
        }

        return count.stream().mapToInt(Integer::intValue).min().getAsInt();
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
        int primaryDiagonal = 0;
        int secondaryDiagonal = 0;

        for (int i = 0; i < size; i++) {
            primaryDiagonal = primaryDiagonal + arr.get(i).get(i);
            secondaryDiagonal = secondaryDiagonal + arr.get(i).get(size - i - 1);
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
    //Given two arrays of integers, find which elements in the second array are missing from the first array.
    //If a number occurs multiple times in the lists, you must ensure that the frequency of that number in both lists is the
    // same. If that is not the case, then it is also a missing number.
    //Return the missing numbers sorted ascending.
    //Only include a missing number once, even if it is missing multiple times.
    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        Collections.sort(arr);
        Collections.sort(brr);
        Set<Integer> missingList = new TreeSet<>();

        for (Integer i : brr) {
            //check if a number in the original list is not found in the missing list
            if (!arr.contains(i)) {
                missingList.add(i);
            }
        }

       //check if the element occurs multiple times in both lists and the freq is the same
        Map<Integer, Integer> frequencyMap1 = getFrequencyMap(arr);
        Map<Integer, Integer> frequencyMap2 = getFrequencyMap(brr);

        for (Map.Entry<Integer, Integer> entry : frequencyMap2.entrySet()) {
            int key = entry.getKey();
            int freq = entry.getValue();
            if (freq > 1 && frequencyMap1.get(key) != freq) {
                missingList.add(entry.getKey());
            }
        }


        System.out.println("Missing list " + missingList);

        return null;
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
        //,ove through the columns
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
        for (int x = c.length -1; x >=0; x--) {
            builder.append(c[x]);
        }
        return Integer.parseInt(builder.toString());
    }
}
