package com.algorithm;

import java.util.Random;

public class QuickSelect {
    private static final Random random = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Array is empty or k is out of range!");
        }
        return quickSelect(a, 0, a.length - 1, k, metrics);
    }

    private static int quickSelect(int[] a, int low, int high, int k, Metrics metrics) {
        metrics.enterRecursion();
        try {
            if (low == high) {
                return a[low];
            }

            int pivotIdx = low + random.nextInt(high - low + 1);
            swap(a, low, pivotIdx);

            int lt = low, gt = high;
            int pivot = a[low];
            int i = low + 1;

            // 3-Way Partitioning (Dutch National Flag)
            while (i <= gt) {
                metrics.incrementComparisons();
                if (a[i] < pivot) {
                    swap(a, lt++, i++);
                } else if (a[i] > pivot) {
                    // Убираем лишний incrementComparisons, так как проверка выше уже выполнена
                    swap(a, i, gt--);
                } else {
                    i++;
                }
            }

            if (k >= lt && k <= gt) {
                return a[k];
            } else if (k < lt) {
                return quickSelect(a, low, lt - 1, k, metrics);  // Рекурсивный шаг влево
            } else {
                return quickSelect(a, gt + 1, high, k, metrics); // Рекурсивный шаг вправо
            }
        } finally {
            metrics.exitRecursion();
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}