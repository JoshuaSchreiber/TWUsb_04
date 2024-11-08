import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.util.Scanner;

public class Senden extends Thread {
    public String sendData = "";
    @Override
    public void run() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            System.out.println(data);
            sendData = data;
            try {
                MindConnection.senden(sendData);
            } catch (TWUsbException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
