/**
 * @author Adam Pine
 * The Song class
 */
public class Song
{
	private int duration;
	private String title;

	/**
	 * @param title
	 * @param duration
	 * initialize our song object.
	 */
	public Song(String title, int duration)
	{
		this.duration = duration;
		this.title = title;
	}

	/**
	 * @return duration of the song.
	 */
	public int getDuration()
	{
		return duration;
	}

	/**
	 * @return title of the song
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return the string that should be printed out for this object.
	 */
	public String toString()
	{
		String finalString = null;
		int minutes = duration / 60;
		int seconds = duration % 60;
		if (seconds >= 10)
		{
			finalString = title + ": " + minutes + ":" + seconds;
		} else
		{
			finalString = title + ": " + minutes + ":0" + seconds;
		}
		return finalString;
	}
	
	/**
	 * @return the substring of the toString method, but just the numbers, and not the song title.
	 */
	public String getDurationMinAndSec()
	{
		String finalString = toString();
		finalString = finalString.substring(finalString.length()-5);
		return finalString;
	}
	
	
	
}
