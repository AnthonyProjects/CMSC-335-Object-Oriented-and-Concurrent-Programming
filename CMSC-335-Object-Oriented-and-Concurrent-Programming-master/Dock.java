
/**
 *  Anthony Borza
 *  Dock.java
 */


import java.util.Scanner;

public class Dock extends Thing
{
    Ship ship;

  //-------------------------------------------- Dock Method ------------------------------------------------//	
    
	 /**
	  * Constructor with Scanner: allows various types of data to be 
	  * read in.  
	  * @param sc
	  */
	   
	 public Dock(Scanner sc)
	 {
	    super(sc);	// calls parent class constructor
	 }
	 
	  //-------------------------------------------- Get and Set Methods --------------------------------------// 
		/**
		 * getShip() method
		 * @return the ship
		 */
	 
		public Ship getShip() 
		{
			return ship;	// return ship
		}

		/** setShip method
		 * @param ship the ship to set
		 */
		
		public void setShip(Ship ship) 
		{
			this.ship = ship;	
		} 
	//-------------------------------------------- toString Method ----------------------------------------------//	
	 
	 /**
	  *  toString Method:The toString() method is used when we need a string representation of an object. 
	  *  It is defined in Object class
	  */
	 
	 public String toString()
	 {
		 String st = "\n" + "Dock: " + super.toString() + "\n";
		 
		 if(ship == null)	// if ship is equal to null
		 {
			 return st;		// return st 
		 }
		 else	// else
		 {
			 st += "\n " + "   Ship: " + ship;	// return ship name
		 }
		 return st;		// returns st
	 }

} 
