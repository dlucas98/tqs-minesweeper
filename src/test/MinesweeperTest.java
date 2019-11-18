package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.Board;
import core.Minesweeper;
import core.Minesweeper.MenuStatus;
import core.Tile;
import core.Board.Dificulty;
import mocks.Input;
import mocks.Map;

public class MinesweeperTest {

	@Test
	public void testEasyGame() {
		Minesweeper m = new Minesweeper(new Input(), new Map());
		Input i = (Input)m.getInput(); //Cast to mock to call sendKey()		

		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		i.sendKey('1');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.CHOOSE_DIFFICULTY, m.getActualMenu());
		i.sendKey('1');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.BOARD, m.getActualMenu());
		assertEquals(20, m.getBoard().getMines()); //20 mines means EASY board
		
		i.sendKey('0');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
	}
	
	@Test
	public void testMediumGame() {
		Minesweeper m = new Minesweeper(new Input(), new Map());
		Input i = (Input)m.getInput(); //Cast to mock to call sendKey()		

		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		i.sendKey('1');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.CHOOSE_DIFFICULTY, m.getActualMenu());
		i.sendKey('2');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.BOARD, m.getActualMenu());
		assertEquals(40, m.getBoard().getMines()); //40 mines means MEDIUM board
		assertEquals(Board.Dificulty.MEDIUM, m.getBoard().getDifficulty());
		
		i.sendKey('0');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
	}
	
	@Test
	public void testHardGame() {
		Minesweeper m = new Minesweeper(new Input(), new Map());
		Input i = (Input)m.getInput(); //Cast to mock to call sendKey()		

		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		i.sendKey('1');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.CHOOSE_DIFFICULTY, m.getActualMenu());
		i.sendKey('3');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.BOARD, m.getActualMenu());
		assertEquals(99, m.getBoard().getMines()); //99 mines means HARD board
		assertEquals(Board.Dificulty.HARD, m.getBoard().getDifficulty());
		
		i.sendKey('0');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
	}
	
	@Test
	public void testHighScores() {
		Minesweeper m = new Minesweeper(new Input(), new Map());
		Input i = (Input)m.getInput(); //Cast to mock to call sendKey()		
		
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		i.sendKey('2');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.HIGH_SCORES, m.getActualMenu());
		i.sendKey('0');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
	}
	
	@Test
	public void test() {
		Minesweeper m = new Minesweeper(new Input(), new Map());
		Input i = (Input)m.getInput(); //Cast to mock to call sendKey()		

		//Test menu
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		i.sendKey('1');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.CHOOSE_DIFFICULTY, m.getActualMenu());
		i.sendKey('1');
		m.updateFrame();
		
		//Test movement
		i.sendKey('s');
		m.updateFrame();
		assertEquals(1, m.getBoard().getCursor().getY());
		i.sendKey('d');
		m.updateFrame();
		assertEquals(1, m.getBoard().getCursor().getX());
		i.sendKey('w');
		m.updateFrame();
		assertEquals(0, m.getBoard().getCursor().getY());
		i.sendKey('a');
		m.updateFrame();
		assertEquals(0, m.getBoard().getCursor().getX());
		
		i.sendKey('m');
		m.updateFrame();
		assertEquals(Tile.Status.MARK, m.getBoard().getTile().getStatus());
		i.sendKey('m');
		m.updateFrame();
		assertEquals(Tile.Status.HIDDEN, m.getBoard().getTile().getStatus());
		i.sendKey('o');
		m.updateFrame();
		assertEquals(Tile.Status.OPEN, m.getBoard().getTile().getStatus());
		
		i.sendKey('0');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
	}
	
	@Test
	public void testGui() {
		Minesweeper m = new Minesweeper(new Input(), new Map());
		Input i = (Input)m.getInput();
		m.updateFrame();
		String s =  "1-Play\n"+
					"2-HighScores\n"+
					"0-Exit";
		
		assertEquals(s, m.getOutput().getBuffer());
		
		String s2 = "EASY\n" + 
					"1-\n" + 
					"2-\n" + 
					"3-\n" + 
					"4-\n" + 
					"5-\n" + 
					"INTERMEDIATE\n" + 
					"1-\n" + 
					"2-\n" + 
					"3-\n" + 
					"4-\n" + 
					"5-\n" + 
					"HARD\n" + 
					"1-\n" + 
					"2-\n" + 
					"3-\n" + 
					"4-\n" + 
					"5-\n";
		i.sendKey('2');
		m.updateFrame();
		assertEquals(s2, m.getOutput().getBuffer());
		assertEquals(Minesweeper.MenuStatus.HIGH_SCORES, m.getActualMenu());
		
		i.sendKey('0');
		m.updateFrame();
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		
		String s3 = "1-EASY\n" + 
					"2-INTERMEDIATE\n" + 
					"3-HARD\n";
		i.sendKey('1');
		m.updateFrame();
		assertEquals(s3, m.getOutput().getBuffer());
		
		i.sendKey('1');
		m.updateFrame();
		
		String s4 = "[#]# # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n";
		assertEquals(s4, m.getOutput().getBuffer());
		
		i.sendKey('o');
		m.updateFrame();
		
		String s5 = "[ ]        2 # # # \n" +
					"           3 # # # \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s5, m.getOutput().getBuffer());
		
		i.sendKey('d');
		m.updateFrame();
		String s6 = "  [ ]      2 # # # \n" +
					"           3 # # # \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s6, m.getOutput().getBuffer());
		
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('s');
		m.updateFrame();
		//Test w key press
		i.sendKey('w');
		m.updateFrame();
		i.sendKey('s');
		m.updateFrame();

		String s7 = "           2 # # # \n" +
					"           3 #[#]# \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s7, m.getOutput().getBuffer());

		i.sendKey('o');
		m.updateFrame();
		String s8 = "           2 # # # \n" +
					"           3 #[8]# \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s8, m.getOutput().getBuffer());

		i.sendKey('a');
		m.updateFrame();
		i.sendKey('m');
		m.updateFrame();
		String s9 = "           2 # # # \n" +
					"           3[X]8 # \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s9, m.getOutput().getBuffer());

		//Statement coverage
		i.sendKey('1');
		m.updateFrame();
		i.sendKey('2');
		m.updateFrame();
		i.sendKey('3');
		m.updateFrame();
		assertEquals(s9, m.getOutput().getBuffer());
		
		i.sendKey('a');
		m.updateFrame();
		i.sendKey('a');
		m.updateFrame();
		i.sendKey('a');
		m.updateFrame();
		i.sendKey('a');
		m.updateFrame();
		i.sendKey('a');
		m.updateFrame();
		i.sendKey('s');
		m.updateFrame();
		i.sendKey('s');
		m.updateFrame();
		i.sendKey('s');
		m.updateFrame();
		i.sendKey('s');
		m.updateFrame();
		i.sendKey('s');
		m.updateFrame();
		i.sendKey('s');
		m.updateFrame();
		i.sendKey('o');
		m.updateFrame();
		/*String s10 = "           2 # # # \n" +
					 "           3 X 8 # \n" +
					 "           2 # # # \n" +
					 "           1 2 3 2 \n" +
					 " 2 3 3 2 1         \n" +
					 " # # # # 2         \n" +
					 " # # # # 3         \n" +
					 " 2[5]# # 3         \n" +
					 "   2 # # 2         \n";*/
		String s10 = "YOU WON!\n" +
				 "\n" + 
				 "Enter your name:\n";
		assertEquals(s10, m.getOutput().getBuffer());
	
		i.sendKey('D');
		m.updateFrame();
		String s11 = "YOU WON!\n" +
					 "\n" + 
					 "Enter your name:\nd";
		assertEquals(s11, m.getOutput().getBuffer());

		i.sendKey('A');
		m.updateFrame();
		String s12 = "YOU WON!\n" +
				 "\n" + 
				 "Enter your name:\nda";
		assertEquals(s12, m.getOutput().getBuffer());

		i.sendKey('V');
		m.updateFrame();
		String s13 = "EASY\n" + 
				"1-DAV\t0\n" + 
				"2-\n" + 
				"3-\n" + 
				"4-\n" + 
				"5-\n" + 
				"INTERMEDIATE\n" + 
				"1-\n" + 
				"2-\n" + 
				"3-\n" + 
				"4-\n" + 
				"5-\n" + 
				"HARD\n" + 
				"1-\n" + 
				"2-\n" + 
				"3-\n" + 
				"4-\n" + 
				"5-\n";
		assertEquals(s13, m.getOutput().getBuffer());

		i.sendKey('0');
		m.updateFrame();
		
		assertEquals(s, m.getOutput().getBuffer());
	}

	@Test
	public void testLost() {
		Minesweeper m = new Minesweeper(new Input(), new Map());
		Input i = (Input)m.getInput();
		m.updateFrame();

		//enter difficulty menu
		i.sendKey('1');
		m.updateFrame();
		//select easy board
		i.sendKey('1');
		m.updateFrame();
		//move to mined tile
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('d');
		m.updateFrame();
		i.sendKey('o');
		m.updateFrame();
		
		String s = "GAME OVER";
		assertEquals(Minesweeper.MenuStatus.LOST, m.getActualMenu());
		assertEquals(s, m.getOutput().getBuffer());
	}
	
	@Test
	public void testMovementOutsideBoard() {
		Minesweeper m = new Minesweeper(new Input(), new Map());
		Input i = (Input)m.getInput();
		m.updateFrame();

		i.sendKey('w');
		m.updateFrame();
		assertEquals(MenuStatus.MAIN_MENU, m.getActualMenu());
		assertEquals("1-Play\n2-HighScores\n0-Exit", m.getOutput().getBuffer());
		
		i.sendKey('a');
		m.updateFrame();
		assertEquals(MenuStatus.MAIN_MENU, m.getActualMenu());
		assertEquals("1-Play\n2-HighScores\n0-Exit", m.getOutput().getBuffer());
		
		i.sendKey('s');
		m.updateFrame();
		assertEquals(MenuStatus.MAIN_MENU, m.getActualMenu());
		assertEquals("1-Play\n2-HighScores\n0-Exit", m.getOutput().getBuffer());

		i.sendKey('d');
		m.updateFrame();
		assertEquals(MenuStatus.MAIN_MENU, m.getActualMenu());
		assertEquals("1-Play\n2-HighScores\n0-Exit", m.getOutput().getBuffer());
		
		i.sendKey('o');
		m.updateFrame();
		assertEquals(MenuStatus.MAIN_MENU, m.getActualMenu());
		assertEquals("1-Play\n2-HighScores\n0-Exit", m.getOutput().getBuffer());
		
		i.sendKey('m');
		m.updateFrame();
		assertEquals(MenuStatus.MAIN_MENU, m.getActualMenu());
		assertEquals("1-Play\n2-HighScores\n0-Exit", m.getOutput().getBuffer());
	}
}
