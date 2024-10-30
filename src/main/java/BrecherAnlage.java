import de.wenzlaff.twusb.schnittstelle.TWUsb;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

public class BrecherAnlage {
    static Random random = new Random();

    public static void main(String[] args) {
        try {
            TWUsb.OpenDevice(TWUsb.ADDRESSE_0);
            TWUsb.ClearAllDigital();
            Thread.sleep(100);
            TWUsb.ClearAllAnalog();
            Thread.sleep(100);

            // *****************************************************************




            /**
             * Aktions:
             * Min:
             *  Motor M3 und M4
             *  Schieber
             * NichtMin:
             *  M3 aus
             *  Schieber zu
             * NichtMiddle:
             *  M1 und M2
             *  Felsbrocken einfüllung notwendig
             * Max:
             *  M1 und M2 aus
             *  Felsbrocken nicht mehr einfüllen
             * NotAus
             *  Alle motoren aus
             *  Schieber aus
             *
             *
             * Wenn M3 Überlastet
             *  Schieber schließen
             *  M3 aus
             *
             */

            boolean[] output = {false, false, false, false, false, false, false, false};
            //M1, M2, M3, M4, Schieber, Min, Middle, Max
            int[] input = new int[3];
            //Start, Stop
            int fillstand = 0;
            boolean felsbrockenEinfuellen = false;
            boolean m3ueberlastet = false;

            boolean isAktive = false;

            boolean goOn = true;
            for(int i = 0; i < 10000; i++){
                // Getting Input
                int readAllDigital = TWUsb.ReadAllDigital();
                input[0] = readAllDigital & 1;
                input[1] = readAllDigital & 2;
                input[2] = readAllDigital & 4;


                //Initalisation and Force Stop
                if(input[0] == 1){
                    output[0] = true;
                    output[1] = true;
                    output[2] = true;
                    isAktive = true;
                }
                if(input[1] == 2){
                    output[0] = false;
                    output[1] = false;
                    output[2] = false;
                    output[3] = false;
                    output[4] = false;
                    goOn = false;
                }
                if(input[2] == 4){
                    output[4] = false;
                    output[2] = false;
                }

                // Aktions output: M1, M2, M3, M4, Schieber, Min, Middle, Max
                if(output[5]){
                    output[2] = true;
                    output[3] = true;
                    //Hier Theoretisch vor dem Öffnen der Klappe kurz verzögerung
                    output[4] = true;
                } else {
                    output[3] = false;
                    output[4] = false;
                }

                if(isAktive){
                    if (!output[6]){
                        output[0] = true;
                        output[1] = true;
                        felsbrockenEinfuellen = true;
                    }
                }
                if(output[7]){
                    output[0] = false;
                    output[1] = false;
                    felsbrockenEinfuellen = false;
                }




                // AktionHandeling
                // Fillstand
                if(fillstand > 90){
                    output[7] = true;
                } else {
                    output[7] = false;
                }
                if(fillstand > 45){
                    output[6] = true;
                } else {
                    output[6] = false;
                }
                if(fillstand > 15){
                    output[5] = true;
                } else {
                    output[5] = false;
                }
                if(output[0] && output[1] && fillstand < 100){ // Wenn M1 und M2 laufen und der Füllstand kleiner als 100 Prozent ist
                    fillstand = fillstand +2;
                }
                if(output[2] && output[3] && fillstand > 0){
                    fillstand--;
                }
                if(felsbrockenEinfuellen){
                    System.out.println("Felsboken sollen eingefüllt werden!");
                }


                System.out.println(fillstand);
                writeAllDigitalOutput(output);
                Thread.sleep(500);
            }

            // *****************************************************************

            TWUsb.CloseDevice();

        } catch (TWUsbException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void writeAllDigitalOutput(boolean[] output) throws TWUsbException {
        int worth = 0;
        for (int i = 0; i < output.length; i++) {
            if (output[i]) {
                worth += Math.pow(2, i); // Math.pow(2, i) calculates 2^i
            }
        }

        TWUsb.WriteAllDigital(worth);
    }

    public static int newLowerHole(){
        return random.nextInt(70)+1;

    }
    public static int newHigherHole(){
        return random.nextInt(30)+1 + 70;
    }
}
