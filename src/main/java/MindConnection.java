import de.wenzlaff.twusb.schnittstelle.TWUsb;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

import static de.wenzlaff.twusb.schnittstelle.TWUsb.ReadAllDigital;
import java.nio.file.Files;
import java.io.File;

public class MindConnection {
    public Random random = new Random();

    public int toggle = 0;

    public MindConnection() {

    }


    public String dataToString(ArrayList<Integer> data){
        String stringData = "";
        for(int i = 0; i < data.size(); i++){
            stringData = stringData + data.get(i);
        }
        return binaryToAscii(stringData);
    }



    public String binaryToAscii(String binaryString) {
        StringBuilder asciiString = new StringBuilder();
        // Zerlege den Binär-String in 8-Bit-Blöcke
        for (int i = 0; i < binaryString.length(); i += 8) {
            String byteString = binaryString.substring(i, i + 8); // Nimm 8-Bit-Abschnitt
            // Konvertiere den 8-Bit-Abschnitt in eine Dezimalzahl
            int asciiCode = Integer.parseInt(byteString, 2);
            // Konvertiere den ASCII-Code in das entsprechende Zeichen
            asciiString.append((char) asciiCode);
        }
        return asciiString.toString();
    }





    public ArrayList<Integer> waitingOnReceiving() throws TWUsbException, InterruptedException {
        ArrayList<Integer> data = new ArrayList<>();
        boolean received = false;
        while (!received) {
            data = receiving();
            if(data != null){
                received = true;
            }

            Thread.sleep(10);
        }
        return data;
    }
    public ArrayList<Integer>  receiving() throws TWUsbException, InterruptedException {
        if(!receivingConnection()) return null;
        ArrayList<Integer> data = new ArrayList<>();
        boolean stop = false;
        if(received(2)){
            while(!stop){
                data.add(received());
                if(data.size() > 8){
                    if( data.size() % 8 == 0 && data.get(data.size() - 7) == 0 && data.get(data.size() - 6) == 0 && data.get(data.size() - 5) == 0 && data.get(data.size() - 4) == 0 && data.get(data.size() - 3) == 0 && data.get(data.size() - 1) == 1 && data.get(data.size() - 2) == 1){
                        for(int i = 0; i < 8; i++){
                            data.remove(data.size() - 1);
                        }
                        stop = true;
                    }
                }
            }

        }
        while (data.get(0) == 2){
            data.remove(0);
        }
        return data;
    }

    public boolean senden(String data) throws TWUsbException, InterruptedException {
        if(!creatingConnection()) return false;
        char[] dataListChar = data.toCharArray();
        int[] dataListInt = new int[dataListChar.length];
        String[] dataListString = new String[dataListChar.length];

        for(int i = 0; i < 10; i++){
            TWUsb.WriteAllDigital(2);
            Thread.sleep(0,1);
        }



        String completeData = "";
        for(int i = 0; i < dataListChar.length; i++){
            dataListInt[i] = (int) dataListChar[i];
            dataListString[i] = Integer.toBinaryString(dataListInt[i]);

            dataListString[i] =  String.format("%8s", Integer.toBinaryString(dataListInt[i])).replace(' ', '0');
            completeData = completeData + dataListString[i];
        }

        int toggle = 4;
        char[] completeDataChar = completeData.toCharArray();
        for(int i = 0; i < completeDataChar.length; i++){
            writeOneBinary(completeDataChar[i], toggle);
            toggle = changeToggle(toggle);
        }


        writeOneBinary('0', toggle);
        toggle = changeToggle(toggle);
        writeOneBinary('0', toggle);
        toggle = changeToggle(toggle);
        writeOneBinary('0', toggle);
        toggle = changeToggle(toggle);
        writeOneBinary('0', toggle);
        toggle = changeToggle(toggle);
        writeOneBinary('0', toggle);
        toggle = changeToggle(toggle);
        writeOneBinary('0', toggle);
        toggle = changeToggle(toggle);
        writeOneBinary('1', toggle);
        toggle = changeToggle(toggle);
        writeOneBinary('1', toggle);

        return true;
    }

    public int changeToggle(int toggle){
        if(toggle == 0) toggle = 4;
        else toggle = 0;
        return toggle;
    }



    public void writeOneBinary(Character binary, int toggle) throws TWUsbException, InterruptedException {

        if(binary.equals('1')) TWUsb.WriteAllDigital(1+toggle);
        else TWUsb.WriteAllDigital(0+toggle);
        Thread.sleep(0,10);


    }

    public boolean creatingConnection() throws TWUsbException, InterruptedException {
        TWUsb.WriteAllDigital(2); // DTR
        if(!received(2)) return false; // DSR

        TWUsb.WriteAllDigital(4);
        if(!received(4)) return false;

        TWUsb.WriteAllDigital(8);
        if(!received(8)) return false;

        return true;
    }

    public boolean receivingConnection() throws TWUsbException, InterruptedException {
        if(!received(2)) return false; // DSR
        TWUsb.WriteAllDigital(2); // DTR

        if(!received(4)) return false;
        TWUsb.WriteAllDigital(4);

        if(!received(8)) return false;
        TWUsb.WriteAllDigital(8);

        return true;
    }

    public boolean received(int expected) throws TWUsbException, InterruptedException {
        int failCounter = 0;
        while(ReadAllDigital() != expected){
            Thread.sleep(0,1);
            failCounter++;
            if(failCounter == 100) return false;
        }
        return true;
    }

    public int received() throws TWUsbException, InterruptedException {
        int incoming = TWUsb.ReadAllDigital();
        int localToggle;
        if((incoming & 4) == 4) localToggle = 4;
        else localToggle = 0;
        while (localToggle == toggle){
            incoming = TWUsb.ReadAllDigital();
            localToggle = incoming & 4;
        }
        incoming = TWUsb.ReadAllDigital();
        toggle = localToggle;
        return incoming & 1;
    }


    public void writeAllDigitalOutput(boolean[] output) throws TWUsbException {
        int worth = 0;
        for (int i = 0; i < output.length; i++) {
            if (output[i]) {
                worth += Math.pow(2, i); // Math.pow(2, i) calculates 2^i
            }
        }

        TWUsb.WriteAllDigital(worth);
    }

    public  int newLowerHole(){
        return random.nextInt(70)+1;

    }
    public int newHigherHole(){
        return random.nextInt(30)+1 + 70;
    }
}
