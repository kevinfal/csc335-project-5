import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Connect4ControllerTest {
	
	/**
	 * Test to make sure no tokens are added after having a full board
	 */
	@Test
	void testMaxTurns() {
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		for(int i = 0; i < 42; i++) 
			for(int c = 0; c < 7; c++)
				game.humanTurn(c);
		
		String before = game.toString();
		game.computerTurn();
		String after = game.toString();
		assertTrue(before.compareTo(after) == 0);
	}
		
	@Test
	void testGameOverFullBoard() {
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		for(int i = 0; i <= 42; i++) {
			game.computerTurn();
		}
		assertTrue(game.isGameOver() != 0);
	}
	
	/**
	 * Test to make sure negative columns are handled
	 */
	@Test
	void testAddColNegative() {
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		assertFalse(game.humanTurn(-1));
	}
	
	/**
	 * Test to make sure column out of bounds is handled
	 */
	@Test
	void testAddColOOB() {
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		assertFalse(game.humanTurn(Integer.MAX_VALUE));
	}
	/**
	 * Test diagonal win bottom left to right
	 */
	@Test
	void diagonalWinBT() {
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		game.humanTurn(0);
		game.humanTurn(1);
		game.humanTurn(1);
		game.humanTurn(2);
		game.humanTurn(2);
		game.humanTurn(1);
		game.humanTurn(2);
		game.humanTurn(3);
		game.humanTurn(3);
		game.humanTurn(3);
		game.humanTurn(3);
		assertTrue(game.isGameOverBoolean());
	}
	
	@Test 
	void diagonalWinTB2(){
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		for(int i = 0; i < 5; i++)
			game.move(0, 'n');
		System.out.println(game);
		assertTrue(false);
	}
	
	@Test
	void diagonalWinTB() {
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		game.humanTurn(0);
		game.humanTurn(1);
		game.humanTurn(1);
		game.humanTurn(2);
		game.humanTurn(2);
		game.humanTurn(1);
		game.humanTurn(2);
		game.humanTurn(3);
		game.humanTurn(4);
		game.humanTurn(4);
		game.humanTurn(1);game.humanTurn(1);
		game.humanTurn(3);
		assertTrue(game.isGameOverBoolean());
	}
	
	@Test
	void horizontalWin() {
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		for(int i = 0; i < 3; i++) {
			game.humanTurn(i);
			game.humanTurn(i);
		}
		game.humanTurn(4);
		assertTrue(game.isGameOverBoolean());
	}
	
	@Test
	void verticalWin() {
		Connect4Controller game = new Connect4Controller(new Connect4Model());
		for(int i = 0; i < 3; i++) {
			game.humanTurn(0);
			game.humanTurn(1);
		}
		game.humanTurn(0);
		assertTrue(game.isGameOverBoolean());
	}
	
	
}
