package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.Board;
import core.Board.Dificulty;
import core.Cursor.Direction;

public class BoardTest {

	@Test
	public void testConstructor() {
		Board b = new Board();
		assertEquals(9, b.getWidth());
		assertEquals(9, b.getHeight());
		assertEquals(10, b.getMines());
		
		Board b2 = new Board(20, 20, 401);
		assertEquals(20, b2.getWidth());
		assertEquals(20, b2.getHeight());
		assertEquals(399, b2.getMines());
		
		Board b3 = new Board(0, 0, 0);
		assertEquals(9, b3.getWidth());
		assertEquals(9, b3.getHeight());
		assertEquals(10, b3.getMines());
		
		Board b4 = new Board(-1, -1, -10);
		assertEquals(9, b4.getWidth());
		assertEquals(9, b4.getHeight());
		assertEquals(10, b4.getMines());

		Board b5 = new Board(Dificulty.EASY);
		assertEquals(9, b5.getWidth());
		assertEquals(9, b5.getHeight());
		assertEquals(10, b5.getMines());
		
		Board b6 = new Board(Dificulty.MEDIUM);
		assertEquals(16, b6.getWidth());
		assertEquals(16, b6.getHeight());
		assertEquals(40, b6.getMines());
		
		Board b7 = new Board(Dificulty.HARD);
		assertEquals(30, b7.getWidth());
		assertEquals(16, b7.getHeight());
		assertEquals(99, b7.getMines());
	}
	
	@Test
	public void testOpenTile() {
		Board b = new Board();
		b.getCursor().move(Direction.BOTTOM);
		//Falta mock per fer l'init del taulell
	}

}
