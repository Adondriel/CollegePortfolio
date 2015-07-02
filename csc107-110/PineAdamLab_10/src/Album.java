
/**
 * @author Adam Pine
 * The Album class
 */
public class Album
{
	private Song[] songs;
	private int numberOfSongs;
	private String title;
	
	/**
	 * Construct the Album
	 * @param title
	 * @param size
	 */
	public Album(String title, int size)
	{
		songs = new Song[size];
		this.title = title;
		numberOfSongs = 0;
	}

	/**
	 * Return the title variable.
	 * @return title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return size of the song array.
	 */
	public int getSize()
	{
		return songs.length;
	}

	/**
	 * @return the number of songs currently in the song array.
	 */
	public int getNumberOfSongs()
	{
		return numberOfSongs;
	}

	/**
	 * @param song
	 * Adds a Song into the songs array.
	 */
	public void addSong(Song song)
	{
		songs[numberOfSongs] = song;
		numberOfSongs++;
	}

	/**
	 * @param pos (position in the array)
	 * @return The song pointer that is stored at pos
	 */
	public Song getSong(int pos)
	{
		return songs[pos];
	}
	
	/**
	 * @return the total duration of the Album.
	 */
	public int getDuration()
	{
		int sum = 0;
		for (int i = 0; i < numberOfSongs; i++)
		{
			sum = sum + songs[i].getDuration();
		}
		return sum;
	}
	
	/**
	 * Turn it into a big string with fancy characters and some nice formatting.
	 */
	public String toString()
	{
		String finalString = title + ":";
		for (int i = 0; i < numberOfSongs; i++)
		{
			finalString = finalString + "\n\t" + songs[i].getTitle()+":" + songs[i].getDurationMinAndSec();
		}
		System.out.println(finalString);
		return finalString;
	}
}
