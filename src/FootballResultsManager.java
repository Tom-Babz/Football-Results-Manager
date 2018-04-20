import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Football Manager class manages the football manager application through various methods.
 * @author Thomas
 *
 */
public class FootballResultsManager 
{
	// Initialise variables.
	private int totalMatches = 0;
	private int totalHomeScore = 0;
	private int totalAwayScore = 0;
	private int highestHomeScore = 0;
	private int highestAwayScore = 0;
	private int invalidEnteries = 0;
	private ArrayList<Team> teamArray = new ArrayList<Team>();
	
	// Constructor.
	FootballResultsManager()
	{
		// Print text to console.
		System.out.println("Welcome to Football Results Manager.");
		// read options to console on start up.
		this.readFromFile("..\\options.txt", false);
	}
	
	/**
	 * Prints the available options to the screen.
	 */
	public void options()
	{
		// Print text to console.
		System.out.println("Loading options...");
		// Call ReadFromFile method to read options.
		this.readFromFile("..\\options.txt", false);
	}
	
	/**
	 * Print help to the console.
	 */
	public void help()
	{
		// Print text to console.
		System.out.println("Loading help...");
		// Call ReadFromFile method to read help.
		this.readFromFile("..\\help.txt", false);
	}
	
	/**
	 * Exits the application.
	 */
	public void stop()
	{
		// Print text to console.
		System.out.println("Stopping...");
		// Close application.
		System.exit(0);
	}
	
	/**
	 * Creates a HTML file that will either contain a league report or a team specific report.
	 * @param teamReport
	 * @param teamName
	 */
	public void report(boolean teamReport, String teamName)
	{
		//constructs fileWriter object.
		FileWriter fileWriter = null;
		//constructs bufferedWriter object.
		BufferedWriter bufferedWriter = null;
		// If a team report.
		if(teamReport == true)
		{
			// For length of the array.
			for(int i = 0; i < teamArray.size(); i++)
			{
				// If current teamArray elements name is equal to the user passed team name.
				if(teamArray.get(i).getName().equals(teamName))
				{
					try 
					{
						// Set file name.
						fileWriter = new FileWriter(teamName + ".html");
						// Create file.
						bufferedWriter = new BufferedWriter(fileWriter);
						// Write to file.
						bufferedWriter.write
						(
								  "<!doctype html>"
								+ "<html>"
									+ "<head>"
										+ "<title>Football Results Generator</title>"
									+ "</head>"
									+ "<body>"
										+ "<h1>" + teamName + "</h1>"
										+ "<br/>"
										+ "--------------------<br/>"
										+ "Total matches: " + teamArray.get(i).getTotalMatches() + "<br/>"
										+ "Total home score: " + teamArray.get(i).getTotalHomeScore() + "<br/>"
										+ "Total away score: " + teamArray.get(i).getTotalAwayScore() + "<br/>"
										+ "highest home score: " + teamArray.get(i).getHighestHomeScore() + "<br/>"
										+ "highest away score: " + teamArray.get(i).getHighestAwayScore() + "<br/>"
									+ "</body>"
								+ "</html>"
						);
						// Close the bufferedWriter object.
						bufferedWriter.close();
						// Prompt user the file was successfully created.
						System.out.println(teamName + ".html file made, returning to menu...");
					}
					catch(Exception e)
					{
						// Print error text to console.
						System.err.println("ERROR: League report could not be created...");
					}
				}
			}
		}
		else
		{
			try 
			{
				// Set file name.
				fileWriter = new FileWriter("league.html");
				//Create file.
				bufferedWriter = new BufferedWriter(fileWriter);
				//Write to file.
				bufferedWriter.write
				(
						  "<!doctype html>"
						+ "<html>"
							+ "<head>"
								+ "<title>Football Results Generator</title>"
							+ "</head>"
							+ "<body>"
								+ "<h1> League Report</h1>"
								+ "<br/>"
								+ "--------------------<br/>"
								+ "Total matches: " + totalMatches + "<br/>"
								+ "Total home score: " + totalHomeScore + "<br/>"
								+ "Total away score: " + totalAwayScore + "<br/>"
								+ "highest home score: " + highestHomeScore + "<br/>"
								+ "highest away score: " + highestAwayScore + "<br/>"
								+ "Invalid enteries: " + invalidEnteries + "<br/>"
							+ "</body>"
						+ "</html>"
				);
				// Close the bufferedWriter object. 
				bufferedWriter.close();
				// Prompt user the file was successfully created.
				System.out.println("league.html file made, returning to menu...");
			}
			catch(Exception e)
			{
				// Print error text to console.
				System.err.println("ERROR: League report could not be created...");
			}
		}
	}
	
