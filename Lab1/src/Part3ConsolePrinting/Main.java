package Part3ConsolePrinting;

public class Main {
    public static void main(String[] args) {
//        startThreads();

        startAlternateThreads();
    }

    private static void startThreads() {
        var thread1 = new PrinterThread("|");
        var thread2 = new PrinterThread("-");

        thread1.start();
        thread2.start();
    }

    private static void startAlternateThreads() {
        var thread1 = new PrinterAlternateThread("|");
        var thread2 = new PrinterAlternateThread("-");

        thread1.start();
        thread2.start();
    }
}
