/*	
 * Raghurama Reddy Velagala
 * Saud Almashhadi
 */

import java.util.Scanner;

public abstract class BaseQuestion implements Node, Comparable{
	
	private String question;
	private int attemptsCount;
	private float averageTime;
	private float percentCorrect;
	private short questionDifficulty;
	private String questionTopic;
	private boolean answeredCorrectly;
	private Node leftNode;
	private Node rightNode;
	private Node parentNode;
	
	public BaseQuestion(String qt, String q){
		this.questionTopic = qt;
		this.question = q;
		
	}
	
	public String readAttempt(Scanner scan){
		String userInput;
		float timeTaken;
		
		long timeStart = System.currentTimeMillis();	//Counting time in ms starts
		userInput = scan.nextLine();
		long timeEnd = System.currentTimeMillis(); //Counting time ends
		timeTaken = (float) timeEnd - (float) timeStart; //time taken is calculated
		
		if(averageTime == 0){
			averageTime = timeTaken;
		}else{
			averageTime = ((averageTime*attemptsCount) + timeTaken)/(attemptsCount + 1);
		}
		
		attemptsCount++;	//updating attemptsCount
		return userInput;	// returns userInput
	}
	
	public abstract boolean process(Scanner input);
	
	public float percentCorrect(boolean lastAttempt){
		float correct = (attemptsCount - 1)* percentCorrect;
		
		if (lastAttempt){
			percentCorrect = ((correct + 1)/ attemptsCount)*100;
		}else{
			percentCorrect = ((correct)/ attemptsCount)*100;		
		}
		
		return percentCorrect;
	}
	
	public short difficulty(){
		/*float x;
		int d;
				
		x = 100 - percentCorrect;
		d = (int) x;
		questionDifficulty = (short) d; */
		return questionDifficulty;
	}
	
	public Node getLeft(){		//return left node. returns null automatically if leftNode doesn't exist because of constructor.
		return leftNode;
	}
	
	public Node getRight(){		//return right node. returns null automatically if rightNode doesn't exist because of constructor.
		return rightNode;
	}
	
	public Integer getValue(){		//returns an Integer to compare the difficulty of the current BaseQuestion object
		Integer difficulty = new Integer(questionDifficulty);
		return difficulty;
	}
	
	public void setLeft(Node node){		//sets leftNode
		leftNode = node;
	}
	
	public void setRight(Node node){	// sets rightNode
		rightNode = node;
	}
	
	public int compareTo(Object o){
		int result;
		try{		 	
			if (this.getValue().intValue() == (((Node) o).getValue().intValue())){		// if current question is equal to Node o, return 0
				result = 0;
			}
			else if (this.getValue().intValue() > ((Node) o).getValue().intValue()){	// if current question is greater than Node o, return 1
				result = 1;
			}
			else if (this.getValue().intValue() < ((Node) o).getValue().intValue()){	// if current question is less than Node o, return -1
				result = -1;
			}
			else {		// if all the previous conditions fail(not comparable), current object is greater
				result = 1;
			}
			
		}
		catch (Exception e){
			result = 1;		// if the two objects can't be compared, current object is greater
		}
		return result;	// return result, it can only be {1, -1, or 0} 
	}
	
	public static boolean check(String correctstr, String str){
		boolean result;
		
		if (correctstr.replaceAll("\\s", "").equals(str.replaceAll("\\s", ""))){
			result = true;
		}
		else if((correctstr.replaceAll("\\s", "").toLowerCase().equals("true") || correctstr.replaceAll("\\s", "").toLowerCase().equals("t"))	&& (str.replaceAll("\\s", "").toLowerCase().equals("true") || str.replaceAll("\\s", "").toLowerCase().equals("t"))){
			result = true;
		}
		else if ((correctstr.replaceAll("\\s", "").toLowerCase().equals("false") || correctstr.replaceAll("\\s", "").toLowerCase().equals("f")) && (str.replaceAll("\\s", "").toLowerCase().equals("false") || str.replaceAll("\\s", "").toLowerCase().equals("f"))){
			result = true;
		}
		else if ((correctstr.trim().length() == 1 && (str.trim().length() == 1)) && (correctstr.trim().toLowerCase().equals(str.trim().toLowerCase()))){
			result = true;
		}
		else {
			result = false;
		}
		
		return result;
	}
	
	// getters and setters
	public Node getParent(){
		return parentNode;
	}
	
	public void setParent(Node node){
		parentNode = node;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public int getattemptsCount(){
		return attemptsCount;
	}
	
	public boolean getansweredCorrectly(){
		return answeredCorrectly;
	}
	
	public void setansweredCorrectly(boolean arg){
		answeredCorrectly = arg;
	}
	
	public void setDifficulty (short arg){
		this.questionDifficulty = arg;
	}

}
