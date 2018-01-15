
/**
 *  Anthony Borza
 *  CargoShip.java
 */

import java.util.Scanner;

public class CargoShip extends Ship 
{

   double cargoWeight;		// cargoWeight declared as a data type double
   double cargoVolume;		// cargoVolume declared as a data type double
   double cargoValue;		// cargoValue declared as a data type double
   
  //-------------------------------------------- CargoShip Method ------------------------------------------------//	
   
   /**
    * Constructor with Scanner: allows various types of data to be 
    * read in.  
    * @param sc
    */
    
	public CargoShip(Scanner sc)
	{
	   super(sc);								// calls parent class constructor
	   
	   if(sc.hasNextDouble())					// returns true if the next token in this scanner's input can be interpreted as a double value
	   {
		   cargoWeight = sc.nextDouble();		// scans the next token of the input as a double
	   }
	   
	   if(sc.hasNextDouble())					// returns true if the next token in this scanner's input can be interpreted as a double value
	   {
		   cargoVolume = sc.nextDouble();		// scans the next token of the input as a double
	   }
	   
	   if(sc.hasNextDouble())					// returns true if the next token in this scanner's input can be interpreted as a double value
	   {
		   cargoValue = sc.nextDouble();		// scans the next token of the input as a double
	   }
	}
	
	//-------------------------------------------- toString Method ------------------------------------------------//	
	
   /**
	*  toString Method:The toString() method is used when we need a string representation of an object. 
	*  It is defined in Object class
	*/

   public String toString() 
   {
	   String st = "Cargo ship: " + super.toString();
	   
	   if(jobs.size() == 0) 	// if number of jobs is zero
	   {
	      return st;	// return st
	   }
	   for(Job j: jobs)	// for each element j of type Job in array jobs	
	   {
	      st += "\n       - " + j;	// show jobs
	   }
	      return st;	// return st
   }
}
