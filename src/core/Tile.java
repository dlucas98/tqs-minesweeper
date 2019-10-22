package core;

public class Tile {
	enum Status {
		OPEN,
		MINE,
		MARK,
		HIDDEN
	}
	private boolean mine;
	private Tile.Status status;
	private boolean hasCursor;
	private int nMines;

	public Tile(boolean mine) {
		
	}
	
	public boolean hasCursor() {
		return hasCursor;
	}

	public void setHasCursor(boolean hasCursor) {
		this.hasCursor = hasCursor;
	}

	public boolean hasMine() {
		return mine;
	}

	public Tile.Status getStatus() {
		return status;
	}

	public void setnMines(int nMines) {
		this.nMines = nMines;
	}	
	
	public int getnMines() {
		return this.nMines;
	}
	
	public Tile.Status open() {
		return null;
	}
	
	public void mark() {
		
	}
}