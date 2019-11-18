package core;

/**
 * The class Cursor allows the player to move between the different tiles in the board of the game.
 * Makes possible to know the coordinates where the player is allocated.
 */
public class Cursor {
	/**
	 * An enumeration in order to clasify the direction of the movements:
	 * TOP
	 * LEFT
	 * RIGHT
	 * BOTTOM
	 */
	public enum Direction{
		TOP,
		LEFT,
		RIGHT,
		BOTTOM
	}
	private int x;
	private int y;
	private Board board;
	
	/**
	 * The class constructor that creates an instance in the current board. Sets the startin position on 0,0
	 * @param b: the board created to play the current game.
	 */
	public Cursor(Board b) {
		this.x = 0;
		this.y = 0;
		this.board = b;
	}
	
	/**
	 * A getter that returns the position in the X axis.
	 * @return	x: position in X axis.
	 */
	public int getX() {
		return x;
	}

	 /**
	 * A getter that returns the position in the Y axis.
	 * @return	y: position in Y axis.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * A method that allows doing moves around the board taking into account the limits.
	 * @param direction: the Cursor.Direction desired to make the move.
	 */
	public void move(Direction direction) {
		switch (direction) {
		case TOP:
			if(y>0)
				y--;
			break;
			
		case BOTTOM:
			if(y < board.getHeight() - 1)
				y++;
			break;

		case RIGHT:
			if(x < board.getWidth() - 1)
				x++;
			break;
			
		case LEFT:
			if(x > 0)
				x--;
			break;
		}
	}
}
