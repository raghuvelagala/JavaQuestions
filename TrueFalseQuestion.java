import java.util.Scanner;


public class TrueFalseQuestion extends BaseQuestion{
	
	private boolean correctAnswer;
	private static int totalQuestions;
	
	
	public TrueFalseQuestion(boolean correctAnswer, String question, String questionTopic){
		super(questionTopic, question);
		totalQuestions++;
		this.correctAnswer = correctAnswer;
	}
	
	public String toString()
	{
		String output;
		output = "\n\n--------------------------------------------------\nTrue/False question:\n\n";
		output = output + getQuestion() +"\n\n";
		output = output + "Please make your selection below. \nSelection: ";
	
		return output;
	}

	@Override
	public boolean process(Scanner scan) {
		String userInput = readAttempt(scan);	// Processes reading userInput,averageTime, attemptsCount
		boolean result;
		String correctAns;
		
		if(correctAnswer == true){
			correctAns = "true";
		}
		else{
			correctAns = "false";
		}
		
		result = check(correctAns, userInput);
		setansweredCorrectly(result);
		
		/*
		if (correctAnswer == true  &&  userInput.equals("True")){	//Checks whether the userInput is the correctAnswer
			result = true;
			setansweredCorrectly(result);
		}
		else if (correctAnswer == false  && userInput.equals("False")){
			result = true;
			setansweredCorrectly(result);
		}else {
			result = false;
			setansweredCorrectly(result);
		}*/
		return getansweredCorrectly(); // return whether the answered matched correctly
	}
	
	public static int count(){
		return totalQuestions;
	}
	
	
}
