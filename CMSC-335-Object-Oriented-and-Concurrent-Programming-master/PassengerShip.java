
/**
 *  Anthony Borza
 *  PassengerShip.java
 */

import java.util.Scanner;

public class PassengerShip extends Ship 
{

   int numberOfPassengers;		// numberOfPassengers declared as a data type integer
   int numberOfRooms;			// numberOfRooms declared as a data type integer
   int numberOfOccupiedRooms;	// numberOfOccupiedRooms declared as a data type integer

   //-------------------------------------------- PassengerShip Method ------------------------------------------------//	
   
   /**
    * Constructor with Scanner: allows various types of data to be 
    * read in.  
    * @param sc
    */
    
   public PassengerShip(Scanner sc) 
   {
	   super(sc);									// calls parent class constructor
	   	
	   if(sc.hasNextInt())							// returns true if the next token in this scanner's input can be interpreted as a Integer value					
	   {		
		   numberOfPassengers = sc.nextInt();		// scans the next token of the input as a integer
	   }
	   
	   if(sc.hasNextInt())							// returns true if the next token in this scanner's input can be interpreted as a Integer value	
	   {
		   numberOfRooms = sc.nextInt();			// scans the next token of the input as a integer	
	   }
	   
	   if(sc.hasNextInt())							// returns true if the next token in this scanner's input can be interpreted as a Integer value	
	   {
		   numberOfOccupiedRooms = sc.nextInt();	// scans the next token of the input as a integer		
	   }
	}
   
  //-------------------------------------------- toString Method ------------------------------------------------//	
   
   	/**
	 *  toString Method:The toString() method is used when we need a string representation of an object. 
	 *  It is defined in Object class
	 */
   
   public String toString() 
   {
	   String st = "Passenger ship:  " +  super.toString();
	   
	   if(jobs.size() == 0) // if number of jobs is zero
	   {	
	      return st;		// return st
	   }
	   for(Job j: jobs)	// for each element j of type Job in array jobs	
	   {
	      st += "\n       - " + j;		// shows jobs
	   }
	      return st;		// return st
   }
} 
