import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.util.Scanner;

public class Senden extends Thread {
    public String sendData = "";
    public MindConnection sendConnection = new MindConnection();
    @Override
    public void run() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            sendData = data;
            try {
                sendConnection.senden(sendData);
            } catch (TWUsbException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
