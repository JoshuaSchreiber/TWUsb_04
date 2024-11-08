package UI.CustomJElements;

import javax.swing.*;

public class CustomJPanelInGridBagSystem extends JPanel {
    CustomJElementInGridBagSystem<JPanel> customJElementInGridBagSystem;

    public CustomJPanelInGridBagSystem(boolean visibility, String backgroundColor, String foregroundColor, int preferredSizeWidth, int preferredSizeHeight, int fontSize, boolean boldFont, int gridx, int gridy, int gridHeight, int gridwidth, int insetsUp, int insetsDown, int insetsRight, int insetsLeft) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JPanel>(this, true, backgroundColor, foregroundColor, preferredSizeWidth, preferredSizeHeight, fontSize, boldFont, gridx, gridy, gridHeight, gridwidth, insetsUp, insetsDown, insetsRight, insetsLeft);
    }

    public CustomJPanelInGridBagSystem(int preferredSizeWidth, int preferredSizeHeight, int gridx, int gridy, int gridHeight, int gridwidth, int insetsAllDirecitons) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JPanel>(this, preferredSizeWidth, preferredSizeHeight, gridx, gridy, gridHeight, gridwidth, insetsAllDirecitons);
    }

    public CustomJPanelInGridBagSystem(int preferredSizeWidth, int preferredSizeHeight, int gridx, int gridy, int gridHeight, int gridwidth, int insetsUp, int insetsDown, int insetsRight, int insetsLeft) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JPanel>(this, preferredSizeWidth, preferredSizeHeight, gridx, gridy, gridHeight, gridwidth, insetsUp, insetsDown, insetsRight, insetsLeft);
    }

    public CustomJElementInGridBagSystem<JPanel> getCustomJElementInGridBagSystem() {
        return customJElementInGridBagSystem;
    }

    public void setCustomJElementInGridBagSystem(CustomJElementInGridBagSystem<JPanel> customJElementInGridBagSystem) {
        this.customJElementInGridBagSystem = customJElementInGridBagSystem;
    }
}