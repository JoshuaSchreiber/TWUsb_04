import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.util.ArrayList;
import java.util.Scanner;

public class Senden extends Thread {
    public String sendData = "";
    public MindConnection sendConnection = new MindConnection();
    @Override
    public void run() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();

            ArrayList<Character> chars = new ArrayList<Character>();
            for (char c : data.toCharArray()) {
                chars.add(c);
            }
            if(chars.get(0) == 'W' && chars.get(1) == 'r' && chars.get(2) == 'i' &&chars.get(3) == 't' && chars.get(4) == 'e' && chars.get(5) == ':'){
                data = "";
                for(int i = 6; i < chars.size(); i++){
                    data += chars.get(i);
                }
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
}
