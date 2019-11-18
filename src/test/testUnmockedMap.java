package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Board;
import core.Board.Dificulty;
import core.Map;
import core.Minesweeper;

public class testUnmockedMap {

	@Test
	public void testEasy() {
		Map m = new Map();
		Board b = m.getBoard(Dificulty.EASY);

		//Test if the board has been correctly generated
		assertEquals(Board.DEFAULT_HEIGHT, b.getHeight());
		assertEquals(Board.DEFAULT_WIDTH, b.getWidth());
		assertEquals(Board.DEFAULT_MINES, b.getMines());
	}
	
	@Test
	public void testMedium() {
		Map m = new Map();
		Board b = m.getBoard(Dificulty.MEDIUM);
		
		//Test if the board has been correctly generated
		assertEquals(Board.DEFAULT_INTERMEDIATE_HEIGHT, b.getHeight());
		assertEquals(Board.DEFAULT_INTERMEDIATE_WIDTH, b.getWidth());
		assertEquals(Board.DEFAULT_INTERMEDIATE_MINES, b.getMines());
	}
	
	@Test
	public void testHard() {
		Map m = new Map();
		Board b = m.getBoard(Dificulty.HARD);

		//Test if the board has been correctly generated
		assertEquals(Board.DEFAULT_HARD_HEIGHT, b.getHeight());
		assertEquals(Board.DEFAULT_HARD_WIDTH, b.getWidth());
		assertEquals(Board.DEFAULT_HARD_MINES, b.getMines());
	}
	
	@Test
	public void testCustomDiff() {
		//Generating a Difficulty.CUSTOM board should return a Difficulty.EASY board
		//If we want to generate a custom board, we need to use Map(int,int,int) constructor
		Map m = new Map();
		Board b = m.getBoard(Dificulty.CUSTOM);

		//Test if the board has been correctly generated
		assertEquals(Board.DEFAULT_HEIGHT, b.getHeight());
		assertEquals(Board.DEFAULT_WIDTH, b.getWidth());
		assertEquals(Board.DEFAULT_MINES, b.getMines());
	}

	@Test
	public void testConstructor() {
		Map m = new Map(Dificulty.MEDIUM);
		Board b = m.getBoard();

		//Test if the board has been correctly generated
		assertEquals(Board.DEFAULT_INTERMEDIATE_HEIGHT, b.getHeight());
		assertEquals(Board.DEFAULT_INTERMEDIATE_WIDTH, b.getWidth());
		assertEquals(Board.DEFAULT_INTERMEDIATE_MINES, b.getMines());
	}
	
	@Test
	public void testCustom() {
		Map m = new Map(100,100,100);
		Board b = m.getBoard();

		//Test if the board has been correctly generated
		assertEquals(100, b.getHeight());
		assertEquals(100, b.getWidth());
		assertEquals(100, b.getMines());
	}
}
