package Part3ConsolePrinting;

public class PrinterAlternateThread extends Thread {
    private final String symbol;
    private static Object object = new Object();

    public PrinterAlternateThread(String symbol) {
        this.symbol = symbol;
    }

    public void run() {
        for (int line = 0; line < 100; line++) {
            for (int i = 0; i < 100; i++) {
                synchronized (object) {
                    object.notify();
                    System.out.print(this.symbol);
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}