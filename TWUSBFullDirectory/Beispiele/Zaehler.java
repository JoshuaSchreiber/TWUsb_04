package Board;

import de.wenzlaff.twusb.schnittstelle.TWUsb;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

public class Zaehler {

	public static void main(String[] args) {
		try {
			TWUsb.OpenDevice(TWUsb.ADDRESSE_0);
			TWUsb.ClearAllDigital();
			Thread.sleep(100);
			TWUsb.ClearAllAnalog();
			Thread.sleep(100);
			
			// *****************************************************************
			// hier kommt dann später euer eigener Code hinein 

			for (int i = 0; i < 256; i++) {
				while (!TWUsb.ReadDigitalChannel(TWUsb.DIGITALER_EINGABE_KANAL_5)){ // Flanke 
					Thread.sleep(1);				
				}
				TWUsb.WriteAllDigital(i);
				Thread.sleep(100);
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
}
