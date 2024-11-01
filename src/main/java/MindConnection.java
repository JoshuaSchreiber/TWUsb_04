import de.wenzlaff.twusb.schnittstelle.TWUsb;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

import static de.wenzlaff.twusb.schnittstelle.TWUsb.ReadAllDigital;

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

            /**
             * 1 = Send Kanal
             * 2 = DTR, DSR
             * 4 = Träger, DCD DCD
             * 8 = RTS CTS
             */

            senden("!");

            int i = 0;
            ArrayList<Integer> data = new ArrayList<>();
            while (i < 1000000) {
                data = receiving();
                i++;
                Thread.sleep(10);
            }
            // Hier data Umwandeln und ausgeben

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

    public static ArrayList<Integer>  receiving() throws TWUsbException, InterruptedException {
        if(!receivingConnection()) return null;
        ArrayList<Integer> data = new ArrayList<>();
        if(ReadAllDigital() == 2){
            data.add(ReadAllDigital());
        } return data;
    }

    public static boolean senden(String data) throws TWUsbException, InterruptedException {
        if(!creatingConnection()) return false;
        char[] dataListChar = data.toCharArray();
        int[] dataListInt = new int[dataListChar.length];
        String[] dataListString = new String[dataListChar.length];
        TWUsb.WriteAllDigital(2);
        for(int i = 0; i < dataListChar.length; i++){
            dataListInt[i] = (int) dataListChar[i];
            dataListString[i] = Integer.toBinaryString(dataListInt[i]);

            dataListString[i] =  String.format("%8s", Integer.toBinaryString(dataListInt[i])).replace(' ', '0');
            oneBitOut(dataListString[i]);
        }
        TWUsb.WriteAllDigital(3);

        return true;
    }


    public static void oneBitOut(String oneBit) throws TWUsbException {
        char[] charArray = oneBit.toCharArray();
        for(int i = 0; i < charArray.length; i++){
            writeOneBinary(String.valueOf(charArray[i]));
        }
    }
    public static void writeOneBinary(String binary) throws TWUsbException {
        if(binary.equals("1")) TWUsb.WriteAllDigital(1);
        else TWUsb.WriteAllDigital(0);
    }

    public static boolean creatingConnection() throws TWUsbException, InterruptedException {
        TWUsb.WriteAllDigital(2); // DTR
        if(!received(2)) return false; // DSR

        TWUsb.WriteAllDigital(4);
        if(!received(4)) return false;

        TWUsb.WriteAllDigital(8);
        if(!received(8)) return false;

        return true;
    }

    public static boolean receivingConnection() throws TWUsbException, InterruptedException {
        if(!received(2)) return false; // DSR
        TWUsb.WriteAllDigital(2); // DTR

        if(!received(4)) return false;
        TWUsb.WriteAllDigital(4);

        if(!received(8)) return false;
        TWUsb.WriteAllDigital(8);

        return true;
    }

    public static boolean received(int expected) throws TWUsbException, InterruptedException {
        int failCounter = 0;
        while(ReadAllDigital() != expected){
            Thread.sleep(1);
            failCounter++;
            if(failCounter == 100) return false;
        }
        return true;
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
