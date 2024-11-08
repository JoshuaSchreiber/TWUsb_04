import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.util.ArrayList;

public class Empfangen extends Thread {
    private String gottenData = "";
    @Override
    public void run() {
        while (true) {
            ArrayList<Integer> data = null;
            try {
                data = MindConnection.waitingOnReceiving();
            } catch (TWUsbException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String dataString = MindConnection.dataToString(data);

            gottenData = dataString;
            System.out.println(gottenData);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getGottenData() {
        return gottenData;
    }

    public void setGottenData(String gottenData) {
        this.gottenData = gottenData;
    }
}
