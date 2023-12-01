import java.util.Scanner;

public class Teller {
    private TellerHandler handler;

    public static void main(String[] args) {
        Teller teller = new Teller();
        teller.start();
    }

    private void start() {
        // Initialize TellerHandler
        handler = new TellerHandler();
        handler.run();
    }

    public TellerHandler getHandler() {
        return handler;
    }

    public void setHandler(TellerHandler handler) {
        this.handler = handler;
    }
}
