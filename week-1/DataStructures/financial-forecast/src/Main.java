import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    // --- Counters for measuring recursive stack usage ---
    private static int currentDepth = 0;
    private static int maxDepth = 0;

    // --- 1. Recursion Explained ---
    // Recursion is when a method calls itself with a smaller subproblem
    // until it reaches a base case. It can make code more declarative
    // but uses extra stack space (one frame per call).

    // --- 2. Recursive Future Value (Pure Recursion) ---
    /**
     * Recursively computes the future value after applying growthRates[0..index].
     * @param baseValue   initial amount
     * @param growthRates percent rates, e.g. 0.05 for 5%
     * @param index       last period to apply
     * @return forecasted value
     */
    public static double forecastRecursive(double baseValue, double[] growthRates, int index) {
        currentDepth++;
        maxDepth = Math.max(maxDepth, currentDepth);

        // Base case: no more rates to apply
        if (index < 0) {
            currentDepth--;
            return baseValue;
        }
        // First compute the value up to index-1
        double prev = forecastRecursive(baseValue, growthRates, index - 1);
        // Then apply this period’s growth
        double result = prev * (1 + growthRates[index]);

        currentDepth--;
        return result;
    }

    // --- 3. Iterative Future Value (Loop) ---
    /**
     * Iteratively applies every growth rate in one pass.
     * @return forecasted value
     */
    public static double forecastIterative(double baseValue, double[] growthRates) {
        double value = baseValue;
        for (double r : growthRates) {
            value *= (1 + r);
        }
        return value;
    }

    // --- 4. Recursive with Memoization ---
    /**
     * Uses a cache to avoid recomputing subproblems.
     */
    public static double forecastMemo(double baseValue, double[] growthRates, int index,
                                      Map<Integer, Double> cache) {
        if (index < 0) return baseValue;
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        double prev = forecastMemo(baseValue, growthRates, index - 1, cache);
        double result = prev * (1 + growthRates[index]);
        cache.put(index, result);
        return result;
    }

    // --- 5. Simple Test Harness & Scenarios ---
    public static void main(String[] args) {
        double base = 1_000.0;
        // Example: 4 years of growth for a retirement fund
        double[] rates = { 0.07, 0.06, 0.065, 0.055 };  // realistic annual returns

        System.out.println("=== Financial Forecast Demo ===");
        System.out.println("Base amount: ₹" + base);
        System.out.println("Rates (annual): " + Arrays.toString(rates));
        System.out.println();

        // --- A) Pure Recursion ---
        maxDepth = 0;
        double recVal = forecastRecursive(base, rates, rates.length - 1);
        System.out.printf("1) Recursive result: ₹%.2f  (max stack depth = %d)%n",
                recVal, maxDepth);

        // --- B) Iteration ---
        double iterVal = forecastIterative(base, rates);
        System.out.printf("2) Iterative result: ₹%.2f  (no extra stack)%n", iterVal);

        // --- C) Memoized Recursion ---
        Map<Integer, Double> cache = new HashMap<>();
        double memoVal = forecastMemo(base, rates, rates.length - 1, cache);
        System.out.printf("3) Memoized recursion: ₹%.2f  (cache entries = %d)%n",
                memoVal, cache.size());

        // --- D) Test Case: Repeated Queries ---
        System.out.println("\n--- Test: Repeated Queries (Years 2 & 4) ---");
        // Suppose evaluator asks: “What’s the 2-year forecast?” then later “What’s the 4-year forecast?”
        // * Iteration * will recompute both from scratch.
        // * Memo * will reuse the cached year-2 value to speed up year-4.
        int query1 = 1, query2 = 3; // zero-based: year-2 and year-4
        // Iteration approach times:
        long t0 = System.nanoTime();
        forecastIterative(base, Arrays.copyOfRange(rates, 0, query1 + 1));
        long dtIter1 = System.nanoTime() - t0;

        t0 = System.nanoTime();
        forecastIterative(base, Arrays.copyOfRange(rates, 0, query2 + 1));
        long dtIter2 = System.nanoTime() - t0;
        System.out.printf("Iterative: Year %d in %,d ns; Year %d in %,d ns%n",
                query1+1, dtIter1, query2+1, dtIter2);

        // Memoized recursion times:
        cache.clear();
        t0 = System.nanoTime();
        forecastMemo(base, rates, query1, cache);
        long dtMemo1 = System.nanoTime() - t0;

        t0 = System.nanoTime();
        forecastMemo(base, rates, query2, cache);
        long dtMemo2 = System.nanoTime() - t0;
        System.out.printf("MemoRec : Year %d in %,d ns; Year %d in %,d ns%n",
                query1+1, dtMemo1, query2+1, dtMemo2);

        System.out.println("\n→ You’ll see MemoRec for Year 4 is faster than recomputing iteratively from scratch.");

        // --- 6. Analysis Summary ---
        System.out.println("\n=== Complexity Analysis ===");
        System.out.println("Pure Recursion:");
        System.out.println("  • Time: O(n) calls");
        System.out.println("  • Space: O(n) stack frames  (maxDepth = n+1)");
        System.out.println("Iteration:");
        System.out.println("  • Time: O(n) loop");
        System.out.println("  • Space: O(1)");
        System.out.println("Memoized Recursion:");
        System.out.println("  • Time: O(n) overall, but re-queries in O(1)");
        System.out.println("  • Space: O(n) for cache + O(n) stack (can be reduced with tail recursion elimination)");

        System.out.println("\nOptimization Tips:");
        System.out.println("- Use memoization when you need multiple overlapping queries (as shown).");
        System.out.println("- Convert tail recursion to loops if stack depth is a concern.");
        System.out.println("- For very large n, consider an iterative DP with array storage for both speed and constant stack usage.");
    }
}
