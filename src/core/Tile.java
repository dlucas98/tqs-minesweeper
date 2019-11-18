package core;
/**
 * 
 * The Tile class is the minimal object in the Minesweeper.
 * Its function is to save the status and be set with or without mine.
 */
public class Tile {
	/**
	 * An enumeration in order to classify the diferent status a tile can have.
	 * OPEN: The tile was already opened.
	 * MINE: The tile was already opened and has a mine inside.
	 * MARK: The player marked the tile (probably a mine).
	 * HIDDEN: The player has not interacted with the tile yet.
	 */
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
	
	/**
	 * The constructor of the class.
	 * It initializes all the tile parameters.
	 * @param mine	a boolean that indicates if the tile has a mine or not.
	 */	
	public Tile(boolean mine) {
		this.mine = mine;
		this.status = Tile.Status.HIDDEN;
		this.hasCursor = false;
		this.nMines = 0;
	}
	
	/**
	 * A getter for the private parameter that tells if the cursor is selecting the tile.
	 * @return	hasCursor: a boolean that is going to be true if the cursor is selecting or false if not.
	 */
	public boolean hasCursor() {
		return hasCursor;
	}
	
	/**
	 * A setter for the private parameter that tells if the cursor is selecting the tile.
	 * @param hasCursor	a boolean that is going to be true if the cursor is selecting orfalse if not.
	 */
	public void setHasCursor(boolean hasCursor) {
		this.hasCursor = hasCursor;
	}
	
	/**
	 * A getter for the private parameter that tells if the tile has a mine inside.
	 * @return	mine: a boolean that tells if the tile has a mine.
	 */
	public boolean hasMine() {
		return mine;
	}
	
	/**
	 * A getter that tells the actual Status of the tile.
	 * @return	status: an entry of the enumeration "Tile.Status".
	 */
	public Tile.Status getStatus() {
		return status;
	}

	/**
	 * A setter that will set the number of mines that will appear in a non-mined tile if gets opened.
	 * @param nMines	the number of mines the tile has in the nearest neighbours around.
	 */
	public void setnMines(int nMines) {
		if(nMines < 0)
			this.nMines = 0;
		else if(nMines > 8)
			this.nMines = 8;
		else 
			this.nMines = nMines;
	}	
	
	/**
	 * A getter that tells the number of mines that will appear in a non-mined tile if gets opened.
	 * @return	nMines	the number of mines the tile has in the nearest neighbours around.
	 */
	public int getnMines() {
		return this.nMines;
	}
	
	/**
	 * A method that opens the current tile changing its Tile.Status depending on the initialization (with or without mine).
	 * @return	status: the current Tile.Status of the tile.
	 */
	public Tile.Status open() {
		if(this.status.equals(Tile.Status.HIDDEN)) {
			if(this.hasMine())
				this.status = Tile.Status.MINE;
			else 
				this.status = Tile.Status.OPEN;
		}
		return this.status;
	}
	
	/**
	 * A method that allows the player to mark the tile if there can be a probability to have a mine inside.
	 */
	public void mark() {
		//this.status = (this.status.equals(Tile.Status.HIDDEN)) ? Tile.Status.MARK : Tile.Status.HIDDEN;
		if(this.status == Tile.Status.HIDDEN)
			this.status=Tile.Status.MARK;
		else if(this.status == Tile.Status.MARK)
			this.status=Tile.Status.HIDDEN;			
	}
}