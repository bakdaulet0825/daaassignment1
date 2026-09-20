# Report: Assignment 1 — Divide and Conquer Algorithms

**Author:** Bakdaulet Serik  
**Group:** SE-2518

---

## 1. Asymptotic Complexity Bounds

| Algorithm | Best Case | Average Case | Worst Case | Reason / Input Scenario |
| :--- | :--- | :--- | :--- | :--- |
| **MergeSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $\Theta(n \log n)$ | Division into halves is invariant to initial array order. |
| **QuickSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $O(n^2)$ | Worst case occurs on extremely unbalanced splits (mitigated by random pivot). |
| **QuickSelect**| $\Theta(n)$ | $\Theta(n)$ | $O(n^2)$ | Halves search space each step on average: $n + n/2 + n/4 \dots = O(n)$. |
| **InsertionSort** | $\Theta(n)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | Best case occurs on already sorted input (0 element shifts). Used as cutoff ($N \le 15$). |

---

## 2. Recurrence Relations & Master Theorem

### MergeSort
$$T(n) = 2T(n/2) + \Theta(n)$$
* **Parameters:** $a = 2$, $b = 2$, $f(n) = \Theta(n)$.
* **Critical Exponent:** $n^{\log_b a} = n^{\log_2 2} = n^1$.
* **Master Theorem Case:** Case 2 ($f(n) = \Theta(n^{\log_b a})$).
* **Solution:** $T(n) = \Theta(n \log n)$.

### QuickSort (Balanced Split)
$$T(n) = 2T(n/2) + \Theta(n)$$
* **Parameters:** $a = 2$, $b = 2$, $f(n) = \Theta(n)$.
* **Master Theorem Case:** Case 2.
* **Solution:** $T(n) = \Theta(n \log n)$.
* **Average Case Justification:** Random pivot selection ensures balanced splits (e.g., 50/50 or 25/75 probability), keeping expected tree height at $O(\log n)$ and average runtime at $O(n \log n)$.

### QuickSelect (Balanced Split)
$$T(n) = 1T(n/2) + \Theta(n)$$
* **Parameters:** $a = 1$, $b = 2$, $f(n) = \Theta(n)$.
* **Critical Exponent:** $n^{\log_b a} = n^{\log_2 1} = n^0 = 1$.
* **Master Theorem Case:** Case 3 ($f(n) = \Omega(n^{\log_b a + \epsilon})$ where $\epsilon = 1$).
* **Regularity Condition:** $1 \cdot f(n/2) = n/2 \le c \cdot n$ for $c = 1/2 < 1$.
* **Solution:** $T(n) = \Theta(n)$.

---

## 3. Empirical Analysis & Discussion

1. **Theoretical Alignment:** Empirical comparison counts closely match asymptotic models. Sorting algorithm comparisons grow proportionally to $N \log_2 N$, while QuickSelect comparisons scale linearly with $N$.
2. **Recursion Stack Bound Protection:** QuickSort's stack depth remains strictly bounded within $O(\log n)$ even on sorted inputs ($N = 10^6$ resulting in depth $\le 13$), confirming the efficiency of processing the smaller partition recursively and the larger iteratively.
3. **Impact of 3-Way Partitioning:** On input arrays with high duplicate density (`duplicates`), QuickSort and QuickSelect achieve drastically lower recursion depths (depths 2–3) and lower comparison counts, preventing degradation.
4. **Hardware & JVM Behavior:**
    - **JVM Warm-up & JIT Compilation:** Initial benchmark executions show higher relative latency due to class loading and Just-In-Time compilation. Taking the median over 5 runs eliminates noise.
    - **Cutoff Optimization:** Switch to Insertion Sort for sub-arrays $N \le 15$ eliminates recursive call stack overhead on tiny partitions, improving overall cache locality.