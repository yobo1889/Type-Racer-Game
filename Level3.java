import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;


public class Level3 extends TypeFast{


	JPanel panel;
	MoveWord mw;
	Word word1;
	Word word2;
	Word word3;
	Word word4;
	Word word5;
	ArrayList<Word> words;
	PriorityQueue<Word> wordQueue;
	Thread thread;
   

	/**
  *A constructor for Level2(subclass) which setsup a timer for the game play
	*It also calls all the essential method to create a new level
  *@param none
  *@return none
  */
	public Level3(){

		timeOver=new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			  lost();
			}
		};
		timer=new Timer(22000,timeOver);

		initiate();
		setUp();

	}


	/**
	*A method which overrides another method from the superclass.
	*It creates a customized GUI for Level 2 of the Game and calls other necessary methods for the
	*functionality of this level.
	*@param none
	*@return none
	*/

	public void initiate(){

		main.getContentPane().removeAll();
		main.repaint();

		word1 = new Word();
		word2 = new Word();
		word3 = new Word();
		word4 = new Word();
		word5 = new Word();
        Word[] wordVar = {word1,word2,word3,word4,word5};

		words = loadWordList();
		wordQueue = new PriorityQueue<>();

		random = new Random();

		for(int i = 0;i<5;i++){
			wordVar[i].setWord(words.get(random.nextInt(words.size())).getWord());
			wordQueue.add(wordVar[i]);
		}

		scoreBoard = new JTextArea();
		main.getContentPane().add(scoreBoard);
		main.getContentPane().add(BorderLayout.EAST, scoreBoard);

		panel = new JPanel(new BorderLayout());
		main.add(panel);


		main.setVisible(true);

		startWordMovement();

		startTimer();

	}

	/**
	*A class that creates a runnable thread
	*@param none
	*@return none
	*/

	class MoveWord implements Runnable{

		/**
		*A method which is called every time a thread is started
		*It changes the x and y positon of the Word object randomly so it can be displayed in a scattered pattern in the GUI
		*It also changes the priority of the Word object so that a Word that is answered correctly is not displayed in the GUI
		*@param none
		*@return none
		*/

		public void run(){

             int c = 0;
             //Word wordDisp = words.get(random.nextInt(words.size());
			 while(flag){

				    wordDisp.setX(50);
					wordDisp.setY(c);
                    c = c + 20;
					Graphics g = panel.getGraphics();
					Word tempWord = customRepaint(g);

					if(hashingList.get(tempWord.getWord()).getIsAnswered()){

					 tempWord.setPriority(tempWord.getPriority()-99);

				  }
				  else{

					 tempWord.setPriority(tempWord.getPriority()-1);

				  }
					wordQueue.add(tempWord);


			}
		}
	}

	/**
	*A method which creates a thread that displays Word in a scattered pattern in the GUI
	*@param none
	*@return none
	*/

	public void startWordMovement(){

		mw = new MoveWord();
        thread = new Thread(mw);
        thread.start();

	}

	/**
  *A method which updates the GUI by painting(displaying) words according to their priority and x-y position
  *@param g a varibable represeting the GUI to be updated
  *@return Word returns the Word with the highest priority that has been painted(displayed) in the GUI
  */

  public Word customRepaint(Graphics g){

			super.paintComponent(g);
			Word word = wordQueue.poll();
            Graphics2D draw=(Graphics2D)g;
            draw.setColor(Color.BLUE);
            draw.setFont(f);
			draw.drawString(word.getWord(),word.getX(),word.getY());
			try{
			    Thread.currentThread().sleep(random.nextInt(1500));
			}
			catch(Exception e){}
			return word;

  }

	/**
	*A method which checks whether the users has answered all the words
	*@param none
	*@return none
	*/

	public void checkWin(){
		if(getScore()==5){
		   	main.getContentPane().add(BorderLayout.WEST, scoreBoard);
				scoreBoard.setText("\n\n\n\n\n\n\nYou Won");
				inActivate();
				flag = false;
				thread.interrupt();
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

		scoreBoard.setText("You Lost");
	  inActivate();
		flag = false;
		thread.interrupt();
	}

}
