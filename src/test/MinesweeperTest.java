package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Board;
import core.Minesweeper;
import core.Tile;
import mocks.Input;

public class MinesweeperTest {

	@Test
	public void testEasyGame() {
		Minesweeper m = new Minesweeper(new Input());
		Input i = (Input)m.getInput(); //Cast to mock to call sendKey()		

		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		i.sendKey('1');
		assertEquals(Minesweeper.MenuStatus.CHOOSE_DIFFICULTY, m.getActualMenu());
		i.sendKey('1');
		assertEquals(Minesweeper.MenuStatus.BOARD, m.getActualMenu());
		assertEquals(20, m.getBoard().getMines()); //20 mines means EASY board
		
		i.sendKey('0');
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
	}
	
	@Test
	public void testMediumGame() {
		Minesweeper m = new Minesweeper(new Input());
		Input i = (Input)m.getInput(); //Cast to mock to call sendKey()		

		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		i.sendKey('1');
		assertEquals(Minesweeper.MenuStatus.CHOOSE_DIFFICULTY, m.getActualMenu());
		i.sendKey('2');
		assertEquals(Minesweeper.MenuStatus.BOARD, m.getActualMenu());
		assertEquals(40, m.getBoard().getMines()); //40 mines means MEDIUM board
		
		i.sendKey('0');
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
	}
	
	@Test
	public void testHardGame() {
		Minesweeper m = new Minesweeper(new Input());
		Input i = (Input)m.getInput(); //Cast to mock to call sendKey()		

		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		i.sendKey('1');
		assertEquals(Minesweeper.MenuStatus.CHOOSE_DIFFICULTY, m.getActualMenu());
		i.sendKey('3');
		assertEquals(Minesweeper.MenuStatus.BOARD, m.getActualMenu());
		assertEquals(99, m.getBoard().getMines()); //99 mines means HARD board
		
		i.sendKey('0');
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
	}
	
	@Test
	public void testHighScores() {
		Minesweeper m = new Minesweeper(new Input());
		Input i = (Input)m.getInput(); //Cast to mock to call sendKey()		
		
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		i.sendKey('2');
		assertEquals(Minesweeper.MenuStatus.HIGH_SCORES, m.getActualMenu());
		i.sendKey('0');
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
	}
	
	@Test
	public void test() {
		Minesweeper m = new Minesweeper(new Input());
		Input i = (Input)m.getInput(); //Cast to mock to call sendKey()		

		//Test menu
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
		i.sendKey('1');
		assertEquals(Minesweeper.MenuStatus.CHOOSE_DIFFICULTY, m.getActualMenu());
		i.sendKey('1');
		
		//Test movement
		i.sendKey('s');
		assertEquals(1, m.getBoard().getCursor().getY());
		i.sendKey('d');
		assertEquals(1, m.getBoard().getCursor().getX());
		i.sendKey('w');
		assertEquals(0, m.getBoard().getCursor().getY());
		i.sendKey('a');
		assertEquals(0, m.getBoard().getCursor().getX());
		
		i.sendKey('m');
		assertEquals(Tile.Status.MARK, m.getBoard().getTile().getStatus());
		i.sendKey('m');
		assertEquals(Tile.Status.HIDDEN, m.getBoard().getTile().getStatus());
		i.sendKey('o');
		assertEquals(Tile.Status.OPEN, m.getBoard().getTile().getStatus());
		
		i.sendKey('0');
		assertEquals(Minesweeper.MenuStatus.MAIN_MENU, m.getActualMenu());
	}
	
	@Test
	public void testGui() {
		Minesweeper m = new Minesweeper(new Input());
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
					 "Enter your name: \n";
		assertEquals(s10, m.getOutput().getBuffer());
		i.sendKey('D');
		m.updateFrame();
		String s11 = "YOU WON!\n" +
					 "\n" + 
					 "Enter your name: \n" + 
					 "D";
		assertEquals(s11, m.getOutput().getBuffer());
		i.sendKey('A');
		m.updateFrame();
		i.sendKey('V');
		m.updateFrame();
		
		assertEquals(s, m.getOutput().getBuffer());
	}

}
