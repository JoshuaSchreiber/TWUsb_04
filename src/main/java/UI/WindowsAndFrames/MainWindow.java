package UI.WindowsAndFrames;



import UI.Chat.Empfangen;
import UI.Chat.Senden;
import UI.CustomJElements.CustomJButtonInGridBagSystem;
import UI.CustomJElements.CustomJLabelInGridBagSystem;
import UI.CustomJElements.CustomJTextFieldInGridBagSystem;
import de.wenzlaff.twusb.schnittstelle.TWUsb;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class MainWindow extends Window {
    private GridBagLayout gridBagLayout;
    private CustomJButtonInGridBagSystem sendButton;
    private CustomJTextFieldInGridBagSystem sendTextField;

    ArrayList<CustomJLabelInGridBagSystem> monitor = new ArrayList<>();

    private boolean send = false;

    public MainWindow(JFrame jFrame) throws HeadlessException, IOException, InterruptedException {
        super(jFrame);
        jFrame.add(this);

        gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);

        addElements();
        setBackground(Color.decode("#000000"));
        setVisible(true);



        createChat(sendTextField);

    }

    private void createChat(CustomJTextFieldInGridBagSystem customJTextFieldInGridBagSystem) {
        try {
            TWUsb.OpenDevice(TWUsb.ADDRESSE_0);
            TWUsb.ClearAllDigital();
            Thread.sleep(100);
            TWUsb.ClearAllAnalog();
            Thread.sleep(100);

            // *****************************************************************

            System.out.println("Start");


            Senden senden  = new Senden(customJTextFieldInGridBagSystem, this, monitor, send);
            senden.start();

            Empfangen empfangen = new Empfangen(monitor);
            empfangen.start();



        } catch (TWUsbException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void addElements() {
        //Monitor

        monitor.add(0, new CustomJLabelInGridBagSystem(true, "#B5DAD7", "#FFFFFF", 500, 50, 35,true, 1,0,1, 1, 5,5,5,5));
        monitor.add(1, new CustomJLabelInGridBagSystem(true, "#B5DAD7", "#FFFFFF", 500, 50, 35,true, 2,0,1, 1, 5,5,5,5));

        gridBagLayout.setConstraints(monitor.get(0), monitor.get(0).getCustomJElementInGridBagSystem().getGridBagConstraints());
        monitor.get(0).setText("You");
        this.add(monitor.get(0));

        gridBagLayout.setConstraints(monitor.get(1), monitor.get(1).getCustomJElementInGridBagSystem().getGridBagConstraints());
        monitor.get(1).setText("Sender");
        this.add(monitor.get(1));

        for(int i = 3; i < 6; i = i+2){
            monitor.add(i-1, new CustomJLabelInGridBagSystem(true, "#FFDAC1", "#FFFFFF", 500, 100, 70,true, 1,i,1, 1, 5,5,5,5));
            monitor.add(i, new CustomJLabelInGridBagSystem(true, "#999090", "#FFFFFF", 500, 100, 70,true, 2,i,1, 1, 5,5,5,5));

            gridBagLayout.setConstraints(monitor.get(i-1), monitor.get(i-1).getCustomJElementInGridBagSystem().getGridBagConstraints());
            monitor.get(i-1).setText("");
            this.add(monitor.get(i-1));

            gridBagLayout.setConstraints(monitor.get(i), monitor.get(i).getCustomJElementInGridBagSystem().getGridBagConstraints());
            monitor.get(i).setText("");
            this.add(monitor.get(i));
        }



        CustomJTextFieldInGridBagSystem input = new CustomJTextFieldInGridBagSystem(true,"#04E6E6", "#000000", 300, 50,50, true, 1, 6, 1,2, 0,5,50,50);
        input.setText("Sent");
        gridBagLayout.setConstraints(input, input.getCustomJElementInGridBagSystem().getGridBagConstraints());
        this.add(input);
        sendTextField = input;


        CustomJButtonInGridBagSystem senden = new CustomJButtonInGridBagSystem("#04E6E6", "#000000", 50, 50,20, true, 3, 6, 1,1, 100,5,5,5);
        senden.setText("Sent");
        senden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send = true;
            }
        });
        gridBagLayout.setConstraints(senden, senden.getCustomJElementInGridBagSystem().getGridBagConstraints());
        this.add(senden);
        sendButton = senden;
    }

    public GridBagLayout getGridBagLayout() {
        return gridBagLayout;
    }

    public void setGridBagLayout(GridBagLayout gridBagLayout) {
        this.gridBagLayout = gridBagLayout;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }
}

