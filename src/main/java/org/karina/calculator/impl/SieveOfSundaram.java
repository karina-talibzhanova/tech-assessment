package org.karina.calculator.impl;

import org.karina.calculator.PrimeNumberCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfSundaram implements PrimeNumberCalculator {
    @Override
    public List<Integer> calculate(int n) {
        if (n == 2) {
            return List.of(2);
        }
        List<Integer> result = new ArrayList<>();
        int k = (n-3)/2 + 1;
        Boolean[] arr = new Boolean[k];
        Arrays.fill(arr, true);

        // honestly a little lost on how this works, refer to https://en.wikipedia.org/wiki/Sieve_of_Sundaram
        for (int i = 0; i < (Math.sqrt(n) - 3)/2 + 1; i++) {
            int p = 2*i + 3;
            int s = (p*p -3) /2;
            for (int j = s; j < k; j+=p) {
                arr[j] = false;
            }
        }

        result.add(2);  // 2 is prime, was filtered out above

        for (int i = 0; i < k; i++) {
            if (arr[i]) {
                result.add(2*i + 3);
            }
        }

        return result;

    }
}
