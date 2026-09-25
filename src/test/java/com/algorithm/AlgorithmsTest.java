package com.algorithm;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;
public class AlgorithmsTest {
    @Test
    void testMergeSortCorrectness() {
        Random rnd = new Random();
        Metrics metrics = new Metrics();
        for (int t = 0; t < 100; t++) {
            int[] a1 = rnd.ints(100, -1000, 1000).toArray();
            int[] a2 = a1.clone();
            Arrays.sort(a1);
            MergeSort.sort(a2, metrics);
            assertArrayEquals(a1, a2);
        }
    }
    @Test
    void testQuickSortCorrectnessAndDepth() {
        Random rnd = new Random();
        Metrics metrics = new Metrics();
        for (int t = 0; t < 100; t++) {
            int[] a1 = rnd.ints(100, -1000, 1000).toArray();
            int[] a2 = a1.clone();
            Arrays.sort(a1);
            QuickSort.sort(a2, metrics);
            assertArrayEquals(a1, a2);
        }
        int n = 100_000;
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) sorted[i] = i;
        metrics.reset();
        QuickSort.sort(sorted, metrics);
        double maxAllowedDepth = 2 * (Math.log(n) / Math.log(2));
        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth,
                "Depth " + metrics.getMaxDepth() + " exceeded limit " + maxAllowedDepth);
    }
    @Test
    void testQuickSelectCorrectness() {
        Random rnd = new Random();
        Metrics metrics = new Metrics();
        for (int t = 0; t < 100; t++) {
            int[] a = rnd.ints(100, -1000, 1000).toArray();
            int k = rnd.nextInt(100);
            int[] sorted = a.clone();
            Arrays.sort(sorted);
            int expected = sorted[k];
            int actual = QuickSelect.select(a.clone(), k, metrics);
            assertEquals(expected, actual);
        }
    }
    @Test
    void testQuickSelectExceptions() {
        Metrics metrics = new Metrics();
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[0], 0, metrics));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2, 3}, -1, metrics));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2, 3}, 3, metrics));
    }
}