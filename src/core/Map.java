package core;

import java.util.Random;
/**
 * Board factory. Map is used to generate Board instances.
 * 
 */
public class Map implements interfaces.Map {
	private Board board;
	/**
	 * Default constructor.
	 */
	public Map() {
		this.board = new Board(generateEasy());
	}
	/**
	 * Map builds a Board of the specified Board.Difficulty.
	 * You need to use getBoard() to get the generated Board.
	 * @param d Difficulty of the board
	 */
	public Map(Board.Dificulty d) {
		this.board = new Board(generateMap(d));
	}
	
	/**
	 * Map builds a Board of the specified dimensions with the specified mines.
	 * You need to use getBoard() to get the generated Board.Board.
	 * @param height height
	 * @param width width
	 * @param mines mines in the board
	 */
	public Map(int height, int width, int mines) {
		this.board = new Board(generateMap(height, width, mines));
	}
	
	/**
	 * Returns a Board of the specified Board.Difficulty.
	 * You can use this method in any circumstance, but its aim is to be used when Map is built with default constuctor.
	 */
	public Board getBoard(Board.Dificulty d) {
		return new Board(generateMap(d));
	}
	
	/**
	 * Returns a Tile matrix with the dimensions and mines of the target Board.Difficulty
	 */
	public Tile[][] generateMap(Board.Dificulty d) {
		switch(d) {
			case EASY:
				return generateEasy();
			case MEDIUM:
				return generateMedium();
			case HARD:
				return generateHard();
			default:
				return generateEasy();
		}
	}
	
	/**
	 * Returns the generated Board
	 * @return generated Board
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Returns a Tile matrix with the dimensions and mines of the target dimensions and mines
	 */
	public Tile[][] generateMap(int height, int width, int mines) {
		return generateBoard(height, width, mines);
	}
	
	/**
	 * Returns a Tile matrix with the dimensions and mines of the target dimensions and mines
	 * @param h
	 * @param w
	 * @param mines
	 * @return
	 */
	private Tile[][] generateBoard(int h, int w, int mines) {
		Random r = new Random();
		Tile[][] board = new Tile[h][w];
		int pMines = 0;
		boolean end = false;
		
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				board[i][j] = new Tile(false);
		
		while(pMines < mines) {
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(!board[i][j].hasMine()) {
						if(end)
							break;
						if(r.nextInt(11) == 1) {
							pMines++;
							board[i][j] = new Tile(true);
							if(pMines == mines)
								end = true;
						}
					}
				}
			}
		}
		
		return board;
	}
	
	/**
	 * Returns a Board.Difficulty.EASY Tile matrix
	 * @return Board.Difficulty.EASY Tile matrix
	 */
	private Tile[][] generateEasy() {
		return generateBoard(Board.DEFAULT_HEIGHT, Board.DEFAULT_WIDTH, Board.DEFAULT_MINES);
	}
	
	/**
	 * Returns a Board.Difficulty.INTERMEDIATE Tile matrix
	 * @return Board.Difficulty.INTERMEDIATE Tile matrix
	 */
	private Tile[][] generateMedium() {
		return generateBoard(Board.DEFAULT_INTERMEDIATE_HEIGHT, Board.DEFAULT_INTERMEDIATE_WIDTH, Board.DEFAULT_INTERMEDIATE_MINES);
	}
	
	/**
	 * Returns a Board.Difficulty.HARD Tile matrix
	 * @return Board.Difficulty.HARD Tile matrix
	 */
	private Tile[][] generateHard() {
		return generateBoard(Board.DEFAULT_HARD_HEIGHT, Board.DEFAULT_HARD_WIDTH, Board.DEFAULT_HARD_MINES);
	}
}