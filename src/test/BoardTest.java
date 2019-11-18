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
		//Test if we are able to generate every type of Board
		Map m = new Map();
		Board b = m.getBoard();
		assertEquals(Board.DEFAULT_WIDTH, b.getWidth());
		assertEquals(Board.DEFAULT_HEIGHT, b.getHeight());
		assertEquals(Board.DEFAULT_MINES, b.getMines());
		
		Map m2 = new Map(20, 20, 401);
		Board b2 = m2.getBoard();
		assertEquals(20, b2.getWidth());
		assertEquals(20, b2.getHeight());
		assertEquals(399, b2.getMines());
		
		Map m3 = new Map(0, 0, 0);
		Board b3 = m3.getBoard();
		assertEquals(Board.DEFAULT_WIDTH, b3.getWidth());
		assertEquals(Board.DEFAULT_HEIGHT, b3.getHeight());
		assertEquals(Board.DEFAULT_MINES, b3.getMines());
		
		Map m4 = new Map(-1, -1, -10);
		Board b4 = m4.getBoard();
		assertEquals(Board.DEFAULT_WIDTH, b4.getWidth());
		assertEquals(Board.DEFAULT_HEIGHT, b4.getHeight());
		assertEquals(Board.DEFAULT_MINES, b4.getMines());

		Map m5 = new Map(Dificulty.EASY);
		Board b5 = m5.getBoard();
		assertEquals(Board.DEFAULT_WIDTH, b5.getWidth());
		assertEquals(Board.DEFAULT_HEIGHT, b5.getHeight());
		assertEquals(Board.DEFAULT_MINES, b5.getMines());

		Map m6 = new Map(Dificulty.MEDIUM);
		Board b6 = m6.getBoard();
		assertEquals(Board.DEFAULT_INTERMEDIATE_WIDTH, b6.getWidth());
		assertEquals(Board.DEFAULT_INTERMEDIATE_HEIGHT, b6.getHeight());
		assertEquals(Board.DEFAULT_INTERMEDIATE_MINES, b6.getMines());

		Map m7 = new Map(Dificulty.HARD);
		Board b7 = m7.getBoard();
		assertEquals(Board.DEFAULT_HARD_WIDTH, b7.getWidth());
		assertEquals(Board.DEFAULT_HARD_HEIGHT, b7.getHeight());
		assertEquals(Board.DEFAULT_HARD_MINES, b7.getMines());
	}
	
	@Test
	public void testGetTile() {
		//Test invalid case of getTile() method.
		Map m = new Map();
		Board b = m.getBoard();
		assertEquals(null, b.getTile(-1,-1));
	}
	
	@Test
	public void testOpenTile() {
		//Test if Cursor and Tile are working correctly
		Map m = new Map();
		Board b = m.getBoard();
		b.getCursor().move(Direction.BOTTOM);
		b.openTile();
		assertEquals(Tile.Status.OPEN, b.openTile());
		
		b.getCursor().move(Direction.BOTTOM);
		b.getCursor().move(Direction.BOTTOM);
		b.getCursor().move(Direction.BOTTOM);
		b.getCursor().move(Direction.BOTTOM);
		b.openTile();
		assertEquals(Tile.Status.MINE, b.openTile());
	}
	
	@Test
	public void testMarkTile() {
		//Test if we can mark a Tile using Cursor instance
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
		//Test uf we cab move correctly the Cursor instance
		Map m = new Map();
		Board b = m.getBoard();

		//Test initial position
		assertEquals(0, b.getCursor().getX());
		assertEquals(0, b.getCursor().getY());

		//Test bottom movement
		b.getCursor().move(Cursor.Direction.BOTTOM);
		assertEquals(0, b.getCursor().getX());
		assertEquals(1, b.getCursor().getY());

		//Test right movement
		b.getCursor().move(Cursor.Direction.RIGHT);
		assertEquals(1, b.getCursor().getX());
		assertEquals(1, b.getCursor().getY());

		//Test top movement
		b.getCursor().move(Cursor.Direction.TOP);
		assertEquals(1, b.getCursor().getX());
		assertEquals(0, b.getCursor().getY());
		
		//Test left movement
		b.getCursor().move(Cursor.Direction.LEFT);
		assertEquals(0, b.getCursor().getX());
		assertEquals(0, b.getCursor().getY());
	}
	
	@Test
	public void testGame() {
		//Test if we can finish successfully a game
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
		assertEquals(Status.HIDDEN, b.getTile(0, 7).getStatus());
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
		//Test if we lose a game when we open a mine
		Map m = new Map();
		Board b = m.getBoard();
		
		assertEquals(Board.Status.IN_PROGRESS, b.checkStatus());
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);
		b.getCursor().move(Cursor.Direction.BOTTOM);

		//Open a mine and check if we lost
		b.openTile();
		assertEquals(Board.Status.LOST, b.checkStatus());
	}
	
	@Test
	public void testOpenXY() {
		//Check if we can successfully open tiles recursively
		Map m = new Map();
		Board b = m.getBoard();
		
		b.openXYwrapper(0, 0);
		assertEquals(Status.OPEN, b.getTile(0,0).getStatus());
		assertEquals(Status.OPEN, b.getTile(1,0).getStatus());
		assertEquals(Status.OPEN, b.getTile(0,1).getStatus());
		assertEquals(Status.OPEN, b.getTile(1,1).getStatus());
	}

	@Test
	public void testGui() {
		//Check if print() method works correctly
		Map m = new Map();
		Board b = m.getBoard(Dificulty.EASY);
		
		String s = "[#]# # # # # # # # \n" +
				   " # # # # # # # # # \n" +
				   " # # # # # # # # # \n" +
				   " # # # # # # # # # \n" +
				   " # # # # # # # # # \n" +
				   " # # # # # # # # # \n" +
				   " # # # # # # # # # \n" +
				   " # # # # # # # # # \n" +
				   " # # # # # # # # # \n";
		assertEquals(s, b.print());
		
		b.openTile();

		String s2 = "[ ]        2 # # # \n" +
					"           3 # # # \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s2, b.print());

		b.moveCursor(Direction.RIGHT);
		b.moveCursor(Direction.RIGHT);
		b.moveCursor(Direction.RIGHT);
		b.moveCursor(Direction.RIGHT);
		b.moveCursor(Direction.RIGHT);
		b.moveCursor(Direction.RIGHT);
		b.openTile();
		String s3 = "           2[*]# # \n" +
					"           3 # # # \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s3, b.print());
	}
	
}
