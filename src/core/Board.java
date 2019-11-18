package core;

import java.time.Instant;

import core.Cursor.Direction;

/*
 * Class that contains a Tile matrix and information about the game.
 */
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
	private long time;
	private Board.Dificulty difficulty;
	
	public static final int DEFAULT_WIDTH = 9;
	public static final int DEFAULT_HEIGHT = 9;
	public static final int DEFAULT_MINES = 20;

	public static final int DEFAULT_INTERMEDIATE_WIDTH = 16;
	public static final int DEFAULT_INTERMEDIATE_HEIGHT = 16;
	public static final int DEFAULT_INTERMEDIATE_MINES = 40;
	
	public static final int DEFAULT_HARD_WIDTH = 30;
	public static final int DEFAULT_HARD_HEIGHT = 16;
	public static final int DEFAULT_HARD_MINES = 99;
	
	/**
	 * Default constructor.
	 * @param board Tile matrix.
	 */
	public Board(Tile[][] board) {
		this.cursor = new Cursor(this);
		this.board = board;

		if(board[0].length == DEFAULT_WIDTH)
			this.difficulty = Board.Dificulty.EASY;
		if(board[0].length == DEFAULT_INTERMEDIATE_WIDTH)
			this.difficulty = Board.Dificulty.MEDIUM;
		if(board[0].length == DEFAULT_HARD_WIDTH)
			this.difficulty = Board.Dificulty.HARD;
		
		int mines = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j].hasMine())
					mines++;
				board[i][j].setnMines(calcularNMines(i, j));
			}
		}
		this.mines = mines;
		this.board[0][0].setHasCursor(true);
	
		//Will be used to calculate score
		Instant instant = Instant.now();
		time = instant.getEpochSecond();
	}
	
	/**
	 * Getter for number of mines contained in the Board
	 * @return number of mines
	 */
	public int getMines() {
		return this.mines;		
	}
	
	/**
	 * Getter for the Board width
	 * @return width of the board
	 */
	public int getWidth() {
		return board[0].length;
	}
	
	/**
	 * Getter of the Board height
	 * @return height of the board
	 */
	public int getHeight() {
		return board.length;
	}
	
	/**
	 * Getter for the Cursor instance used in the Board
	 * @return Cursor instance
	 */
	public Cursor getCursor() {
		return this.cursor;
	}
	
	/**
	 * Getter for the Tile where is placed the cursor.
	 * @return Selected tile.
	 */
	public Tile getTile() {
		return board[cursor.getY()][cursor.getX()];
	}
	
	/**
	 * Getter for a tile placed at the desired x and y positions
	 * @param x x axis position
	 * @param y y axis position
	 * @return Desired tile
	 */
	public Tile getTile(int x, int y) {
		if(y >= 0 && y < board[0].length && x >= 0 && x < board.length)
			return board[x][y];
		return null;
	}
	
	/**
	 * Getter for the starting time of the Board. When the game started.
	 * @return
	 */
	public long getTime() {
		return this.time;
	}
	
	/**
	 * Getter for the Board.Difficulty of the Board
	 * @return
	 */
	public Board.Dificulty getDifficulty() {
		return this.difficulty;
	}
	
	/**
	 * Move the cursor towards the desired Cursor.Direction
	 * @param d desired direction
	 */
	public void moveCursor(Direction d) {
		getTile().setHasCursor(false);
		getCursor().move(d);
		getTile().setHasCursor(true);
	}
	
	/**
	 * Print method. Returns a string with a visual representation of the actual Board.
	 * @return String of the actual representation of the Board
	 */
	public String print() {
		String ret = "";
		String pre = " ";
		String post = " ";
		String tile = " ";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				switch (board[i][j].getStatus()) {
				case HIDDEN:
					tile = "#";
					break;
				case OPEN:
					if(board[i][j].getnMines() == 0)
						tile = " ";
					else
						tile = String.valueOf(board[i][j].getnMines());
					break;
				case MARK:
					tile = "X";
					break;
				case MINE:
					tile = "*";
					break;
				}
				if(board[i][j].hasCursor()) {
					pre = "[";
					post = "]";
				} else {
					pre = " ";
					post = " ";
				}
				if(j == 0)
					ret += pre + tile + post;
				else {
					if(board[i][j].hasCursor()) {
						ret = ret.substring(0, ret.length() - 1);
						ret += pre + tile + post;
					} else {
						ret += tile + post;
					}
				}
			}
			ret += "\n";
		}
		return ret;
	}
	
	/**
	 * Opens the tile where the Cursor instance is placed.
	 * @return Tile.Status of the tile after opening it.
	 */
	public Tile.Status openTile() {
		getTile().open();
		openXY(cursor.getY(), cursor.getX());
		return board[cursor.getY()][cursor.getX()].getStatus();
	}
	
	/**
	 * Marks the tile where the Cursor instance is placed.
	 */
	public void markTile() {
		board[cursor.getY()][cursor.getX()].mark();
	}
	
	/**
	 * Opens a the Tile at the desired x and y position
	 * @param x desired X position
	 * @param y desired Y position
	 */
	private void openXY(int x, int y) {
		//Comprovar si som dins l'espai de caselles
		if(y >= 0 && y < board[0].length && x >= 0 && x < board.length) {
			if(!board[x][y].hasMine()) //Per obrir els mars no volem obrir les mines
				board[x][y].open();
			
			//Condicions abans de cridar openxy per no fer stack overflow
			if(x > 0)
				if(getTile(x - 1, y).getStatus() == Tile.Status.HIDDEN && !getTile(x - 1, y).hasMine())
					openXY(x - 1, y);
			if(y > 0)
				if(getTile(x, y - 1).getStatus() == Tile.Status.HIDDEN && !getTile(x, y - 1).hasMine())
					openXY(x, y - 1);
			if(x < board.length - 1)
				if(getTile(x + 1, y).getStatus() == Tile.Status.HIDDEN && !getTile(x + 1, y).hasMine())
					openXY(x + 1, y);
			if(y < board[0].length - 1)
				if(getTile(x, y + 1).getStatus() == Tile.Status.HIDDEN && !getTile(x, y + 1).hasMine())
					openXY(x, y + 1);
		}
	}
	
	/**
	 * <b>DO NOT USE, ITS ONLY PURPOSE IS TO TEST.</b><br>
	 * Wraper to call a private function. 
	 */
	public void openXYwrapper(int x, int y) {
		openXY(x, y);
	}
	
	/**
	 * Gets the actual Board.Status of the Board
	 * @return
	 */
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
		
		if(this.mines == closed)
			return Status.WIN;
		return Status.IN_PROGRESS;
	}
	
	/**
	 * Calculates the number of near mines of the desired Tile
	 * @param x desired X position
	 * @param y desired Y position
	 * @return number of near mines
	 */
	public int calcularNMines(int x, int y) {
		int m = 0;
		//(-1,-1)
		if(x > 0 && y > 0)
			if(board[x-1][y-1].hasMine())
				m++;
		//(0,-1)
		if(y > 0)
			if(board[x][y-1].hasMine())
				m++;
		//(1,-1)
		if(x < board.length - 1 && y > 0)
			if(board[x+1][y-1].hasMine())
				m++;
		
		//(-1,0)
		if(x > 0)
			if(board[x-1][y].hasMine())
				m++;
		//(1,0)
		if(x < board.length - 1)
			if(board[x+1][y].hasMine())
				m++;
		
		//(-1,1)
		if(x > 0 && y < board[0].length - 1)
			if(board[x-1][y+1].hasMine())
				m++;
		//(0,1)
		if(y < board[0].length - 1)
			if(board[x][y+1].hasMine())
				m++;
		//(1,1)
		if(x < board.length - 1 && y < board[0].length - 1)
			if(board[x+1][y+1].hasMine())
				m++;
		
		return m;
	}
}
