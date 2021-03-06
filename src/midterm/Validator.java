package midterm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {
	
	/**
	 * Validates user input is a number between 1 and 3.
	 */
	public static boolean isMethodPaymentValid(String userInputString) {
		boolean isValid = false;
		int userInput = 0;
		
		try {
			userInput = Integer.parseInt(userInputString);
		} catch (InputMismatchException e) {
			isValid = false;			
		}
		
		if (userInput > 0 && userInput <= 3) {
			isValid = true;
		} else {
			isValid = false;
		}
		
		return isValid;
	}
	
	public static String isEmailAddressValid(Scanner scnr) {
		String email;
		boolean valid = false;
		do {
			System.out.println("Please enter your email address:");
			email = scnr.next();
	        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
	        java.util.regex.Matcher m = p.matcher(email);
	        valid = m.matches();
	        //return m.matches();
		} while (!valid);
		return email;
	}
	
	/**
	 * Get any valid integer.
	 */
	public static int getInt(Scanner scnr, String prompt) {
		// This approach uses exception handling.
		System.out.print(prompt);
		try {
			int num = scnr.nextInt();
			scnr.nextLine();
			return num;
		} catch (InputMismatchException e) {
			System.out.println("Enter a whole number, using digits.");
			scnr.nextLine();
			return getInt(scnr, prompt);
		}
	}
	public static int getQuantityInt(Scanner scnr, String prompt, int min, int max, Product menu) {
		boolean isValid = false;
		int number;
		
		do {
			number = getInt(scnr, prompt);
			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("We're sorry but we only have " + menu.getStock() + ", please enter a valid quantity.");

			} else {
				isValid = true;
			}
			
		} while (!isValid);
		return number;	
		
	}

	/**
	 * Get any valid double.
	 */
	public static double getDouble(Scanner scnr, String prompt) {
		// This approach use "hasNext" look ahead.
		boolean isValid = false;
		do {
			System.out.print(prompt);
			isValid = scnr.hasNextDouble();
			if (!isValid) {
				scnr.nextLine();
				System.out.println("Enter a number, in digits.");
			}
		} while (!isValid);
		
		double number = scnr.nextDouble();
		scnr.nextLine();
		return number;
	}
	
	/**
	 * Get any string.
	 */
	public static String getString(Scanner scnr, String prompt) {

		System.out.print(prompt);
		return scnr.nextLine();
	}
	
	public static String checkContinue(Scanner scnr, String prompt) {

		System.out.print(prompt);
		boolean choiceIsOK = false;
		String userinput;
		char choice;
	    do{
	    userinput = scnr.next();
	    scnr.nextLine();
	    choice = userinput.toLowerCase().charAt(0);
	    switch(choice){
	    case 'y':
	        choiceIsOK = true;
	        break;
	    case 'n':
	        choiceIsOK = true;
	        break;
	    case 'l':
	        choiceIsOK = false;
			SVKPartyStoreApp.printInventory();
			System.out.println("Would you like to add another item (yes or no)");
			break;
	    default:
	        // error or warning
	        System.out.println("Type yes or no to respectively continue or quit or list to see products available");
	        break;
	    }
	    }while(!choiceIsOK);
		return Character.toString(choice);
	}
	
	/**
	 * Get any valid integer between min and max.
	 */
	public static int getInt(Scanner scnr, String prompt, int min, int max) {
		boolean isValid = false;
		int number;
		do {
			number = getInt(scnr, prompt);
			
			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}
			
		} while (!isValid);
		return number;
	}
	
	/**
	 * Get any valid double between min and max.
	 */
	public static double getDouble(Scanner scnr, String prompt, double min, double max) {
		boolean isValid = false;
		double number;
		do {
			number = getDouble(scnr, prompt);
			
			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}
			
		} while (!isValid);
		return number;
	}
	
	/**
	 * Get a string of input from the user that must match the given regex.
	 */
	public static String getStringMatchingRegex(Scanner scnr, String prompt, String regex) {
		boolean isValid = false;
		String input;
		
		do {
			input = getString(scnr, prompt);
			
			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.out.println("Input must match the appropriate format.");
				isValid = false;
			}
			
		} while (!isValid);
		return input;
	}

	public static boolean validateCardExpiryDate(String expiryDate) {
	    return expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");
	}
	/**
	 * Get a date from user input in the format mm/dd/yyyy
	 */

	public static Date getDate(Scanner scnr, String prompt) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		format.setLenient(false); // <-- date format must match
		boolean isValid = false;
		Date date = null;
		String input;
		do {
			// Step 1: get the raw string
			input = getString(scnr, prompt);
			// Step 2: convert it to a date
			try {
				// format.parse throws a ParseException, which is a checked exception and MUST be caught.
				date = format.parse(input);
				// If exception doesn't occur, it's valid.
				isValid = true;
			} catch (ParseException ex) {
				// If exception occurs, it's invalid.
				isValid = false;
				System.out.println("Enter a valid date in format mm/dd/yyyy.");
			}
			
		} while (!isValid);
		return date;
	}
	public static boolean checkExpiration(Scanner scnr, String prompt) { // throws ParseException { // added throws exception to kick the red squigles
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
		simpleDateFormat.setLenient(false);
		System.out.println(prompt);
		boolean valid;
		do {
			String input = scnr.next();
			if (input.matches("(?:0[1-9]|1[0-2])/[0-9]{2}")) {
				Date expiry = null;
				try {
					expiry = simpleDateFormat.parse(input);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				valid = expiry.before(new Date());
				System.out.println("Expiration date valid");
			} else {
				System.out.println("Please enter a valid MM/YY");
			}
		} while (valid = false);
		return valid;
	}
	
	
}