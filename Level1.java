import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class Level1 extends TypeFast{

	JTextArea word1;

	/**
  *A constructor for Level1(subclass) which setsup a timer for the game play
	*It also calls all the essential method to create a new level
  *@param none
  *@return none
  */

	public Level1(){
		timeOver=new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				lost();
			}
		};
		timer=new Timer(21000,timeOver);
		initiate();
		setUp();

	}

	/**
	*A method which overrides another method from the superclass.
	*It creates a customized GUI for Level 1 of the Game and calls other necessary methods for the
	*functionality of this level.
	*@param none
	*@return none
	*/

	public void initiate(){

		main.getContentPane().removeAll();
    main.repaint();

		word1 = new JTextArea();
		word1.setText("");
		words = loadWordList();
		random = new Random();
		word1.append("\n\n");
		for(int i = 0;i<5;i++){
		 word1.append("\n" + "\s".repeat(15) + words.get(random.nextInt(words.size())).getWord());
		}
		f = new Font("Segoe Script", Font.BOLD, 20);
    word1.setFont(f);
		word1.setForeground(Color.BLUE);
		word1.setEditable(false);

    scoreBoard = new JTextArea();
		main.getContentPane().add(scoreBoard);
		main.getContentPane().add(BorderLayout.EAST, scoreBoard);

		main.getContentPane().add(word1);
		main.getContentPane().add(BorderLayout.CENTER, word1);

		main.setVisible(true);

		startTimer();


	}

	/**
	*A method which checks whether the users has answered all the words
	*@param none
	*@return none
	*/

	public void checkWin(){
		if(getScore()==5){
			main.getContentPane().add(BorderLayout.WEST, scoreBoard);
				scoreBoard.setText("");
				word1.setText("\n\n\n\n\n\n\n\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\sYou Won");
				inActivate();
		}
	}


	/**
	*A method which is called whenever time runs out for the player(lost the game)
	*It automatically stop the gameplay by calling the inActivate() method form the super class
	*It displays a "you lost" message and gives the user an option to Retry
	*@param none
	*@return none
	*/

	public void lost(){
		main.getContentPane().add(BorderLayout.EAST, scoreBoard);
	  scoreBoard.setText("");
		word1.setText("You Lost");
		inActivate();
	}


}
