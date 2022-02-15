package Part3ConsolePrinting;

public class PrinterThread extends Thread {
    private final String symbol;

    public PrinterThread(String symbol) {
        this.symbol = symbol;
    }

    public void run() {
        for (int line = 0; line < 100; line++) {
            for (int i = 0; i < 100; i++) {
                System.out.print(this.symbol);
            }
            System.out.println();
        }
    }
}