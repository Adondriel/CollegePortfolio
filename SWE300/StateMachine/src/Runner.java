import java.util.Scanner;

public class Runner{
	
	private static char[] inputChars;
	
	public static void main( String args[] ){
		
		Scanner userInput;
		
		try{			
			userInput = new Scanner( System.in );			
		}catch( Exception e ){			
			userInput = null;			
		}
		
		System.out.print("Please Enter Value: ");
		
		inputChars = (userInput.nextLine()+'\0').toCharArray();
		
		Context context = new Context(  );

		for( int i = 0; i < inputChars.length; i++ ){
			
			context.execute( inputChars[ i ] );
			
		}
		
		System.out.println( context.getValue() );
		
	}
	
}
