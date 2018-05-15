/*
 * Name : Jordan Watson
 * Date: 10/22/2017
 * FileName: CalendarGames.java
 */
 
 /*
  *The class CalendarGames contains a program
  *that prompts a user through a series of
  *questions that will result in the program guessing 
  *the users birthday, month and day in nine or less guesses.
  */

import java.util.*;

public class CalendarGames{

	//This method converts the users birthday month to a string
	public static String monthToString(int month) {
		String monthOfYear;
		if(month == 1) {
		  monthOfYear = "January";
		} else if(month == 2) {
		  monthOfYear = "February";
		} else if(month == 3) {
		  monthOfYear = "March";
		} else if(month == 4) {
		  monthOfYear = "April";
		} else if(month == 5) {
		  monthOfYear = "May";
		} else if(month == 6) {
		  monthOfYear = "June";
		} else if(month == 7) {
		  monthOfYear = "July";
		} else if(month == 8) {
		  monthOfYear = "August";
		} else if(month == 9) {
		  monthOfYear = "September";
		} else if(month == 10) {
		  monthOfYear = "October";
		} else if(month == 11) {
		  monthOfYear = "November";
		} else if(month == 12) {
		  monthOfYear = "December";
		} else {
			monthOfYear = "Not a valid month";
		}
		return monthOfYear;
	}
	
	//This method determines when the birthday is in February, if the user is born in a leap year or not
	public static boolean isLeapYear(int year) {
		if(year % 4 != 0){
		  return false;
		} else if(year % 100 != 0){
		  return true;
		} else if(year % 400 != 0) {
		  return false;
		} else {
		  return true;
		}
	}
	
	//This method determines the max day of each month, including the leap year of February
	public static int numDaysInMonth(int month, int year) {
		int numDays = 0;
		switch (month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				numDays = 31;
				break;
			case 4: case 6: case 9: case 11:
				numDays = 30;
				break;
			case 2:
			
				//When february, leap year or not
				if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) {
					numDays = 29;
				}else {
					numDays = 28;
				}
				break;
			default:
				System.out.println("Invalid month.");
				break;
				}
			return numDays;
	}

	//This method involves the interaction between the console and the user. Guessing the users birthday
	public static void guessMyBirthday(){

		int lowMonthNum = 1;
		int highMonthNum = 12;
		int Guess = (lowMonthNum + highMonthNum) / 2;
		int guessCounter = 0;
		int lowDay = 1;
		int highDay = 31;
		int currentDayGuess = (lowDay + highDay) / 2;
	

		Scanner console = new Scanner(System.in);

		//Determining if the users birthday is in June
		System.out.println("Is your birthday in " + monthToString(Guess) + " yes or no: ");
		String userResponse = console.next();
		guessCounter++;

		//If Birthday is not June, determining if birhthday is before or after June
		while(userResponse.equalsIgnoreCase("no")) {
			System.out.println("Is your birthday after this month? yes or no: ");
			userResponse = console.next();
		  
	  
			//Determining the birthday month when it is after June
			if(userResponse.equalsIgnoreCase("no")) {
				lowMonthNum = lowMonthNum;
				highMonthNum = Guess -1;
				Guess = (highMonthNum + lowMonthNum) / 2;
				System.out.println("Is your birthday in " + monthToString(Guess) + " yes or no: ");
				guessCounter++;
				
			//Determining the birhday month when it is before June
			} else if(userResponse.equalsIgnoreCase("yes")) {
				lowMonthNum = Guess + 1;
				highMonthNum = highMonthNum;
				Guess = (lowMonthNum + highMonthNum) / 2; 
				System.out.println("Is your birthday in " + monthToString(Guess) + " yes or no: ");
				guessCounter++;
				}
				userResponse= console.next();
			  
			}
		 
		//If birthday is in February, determining the day if the user is born on a leap year
		while(Guess == 2) {
			System.out.println("What year were you born?: ");
			int userInt = console.nextInt();
			boolean leapOrNoLeap = isLeapYear(userInt);
		
			System.out.println("Is your birthday on " + monthToString(Guess) + " " + (numDaysInMonth(Guess, userInt) / 2) + " yes or no: ");
			userResponse = console.next();
			guessCounter++;
			currentDayGuess = numDaysInMonth(Guess, userInt) / 2;
			
			//Users birhtday is on a leap year
			while(leapOrNoLeap == false) {
				highDay = 28;
				break;
			}
			
			//Users birthday is not on a leap year
			while(leapOrNoLeap == true) {
				highDay = 29;
				break;
			}
		
			//Determining the users birth day in February 
			while(userResponse.equalsIgnoreCase("no")) {
				System.out.println("Is your birthday after this day? yes or no: ");
				userResponse = console.next();
				guessCounter++;
			  
				//Determining the birth day when it is before February the 14th
				if(userResponse.equalsIgnoreCase("no")) {
				lowDay = lowDay;
				highDay = currentDayGuess -1;
				currentDayGuess = (highDay + lowDay) / 2;
				System.out.println("Is your birthday in " + monthToString(Guess) + " " + currentDayGuess + " yes or no: ");
				guessCounter++;
				
				//Determining the birth day when it is after February the 14th
				}else if(userResponse.equalsIgnoreCase("yes")) {
					lowDay = currentDayGuess + 1;
					highDay = highDay;
					currentDayGuess = (lowDay + highDay) / 2; 
					
					System.out.println("Is your birthday in " + monthToString(Guess) + " " + currentDayGuess + " yes or no: ");
					guessCounter++;
				}
				userResponse= console.next();
			}
		break;	
		}

		//If birthday is not in February, determining the users birth day of any month
		while(Guess == 1 || Guess > 2) {
			System.out.println("Is your birthday in " + monthToString(Guess) + " " + (numDaysInMonth(Guess, 2017) / 2) + " yes or no: ");
			guessCounter++;
			userResponse = console.next();
			currentDayGuess = numDaysInMonth(Guess, 2017) / 2;
			break;
		}
   
		//When the users birthday is not in the middle of that month, determining if it is before or after
		while(userResponse.equalsIgnoreCase("no")) {
			System.out.println("Is your birthday after this day? yes or no: ");
			userResponse = console.next();
	  	  
			//Determining birth day when it is after the 15th
			if(userResponse.equalsIgnoreCase("no")) {
				lowDay = lowDay;
				highDay = currentDayGuess -1;
				currentDayGuess = (highDay + lowDay) / 2;
				System.out.println("Is your birthday in " + monthToString(Guess) + " " + currentDayGuess + " yes or no: ");
				guessCounter++;
			
			//Determining the birth month when it is before the 15th
			}else if(userResponse.equalsIgnoreCase("yes")) {
				lowDay = currentDayGuess + 1;
				highDay = highDay;
				currentDayGuess = (lowDay + highDay) / 2; 			
				System.out.println("Is your birthday in " + monthToString(Guess) + " " + currentDayGuess + " yes or no: ");
				guessCounter++;
			}
			userResponse= console.next();
		}
		
		//Printing out the number of guesses and the users birthday
		System.out.print("It took " + guessCounter + " guesses to find your birthday, which is " + monthToString(Guess) + " " + currentDayGuess);
	}
	
	//Calling guessMyBirthday method to perform tasks.
	public static void main(String[] args) {
		guessMyBirthday();
	}
}
