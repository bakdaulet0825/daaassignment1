package com.algorithm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class Benchmark {
    private static final int[] SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final String[] INPUT_TYPES = {"random", "sorted", "duplicates"};
    private static final int RUNS = 5;

    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth");

            for (String inputType : INPUT_TYPES) {
                for (int n : SIZES) {
                    runBenchmarkForConfig("MergeSort", inputType, n, writer);
                    runBenchmarkForConfig("QuickSort", inputType, n, writer);
                    runBenchmarkForConfig("QuickSelect", inputType, n, writer);
                }
            }
            System.out.println("Benchmark completed. Results written to results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmarkForConfig(String algo, String inputType, int n, PrintWriter writer) {
        double[] times = new double[RUNS];
        long[] comparisons = new long[RUNS];
        int[] depths = new int[RUNS];

        for (int r = 0; r < RUNS; r++) {
            int[] data = generateInput(inputType, n);
            Metrics metrics = new Metrics();

            long startTime = System.nanoTime();
            if (algo.equals("MergeSort")) {
                MergeSort.sort(data, metrics);
            } else if (algo.equals("QuickSort")) {
                QuickSort.sort(data, metrics);
            } else if (algo.equals("QuickSelect")) {
                QuickSelect.select(data, n / 2, metrics);
            }
            long endTime = System.nanoTime();

            times[r] = (endTime - startTime) / 1_000_000.0;
            comparisons[r] = metrics.getComparisons();
            depths[r] = metrics.getMaxDepth();
        }

        Integer[] indices = {0, 1, 2, 3, 4};
        Arrays.sort(indices, (a, b) -> Double.compare(times[a], times[b]));
        int medianIdx = indices[RUNS / 2];

        writer.printf(Locale.US, "%s,%s,%d,%.2f,%d,%d\n",
                algo, inputType, n, times[medianIdx], comparisons[medianIdx], depths[medianIdx]);
    }

    private static int[] generateInput(String type, int n) {
        Random rnd = new Random();
        int[] arr = new int[n];
        switch (type) {
            case "random":
                for (int i = 0; i < n; i++) arr[i] = rnd.nextInt();
                break;
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "duplicates":
                for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(10);
                break;
        }
        return arr;
    }
}