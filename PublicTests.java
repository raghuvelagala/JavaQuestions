import static org.junit.Assert.*;

import org.junit.Test;


public class PublicTests {

	/*@Test
	public void test1(){
		
		StringBuffer result = DriverRun.run(1,"");
        assertEquals(result.toString(), "\n\n--------------------------------------------------\nMultiple choice question:\n\nIs Java a compiled or interpreted programming language?\n\nA: Compiled\nB: Interpreted\nC: Compiled and Interpreted\nD: Executed\n\n\nPlease make your letter selection below. \nSelection: \nPercent correct: 100.0\nCongratulations! You passed all questions on this quiz...eventually :-)\n");
	}*/
	/*
	@Test
	public void test1a(){
		
		StringBuffer result = DriverRun.run(1,"a");
		System.out.println(result);
        assertEquals(result.toString(), "\n\n--------------------------------------------------\nMultiple choice question:\n\nIs Java a compiled or interpreted programming language?\n\nA: Compiled\nB: Interpreted\nC: Compiled and Interpreted\nD: Executed\n\n\nPlease make your letter selection below. \nSelection: \nPercent correct: 0.0\n\n\n--------------------------------------------------\nMultiple choice question:\n\nIs Java a compiled or interpreted programming language?\n\nA: Compiled\nB: Interpreted\nC: Compiled and Interpreted\nD: Executed\n\n\nPlease make your letter selection below. \nSelection: \nPercent correct: 50.0\nCongratulations! You passed all questions on this quiz...eventually :-)\n");
    }
	
	@Test
	public void test1b(){
		
		String result = DriverRun.run(1,"b").toString().replaceAll("33.33333\\d", "33.333336");
        assertEquals(result.toString(), "\n\n--------------------------------------------------\nMultiple choice question:\n\nIs Java a compiled or interpreted programming language?\n\nA: Compiled\nB: Interpreted\nC: Compiled and Interpreted\nD: Executed\n\n\nPlease make your letter selection below. \nSelection: \nPercent correct: 0.0\n\n\n--------------------------------------------------\nMultiple choice question:\n\nIs Java a compiled or interpreted programming language?\n\nA: Compiled\nB: Interpreted\nC: Compiled and Interpreted\nD: Executed\n\n\nPlease make your letter selection below. \nSelection: \nPercent correct: 0.0\n\n\n--------------------------------------------------\nMultiple choice question:\n\nIs Java a compiled or interpreted programming language?\n\nA: Compiled\nB: Interpreted\nC: Compiled and Interpreted\nD: Executed\n\n\nPlease make your letter selection below. \nSelection: \nPercent correct: 33.333336\nCongratulations! You passed all questions on this quiz...eventually :-)\n");
    }	
	
	@Test
	public void test2a(){
		String s = "MULTIPLECHOICEQUESTION#2#[Compiled,Interpreted,Compiled and Interpreted,Executed]#Is Java a compiled or interpreted programming language?#Java as a Language#100";
		boolean v = DriverRun.validate(s);
		System.out.println(v);
        
	}
	
	@Test
	public void test2b(){
		String line = "TRUEFALSEQUESTION#true#A static method cannot access instance attributes.#Java Syntax#7";
		System.out.print(DriverRun.validate(line));
	}
	*/
	@Test
	public void test3a(){
		
		StringBuffer result = DriverRun.run(3,"a");
		System.out.println(result);
		       
	}

	
}