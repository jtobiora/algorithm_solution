package com.swiftfingers.codingchallenge.exercises;

import java.util.*;

/**
 * Created by Obiora on 06-Dec-2024 at 21:54
 */
public class LeetCode {
    public static void main(String[] args) {
        romanToInt("MCMXCIV");
        longestCommonPrefix(new String[]{"flower","flow","flight"});
        System.out.println("Is string valid ? " + isValid("()"));
    }

    //https://leetcode.com/problems/roman-to-integer/description/
    //Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
    //Symbol       Value
    //I             1
    //V             5
    //X             10
    //L             50
    //C             100
    //D             500
    //M             1000
    //For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
    //Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
    // Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same
    // principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
    //I can be placed before V (5) and X (10) to make 4 and 9.
    //X can be placed before L (50) and C (100) to make 40 and 90.
    //C can be placed before D (500) and M (1000) to make 400 and 900.
    //Given a roman numeral, convert it to an integer.
    public static int romanToInt(String s) {
        Map<String, Integer> romanMap = new HashMap<>();
        romanMap.put("I", 1);
        romanMap.put("V", 5);
        romanMap.put("X", 10);
        romanMap.put("L", 50);
        romanMap.put("C", 100);
        romanMap.put("D", 500);
        romanMap.put("M", 1000);
        romanMap.put("IV", 4);
        romanMap.put("IX", 9);
        romanMap.put("XL", 40);
        romanMap.put("XC", 90);
        romanMap.put("CD", 400);
        romanMap.put("CM", 900);

        int digitSum = 0;
        int pointer  = 1;

        if (s.length() == 1) {
            return romanMap.get(String.valueOf(s.charAt(0)));
        }

        while (pointer < s.length()) {
            char prev = s.charAt(pointer - 1); //get the previous element
            char curr = s.charAt(pointer); //get the current element

            String joinedStr = prev + "" + curr; //join the prev and curr element
            if (romanMap.containsKey(joinedStr)) {
                //if the map contains the joined value, then extract it. The pointer should move two steps ahead to avoid reading
                //the elements again
                digitSum += romanMap.get(joinedStr);
                pointer = pointer + 2;
            } else {
                //if the map does not have both elements, just get the previous element and move the pointer one step forward
                digitSum += romanMap.get(String.valueOf(prev));
                pointer++;
            }

            if (pointer == s.length()) {
                //Now we have to add the last character. This character is obtained using the length of the pointer
                char lastChar = s.charAt(pointer - 1);
                digitSum += romanMap.get(String.valueOf(lastChar));
            }
        }

        return digitSum;
    }

    public static String longestCommonPrefix (String[] str) {
        List<String> list = Arrays.asList(str);
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
        return builder.toString();
    }

    public static boolean isValid(String str) {
        Map<Character, Character> openCharMap = new HashMap<>();
        openCharMap.put('(', ')');
        openCharMap.put('{', '}');
        openCharMap.put('[', ']');

        Map<Character, Character> closedCharMap = new HashMap<>();
        closedCharMap.put(')', '(');
        closedCharMap.put('}', '{');
        closedCharMap.put(']', '[');

        for (int x = 0; x < str.length(); x++) {
            char c = str.charAt(x);
             //check if char is a beginning type of char
            if (c == '(' || c == '{' || c == '[') {
                Character endChar = openCharMap.get(c); //get the end char bracket

                if (!str.contains(String.valueOf(endChar))) {
                    return false;
                } else {
                    int index = str.indexOf(String.valueOf(endChar));
                    int lastIndex = str.lastIndexOf(String.valueOf(endChar));
                    if (index != lastIndex) {
                        return false;
                    }
                }

                //if the difference between the index of beginning char and the end char is not odd, return false
                int indexOfBeginningChar = str.indexOf(String.valueOf(c));
                int indexOfEndChar  = str.indexOf(String.valueOf(endChar));
                if ((indexOfBeginningChar - indexOfEndChar) % 2 == 0) {
                    return false;
                }
            } else if (c == ')' || c == '}' || c == ']') {
                //check if the string contains the beginning char too
                Character openChar = closedCharMap.get(c); //get the open char bracket
                if (!str.contains(String.valueOf(openChar))) {
                    return false;
                }
                int indexOfOpenBracket = str.indexOf(String.valueOf(openChar));
                int lastIndexOfOpenBracket = str.lastIndexOf(String.valueOf(openChar));
                if (indexOfOpenBracket != lastIndexOfOpenBracket) {
                    return false;
                }

                //if the end char comes before the start, return false
                int indexOfCloseBracket = str.indexOf(String.valueOf(c));

                if (str.length() == 2 && indexOfOpenBracket > indexOfCloseBracket){
                    return false;
                }


                 if (str.length() > 2) {
                     if (indexOfOpenBracket > indexOfCloseBracket) {
                         return false;
                     }
                 }
            }

        }

        return true;

    }
}
