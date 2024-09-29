import de.wenzlaff.twusb.schnittstelle.TWUsb;
import de.wenzlaff.twusb.schnittstelle.exception.TWUsbException;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

public class Zaehler {
	static Random random = new Random();

	public static void main(String[] args) {
		try {
			TWUsb.OpenDevice(TWUsb.ADDRESSE_0);
			TWUsb.ClearAllDigital();
			Thread.sleep(100);
			TWUsb.ClearAllAnalog();
			Thread.sleep(100);
			
			// *****************************************************************

			boolean isTankingInTheMoment = false;
			boolean hasAHoleLowerThan70Percent = false;
			boolean hasAHoleHigherThan70Percent = false;
			int fillstand = 0;


			int randomLowerHole = newLowerHole();
			int randomHigherHole = newHigherHole();


			boolean[] output = {false, false, false, false, false, false, false, false};
			boolean alarm2 = false;
			boolean alarm1 = false;

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
				if(input[0] > 0){
					isTankingInTheMoment = true;
					alarm1 = true;
					output[5] = false;
				}
				if(input[1] > 0){
					isTankingInTheMoment = false;
					alarm1 = false;
					output[5] = true;
				}
				if((input[2] > 0) && output[5]){
					hasAHoleLowerThan70Percent = true;
					alarm2 = true;
				}
				if(input[3] > 0 && output[5]){
					hasAHoleHigherThan70Percent = true;
					alarm2 = true;
				}
				if(input[4] > 0){
					hasAHoleHigherThan70Percent = false;
					randomLowerHole = newLowerHole();
					randomHigherHole = newHigherHole();
					hasAHoleLowerThan70Percent	= false;
					alarm2 = false;
				}



				// FillstandHandeling
				if(isTankingInTheMoment && fillstand < 100){
					fillstand++;
				}
				output[0] = fillstand > 5;
				output[1] = fillstand > 25;
				output[2] = fillstand > 50;
				output[3] = fillstand > 75;
				output[4] = fillstand > 95;

				// Alarm when tanking
				output[6] = false;
				if(alarm1 && ((i % 5 == 0) || ((i-1) % 5 == 0))){
					output[6] = true;
				}

				// Hole in the lower part
				output[7] = false;
				if(alarm2 && (i % 2 == 0)){
					output[7] = true;
				}
				if(hasAHoleLowerThan70Percent && fillstand > randomLowerHole){
					fillstand = fillstand - 5;
					if(fillstand < randomLowerHole) fillstand = randomLowerHole;
				}

				// Hole in the upper part
				if(alarm2 && (i % 2 == 0)){
					output[7] = true;
				}
				if(hasAHoleHigherThan70Percent && fillstand > randomHigherHole){
					fillstand = fillstand - 5;

					if(fillstand < randomHigherHole) fillstand = randomHigherHole;
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
