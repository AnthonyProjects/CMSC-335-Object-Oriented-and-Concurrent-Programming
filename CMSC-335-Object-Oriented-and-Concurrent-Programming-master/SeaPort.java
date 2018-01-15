
/**
 *  Anthony Borza
 *  SeaPort.java
 */

import java.util.ArrayList;
import java.util.Scanner;

public class SeaPort extends Thing
{

   ArrayList <Dock>   docks   = new ArrayList<>();	// list of all docks at the port
   ArrayList <Ship>   que     = new ArrayList<>();	// list of ships waiting to dock
   ArrayList <Ship>   ships   = new ArrayList<>();	// a list of all the ships at this port
   ArrayList <Person> persons = new ArrayList<>();	// people with skills at this port
   ArrayList <Job> jobs = new ArrayList<>();		// jobs is an array list for Job
  
   //-------------------------------------------- SeaPort Method ------------------------------------------------//	
   
   /**
    * Constructor with Scanner: allows various types of data to be 
    * read in.  
    * @param sc
    */
   
   public SeaPort(Scanner sc) 
   {
       super(sc);	// calls parent class constructor
   }

 //-------------------------------------------- Get Methods --------------------------------------------------//
   
   /**
    *  getDocks() method: is used to access the variable docks
    *  @return docks
    */
   
   public ArrayList<Dock> getDocks()
   {
		return docks;		// return docks
   }
   
	/**
	 * getQue() method: is used to access the variable que
	 * @return que
	 */
	   
	public ArrayList<Ship> getQue() 
	{
		return que;			// return que
	}

	/**
	 * getShips() method: is used to access the variable ships
	 * @return ship
	 */
	
	public ArrayList<Ship> getShips() 
	{
		return ships;		// return ships
	}
	
	/**
	 * getPerson() method: is used to access the variable persons
	 * @return person
	 */
	
	public ArrayList<Person> getPerson() 
	{
		return persons;		// return persons
	}
	
	//-------------------------------------------- Set Methods ------------------------------------------------//	
	
	/**
	 * setDocks() method: is used to modify the variable docks
	 * @param docks
	 */
   
	public void setDocks(ArrayList<Dock> docks) 
	{
		this.docks = docks;		// reference to docks
	}
	
	/**
	 * setQue() method: is used to modify the variable que
	 * @param que
	 */
	
	public void setQue(ArrayList<Ship> que)
	{
		this.que = que;			// reference to que
	}
	
	/**
	 * setShips() method: is used to modify the variable ships
	 * @param ships
	 */
	
	public void setShips(ArrayList<Ship> ships) 
	{
		this.ships = ships;		// reference to ships
	}

	/**
	 * setPerson() method: is used to modify the variable person
	 * @param person
	 */

	public void setPerson(ArrayList<Person> person) 
	{
		this.persons = person;	// reference to person
	} 

   //-------------------------------------------- toString Method ------------------------------------------------//	
   
   /** 
    * toString method: The toString() method is used when we need a string representation of an object. 
    * It is defined in Object class
    * 
    */

   public String toString() 
   {
	   String st = "\n\nSeaPort:  " + super.toString();
	   
	   for(Dock d: docks)	// for each element d of type Dock in array docks	
	   {
		   st += "\n" + d;	// add a line space after docks
	   }
	   
	   st += "\n\n --- List of all ships in que:\n";	// displays as text above ships in que
	   
	   for(Ship s: que )				// for each element s of type Ship in array que	
	   {
		   st += "\n\n   > " + s;		// adds a > before all ships in que
	   }
	   
	   st += "\n\n --- List of all ships:\n";			// displays as text above all ships 
	   
	   for(Ship s: ships)			// for each element s of type Ship in array ships	
	   {
		   st += "\n\n   > " + s;	// adds a > before all of the ship types
	   }
	   
	   st += "\n\n\n --- List of all persons:\n";		// displays as text above all persons
	   
	   for(Person p: persons)		// for each element p of type Person in array persons	
	   {
		   st += "\n   > " + p;		// adds a > before Persons
	   }
	   return st;	// return st

	} 
} 