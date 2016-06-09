import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;


public class CodingQuestion extends BaseQuestion{
	
	public String correctAnswer;
	public String cquestion;
	public boolean gradingIndicator;
	public String sampleInput;
	public String sampleOutput;
	public static int totalQuestions;
	
	
	public CodingQuestion(String correctAnswer, String question, String questionTopic, boolean gradingIndicator, String sampleInput, String sampleOutput)
	{
		super(questionTopic, question);
		totalQuestions++;
		this.correctAnswer = correctAnswer;
		this.gradingIndicator = gradingIndicator;
		this.sampleInput = sampleInput;
		this.sampleOutput = sampleOutput;
	}
	
	public String toString()
	{
		String output;
		output = "\n\n--------------------------------------------------\nCoding question:\n\n";
		output = output + getQuestion() + "\n\n" + "Please type your answer here:";
		
		return output;
	}

	
	@Override
	public boolean process(Scanner scan) {
		String userInput = readAttempt(scan);
		boolean result;
		String r;
				
		makeFile("import java.util.*;\nimport java.io.*;\npublic class Question{\npublic static void main(String[] args){\n"+userInput+"\n}\n}", "Question.java");
		makeFile(sampleInput, "input.txt");
		r = getOutput("javac Question.java");	// compiling Question.java file
		if (!r.equals("")){				// Checking to make sure the file compiles properly
			System.err.println("DIDN'T COMPILE");
			return result = false;
		} else {}
		
		r = getOutput("java Question");		//Running file Question.class
		r = r.trim();		//Trimming r for white spaces
		if (gradingIndicator == true  && r.equals(sampleOutput)) 
		{
			result = true;
			setansweredCorrectly(result);			
		} 
		else if (gradingIndicator == false){
			result = check(correctAnswer, userInput);
			setansweredCorrectly(result);
		}
		/*else if (gradingIndicator == false  && correctAnswer.equals(userInput))
		{
			result = true;
			setansweredCorrectly(result);		
		}*/
		else {
			result = false;
			setansweredCorrectly(result);
		}
		
		return getansweredCorrectly();
	}
	
	public static int count(){
		return totalQuestions;
	}
	
	// Methods for making a file and compiling .java files
	public static void makeFile(String code, String fileName){
		try{
			BufferedWriter buf = new BufferedWriter(new FileWriter(fileName));
			buf.write(code);
			buf.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getOutput(String command){
		Runtime run = Runtime.getRuntime();
		StringBuffer output = new StringBuffer();
		try
		{
				Process pr = run.exec(command);
				BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line = null;
				
				while ((line=buf.readLine())!=null) {
					System.err.println(line);
					output.append(line+"\n");
				} 
				buf.close();

				buf = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
				if (command.startsWith("javac")){
					line=buf.readLine();
					line=buf.readLine();
				}				
				while ((line=buf.readLine())!=null) {
					System.err.println(line);
					output.append(line+"\n");
				}
				buf.close();
				pr.getInputStream().close();
				pr.getOutputStream().close();
				pr.getErrorStream().close();
				pr.destroy();
			}
			catch (Exception e)
			{
				output.append(e.getMessage());
			}
			return output.toString();		
	}

}
