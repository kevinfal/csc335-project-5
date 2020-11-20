import java.util.HashMap;
public class Connect4Model {
	
	private int turns = 0;
	public static final int BOARD_DIM = 7;
	/*
	 * The key represents the column to index
	 * Value holds array of characters that are in that column
	 * 		End of the Array/Length holds the top of the board
	 * 		Beginning index/0 holds the bottom of the board
	 */
	private HashMap<Integer,Character[]> board;
	
	public Connect4Model() {
		board = new HashMap<Integer,Character[]>();
		fillBoard();
	}
	
	public HashMap<Integer,Character[]> getBoard(){
		return board;
	}
	
	/**
	 * fills the board to be blank, full of 'w' chars
	 */
	public void fillBoard() {
		for(int col = 0; col< BOARD_DIM; col++) {
			Character[] added = new Character[BOARD_DIM];
			for(int i = 0; i<BOARD_DIM; i++)
				added[i] = 'w';
			board.put(col,added);
		}
			
	}
	
	/**
	 * 
	 * @param boardCol - column to add token to
	 * @param color - color of token to add
	 * @return true if move was successful, false otherwise
	 */
	public boolean move(int boardCol, char color) {
		int row = tryMove(boardCol);
		if(row == -1)//move failed, don't notify observers
			return false;
		//move is possible, add and notify observers
		Character[] column = board.get(boardCol);
		column[row] = color;
		board.put(boardCol, column); 
		return true;
	}
	/**
	 * Gets the index that the token will go to
	 * If token is -1, then no move possible
	 * @param boardCol - column (of the board representation)
	 * @return index to place new token, -1 if does not fit
	 */
	public int tryMove(int boardCol) {
		Character[] column = board.get(boardCol);
		int i = 0;
		//move up the column until an an empty/'w' is found;
		//if there is not one, move is 
		for( ; column[i] != 'w'; i++)
			if(i >= BOARD_DIM -1)
				return -1;
		return i; 
	}
	
	/**
	 * Check if a player has won
	 * @return 0 if no p layer has won, 1 if red has won, 2 if yellow has won
	 */
	public int isGameOver() {
		int check = 0;
		//check rows first
		check = checkRows();
		if(checkRows() != 0) {
			return check;
		}
		//then check columns
		check = checkCols();

		if(checkCols() != 0) {
			return check;
		}
		System.out.println("won1");
		return 0;
	}
	/**
	 * Checks the board for a horizontal win
	 * @return 0 if no player has won, 1 if red has won, 2 if yellow has won
	 */
	private int checkRows() {
		//iterate rows
		for(int row = 0; row < BOARD_DIM; row++) {
			int count = 1;
			//get index of row from each column
			for(int col = 0; col< BOARD_DIM; col++) {
				count++;
				//check if 0, can't check prev if 0
				if(col == 0)
					continue;
				else {
					//
					char prev = board.get(col-1)[row];
					char curr = board.get(col)[row];
					//not a repeating character
					if(prev!=curr)
						count = 1;
					//otherwise repeating
					//4 are in a row
					if(count == 4) {
						//4 empty/white spaces
						if(curr == 'w') {
							//because 4 > (7/2)
							return 0; //change to continue if want bigger board
						}
						//4 red tokens
						else if(curr == 'r')
							return 1;
						//4 yellow tokens
						else if(curr == 'y')
							return 2;
					}
				}
			}
		}
		return 0;
	}
	/**
	 * Checks for a vertical win
	 * @return 0 if no player has won, 1 if red has won, 2 if yellow has won
	 */
	private int checkCols() {
		for(int col = 0; col < BOARD_DIM; col++) {
			Character[] column = board.get(col);
			//check for repeats of 4
			int count = 1;
			//iterate through column
			for(int i = 0; i < BOARD_DIM; i++) {
				count++;
				if(i == 0)
					continue;
				else {
					char prev = column[i-1];//won't be out of bounds
					char curr = column[i];
					//not a repeating character
					if(prev != curr)
						count = 1;
					//if there are 4 repeating
					if(count == 4) {
						//4 empty/white spaces
						if(curr == 'w') {
							return 0;//change to continue if want bigger board
						}
						//4 red tokens
						else if(curr == 'r')
							return 1;
						//4 yellow tokens
						else if(curr == 'y')
							return 2;
							
					}
				}
			}
		}
		return 0;
	}
	
}
