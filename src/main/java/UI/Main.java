package UI;

import WindowsAndFrames.MainFrame;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialOceanicIJTheme;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException {
        ArrayList<String> getraenke = new ArrayList<>();
        getraenke.add("Cola");
        getraenke.add("Fanta");
        getraenke.add("Orangen Saft");
        getraenke.add("Sauerkraut Saft");


        /*
        FlatMaterialOceanicIJTheme.setup();
        UIManager.setLookAndFeel(new FlatMaterialOceanicIJTheme());
        new MainFrame(getraenke);
         */

    }
}