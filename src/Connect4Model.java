import java.util.HashMap;
/**
 * 
 * @author Kevin Falconett
 *
 */
public class Connect4Model {
	
	private int turns = 0;
	public static final int BOARD_WIDTH = 7;
	public static final int BOARD_HEIGHT = 6;
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
	
	/**
	 * This only exists for testing
	 * TODO remove
	 * @return
	 */
	public HashMap<Integer,Character[]> getBoard(){
		return board;
	}
	
	/**
	 * fills the board to be blank, full of 'w' chars
	 */
	public void fillBoard() {
		for(int col = 0; col< BOARD_WIDTH; col++) {
			Character[] added = new Character[BOARD_WIDTH];
			for(int i = 0; i<BOARD_HEIGHT; i++)
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
		//if there is not one, move is valid
		for( ; column[i] != 'w'; i++)
			if(i >= BOARD_HEIGHT -1)
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
//		check = checkRows();
//		if(check != 0) {
//			return check;
//		}
//		//then check columns
//		check = checkCols();
//		if(check != 0) {
//			return check;
//		}
		check = checkDiagonals();
		if(check != 0) {
			return check;
		}
		System.out.println("wtf");
		return 0;
	}
	/**
	 * Checks the board for a horizontal win
	 * @return 0 if no player has won, 1 if red has won, 2 if yellow has won
	 */
	private int checkRows() {
		//iterate rows
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			int count = 1;
			//get index of row from each column
			for(int col = 0; col< BOARD_WIDTH; col++) {
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
		for(int col = 0; col < BOARD_WIDTH; col++) {
			Character[] column = board.get(col);
			//check for repeats of 4
			int count = 1;
			//iterate through column
			for(int i = 0; i < BOARD_HEIGHT; i++) {
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
	/**
	 * 
	 * @return
	 */
	private int checkDiagonals() {
		return checkDiagTB1();
	}
	
	/**
	 * Check if player has won a diagonal
	 * victory, checking from bottom left to top right
	 * ONLY CHECKS DIAGONALS THAT START A COLUMN 1
	 * AND ALL DIAGONALS THAT START AT ROWS 0 TO 3
	 * @return 0 if no player has won, 1 if red has won, 2 if yellow has won
	 */
	private int checkDiagBT1() {
		//starting column is always 0;
		//This function only checks the diagonals that start on rows 0 through 3
		for(int startRow = 0; startRow <= 3; startRow++) {
			int count = 0;
			for(int col = 0, row = startRow; col < BOARD_WIDTH && row < BOARD_HEIGHT; col++,row++) {
				count++;
				//can't check previous if col = 0
				if(col == 0)
					continue;
				else {
					//to get the previous subtract row and column by 1
					char prev = board.get(col - 1)[row -1];
					char curr = board.get(col)[row];
					//not a repeating character
					if(prev != curr)
						count = 1;
					//if there are 4 repeating
					if(count == 4) {
						//4 empty/white spaces
						if(curr == 'w') {
							continue;//change to continue if want bigger board
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
	 * Check if player has won a diagonal
	 * victory, checking from bottom left to top right
	 * ONLY CHECKS DIAGONALS FROM COLUMNS 1 THROUGH 3
	 * AND ALL DIAGONALS THAT START AT ROW 0 (bottom)
	 * @return 0 if no player has won, 1 if red has won, 2 if yellow has won
	 */
	private int checkDiagBT2() {
		//starting row is always 0;
		//This function only checks the diagonals that start on rows 0 through 3
		for(int startCol = 1; startCol <= 3; startCol++) {
			int count = 0;
			
			for(int col = startCol, row = 0; col < BOARD_WIDTH && row < BOARD_HEIGHT; col++,row++) {
				//System.out.print(board.get(col)[row]);
				count++;
				//can't check previous if col = 0
				if(row == 0)
					continue;
				else {
					//to get the previous subtract row and column by 1
					char prev = board.get(col - 1)[row -1];
					char curr = board.get(col)[row];
					//not a repeating character
					if(prev != curr)
						count = 1;
					//if there are 4 repeating
					if(count == 4) {
						//4 empty/white spaces
						if(curr == 'w') {
							continue;//change to continue if want bigger board
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
			//System.out.println();
		}
		return 0;
	}
	
	/**
	 * Check if player has won a diagonal
	 * victory, checking from top left to bottom right
	 * ONLY CHECKS DIAGONALS STARTING AT COLUMN 0
	 * AND ALL DIAGONALS THAT START AT ROWS 5 THROUGH 3
	 * @return 0 if no player has won, 1 if red has won, 2 if yellow has won
	 */
	private int checkDiagTB1() {
		for(int startRow = 5; startRow >=3; startRow--) {
			int count = 0;
			for(int row = startRow,col = 0; row >= 0 && col < BOARD_HEIGHT; col++, row--) {
				count++;
				System.out.print(board.get(col)[row]);

				//can't check previous if col = 0
				if(col == 0)
					continue;
				else {
					//to get the previous subtract row and column by 1
					char prev = board.get(col - 1)[row + 1];
					char curr = board.get(col)[row];
					//not a repeating character
					if(prev != curr)
						count = 1;
					//if there are 4 repeating
					if(count == 4) {
						//4 empty/white spaces
						if(curr == 'w') {
							continue;//change to continue if want bigger board
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
			System.out.println();
		}
		//nobody has won
		return 0;
	}
	
}
