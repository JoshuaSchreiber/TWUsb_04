package UI.Chat;

import UI.CustomJElements.CustomJLabelInGridBagSystem;
import UI.CustomJElements.CustomJTextFieldInGridBagSystem;
import UI.WindowsAndFrames.MainWindow;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.util.ArrayList;
import java.util.Scanner;

public class Senden extends Thread {
    public String sendData = "";
    public MindConnection sendConnection = new MindConnection();
    CustomJTextFieldInGridBagSystem customJTextFieldInGridBagSystem;
    MainWindow mainWindow;
    boolean senden;
    ArrayList<CustomJLabelInGridBagSystem> customJLabelInGridBagSystems;

    public Senden(CustomJTextFieldInGridBagSystem customJTextFieldInGridBagSystem, MainWindow mainWindow, ArrayList<CustomJLabelInGridBagSystem> customJLabelInGridBagSystems, boolean senden) {
        this.customJTextFieldInGridBagSystem = customJTextFieldInGridBagSystem;
        this.senden = senden;
        this.mainWindow = mainWindow;
        this.customJLabelInGridBagSystems = customJLabelInGridBagSystems;
    }
    @Override
    public void run() {
        while (true) {
            if(mainWindow.isSend()){
                String data = customJTextFieldInGridBagSystem.getText();
                customJTextFieldInGridBagSystem.setText("");

                ArrayList<Character> chars = new ArrayList<Character>();
                for (char c : data.toCharArray()) {
                    chars.add(c);
                }

                sendData = data;
                try {
                    sendConnection.senden(sendData);
                } catch (TWUsbException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mainWindow.setSend(false);
                customJLabelInGridBagSystems.get(3).setText(customJLabelInGridBagSystems.get(1).getText());
                customJLabelInGridBagSystems.get(1).setText(sendData);
            }

        }
    }
}
