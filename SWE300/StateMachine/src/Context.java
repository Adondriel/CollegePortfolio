public class Context {

	private State state;
	private int sign;
	private double value, point;

	public Context() {
		this.state = State_Start.getState();
		this.setSign(1);
		this.setValue(0);
	}

	public void execute(char c) {
		state.execute(this, c);
	}

	public void setCurrentState(State state) {
		this.state = state;
	}

	public State getCurrentState() {
		return this.state;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}
}
