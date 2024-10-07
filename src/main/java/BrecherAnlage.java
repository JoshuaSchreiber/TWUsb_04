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


            boolean[] output = {true, true, false, false, false, false, false, false};
            //M1, M2, M3, M4, Schieber, Min, Middle, Max
            int[] input = new int[2];
            //Start, Stop
            int fillstand = 0;

            for(int i = 0; i < 10000; i++){

                // Getting Input
                int readAllDigital = TWUsb.ReadAllDigital();
                input[0] = readAllDigital & 1;
                input[1] = readAllDigital & 2;
                input[2] = readAllDigital & 4;
                input[3] = readAllDigital & 8;
                input[4] = readAllDigital & 16;

                // Aktions
                if(output[4]){
                    output[2] = true;
                    output[3] = true;
                } else {
                    output[2] = false;
                    output[3] = false;
                }
                if(output[5]){} else {
                    output[0] = true;
                    output[1] = true;
                }
                if(output[6]){
                    output[0] = false;
                    output[1] = false;
                }
                if(output[7]){
                    output[4] = false;
                    output[2] = false;
                }
                if(input[4] > 0){
                    output[1] = false;
                    output[2] = false;
                    output[3] = false;
                    output[4] = false;
                }



                // AktionHandeling
                // Fillstand
                if(output[0] && output[1] && fillstand < 100){ // Wenn M1 und M2 laufen und der Füllstand kleiner als 100 Prozent ist
                    fillstand++;
                } else if (fillstand == 100){
                    output[1] = false;
                    output[2] = false;
                }
                if(output[2] && output[3] && fillstand > 0){
                    fillstand--;
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
