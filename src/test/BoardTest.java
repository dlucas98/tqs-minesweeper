package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.Board;
import core.Cursor;
import core.Board.Dificulty;
import core.Cursor.Direction;
import core.Tile;
import core.Tile.Status;
import mocks.Map;

public class BoardTest {

	@Test
	public void testConstructor() {
		Map m = new Map();
		Board b = m.getBoard();
		assertEquals(9, b.getWidth());
		assertEquals(9, b.getHeight());
		assertEquals(20, b.getMines());
		
		Map m2 = new Map(20, 20, 401);
		Board b2 = m2.getBoard();
		assertEquals(20, b2.getWidth());
		assertEquals(20, b2.getHeight());
		assertEquals(399, b2.getMines());
		
		Map m3 = new Map(0, 0, 0);
		Board b3 = m3.getBoard();
		assertEquals(9, b3.getWidth());
		assertEquals(9, b3.getHeight());
		assertEquals(10, b3.getMines());
		
		Map m4 = new Map(-1, -1, -10);
		Board b4 = m4.getBoard();
		assertEquals(9, b4.getWidth());
		assertEquals(9, b4.getHeight());
		assertEquals(10, b4.getMines());

		Map m5 = new Map(Dificulty.EASY);
		Board b5 = m5.getBoard();
		assertEquals(9, b5.getWidth());
		assertEquals(9, b5.getHeight());
		assertEquals(10, b5.getMines());

		Map m6 = new Map(Dificulty.MEDIUM);
		Board b6 = m6.getBoard();
		assertEquals(16, b6.getWidth());
		assertEquals(16, b6.getHeight());
		assertEquals(40, b6.getMines());

		Map m7 = new Map(Dificulty.HARD);
		Board b7 = m7.getBoard();
		assertEquals(30, b7.getWidth());
		assertEquals(16, b7.getHeight());
		assertEquals(99, b7.getMines());
	}
	
	@Test
	public void testOpenTile() {
		Map m = new Map();
		Board b = m.getBoard();
		b.getCursor().move(Direction.BOTTOM);
		assertEquals(Tile.Status.OPEN, b.openTile());
		
		b.getCursor().move(Direction.RIGHT);
		assertEquals(Tile.Status.MINE, b.openTile());
	}
	
	@Test
	public void testMarkTile() {
		Map m = new Map();
		Board b = m.getBoard();
		assertEquals(Tile.Status.HIDDEN, b.getTile().getStatus());
		b.markTile();
		assertEquals(Tile.Status.MARK, b.getTile().getStatus());
		b.markTile();
		assertEquals(Tile.Status.HIDDEN, b.getTile().getStatus());
	}
	
	@Test
	public void testCursor() {
		Map m = new Map();
		Board b = m.getBoard();

		//Test posicio inicial
		assertEquals(0, b.getCursor().getX());
		assertEquals(0, b.getCursor().getY());

		//Test posicio inferior
		b.getCursor().move(Cursor.Direction.BOTTOM);
		assertEquals(0, b.getCursor().getX());
		assertEquals(1, b.getCursor().getY());

		//Test a la dreta
		b.getCursor().move(Cursor.Direction.RIGHT);
		assertEquals(1, b.getCursor().getX());
		assertEquals(1, b.getCursor().getY());

		//Test cap amunt
		b.getCursor().move(Cursor.Direction.TOP);
		assertEquals(1, b.getCursor().getX());
		assertEquals(0, b.getCursor().getY());
		
		//Test a la esquerra
		b.getCursor().move(Cursor.Direction.LEFT);
		assertEquals(0, b.getCursor().getX());
		assertEquals(0, b.getCursor().getY());
	}
	
	@Test
	public void testGame() {
		Map m = new Map();
		Board b = m.getBoard();

		assertEquals(Board.Status.IN_PROGRESS, b.checkStatus());
		b.openTile();
		//Comprovar que el mar de mines esta descobert
		assertEquals(b.getTile(1, 1).getStatus(), Tile.Status.OPEN);
		assertEquals(b.getTile(3, 3).getStatus(), Tile.Status.OPEN);
		assertEquals(Board.Status.IN_PROGRESS, b.checkStatus());
		
		b.getCursor().move(Cursor.Direction.RIGHT);
		b.getCursor().move(Cursor.Direction.RIGHT);
		b.getCursor().move(Cursor.Direction.RIGHT);
		b.getCursor().move(Cursor.Direction.RIGHT);
		b.getCursor().move(Cursor.Direction.RIGHT);
		b.getCursor().move(Cursor.Direction.RIGHT);
		b.getCursor().move(Cursor.Direction.RIGHT);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.openTile();
		assertEquals(8, b.getTile().getnMines());
		assertEquals(Board.Status.IN_PROGRESS, b.checkStatus());

		b.getCursor().move(Cursor.Direction.LEFT);
		b.getCursor().move(Cursor.Direction.LEFT);
		b.getCursor().move(Cursor.Direction.LEFT);
		b.getCursor().move(Cursor.Direction.LEFT);
		b.getCursor().move(Cursor.Direction.LEFT);
		b.getCursor().move(Cursor.Direction.LEFT);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.openTile();
		assertEquals(Board.Status.WIN, b.checkStatus());
	}
	
	@Test
	public void testLose() {
		//Provem una partida en la que perdem
		Map m = new Map();
		Board b = m.getBoard();
		
		assertEquals(Board.Status.IN_PROGRESS, b.checkStatus());
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);

		//Quan intentem obrir una mina, perdem
		b.openTile();
		assertEquals(Board.Status.LOST, b.checkStatus());
	}
	
	@Test
	public void testOpenXY() {
		Map m = new Map();
		Board b = m.getBoard();
		
		b.openXYwrapper(0, 0);
		assertEquals(Status.OPEN, b.getTile(0,0).getStatus());
		assertEquals(Status.OPEN, b.getTile(1,0).getStatus());
		assertEquals(Status.OPEN, b.getTile(0,1).getStatus());
		assertEquals(Status.OPEN, b.getTile(1,1).getStatus());
	}

}
