import java.io.*;
import java.util.*;

	
public class DriverRun {
	
		public static void main(String[] args){
		
		StringBuffer sbf;
		sbf = run(Integer.parseInt(args[0]), args[1]);
		//sbf = run(2, "a");
		System.out.println(sbf.toString());
				
	}
	
	public static StringBuffer run(int num, String option){
		String filename = "db"+num+".txt";
		StringBuffer sbf = new StringBuffer();
		String[] field;		//Stores each argument of the question in a array
		int questionsCount = 0;
		Node root = null;
		Node newNode;
		
		Scanner scan;
		try			// try-catch block to read database,create objects and store them
		{
		 scan = new Scanner(new FileInputStream(filename));	// creating a stream for Scanner to read line by line
		 BufferedReader questions = new BufferedReader(new FileReader(filename));
		 String s;
		 while ((s=questions.readLine())!=null){		// reads database line by line until end of file
			 //String s = scan.nextLine();
			 field = s.split("#");			// splits the line into different arguments for the questions' constructors
			 
			 if(validate(s) == true	&&	field[0].equals("MULTIPLECHOICEQUESTION")){
				 questionsCount++;
				 if(questionsCount != 1){
					 field[2] = field[2].substring(1, (field[2].length() - 1));	//removing "[" and "]" for possibleAnswers
					 String[] Answers = field[2].split(",");	//Placing possible answers in a Array
					 newNode = (Node) new MultipleChoiceQuestion(new Integer(field[1]).intValue(), Answers, field[3], field[4]);
					 ((BaseQuestion)newNode).setDifficulty(new Integer(field[5]).shortValue());
					 addNode(root, newNode);	//adding MULTIPLECHOICEQUESTION object
				 }
				 else if(questionsCount == 1){
					 field[2] = field[2].substring(1, (field[2].length() - 1));	//removing "[" and "]" for possibleAnswers
					 String[] Answers = field[2].split(",");	//Placing possible answers in a Array
					 root = (Node) new MultipleChoiceQuestion(new Integer(field[1]).intValue(), Answers, field[3], field[4]);
					 ((BaseQuestion)root).setDifficulty(new Integer(field[5]).shortValue());
				 }
			 }
			 else if (validate(s) == true	&&	field[0].equals("TRUEFALSEQUESTION")){
				 questionsCount++;
				 if(questionsCount != 1){
					 newNode = (Node) new TrueFalseQuestion(new Boolean(field[1]).booleanValue(), field[2], field[3]);
					 ((BaseQuestion)newNode).setDifficulty(new Integer(field[4]).shortValue());
					 addNode(root,newNode);
				}
				 else if(questionsCount == 1){
					 root = (Node) new TrueFalseQuestion(new Boolean(field[1]).booleanValue(), field[2], field[3]);
					 ((BaseQuestion)root).setDifficulty(new Integer(field[4]).shortValue());
				 }
			 }
			 else if (validate(s) == true	&&	field[0].equals("SHORTANSWERQUESTION")){
				 questionsCount++;
				 if(questionsCount != 1){
					 newNode = (Node) new ShortAnswerQuestion(field[1], field[2], field[3]);
					 ((BaseQuestion)newNode).setDifficulty(new Integer(field[4]).shortValue());
					 addNode(root, newNode);
				 }
				 else if (questionsCount == 1){
					 root = newNode = (Node) new ShortAnswerQuestion(field[1], field[2], field[3]);
					 ((BaseQuestion)root).setDifficulty(new Integer(field[4]).shortValue());
				 }
			 }
			 else if (validate(s) == true	&&	field[0].equals("CODINGQUESTION")){
				 questionsCount++;
				 if (questionsCount != 1){
					 newNode = (Node) new CodingQuestion(field[1], field[2], field[3], new Boolean(field[4]).booleanValue(), field[5], field[6]);
					 ((BaseQuestion)newNode).setDifficulty(new Integer(field[7]).shortValue());
					 addNode(root, newNode);
				 }
				 else if (questionsCount == 1){
					 root = (Node) new CodingQuestion(field[1], field[2], field[3], new Boolean(field[4]).booleanValue(), field[5], field[6]);
					 ((BaseQuestion)root).setDifficulty(new Integer(field[7]).shortValue());
				 }
			 }
			 else if(validate(s) == false){
				 sbf.append("INVALID " + s + "\n");
			 }
		 }
		scan.close();	// closes the stream
		}
		catch(IOException ex)	// if database can't be opened, then it catches the exception
		{ 
		 sbf.append("Could not open file..." + "\n");
		 System.exit(0);
		}
		//BaseQuestion debug = ((BaseQuestion)root.getRight().getLeft().getRight());
		
		//Printing the Questions
		try{
			
			Scanner read = new Scanner (new File("user"+num+option+".txt"));
					
			BaseQuestion currQuestion = (BaseQuestion) root;
			short hardestdiff = (short)((BaseQuestion)findHardest(root)).difficulty();
			boolean lastAttempt = true;
			while((((BaseQuestion)findHardest(root)).getansweredCorrectly() == false)){ // || (currQuestion != null)){
						
				if((currQuestion.difficulty() != hardestdiff) && (currQuestion.getansweredCorrectly() == false)){
					sbf.append(((BaseQuestion)currQuestion).toString());
					currQuestion.setansweredCorrectly(currQuestion.process(read));
					lastAttempt = currQuestion.getansweredCorrectly();
					sbf.append("\n" + "Percent correct: " + currQuestion.percentCorrect(lastAttempt) + "\n");
					
					if (lastAttempt == true){
						sbf.append("CORRECT! It took you " + currQuestion.getattemptsCount() + " tries." + "\n");
						
						if(currQuestion.getRight() != null){
							currQuestion = (BaseQuestion) currQuestion.getRight();
						}else if((currQuestion.getRight() == null)){
							
							currQuestion = (BaseQuestion) root;
						}
						
					}else if(lastAttempt == false){
						
						sbf.append("Sorry, that was WRONG. You'll be able to try again in a bit." + "\n");
					
						if(currQuestion.getLeft() != null){
							currQuestion = (BaseQuestion) currQuestion.getLeft();
						}else if(currQuestion.getLeft() == null){
							
							currQuestion = (BaseQuestion) root;
						}
					}
				}
				else if((currQuestion.difficulty() == hardestdiff) && (currQuestion.getansweredCorrectly() == false)){
					sbf.append(((BaseQuestion)currQuestion).toString());
					currQuestion.setansweredCorrectly(currQuestion.process(read));
					lastAttempt = currQuestion.getansweredCorrectly();
					sbf.append("\n" + "Percent correct: " + currQuestion.percentCorrect(lastAttempt) + "\n");
					
				}
				else if (currQuestion.getansweredCorrectly() == true){		//if the question is already answered but unanswered questions still exist in the tree.
					if(currQuestion.getRight() != null){
						currQuestion = (BaseQuestion) currQuestion.getRight();
					}else{
						currQuestion = (BaseQuestion) root;
					}
					
					
				}
			}

			sbf.append("Congratulations! You passed all questions on this quiz...eventually :-)" + "\n");
				
		} catch (Exception e){ //FileNotFoundException
			sbf.append("user.txt not found." + "\n");
		} 
		return sbf;
		
	}
	
