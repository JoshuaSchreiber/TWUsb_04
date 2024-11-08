package UI.CustomJElements;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class CustomJElementInGridBagSystem<E extends JComponent>{

    private E jComponent;
    private boolean visibility;
    private String backgroundColor;
    private String foregroundColor;
    private Dimension dimension;
    private int fontSize;
    private boolean boldFont;
    private int gridx;
    private int gridy;

    private int gridHeight;
    private int gridWidth;
    private int insetsUp;
    private int insetsDown;
    private int insetsRight;
    private int insetsLeft;
    private GridBagConstraints gridBagConstraints;

    public CustomJElementInGridBagSystem(E jComponent, boolean visibility, String backgroundColor, String foregroundColor, int preferredSizeWidth, int preferredSizeHeight, int fontSize, boolean boldFont, int gridx, int gridy, int gridHeight, int gridwidth, int insetsUp, int insetsDown, int insetsRight, int insetsLeft) {
        this.jComponent = jComponent;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.dimension = new Dimension(preferredSizeWidth, preferredSizeHeight);
        this.fontSize = fontSize;
        this.boldFont = boldFont;
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridWidth = gridwidth;
        this.gridHeight = gridHeight;
        this.insetsUp = insetsUp;
        this.insetsDown = insetsDown;
        this.insetsRight = insetsRight;
        this.insetsLeft = insetsLeft;
        this.visibility = visibility;

        setBackgroundColor(backgroundColor);
        setForegroundColor(foregroundColor);
        jComponent.setPreferredSize(new Dimension(preferredSizeWidth, preferredSizeHeight));
        jComponent.setOpaque(true);
        defineGridBagConstraints(gridx, gridy, gridwidth, gridHeight, insetsUp, insetsDown, insetsRight, insetsLeft);
        resetFontsize();


        jComponent.setVisible(true);
    }

    public CustomJElementInGridBagSystem(E jComponent, int preferredSizeWidth, int preferredSizeHeight, int gridx, int gridy, int gridHeight, int gridwidth, int insetsAllDirecitons){
        this.jComponent = jComponent;
        this.backgroundColor = "#000000";
        this.foregroundColor = "#FFFFFF";
        this.dimension = new Dimension(preferredSizeWidth, preferredSizeHeight);
        this.fontSize = 20;
        this.boldFont = true;
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridWidth = gridwidth;
        this.gridHeight = gridHeight;
        this.insetsUp = insetsAllDirecitons;
        this.insetsDown = insetsAllDirecitons;
        this.insetsRight = insetsAllDirecitons;
        this.insetsLeft = insetsAllDirecitons;
        this.visibility = true;


        setBackgroundColor("#000000");
        setForegroundColor("#FFFFFF");
        jComponent.setPreferredSize(new Dimension(preferredSizeWidth, preferredSizeHeight));
        jComponent.setOpaque(true);
        defineGridBagConstraints(gridx, gridy, gridwidth, gridHeight, insetsAllDirecitons, insetsAllDirecitons,insetsAllDirecitons, insetsAllDirecitons);
        resetFontsize();

        jComponent.setVisible(true);
    }

    public CustomJElementInGridBagSystem(E jComponent, int preferredSizeWidth, int preferredSizeHeight, int gridx, int gridy, int gridHeight, int gridwidth, int insetsUp, int insetsDown, int insetsRight, int insetsLeft){
        this.jComponent = jComponent;
        this.backgroundColor = "#000000";
        this.foregroundColor = "#FFFFFF";
        this.dimension = new Dimension(preferredSizeWidth, preferredSizeHeight);
        this.fontSize = 20;
        this.boldFont = true;
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridWidth = gridwidth;
        this.gridHeight = gridHeight;
        this.insetsUp = insetsUp;
        this.insetsDown = insetsDown;
        this.insetsRight = insetsRight;
        this.insetsLeft = insetsLeft;
        this.visibility = true;

        setBackgroundColor("#000000");
        setForegroundColor("#FFFFFF");
        jComponent.setPreferredSize(new Dimension(preferredSizeWidth, preferredSizeHeight));
        setFontSize(20);
        jComponent.setOpaque(true);
        defineGridBagConstraints(gridx, gridy, gridwidth, gridHeight, insetsUp, insetsDown, insetsRight, insetsLeft);
        resetFontsize();

        jComponent.setVisible(true);
    }


    public void defineGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, int insetsUp, int insetsDown, int insetsRight, int insetsLeft){
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(insetsUp, insetsLeft, insetsDown, insetsRight);
    }

    public void defineGridBagConstraints(){
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridWidth;
        gridBagConstraints.gridheight = gridHeight;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(insetsUp, insetsLeft, insetsDown, insetsRight);
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
        jComponent.setVisible(visibility);
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        jComponent.setBackground(Color.decode(backgroundColor));
        jComponent.setForeground(Color.decode(foregroundColor));
        if (!backgroundColor.equals("#000000")) {
            jComponent.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }else{
            jComponent.setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));
        }
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
        jComponent.setForeground(Color.decode(foregroundColor));

    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
        jComponent.setPreferredSize(new Dimension(dimension));
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {

        resetFontsize();
    }

    public boolean getBoldFont() {
        return boldFont;
    }

    public void settBoldFont(boolean boldFont) {
        this.boldFont = boldFont;
        resetFontsize();
    }

    public int getGridx() {
        return gridx;
    }

    public void setGridx(int gridx) {
        this.gridx = gridx;
        defineGridBagConstraints();
    }

    public int getGridy() {
        return gridy;
    }

    public void setGridy(int gridy) {
        this.gridy = gridy;
        defineGridBagConstraints();
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
        defineGridBagConstraints();
    }

    public int getInsetsUp() {
        return insetsUp;
    }

    public void setInsetsUp(int insetsUp) {
        this.insetsUp = insetsUp;
        defineGridBagConstraints();
    }

    public int getInsetsDown() {
        return insetsDown;
    }

    public void setInsetsDown(int insetsDown) {
        this.insetsDown = insetsDown;
        defineGridBagConstraints();
    }

    public int getInsetsRight() {
        return insetsRight;
    }

    public void setInsetsRight(int insetsRight) {
        this.insetsRight = insetsRight;
        defineGridBagConstraints();
    }

    public int getInsetsLeft() {
        return insetsLeft;
    }

    public void setInsetsLeft(int insetsLeft) {
        this.insetsLeft = insetsLeft;
        defineGridBagConstraints();
    }

    public GridBagConstraints getGridBagConstraints() {
        return gridBagConstraints;
    }

    public void setGridBagConstraints(GridBagConstraints gridBagConstraints) {
        this.gridBagConstraints = gridBagConstraints;
    }

    public E getjComponent() {
        return jComponent;
    }

    public void setjComponent(E jComponent) {
        this.jComponent = jComponent;
    }

    public boolean isBoldFont() {
        return boldFont;
    }

    public void setBoldFont(boolean boldFont) {
        this.boldFont = boldFont;
        this.setBoldFont(true);
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
        defineGridBagConstraints();
    }

    public void resetFontsize(){
        if(boldFont == true){
            jComponent.setFont(new Font("Arial", Font.BOLD, fontSize));
        }else{
            jComponent.setFont(new Font("Arial", Font.BOLD, fontSize));
        }
    }
}
