package UI.WindowsAndFrames;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private MainWindow mainWindow;

    public MainFrame() throws IOException, InterruptedException {
        mainWindow = new MainWindow(this);

        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

}
