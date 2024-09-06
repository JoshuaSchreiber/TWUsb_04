/**
 * @author Copyright 2004 by Thomas Wenzlaff ( <a
 *         href="mailto://twusb@wenzlaff.de">twusb@wenzlaff.de </a>)
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 * @since 27.08.2004
 * 
 * Source is only for non commercial and coaching usage. Not Warranty to use it.
 */

package test.de.wenzlaff.twusb.schnittstelle;

import org.apache.log4j.Logger;

import de.wenzlaff.twusb.schnittstelle.TWUsb;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

/**
 * Test aller Funktionen der Reihe nach.
 * 
 * @author Thomas Wenzlaff
 * @since 24.10.2004
 * @version 2.0
 * @see <a href="http://www.wenzlaff.de">www.wenzlaff.de </a>
 */
@SuppressWarnings("unused")
public class TWUsbTest {

	/** Der log4j Logger */
	private static Logger ivLog = Logger.getLogger(TWUsbTest.class);

	/**
	 * 1 Sekunde = 1000ms
	 */
	private static final int EINE_SEKUNDE = 1000;

	/**
	 * Konstruktor.
	 * 
	 */
	public TWUsbTest() {
	}

	/**
	 * Main mit Startcode.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ivLog.debug("Start:" + TWUsb.getInfo());
			try {
				ivLog.debug("OpenDevice: " + TWUsb.OpenDevice(TWUsb.ADDRESSE_0));
			} catch (RuntimeException pvException) {
				ivLog.debug("RuntimeException");
				pvException.printStackTrace();
			}
			ivLog.debug("Start Test");
			ivLog.debug("1. testSetClearAllDigital");
			testSetClearAllDigital();
			ivLog.debug("2. testAnalog");
			testAnalog();
			ivLog.debug("3. testWriteAllDigital");
			testWriteAllDigital();
			TWUsb.ClearAllDigital();
			/*
			 * testWriteAllDigital(); testReadAnalogChannel();
			 * testReadDigitalChannel(); testReadAllDigital();
			 * testSetClearAllDigital(); testSetClearDigitalChannel();
			 * TWUsb.OutputAnalogChannel(TWUsb.ANALOGER_AUSGABE_KANAL_2, 70);
			 * TWUsb.OutputAnalogChannel(TWUsb.ANALOGER_AUSGABE_KANAL_1, 200);
			 * TWUsb.OutputAllAnalog(2,255); testCounter();
			 */
			ivLog.debug("Ende Test");
			ivLog.debug("CloseDevice");
			TWUsb.CloseDevice();
			ivLog.debug("Ende");
		} catch (TWUsbException pvException) {
			ivLog.debug("TWUsbException: " + pvException.getLocalizedMessage());
		} catch (InterruptedException pvException) {
			ivLog.debug("TWUsbException: " + pvException.getLocalizedMessage());
		}

		try {
			testAutoSearchDevices();
		} catch (TWUsbException pvException) {
			ivLog.debug("TWUsbException: " + pvException.getLocalizedMessage());
		}
		
	}

	/**
	 * @throws InterruptedException
	 * @throws TWUsbException 
	 */
	private static void testSetClearAllDigital() throws InterruptedException, TWUsbException {
		ivLog.debug("SetAllDigital");
		TWUsb.SetAllDigital();
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
		ivLog.debug("ClearAllDigital");
		TWUsb.ClearAllDigital();
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
		ivLog.debug("SetAllDigital");
		TWUsb.SetAllDigital();
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
		ivLog.debug("ClearAllDigital");
		TWUsb.ClearAllDigital();
	}

	/**
	 * @throws InterruptedException
	 * @throws TWUsbException
	 */
	private static void testAnalog() throws InterruptedException, TWUsbException {
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
		ivLog.debug("ClearAllAnalog");
		TWUsb.ClearAllAnalog();
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
		ivLog.debug("SetAllAnalog");
		TWUsb.SetAllAnalog();
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
		ivLog.debug("ClearAllAnalog");
		TWUsb.ClearAllAnalog();
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
		ivLog.debug("ClearAnalogChannel 1");
		TWUsb.ClearAnalogChannel(TWUsb.ANALOGER_AUSGABE_KANAL_1);
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
		ivLog.debug("ClearAnalogChannel 2");
		TWUsb.ClearAnalogChannel(TWUsb.ANALOGER_AUSGABE_KANAL_2);
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
		ivLog.debug("SetAnalogChannel 1");
		TWUsb.SetAnalogChannel(TWUsb.ANALOGER_AUSGABE_KANAL_1);
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
		ivLog.debug("SetAnalogChannel 2");
		TWUsb.SetAnalogChannel(TWUsb.ANALOGER_AUSGABE_KANAL_2);
		ivLog.debug("Warte 1 Sekunde ...");
		Thread.sleep(EINE_SEKUNDE);
	}

	/**
	 * @throws TWUsbException
	 */
	private static void testCounter() throws TWUsbException {
		TWUsb.SetCounterDebounceTime(TWUsb.COUNTER_KANAL_1, 500);
		long lvCount = 0;
		while (lvCount < 10) {
			lvCount = TWUsb.ReadCounter(TWUsb.COUNTER_KANAL_1);
			ivLog.debug("Counter Kanal 1 = " + lvCount);
		}
		TWUsb.ResetCounter(TWUsb.COUNTER_KANAL_1);
	}

	/**
	 * @throws TWUsbException
	 */
	private static void testReadAnalogChannel() throws TWUsbException {
		int lvData = 0;
		while (lvData < 220) {
			lvData = TWUsb.ReadAnalogChannel(TWUsb.ANALOGER_EINGABE_KANAL_1);
			ivLog.debug("Analoges Eingangs Signal an Kanal 1 = " + lvData);
		}
	}

	/**
	 * @throws TWUsbException
	 */
	private static void testReadDigitalChannel() throws TWUsbException {
		while (true) {
			boolean lvStatus = TWUsb.ReadDigitalChannel(TWUsb.DIGITALER_EINGABE_KANAL_2);
			ivLog.debug("Ausgabe:" + lvStatus);
			if (lvStatus) {
				ivLog.debug("ABBRUCH!");
				break;
			}
		}
	}

	/**
	 * @throws TWUsbException 
	 * 
	 */
	private static void testReadAllDigital() throws TWUsbException {
		while (true) {
			int lvStatus = TWUsb.ReadAllDigital();
			ivLog.debug("Ausgabe:" + lvStatus);
			if (lvStatus == TWUsb.DIGITALER_EINGABE_KANAL_5) {
				ivLog.debug("ABBRUCH!");
				break;
			}
		}
	}

	/**
	 * @throws InterruptedException
	 */
	private static void testWriteAllDigital() throws InterruptedException {
		for (int i = 0; i < 256; i++) {
			try {
				TWUsb.WriteAllDigital(i);
				Thread.sleep(50);
			} catch (TWUsbException pvException) {
				pvException.printStackTrace();
			}
		}
	}

	/**
	 * @throws InterruptedException
	 * @throws TWUsbException
	 */
	private static void testSetClearDigitalChannel() throws InterruptedException, TWUsbException {
		TWUsb.SetDigitalChannel(TWUsb.DIGITALER_AUSGABE_KANAL_4);
		Thread.sleep(EINE_SEKUNDE);
		TWUsb.SetDigitalChannel(TWUsb.DIGITALER_AUSGABE_KANAL_6);
		Thread.sleep(EINE_SEKUNDE);
		TWUsb.ClearDigitalChannel(TWUsb.DIGITALER_AUSGABE_KANAL_6);
		Thread.sleep(EINE_SEKUNDE);
		TWUsb.ClearDigitalChannel(TWUsb.DIGITALER_AUSGABE_KANAL_4);
		TWUsb.ClearDigitalChannel(TWUsb.DIGITALER_AUSGABE_KANAL_4);
	}
	/**
	 * 
	 * @throws TWUsbException
	 */
	private static void testAutoSearchDevices() throws TWUsbException {
		TWUsb.OpenDevice(TWUsb.ADDRESSE_AUTO_SEARCH);
		System.out.print("Search Devices liefert=" +TWUsb.SearchDevices());
		TWUsb.CloseDevice();
	}
}
