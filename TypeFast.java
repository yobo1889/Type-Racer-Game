import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;




public class TypeFast extends JComponent{


	JFrame main;
	JTextField wordInput;
	JButton check;
	JButton reset;
	JPanel panel2;
	JTextArea timerBoard;
	JTextArea scoreBoard;

	int score = 0;
	JPanel panel;
	JLabel label;
	Font f;


	boolean flag = true;
	HashMap<String, Word> wordList;
	HashMap<String,Word> hashingList;
	ArrayList<Word> words;

	EnterKeyListener enterListener;
	boolean enterKeyFlag = true;

	Random random;

	Timer timer;
	Timer timer2;
	ActionListener countDown;
	int timeRemaining;
	ActionListener timeOver;


	  /**
		*This is the constructor for our superclass(TypeFast) which calls a methods start()
		*@param none
		*@return none
		*/

    public TypeFast(){
      start();
    }

		/**
		*This is a method which is called by the constructor of this class
		*It creates the main GUI frame for the individual levels
		*This method is responsible for the layout of the individual levels
		*@param none
		*@return none
		*/

	  public void start(){

		main = new JFrame("                  Type Fast");
		main.setSize(600,600);
		f = new Font("Segoe Script", Font.BOLD, 30);

	}

	/**
	*This is a method which is called by the constructor of this class
	*It creates the main frame for our GUI and adds contents like buttons and text fields
	*This method is responsible for the main menu GUI of the Game
	*@param none
	*@return none
	*/

