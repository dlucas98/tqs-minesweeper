package core;

public class Cursor {
	public enum Direction{
		TOP,
		LEFT,
		RIGHT,
		BOTTOM
	}
	private int x;
	private int y;
	private Board board;
	
	public Cursor(Board b) {
		this.x = 0;
		this.y = 0;
		this.board = b;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
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
