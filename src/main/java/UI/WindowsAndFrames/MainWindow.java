package UI.WindowsAndFrames;

import CustomJElements.CustomJButtonInGridBagSystem;
import CustomJElements.CustomJLabelInGridBagSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainWindow extends Window {
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private JFrame jFrame;
    private int size = 4;
    int counter = 0;

    int oldRandom = 0;

    ArrayList<String> getranke;

    CustomJLabelInGridBagSystem monitor;

    public MainWindow(JFrame jFrame, ArrayList<String> getraenke) throws HeadlessException, IOException {
        super(jFrame);
        this.jFrame = jFrame;
        this.getranke = getraenke;
        jFrame.add(this);

        gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);

        addElements(getraenke);

        setVisible(true);
    }

    private void addElements(ArrayList<String> getraenke) {
        //Monitor
        monitor = new CustomJLabelInGridBagSystem(true, "#999090", "#FFFFFF", 1700, 500, 70,true, 1,1,1, 5, 5,5,5,5);
        gridBagLayout.setConstraints(monitor, monitor.getCustomJElementInGridBagSystem().getGridBagConstraints());
        monitor.setText("");
        this.add(monitor);

        //Main Button
        CustomJButtonInGridBagSystem mainButton = new CustomJButtonInGridBagSystem("#04E6E6", "#000000", 1000, 200,50, true, 1, 2, 1,1, 5,5,350,350);
        mainButton.setText("Click Me!");

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whenButtonClicked();
            }
        });
        gridBagLayout.setConstraints(mainButton, mainButton.getCustomJElementInGridBagSystem().getGridBagConstraints());
        this.add(mainButton);

    }

    public void whenButtonClicked(){
        Random random = new Random();
        int randomNumber = random.nextInt(getranke.size());
        System.out.println(randomNumber);
        if(randomNumber == oldRandom){
            whenButtonClicked();
        }
        oldRandom = randomNumber;
        if(counter == 0){
            monitor.setText(getranke.get(randomNumber));
            counter++;
        } else if(counter == 1) {
            monitor.setText(monitor.getText() + "   +   " + getranke.get(randomNumber));
            counter--;
        }
    }
    public GridBagLayout getGridBagLayout() {
        return gridBagLayout;
    }

    public void setGridBagLayout(GridBagLayout gridBagLayout) {
        this.gridBagLayout = gridBagLayout;
    }
}

