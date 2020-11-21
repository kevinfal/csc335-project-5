import java.util.HashMap;
import java.util.Random;
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
	
	/**
	 * Determines whether someone has won or not
	 * @return 0 if no player has won, 1 if red has won, 2 if yellow has won
	 */
	public int isGameOver() {
		return model.isGameOver();
	}
	
	/**
	 * Checks to see if anyone has won yet, and the board is not full
	 * @return true if a player has won or if the board is full
	 */
	public boolean isGameOverBoolean() {
		int check = model.isGameOver();
		return check > 0 || check == -1;
	}
	/**
	 * This method manually places a color at a column
	 * @param boardCol - color of token
	 * @param color - color of token
	 * @return true if move was successful
	 */
	public boolean move(int boardCol, char color) {
		return model.move(boardCol, color);
	}
	/**
	 * This is really only for testing
	 * Prints out every spot on the board in
	 * the order that it would look like in gui
	 */
	public String toString() {
		String returned = "";
		HashMap<Integer,Character[]> board = model.getBoard();
		for(int row = 5; row >= 0; row--) {
			String added = "";
			for(int col = 0; col < 7; col++) {
				added+= board.get(col)[row];
			}
			returned += added+"\n";
		}
		return returned;
	}
	/**
	 * Adds a token to the board, to be used by the
	 * players. Takes column input
	 * @param col - column to add a token to
	 * @return True if move was successful, false if not a valid move
	 */
	public boolean humanTurn(int col) {
		//if col is out of bounds
		if(col > 6 || col < 0)
			return false;
		int turn = model.getTurns();
		boolean even = turn % 2 == 0;
		if(even) // even turn, use yellow
			return model.move(col, 'r');
		else //odd turn, use yellow
			return model.move(col, 'y');
	}
	/**
	 * Simulates a "turn", places a token
	 * in a random legal spot on the board
	 * @return true if a move was made, false if there was no possible move
	 */
	public boolean computerTurn() {
		int col = new Random().nextInt(6);
		boolean moved = humanTurn(col);
		while(moved == false && model.isGameOver() == 0 ) {
			col = new Random().nextInt(6);
			moved = humanTurn(col);
		}
		if(moved)
			return true;
		return false;
	}

	public static void main(String[] args) {
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		char r = 'r';
		char y = 'y';
		int inc = -1;
		for(int i = 0; i < 42; i++) {
			game.computerTurn();
		}
	
		System.out.println(game);
		System.out.println(game.isGameOver());
		
	}

}
