import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A thread-safe Singleton Logger demo.
 * File: singleton/src/main/java/designprinciples/singleton/SingletonDemo.java
 */
public class Singleton {
    // --- 1. The Singleton ---
    static class Logger {
        private static volatile Logger instance;
        private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // private constructor
        private Logger() {
            System.out.println("Logger initialized at " + LocalDateTime.now().format(fmt));
        }

        // double-checked locking accessor
        public static Logger getInstance() {
            if (instance == null) {
                synchronized (Logger.class) {
                    if (instance == null) {
                        instance = new Logger();
                    }
                }
            }
            return instance;
        }

        public void log(String level, String msg) {
            String ts = LocalDateTime.now().format(fmt);
            System.out.printf("[%s] %-5s %s%n", ts, level, msg);
        }
    }

    // --- 2. Demo & sanity checks ---
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Singleton Logger Demo ===");

        // a) identity check
        Logger a = Logger.getInstance();
        a.log("INFO",  "First message");
        Logger b = Logger.getInstance();
        System.out.println("Same instance? " + (a == b));

        // b) thread-safety check
        System.out.println("\nSpawning threads:");
        Runnable task = () -> Logger.getInstance().log("DEBUG", "log from " + Thread.currentThread().getName());
        Thread[] ts = { new Thread(task, "T1"), new Thread(task, "T2"), new Thread(task, "T3") };
        for (Thread t : ts) t.start();
        for (Thread t : ts) t.join();

        // c) final identity check
        Logger c = Logger.getInstance();
        System.out.println("\nStill same? " + (a == c));

        // d) Analysis notes
        System.out.println("\n--- Analysis ---");
        System.out.println("1) Private ctor → no outside instantiation.");
        System.out.println("2) Double-checked locking → thread-safe.");
        System.out.println("3) Only one \"Logger initialized\" printed → singleton holds.");
    }
}
