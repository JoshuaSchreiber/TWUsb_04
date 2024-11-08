package UI.CustomJElements;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CustomJLabelInGridBagSystem extends JLabel {

    private PropertyChangeListener listener;
    CustomJElementInGridBagSystem<JLabel> customJElementInGridBagSystem;

    @Override
    public void setText(String text){
        String oldValue = getText();
        super.setText(text);
        firePropertyChange("text", oldValue, text);
    }

    public void addTextChangeListener(PropertyChangeListener listener) {
        this.listener = listener;
    }

    public void removeTextChangeListener(){
        listener = null;
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (listener != null) {
            listener.propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
        }
    }

    public CustomJLabelInGridBagSystem(boolean visibility, String backgroundColor, String foregroundColor, int preferredSizeWidth, int preferredSizeHeight, int fontSize, boolean boldFont, int gridx, int gridy, int gridHeight, int gridwidth, int insetsUp, int insetsDown, int insetsRight, int insetsLeft) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JLabel>(this, true, backgroundColor, foregroundColor, preferredSizeWidth, preferredSizeHeight, fontSize, boldFont, gridx, gridy, gridHeight, gridwidth, insetsUp, insetsDown, insetsRight, insetsLeft);

        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    public CustomJLabelInGridBagSystem(int preferredSizeWidth, int preferredSizeHeight, int gridx, int gridy, int gridHeight, int gridwidth, int insetsAllDirecitons) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JLabel>(this, preferredSizeWidth, preferredSizeHeight, gridx, gridy, gridHeight, gridwidth, insetsAllDirecitons);

        this.setVerticalAlignment(JButton.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
    }

    public CustomJLabelInGridBagSystem(int preferredSizeWidth, int preferredSizeHeight, int gridx, int gridy, int gridHeight, int gridwidth, int insetsUp, int insetsDown, int insetsRight, int insetsLeft) {
        customJElementInGridBagSystem = new CustomJElementInGridBagSystem<JLabel>(this, preferredSizeWidth, preferredSizeHeight, gridx, gridy, gridHeight, gridwidth, insetsUp, insetsDown, insetsRight, insetsLeft);

        this.setVerticalAlignment(JButton.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
    }

    public CustomJElementInGridBagSystem<JLabel> getCustomJElementInGridBagSystem() {
        return customJElementInGridBagSystem;
    }

    public void setCustomJElementInGridBagSystem(CustomJElementInGridBagSystem<JLabel> customJElementInGridBagSystem) {
        this.customJElementInGridBagSystem = customJElementInGridBagSystem;
    }
}

