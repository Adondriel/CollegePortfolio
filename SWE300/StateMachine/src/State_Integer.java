public class State_Integer implements State{

	private static State state;
	
	public static State getState(){
		if( State_Integer.state == null ){
			State_Integer.state = new State_Integer();
		}
		return State_Integer.state;
	}
	
	@Override
	public void execute( Context context , char c ){
		
		if( Character.isDigit( c ) ){
			
			context.setValue( ( 10 * context.getValue() ) + Character.getNumericValue( c ) );
			context.setCurrentState( State_Integer.getState() );
			return;
			
		}else if( c == '.' ){
				
				context.setPoint( 0.1 );
				context.setCurrentState( State_Decimal.getState() );
				return;
				
		}else if( c == '\0' ){
				
			context.setValue( context.getSign() * context.getValue() );
			context.setCurrentState( State_End.getState() );
			return;
			
		}else{
			context.setValue( 0 );
			context.setCurrentState( State_End.getState() );
		}
		
	}

}