	public static void addNode(Node currNode, Node newNode){
	  if(currNode != null && newNode != null){	
		if((((BaseQuestion)currNode).difficulty() > ((BaseQuestion)newNode).difficulty()) && currNode.getLeft() == null){
			currNode.setLeft(newNode);
			newNode.setParent(currNode);
		}
		else if ((((BaseQuestion)currNode).difficulty() < ((BaseQuestion)newNode).difficulty()) && currNode.getRight() == null){
			currNode.setRight(newNode);
			newNode.setParent(currNode);
		}
		else if ((((BaseQuestion)currNode).difficulty() == ((BaseQuestion)newNode).difficulty()) && currNode.getRight() == null){
			currNode.setLeft(newNode);
			newNode.setParent(currNode);
		}
		else if((((BaseQuestion)currNode).difficulty() > ((BaseQuestion)newNode).difficulty()) && currNode.getLeft() != null){
			addNode(currNode.getLeft(), newNode);
		}
		else if((((BaseQuestion)currNode).difficulty() < ((BaseQuestion)newNode).difficulty()) && currNode.getRight() != null){
			addNode(currNode.getRight(), newNode);
		}
	  }
	}
	
	public static String print(Node node, int level){
		String output;
		String indent = "";
		
		for(int i = 0; i<level;i++){
			indent = indent + " ";
		}
		output = indent + ((BaseQuestion) node).difficulty() + "\n";
		
		if(node.getLeft() == null){
			output = output + "";
			
		} if (node.getRight() == null){
			output = output + "";
			
		} if (node.getLeft() != null){
			output = output + print(node.getLeft(), level+2);
			
		} if (node.getRight() != null){
			output = output + print(node.getRight(), level+2);
			
		}
		
		return output;
	
	}
	
