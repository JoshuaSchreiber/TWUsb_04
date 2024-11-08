package UI.WindowsAndFrames;


import UI.Handler.JMenuItemHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public abstract class Window extends JPanel {
    private static final int KEY_F11 = KeyEvent.VK_F11;

    public double height;
    public static double width;
    final JFrame mainFrame;
    Image backgroundImage;
    JMenuBar mainMenuBar;
    JMenu settings;

    public Window(JFrame mainFrame) throws IOException {
        super();
        this.mainFrame = mainFrame;
        initializeGameWindow();
        mainFrame.setFocusable(true);
    }

    private void initializeGameWindow() {
        addF11KeyListener();
        registerWindowListenerExit();
        createMenuBar();
        mainFrame.setJMenuBar(mainMenuBar);
        mainFrame.validate();
        mainFrame.repaint();
    }

    public void registerWindowListenerExit() {
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });
    }

    public void exitApplication() {
        System.exit(0);
    }

    private void addF11KeyListener() {
        mainFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleF11Key(e);
            }
        });
    }

    private void handleF11Key(KeyEvent e) {
        if (e.getKeyCode() == 36) {
            toggleFullScreenMode();
        }
    }

    private void toggleFullScreenMode() {
        if (mainMenuBar.isVisible()) {
            hideMenuBarAndSetUndecorated(true);
        } else {
            showMenuBarAndSetUndecorated(false);
        }
    }

    private void hideMenuBarAndSetUndecorated(boolean undecorated) {
        mainMenuBar.setVisible(false);
        mainFrame.dispose();
        mainFrame.setUndecorated(true);
        mainFrame.setVisible(true);
        mainFrame.repaint();
    }

    private void showMenuBarAndSetUndecorated(boolean undecorated) {
        mainFrame.dispose();
        mainFrame.setUndecorated(false);
        mainFrame.setVisible(true);
        mainFrame.repaint();
    }

    public void createMenuBar() {
        mainMenuBar = new JMenuBar();
        mainFrame.setJMenuBar(mainMenuBar);

        mainMenuBar.add(createSettingsItem(mainMenuBar));
    }

    public JMenuItem createSettingsItem(JMenuBar mainMenuBar) {
        settings = new JMenu("Menu");

        settings.add(new JMenuItemHandler("Close", () -> System.exit(0)));

        JMenu fullGameWindow = new JMenu("Full WindowsAndFrames.Window");
        JMenu infoItem = new JMenu("To get the Menu press F11!");
        fullGameWindow.add(infoItem);
        infoItem.add(new JMenuItemHandler("  X", () -> {
            mainMenuBar.setVisible(false);
            mainFrame.dispose();
            mainFrame.setUndecorated(true);
            mainFrame.setVisible(true);
        }));
        settings.add(fullGameWindow);

        return settings;
    }
}
