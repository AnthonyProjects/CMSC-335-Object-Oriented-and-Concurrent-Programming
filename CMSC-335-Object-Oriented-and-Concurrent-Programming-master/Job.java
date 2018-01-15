
/**
 *  Anthony Borza
 *  Job.java
 * The purpose of this class is implement threads for each job representing a task that ship requires.
 * It then uses synchronized directive to avoid race conditions and insures that a dock is performing a
 * job for only one ship at a time. When all of the jobs for a ship are done,the ship will then leave the
 * dock, allowing a ship from the que to dock. Once the ship has docked, all of the ships jobs should then 
 * progress. The Thread for each job is started as the job is read in from the data file. The class also 
 * implements delays to simulate the progress of each job; as well as, using a ProgressBar for each job to 
 * display the progress of that job. Lastly, it uses JButtons that allow the job to be suspended or cancelled. 
 */


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Job extends Thing implements Runnable
{	
   private ArrayList<Skill> skills = new ArrayList<>();		// ArrayList skills
   public ArrayList<Person> persons = new ArrayList<>(); 	// ArrayList persons	
   private double duration;									// duration declared as a double
   private Thing worker = null;								// worker declared as null
   String jobName;											// jobName declared as a string
   private long jobTime;									// jobTime declared as a long
   JProgressBar pb = new JProgressBar ();					// pb declared as ProgressBar
   JLabel label = new JLabel();								// new JLabel named label
   private boolean goFlag = true;							// goFlag assigned as a boolean and declared true
   private boolean noKillFlag = true;						// noKillFlag assigned as a boolean and declared true
   public JButton goButton = new JButton("Stop");			// goButton declared as a JButton and named Stop
   public JButton killButton = new JButton("Cancel");		// killButton declared as a JButton and named Cancel
   Status status = Status.SUSPENDED;						// status 
   enum Status{RUNNING, SUSPENDED, WAITING, DONE};			// used to define a collection of constants to be used


   //--------------------------------------------------------- Job Method --------------------------------------------------//

   /**
    * Job class: implements a HashMap for Ship, a JPanel for parent, and a Scanner for sc
    * @param hmShips
    * @param parent
    * @param sc
    */

   public Job(HashMap<Integer, Ship> hmShips, JPanel parent, Scanner sc) 
   {
      super(sc);							// calls parent class constructor

      if(sc.hasNextDouble())				// returns true if the next token in this scanner's input can be interpreted as a double value
      {
         this.duration = sc.nextDouble();	// scans the next token of the input as a double
      }
      while(sc.hasNext())					// returns true if this scanner has another token in its input
      {
         skills.add(new Skill(sc.next()));	// finds and returns the next complete token from this scanner
      }

      jobName =  this.getName();						// calls the getName method from Thing class
      jobTime = (long) duration;						// duration is being casted as a long, and then jobTime is using it
      pb.setStringPainted(true);						// Sets the value of the stringPainted property, which determines whether the progress bar should render a progress string.
      worker = (Thing) (hmShips.get(this.getParent()));	// gets the getParent method from class Thing
      SeaPortProgram.panel4.add(StatusPanel());			// adds StatusPanel to panel4 in the SeaPortProgram.java class


      //----------------------------------------------------- ActionListener Methods --------------------------------------------------//

      /** 
       * ActionListener for goButton
       */

      goButton.addActionListener(new ActionListener() 		// Adds action listener to the button goButton
            {
         public void actionPerformed(ActionEvent e) 		// is used when the action is performed
         {
            toogleGoFlag();		// when button is pressed it performs the tasks in the toogleGoFlag method
         }
            });

      /** 
       * ActionListener for killButton
       */

      killButton.addActionListener(new ActionListener() 	// Adds action listener to the button killButton
            {
         public void actionPerformed(ActionEvent e) 		// is used when the action is performed
         {
            setKillFlag();		// when button is pressed it performs the tasks in the setKillFlag method
         }
            });

      new Thread(this).start();	// allocates a new thread object for Job, and causes the thread to begin execution

   } 


   //-------------------------------------------------------- JPanel Method --------------------------------------------------//

   /**
    * StatusPanel added to GUI: The purpose of this method is to add a new panel 
    * to the current GUI using GridLayout.
    * @return panel
    */

   public JPanel StatusPanel() 
   {
      JPanel panel = new JPanel(new GridLayout(4,1));					// creates a grid layout with the specified number of rows (3) and columns (2)
      panel.setBackground(Color.CYAN);									// sets background to the color Cyan
      panel.add(new JLabel(worker.getName(), SwingConstants.CENTER));	// centers name of ship
      panel.add(pb);													// adds progress bar to panel
      panel.add(goButton);												// adds goButton to panel
      panel.add(killButton);											// adds killButton to panel
      return panel;														// returns panel
   }


   //--------------------------------------------------------- toogleGoFlag, and setKillFlag Methods --------------------------------------------------//

   /** 
    * toogleGoFlag method
    */

   public void toogleGoFlag()
   {
      goFlag = ! goFlag;
   }

   /** 
    * setKillFlag method 
    */

   public void setKillFlag()
   {
      noKillFlag = false;						
      killButton.setBackground(Color.red);	// sets the background color of the button killButton to red
   }

   //--------------------------------------------------------- showStatus Method --------------------------------------------------//
   /**
    * showStatus Method: The purpose of this method is to use a switch statement
    * to determine the status of a job or jobs being done on one of the ships. 
    * If the job is running it will be green and say Running, If the job is Suspended
    * it will be yellow and say Suspended, If the job is waiting it will be orange
    * and say Waiting, and If the job is Done it will be red and say Done. 
    * @param st
    */

   public void showStatus(Status st)
   {

      status = st;
      switch(status)  	// start of switch statement
      {
      case RUNNING:	
         System.out.println("Status of Job: " + name + " is Running.");	// shows the name of job, and status as Running
         goButton.setBackground(Color.green);		// sets the background of the goButton to green
         goButton.setText("Running");				// sets the text of the goButton to Running
         break;										// break

      case SUSPENDED:
         System.out.println(name + " Suspended.");	// shows the name of job, and status as Suspended
         goButton.setBackground(Color.yellow);		// sets the background of the goButton to yellow
         goButton.setText("Suspended");				// sets the text of the goButton to Suspended
         break;										// break

      case WAITING:
         System.out.println(name + " Waiting.");	// shows the name of job, and status as Waiting
         goButton.setBackground(Color.orange);		// sets the background of the goButton to orange
         goButton.setText("Waiting turn");			// sets the text of the goButton to Waiting
         break;										// break

      case DONE:
         System.out.println(name + " Done.");		// shows the name of job, and status as Done
         goButton.setBackground(Color.red);			// sets the background of the goButton to red
         goButton.setText("Done");					// sets the text of the goButton to Done
         break;										// break

      } 
   } 

   //--------------------------------------------------------- run Method --------------------------------------------------//

   /**
    * run method: This method does not return anything as it is meant to contain the
    * code executed in the thread. The purpose of this method is to run the implemented threads
    * for each job representing a task that ship requires. It then uses synchronized directive to 
    * avoid race conditions and insures that a dock is performing a job for only one ship at a time.
    */
   public void run() 
   {
      long time = System.currentTimeMillis();	// time is set to return the current time in milliseconds.
      long startTime = time;					// startTime becomes current time in milliseconds
      long stopTime = time + 1000 * jobTime;	// stopTime  time plus 1000 multiplied by jobTime
      double duration = stopTime - time;		// duration is stopTime minus time

      synchronized(worker) // is the used to execute two or more threads that share critical resources (in our cases, ships, and jobs)
      { 											
         while(worker.busyFlag && isResourcesAvailable()) 	// while job has not been started and calls isResourceAvailable method
         {
            showStatus(Status.WAITING);						// show the status as waiting

            try 	// exception handling
            {
               worker.wait();								// causes the current thread to wait until another thread invokes the notify() method
            }
            catch(InterruptedException e)					// catches if thread is waiting, sleeping, or otherwise occupied
            {
               System.out.println("Interrupted" + e);		// prints out exception	
            }

         } 
         markResources(true);				// calls MarkResources method
         worker.busyFlag = true;			// job is in progress 
      } 
      while(time < stopTime && noKillFlag)	// while time is less than the stopTime and true
      {
         try 	// exception handling
         {
            Thread.sleep(100);						// temporarily cease execution for 100 milliseconds
         } 
         catch(InterruptedException e) 				// catches if thread is waiting, sleeping, or otherwise occupied
         {
            System.out.println("Interrupted" + e);	// prints out exception	
         }	

         if(goFlag)   // if goFlag (True)
         {
            showStatus(Status.RUNNING);										// show status of the job as running
            time += 100;													// sets time to currentMilliseconds
            pb.setValue((int)(((time - startTime) / duration) * 100));		// sets progress bar percentage

         } 
         else 		// else
         {
            showStatus(Status.SUSPENDED);	// show job as suspended
         } 
      } 

      pb.setValue(100);				// set value to 100%
      showStatus(Status.DONE);		// show status as done

      synchronized(worker) 			// is the used to execute two or more threads that share critical resources (in our cases, ships, and jobs)
      {
         markResources(false);		// calls markResource method
         worker.busyFlag = false;	
         worker.notifyAll();		// Wakes up all threads that are waiting on this object's monitor (worker.wait)
      }
   }

   
   //--------------------------------------------------------- isResourcesAvailable Method --------------------------------------------------// 
   
   /**
    * isResourcesAvailable method: checks to see if any resources are available (skills) for that particular job. 
    * It then checks to see if there are people with that specified skill for that job. 
    * @return true
    */
   
   public boolean isResourcesAvailable() 
   {
      if(skills.isEmpty())		// if there are no skills
      {
    	  return true;			// return true
      }   
      Map<String, Boolean> available = new HashMap<>();		// available data type String, and Boolean
      
      for(Skill skill: skills) 								// for each element skill of type Skill in array skills
      {
         available.put(skill.getName(), false);				// puts name of skill with the specific key within the map
      }
      for(Person person: persons)							// for each element person of type Person in array persons
      {
         if(person.busyFlag == false)						// if person.busyFlag is false
         {
            for(Skill skill: person.skills) 				// for each element skill of type Skill in array person.skills
            {
               if(requires(skill))							// calls require method to determine if that skill is avialble for that person
               {
                  available.put(skill.getName(), true);		// puts name of skill with the specific key within the map
               }
            }
         }
      }
      for(String skill: available.keySet()) 				// for each element skill of type String in array avail.key 
      {
         if(available.get(skill) == false)					// if false
         {
            return false;									// return false
         }
      }
      return true;											// return true
   }
   
   //-------------------------------------------- markResources Method --------------------------------------------------// 

   /**
    * markResources method: if it is busy it is true, and will mark resources as busy
    * @param busy
    */
   
   public void markResources(boolean busy) 
   {
      if(skills.isEmpty())								 // if skills contains no elements
      {
    	  return;										 // simply returns
      }
      Map<String, Boolean> available = new HashMap<>();	 // HashMap available data type String, and Boolean

      for(Skill skill: skills) 							 // for each element skill of type Skill in array skills
      {
         available.put(skill.getName(), false);			 // puts name of skill with the specific key within the map
      }
      for(Skill skill: skills) 							 // for each element skill of type Skill in array skills
      {
         if(available.get(skill.getName()) == true)		 // if  name of skill with the specific key within the map is true
         {
        	 continue;									 // continue execution
         }
         for(Person person: persons) 					 // for each element person of type Person in array persons
         {
            if(person.capableOf(skill)) 				 // if person is capableOf that skill call the capableOf method
            {
               if(person.busyFlag != busy)				 // if not equal to busy
               {
                  person.busyFlag = busy;				
                  available.put(skill.getName(), true);	 // puts name of skill with the specific key within the map
                  
                  for(Skill sk: person.skills) 			 // for each element sk of type Skill in array person.skill
                  {
                     if(available.get(sk.getName()) == false) 	// if name of skill with the specific key within the map is true
                     {
                        available.put(sk.getName(), true);		// puts name of skill with the specific key within the map
                     }
                  }
               }
            }
         }
      }
   }
 
   //-----------------------------------------------------requires Method --------------------------------------------------// 
   
   /**
    * requires method: is used to determine if that skill is required. If it is it will get 
    * the name of that skill, and return it (true). If the skill is equal to null (null check) it
    * will not return anything (false).
    * @param skill
    * @return false, true, and false
    */
   
   public boolean requires(Skill skill)
   {
      if(skills == null)		// if skills is equal to null (null check)
      {
    	  return false;			// return false
      }
      for(Skill sk: skills)  	// for each element sk of type Skill in array skills
      {
         if(sk.getName().equals(skill.getName())) // if name is equal to name
         {
            return true;		// return true
         }
      }
      return false;				// return false
   }


   //--------------------------------------------------------- Add Method --------------------------------------------------//

   /** 
    * addSkill method: adds a new skill by calling the Skill class
    * @param skill
    */

   public void addSkill(String skill)
   {
      skills.add(new Skill(skill));		// adds skill
   }
 
   //--------------------------------------------------------- Get Methods --------------------------------------------------//
  
   /**
    * getDuration() method: is used to access the variable duration
    * @return duration
    */

   public double getDuration()
   {
      return duration;			// return duration
   }

   /** 
    * getSkill() method: is used to access the variable skills
    * @return skills
    */

   public ArrayList<Skill> getSkill()
   {
      return skills;			 // return skills
   }

   /** 
    * getPerson() method: is used to access the variable persons
    * @return skills
    */
   
   public ArrayList<Person> getPerson()
   {
      return persons;			// return persons
   }

   //--------------------------------------------------------- toString Method --------------------------------------------------//

   /** toString Method:The toString() method is used when we need a string representation of an object. 
    *  It is defined in Object class
    */

   public String toString()
   { 
      String st = "Job: " + super.toString() +  "  " +  duration + "\n       - Resources Available:  "  + getSkill() +  "\n       - People Available: " + "  " + getPerson() + "\n";	
      return st;	// return Job, duration, resources available, and people available with those skills
   }

}
