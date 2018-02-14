package edu.jsu.mcis;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeView extends JPanel implements ActionListener{

    private TicTacToeModel model;
	private JPanel squaresPanel;
	private JButton[][] squares;
	private JLabel resultLabel;
    
    /* CONSTRUCTOR */
	
    public TicTacToeView(TicTacToeModel model) {
        
        this.model = model;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		squaresPanel = new JPanel(new GridLayout(model.getWidth(), model.getWidth()));

		squares = new JButton[model.getWidth()][model.getWidth()];
		
		for(int i = 0; i < model.getWidth(); i++){
			for(int j = 0; j < model.getWidth(); j++){
				squares[i][j] = new JButton();
				squares[i][j].setPreferredSize(new Dimension(64, 64));
				squares[i][j].addActionListener(this);
				squares[i][j].setName("Square" + i + j);
				squaresPanel.add(squares[i][j]);
			}
		}
		
		resultLabel = new JLabel();
		resultLabel.setName("ResultLabel");
		resultLabel.setText("\n\nPlayer 1 (X) Move"); //X goes first
		add(squaresPanel);
		add(resultLabel);
    }
	
	public void actionPerformed(ActionEvent a){
		for(int i = 0; i < model.getWidth(); i++){
			for(int j = 0; j < model.getWidth(); j++){
				if(a.getSource() == squares[i][j] && model.makeMark(i, j))
					squares[i][j].setText(model.getMark(i, j).toString());
			}
		}
		if(model.isXTurn() == true){
			resultLabel.setText("\n\nPlayer 1 (X) Move");
		}
		if(model.isXTurn() == false){
			resultLabel.setText("\n\nPlayer 2 (O) Move");
		}
		if(model.getResult() == TicTacToeModel.Result.X){
			resultLabel.setText("X");
		}
		if(model.getResult() == TicTacToeModel.Result.O){
			resultLabel.setText("O");
		}
		if(model.getResult() == TicTacToeModel.Result.TIE){
			resultLabel.setText("TIE");
		}
	}
}