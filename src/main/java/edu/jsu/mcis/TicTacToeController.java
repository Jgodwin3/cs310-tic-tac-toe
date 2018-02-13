package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToeController {

    private TicTacToeModel model;
    private TicTacToeView view;
    private Scanner keyboard;
    
    /* CONSTRUCTOR */

    public TicTacToeController(TicTacToeModel model, TicTacToeView view) {
        
        /* Initialize model and view */

        this.model = model;
        this.view = view;
        
        /* Initialize scanner (for console keyboard) */
        
        keyboard = new Scanner(System.in);

    }

    public void controlModel() {
        
        /* Prompt player for next move using view's showNextMovePrompt() */
        
        view.showNextMovePrompt();
        
        /* Receive and validate input, which should be read at the keyboard as
           two integers, the row and the column (for example, "1 1" for the
           center square of a 3 x 3 grid).  Make mark if input is valid, or show
           error message using view's showInputError() if input is invalid. */
        
        String command = keyboard.nextLine();
		String arr[] = command.split(" ");
		if(Integer.parseInt(arr[0]) > model.getWidth() || Integer.parseInt(arr[1]) > model.getWidth() || model.makeMark(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])) == false)
			view.showInputError();
		else
				model.makeMark(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        
    }

}