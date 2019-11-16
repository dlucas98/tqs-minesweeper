package core;

import java.util.Random;

public class Map implements interfaces.Map {
	private Board board;
	public Map() {
		this.board = new Board(generateEasy());
	}
	
	public Map(Board.Dificulty d) {
		this.board = new Board(generateMap(d));
	}
	
	public Map(int height, int width, int mines) {
		this.board = new Board(generateMap(height, width, mines));
	}
	
	public Tile[][] generateMap(Board.Dificulty d) {
		switch(d) {
			case EASY:
				return generateEasy();
			case MEDIUM:
				return generateMedium();
			case HARD:
				return generateHard();
			default:
				return null;
		}
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public Tile[][] generateMap(int height, int width, int mines) {
		return generateBoard(height, width, mines);
	}
	
	private Tile[][] generateBoard(int h, int w, int mines) {
		Random r = new Random();
		Tile[][] board = new Tile[w][h];
		int pMines = 0;
		while(pMines < mines) {
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					if(!board[i][j].hasMine()) {
						if(r.nextInt(11) == 1) {
							pMines++;
							board[i][j] = new Tile(true);
						}
						else
							board[i][j] = new Tile(false);
					}
				}
			}
		}
		
		return board;
	}
	
	private Tile[][] generateEasy() {
		return generateBoard(Board.DEFAULT_HEIGHT, Board.DEFAULT_WIDTH, Board.DEFAULT_MINES);
	}
	
	private Tile[][] generateMedium() {
		return generateBoard(Board.DEFAULT_INTERMEDIATE_HEIGHT, Board.DEFAULT_INTERMEDIATE_WIDTH, Board.DEFAULT_INTERMEDIATE_MINES);
	}
	
	private Tile[][] generateHard() {
		return generateBoard(Board.DEFAULT_HARD_HEIGHT, Board.DEFAULT_HARD_WIDTH, Board.DEFAULT_HARD_MINES);
	}
}
