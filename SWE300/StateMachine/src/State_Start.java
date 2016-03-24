public class State_Start implements State{

private static State state;
	
	public static State getState(){
		if( State_Start.state == null ){
			State_Start.state = new State_Start();
		}
		return State_Start.state;
	}
	
	@Override
	public void execute( Context context , char c ){

		if( Character.isDigit( c ) ){
			context.setValue( Character.getNumericValue( c ) );
			context.setCurrentState( State_Integer.getState() );
			
		}else if( c == '-' ){
			
			context.setSign( -1 );
			context.setCurrentState( State_Integer.getState() );
				
		}else if( c == '+' ){
			
			context.setSign( 1 );
			context.setCurrentState( State_Integer.getState() );
				
		}else if( c == '.' ){
				
			context.setPoint( 0.1 );
			context.setCurrentState( State_Decimal.getState() );
				
		}else{
			
			context.setValue( 0 );
			context.setCurrentState( State_End.getState() );
			
		}
		
	}

}