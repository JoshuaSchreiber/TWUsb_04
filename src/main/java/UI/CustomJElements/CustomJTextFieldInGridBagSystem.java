package UI.CustomJElements;

import javax.swing.*;

public class CustomJTextFieldInGridBagSystem extends JTextField {
    CustomJElementInGridBagSystem<JTextField> customJElementInGridBagSystem;

    public CustomJTextFieldInGridBagSystem(boolean visibility, String backgroundColor, String foregroundColor, int preferredSizeWidth, int preferredSizeHeight, int fontSize, boolean boldFont, int gridx, int gridy, int gridHeight, int gridwidth, int insetsUp, int insetsDown, int insetsRight, int insetsLeft) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JTextField>(this, true, backgroundColor, foregroundColor, preferredSizeWidth, preferredSizeHeight, fontSize, boldFont, gridx, gridy, gridHeight, gridwidth, insetsUp, insetsDown, insetsRight, insetsLeft);

        this.setHorizontalAlignment(JLabel.CENTER);
    }

    public CustomJTextFieldInGridBagSystem(int preferredSizeWidth, int preferredSizeHeight, int gridx, int gridy, int gridHeight, int gridwidth, int insetsAllDirecitons) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JTextField>(this, preferredSizeWidth, preferredSizeHeight, gridx, gridy, gridHeight, gridwidth, insetsAllDirecitons);

        this.setHorizontalAlignment(JButton.CENTER);
    }

    public CustomJTextFieldInGridBagSystem(int preferredSizeWidth, int preferredSizeHeight, int gridx, int gridy, int gridHeight, int gridwidth, int insetsUp, int insetsDown, int insetsRight, int insetsLeft) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JTextField>(this, preferredSizeWidth, preferredSizeHeight, gridx, gridy, gridHeight, gridwidth, insetsUp, insetsDown, insetsRight, insetsLeft);

        this.setHorizontalAlignment(JButton.CENTER);
    }

    public CustomJElementInGridBagSystem<JTextField> getCustomJElementInGridBagSystem() {
        return customJElementInGridBagSystem;
    }

    public void setCustomJElementInGridBagSystem(CustomJElementInGridBagSystem<JTextField> customJElementInGridBagSystem) {
        this.customJElementInGridBagSystem = customJElementInGridBagSystem;
    }
}