	public void setUp(){
		  panel = new JPanel();
			main.getContentPane().add(BorderLayout.SOUTH, panel);

		    label = new JLabel("Type the Words :) ");
			panel.add(label);

			wordInput = new JTextField(15);
			panel.add(wordInput);
			enterListener = new EnterKeyListener();
			wordInput.addKeyListener(enterListener);

			scoreBoard.setText("Score: \n" + getScore() + "/5");
			scoreBoard.setPreferredSize(new Dimension(100, 50));
			scoreBoard.setMaximumSize(scoreBoard.getPreferredSize());
			f = new Font("Segoe Script", Font.BOLD, 20);
			scoreBoard.setFont(f);
			scoreBoard.setForeground(Color.RED);
			scoreBoard.setEditable(false);

			check = new JButton("Check");
			panel.add(check);
			check.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					checkIfCorrect(wordInput.getText(),hashingList);
					wordInput.setText("");
					updateScore();
					checkWin();
				}
		  });

			reset = new JButton("Restart");
			panel.add(reset);
			reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 restart();
			 }
			});

			panel2 = new JPanel();
			panel2.setPreferredSize(new Dimension(110, 50));
			panel2.setMaximumSize(panel2.getPreferredSize());
			panel2.setBackground(Color.white);
			main.getContentPane().add(BorderLayout.WEST, panel2);


			timerBoard = new JTextArea();
			timerBoard.setEditable(false);
			timerBoard.setText("Time Left\n20 sec");
			timerBoard.setFont(f);
			timerBoard.setForeground(Color.BLACK);
			panel2.add(timerBoard);

			hashingList = mapWordListAsHash();
	}

  /**
	*A method which is overridden by the two levels(sub classes)
	*@param none
	*@return none
	*/
	public void initiate(){
	}

	/**
	*This a class that contains an overriden method keyPressed() which is triggered when the user presses 'enter' during game play
	*@param none
	*@return none
	*/
	class EnterKeyListener extends KeyAdapter {

		/**
		*This a method which overrides a keyPressed() in KeyAdapter which is triggered when the user presses 'enter' during game play
		*It is the same as clicking the check button to verify your spelling
		*@param none
		*@return none
		*/

    @Override
    public void keyPressed(KeyEvent e) {

	     char c = e.getKeyChar();
	     if (e.getKeyCode() == KeyEvent.VK_ENTER && enterKeyFlag) {
				checkIfCorrect(wordInput.getText(),hashingList);
 				updateScore();
 				checkWin();

				wordInput.setText("");
	     }
    }
  }

	/**
  *This a method which copies all the strings in a file to a variable
  *@param filepath the path of the file containing all the words that are to be displayed in the game
  *@return String a string variable which contains all the strings in a file
  */

	public String parseWordList(String filepath){
		 String wordList = "";
		 String nextWord;

		 try {

		  File wordListFile = new File(filepath);
		  Scanner scanWordList = new Scanner(wordListFile);
		  while (scanWordList.hasNextLine()) {

			 nextWord = scanWordList.nextLine();

			 if(nextWord.length()>1){

			  wordList = wordList + "," + nextWord;

			 }

		  }
      scanWordList.close();

		} catch (FileNotFoundException e) {

		  System.out.println("File Not Valid");

		}

		return wordList;
	}

	/**
	*This a method that splits the strings from the above method and return it as an ArrayList after adding it
	*This are the individual words that are to be displayed in the Game
	*@param none
	*@return ArrayList<Word> a list containing all the words from a file wrapped around Word object
	*/

	public  ArrayList<Word> loadWordList(){
    String[] wordData = parseWordList("WordList.txt").split(",");
	  ArrayList<Word> wordList = new ArrayList<>();

	  for(int i = 0;i<wordData.length;i++){
			if(wordData[i].matches("[a-zA-Z]+")){
		  	Word newWord = new Word(wordData[i]);
				wordList.add(newWord);
			}
	  }

	  return wordList;

	}

	/**
	*This a method that adds each word from the above method into a HashMap with the string of the word as key and the Word object as the value
	*This allows the program to easily get the 'Word' object using just the String version of it.
	*@param none
	*@return HashMap<String,Word> a HaspMap containing a word's 'String' and 'Word' version
	*/

	public HashMap<String,Word> mapWordListAsHash(){
		wordList = new HashMap<String, Word>();
		ArrayList<Word> wordListA = loadWordList();
		for(int i = 0;i<wordListA.size();i++){
			wordList.put(wordListA.get(i).getWord(),wordListA.get(i));
		}
		return wordList;
	}

	/**
	*A method which checks if a given String is in the above HashMap which in the game's
	*context means that a user has typed in a correct spelling of a word.
	*@param text the spelling entered by the user(player)
	*@param hash the HashMap containing all the words that are displayed during the GamePlay
	*@return boolean true if the user entered a correct word and false other wise
	*/

	public boolean checkIfCorrect(String text, HashMap<String, Word> hash){
		if(hash.get(text)!=null && hash.get(text).getWord() != "" && !hash.get(text).getIsAnswered()){
			hash.get(text).setIsAnswered(true);
			incrementScore();
			return true;
		}
		else{
			return false;
		}
	}

	/**
	*A method which increases the score of a player once they enter a correct word as verified by the above method
	*@param none
	*@return none
	*/

	public  void incrementScore(){
		score = score + 1;
	}

	/**
	*A method which returns the current score of a player by reading it from an instance varible
	*@param none
	*@return score the current score of the player
	*/

	public  int getScore(){
		return score;
	}

	/**
	*A method which updates the score board based on the current score of the player
	*@param none
	*@return none
	*/

	public void updateScore(){
	  scoreBoard.setText("Score: \n" + getScore() + "/5");
	}

	/**
	*A method which is overridden by the two levels(sub classes)
	*@param none
	*@return none
	*/

	public void checkWin(){
	}

	/**
	*A method which is called whenever the user clicks the Restart Button in both Levels(subclasses).
	*It restarts the level again by resetting the essential variables like the score and also by calling methods which create a new level.
	*@param none
	*@return none
	*/

	public void restart() {
		score = 0;
		updateScore();
		flag = true;
		timer.stop();
		timer2.stop();
		initiate();
		setUp();
	}

	/**
	*A method which is called whenever the user clicks the retry button after losing or winning a level(game)
	*It resets essential variables and call methods which start a new level
	*@param none
	*@return none
	*/

	public void retry(){
		check.setEnabled(true);
		enterKeyFlag = true;
		flag = true;
		reset.setText("Restart");
		initiate();
		timer.restart();
		timer2.restart();
		setUp();
	}

	/**
	*This method that starts a count down timer and update the time board in the GUI every one second
	*@param none
	*@return none
	*/

	public void countDownTimer(){
		timeRemaining = 20;
		countDown=new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(timeRemaining>0){
	        timerBoard.setText("Time Left\n" + timeRemaining + " sec");
					timeRemaining--;
		  	}
			}
		};
		timer2=new Timer(1000,countDown);
		timer2.start();
	}


	/**
	*A method which is called after losing or winning a game
	*It stops the gameplay after losing or winning a game
	*It also creates a retry button in the GUI to replay a level
	*@param none
	*@return none
	*/

	public void inActivate(){
		score = 0;
		timer.stop();
		timer2.stop();
		main.remove(panel2);
		enterKeyFlag = false;
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			  retry();
			}
		 });
		 reset.setText("Retry");
		 check.setEnabled(false);
	}

	/**
	*A method which starts the count down timer, which updates the clock on the GUI and the main timer, which ends the game play when time runs out
	*@param none
	*@return none
	*/

	public void startTimer(){
		timer.start();
		countDownTimer();
	}

}
