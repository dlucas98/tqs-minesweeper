package core;

public class Board {
	public enum Dificulty {
		EASY,
		MEDIUM,
		HARD,
		CUSTOM
	}
	private Tile[][] board;
	private int mines;
	private Cursor cursor;
	
	final int DEFAULT_WIDTH = 9;
	final int DEFAULT_HEIGHT = 9;
	final int DEFAULT_MINES = 20;

	final int DEFAULT_INTERMEDIATE_WIDTH = 16;
	final int DEFAULT_INTERMEDIATE_HEIGHT = 16;
	final int DEFAULT_INTERMEDIATE_MINES = 40;
	
	final int DEFAULT_HARD_WIDTH = 30;
	final int DEFAULT_HARD_HEIGHT = 16;
	final int DEFAULT_HARD_MINES = 99;
	
	public Board() {
		
	}
	
	public Board(int height, int width, int mines) {
		
	}
	
	public Board(Dificulty dificulty) {
		
	}
	
	public int getMines() {
		
	}
	
	public int getWidth() {
		
	}
	
	public int getHeight() {
		
	}
	
	public getTile(int x, int y) {
		
	}
	
	private void build(int height, int width, int mines) {
		
	}
	
	public void print() {
		
	}
	
	public void openTile() {
		
	}
	
	private void openXY(int x, int y) {
		
	}
}
