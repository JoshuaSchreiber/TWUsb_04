package UI.CustomJElements;

import javax.swing.*;

public class CustomJButtonInGridBagSystem extends JButton{
    CustomJElementInGridBagSystem<JButton> customJElementInGridBagSystem;

    public CustomJButtonInGridBagSystem(String backgroundColor, String foregroundColor, int preferredSizeWidth, int preferredSizeHeight, int fontSize, boolean boldFont, int gridx, int gridy, int gridHeight, int gridwidth, int insetsUp, int insetsDown, int insetsRight, int insetsLeft) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JButton>(this, true, backgroundColor, foregroundColor, preferredSizeWidth, preferredSizeHeight, fontSize, boldFont, gridx, gridy, gridHeight, gridwidth, insetsUp, insetsDown, insetsRight, insetsLeft);

        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    public CustomJButtonInGridBagSystem(int preferredSizeWidth, int preferredSizeHeight, int gridx, int gridy, int gridHeight, int gridwidth, int insetsAllDirecitons) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JButton>(this, preferredSizeWidth, preferredSizeHeight, gridx, gridy, gridHeight, gridwidth, insetsAllDirecitons);

        this.setVerticalAlignment(JButton.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
    }

    public CustomJButtonInGridBagSystem(int preferredSizeWidth, int preferredSizeHeight, int gridx, int gridy, int gridHeight, int gridwidth, int insetsUp, int insetsDown, int insetsRight, int insetsLeft) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JButton>(this, preferredSizeWidth, preferredSizeHeight, gridx, gridy, gridHeight, gridwidth, insetsUp, insetsDown, insetsRight, insetsLeft);

        this.setVerticalAlignment(JButton.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
    }

    public CustomJElementInGridBagSystem<JButton> getCustomJElementInGridBagSystem() {
        return customJElementInGridBagSystem;
    }

    public void setCustomJElementInGridBagSystem(CustomJElementInGridBagSystem<JButton> customJElementInGridBagSystem) {
        this.customJElementInGridBagSystem = customJElementInGridBagSystem;
    }

}
