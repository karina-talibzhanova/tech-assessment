package org.karina.calculator.impl;

import org.karina.calculator.PrimeNumberCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfAtkin implements PrimeNumberCalculator {
    @Override
    public List<Integer> calculate(int n) {
        List<Integer> result = new ArrayList<>();
        Boolean[] arr = new Boolean[n];
        Arrays.fill(arr, false);
        arr[1] = true;  // mark 2 as prime
        if (n > 2) {
            arr[2] = true;  // mark 3 as prime
        }

        for (int x = 1; x*x <=n; x++) {
            for (int y = 1; y*y <=n; y++) {
                int z = (4*x*x) + (y*y);
                if (z <= n && (z%12 == 1 || z%12 == 5)) {
                    arr[z-1] = !arr[z-1];
                }

                z = (3*x*x) + (y*y);
                if (z <= n && z%12 == 7) {
                    arr[z-1] = !arr[z-1];
                }

                z = (3*x*x) - (y*y);
                if (x > y && z <= n && z%12 == 11) {
                    arr[z-1] = !arr[z-1];
                }
            }
        }

        for (int i = 5; i*i <= n; i++) {
            if (arr[i-1]) {
                for (int j = i*i; j <= n; j+=i*i) {
                    arr[j-1] = false;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (arr[i]) {
                result.add(i+1);
            }
        }

        return result;
    }
}
