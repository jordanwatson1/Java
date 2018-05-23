/*
 * Name : Jordan Watson
 * Date: 11/17/2017
 * FileName: CutupSongCreator.java
 */
 
 /*
  * The class CutupSongCreator contains a program
  * that asks a user for a file which contains a song. In a song line it will contain in order 
  * the genre of the song, the line number of the song and the lyrics of the song.
  * This class creates new songs by using the cut-up technique. 
  * The class SongLine is used in accordance with this class.
  */
  
import java.awt.*;
import java.util.*;
import java.io.*;

public class CutupSongCreator{
	
	/*
	 * The makeArray method creats an array of SongLines from a set of lines,
	 * sourced by the Scanner object.
	 */
	public static SongLine[] makeArray(Scanner reader){
		
		/* <lines> indicates how many lines of information will follow.
		 * Each subsequent line contains, in order, <genre>: a single word,
		 * <line number>: a single integer, and <single lyric line>: all the remaining words up to the newline character.
		 */
		int lines = reader.nextInt();
		reader.nextLine();
		SongLine[] songArray;
		songArray = new SongLine[lines];
		
		String oneString;
		String[] lineArray;
		
		// The for loop iterates through the whole file and stores it into the array <songArray[]>.
		for(int i = 0; i < lines; i++){
			oneString = reader.nextLine();
			lineArray = oneString.split("\t");
			songArray[i] = new SongLine(lineArray[0], Integer.parseInt(lineArray[1]), lineArray[2]);
		}
		
		return songArray;
	}
	
	/*
	 * listLinesByGenre method prints out all items in an array that have a genre equal to filterWord. 
	 * The first output line is: Lines by <filterWord>:
	 */
	public static void listLinesByGenre(SongLine[] songs, String filterWord){
		
		System.out.println("Lines by " + filterWord + " :");
		for(int i = 0; i < songs.length; i++){
			String genre = songs[i].getGenre();
			
			// If the genre in the file matches the filterWord given by the user
			// the if statement will print out that lines number and words.
			if(genre.equalsIgnoreCase(filterWord)){
				System.out.println(songs[i].getLineNumber() + ": " + songs[i].getWords());
			}
		}
		System.out.println("End of listLinesByGenre test");
	}
	
	/*
	 * PrintArray prints out the elements of an array.
	 */
	public static void printArray(SongLine[] songs){
		
		// The for loop converts the array of type SongLine into a string, 
		// allowing it to be printed into readable text to the console.
		for(int i = 0; i < songs.length; i++){
			System.out.println(songs[i].toString());
		}
	}
	
	/*
	 * sortByLineNumber method sorts an array of SongLine objects by 
	 * their line number in ascending order.
	 */
	public static void sortByLineNumber(SongLine[] songs){
		
		// This portion prints the origional song from the file called.
		System.out.println("Before sorting: ");
		for(int i = 0; i < songs.length; i++){
			System.out.println(songs[i]);
		}
		
		// This protion prints the line number in ascending order
		// The bubble sort technique was used, creating a boolean
		// and then testing a line to the previous line and then 
		// if true, the first number is stored into <tempValue> and the
		// the numbers are swapped. 
		System.out.println("After sorting: ");
		boolean test = true;
		while(test){
			test = false;
			
			for(int i = 0; i < songs.length-1; i++){
				
				int numLine1 = songs[i].getLineNumber();
				int numLine2 = songs[i+1].getLineNumber();

				if(numLine1 > numLine2){
					SongLine tempValue = songs[i];
					songs[i] = songs[i+1];
					songs[i+1] = tempValue;
					
					test = true;
				}
			}
		}
		
		// Prints the sorted song by ascending line number
		for(int i = 0; i < songs.length; i++){
			System.out.println(songs[i].toString());
		}
	}
	
	/*
	 * makeSong method prints out a custom 21 line song, randomly selecting words 
	 * from the array of SongLine objects.
	 */
	public static void makeSong(SongLine[] songs){
		
		// The random object was created to pick a number 
		// between 0 and the largest line number (songs.length),
		// the forloop goes through 21 times in order to print 21 random lines
		// and the random generator is in the forloop to pick a new number every
		// iteration.
		Random generator = new Random();
		for(int i = 0; i < 21; i++){
			int randomNum = generator.nextInt(songs.length);
			System.out.println(songs[randomNum].getWords());
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		// First Scanner object where user will provide name of the 
		// text file and then stored in variable reader
		Scanner console = new Scanner(System.in);
		System.out.println("What is the input filename: ");
		String fileName = console.nextLine();
		
		// Creating a file object to be tested
		File userFile = new File(fileName);
		
		// While fileName does not exist, prompt the user to enter a new fileName
		while(!userFile.exists()){
			System.out.println("That file does not exist.");
			System.out.println("What is the input filename: ");
			// Second Scanner object reads the file text given by user
			userFile = new File(console.nextLine());
		}
		
		Scanner f = new Scanner(userFile);
		
		/***
		 *** Method tests which have been commented out inorder to test them all
		 ***/
		//printArray(makeArray(f));
		
		//System.out.println("What genre are you looking for: ");
		//String genreName = console.nextLine();
		//listLinesByGenre(makeArray(f), genreName);
		
		//sortByLineNumber(makeArray(f));
		
		//makeSong(makeArray(f));
		
		f.close();
		
		
	}
	
}