	/**
	 * Read data from a file.
	 * @param fileName
	 * @param validate
	 */
	public void readFromFile(String fileName, Boolean validate)
	{
		// Initialise new validation object.
		Validation valid = new Validation();
		// Initialise new BufferedReader object.
		BufferedReader reader = null;
		// Print text to console
		System.out.println("Reading from file... ");
		try 
		{
			// Initialise new File object using the path as an argument.
			File file = new File(fileName);
			// Assignment BufferedReader object using file object as argument.
			reader = new BufferedReader(new FileReader(file.getCanonicalFile()));
			// Initialise new String object.
		    String line = new String();
		    // While the current line in the file is not empty.
		    while ((line = reader.readLine()) != null) 
		    {
		    	// If validation was set to false then print directly to console.
		    	if(validate == false)
		    	{
		    		// Print text to console.
		    		System.out.println(line);
		    	}
		    	else
		    	{
		    		// Validate and store file input.
		    		this.store(valid.validate(line));
		    	}
		    }
		} 
		catch (IOException e) 
		{
			// Print error text to console.
		    System.err.println("ERROR: Cannot read from file!");
		} 
		finally 
		{
		    try 
		    {
		    	// Close reader object.
		        reader.close();
		    } 
		    catch (IOException e) 
		    {
		    	// Print error text to console.
		    	System.err.println("ERROR: Cannot read from file!");
		    }
		}
	}
	
	/**
	 * Store the user input into individual team objects.
	 * @param input
	 */
	public void store(String[] input)
	{
		// Initialise variables.
		boolean home = false;
		boolean away = false;
		
		// If input is null, break.
		if(input == null)
		{
			// Increase invalid entries.
			invalidEnteries += 1;
			return;
		}
		else
		{
			// Increase total matches.
			totalMatches += 1;
			// Add current score to total score.
			totalHomeScore += Integer.parseInt(input[1]);
			// If the current high score is less than the current score.
			if(highestHomeScore < Integer.parseInt(input[1]))
			{
				// Change high score to current score.
				highestHomeScore = Integer.parseInt(input[1]);
			}
			// Add current score to total score.
			totalAwayScore += Integer.parseInt(input[3]);
			// If the current high score is less than the current score.
			if(highestAwayScore < Integer.parseInt(input[3]))
			{
				// Change high score to current score.
				highestAwayScore = Integer.parseInt(input[3]);
			}
			// For each object in ArrayList.
			for(int i = 0; i < teamArray.size(); i++)
			{
				// If the current object in TeamArray is equal to the home team.
				if(teamArray.get(i).getName().equals(input[0]))
				{
					// Increase total matches by 1.
					teamArray.get(i).setTotalMatches(teamArray.get(i).getTotalMatches() + 1);
					// Check for highest score.
					teamArray.get(i).setHighestHomeScore(Integer.parseInt(input[1]));
					// Add score to total home score.
					teamArray.get(i).setTotalHomeScore(teamArray.get(i).getTotalHomeScore() + Integer.parseInt(input[1]));
					// Set home to true to show home team exists.
					home = true;
				}
				// If the current object in TeamArray is equal to the away team.
				if(teamArray.get(i).getName().equals(input[2]))
				{
					// Increase total matches by 1.
					teamArray.get(i).setTotalMatches(teamArray.get(i).getTotalMatches() + 1);
					// Check for highest score.
					teamArray.get(i).setHighestAwayScore(Integer.parseInt(input[3]));
					// Add score to total away score.
					teamArray.get(i).setTotalAwayScore(teamArray.get(i).getTotalAwayScore() + Integer.parseInt(input[3]));
					// Set away to true to show away team exists.
					away = true;
				}
			}
			// If home team doesn't already exist.
			if(home == false)
			{
				// Initialise new team object.
				Team team = new Team();
				// Add name.
				team.setName(input[0]);
				// Increase total matches.
				team.setTotalMatches(team.getTotalMatches() + 1);
				// Check highest score.
				team.setHighestHomeScore(Integer.parseInt(input[1]));
				// Increase total.
				team.setTotalHomeScore(team.getTotalHomeScore() + Integer.parseInt(input[1]));
				// Add team to teamArray.
				teamArray.add(team);
			}
			// If away team doesn't already exist.
			if(away == false)
			{
				// Initialise new team object.
				Team team = new Team();
				// Add name.
				team.setName(input[2]);
				// Increase total matches.
				team.setTotalMatches(team.getTotalMatches() + 1);
				// Check highest score.
				team.setHighestAwayScore(Integer.parseInt(input[3]));
				// Increase total.
				team.setTotalAwayScore(team.getTotalAwayScore() + Integer.parseInt(input[3]));
				// Add team to teamArray.
				teamArray.add(team);
			}
		}
	}
	
