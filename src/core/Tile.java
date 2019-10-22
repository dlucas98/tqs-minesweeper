package core;

public class Tile {
	public enum Status {
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
		this.mine = mine;
		this.status = Tile.Status.HIDDEN;
		this.hasCursor = false;
		this.nMines = 0;
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
		if(this.status.equals(Tile.Status.HIDDEN)) {
			if(this.hasMine())
				this.status = Tile.Status.MINE;
			else 
				this.status = Tile.Status.OPEN;
		}
		return this.status;
	}
	
	public void mark() {
		if(this.status.equals(Tile.Status.HIDDEN))
			this.status = Tile.Status.MARK;
		if(this.status.equals(Tile.Status.MARK))
			this.status = Tile.Status.HIDDEN;
	}
}