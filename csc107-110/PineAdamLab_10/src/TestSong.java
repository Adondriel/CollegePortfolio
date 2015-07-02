import static org.junit.Assert.*;

import org.junit.Test;


/**
 * @author Adam Pine
 * Our Test cases for the Song and Album classes
 */
public class TestSong
{

	/**
	 * Test to make sure we are creating our song correctly.
	 */
	@Test
	public void testCreation()
	{
		Song s1 = new Song("First Song Title", 246);
		Song s2 = new Song("Second Song Title", 340);
		assertEquals(246, s1.getDuration());
		assertEquals(340, s2.getDuration());
		assertEquals("First Song Title", s1.getTitle());
		assertEquals("Second Song Title", s2.getTitle());
	}

	/**
	 * Test the toString method of the song class.
	 */
	@Test
	public void testtoString()
	{
		Song s2 = new Song("First Song Title", 340);
		assertEquals("First Song Title: 5:40", s2.toString());
	}
	
	/**
	 * Test the tostring method of the Song class. Make sure it can deal with seconds less than 10
	 */
	@Test
	public void testtoString2()
	{
		Song s1 = new Song("Song Title", 246);
		assertEquals("Song Title: 4:06", s1.toString());
	}
	
	/**
	 * Test to ensure we are constructing the album correctly
	 */
	@Test
	public void testConstructor()
	{
		Album a1 = new Album("My Album", 3);
		assertEquals("My Album", a1.getTitle());
		assertEquals(3, a1.getSize());
		assertEquals(0, a1.getNumberOfSongs());
		Album a2 = new Album("My Second Album", 5);
		assertEquals("My Second Album", a2.getTitle());
		assertEquals(5, a2.getSize());
		assertEquals(0, a2.getNumberOfSongs());
	}
	
	/**
	 * Test to see if the pointers of the songs are the same thing.
	 */
	@Test
	public void testOneSong()
	{
		Album a1 = new Album("My Cool Album", 3);
		Song s1 = new Song("Song Title", 272);
		a1.addSong(s1);
		assertEquals(1, a1.getNumberOfSongs());
		assertEquals(s1, a1.getSong(0));
	}
	
	
	/**
	 * Test to make sure the addSong method is adding the songs correctly.
	 */
	@Test
	public void testFilledAlbum()
	{
		Album a1 = new Album("My Full Album", 3);
		Song s1 = new Song("First Song", 272);
		a1.addSong(s1);
		Song s2 = new Song("Second Song", 305);
		a1.addSong(s2);
		Song s3 = new Song("Third Song", 249);
		a1.addSong(s3);
		assertEquals(3, a1.getNumberOfSongs());
		assertEquals(s1, a1.getSong(0));
		assertEquals(s2, a1.getSong(1));
		assertEquals(s3, a1.getSong(2));		
	}
	/**
	 * Test the duration of a album with only 1 song.
	 */
	@Test
	public void testDurationSimple()
	{
		Album a1 = new Album("My Full Album", 3);
		Song s1 = new Song("First Song", 272);
		a1.addSong(s1);
		assertEquals(272, a1.getDuration());		
	}
	
	/**
	 * Test the duration method of a full album
	 */
	@Test
	public void testDurationFull()
	{
		Album a1 = new Album("My Full Album", 3);
		Song s1 = new Song("First Song", 272);
		a1.addSong(s1);
		Song s2 = new Song("Second Song", 305);
		a1.addSong(s2);
		Song s3 = new Song("Third Song", 249);
		a1.addSong(s3);
		assertEquals(826, a1.getDuration());		
	}
	
	/**
	 * Test a Part full Albums getDuration method
	 */
	@Test
	public void testDurationPartFull()
	{
		Album a1 = new Album("My Full Album", 3);
		Song s1 = new Song("First Song", 272);
		a1.addSong(s1);
		Song s2 = new Song("Second Song", 305);
		a1.addSong(s2);
		assertEquals(577, a1.getDuration());		
	}
	
	/**
	 * Test the toString method with only one song.
	 */
	@Test
	public void testAlbumtoStringSimple()
	{
		Album a1 = new Album("My Full Album", 3);
		Song s1 = new Song("First Song", 272);
		a1.addSong(s1);
		assertEquals("My Full Album:\n\tFirst Song: 4:32", a1.toString());
	}
	
	/**
	 * Test the toString method with a full album
	 */
	@Test
	public void testAlbumtoStringFull()
	{
		Album a1 = new Album("My Full Album", 3);
		Song s1 = new Song("First Song", 272);
		a1.addSong(s1);
		Song s2 = new Song("Second Song", 305);
		a1.addSong(s2);
		Song s3 = new Song("Third Song", 249);
		a1.addSong(s3);
		assertEquals("My Full Album:\n\tFirst Song: 4:32\n\tSecond Song: 5:05\n\tThird Song: 4:09", a1.toString());		
	}
	
	/**
	 * Test the toString method if the album is only part full.
	 */
	@Test
	public void testAlbumtoStringPartFull()
	{
		Album a1 = new Album("My Full Album", 3);
		Song s1 = new Song("First Song", 272);
		a1.addSong(s1);
		Song s2 = new Song("Second Song", 305);
		a1.addSong(s2);
		assertEquals("My Full Album:\n\tFirst Song: 4:32\n\tSecond Song: 5:05", a1.toString());		
	}
}
