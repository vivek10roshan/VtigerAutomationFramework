package Practice_Project;

import java.util.Random;

public class ToGenerateRandomNumber {

	public static void main(String[] args) {
		//Generate random number 
		Random r = new Random();
		int value = r.nextInt(1000);
		System.out.println(value);

	}

}
