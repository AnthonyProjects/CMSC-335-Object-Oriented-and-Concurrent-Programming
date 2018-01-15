
/**
 *  Anthony Borza
 *  PortTime.java
 */

public class PortTime implements Comparable<PortTime>
{
   private int time; // time declared as an integer, and measured in seconds
   
  //-------------------------------------------- PortTime Method ------------------------------------------------//	
   
   /**
    * PortTime constructor: 
    * @param t
    */
   
   public PortTime(int time)
   {
     super();				// calls parent class constructor
     this.time = time;		// reference to  time
   }
   
   
   //-------------------------------------------- get and set Methods ------------------------------------------------//	
   
   /**
    * getTime method: is used to access the variable time
    * @return time
    */
   
	public int getTime() 
	{
		return time;	// return time
	}
	
	/**
	 * setTime method: is used to modify the variable time 
	 * @param time
	 */
	
	public void setTime(int time) 
	{
		this.time = time;	// reference to time
	}
	
   //-------------------------------------------- compareTo Method ------------------------------------------------//	
   
	/**
	 * compareTo method: compares time to other time
	 * @param other
	 */
	public int compareTo(PortTime other) 
	{
		return time - other.time;	// return the compared time to other time
	} 

}
