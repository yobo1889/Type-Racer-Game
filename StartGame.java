import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class StartGame{


	JFrame mainMenu;
	JTextArea Greetings;
	JButton Level1;
	JButton Level2;
	JButton Level3;
	Font f;


	  /**
		*This is the constructor for our starter of our Game which calls a methods start()
		*@param none
		*@return none
		*/

    public StartGame(){
      start();
    }

		/**
		*This is a method which is called by the constructor of this class
		*It creates the main frame for our GUI and adds contents like buttons and Greeting Messages
		*This method is responsible for the main menu GUI of the Game
		*@param none
		*@return none
		*/

	  public void start(){

	    mainMenu = new JFrame("\s".repeat(73) + "Type Fast");
		mainMenu.setSize(600,600);

		Level1 = new JButton("Level 1");
		Level2 = new JButton("Level 2");
		Level3 = new JButton("Level 3");

		Level1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					chooseLevel(1);

			}
		});
		Level2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					chooseLevel(2);
			}
		});
		Level3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				chooseLevel(3);
			}
		});

		f = new Font("Segoe Script", Font.BOLD, 30);
		Greetings = new JTextArea();
		Greetings.setFont(f);
		Greetings.setForeground(Color.BLUE);
		Greetings.setText("\n\n\n\n        Type Fast");

		mainMenu.getContentPane().add(Greetings);
		mainMenu.getContentPane().add(Level1);
		mainMenu.getContentPane().add(Level2);
		mainMenu.getContentPane().add(Level3);
		mainMenu.getContentPane().add(BorderLayout.EAST, Level1);
		mainMenu.getContentPane().add(BorderLayout.WEST, Level2);
		mainMenu.getContentPane().add(BorderLayout.SOUTH, Level3);
		mainMenu.getContentPane().add(BorderLayout.CENTER, Greetings);

		mainMenu.setVisible(true);

    }

	/**
	*This method accepts a parameter and initializes one of the two subclasses based on the parameter
	*If the user press the Level 1 button then The Level 1 object is initialized.
	*If the user press the Level 2 button then The Level 2 object is initialized.
	*@param level an integer indicating the whether the user pressed Level 1 or Level 2
	*@return none
	*/

	public void chooseLevel(int level){
		if(level==1){
		 Level1 level1 = new Level1();
		}
		else if(level==2){
         Level2 level2 = new Level2();
		}
		else{
		 Level3 level3 = new Level3();
		}
	}

	/**
	*The main method which starts the Game by initializing the StartGame object
	*@param args none
	*@return none
	*/
	public static void main (String[] args){
		StartGame sg = new StartGame();
	}
}
