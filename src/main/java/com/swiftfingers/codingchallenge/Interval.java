package com.swiftfingers.codingchallenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* An Interval class is defined to represent the interval pairs. Each interval has a start
* and an end timestamp.
* */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Interval {
    private int start;
    private int end;
}
