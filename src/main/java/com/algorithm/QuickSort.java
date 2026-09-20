package com.algorithm;

import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        sort(a, 0, a.length - 1, metrics);
    }

    private static void sort(int[] a, int low, int high, Metrics metrics) {
        while (low < high) {
            metrics.enterRecursion();
            try {
                int pivotIdx = low + random.nextInt(high - low + 1);
                swap(a, low, pivotIdx);

                // 3-way partitioning
                int lt = low, gt = high;
                int pivot = a[low];
                int i = low + 1;

                while (i <= gt) {
                    metrics.incrementComparisons();
                    if (a[i] < pivot) {
                        swap(a, lt++, i++);
                    } else if (a[i] > pivot) {
                        metrics.incrementComparisons(); // Доп. сравнение
                        swap(a, i, gt--);
                    } else {
                        metrics.incrementComparisons(); // Доп. сравнение
                        i++;
                    }
                }

                // В рекурсию отправляем меньшую часть, большую обрабатываем в циклах
                if (lt - low < high - gt) {
                    sort(a, low, lt - 1, metrics);
                    low = gt + 1; // Обрабатываем правую (большую) часть в while
                } else {
                    sort(a, gt + 1, high, metrics);
                    high = lt - 1; // Обрабатываем левую (большую) часть в while
                }
            } finally {
                metrics.exitRecursion();
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}