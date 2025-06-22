import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Singleton {
    // ---  Singleton ---
    static class Logger {
        private static volatile Logger instance;
        private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        private Logger() {
            System.out.println("Logger initialized at " + LocalDateTime.now().format(fmt));
        }

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

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Singleton Logger");

        // identity check
        Logger a = Logger.getInstance();
        a.log("INFO",  "First message");
        Logger b = Logger.getInstance();
        System.out.println("Same instance? " + (a == b));

        // thread-safety check
        System.out.println("\nSpawning threads:");
        Runnable task = () -> Logger.getInstance().log("DEBUG", "log from " + Thread.currentThread().getName());
        Thread[] ts = { new Thread(task, "T1"), new Thread(task, "T2"), new Thread(task, "T3") };
        for (Thread t : ts) t.start();
        for (Thread t : ts) t.join();

        Logger c = Logger.getInstance();
        System.out.println("\nStill same? " + (a == c));

        System.out.println("\n--- Analysis ---");
        System.out.println("1) Private ctor → no outside instantiation.");
        System.out.println("2) Double-checked locking → thread-safe.");
        System.out.println("3) Only one \"Logger initialized\" printed → singleton holds.");
    }
}
