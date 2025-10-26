package org.karina.calculator.impl;

import org.karina.calculator.PrimeNumberCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfEratosthenes implements PrimeNumberCalculator {
    @Override
    public List<Integer> calculate(int n) {
        // assume that n >= 2
        // create a boolean array of size n-1 (since we're basically starting from 2)
        Boolean[] arr = new Boolean[n-1];
        Arrays.fill(arr, true);

        for (int i = 2 ; i < Math.sqrt(n); i++) {
            if (arr[i-2]) {
                for (int j = (int) Math.pow(i, 2) - 2; j < n-1 ; j+=i) {
                    arr[j] = false;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        // traverse arr, adding all i such that arr[i] is true
        // this will be the list of primes up to and including n

        for (int k = 0; k < n-1 ; k++) {
            if (arr[k]) {
                result.add(k+2);
            }
        }

        return result;
    }
}
