package myTest;

import de.wenzlaff.twusb.schnittstelle.TWUsb;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

public class Analog {

	public static void main(String[] args) {
		try {
			TWUsb.OpenDevice(TWUsb.ADDRESSE_0);
			TWUsb.ClearAllDigital();
			Thread.sleep(100);
			TWUsb.ClearAllAnalog();
			Thread.sleep(100);
			
			int dingesn=0;
			while (!TWUsb.ReadDigitalChannel(TWUsb.DIGITALER_EINGABE_KANAL_5)){
				TWUsb.OutputAnalogChannel(1, TWUsb.ReadAnalogChannel(2));
				System.out.println("Differenz: "+ (TWUsb.ReadAnalogChannel(2)-dingesn) );
				Thread.sleep(50);
				TWUsb.OutputAnalogChannel(2, dingesn);
				dingesn= (dingesn+1) %100;
			}
			
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
