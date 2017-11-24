public class GameSquare {
	private int exPower = 0;
	private int row = 0;
	private int col = 0;
	
	// 1 - construction
	public GameSquare(int row, int col, int exPower) {
		this.row = row;
		this.col = col;
		this.exPower = exPower;
	}
	
	// 2 - getters, setters
	int getCol(){
		return col;
	}
	int getRow() {
		return row;
	}
	int getExpower() {
		return exPower;
	}
	void setExpower(int exPower) {
		this.exPower = exPower;
		// not return for a setter
	}
}