package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Tile;

public class TileTest {

	@Test
	public void testHasMine() {
		Tile mina = new Tile(true);
		Tile tile = new Tile(false);
		
		assertEquals(true, mina.hasMine());
		assertEquals(false, tile.hasMine());
	}

	@Test
	public void testnMines() {
		Tile tile = new Tile(false);
		tile.setnMines(0);
		assertEquals(0, tile.getnMines());
		tile.setnMines(1);
		assertEquals(1, tile.getnMines());
		tile.setnMines(8);
		assertEquals(8, tile.getnMines());
		tile.setnMines(9);
		assertEquals(8, tile.getnMines());
	}
}