	/**
	 * Creates a league table based off mean score of each team that is currently stored.
	 */
	public void leagueTable()
	{
		// Constructs fileWriter object.
		FileWriter fileWriter = null;
		// Constructs bufferedWriter object.
		BufferedWriter bufferedWriter = null;
		// Sort the teamArray.
		this.sort();
		try 
		{
			// Set file name.
			fileWriter = new FileWriter("League Table.html");
			// Create file.
			bufferedWriter = new BufferedWriter(fileWriter);
			// Write to file.
			bufferedWriter.write
			(
					  "<!doctype html>"
					+ "<html>"
						+ "<head>"
							+ "<title>Football Results Generator</title>"
						+ "</head>"
						+ "<body>"
							+ "<table>"
								+ "<tr>"
						  			+ "<th>Name</th>"
						  			+ "<th>Total Matches</th>" 
						  			+ "<th>Total Home Score</th>"
						  			+ "<th>Total Away Score</th>"
						  			+ "<th>Highest Home Score</th>"
						  			+ "<th>Highest Away Score</th>"
						  			+ "<th>Mean Score</th>"
						  		+ "</tr>"
			);
			// For the length of the array add table rows.
			for(int i = 0; i < teamArray.size(); i++)
			{
				bufferedWriter.write
				(
							  "<tr>"
								    + "<th>" + teamArray.get(i).getName() + "</th>"
								    + "<th>" + teamArray.get(i).getTotalMatches() + "</th>"
								    + "<th>" + teamArray.get(i).getTotalHomeScore() + "</th>" 
								    + "<th>" + teamArray.get(i).getTotalAwayScore() + "</th>"
								    + "<th>" + teamArray.get(i).getHighestHomeScore() + "</th>"
								    + "<th>" + teamArray.get(i).getHighestAwayScore() + "</th>"
								    + "<th>" + ( (teamArray.get(i).getTotalAwayScore() + teamArray.get(i).getTotalHomeScore() ) / teamArray.get(i).getTotalMatches() ) + "</th>"
							+ "</tr>"
				);
			}
			// Write the bottom of the HTML file.
			bufferedWriter.write
			(
						  	  "</table>"
						+ "</body>"
					+ "</html>"
			);
			// Close the bufferedWriter object.
			bufferedWriter.close();
			// Prompt user the file was successfully created.
			System.out.println("League Table.html file made, returning to menu...");
		}
		catch(Exception e)
		{
			// Print error text to console.
			System.err.println("ERROR: League table could not be created...");
		}
	}
	
	/**
	 * Method to sort the teamArray into reverse order based off mean score.
	 * @return
	 */
	public void sort()
	{	
		// Get the size of the teamArray.
		int size = teamArray.size();
		// Use bubble sort to order array in reverse
		// For length of the array.
		for(int i = 0; i < size; i++)
		{
			for(int j = 1; j < (size - i); j++)
			{
				// Calculate home + away / total matches for mean score.
				if( (teamArray.get(j - 1).getTotalAwayScore() + teamArray.get(j - 1).getTotalHomeScore()) / teamArray.get(j - 1).getTotalMatches() <= (teamArray.get(j).getTotalAwayScore() + teamArray.get(j).getTotalHomeScore()) / teamArray.get(j).getTotalMatches() )
				{
					// Create two new objects to replace the current existing objects.
					Team team1 = new Team();
					Team team2 = new Team();
					
					// Fill new objects.
					team1.setName(teamArray.get(j - 1).getName());
					team1.setTotalMatches(teamArray.get(j - 1).getTotalMatches());
					team1.setTotalHomeScore(teamArray.get(j - 1).getTotalHomeScore());
					team1.setTotalAwayScore(teamArray.get(j - 1).getTotalAwayScore());
					team1.setHighestHomeScore(teamArray.get(j - 1).getHighestHomeScore());
					team1.setHighestAwayScore(teamArray.get(j - 1).getHighestHomeScore());
					
					team2.setName(teamArray.get(j).getName());
					team2.setTotalMatches(teamArray.get(j).getTotalMatches());
					team2.setTotalHomeScore(teamArray.get(j).getTotalHomeScore());
					team2.setTotalAwayScore(teamArray.get(j).getTotalAwayScore());
					team2.setHighestHomeScore(teamArray.get(j).getHighestHomeScore());
					team2.setHighestAwayScore(teamArray.get(j).getHighestHomeScore());
					
					// Swap object around
					teamArray.set(j - 1, team2);
					teamArray.set(j, team1);
				}
			}	
		}
	}
}
