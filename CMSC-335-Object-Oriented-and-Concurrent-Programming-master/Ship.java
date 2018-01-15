
/**
 *  Anthony Borza
 *  Ship.java
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Ship extends Thing
{
	public static final int WEIGHT = 0;	 		// WEIGHT declared as static
	public static final int LENGTH = 1;			// LENGTH declared as static
	public static final int WIDTH = 2;			// WIDTH declared as static
	public static final int DRAFT = 3;			// DRAFT declared as static
	public static int compareWith = WEIGHT;

	double weight;		// Weight declared as a data type double
	double length;		// length declared as a data type double
	double width;		// width declared as a data type double
	double draft;		// draft declared as a data type double
   
   PortTime arrivalTime;							// arrivalTime acting as a variable from class PortTime
   PortTime dockTime;								// dockTime acting as a variable from class PortTime
   ArrayList <Job> jobs = new ArrayList<>();		// ArrayList for job
   ArrayList <Person> persons = new ArrayList<>();	// ArrayList for persons
   World world = null;								// world is null
   public boolean busyFlag;							// busyFlag declared as a boolean
   
  //-------------------------------------------- Ship Method ------------------------------------------------//	
   
   /**
    * Constructor with Scanner: allows various types of data to be 
    * read in.  
    * @param sc
    */
    
   public Ship(Scanner sc)
   {
	   super(sc);						// calls parent class constructor
	   
	   if(sc.hasNextDouble())			// returns true if the next token in this scanner's input can be interpreted as a double value
	   {
		   weight = sc.nextDouble();	// scans the next token of the input as a double
	   }
	   
	   if(sc.hasNextDouble())			// returns true if the next token in this scanner's input can be interpreted as a double value
	   {
		   length = sc.nextDouble();	// scans the next token of the input as a double
	   }
	   
	   if(sc.hasNextDouble())			// returns true if the next token in this scanner's input can be interpreted as a double value
	   {
		   width = sc.nextDouble();		// scans the next token of the input as a double
	   }
	   
	   if(sc.hasNextDouble())			// returns true if the next token in this scanner's input can be interpreted as a double value
	   {
		   draft = sc.nextDouble();		// scans the next token of the input as a double
	   }
   } 

   //-------------------------------------------- get Methods ------------------------------------------------//	
   
   /**
    * getJobs  method: is used to access the variable jobs
    * @return jobs
    */
   
	public ArrayList<Job> getJobs() 
	{
		return jobs;		// return jobs
	}
	
	/**
	 * getPerson  method: is used to access the variable persons
	 * @return persons
	 */
	
	public ArrayList<Person> getPerson() 
	{
		return persons;		// return persons
	}

 //-------------------------------------------- compareTo2 Method ------------------------------------------------//	
   
   /**
    * compareTo2 Method: implements a switch statement with the following cases:
    * Weight: compares the ships weight
    * Length: compares the ships length
    * Width:  compares the ships width
    * Draft:  compares the ships draft
    * @param other
    * @return
    */
	
   public int compareTo2(Ship other) 
   {
	   System.out.println("ship compares");		// print statement
	   
	   switch(Ship.compareWith)		// start of switch statement
	   {
		   case WEIGHT: 	// case 1 WEIGHT
			   
			    if(weight > other.weight)	// ship 1 weight is greater than ship 2 weight
		        {
		            return 1;	// return 1
		        } 
		        else 	// else
		        {
		            return -1;	// return -1
		        }
			    
		   case LENGTH: 	// case 2 LENGTH
			   
			   if(length > other.length)	// ship 1 length is greater than ship 2 length
		        {
		            return 1;	// return 1
		        } 
		        else 	// else
		        {
		            return -1;	// return -1
		        }
			   
		   case WIDTH: 		// case 3 WIDTH
			   
			   if(width > other.width)		// ship 1 width is greater than ship 2 width
		        {
		            return 1;	// return 1
		        } 
		        else 	// else
		        {
		            return -1;	// return -1
		        }

		 case DRAFT:  		// case 4 DRAFT
			 
			   if(draft > other.draft)		// ship 1 draft is greater than ship 2 draft
		        {
		            return 1;	// return 1
		        } 
		        else 	// else
		        {
		            return -1;	// return -1
		        }
	   } 
			return 0;	// return 0
	}
	
   //-------------------------------------------- getInfo Method ------------------------------------------------//	
   
   /** 
    * getInfo Method: implements a switch statement with the following cases:
    * Weight: compares the ships weight
    * Length: compares the ships length
    * Width:  compares the ships width
    * Draft:  compares the ships draft
    **/
   
   public String getInfo() 
   {
	   switch(Ship.compareWith)	// start of switch statement
	   {
		   case WEIGHT: 	// case 1 WEIGHT
			   
			   return String.valueOf("\nWeight: " + weight);	// returns value of weight
			   
		   case LENGTH: 	// case 2 LENGTH
			   
			   return String.valueOf("\nLength: " + length);	// returns value of length
			   
		   case WIDTH:  	// case 3 WIDTH
			   
			   return String.valueOf("\nWidth: "  + width);		// returns value of width
			   
		   case DRAFT:  	// case 4 DRAFT
			   
			   return String.valueOf("\nDraft: "  + draft);		// returns value of draft
	   }
	   return "";	// return empty string
   }
} 


