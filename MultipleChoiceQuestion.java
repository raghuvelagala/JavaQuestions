import java.util.Scanner;

public class  MultipleChoiceQuestion extends BaseQuestion{

	private String answers[];
	private int correctAnswer;
	private static int totalQuestions;
	
	public MultipleChoiceQuestion(int correctAnswer, String[] answers, String question, String questionTopic)
	{
			super(questionTopic, question);	
			totalQuestions++;
			this.correctAnswer = correctAnswer;
			this.answers = answers;
	}
	
	public String toString()
	{
		String output;
		int number = 1;
		output = "\n\n--------------------------------------------------\nMultiple choice question:\n\n";
		output = output + getQuestion() + "\n\n";
		String[][] options = new String[2][answers.length];
		for (int i=0;i<answers.length;i++)   //Places the options in a 2nd column of the array
		{
			options[1][i] = answers[i] + "\n";
		}
		for (int j=0;j<answers.length;j++)	//Placing the alphabets/numbers in the 1st column of the array
		{
			if (j<26){
				int unicode = 65 + j;
				char alphabet = (char) unicode;
				options[0][j] = alphabet + ": "; }
			else if (j>25){
				options[0][j] = "O" + number + ": ";
				number = number +1;
			}
		}
		for(int k=0;k<answers.length;k++) //Merges the columns and attaches them to output
		{
			output = output + options[0][k] + options[1][k];
		}
		
		output = output + "\n\nPlease make your letter selection below. \nSelection: ";
				
		return output;
	}

	@Override
	public boolean process(Scanner scan) {
		
		String userInput = readAttempt(scan);	// Processes reading userInput,averageTime, attemptsCount
		int ascii;
		boolean result;
		String correctAns;
				
		ascii =(char) userInput.charAt(0) - 65;		//The userInput is now a ascii value and adjusted to compare with Answer[]
		
		correctAns = String.valueOf((char) (65 + correctAnswer));
		result = check(correctAns, userInput);
		setansweredCorrectly(result);
		/*
		if(ascii == correctAnswer){	//comparing the user input and actual answer
			result = true;
			setansweredCorrectly(result);
		} else {
			result = false;
			setansweredCorrectly(result);
		}
		*/
		return getansweredCorrectly();
	}
	
	public static int count(){
		return totalQuestions;
	}
	
	
		
}
