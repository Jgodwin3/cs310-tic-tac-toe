package edu.jsu.mcis;

public class TicTacToeModel{
    
    private static final int DEFAULT_WIDTH = 3;
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a tie,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("Tie"), 
        NONE("none");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    private Mark[][] grid; /* Game grid */
    private boolean xTurn; /* True if X is current player */
    private int width;     /* Size of game grid */
    
    /* DEFAULT CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        /* No arguments (call main constructor; use default size) */
        
        this(DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create grid (width x width) as a 2D Mark array */

        grid = new Mark[width][width];

        /* Initialize grid by filling every square with empty marks */

        for(int i = 0; i < width; i++){
			for(int j = 0; j<width; j++){
				grid[i][j] = Mark.EMPTY;
			}
		}
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Place the current player's mark in the square at the specified
           location, but only if the location is valid and if the square is
           empty! */
        if (isValidSquare(row, col) == false || isSquareMarked(row, col) == true)
			return false;
		else if(isValidSquare(row, col) == true && isSquareMarked(row, col) == false){
			if(this.xTurn == true){
				grid[row][col] = Mark.X;
				this.xTurn = false;
				return true;
			}
			else{
				grid[row][col] = Mark.O;
				this.xTurn = true;
				return true;
			}
		}
        return true;         
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return true if specified location is within grid bounds */
        
        if( (row < width) && (col < width) && (row >= 0) && (col >= 0) )
			return true;
		else
			return false;
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return true if square at specified location is marked */
        
        if(grid[row][col] != Mark.EMPTY)
			return true;
		else if(grid[row][col] == Mark.EMPTY)
			return false;      
    return false;
	}
	
    public Mark getMark(int row, int col) {
        
        /* Return mark from the square at the specified location */
        if(isValidSquare(row, col) == true)
			return grid[row][col];
		else
			return null;
		

    }
	
    public Result getResult() {
        
        /* Use isMarkWin() to see if X or O is the winner, if the game is a
           tie, or if the game is not over, and return the corresponding Result
           value */
        
        if(isMarkWin(Mark.X) == true)
			return Result.X;
		else if(isMarkWin(Mark.O) == true)
			return Result.O;
		else if(isTie() == true)
			return Result.TIE;
		else
			return Result.NONE;

    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
		int counter = 0;
		
		//check rows
		for(int i = 0; i < width; i++){
			for(int j = 0; j < width; j++){
				if(grid[i][j] == mark){
					++counter;
				}
			}
		
			if(counter == width)
				return true;
			else
				counter = 0;
		}
		if(counter == width)
			return true;
		counter = 0;
		//check col
		for(int i = 0; i < width; i++){
			for(int j = 0; j < width; j++){
				if(grid[j][i] == mark){
					++counter;
				}
			}
			if(counter == width)
				return true;
			else
				counter = 0;
		}
		if(counter == width)
			return true;
		counter = 0;
		//top left diagonal
		for (int i = 0; i < width; i++) {
				if(grid[i][i] == mark){
					++counter;
				}
		}
		if(counter == width)
				return true;
			else
				counter = 0;
		//top right diagonal
		for(int i = 0, j = width -1; i < width && j >= 0; i++, j--){
			if(grid[i][j] == mark){
				++counter;
			}
		}
		if(counter == width)
				return true;
			else
				counter = 0;
	
		return false;
	}
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
		for(int i =0; i<width; i++){
			for(int j = 0; j<width; j++) {
				if(this.getMark(i,j) == Mark.EMPTY)
					return false;
			}
		}
		return true;
    }

    public boolean isGameover(){
        
        /* Return true if the game is over */
        
        if(getResult() != Result.NONE)
			return true;
		else
			return false;
        
    }

    public boolean isXTurn(){
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth(){
        
        /* Getter for width */
        
        return width;
        
    }
    
}