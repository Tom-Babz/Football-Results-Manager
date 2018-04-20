/**
 * Validates user input so that processing on user input by football manger can occur without errors.
 * @author Thomas
 *
 */
public class Validation 
{
	// Initialise variables.
	private String[] unparsed = new String[4];
	private boolean errorStat = false;
	
	/**
	 * Validate the user input, and display different messages based off why the input is invalid.
	 * @param message
	 * @return String[]
	 */
	public String[] validate(String message)
	{
		// Set error status to false.
		this.setErrorStat(false);
		
		// If no input, return null.
		if(message.equals(""))
		{
			// Print error to console.
			System.err.println("No data entered!");
			// Return null so that it can be removed later.
			return null;
		}
		
		// Parse delimiters.
		message = message.replace("[", ":");
		message = message.replace("]", "");
		message = message.replace("|", ":");
		
		// Split input for processing.
		unparsed = message.split(":", 4);
		
		try
		{
			// If home team name is not alphabetical characters.
			if (!unparsed[0].matches("^([A-Za-z ])+$"))
			{
				// Set error stat to true.
				this.setErrorStat(true);
				// Print error to console.
				System.err.println("Home team name missing/incorrect format!");
			}
			if (this.getErrorStat() == true)
			{
				// Return null so that it can be removed later.
				return null;
			}
		}
		catch(Exception e)
		{
			// Print error to console.
			System.err.println("Home team name missing/incorrect format!");
			// Return null so that it can be removed later.
			return null;
		}
		
		try 
		{
			// If home score is not a digit.
			unparsed[1] = unparsed[1].replaceAll("\\s+","");
			if (!unparsed[1].matches("^\\d+$"))
			{
				// Set error stat to true.
				this.setErrorStat(true);
				// Print error to console.
				System.err.println("Home team score is not an valid integer!");
			}
			if (this.getErrorStat() == true)
			{
				// Return null so that it can be removed later.
				return null;
			}
		}
		catch(Exception e)
		{
			// Print error to console.
			System.err.println("Home team score missing/incorrect format!");
			// Return null so that it can be removed later.
			return null;
		}
		
		try
		{
			// If away team is not an alphabetical character.
			if (!unparsed[2].matches("^([A-Za-z ])+$"))
			{
				// Set error stat to true.
				this.setErrorStat(true);
				// Print error to console.
				System.err.println("Away team name missing/incorrect format!");
			}
			if (this.getErrorStat() == true)
			{
				// Return null so that it can be removed later.
				return null;
			}
		}
		catch (Exception e)
		{
			// Print error to console.
			System.err.println("Away team name missing/incorrect format!");
			// Return null so that it can be removed later.
			return null;
		}
		
		try
		{
			// If away score is not a digit.
			unparsed[3] = unparsed[3].replaceAll("\\s+","");
			if (!unparsed[3].matches("^\\d+$"))
			{
				// Set error stat to true.
				this.setErrorStat(true);
				// Print error to console.
				System.err.println("Away team score is not valid integer!");
			}	
			if (this.getErrorStat() == true)
			{
				// Return null so that it can be removed later.
				return null;
			}	
		}
		catch(Exception e)
		{
			// Print error to console.
			System.err.println("Away team score missing/incorrect format!");
			// Return null so that it can be removed later.
			return null;
		}
		// Remove space from start and end of team names.
		unparsed[0] = unparsed[0].trim();
		unparsed[2] = unparsed[2].trim();
		// Return validated input.
		return unparsed;
	}
	
	/**
	 * Get the status of errorStat.
	 * @return boolean
	 */
	public boolean getErrorStat()
	{
		return errorStat;
	}
	/**
	 * Set the status of errorStat.
	 * @param a
	 */
	public void setErrorStat(boolean a)
	{
		errorStat = a;
	}
}
