package com.swiftfingers.codingchallenge.exercises;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CodingTest {
    public static void main(String[] args) {
        findIndicesOfSum();
        //findRepeating("abcgd");
        findTheLongestSubstringWithoutRepeatingChars("school");
        findTheLongestPalindromicSubstring("babad");
        longestCommonPrefix();
        firstUniqueCharacterInString("leetcode");
        anagramNumbers(371);
        isDigitSumPalindrome(96);
        kthDigit();
        System.out.println("This is a jumping number: " +jumpingNums(96));
        sumOfPrimeNums();
        checkIfStringCanBeRearrangedToPalindrome("mum");
        convertStringToUppercase();
        sortAString();
        mergeStrings();
        extractMaximum();
        uncommonCharacters();
        System.out.println("The index of the first occurrence is " + strstr());
        largestPrimeFactor();
        isPerfectNumber ();
        nthRoot();
        findLongestDistinctCharInString("geeksforgeeks");
        kthLargestArrayElement();
        findArrayLeaders();
        productArrayPuzzle();
        findMissingAndRepeatingNums();
        countPair();
        medianOfTwoSortedArrays();
        removeNthNodeFromList();
        removeArrayDuplicates();
        findFirstAndLastPositionInArray();
        firstMissingPositive();
        groupAnagrams();
        plusOne();
        sortColors();
        subsets();
        longestConsecutiveSequence();
        findPeakElement();
        productofArrayExceptSelf();
        countOfSmallerNumbersExceptSelf();
        checkIfStringsKAnagrams();
        findMajorityElement();
        reverseAStringAndPreserveSpaces();
    }


    //find the indexes where the sum of the elements will be equal to a given target
    private static void findIndicesOfSum () {
        int [] nums = {9, 1,5,8};
        int target = 6;
        int prev = nums[0];
        int curr;
        int indexOne = -1;
        int indexTwo = -1;
        boolean found = false;
        for (int x =0; x < nums.length; x++) {
            if (x == 0) continue;

            curr = nums[x];

            if (prev + curr == target) {
                indexOne = x;
                indexTwo = x - 1;
                System.out.println("Index One " + indexOne + " Index Two "+ indexTwo);
                found = true;
                break;
            }
            prev = curr;
        }

        if (found)
            System.out.printf("The Indices are %d and %d\n ", indexOne, indexTwo);
        else
            System.out.println("The sum was not found!");

//        int firstIndex = -1;
//        int secondIndex = -1;
//        for (int x =0; x < nums.length; x++) {
//            int sum = nums[x] + nums[x - 1];
//            if (sum == target) {
//                firstIndex = x;
//                secondIndex = x - 1;
//            }
//        }

    }

    //verify if a string has repeating character
    public static boolean isCharRepeat (String str) {
        Set<Character> set = new HashSet<>();
        char[] chars = str.toCharArray();
        for (Character c: chars) {
            set.add(c);
        }

        return str.length() > set.size();
    }

    public static void findTheLongestSubstringWithoutRepeatingChars (String str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String sub = str.substring(i, j);
                if(!isCharRepeat(sub)) {
                   list.add(sub);
                }
            }
        }


        String s = list.stream().collect(Collectors.maxBy(Comparator.comparingInt(String::length))).get();
        System.out.println(s);
    }


    public static void findTheLongestPalindromicSubstring (String str) {
        List<String> list = new ArrayList<>();
        //find all the substrings
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String sub = str.substring(i, j);
                if(isPalindrome(sub)) {
                    list.add(sub);
                }
            }
        }
        String s = list.stream().collect(Collectors.maxBy(Comparator.comparingInt(String::length))).get();
        System.out.println(s);

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

    public static void longestCommonPrefix () {
        String[] st = {"flour","flower","flourish","flow","floor"};
//        Set<String> set = new TreeSet<>();
//        Set<String> s = Arrays.stream(st).collect(Collectors.toCollection(TreeSet::new)); //convert the array to set
        List<String> list = Arrays.asList(st);
        Collections.sort(list);
        String first = list.get(0);
        String last = list.get(list.size() -1 );
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            if (c != last.charAt(i)) {
                break;
            }
            builder.append(c);
        }

        System.out.println(builder.toString());
    }

    public static void firstUniqueCharacterInString(String str) {
        //Method 1 ---- Using Map
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (Character ch: str.toCharArray()) {
            map.put(ch, map.containsKey(ch) ? map.get(ch) + 1 : 1);
        }

        Character firstNonRepeatingChar = map.entrySet().stream().filter(f -> f.getValue() == 1)
                .findFirst().get().getKey();
        System.out.println(firstNonRepeatingChar);




        //Method 2 ---- using lastIndexOf and indexOf
        for (Character c : str.toCharArray()) {
                  //if current character is the last occurrence in the string
            if (str.indexOf(c) == str.lastIndexOf(c)) {
                System.out.println("First non repeating character is " + c);
                break;
            }
        }


    }

    public static boolean anagramNumbers (int num) {
        String st = String.valueOf(num);
        char[] ch = st.toCharArray();
        int sum = 0;
        for (Character c: ch) {

            sum += Math.pow(c - '0', 3);
        }

        return sum == num;
    }

    public static void isDigitSumPalindrome(int num) {
        String st = String.valueOf(num);
        char[] ch = st.toCharArray();
        int sum = 0;
        for (Character c: ch) {

            sum += Integer.parseInt(String.valueOf(c));
        }

        System.out.println(isPalindrome(String.valueOf(sum)));

    }

    //Given two numbers A and B, find the kth digit from the right of A raise to power B
    public static void kthDigit() {
        int A = 3;
        int B = 3;
        int K = 1;
        double  power = Math.pow(A, B);
        String st = String.valueOf(power);
        String modified = st.substring(0, st.lastIndexOf("."));
        char[] c = modified.toCharArray();
        int index = 1;
        String numToFind = null;
        for(int x = c.length - 1; x >= 0; x--) {
            if (index == K) {
                numToFind = st.charAt(x) + "";
                break;
            }
            index++;
        }


        System.out.println("The kth digit is " + numToFind);
    }

    //Given a positive number X, find the largest jumping number which is smaller than or equal to X. A number is
    //called a jumping number if all the adjacent digits in it differ by only 1. E.g 78987 is a jumping num while
    //7869 is not
    public static boolean jumpingNums(int num) {
        String st = String.valueOf(num);
        if(st.length() == 1) return true;
        char[] ch = st.toCharArray();
        char startIndex = ch[0];
        //start the iteration from the number
        for (int x = 0; x < ch.length; x++)  {
            if (x == 0) continue;
            char c = st.charAt(x);
            if(Math.abs((int)startIndex - c) > 1) return false;
            startIndex = ch[x];
        }

        return true;
    }

    public static boolean isPrimeNumber (int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= num/2; i++) {
            if (num % i == 0) return false;
            System.out.println("------------------ " + i);
        }
        return true;
    }

    //Given a positive number N, find the sum of all prime numbers between 1 and N (inclusive)
    public static void sumOfPrimeNums () {
        int N = 10;
        int sum = 0;
        for (int x = 1; x <= N; x++) {
            if(isPrimeNumber(x)) {
                sum += x;
            }
        }

        System.out.println("Sum of the prime numbers " + sum);
    }

    //Given a String S check if the characters of the given string can be rearranged to form a palindrome
    public static void checkIfStringCanBeRearrangedToPalindrome (String s) {
        //solution *** A string is palindrome if at MOST one of the characters can be odd in frequency count

        Map<Character, Integer> map = new HashMap<>();
        int oddCount = 0;

        for (Character c: s.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }


        for (Integer freq : map.values()) {
            if (freq % 2 != 0) {
                oddCount++;
            }
        }

        boolean b =  oddCount <= 1 ? true : false;
        System.out.println("The string can be rearranged to form a palindrome " +  b);

    }

    public static void convertStringToUppercase () {
        String str = "This is intellij software";
        List<String> st = Arrays.asList(str.split("\\s"));
        StringBuilder builder = new StringBuilder();
        for(String s : st) {
            builder.append(String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1)).append(" ");
        }

        System.out.println(builder.toString().trim());
    }


    //Given a string containing only lowercase alphabets, the task is to sort it in lexicographically descending order
    public static void sortAString() {
        String s = "sorted list object";
        String[] stArray = s.split(" ");
        List<String> list = Arrays.asList(stArray);
        Collections.sort(list);
        StringBuilder builder = new StringBuilder();

        for(int x = list.size() -1; x >= 0; x--) {
            builder.append(list.get(x)).append(" ");
        }

        System.out.println(builder.toString().trim());

    }

    //Given two strings S1 and S2 as input, the task is to merge them alternatively i.e the first character of S1
    //then the first character of S2 and so on till the strings end.

    public static void mergeStrings() {
        String s1 = "Hello";
        String s2 = "Nigeria";
        StringBuilder builder = new StringBuilder();
        int minLength = Math.min(s1.length(),s2.length());

        for(int x = 0; x < minLength; x++) {
            builder.append(s1.charAt(x)).append(s2.charAt(x));
        }


        if (s1.length() > s2.length()) builder.append(s1.substring(s2.length()));

        if (s2.length() > s1.length()) builder.append(s2.substring(s1.length()));

        System.out.println(builder.toString());

    }

    //Given a alphanumeric string S, extract the maximum numeric value from S
    public static void extractMaximum () {
        String concatStr = "";
        String s = "ab342bc493p4q55";
        char[] charArray = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
//        for (Character c: charArray) {
//            if (!Character.isDigit(c)) {
//                if (!concatStr.isEmpty()) {
//                    list.add(Integer.parseInt(concatStr));
//                    concatStr = "";
//                }
//                continue;
//            }
//           concatStr += c + "";
//
//        }
//        list.add(Integer.parseInt(concatStr));
//

        for (Character c : charArray) {
            if (!Character.isDigit(c)) {
                if (builder.length() > 0) {
                    list.add(Integer.parseInt(builder.toString()));
                    builder.setLength(0);
                }
//
            } else {
                builder.append(c);
            }
        }

        list.add(Integer.parseInt(builder.toString()));


        int max = list.stream().max(Integer::compare).get();
        System.out.println("Maximum value is ::: " + max);
    }


    //Given two strings A and B, find the characters that are not common in the strings
    public static void uncommonCharacters () {
      String A = "school";
      String B = "houses";
      String replacedStr = B;
      String[] aArray = A.split("");
      List<String> list = Arrays.asList(B.split(""));
      StringBuilder builder = new StringBuilder();
      for (String s : aArray) {
         if (list.contains(s)) {
            replacedStr = replacedStr.replace(s, "");
         } else {
            builder.append(s);
         }
      }

      builder.append(replacedStr);
      System.out.println("The characters that are not common in the two strings are " + builder.toString());


    }

    //Your task is to implement the function strstr. The function takes two strings as arguments (s,x) and locates
    //the occurence of the string x in the string s. The function returns an integer denoting the first occurence of
    //the string x in s (0 based indexing). The function returns -1 if no match is found.
    public static int strstr () {
        //solution
        //1. Get the length of x and Loop through the s to get the characters in s
        //2. Check if the string obtained matches x. If it does, get the index
        //3. If NOT, Start a loop the starting point to be the length of x
        //4. For each loop, remove the first character of the string and append the current character to the string.
        //5. Then check if it matches what is being searched for

//        String x = "programming";
//        String s = "min";
//        int finderLength = x.length();
//        String matcher  = "";
//
//        for (int j = 0; j < finderLength; j++) {
//            matcher += String.valueOf(s.charAt(j));
//        }
//
//        if (x.equals(matcher)) {
//            return s.indexOf(matcher);
//        }
//
//        for (int i = finderLength; i < s.length(); i++) {
//            matcher += String.valueOf(s.charAt(i)); //append the current character to the string at the last position
//            matcher = matcher.substring(1); //remove the first character from the string
//            if (matcher.equals(x)) {
//                return s.indexOf(matcher);
//            }
//        }
//
       return -1;
    }

    //Given a number N the task is to find the largest prime factor of that number
    public static void largestPrimeFactor () {
        int num = 5;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            if (isPrimeNumber(i)) {
                if (num % i == 0) {
                    list.add(i);
                }
            }
        }

       int maxPrimeFactor = list.stream().mapToInt(Integer::intValue).max().getAsInt();
        System.out.println("Largest prime factor " + maxPrimeFactor);
    }


    //Given a number N, check if the number is a perfect number. A number is said to be perfect if the sum of
    //all its factors excluding the number itself is equal to the number
    public static void isPerfectNumber() {
        int num = 6;
        int sum = 0;

        for (int k = 1; k < num; k++) {
            if (num % k == 0)
                sum += k;
        }

        System.out.println("Number is a perfect number " + (sum == num ? true : false));

    }

    public static void nthRoot() {
        double n = 2;
        double m = 9;
        double d = Math.round(Math.pow(m, 1.0/n));
        System.out.println(d);
    }

    public static void findLongestDistinctCharInString (String s) {
        String longest = "";
        for (int x = 0; x < s.length(); x++) {
            for (int y = x + 1; y <= s.length(); y++) {
                String str = s.substring(x,y);
                if (isUniqueCharacters(str)) {
                    if (str.length() > longest.length()) {
                        longest = str;
                    }
                }
            }
        }

        System.out.println("The longest substring with all distinct characters is " + longest);
    }

    public static boolean isUniqueCharacters (String s) {
        Set<Character> set = new TreeSet<>();

        for (int x= 0; x < s.length(); x++) {
            char c = s.charAt(x);
            set.add(c);
        }

        return s.length() == set.size();
    }

    //Given an array Arr of size N, print the kth largest element in the array
    public static void kthLargestArrayElement () {
        int k = 3;
        int[] array = {12, 4, 7, 18, 21, 5, 8};
        Arrays.sort(array);
        int count = 1;

        for (int y = array.length - 1; y >= 0; y--) {
            if (count == k) {
                System.out.println("The element is " + array[k]);
                break;
            }
            count++;
        }
    }

    //Given an array of positive integers. Your task is to find the leaders in the array. An element is a leader
    //if it is greater than or equal to all equal to all the elements to its right side. The rightmost element
    //is always a leader
    public static void findArrayLeaders () {
        int[] array = {10,32,5,12,9,13,4};
        List<Integer> leadersList = new ArrayList<>();
        boolean flag;
       // List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        for (int x = 0; x < array.length; x++) {
            flag = false;
            for (int y = x + 1; y < array.length; y++) {
              if (array[x] < array[y]) {
                 flag = true;
              }

            }

            if (flag == false)
                leadersList.add(array[x]);
        }

        System.out.println("The leaders in the array are: "+leadersList);
    }

    //Given an array A of N elements, Find the majority element in the array. A majority element in an array
    // A of size N is an
    //element that appears more than N/2 times in the array
    public static void findMajorityElement () {
        int[] array = {3,12,3,4,6,1,4,2,1,3,4};
        List<Integer> list = new ArrayList<>();

        Map<Integer, Integer> elementMap = new HashMap<>();
        for (int x = 0; x < array.length; x++) {
            elementMap.put(array[x], elementMap.containsKey(array[x]) ?  elementMap.get(array[x]) + 1 : 1);
        }

       for(Map.Entry<Integer, Integer> entry: elementMap.entrySet()) {
            if (entry.getValue() > array.length/2) {
                 list.add(entry.getKey());
            }
       }

        System.out.println("The majority element is " + list);
    }

    //Given an array nums[] of size n, construct a Product array P (of same size n) such that p[i] is equal
    //to the product of all the elements of nums except nums[i]
    public static void productArrayPuzzle () {
       int[] array = {10,3,5,6,2};
      // Arrays.sort(array);
       int multiply = 1;
       int total = 1;
       List<Integer> integerList = new ArrayList<>();

       for (int x = 0; x < array.length; x++) {
           if (x == 0) {
               multiply = 1;
           } else {
               multiply = multiply * array[x - 1];
           }

           for (int y = x + 1; y < array.length; y++) {
              total = total * array[y];
           }
           total = multiply * total;
           integerList.add(total);
           total = 1;
       }

       System.out.println("product puzzle list " +integerList);
    }

    //Given an unsorted array Arr of size N of positive integers. One number 'A' from set {1,2,...,N} is
    //missing and one number 'B' occurs twice in the array. Find these two numbers
    public static void findMissingAndRepeatingNums () {
        int[] array = {1,3,3};
        List<Integer> arrList = Arrays.stream(array).boxed().collect(Collectors.toList());
        Map<Integer, Integer> holderMap = new LinkedHashMap<>();
        List<Integer> repititionsList = new ArrayList<>();
        boolean flag = true;
        StringBuilder builder = new StringBuilder();
        int loopTracker = 1;

        for (int x = 0; x < array.length; x++ ) {
            holderMap.put(array[x], holderMap.containsKey(array[x]) ? holderMap.get(array[x]) + 1 : 1);
        }

        for(Map.Entry<Integer, Integer> entry: holderMap.entrySet()) {
            int key = entry.getKey();
            if (entry.getValue() > 1) {
                repititionsList.add(key);
            }

            if(flag && !arrList.contains(key + 1) && loopTracker != holderMap.size()) {
                flag = false;
                int smallestMissingItem = key + 1;
                builder.append(smallestMissingItem);
            }
            loopTracker++;
        }
        System.out.println("The number that is repeated is " +repititionsList);
        System.out.println("The smallest missing number is " + builder.toString());
    }


    //You are given an array Arr of size N. You need to find all pairs in the array that sum to a number K. If no such pair
    //exists then output -1. The elements of the array are distinct and are in sorted order.
    //Note (a,b) and (b,a) are considered same. Also an element cannot pair with itself such as (a,a)
    public static void countPair () {
        int index = 1;
        int [] array = {1, 2, 3, 4, 5, 6, 7};
        int k = 8;
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == k) {
                   map.put(index, String.join("," ,String.valueOf(array[i]), String.valueOf(array[j])));
                   index++;
                }
            }
        }

       map.forEach( (key,value ) -> System.out.print(" Pairs " + value));
        System.out.println();

    }

    //Given two arrays num1 and num2 of size m and n respectively, return the median of the two sorted arrays.
    public static void medianOfTwoSortedArrays () {
        int num1 [] = {1,4};
        int num2 [] = {3, 8};

        int [] concatArray = IntStream.concat(Arrays.stream(num1), Arrays.stream(num2)).toArray();
        int arrSize = concatArray.length;
        Arrays.sort(concatArray);

        //check for odd size
        if (arrSize % 2 != 0) {
            int x  = (arrSize + 1) / 2;
            int median = concatArray[x - 1];
            System.out.println("Median for odd size is " + median);
        }



        //check for even size
        if (arrSize % 2 == 0) {
            int x  = arrSize / 2 ;
            double median = (double) (concatArray[x] +  concatArray[x - 1]) / 2;
            System.out.println("Median for even size is " + median);
        }
     }

     //Given the head of aa linked list, remove the nth node from the end of the list and return its head
     public static void removeNthNodeFromList () {
         List<Integer> linkedList = new LinkedList<>();
         linkedList.add(1);
         linkedList.add(2);
         linkedList.add(3);
         linkedList.add(4);
         linkedList.add(5);

         int n = 2;
         linkedList.remove(linkedList.size() - n);
         System.out.println("Linked list after remove operation has been done: " +linkedList);
     }

     public static void removeArrayDuplicates () {
        int[] array = {0,0,1,1,1,2,2,3,3,4};
        Set<Integer> set = new HashSet<>();

        for (int x = 0; x < array.length; x++) {
            set.add(array[x]);
         }

         System.out.println("Duplicates removed " + set);
     }

     //Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a
    //given target value
     public static void findFirstAndLastPositionInArray () {
        int nums [] = {5, 7, 7, 8, 8, 10};
        int target = 7;
        List<String> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                list.add(String.valueOf(i));
            }
        }

        if (!list.isEmpty()){
            String str = String.join(",", list.get(0), list.get(list.size() -1));
            System.out.println("The first and last elements are " + str);
        } else
            System.out.println("The first and last elements are (-1,-1)");
     }

     //Given an unsorted integer array nums, return the smallest missing positive integer.
     public static void firstMissingPositive () {
        int [] nums = {1,-1,5,4,2};
        int prevNum = 0;
        int nextNum = 0;
        int missingNum = 0;


        if (nums.length == 1) missingNum = nums[0] + 1;

        if (nums.length >= 1) {
            Arrays.sort(nums);
        }


        for (int i = 0; i <  nums.length; i++) {
            if (nums[i] < 1) {
                continue;
            }

            if (i == nums[nums.length - 1])  {
                missingNum = nextNum + 1;
                break;
            }



            prevNum = nums[i];
            nextNum = nums[i + 1];

            if (nextNum - prevNum > 1){
                missingNum = prevNum + 1;
                break;
            }
        }

        System.out.println("The smallest positive missing number is " + missingNum);

//         int[] num = {-1, 1, 2, 3, 5};
//         int smallestElement = 0;
//         Arrays.sort(num);
//
//         for (int x = 0; x < num.length; x++) {
//             if (num[x] <= 0) continue;
//
//             if ((x + 1) <=  num.length) {
//                 int current = x;
//                 int next = x + 1;
//
//                 if (num[next] - num[current] > 1) {
//                     smallestElement = num[next] - 1;
//                 }
//             }
//         }
     }


     //Given an array of strings strs, group anagrams together. You can return the answer in any order
     public static void groupAnagrams () {
        String[] strs = {"eat", "tea", "tan","ate","nat","bat"};
        Map <String, List<String> > anagramMap = new HashMap<>();

        for (String st : strs) {
            char[] c = st.toCharArray();
            Arrays.sort(c);
            String sortedStr = String.valueOf(c);

            if (anagramMap.containsKey(sortedStr)) {
                List<String> list = anagramMap.get(sortedStr);
                list.add(st);
                anagramMap.put(sortedStr, list);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(st);
                anagramMap.put(sortedStr, newList);
            }
        }

        for (List<String> anagrams: anagramMap.values()) {
            System.out.println(anagrams);
        }
     }

     //You are given a large integer represented as an integer array digits where each digits[i] is the ith digit of the
    //integer. The digits are ordered from the most significant to least significant in left to right order. The large
    //integer does not contain any leading 0's. increment the large integer by one and return the resulting array of digits
     public static void plusOne () {
        int[] digits = {4,3,2,1};
        List<Integer> numList = new ArrayList<>();
        int num = 0;
        for (int j =0 ; j < digits.length; j++) {
            num = digits[j];
            if ( j == digits.length - 1) {
                num  = digits[j] + 1;
            }

            numList.add(num);
        }

         System.out.println("The array obtained afterwards is: " +numList);
     }



     public static void sortColors () {
       int [] nums  = {2,0,2,1,1,0};
       Map<Integer, List<Integer>> map = new TreeMap<> ();

       for (int i = 0; i < nums.length; i++) {
           if (map.containsKey(nums[i])) {
               List<Integer> list = map.get(nums[i]);
               list.add(nums[i]);
               map.put(nums[i], list);
           } else {
               List<Integer> newList = new ArrayList<>();
               newList.add(nums[i]);
               map.put(nums[i], newList);
           }
       }

         System.out.println("The sorted numbers are; " +map);
     }

     public static void subsets () {
        int [] nums = {1, 2, 3};
        Set<String> subsets = new TreeSet<>();

        for (int y = 0; y < nums.length; y++ ) {
            subsets.add(String.valueOf(nums[y]));
            for (int z = y + 1; z < nums.length; z++) {
                String s = String.join(",", String.valueOf(nums[y]), String.valueOf(nums[z]));
                subsets.add(s);
            }
        }

         System.out.println("Subsets:: " +subsets);
     }


     //Given an unsorted array of integers nums, return the length of the longest consecutive elements in sequence
    public static void longestConsecutiveSequence () {
        int num[] = {100,4,200,1,3,2};
        List<Integer> list = new LinkedList<>();
        Arrays.sort(num);
        int prev = num[0];
        int next = num[0];
        for (int  i =0; i < num.length; i++) {
            if (i == 0) {
                list.add(num[i]);
                continue;
            }
            prev = num[i - 1];
            next = num[i];
            if (next - prev > 1) break;

            list.add(num[i]);

        }

        System.out.println("The longest consecutive sequence is " +list);
    }


    //A peak element is an elemnt that is strictly greater than its neighbours. Given a 0-indexed integer array nums,
    //find a peak element and return its index. If the array contains multiple peaks, return the index to any of the
    //peaks.
    public static void findPeakElement () {
        int [] nums = {1, 2, 1, 3, 5, 6, 4};

        int prev = nums[0];
        int current = 0;
        int next = 0;
        String peakElement = "";

        for (int i = 0; i < nums.length; i++) {
            if (i == 0 ) continue;

            if(i != nums.length -1) {
                prev = nums[i - 1];
                current = nums [i];
                next = nums[i + 1];
            }

            if (current > prev && current > next) {
                peakElement = String.valueOf(i);
                break;
            }

            if (i == nums.length - 1) {
                if (current > prev) {
                    peakElement = String.valueOf(i);
                }
            }
        }

        System.out.println("The peak element index position is at " + peakElement);

    }


    //Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements
    //of nums except nums[i]
    public static void productofArrayExceptSelf () {
        int[] numArray = {1, 2, 3, 4};
        List<Integer> numList = new LinkedList<>();
        int tracker =1;
        int total = 1;

        for (int x = 0; x < numArray.length; x++) {
            if (x == 0) {
                tracker = 1;
            } else {
                 tracker = tracker * numArray[x - 1];
            }

            for (int y = x + 1; y < numArray.length; y++) {
                total *= numArray[y];

            }

            total *= tracker;
            numList.add(total);
            total = 1;
        }

      System.out.println("The array returned is " +numList);
    }

    //Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements
    //to the right of nums[i]
    public static void countOfSmallerNumbersExceptSelf () {
        int[] nums = {5, 2, 6, 1};
        int minCountSize = 0;
        List<Integer> intList = new ArrayList<>();
        for (int x = 0; x < nums.length; x++) {
            for (int y = x + 1; y < nums.length; y++) {
               if (nums[y] < nums[x]) {
                   minCountSize++;
               }

            }

            intList.add(minCountSize);
            minCountSize = 0;


        }

        System.out.println("The count list of the smaller numbers is " + intList);
    }


    //Given two strings of lowercase alphabets and a value K, your task is to complete the given fucntion which tells if two strings are K-Anagrams
    //of each other or not. Two strings are K-Anagrams of each other if these conditions are true:
    //1. Both have the same number of characters
    //2. Two strings can become anagram by changing at most K characters in a string
    public static void checkIfStringsKAnagrams () {
       String s1 = "fodr";
       String s2 = "gork";
       int k = 2;
       int count = 0;

       if (s1.length() != s2.length())
           System.out.println("The two strings are not K-Anagrams of each other");

       String[] stArray1 = s1.split("");
       Arrays.sort(stArray1);

       List<String> list = Arrays.asList(s2.split(""));
       Collections.sort(list);

       for (int x = 0; x < stArray1.length; x++) {
           if (!stArray1[x].equals(list.get(x)) && count != k) {
               list.set(x, stArray1[x]);
               count++;
           }
       }

       if (Objects.equals(list, Arrays.asList(stArray1))) {
           System.out.println("Both are anagrams");
       }
    }

    //Write a java program to reverse a string while preserving the position of spaces. For example, "I Am Not"
    //is the given string then the reverse of this string is "t on amI "
    public static void reverseAStringAndPreserveSpaces () {
        String s = "we keep pushing always!";
        char [] c = s.toCharArray();
        List<Integer> indexes = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < c.length; x++) {
            if (Character.isWhitespace(c[x])) {
                indexes.add(x);
            }
        }

        //TODO
        for (int x = c.length -1; x >=0; x--) {

        }

    }

}
