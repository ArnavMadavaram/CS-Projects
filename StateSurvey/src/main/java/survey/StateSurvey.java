package survey;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * Name: Arnav Madavaram
 * Username: madaa01
 */

public class StateSurvey {

	// You cannot modify any of the provided code in any way!
	private static Scanner kb;

	public static void main(String[] args) {
		try {
			System.out.printf("Welcome to our survey. You may enter "
					+ "\"quit\" at any time to cancel the survey.\n\n");

			kb = new Scanner(System.in);

			int age = getAge();

			int ZIPCode = getZIPCode();

			String[][] states = readStateFile();

			String state = getState(states);

			System.out.printf("\nAge:\t\t%d\n", age);
			System.out.printf("Address:\t%s %s\n\n", ZIPCode, state.strip());

			System.out.printf("Your survey is complete!\n");
		} catch (CancelledSurveyException e) {
			System.out.printf("%s\n", e.getMessage());
		} finally {
			System.out.printf("Thank you for your time.");
			kb.close();
		}
	}

	//TODO Complete all the needed methods below this line
	//Reminder: Use only printf (with "\n" as needed) for all printing
	static int getAge() throws CancelledSurveyException {
		while (true) {
			System.out.println("Please enter your age: ");

		String input = kb.nextLine();
		if (input.equalsIgnoreCase("quit")) {
			System.out.println("Survey Cancelled.");
			throw new CancelledSurveyException();
		}
		try {
			int intInput = Integer.parseInt(input);

			if (intInput < 18) {
				System.out.println("You are not old enough to complete this survey!");
				throw new CancelledSurveyException();
			}else{
				return intInput;
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid Input");
		}
	}
}

	public static int getZIPCode() throws CancelledSurveyException {
		while (true) {
			System.out.println("Please enter your ZIP code: ");
			String ZIPinput = kb.nextLine();
			if (ZIPinput.equalsIgnoreCase("quit")) {
				System.out.println("Survey Cancelled.");
				throw new CancelledSurveyException();
			}
			int ZIPint = Integer.parseInt(ZIPinput);
			try {
				if (ZIPint >= 10000 && ZIPint <= 99999) {
					return ZIPint;
				} else {
					System.out.println("Invalid Input");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
		}
	}

		public static String[][] readStateFile() {
			String[][] states = new String[50][2];

			try (RandomAccessFile raf = new RandomAccessFile("states.bin", "r")) {
				for (int i = 0; i < 50; i++) {
					states[i][0] = raf.readUTF();
					states[i][1] = raf.readUTF();
				}
			} catch (IOException e) {
				System.out.println("Error reading states file.");
				e.printStackTrace();
			}

			return states;
		}
	public static String getState(String[][] states) throws CancelledSurveyException {
		while (true) {
			System.out.printf("Please enter the 2-letter state abbreviation: ");
			String input = kb.nextLine();

			if (input.equalsIgnoreCase("quit")) {
				throw new CancelledSurveyException();
			}

			for (String[] state : states) {
				if (state[0].equalsIgnoreCase(input)) {
					return state[1];
				}
			}
			System.out.println("The state abbreviation was not found.");
		}
	}
}
