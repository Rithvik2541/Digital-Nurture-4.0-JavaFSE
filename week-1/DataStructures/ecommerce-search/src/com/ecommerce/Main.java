package com.ecommerce;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
    // --- 1. Data Model ---
    static class Product {
        int id;
        String name;
        String category;

        Product(int id, String name, String category) {
            this.id = id;
            this.name = name;
            this.category = category;
        }

        @Override
        public String toString() {
            return String.format("[id=%d, name=%s, category=%s]", id, name, category);
        }
    }

    // --- 2. Search Algorithms ---
    /** Linear search: O(n) in worst case */
    static int linearSearch(Product[] arr, int targetId) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].id == targetId) return i;
        }
        return -1;
    }

    /** Binary search: O(log n), requires sorted array */
    static int binarySearch(Product[] sortedArr, int targetId) {
        int low = 0, high = sortedArr.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (sortedArr[mid].id < targetId) {
                low = mid + 1;
            } else if (sortedArr[mid].id > targetId) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // --- 3. Demo & Performance Comparison ---
    public static void main(String[] args) {
        // a) Generate sample data
        int n = 100_000;  // you can lower this for quick runs
        Product[] products = new Product[n];
        for (int i = 0; i < n; i++) {
            products[i] = new Product(i + 1, "Product" + (i + 1), "Category" + ((i % 5) + 1));
        }

        // b) Pick a random product ID to search
        int targetId = new Random().nextInt(n) + 1;
        System.out.println("Searching for Product ID: " + targetId);

        // c) Linear search timing
        long t0 = System.nanoTime();
        int idx1 = linearSearch(products, targetId);
        long t1 = System.nanoTime();
        System.out.printf("Linear -> index=%d, time=%,d ns%n", idx1, (t1 - t0));

        // d) Binary search timing (sort first)
        Product[] sorted = Arrays.copyOf(products, products.length);
        Arrays.sort(sorted, Comparator.comparingInt(p -> p.id));

        t0 = System.nanoTime();
        int idx2 = binarySearch(sorted, targetId);
        t1 = System.nanoTime();
        System.out.printf("Binary -> index=%d, time=%,d ns%n", idx2, (t1 - t0));

        // e) Complexity recap
        System.out.println("\nBig-O Summary:");
        System.out.println("  Linear Search:  O(n)");
        System.out.println("  Binary Search:  O(log n)  (requires sorted data)");
    }
}
