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

            senden("Hello World!");


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

    public static ArrayList<Integer> waitingOnReceiving() throws TWUsbException, InterruptedException {
        int i = 0;
        ArrayList<Integer> data = new ArrayList<>();
        boolean received = false;
        while (!received) {
            data = receiving();
            if(data != null){
                received = true;
            }
            i++;
            Thread.sleep(10);
        }
        System.out.println("Left Loop");
        return data;
    }
    public static ArrayList<Integer>  receiving() throws TWUsbException, InterruptedException {
        if(!receivingConnection()) return null;
        ArrayList<Integer> data = new ArrayList<>();
        boolean stop = false;
        if(received(2)){
            System.out.println("Gotten 01 as Transition Start");
            while(!stop){
                data.add(ReadAllDigital());
                if(data.size() > 8){
                    if( data.get(data.size() - 8) == 0 && data.get(data.size() - 7) == 0 && data.get(data.size() - 6) == 0 && data.get(data.size() - 5) == 0 && data.get(data.size() - 4) == 0 && data.get(data.size() - 3) == 0 && data.get(data.size() - 1) == 1 && data.get(data.size() - 2) == 1){
                        for(int i = 0; i < 8; i++){
                            data.remove(data.size() - 1);
                        }
                        System.out.println("Gotten transition end 11");
                        stop = true;
                    }
                }
            }
        }
        System.out.println("Gotten data: " + data);
        return data;
    }

    public static boolean senden(String data) throws TWUsbException, InterruptedException {
        if(!creatingConnection()) return false;
        System.out.println("        => Transmitting Data");
        char[] dataListChar = data.toCharArray();
        int[] dataListInt = new int[dataListChar.length];
        String[] dataListString = new String[dataListChar.length];

        System.out.println("        => Giving Data Go (Value2)");
        TWUsb.WriteAllDigital(2);

        String completeData = "";
        for(int i = 0; i < dataListChar.length; i++){
            dataListInt[i] = (int) dataListChar[i];
            dataListString[i] = Integer.toBinaryString(dataListInt[i]);

            dataListString[i] =  String.format("%8s", Integer.toBinaryString(dataListInt[i])).replace(' ', '0');
            completeData = completeData + dataListString[i];
        }
        System.out.println("Complete Data: " + completeData);
        char[] completeDataChar = completeData.toCharArray();
        for(int i = 0; i < completeDataChar.length; i++){
            writeOneBinary(completeDataChar[i]);
        }

        System.out.println();

        System.out.println("        => Transmition complete");
        writeOneBinary('0');
        writeOneBinary('0');
        writeOneBinary('0');
        writeOneBinary('0');
        writeOneBinary('0');
        writeOneBinary('0');
        writeOneBinary('1');
        writeOneBinary('1');
        System.out.println("Written Transmittion End");

        return true;
    }



    public static void writeOneBinary(Character binary) throws TWUsbException {
        if(binary.equals('1')) TWUsb.WriteAllDigital(1);
        else TWUsb.WriteAllDigital(0);
        System.out.print("Written:" + binary + " ");
    }

    public static boolean creatingConnection() throws TWUsbException, InterruptedException {
        System.out.println("        => Creating Connection");
        TWUsb.WriteAllDigital(2); // DTR
        System.out.println("Sent DTR");
        if(!received(2)) return false; // DSR
        System.out.println("Received DSR");

        TWUsb.WriteAllDigital(4);
        System.out.println("Sent DCD");
        if(!received(4)) return false;
        System.out.println("Received Träger");

        TWUsb.WriteAllDigital(8);
        System.out.println("Sent CTS");
        if(!received(8)) return false;
        System.out.println("Received RTS");

        System.out.println("        => Connection Complete");
        return true;
    }

    public static boolean receivingConnection() throws TWUsbException, InterruptedException {
        System.out.println("        => Receiving Connection");
        if(!received(2)) return false; // DSR
        System.out.println("Received DSR");
        TWUsb.WriteAllDigital(2); // DTR
        System.out.println("Sent DTR");

        if(!received(4)) return false;
        System.out.println("Received Träger");
        TWUsb.WriteAllDigital(4);
        System.out.println("Sent DCD");

        if(!received(8)) return false;
        System.out.println("Received RTS");
        TWUsb.WriteAllDigital(8);
        System.out.println("Sent CTS");

        System.out.println("        => Connection Complete");
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
