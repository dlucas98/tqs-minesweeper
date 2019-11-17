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
	
	public static final int DEFAULT_WIDTH = 9;
	public static final int DEFAULT_HEIGHT = 9;
	public static final int DEFAULT_MINES = 20;

	public static final int DEFAULT_INTERMEDIATE_WIDTH = 16;
	public static final int DEFAULT_INTERMEDIATE_HEIGHT = 16;
	public static final int DEFAULT_INTERMEDIATE_MINES = 40;
	
	public static final int DEFAULT_HARD_WIDTH = 30;
	public static final int DEFAULT_HARD_HEIGHT = 16;
	public static final int DEFAULT_HARD_MINES = 99;
	
	public Board(Tile[][] board) {
		this.cursor = new Cursor(this);
		this.board = board;
		int mines = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j].hasMine())
					mines++;
				board[i][j].setnMines(calcularNMines(i, j));
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
		if(y >= 0 && y < board[0].length && x >= 0 && x < board.length)
			return board[x][y];
		return null;
	}
	
	public String print() {
		String ret = "";
		char pre = ' ';
		char post = ' ';
		char tile = ' ';
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				switch (board[i][j].getStatus()) {
				case HIDDEN:
					tile = '#';
					break;
				case OPEN:
					tile = ' ';
					break;
				case MARK:
					tile = 'X';
					break;
				case MINE:
					tile = '*';
					break;
				}
				if(board[i][j].hasCursor()) {
					pre = '[';
					post = ']';
				}
				ret += pre + tile + post;
			}
		}
		return ret;
	}
	
	public Tile.Status openTile() {
		getTile().open();
		openXY(cursor.getY(), cursor.getX());
		return board[cursor.getY()][cursor.getX()].getStatus();
	}
	
	public void markTile() {
		board[cursor.getY()][cursor.getX()].mark();
	}
	
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
	
	public void openXYwrapper(int x, int y) {
		openXY(x, y);
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
		
		if(this.mines == closed)
			return Status.WIN;
		return Status.IN_PROGRESS;
	}
	
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
		if(x < board.length - 1 && y < board[0].length - 1)
			if(board[x+1][y+1].hasMine())
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