	public static Node findHardest(Node node){
		/*Node currHardestNode = node;
		
		if(node.getParent() == null){		// if node is the only node in the tree or doesn't have a parent(root)
			currHardestNode = node;
		}
		else if (node.getParent() != null){	//if the tree has more elements
			Node parent = node;		// temp variable of type Node (to hold a parent of a node)
			while(parent.getParent() != null){		//loops until the root of the entire tree is found
				parent = node.getParent();
			}
			currHardestNode = parent;		//In case the data structure is linear and the root doesn't have a getRight(). The root becomes the hardest Node
			while(parent.getRight() != null){	//After the root of the entire tree is found, the loop searches the hardest node from there (i.e until getRight()==null)
				parent = parent.getRight();
				currHardestNode = parent;
			}
		}
		
		return currHardestNode;
		*/
		
		Node currHardestNode = node;
		Node temp = node;
		while(temp.getRight() != null){
			temp = temp.getRight();
			currHardestNode = temp;
		}
		
		return currHardestNode;
	}
	
	public static boolean validate(String line){
		String[] split = line.split("#"); 
		String pattern = "^(((MULTIPLECHOICEQUESTION#\\d+#\\[.*\\])|(TRUEFALSEQUESTION#(true|false))|((SHORTANSWERQUESTION#|CODINGQUESTION#)[^!@#$^~`]*))#)(\\w|\\?|\\.| )+#(\\w| )+(#(true|false)#(.*)?#(.*)?)?#(0$|[1-9]{1}$|[1-9]{1}[0-9]{1}$|100$)+$";
		
		String mcregex = "MULTIPLECHOICEQUESTION#\\d+#\\[.*\\]#(\\w|\\?|\\.| )+#(\\w| )+#([1-9]{1}$|[1-9]{1}[0-9]{1}$|100$)+";
		String tfregex = "TRUEFALSEQUESTION#(true|false)#(\\w|\\?|\\.| )+#(\\w| )+#[0-100]+#([1-9]{1}$|[1-9]{1}[0-9]{1}$|100$)+";
		String saregex = "SHORTANSWERQUESTION#[^!@#$^~`]*#(\\w|\\?|\\.| )+#(\\w| )+#[0-100]+#([1-9]{1}$|[1-9]{1}[0-9]{1}$|100$)+";
		String cdregex = "CODINGQUESTION#[^!@#$^~`]*#(\\w|\\?|\\.| )+#(\\w| )+#(true|false)#(.*)+#(.*)+#[0-100]+#([1-9]{1}$|[1-9]{1}[0-9]{1}$|100$)+";
		/*
		if (split[0].equals("MULTIPLECHOICEQUESTION") && line.matches(mcregex)){
			return true;
		}else if (split[0].equals("TRUEFALSEQUESTION") && line.matches(tfregex)){
			return true;
		}else if (split[0].equals("SHORTANSWERQUESTION") && line.matches(saregex)){
			return true;
		}else if (split[0].equals("CODINGQUESTION") && line.matches(cdregex)){
			return true;
		} else{
			return false;
		}
		*/
		return line.matches(pattern);
	}

}
