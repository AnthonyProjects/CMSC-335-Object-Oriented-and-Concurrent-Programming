
/**
 *  Anthony Borza
 *   Person.java
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Person extends Thing
{

   public ArrayList<Skill> skills = new ArrayList<>();	// ArrayList skills
   public boolean busyFlag;								// busyFlag declared as a boolean
  
 //-------------------------------------------- Person Method ------------------------------------------------//	
   
   /**
    * Constructor with Scanner: allows various types of data to be 
    * read in.  
    * @param sc
    */
    
	public Person(Scanner sc)
	{
	   super(sc);				// calls parent class constructor
	   
	   if(sc.hasNext())			// returns true if this scanner has another token in its input.
	   {
		  setSkill(sc.next());	// calls the setSkill method
	   }
	}
	
	//-------------------------------------------- Get Method ------------------------------------------------//
	
	/**
	 * getSkills() method: is used to access the variable skills
	 * @return skills
	 */
	
	public ArrayList<Skill> getSkills()
	{
		return skills;	// return skills
	}

	//-------------------------------------------- Set Method ------------------------------------------------//
	/**
	 * setSkill method: is used to modify the variable skill
	 * @param skill
	 */
	
	public void setSkill(String skill) 
	{
		if(skills.contains(skill) == false)		// if skills contains the specified element skill, and is false
		{
			skills.add(new Skill(skill));		// add a new skill
		}
	}
	
	//-------------------------------------------- capableOf Method -------------------------------------------//
	
	/**
	 * capableOf method
	 * @param skill
	 * @return false, true, and false
	 */
	
	public boolean capableOf(Skill skill) 
	{
	   if(skills == null)	// if skills is equal to null (null check)
	   {
		   return false;	// return false
	   }
	   for(Skill s: skills) 						// for each element s of type Skill in array skills
	   {
	      if(s.getName().equals(skill.getName()))	// if name is equal to name
	      {
	         return true;	// return true
	      }
	   }
	   return false;		// return false
	}
	
   //-------------------------------------------- toString Method ------------------------------------------------//

   /**
	*  toString Method:The toString() method is used when we need a string representation of an object. 
	*  It is defined in Object class
	*/
	   
   public String toString ()
   {
      String sk = "";				// sk declared as a string
      
      for(Skill s: skills)			// for each element s of type Skill in array skills
      {
         sk += s.getName() + ", ";	// gets name 
      }
      
      if(sk.length() > 0) 			// if the length of ski is greater than zero
      {
         sk = sk.substring(0, sk.length()-2);					// returns a string that is a substring of this string. 
      }
      return "Person: " + "  " + super.toString() + "  " + sk;	// return Person, name of person, index value, and skill type as a string
   } 
} 