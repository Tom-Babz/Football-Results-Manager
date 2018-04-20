/**
 * Team class is used to store details about a specific team.
 * @author Thomas
 *
 */
public class Team 
{
	// Initialise objects
	private String name = new String();
	private int totalMatches = 0;
	private int totalHomeScore = 0;
	private int totalAwayScore = 0;
	private int highestHomeScore = 0;
	private int highestAwayScore = 0;
	
	/**
	 * Set the name of a team.
	 * @param newName
	 */
	public void setName(String newName)
	{
		name = newName;
	}
	/**
	 * Return the name of the current team.
	 * @return String
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Set the total matches the team played.
	 * @param newTotalMatches
	 */
	public void setTotalMatches(int newTotalMatches)
	{
		totalMatches = newTotalMatches;
	}
	/**
	 * Get the total matches the team played.
	 * @return int
	 */
	public int getTotalMatches()
	{
		return totalMatches;
	}
	
	/**
	 * Set the total home score.
	 * @param newTotalHomeScore
	 */
	public void setTotalHomeScore(int newTotalHomeScore)
	{
		totalHomeScore = newTotalHomeScore;
	}
	/**
	 * Get the total home score.
	 * @return int
	 */
	public int getTotalHomeScore()
	{
		return totalHomeScore;
	}
	
	/**
	 * Set the total away score.
	 * @param newTotalAwayScore
	 */
	public void setTotalAwayScore(int newTotalAwayScore)
	{
		totalAwayScore = newTotalAwayScore;
	}
	/**
	 * Get the total away score.
	 * @return int
	 */
	public int getTotalAwayScore()
	{
		return totalAwayScore;
	}
	
	/**
	 * Set highest home score.
	 * @param newHighestHomeScore
	 */
	public void setHighestHomeScore(int newHighestHomeScore)
	{
		// If old highest score is less than new high score.
		if(highestHomeScore < newHighestHomeScore)
		{
			highestHomeScore = newHighestHomeScore;
		}
	}
	/**
	 * Get highest home score.
	 * @return int
	 */
	public int getHighestHomeScore()
	{
		return highestHomeScore;
	}
	
	/**
	 * Set highest away score.
	 * @param newHighestAwayScore
	 */
	public void setHighestAwayScore(int newHighestAwayScore)
	{
		// If old high score is less than new high score.
		if(highestAwayScore < newHighestAwayScore)
		{
			highestAwayScore = newHighestAwayScore;
		}
	}
	/**
	 * Get highest away score.
	 * @return int
	 */
	public int getHighestAwayScore()
	{
		return highestAwayScore;
	}
}
