package com.algorithm;

public class Metrics {
    private long comparisons = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;
    private long timeNs = 0;

    public void incrementComparisons() {
        comparisons++;
    }

    public void addComparisons(long count) {
        comparisons += count;
    }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        if (currentDepth > 0) {
            currentDepth--;
        }
    }

    public long getComparisons() { return comparisons; }
    public int getMaxDepth() { return maxDepth; }
    public long getTimeNs() { return timeNs; }
    public void setTimeNs(long timeNs) { this.timeNs = timeNs; }

    public void reset() {
        comparisons = 0;
        currentDepth = 0;
        maxDepth = 0;
        timeNs = 0;
    }
}