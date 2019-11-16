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

}
