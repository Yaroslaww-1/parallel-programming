package Part4Counter;

public class Counter {
    public int count = 0;
    public static int countStatic = 0;
    private final Object blockingObject = new Object();

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }


    public synchronized void incrementSyncMethod() {
        count++;
    }

    public synchronized void decrementSyncMethod() {
        count--;
    }


    public void incrementSyncBlock() {
        synchronized (blockingObject) {
            count++;
        }
    }

    public void decrementSyncBlock() {
        synchronized (blockingObject) {
            count--;
        }
    }


    public static void incrementSyncObject() {
        synchronized (Counter.class) {
            Counter.countStatic++;
        }
    }

    public static void decrementSyncObject() {
        synchronized (Counter.class) {
            Counter.countStatic--;
        }
    }
}