public class State_Decimal implements State{

	private static State state;
	
	public static State getState(){
		
		if( State_Decimal.state == null ){
			
			State_Decimal.state = new State_Decimal();
			
		}
		
		return State_Decimal.state;
	}
	
	@Override
	public void execute( Context context , char c ){
		
		if( Character.isDigit( c ) ){
			
			context.setValue( context.getValue() + ( context.getPoint() * Character.getNumericValue( c ) ) );
			context.setPoint( context.getPoint() / 10 );
			context.setCurrentState( State_Decimal.getState() );
			return;
			
		}else if( c == '\0' ){
			
				context.setValue( context.getSign() * context.getValue() );
			
		}else{
			
			context.setValue( 0 );
			
		}		
		
		context.setCurrentState( State_End.getState() );
	
	}

}
