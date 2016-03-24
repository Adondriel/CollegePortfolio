public class State_End implements State{
	
	private static State state;
	
	public static State getState(){
		
		if( State_End.state == null ){
			
			State_End.state = new State_End();
			
		}
		
		return State_End.state;
	}
	
	@Override
	public void execute( Context context , char c ){
		
		if( c == '\0'){
			return;
		}else{
			context.setValue( 0 );
		}
		
	}

}
