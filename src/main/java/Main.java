import de.wenzlaff.twusb.schnittstelle.TWUsb;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

public class Main {
    public static void main(String[] args) {
        try {
            TWUsb.OpenDevice(TWUsb.ADDRESSE_0);
            TWUsb.ClearAllDigital();
            Thread.sleep(100);
            TWUsb.ClearAllAnalog();
            Thread.sleep(100);

            // *****************************************************************

            System.out.println("Start");


            Senden senden  = new Senden();
            senden.start();

            Empfangen empfangen = new Empfangen();
            empfangen.start();

            for(int i = 0; i < 10000; i++){
                Thread.sleep(100);
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
}
