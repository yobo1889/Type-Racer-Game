public class Word implements Comparable<Word>{
	  int priority = 100;
	  int x = 0;
	  int y = 0;
	  String word;
		boolean isAnswered = false;

		/**
		*A constructor for Word which creates a Word object with a specific String
		*@param none
		*@return none
		*/

		public Word(String word){
			this.word = word;
		}
		/**
		*A constructor for Word that Just creates an empty Word object
		*@param none
		*@return none
		*/

		public Word(){
		}

		/**
		*A method that returns the x coordinate of a Word
		*@param none
		*@return x the x-coordinate of a Word Object
		*/

	  public int getX(){
		  return x;
	  }

		/**
		*A method that returns the y coordinate of a Word
		*@param none
		*@return y the y-coordinate of a Word Object
		*/

	  public int getY(){
		  return y;
	  }

		/**
		*A method that changes the x-coordinate of a Word
		*@param x the new vaule of the x-coordinate
		*@return none
		*/

	  public void setX(int x){
		  this.x = x;
	  }

		/**
		*A method that changes the y-coordinate of a Word
		*@param y the new vaule of the y-coordinate
		*@return none
		*/

	  public void setY(int y){
		  this.y = y;
	  }

		/**
		*A method that returns the priority of a Word
		*@param none
		*@return priority the priority of a Word Object
		*/

	  public int getPriority(){
		  return priority;
	  }

		/**
		*A method that changes the priority of a Word
		*@param priority the new vaule of priority
		*@return none
		*/

	  public void setPriority(int priority){
		  this.priority = priority;
	  }

		/**
		*A method that returns the string of a Word
		*@param none
		*@return word the string of a Word Object
		*/

	  public String getWord(){
		  return word;
	  }

		/**
	 *A method that changes the string of a Word
	 *@param word the new vaule of the string
	 *@return none
	 */

	  public void setWord(String word){
		  this.word = word;
	  }

		/**
		*A method that returns whether the Word has already been answered or not
		*@param none
		*@return isAnswered the isAnswered of a Word Object
		*/

		public boolean getIsAnswered(){
		  return isAnswered;
	  }

		/**
		*A method that changes the isAnswered of a Word
		*@param isAnswered the new vaule of the isAnswered
		*@return none
		*/

	  public void setIsAnswered(boolean isAnswered){
		  this.isAnswered = isAnswered;
	  }

		/**
		*An overridn method that returns an int after comparing the priorities of a Word
		*@param word the word to be compared
		*@return int the status of the comparison
		*/

	  @Override
	  public int compareTo(Word word) {
        if(this.getPriority()<word.getPriority()){
            return 1;
		}
		else if(this.getPriority()>word.getPriority()){
			return -1;
		}
		return 0;
      }
}
