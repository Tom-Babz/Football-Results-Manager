import java.util.Scanner;

/**
 * Input class is used to get user input from the keyboard.
 * @author Thomas
 *
 */
public class Input 
{
	// Initialise new object.
	private Scanner scan = new Scanner(System.in);
	
	// Constructor, can be passed a message to print on initialisation.
	Input(String message)
	{
		System.out.println(message);
	}
	
	/**
	 * Method for receiving user input from the keyboard, can be passed a string to print.
	 * @param message
	 * @return String user input
	 */
	public String recieveInput(String message)
	{
		System.out.println(message);
		return scan.nextLine();
	}
	
}
