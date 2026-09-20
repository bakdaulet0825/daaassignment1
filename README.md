# Assignment 1: Fast Sorting & Selection Engine (Divide and Conquer)

**Course:** Design and Analysis of Algorithms  
**Student:** Bakdaulet Serik  
**Group:** SE-2518

---

## Project Overview

This project implements optimized Divide-and-Conquer algorithms for array sorting and selection in Java:
- **MergeSort:** Uses a single reusable helper array and Insertion Sort cutoff for small sub-arrays ($N \le 15$).
- **QuickSort:** Features random pivot selection, 3-way partitioning ($<, =, >$), and stack depth optimization by recursing into the smaller partition first.
- **QuickSelect:** Recursive selection algorithm for finding the $k$-th smallest element with random pivot and 3-way partitioning.
- **Metrics & Benchmark Engine:** Measures execution time, total element comparisons, and maximum recursion stack depth across various input distributions, outputting results to `results.csv`.

---

## Project Structure

```text
daaassignment1/
├── src/
│   ├── main/java/com/algorithm/
│   │   ├── MergeSort.java     # MergeSort with 1 buffer and cutoff
│   │   ├── QuickSort.java     # QuickSort with random pivot, 3-way partition & bounded depth
│   │   ├── QuickSelect.java   # QuickSelect (Divide-and-Conquer)
│   │   ├── Metrics.java      # Performance metrics tracker
│   │   └── Benchmark.java    # Benchmark suite generating results.csv
│   └── test/java/com/algorithm/
│       └── AlgorithmsTest.java # JUnit 5 correctness and depth constraint tests
├── results.csv               # Measured performance outputs
├── generate_plots.py         # Python script for plot generation
├── REPORT.md                 # Detailed asymptotic analysis and discussion
├── README.md                 # Project documentation and guide
└── pom.xml                   # Maven build configuration
```
## Requirements

- **JDK 17+**
- **Apache Maven 3.8+** (or Maven Wrapper `mvnw`)
- **Python 3.8+** with `pandas` and `matplotlib` (for generating plots)

---

## Building & Execution Guide

### 1. Build & Compile
```bash
mvn clean compile
2. Run Tests (JUnit 5)Executes correctness verification tests against 100+ random arrays, edge cases, and stack depth bounds:Bash.\mvnw test
(Or run AlgorithmsTest directly in IntelliJ IDEA)3. Run Benchmark SuiteExecutes algorithms across array sizes ($N = 10^3, 10^4, 10^5, 10^6$) and distributions (random, sorted, duplicates), saving median metrics of 5 runs to results.csv:Bashmvn exec:java
4. Generate Performance PlotsBashpy generate_plots.py
Outputs .png visualization files for time, recursion depth, and asymptotic ratio charts.