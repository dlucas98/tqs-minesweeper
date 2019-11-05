package mocks;

import core.Board;
import core.Tile;

public class Map implements interfaces.Map {
	public Map() {
		
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
	
	public Tile[][] generateMap(int height, int width, int mines) {
		
	}
	
	private Tile[][] generateEasy() {
		int[][] matrix = {
			{0,1,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,0,0},
			{0,0,0,0,1,0,1,0,0},
			{1,0,0,0,1,1,1,0,1},
			{1,1,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0},
			{0,0,0,1,0,0,0,0,1},
			{0,0,0,1,0,0,0,1,0}};
		
		Tile[][] board = new Tile[9][9];
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(matrix[i][j] == 1)
					board[i][j] = new Tile(true);
				else
					board[i][j] = new Tile(false);
			}
		}
		return board;
	}
	
	private Tile[][] generateMedium() {
		
	}
	
	private Tile[][] generateHard() {
		
	}
}
