/*
 * Author: Jordan Watson
 * Date: 29/10/2017
 * Filename: DNASequencing.java
*/ 

/*
 * The class DNASequencing contains codes that allow one
 * to read sequences of DNA and determine if there are any
 * mutations within the sequence. This code can also take a 
 * mutated string and determine if it matches a non mutated string.
*/

public class DNASequencing{
	
	//This method counts the total mutations within a string sequence
	static int countTotalMutations(String[] seqArray){
		int numMutations = 0;
		
		//for every string in the array, this for loop determines if  
		//there is a mutation by using the other method "has mutation"
		for(int i = 0; i<seqArray.length; i++){
			if(hasMutation(seqArray[i]) == true){
				numMutations++;
			}
		}
		return numMutations;
	}
	
	//This method can determine if a particular string is repeated within the sequence
	static int findFrequency(String target, String[] strings){
		int found = 0;
		
		//the for loop goes through every sequence in the array and 
		//if the the sequence matches a particular or given sequence, it will 
		//count how many times it occurs within the sequence.
		for(int i = 0; i < strings.length; i++){
			if(strings[i] == target){
				found++;
			} 
		}
		return found;
	}
	
	//This method determines the number of times a particular DNA sequence occurs within an 
	//array of DNA sequences.
	static int findFreqWithMutations(String target, String[] seqArray){
		String stringSeq = null;   
		String stringName = null;  
		int count = 0;
		
		//This for loop goes through the entire length of the sequence array
		for(int i = 0; i < seqArray.length; i++){
			
			stringSeq = seqArray[i];   
			stringName = stringSeq.substring(0,1);  
			
				//This for loop goes through each individual character or the string and determines
				//if any two consecutive letters match, and if they dont match it adds it into a string 
				//to truncate any mutations.
				for(int j = 1; j < stringSeq.length(); j++){
					if(stringSeq.charAt(j) != stringSeq.charAt(j-1)){
						stringName += stringSeq.charAt(j);
					}
				}
				if(stringName.equals(target)) {
						count++;
				}
		}
			return count;
	}
	
	//This method determines the longest string within the entire sequence array
	static String findLongest(String[] strings){
		String longestSeq = null;
		int maxLength = strings[0].length();
		
		//The for loop goes through the entire array 
		for(int i = 0; i < strings.length; i++){
			
			//the if statement will reinitilize "longestSeq" everytime the sequence is longer than the previous one.
			if(strings[i].length() > maxLength){
				maxLength = strings[i].length();
				longestSeq = strings[i];
			}	
		}	
		return longestSeq;
	}
	
	//This boolean method determines if there is a mutation within a sequence array
	static boolean hasMutation(String sequence){
		
		//initially is false as a placeholder and then uses forloop to determine any repeated
		//letters then true if there is a mutation and false if there is no mutation 
		char[] charArray = sequence.toCharArray();
		boolean trueMutation = false;
		for(int i = 1; i< charArray.length; i++){
			if(charArray[i-1] == charArray[i]){
				trueMutation = true;
			}
		}
		return trueMutation;
	}
	
	//This method tests every other method within the program.
	public static void main(String[] args){
		
		String[] testArray= {"ACC", "TACG", "AAC", "AAC", "AC", "GTT", "CTA", "GAC", "ATAC", "AAAAACCCC"};
		String longest =findLongest(testArray);
		System.out.println("The longest String is "+longest);
		System.out.println();
		
		System.out.println("The sequence array is: ");
		printArray(testArray);
		System.out.println();
		
		System.out.println("The number of times AAC occurs: " + findFrequency("AAC", testArray));
		System.out.println();
		
		System.out.println("The total number of mutations in the DNA sequence is: " + countTotalMutations(testArray));
		System.out.println();
		
		System.out.println("The number of times AC occurs within the array of DNA sequences is " + findFreqWithMutations("AC", testArray));
	}
	
	//This method prints each sequence within the array
	static void printArray(String[] strings){
		
		//forloop goes through the length of each element in array and prints it out
		for(int i = 0; i < strings.length; i++){
			System.out.println(strings[i] + " ");
		}
	}
}
