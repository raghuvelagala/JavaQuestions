import java.util.Scanner;


public class ShortAnswerQuestion extends BaseQuestion{
	
	private String correctAnswer;
	private static int totalQuestions;
	
	public ShortAnswerQuestion(String correctAnswer, String question, String questionTopic)
	{
		super(questionTopic, question);
		totalQuestions++;
		this.correctAnswer = correctAnswer;
	}
	
	public String toString()
	{
		String output;
		output = "\n\n--------------------------------------------------\nShort answer question:\n\n";
		output = output + getQuestion() + "\n\n" + "Please type your answer here:";
		
		return output;
	}

	@Override
	public boolean process(Scanner scan) {
		String userInput = readAttempt(scan);	// Processes reading userInput,averageTime, attemptsCount
		boolean result;
		
		result = check(correctAnswer, userInput);
		setansweredCorrectly(result);
		/*		
		if(userInput.equals(correctAnswer)){	//Checks whether the unerInput and correctAnswer matches
			result = true;
			setansweredCorrectly(result);
		} else {
			result = false;
			setansweredCorrectly(result);
		}*/
		
		return getansweredCorrectly();	// returns whether the user inputed the correctAnswer
	}
	
	public static int count(){
		return totalQuestions;
	}

}
