package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class CommonUtilities {

	public static Properties loadPropertiesFile() {
		Properties prop = new Properties();
		FileReader fr;
		try {
			fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties");
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String generateRandomName() {
		String[] firstNames = { "John", "Emily", "Michael", "Sarah", "David", "Jessica", "Daniel", "Emma", "James",
				"Olivia", "Robert", "Sophia" };
		String[] lastNames = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Martinez", "Davis", "Lopez",
				"Taylor", "Anderson", "Thomas" };

		Random random = new Random();
		String firstName = firstNames[random.nextInt(firstNames.length)];
		String lastName = lastNames[random.nextInt(lastNames.length)];
		return firstName + " " + lastName;
	}

	public static String generateRandomStringWithNumber() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		int stringLength = random.nextInt(6) + 5;

		StringBuilder randomString = new StringBuilder(stringLength);
		for (int i = 0; i < stringLength; i++) {
			int index = random.nextInt(characters.length());
			randomString.append(characters.charAt(index));
		}
		int randomNumber = random.nextInt(10000);
		return randomString.toString() + randomNumber;
	}

	public static String generateRandomPhoneNumber() {
		Random random = new Random();
		long phoneNumber = (long) (random.nextDouble() * 1_000_000_0000L);
		return String.format("%010d", phoneNumber);
	}

	public static int getRandomNumberBetween1And8() {
		Random random = new Random();
		return random.nextInt(8) + 1;
	}

}
