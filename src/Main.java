/**
 * Main class to run the application.
 * @author Thomas
 *
 */
public class Main
{
	/**
	 * Main method where the program starts.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Initialise new objects.
		FootballResultsManager mgr = new FootballResultsManager();
		Input input = new Input("");
		Validation valid = new Validation();
		String userInput = new String();
		
		// Program will continuously run unless exited.
		while(true)
		{
			// Get user input.
			userInput = input.recieveInput("Please enter a Games results. ");
			// Check if user input is equal to one of the options.
			if(userInput.equals("stop"))
			{
				// Call the stop method.
				mgr.stop();
			}
			else if(userInput.equals("help"))
			{
				// Call help method.
				mgr.help();
			}
			else if(userInput.equals("options"))
			{
				// Call options method.
				mgr.options();
			}
			else if(userInput.equals("report"))
			{
				// Initialise a string.
				String report = new String();
				// Get user input.
				report = input.recieveInput("Would you like a team or league report? "
						+ "\n - team"
						+ "\n - league");
				if(report.equals("league"))
				{
					// Call report method, false to show its not a team report and null as no team name to pass.
					mgr.report(false, null);
				}
				else if(report.equals("team"))
				{
					// Call report method, true as is a team report and passes team name to method.
					mgr.report(true, input.recieveInput("Which team would you like a report for? "));
				}
				else
				{
					// Error message if input met neither conditions.
					System.err.println("ERROR: invalid input");
				}
			}
			else if(userInput.equals("file"))
			{
				// Call read from file method and pass true to show that file will require validating.
				mgr.readFromFile(input.recieveInput("Please enter the file path. "), true);
			}
			else if(userInput.equals("league table"))
			{
				// Call league table method.
				mgr.leagueTable();
			}
			else
			{
				// Input has been validated.
				mgr.store(valid.validate(userInput));
			}
		}// END while
	}
}