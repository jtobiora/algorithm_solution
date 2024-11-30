package com.swiftfingers.codingchallenge.exercises;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PalindromeSum {
    public static void main(String[] args) {

        Set<String> hs = new HashSet ();
        int count = 0;
        Scanner sc = new Scanner(System.in);
        int numCases = Integer.parseInt(sc.nextLine());
        for(int i = 0;i < numCases;i++){
            if(hs.add(sc.nextLine())){
                count++;
            }
            System.out.println(count);
        }
    }


}
