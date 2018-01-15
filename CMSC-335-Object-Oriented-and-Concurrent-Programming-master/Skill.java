
/**
 *  Anthony Borza
 *  Skill.java
 */

public class Skill
{
	private String name;	// name declared as a string
	
	//-------------------------------------------- Skill Method ------------------------------------------------//	
	
	/**
	 * Skill constructor: takes 'n' as its parameter and is a string
	 * @param n
	 */
	
	public Skill(String n) 
	{
	     name = n;
	}
	
    //-------------------------------------------- Get and Set Methods------------------------------------------------//	
	
	/**
	 * getName()  method: is used to access the variable name
	 * @return name
	 */
	
	public String getName() 
	{
	   return name;		// return name
	}
	
	/**
	 * setName  method: is used to modify the variable n
	 * @param n
	 */
	
	public void setName(String n) 
	{
	   name = n;
	}

   //-------------------------------------------- toString Method ------------------------------------------------//

   /**
	*  toString Method:The toString() method is used when we need a string representation of an object. 
	*  It is defined in Object class
	*/
	   
	public String toString()
	{
	   return name;	// return name
	}
}
