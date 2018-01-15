
/**
 *  Anthony Borza
 *  World.java
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JPanel;


public class World extends Thing
{
	private ArrayList<SeaPort> ports = new ArrayList<SeaPort>();	// SeaPort arrayList ports
	public ArrayList<Ship> ships = new ArrayList<Ship>();			// Ship arrayList ships
	public ArrayList<Person> persons = new ArrayList<Person>();		// Person arrayList persons
	
	public PortTime time;						// PorTime time
	public boolean isJob;						// boolean isJob
    HashMap<Integer, Ship> hmShips;				// HashMap hmShips for Ship
    HashMap<Integer, Job> hmJobs;				// HashMap hmJobs for Job
	private JPanel parent = new JPanel();		// new JPanel parent
	Job jobs;									// jobs calls Job class
	
	//-------------------------------------------- World Method ------------------------------------------------//	
	   
	   /**
	    * World constructor with scanner: allows various types of data to be 
	    * read in. 
	    * @param sc
	    */
	    
	    public World(Scanner sc)
	    {
	 	   super(sc);	// calls parent class constructor
	 	   isJob = false;											// isJob is false
		   setTime(new PortTime(0));								// setPortime to 0
	 	   HashMap<Integer, SeaPort> hmPorts = new HashMap<>();		// HashMap hmPorts for SeaPort
	 	   HashMap<Integer, Dock> hmDocks = new HashMap<>();		// HashMap hmDocks for Dock
           hmShips = new HashMap<>();								// HashMap hmShips for Ship
           hmJobs = new HashMap<>();								// HashMap hmJobs for Job

	 	   while(sc.hasNextLine())		// Returns true if there is another line in the input of this scanner
	 	   {
	 		   process(sc.nextLine(), hmPorts, hmDocks);	// calls the process method and scans each line

	 	   }
	 	   assignJobs();				// calls assignJobs method
	 	   assignPersonsToJobs();		// calls assignPersonsToJobs method
	    }
	    
	 	   
	//--------------------------------------- Set Methods --------------------------------------------------//
	    
	    /**
	     * setTime method: is used to modify the variable time
	     * @param time
	     */
	    
	    public void setTime(PortTime time)
		{
			this.time = time;	// reference to time
		}

		/**
		 * setPorts method: method: is used to modify the variable ports
		 * @param ports
		 */
		
		public void setPorts(ArrayList<SeaPort> ports)
		{
			this.ports = ports; 	// reference to ports
		}
		
	  //--------------------------------------- Get Method --------------------------------------------------//
		
	    /**
	     * getPorts() method: is used to access the variable ports
	     * @return ports
	     */
		
		public ArrayList<SeaPort> getPorts() 
		{
			return ports;	// return ports
		}

	//-------------------------------------------- Process Method ------------------------------------------------//	
		
		/**
	     * process Method: is a method that scans through the file using
	     * a switch statement for the following cases:
	     * port:   replaces with Port, by calling the addPort method
	     * dock:   replace with Dock, by calling the addDock method
	     * cship:  replaces with Cargo ship, by calling the addCargoShip method
	     * pship:  replaces with Passenger ship, by calling the addPassengerShip method
	     * person: replaces with Person, by calling the addPerson method
	     * @param st
	     * @param hmPorts
	     * @param hmDocks
	     */
	    
	    @SuppressWarnings("resource")
        public void process(String st, HashMap<Integer, SeaPort> hmPorts, HashMap<Integer, Dock> hmDocks) 
	    {
	 	   //System.out.println("Processing >" + st + "<"); 	// print statement
	 	   
	 	   Scanner sc = new Scanner(st); 	// creates a new scanner
	 	   
	 	   if(!sc.hasNext())	// if there is nothing to scan
	 	   {
	 		   return;			// simply return
	 	   }
	 	   switch(sc.next())	// switch statement enabled with scanning the file
	 	   {
	 	   		case "port": addPort(sc, hmPorts);	break;						// if port is detected in file calls the addPort method
	 	   		case "dock": addDock(sc, hmPorts, hmDocks);	break;				// if dock is detected in file calls the addDock method
	 	   		case "cship": addCargoShip(sc, hmPorts, hmDocks);	 break;		// if cship is detected in file calls the addCargoShip method
	 	   		case "pship": addPassengerShip(sc, hmPorts, hmDocks); break;	// if pship is detected in file calls the addPassengerShip method
	 	   		case "person": addPerson(sc, hmPorts);	break;				    // if person is detected in file calls the addPerson method
	 	   		case "job": addJob(sc);break;									// if job is detected in file calls the addJob method
	 	   		default: break;	
	 	   }
	 	   
	    }

	//----------------------------------------------Add Methods ------------------------------------------------//	  

	    /**
	     * addJob method: assigns jobs to a ship
	     * @param sc
	     */
	    
	    public void addJob(Scanner sc)
	    {
	    	Job job = new Job(hmShips, parent, sc);		// calling Job class
	    	hmJobs.put(job.getIndex(), job);			// associates the specified value with the specified key in this map
	    	assignJob(job);								// calls the assignJob method
	    }
	    
	    /**
	    * addPerson Method: implements a HashMap for SeaPort 
	    * @param sc
	    * @param hmPorts
	    */
	    
	    public void addPerson(Scanner sc, HashMap<Integer, SeaPort> hmPorts)
	    {
	 	   Person person = new Person(sc);		// calling Person class
	 	   SeaPort seaPort = getSeaPortByIndex(person.getParent(), hmPorts);	// calls getSeaPortByIndex, gets parent from class thing, and implements SeaPort HashMap
	 	   
	 	   if(seaPort != null) 					 // null check
	 	   {
	 		   seaPort.getPerson().add(person);	 // add person
	 	   }
	    }

	   /**
	    * addCargoShip Method: implements a HashMap for SeaPort and Dock
	    * @param sc
	    * @param hmPorts
	    * @param hmDocks
	    */
	    
	    public Ship addCargoShip(Scanner sc, HashMap<Integer, SeaPort> hmPorts, HashMap<Integer, Dock> hmDocks)
	    {
	 		CargoShip cs = new CargoShip(sc);			// calling CargoShip class
            hmShips.put(cs.getIndex(), cs);				// associates the specified value with the specified key in this map
	 		assignShip(cs, hmPorts, hmDocks);			// calls the assignShip method and assigns it to a cargo ship
	 		return cs;									// return cs
	    }
	    
	    /**
	     * addPassengerShip Method: implements a HashMap for SeaPort and Dock
	     * @param sc
	     * @param hmPorts
	     * @param hmDocks
	     */
	    
	    public Ship addPassengerShip(Scanner sc, HashMap<Integer, SeaPort> hmPorts, HashMap<Integer, Dock> hmDocks)
	    {
	 	    PassengerShip ps = new PassengerShip(sc);	// calling PassengerShip class
            hmShips.put(ps.getIndex(), ps);				// associates the specified value with the specified key in this map
	 		assignShip(ps, hmPorts, hmDocks);			// calls the assignShip method and assigns it to a passenger ship
	 		return ps;									// return ps
	    }

	   /**
	    * addDock Method: implements a HashMap for SeaPort and Dock
	    * @param sc
	    * @param hmPorts
	    * @param hmDocks
	    */
	    
	    public void addDock(Scanner sc, HashMap<Integer, SeaPort> hmPorts, HashMap<Integer, Dock> hmDocks)
	    {
	 	   Dock dock = new Dock(sc);			// calling Dock class
	 	   hmDocks.put(dock.getIndex(), dock);	// associates the specified value with the specified key in this map
	 	   
	 	   SeaPort seaPort = getSeaPortByIndex(dock.getParent(), hmPorts);	// calls getSeaPortByIndex, gets parent from class thing, and implements SeaPort HashMap
	 	   
	 	   if(seaPort != null) 						// null check
	 	   {
	 		   seaPort.getDocks().add(dock);		// add dock
	 	   }
	    }

	   /**
	    * addPort Method: implements a HashMap for SeaPort
	    * @param sc
	    * @param hmPorts
	    */
	    
	    public void addPort(Scanner sc, HashMap<Integer, SeaPort> hmPorts) 
	    {
	 	   SeaPort seaPort = new SeaPort(sc);			// calling SeaPort class
	 	   hmPorts.put(seaPort.getIndex(), seaPort);	// associates the specified value with the specified key in this map
	 	   getPorts().add(seaPort);						// add a port
	    }
	    

	 //-------------------------------------------- Assign Methods ------------------------------------------------//	
	    
		/**
		 * assignShip Method: used to assign ship types, such as Cargo and passenger ship, 
	     * and then display all ships, and ships in the que. It is also used with the
	     * addCargoShip, and addPassengerShip methods above. Implements a HashMap for
	     * SeaPort and Dock
		 * @param ms
		 * @param hmPorts
		 * @param hmDocks
		 */
	    
	    public void assignShip(Ship ms, HashMap<Integer, SeaPort> hmPorts, HashMap<Integer, Dock> hmDocks) 
	    {
	 	    Dock d = getDockByIndex(ms.getParent(), hmDocks);	// calls getDockByIndex, gets  parent from the class thing, and implements Dock HashMap
	 	    
	 	    if(d == null)		 // null check
	 	    {
	 	       SeaPort p = getSeaPortByIndex(ms.getParent(), hmPorts);	// calls getSeaPortByIndex, gets parent from class thing, and implements SeaPort HashMap
	 	       
	 	       if(p != null) 	// null check
	 	       {
	 	          p.getShips().add(ms);	// adds to array list ships in SeaPort class
	 	          p.getQue().add(ms);	// adds to array list que in SeaPort class
	 	       }
	 	         return;				// return
	 	      }
	 	    else  // else do the following
	 	    {
	 	      d.ship = ms;
	 	      getSeaPortByIndex(d.getParent(), hmPorts).getShips().add(ms);	// calls getSeaPortByIndex, gets parent, implements SeaPort HashMap, adds it to ships array list in SeaPort class 
	 	    }
	    } 
	    
	    /**
	     * assignJob method: used to assign Jobs to ships, such as Cargo and passenger ship, 
	     * and then display all ships, with their current Job. 
	     * @param job
	     */
	    
		public void assignJob(Job job) 
		{
			Ship ship = getShipByIndex(job.getParent(), hmShips);	// calls getShipByIndex, gets parent from class thing, and implements Ship HashMap
			ship.getJobs().add(job);								// calls getJob ArrayList method from Ship class and adds job
		}
		
		
	    /**
	     * assignJobs method: assigns a job to a ship in that specified port
	     */
		
	    private void assignJobs() 
	    {
	       for(SeaPort port: ports) 		// for each element port of type SeaPort in array ports
	       {
	          for(Ship ship: port.ships) 	// for each element ship of type Ship in array port.ships
	          {
	             for(Job job: ship.jobs) 	// for each element job of type Job in array ships.job
	             {
	                if(port.jobs.contains(job) == false)	// if this list contains the specified element, and is equal to false
	                {
	                   port.jobs.add(job);					// add a new job to that specified port
	                }
	             }
	          }
	          for(Ship ship: port.que) 		// for each element ship of type Ship in array port.que
	          {
                for(Job job: ship.jobs) 	// for each element job of type Job in array ships.jobs
                {
                   if(port.jobs.contains(job) == false) 	// if this list contains the specified element, and is equal to false
                   {
                      port.jobs.add(job);					// add a new job to that specified port
                   }
                }
             }
	       }
	    }
	    
	    /**
	     * assignPersonsToJobs method: assigns a person to a job with their skill
	     */
	    
	    private void assignPersonsToJobs() 
	    {
	       for(SeaPort port: ports) 				// for each element port of type SeaPort in array ports
	       {
	          for(Job job: port.jobs) 				// for each element job of type Job in array ships.jobs
	          {
	             for(Person person: port.persons) 	// for each element person of type Person in array port.persons
	             {
	                for(Skill skill: person.skills) // for each element skill of type Skill in array persons.skill
	                {
	                   if(job.requires(skill))		// if job requires skill
	                   {
	                      if(job.persons.contains(person) == false)	  // if this list contains the specified element, and is equal to false
	                      {
	                         job.persons.add(person);				  // add a person to that specified job with their specified skill
	                      }
	                   }
	                }
	             }
	          }
	       }
	    }

	 //-------------------------------------------- Get Methods ------------------------------------------------//	
	 	
		 /**
		  * getShipByIndex Method: gets the index value of the ship. Implements a 
		  * HashMap for Ship
		  * @param x
		  * @param hmShips
		  * @return hmShips.get(x)
		  */
	    
		   public Ship getShipByIndex(int x, HashMap<Integer, Ship> hmShips) 
		   {
		 	   return hmShips.get(x);	// gets value for the specified key that is mapped, or null if no mapping for key 
		   } 
	    
		/**
		 * getDockByIndex Method: gets the index value of the dock. Implements a 
		 * HashMap for Dock
		 * @param x
		 * @param hmDocks
		 * @return hmDocks.get(x)
		 */
	   
		   public Dock getDockByIndex(int x, HashMap<Integer, Dock> hmDocks)
		   {
		        return hmDocks.get(x);	// gets value for the specified key that is mapped, or null if no mapping for key 
		   }
	    
		/**
		 * getSeaPortByIndex Method: gets the index value of the port. Implements a 
		 * HashMap for SeaPort
		 * @param x
		 * @param hmPorts
		 * @return hmPorts.get(x)
		 */
		    
		   public SeaPort getSeaPortByIndex(int x, HashMap<Integer, SeaPort> hmPorts)
		   {
		 	  return hmPorts.get(x);	// gets value for the specified key that is mapped, or null if no mapping for key 
		   }

		/**
		 * getJobByIndex Method: gets the index value of the Job. Implements a 
		 * HashMap for Job
		 * @param x
		 * @param hmJobs
		 * @return hmJobs.get(x)
		 */
		    
		   public Job getJobByIndex(int x, HashMap<Integer, Job> hmJobs) 
		   {
			   return hmJobs.get(x);	// gets value for the specified key that is mapped, or null if no mapping for key 
		   }
		   
		/**
		 * getJobByName Method: gets the name of the Job. Implements a 
		 * HashMap for Job
		 * @param x
		 * @param hmJobs
		 * @return hmJobs.get(name)
		 */
		   
		   public Job getJobByName(String name, HashMap<Integer, Job> hmJobs)
		   {
			   return hmJobs.get(name);	 // gets value for the specified key that is mapped, or null if no mapping for key 
		   }
		   
		   /**
		    * getPersonByName method: gets the name of the person. Implements a
		    * HashMap for Person
		    * @param name
		    * @param hmPerson
		    * @return hmPerson.get(name)
		    */
		   
		   public Person getPersonByName(String name, HashMap<Integer, Person> hmPerson)
		   {
			   return hmPerson.get(name);  // gets value for the specified key that is mapped, or null if no mapping for key 
		   }
		   
	//-------------------------------------------- Search Methods ------------------------------------------------//	

	/**
	 * nameSearch method: The purpose of this method is to do the following:
	 * Search the port name
	 * Search the docks name
	 * Search the ships name
	 * Search the persons name
	 * @param nameTarget
	 * @return "Sorry, that is an invalid search. Please try again."
	 */

	public String searchName(String nameTarget) 
	{
		String st = "";											// st declared as a string

		for(SeaPort sp: getPorts()) 							// for each element sp of type SeaPort in array ports
		{
			if(sp.getName().equalsIgnoreCase(nameTarget))  		// if name is equal to the desired name target
			{
				st += sp.toString() + '\n';						// return the name to a string
			}
			for(Dock d: sp.getDocks())							// for each element d of type Dock in array docks
			{
				if(d.getName().equalsIgnoreCase(nameTarget)) 	// if name is equal to the desired name target
				{
					st += d.toString() + '\n';					// used to display names that may be the same 
				}
			}
			for(Ship s: sp.getShips()) 							// for each element s of type Ship in array ships
			{
				if(s.getName().equalsIgnoreCase(nameTarget)) 	// if name is equal to the desired name target
				{
					st += s.toString() + '\n';					// used to display names that may be the same 
				}
			}
			for(Person p: sp.getPerson()) 						// for each element p of type Person in array persons
			{
				if(p.getName().equalsIgnoreCase(nameTarget)) 	// if name is equal to the desired name target
				{
					st += p.toString() + '\n';					// used to display names that may be the same 
				}
			}
			return st;	// return st
		}
		return "Sorry, that is an invalid search. Please try again.";	// return if user enters in invalid search
	} 

	/**
	 * indexSearch method:The purpose of this method is to do the following:
	 * Search the port index
	 * Search the docks index
	 * Search the ships index
	 * Search the persons index
	 * @param indexTarget
	 * @return "Sorry, that is an invalid search. Please try again."
	 */

	public String indexSearch(String input)
	{
		int indexTarget = 0;		// indexTarget is declared as an integer and assigned 0
		String st = "";				// st declared as a String

		try // exception handling
		{
			indexTarget = Integer.parseInt(input);				// parses the string input as a signed decimal integer
		} 
		catch(NumberFormatException nfe) 						// catches exception if necessary
		{
			System.out.println(nfe.toString());					// prints nfe to  a string
			return "Sorry, that is an invalid index search\n";  // return if user enters in invalid search
		}
		for(SeaPort sp: getPorts()) 					// for each element sp of type SeaPort in array ports
		{
			if(sp.getIndex() == indexTarget)			// if index is equal to the desired name target
			{
				st += sp.toString() + '\n';				// used to display index names that may be the same 
			}
			for(Dock d: sp.getDocks())					// for each element d of type Dock in array docks
			{
				if(d.getIndex() == indexTarget) 		// if index is equal to the desired name target
				{
					st += d.toString() + '\n'; 			// used to display index names that may be the same 
				}
			}
			for(Ship s: sp.getShips()) 					// for each element s of type Ship in array ships
			{
				if(s.getIndex() == indexTarget) 		// if index is equal to the desired name target
				{
					st += s.toString() + '\n'; 			// used to display index names that may be the same 
				}
			}
			for(Person p: sp.getPerson()) 				// for each element p of type Person in array persons
			{
				if(p.getIndex() == indexTarget) 		// if index is equal to the desired name target
				{
					st += p.toString() + '\n';			// used to display index names that may be the same 
				}
			}
			return st;	// return st
		}
		return "Sorry, that is an invalid search. Please try again.\n";	// return if user enters in invalid search
	}

	/**
	 * searchKind method:The purpose of this method is to do the following:
	 * Search the persons skill
	 * @param skillTarget
	 * @return "Sorry, that is an invalid search. Please try again."
	 */

	public String skillSearch(String skillTarget)
	{
		
		String st = "";	 // st declared as a String
		
		for(SeaPort sp: getPorts()) 								// for each element sp of type SeaPort in array ports
		{
			for(Person p: sp.getPerson()) 							// for each element p of type Person in array persons
			{
				for(Skill s: p.getSkills()) 						// for each element s of type Skills in array skills
				{ 
				   if(s.getName().equalsIgnoreCase(skillTarget))	// if skill is equal to the desired name target
				   {
				      st += p.toString() + '\n';					// used to display skills that may be the same
				   }
				}
			}
			return st;	// return st		
		}
		return "Sorry, that is an invalid search. Please try again.";	// return if user enters in invalid search
	} 
	
	//-------------------------------------------- Sort Method ------------------------------------------------//	

	/** 
	 * Sorting Method: uses to sort in ascending order by:
	 * Ships weight
	 * Ships length
	 * Ships width
	 * Ships draft
	 * @param sortElement
	 * @return result
	 */
	
	public void setSortParameter(int param) 
	{
		Ship.compareWith = param;	// calls switch statement in Ship class
	}
	
	public String sort()
	{
		String str = "";						// str declared as a string
		
		for(SeaPort sp : getPorts()) 			// for each element sp of type SeaPort in array ports
		{
			Collections.sort(sp.getShips());	// sorts ships by weight, length, width, and draft in ascending order
		
			for(Ship ship: sp.getShips()) 		// for each element ship of type Ship in array ships
			{
				str += ((Thing)ship).toString() + "   " + ship.getInfo() + "\n\n";	// returns value of weight, length, width, and draft as a string
			}
		}		
		return str;		// return str 
	}
	
	//-------------------------------------------- toString Method ------------------------------------------------//	

	 /** toString Method:The toString() method is used when we need a string representation of an object. 
	  *  It is defined in Object class
	  *  @return str
	  */

	public String toString()
	{
		String str = "\n>>>>> The World: ";		// simply prints this at the top of the textArea 

		for(SeaPort sp: getPorts())				// for each element sp of type SeaPort in array ports
		{
			str += sp.toString() + "\n";		// displays sp as a string with a line break
		}
		return str;								// return str
	}

}
