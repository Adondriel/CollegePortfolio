/**
 * A class of playing cards.
 * @author Adam Pine
 */
public class Card
{

	private int suit;
	private int faceValue;
	private static final String[] SUIT_DESCRIPTION = {"Spades", "Hearts", "Diamonds", "Clubs"};
	private static final String[] FACE_VALUE_DESCRIPTION = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}; 
	
	/**
	 * Create a new card with a given suit and value
	 * 
	 * @param v The face value of the card  (0-12)
	 * @param s The suit of the card (0-3)
	 */
	public Card(int v, int s)
	{
		faceValue = v;
		suit = s;
	}
	
	/**
	 * 
	 * @return the suit of this card
	 */
	public int getSuit()
	{
		return suit;
	}
	
	/**
	 * 
	 * @return the face value of this card
	 */
	public int getValue()
	{
		return faceValue;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return FACE_VALUE_DESCRIPTION[faceValue] + " of " + SUIT_DESCRIPTION[suit];
	}
}
