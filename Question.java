import java.util.*;
import java.io.*;
public class Question{
public static void main(String[] args){
try{BufferedReader buf = new BufferedReader(new FileReader("input.txt"));String line = null;ArrayList nums = new ArrayList();line=buf.readLine();String[] parts = line.split(",");for(String part: parts)nums.add(new Integer(part));Collections.sort(nums);System.out.println(nums.get(0));}catch(Exception e){e.printStackTrace();}
}
}