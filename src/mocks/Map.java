package mocks;

import core.Board;
import core.Tile;

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
				return generateEasy();
		}
	}
	
	public Board getBoard(Board.Dificulty d) {
		return new Board(generateMap(d));
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public Tile[][] generateMap(int height, int width, int mines) {
		return generateCustom(height, width, mines);
	}
	
	private Tile[][] generateCustom(int h, int w, int mines) {
		//return generateM();
		
		if(h < 1 || w < 1)
			return generateEasy();
		
		mines = Math.min((h * w) - 1, mines);
		Tile[][] board = new Tile[h][w];
		int cnt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(cnt < mines) {
					board[i][j] = new Tile(true);
					cnt++;
				} else {
					board[i][j] = new Tile(false);
				}
			}
		}
		return board;
	}
	
	private Tile[][] generateEasy() {
		int[][] matrix = {
			{0,0,0,0,0,0,1,1,1},
			{0,0,0,0,0,0,1,0,1},
			{0,0,0,0,0,0,1,1,1},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0},
			{0,0,1,1,0,0,0,0,0},
			{0,0,1,1,0,0,0,0,0}};
		
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
	
	private Tile[][] generateM() {
		int[][] matrix = {
			{0,1,0},
			{0,1,0}};
		
		Tile[][] board = new Tile[2][3];
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				if(matrix[i][j] == 1)
					board[i][j] = new Tile(true);
				else
					board[i][j] = new Tile(false);
			}
		}
		return board;
	}
	
	private Tile[][] generateMedium() {
		int[][] matrix = {
				{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0},
				{1,0,0,0,1,1,1,0,1,0,0,0,0,0,0,0},
				{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0},
				{0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1}};
			
			Tile[][] board = new Tile[16][16];
			
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 16; j++) {
					if(matrix[i][j] == 1)
						board[i][j] = new Tile(true);
					else
						board[i][j] = new Tile(false);
				}
			}
			return board;
	}
	
	private Tile[][] generateHard() {
		int[][] matrix = {
				{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,1,0,1},
				{0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{0,0,0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0},
				{1,0,0,0,1,1,1,0,1,0,0,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0},
				{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0},
				{0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,1,0,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,0},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1},
				{0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0,1,0,0,1,0,1,0,0,0,0,0,1,0,1},
				{0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1,1,0,1,0,0,0,1,0,0,0,0,0,1,1}};
			
			Tile[][] board = new Tile[16][30];
			
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 30; j++) {
					if(matrix[i][j] == 1)
						board[i][j] = new Tile(true);
					else
						board[i][j] = new Tile(false);
				}
			}
			return board;
	}
}
