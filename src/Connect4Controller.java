import java.util.HashMap;

/**
 * 
 * @author Kevin Falconett
 *
 */
public class Connect4Controller {
	
	private Connect4Model model; 
	
	public Connect4Controller(Connect4Model model) {
		this.model = model;
	}
	
	//make move
	public boolean move(int col, char color) {
		return model.move(col,color);
	}
	/**
	 * Determines whether someone has won or not
	 * @return 0 if no player has won, 1 if red has won, 2 if yellow has won
	 */
	public int isGameOver() {
		/*
		 * TODO CHECK IF WANT BOOLEAN OR NUMBER+NOTIFY FOR IMPLEMENTATION
		 */
		return model.isGameOver();
	}
	/*
	 * REMOVE LATER
	 */
	public HashMap<Integer,Character[]> getBoard() {
		return model.getBoard();
	}
	
	/**
	 * This is really only for testing
	 * Prints out every spot on the board in
	 * the order that it would look like in gui
	 */
	public String toString() {
		String returned = "";
		HashMap<Integer,Character[]> board = model.getBoard();
		for(int row = 6; row >= 0; row--) {
			String added = "";
			for(int col = 0; col < 7; col++) {
				added+= board.get(col)[row];
			}
			returned += added+"\n";
		}
		return returned;
	}
	
	public static void main(String[] args) {
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		char r = 'r';
		char y = 'y';
		for(int i = 0;i < 10; i++)
			game.move(0, r);
		for(int i = 0; i < 10; i++)
			game.move(1, y);
		System.out.println(game);
		System.out.println(game.isGameOver());

	}
}
