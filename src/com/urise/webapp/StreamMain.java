package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMain {
    private static final int[] ARRAY = {4, 3, 6, 8, 8, 2, 2};

    public static void main(String[] args) {
        System.out.println(minValue(ARRAY));
        System.out.println(oddOrEven(List.of(1,2,3,4,5,6,7)));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, b) -> 10 * acc + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int mod = integers.stream()
                .mapToInt(Integer::valueOf)
                .sum() % 2;
        return integers.stream()
                .filter(val -> val % 2 != mod)
                .collect(Collectors.toList());

    }
}
