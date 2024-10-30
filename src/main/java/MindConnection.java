import de.wenzlaff.twusb.schnittstelle.TWUsb;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

public class MindConnection {
    static Random random = new Random();

    public static void main(String[] args) {
        try {
            TWUsb.OpenDevice(TWUsb.ADDRESSE_0);
            TWUsb.ClearAllDigital();
            Thread.sleep(100);
            TWUsb.ClearAllAnalog();
            Thread.sleep(100);

            // *****************************************************************

            boolean[] output = {false, false, false, false, false, false, false, false};
            int[] input = new int[5];
            for(int i = 0; i < 10000; i++){

                // Getting Input
                int readAllDigital = TWUsb.ReadAllDigital();
                input[0] = readAllDigital & 1;
                input[1] = readAllDigital & 2;
                input[2] = readAllDigital & 4;
                input[3] = readAllDigital & 8;
                input[4] = readAllDigital & 16;

                // Aktions

                int sysOutput = 0;
                for(int l = 0;  l < input.length; l++){
                    if(input[l] != 0){
                        sysOutput =   sysOutput + input[l];
                    }
                }
                System.out.println(sysOutput);
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

    public String senden(int Data) throws TWUsbException, InterruptedException {
        if(!creatingConnection()) return "Connection Exception"

    }

    public boolean creatingConnection() throws TWUsbException, InterruptedException {
        // DTR DSR, DTR DSR
        // Träger senden Träger empfängen, DCD DCD
        // RTS CTS, RTS CTS
        // TxD RxD senden
        TWUsb.WriteAllDigital(1); // DTR
        for(int i = 0; i < 10; i++) { // DSR
            if(TWUsb.ReadAllDigital() == 1) i = 10;
            else Thread.sleep(10);
            i++;
            if(i == 9) return false;
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
