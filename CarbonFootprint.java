/*
 * Author: Jordan Watson
 * Date: 04/10/2017
 * Filename: CarbonFootprint.java
 */ 

/*
 * The class CarbonFootprint contains the estimate of a carbon footprint as the 
 * amount of CO2 emissions generated by a single person in a year.
 * The estimate of CO2 is based on a person's vehile use, electricity use, and food consumption.
 */
 
import java.util.*;
public class CarbonFootprint{
	
	//transportationFootprint method calculates the kilograms of CO2 emissions in a single year, based on some values related to car usage.
	//kgCO2 = 2.3 x litersUsedPerYear
	//litersUsedPerYear = 365 x (kmPerDay/ fuelEfficiency)
	public static double transportationFootprint(double kPD, double effic){
		return 2.3* (365 * (kPD/ effic));
	}
	
	//electricityFootprint method calculates the kilograms of CO2 emissions in a single year, based on home electricity use.
	//kgCO2 = (kWhPerMonth x 12 x 0.257)/ numPeopleInHome
	public static double electricityFootprint(double kWhPerMonth, int num){
		return (kWhPerMonth * 12 * 0.257)/ num;
	}
	
	//foodFootprint method calculates the kilograms of CO2 emissions in a single year, based on the percentages of types of food in a person's diet. 
	//meat kgCO2 = (percent meat & fish) × 53.1
	//dairy kgCO2 = (percent dairy) × 13.8
	//fruit & veg kgCO2 = (percent fruits & veg) × 7.6
	//carbs kgCO2 = (percent carbs) × 3.1
	//A person’s total food kgCO2 measurement is given by the sum of the above four values.
	public static double foodFootprint(double meat, double dairy, double fv, double carb){
		return ((meat*53.1) + (dairy* 13.8) + (fv*7.6) + (carb*3.1))/100;
	}
	
	//Calculates the metric Tons of CO2 emissions in a single year, based on car travel, electricity use, and percentage of food groups.
	//tCO2 = (transportation footprint + electricity footprint + food footprint) ÷ 1000
	public static double totalFootprint(double trans, double elec, double food){
		return (trans + elec + food)/1000;
	}
	
	public static void main(String[] args){
		
		//Importing relivant carbonfootprint strings to the user, which the value is then stored in a variables that is used to calculate the totalFootprint.
		Scanner userInput= new Scanner(System.in);
		
		//Transportation userInput.
		System.out.print("How many km do you drive in one day?");
		double kPD = userInput.nextDouble();
		System.out.println("Choose the appropriate fuel efficiency for your car:");
		System.out.println("\tvery small car : 7.1 km/litre");
		System.out.println("\tsmall car : 8.2 km/litre");
		System.out.println("\tsports car : 14.9 km/litre");
		System.out.println("\tSUV : 12.3 km/litre");
		System.out.print("Or, if you know the efficency rating, choose another number: ");
		double effic = userInput.nextDouble();	
		System.out.println ("Your Carbon footprint with respect to car use is " + transportationFootprint(kPD, effic)+"kg/year.");
		System.out.println();
		
		//Electricity userInput.
		System.out.print("What is the average electricity consumption per month, in kilowatts? ");
		double kWhPerMonth = userInput.nextDouble();
		System.out.print("How many people live in the house? ");
		int num = userInput.nextInt();
		System.out.println("Your Carbon footprint with respect to electricity use is " + electricityFootprint(kWhPerMonth, num)+"kg/year.");
		System.out.println();
		
		//Food userInput.
		System.out.print("Of all the food you eat, what percent consists of meat or fish? ");
		double meat = userInput.nextDouble();
		System.out.print("Of all the food you eat, what percent consists of dairy products? ");
		double dairy = userInput.nextDouble();
		System.out.print("Of all the food you eat, what percent consists of fruit and vegetables? ");
		double fv = userInput.nextDouble();
		System.out.print("Of all the food you eat, what percent consists of carbohydrates? ");
		double carb = userInput.nextDouble();
		System.out.println("Your Carbon footprint with respect to food consumption is " + foodFootprint(meat, dairy, fv, carb)+"kg/year");
		System.out.println();
		
		//totalFootprint is printed to the console.
		System.out.println("You produce an annual total of "+ totalFootprint(transportationFootprint(kPD, effic), electricityFootprint(kWhPerMonth, num), foodFootprint(meat, dairy, fv, carb)) +" metric tons of CO2per year.");	
	}	
}
