package Part4Counter;

public class Main {
    public static void main(String[] args) {
        startThreads();

        startThreadsSyncMethod();

        startThreadsSyncBlock();

        startThreadsSyncObject();
    }

    private static void startThreads() {
        var counter = new Counter();
        var thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });
        var thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrement();
            }
        });

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;

        System.out.println("Without synchronization: " + counter.count + " time: " + time);
    }

    private static void startThreadsSyncMethod() {
        var counter = new Counter();
        var thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementSyncMethod();
            }
        });
        var thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrementSyncMethod();
            }
        });

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;

        System.out.println("With sync methods: " + counter.count + " time: " + time);
    }

    private static void startThreadsSyncBlock() {
        var counter = new Counter();
        var thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementSyncBlock();
            }
        });
        var thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrementSyncBlock();
            }
        });

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;

        System.out.println("With sync block: " + counter.count + " time: " + time);
    }

    private static void startThreadsSyncObject() {
        var thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                Counter.incrementSyncObject();
            }
        });
        var thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                Counter.decrementSyncObject();
            }
        });

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;

        System.out.println("With sync object: " + Counter.countStatic + " time: " + time);
    }
}
