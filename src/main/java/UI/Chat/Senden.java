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
    ArrayList<CustomJTextFieldInGridBagSystem> customJLabelInGridBagSystems;

    public Senden(CustomJTextFieldInGridBagSystem customJTextFieldInGridBagSystem, MainWindow mainWindow, ArrayList<CustomJTextFieldInGridBagSystem> customJLabelInGridBagSystems, boolean senden) {
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
                customJLabelInGridBagSystems.get(4).setText(customJLabelInGridBagSystems.get(2).getText());
                customJLabelInGridBagSystems.get(2).setText(sendData);
            }

        }
    }
}
