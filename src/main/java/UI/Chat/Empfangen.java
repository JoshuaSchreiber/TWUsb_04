package UI.Chat;

import UI.CustomJElements.CustomJLabelInGridBagSystem;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.util.ArrayList;

public class Empfangen extends Thread {
    private String gottenData = "";
    public MindConnection receivingConnection = new MindConnection();
    ArrayList<CustomJLabelInGridBagSystem> customJLabelInGridBagSystem;

    public Empfangen(ArrayList<CustomJLabelInGridBagSystem> customJLabelInGridBagSystem) {
        this.customJLabelInGridBagSystem = customJLabelInGridBagSystem;
    }
    @Override
    public void run() {
        while (true) {
            ArrayList<Integer> data = null;
            try {
                data = receivingConnection.waitingOnReceiving();
            } catch (TWUsbException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String dataString = receivingConnection.dataToString(data);

            gottenData = dataString;
            customJLabelInGridBagSystem.get(2).setText(customJLabelInGridBagSystem.get(0).getText());
            customJLabelInGridBagSystem.get(0).setText(gottenData);
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
