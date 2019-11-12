package core;

public class Board {
	public enum Dificulty {
		EASY,
		MEDIUM,
		HARD,
		CUSTOM
	}
	public enum Status {
		WIN,
		LOST,
		IN_PROGRESS
	}
	private Tile[][] board;
	private int mines;
	private Cursor cursor;
	private int time;
	
	final int DEFAULT_WIDTH = 9;
	final int DEFAULT_HEIGHT = 9;
	final int DEFAULT_MINES = 20;

	final int DEFAULT_INTERMEDIATE_WIDTH = 16;
	final int DEFAULT_INTERMEDIATE_HEIGHT = 16;
	final int DEFAULT_INTERMEDIATE_MINES = 40;
	
	final int DEFAULT_HARD_WIDTH = 30;
	final int DEFAULT_HARD_HEIGHT = 16;
	final int DEFAULT_HARD_MINES = 99;
	
	public Board(Tile[][] board) {
		this.board = board;
		int mines = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j].hasMine())
					mines++;
			}
		}
		this.mines = mines;
	}
	
	public int getMines() {
		return this.mines;		
	}
	
	public int getWidth() {
		return board[0].length;
	}
	
	public int getHeight() {
		return board.length;
	}
	
	public Cursor getCursor() {
		return this.cursor;
	}
	
	public Tile getTile() {
		return board[cursor.getY()][cursor.getX()];
	}
	
	public Tile getTile(int x, int y) {
		return board[y][x];
	}
	
	public void print() {
		
	}
	
	public Tile.Status openTile() {
		openXY(cursor.getY(), cursor.getX());
		return board[cursor.getY()][cursor.getX()].getStatus();
	}
	
	public void markTile() {
		board[cursor.getY()][cursor.getX()].mark();
	}
	
	private void openXY(int x, int y) {
		//Comprovar si som dins l'espai de caselles
		if(x >= 0 && x < board[0].length && y >= 0 && y < board.length) {
			board[y][x].open();
			openXY(x - 1, y - 1);
			openXY(x - 1, y);
			openXY(x - 1, y + 1);
			openXY(x, y - 1);
			openXY(x, y + 1);
			openXY(x + 1, y - 1);
			openXY(x + 1, y);
			openXY(x + 1, y + 1);
		}
	}
	
	public Status checkStatus() {
		int closed = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j].getStatus() == Tile.Status.HIDDEN || board[i][j].getStatus() == Tile.Status.MARK)
					closed++;
				if(board[i][j].getStatus() == Tile.Status.MINE)
					return Status.LOST;
			}
		}
		
		if(mines == closed)
			return Status.WIN;
		return Status.IN_PROGRESS;
	}
}
