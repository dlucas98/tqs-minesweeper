package interfaces;

import core.Board;
import core.Tile;

public interface Map {
	public Tile[][] generateMap(Board.Dificulty d);
	public Tile[][] generateMap(int height, int width, int mines);
	public Board getBoard(Board.Dificulty d);
}
