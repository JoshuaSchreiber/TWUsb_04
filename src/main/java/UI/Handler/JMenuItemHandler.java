package UI.Handler;

import javax.swing.*;

public class JMenuItemHandler extends JMenuItem {
    public JMenuItemHandler(String title, Runnable runnable){
        setText(title);
        addActionListener(e -> runnable.run());
    }


}
