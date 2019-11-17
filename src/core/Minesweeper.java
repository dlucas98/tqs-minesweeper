package core;

import java.time.chrono.MinguoEra;

import core.Cursor.Direction;

public class Minesweeper {
	public enum MenuStatus {
		MAIN_MENU,
		CHOOSE_DIFFICULTY,
		BOARD,
		HIGH_SCORES
	}
	private Board board;
	private HighScores highScores;
	private interfaces.Input input;
	private Output output;
	private MenuStatus status;

	public Minesweeper() {
		this.highScores = new HighScores();
		this.input = new Input();
		this.output = new Output();
	}
	
	public Minesweeper(interfaces.Input input) {
		this.input = input;

		this.highScores = new HighScores();
		this.output = new Output();
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public HighScores getHighScores() {
		return this.highScores;
	}
	
	public interfaces.Input getInput() {
		return this.input;
	}
	
	public Output getOutput() {
		return this.output;
	}
	
	public MenuStatus getActualMenu() {
		return this.status;
	}
	
	private void printMenu() {
		
	}
	
	private void printDifficultyMenu() {
		
	}
	
	private void printHighScores() {
		highScores.printScores();
	}
	
	private void printBoard() {
		board.print();
	}
	
	public void main() {
		while(true) {
			char c = getInput().readKey();
			switch(c) {
			case '1':
				if(getActualMenu() == MenuStatus.MAIN_MENU)
					status = MenuStatus.CHOOSE_DIFFICULTY;
				else if(getActualMenu() == MenuStatus.CHOOSE_DIFFICULTY) {
					status = MenuStatus.BOARD;
					Map m = new Map(Board.Dificulty.EASY);
					this.board = m.getBoard();
				}
				break;
			case '2':
				if(getActualMenu() == MenuStatus.MAIN_MENU)
					status = MenuStatus.HIGH_SCORES;
				else if(getActualMenu() == MenuStatus.CHOOSE_DIFFICULTY) {
					status = MenuStatus.BOARD;
					Map m = new Map(Board.Dificulty.MEDIUM);
					this.board = m.getBoard();
				}
				break;
			case '3':
				if(getActualMenu() == MenuStatus.CHOOSE_DIFFICULTY) {
					status = MenuStatus.BOARD;
					Map m = new Map(Board.Dificulty.HARD);
					this.board = m.getBoard();
				}
				break;
			case '0':
				if(getActualMenu() == MenuStatus.MAIN_MENU) {
					//Exit
				} else if(getActualMenu() == MenuStatus.CHOOSE_DIFFICULTY || getActualMenu() == MenuStatus.HIGH_SCORES)
					status = MenuStatus.MAIN_MENU;
				else if(getActualMenu() == MenuStatus.BOARD)
					status = MenuStatus.MAIN_MENU;
				break;
			case 'w':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().getCursor().move(Direction.TOP);
				break;
			case 'a':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().getCursor().move(Direction.LEFT);
				break;
			case 's':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().getCursor().move(Direction.BOTTOM);
				break;
			case 'd':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().getCursor().move(Direction.RIGHT);
				break;
			case 'o':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().getTile().open();
				break;
			case 'm':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().getTile().mark();
				break;
			}
			
			switch(getActualMenu()) {
			case MAIN_MENU:
				printMenu();
				break;
			case HIGH_SCORES:
				printHighScores();
				break;
			case CHOOSE_DIFFICULTY:
				printDifficultyMenu();
				break;
			case BOARD:
				printBoard();
				break;
			}
		}
	}
}