
/**
 *  Anthony Borza
 *  Thing.java
 */

import java.util.Scanner;

public class Thing implements Comparable<Thing>
{
   String name;					// name declared as data type String
   int index;					// index declared as data type integer
   int parent;					// parent declared as data type integer
   boolean busyFlag = false;	// busyFlag declared as a boolean and set as false
   
   
 //-------------------------------------------- Thing Constructor -------------------------------------------//	
   
   /**
    * Constructor for Thing
    */
   
	public Thing() 
	{
		setName("");	// sets name to empty string
		setIndex(0);	// sets index to zero
		setParent(0);	// sets parent to zero
	} 
   
 //-------------------------------------------- Thing Method ------------------------------------------------//	
  
   /**
    * Constructor with Scanner: allows various types of data to be 
    * read in.
    * @param sc
    */
   
   public Thing(Scanner sc)
   {
	   super();						// calls parent class constructor
	   
	   if(sc.hasNext())				// returns true if this scanner has another token in its input
	   {
		   setName(sc.next());		// finds and returns the next complete token from this scanner
	   }
	   
	   if(sc.hasNextInt())			// returns true if the next token in this scanner's input can be interpreted as a Integer value	
	   {	
		   setIndex(sc.nextInt());	// scans the next token of the input as a integer	
	   }
	   
	   if(sc.hasNextInt())			// returns true if the next token in this scanner's input can be interpreted as a Integer value	
	   {
		   setParent(sc.nextInt());	// scans the next token of the input as a integer	
	   }
   }
   
 //---------------------------------------- Get Methods -------------------------------------------//

   /**
    * getIndex()  method: is used to access the variable index
    * @return index
    */
   
	public int getIndex() 
	{
		return index;	// return index
	}
	
	/**
	 * getName() method: is used to access the variable name
	 * @return name
	 */
	
	public String getName() 
	{	
		return name;	// return name
	}

	/**
	 * getParent() method: is used to access the variable parent
	 * @return
	 */
	
	public int getParent()
	{
		return parent;	// return parent
	}

	//---------------------------------------- Set Methods -------------------------------------------//
	
	/**
	 * setIndex method: is used to modify the variable index
	 * @param index
	 */
	
	public void setIndex(int index)
	{
		this.index = index;	 		// reference to index
	}

	/**
	 * setName method: is used to modify the variable name
	 * @param name
	 */
	
	public void setName(String name) 
	{
		this.name = name;			// reference to name
	}

	/**
	 * setParent method: is used to modify the variable parent
	 * @param parent
	 */
	
	public void setParent(int parent) 
	{
		this.parent = parent;		// reference to parent
	}

   
 //-------------------------------------------- compareTo Method ------------------------------------------------//	
   
   /**
    * compareTo Method: is a member of the comparable interface
    * It is not a considered to be a member of an Objects. Its goal
    * is to provide a ordering of objects.
    */
  
   public int compareTo(Thing m)
   {
	   if(m.getClass() == CargoShip.class || m.getClass() == PassengerShip.class)
	   {
		   System.out.println(" ship");
		   return ((Ship)this).compareTo2((Ship)m);
	   }
	   System.out.println("Thing compares");
	   return name.compareTo(m.getName());
   } 

 //-------------------------------------------- toString Method ------------------------------------------------//	
   
   /** 
    * toString Method:The toString() method is used when we need a string representation of an object. 
    * It is defined in Object class
    */
   
   public String toString()
   {
	   return getName() + "    " + getIndex();		// returns name and index as a representation of a string
   }

}
