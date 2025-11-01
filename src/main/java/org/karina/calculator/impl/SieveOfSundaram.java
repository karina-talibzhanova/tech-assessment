package org.karina.calculator.impl;

import org.karina.calculator.PrimeNumberCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfSundaram implements PrimeNumberCalculator {
    @Override
    public List<Integer> calculate(int n) {
        // it works but not for big numbers (thank you integer overflow)
        List<Integer> result = new ArrayList<>();

        int k = (n-1) / 2;

        Boolean[] arr = new Boolean[k+1];
        Arrays.fill(arr, true);

        for (int i = 1; i <= k; i++) {
            for (int j = i; (i + j + 2*i*j) <= k; j++) {
                arr[i + j + 2*i*j] = false;
            }
        }

        result.add(2);  // 2 is prime, filtered out in above loop
        for (int x = 1; x <= k; x++) {
            if (arr[x]) {
                result.add(2*x + 1);
            }
        }

        return result;
    }
}
