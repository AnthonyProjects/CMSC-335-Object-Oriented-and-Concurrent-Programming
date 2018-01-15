
/**
 *  Anthony Borza
 *  SeaPortProgram.java
 */

// imports used throughout program

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SeaPortProgram extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private final static int FRAME_WIDTH = 1030;   		// window width declared as private, final, static, and as an integer with a size of 800
    private final static int FRAME_HEIGHT = 650; 	 	// window height declared as private, final, static, and as an integer with a size of 600
    static JTextArea textArea;							// JtextArea declared as private and assigned as texArea
    private JTextField target;							// JTextFiled declared as private and assigned as searchTargets
    private JLabel searchName;							// JLabel declared as private and assigned as searchName
    private JRadioButton name;							// JRadioButton declared as private and assigned as name
    private JRadioButton index;							// JRadioButton declared as private and assigned as index
    private JRadioButton skill;							// JRadioButton declared as private and assigned as skill
    private JRadioButton weight;						// JRadioButton declared as private and assigned as weight
    private JRadioButton width;							// JRadioButton declared as private and assigned as width
    private JRadioButton length;						// JRadioButton declared as private and assigned as length
    private JRadioButton draft;							// JRadioButton declared as private and assigned as draft
    private JButton searchButton;						// JButton declared as private and assigned as search
    private JButton sortButton;							// JButton declared as private and assigned as sort
    private JButton readFile;							// JButton declared as private and assigned as readFile
    private JScrollPane scrollPane3;					// JScrollPane declared as private and assigned as scrollPane3
    private JPanel panel1, panel2, panel3, panel5;		// JPanel declared as private and assigned as panel1, panel2, panel3
	static JPanel panel4;								// JPanel declared as static and assigned as panel4
    Scanner sc;											// scanner declared and assigned sc
    World world;										// calls World class
    Job job;											// calls Job class
  
    //-------------------------------------------- SeaPortProgram Method ------------------------------------------------//	  
    
    /** SeaPortProgeam constructor contains the following:
	  * JTextArea: constructs a new empty TextArea with the specified number of rows
	  * TitledBorder: Creates a TitledBorder instance.
	  * JScrollPane: provides a scroll panel for JTextArea
	  * JPannel: calls the methods, panel1, panel2, panel3, panel4
    **/

    public SeaPortProgram()
    {
        panel1();			// calls panel1
        panel2();			// calls panel2
        panel3();			// calls panel3
        panel4();			// calls panel4
        panel5();			// calls panel5
        inputFile();		// calls inputFile method	
        
        readFile.addActionListener(new ActionListener() 	// Adds action listener to the button readFile
        {
        	public void actionPerformed(ActionEvent e)		// is used when the action is performed
        	{
        		inputFile();	// calls inputFile method	
        	}        
        } ); 
        
        searchButton.addActionListener(new Search());		// implements action listener for the search button
        sortButton.addActionListener(new Sort());			// implements action listener for the sort button
    } 
    
  //-------------------------------------------- JPanel Methods ------------------------------------------------//	  
    
    /**
     * panel1 method:
     * @return panel1
     */
    public JPanel panel1()
    {
    	  panel1  = new JPanel();								// panel1 is a JPanel
          searchName = new JLabel("Enter a search item");		// JLabel for searchName
          target = new JTextField(20);							// target is a JTextField with 30 character spaces
          readFile = new JButton("Read File");					// JButton for readFile
          panel1.add(readFile);									// adds readFile to panel1
          panel1.add(searchName);								// adds searchName to panel1
          panel1.add(target);									// adds target to panel1
          add(panel1, BorderLayout.NORTH);						// adds the panel1 to the GUI using BorderLayout position NORTH
          return panel1;										// return panel1
    }
    
    /**
     * panel2 method:
     * @return panel2
     */
    
    public JPanel panel2()
    {
    	 panel2 = new JPanel(new GridLayout(17,2));				// creates a grid layout with the specified number of rows (17) and columns (2)
         searchButton = new JButton("Search");					// JButton for searchButton
         name  = new JRadioButton("Name");						// JRadioButton for name
         index = new JRadioButton("Index");						// JRadioButton for index, and used as a searching target
         skill = new JRadioButton("Skill");						// JRadioButton for skill, and used as a searching target
         sortButton = new JButton("Sort");						// JButton for sort
         panel2.add(sortButton);								// adds the sortButton to panel2
         weight = new JRadioButton("Weight");					// JRadioButton for weight, and used as a sorting target
         length = new JRadioButton("Length");					// JRadioButton for length, and used as a sorting target
         width = new JRadioButton("Width");						// JRadioButton for width, and used as a sorting target
         draft = new JRadioButton("Draft");						// JRadioButton for draft, and used as a sorting target
         panel2.add(searchButton);								// adds searchButton to panel1
         panel2.add(name);										// adds radio button name to panel1
         panel2.add(index);										// adds radio button index to panel1
         panel2.add(skill);										// adds radio button skill to panel2
         panel2.add(new JLabel());								// adds new JLabel										
         panel2.add(sortButton);								// adds sortButton to panel2
         panel2.add(weight);									// adds radio button weight to panel2
         panel2.add(length);									// adds radio button width to panel2
         panel2.add(width);										// adds radio button length to panel2
         panel2.add(draft);										// adds radio button draft to panel2
         ButtonGroup group = new ButtonGroup();					// creates a new button group named group
         group.add(name);										// adds the radio button name 
         group.add(index);										// adds the radio button index 
         group.add(skill);										// adds the radio button skill         
         ButtonGroup group2 = new ButtonGroup();				// creates a new button group named group2
         group2.add(weight);									// adds the radio button weight
         group2.add(length);									// adds the radio button width
         group2.add(width);										// adds the radio button length
         group2.add(draft);										// adds the radio button draft
         add(panel2, BorderLayout.EAST);						// sets panel2 layout to BorderLayout and postion EAST
         return panel2;											// return panel2
    }
    
    /**
     * panel3 method:
     * @return panel3
     */
    
    public JPanel panel3()
    {
        panel3 = new JPanel(new BorderLayout());				// panel3 is a JPanel using BorderLayout
        scrollPane3 = new JScrollPane();						// new JScrollPane named scrollPane3
        panel3.add(scrollPane3, BorderLayout.EAST);				// adds scrollPane3 to panel3
        add(panel3, BorderLayout.WEST);							// adds the panel3 to the GUI using BorderLayout position WEST
        return panel3;											// return panel3
    }
    
    /**
     * panel4 method:
     * @return panel4
     */
    
    public JPanel panel4()
    {
    	panel4 = new JPanel();									// new JPanel named panel4
    	TitledBorder title = new TitledBorder("Jobs In Progress"); 	// border title
    	panel4.setBorder(title);								// sets the border title
    	panel4.setBackground(Color.white);						// sets background of panel4 to white
    	return panel4;											// return panel4
    }
    
    /**
     * panel5 method:
     * @return panel5
     */
    
    public JPanel panel5()
    {
    	 textArea = new JTextArea(20,20);	 					// Constructs a new empty TextArea with the specified number of rows (15)  and columns (20)
         textArea.setEditable(false);							// Sets textArea so it is unable to be edited
         TitledBorder title = new TitledBorder("Results"); 		// adds a title border around the textArea named results
         textArea.setBorder(title);								// sets title border
         JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	
         JScrollPane scroll2 = new JScrollPane(panel4, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	
         JSplitPane scrollPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scroll, scroll2);	
         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// Sets the operation that will happen by default  when the user initiates a "close" on this frame
         add(scrollPane);										 // adds the ScrollPane to the GUI using BorderLayout position CENTER
         return panel5;	// return panel5
    }

    
  //-------------------------------------------- inputFile Method ------------------------------------------------//	  
    
    /** readFile Method: reads in file, and allows user to select the file
     *  in the directory. Display the file in its desired format after file
     *  has been read in.
     **/

	public void inputFile() 
	{
		JFileChooser jfc = new JFileChooser(".");		// enables the user to choose a file from current directory 
		int open = jfc.showOpenDialog(new JFrame());	// Pops up an "Open File" file chooser dialog
		if(open == JFileChooser.APPROVE_OPTION) 		// Return value if approve (yes, ok) is chosen
		{
			try		// Exception Handling
			{
				sc = new Scanner(new FileReader(jfc.getSelectedFile()));	// gets selected file
		
			}
			catch(FileNotFoundException e)	// catch for FileNotFound
			{	
				JOptionPane.showMessageDialog(null, "Sorry, that file does not exist.");	// displays file does not exist
				System.exit(1);		// terminates the program if there is an error
			}
		}
		world = new World(sc);	// calls the world class
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("World", true);	// is a general-purpose node in a tree data structure named root
    	DefaultTreeModel treeModel = new DefaultTreeModel(root);					// A simple tree data model that uses TreeNodes, and is named treeModel
        JTree tree = new JTree(treeModel);											//creates a JTree

        for(SeaPort port : world.getPorts()) 	// for each element port of type SeaPort in array of world.getPorts()
        {
            populateTree(port, root); 	// calls populateTree method
        }
        
        panel3.add(tree);	// adds tree to panel3
        JScrollPane scrollPane3 = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	// adds scroll bar
        scrollPane3.setPreferredSize(new Dimension(300, 100));	// sets dimensions of scrollPane
        panel3.add(scrollPane3, BorderLayout.EAST);				// adds scroll bar to EAST position on GUI
    	textArea.setText(world.toString());						// appends the contents to the textArea
		Thread t = new Thread(job);								// creates a new thread name t, and calls the the job class
		t.start();												// starts thread
	}	
	
	//-------------------------------------------- Search Method ------------------------------------------------//	  
	
    /**
     * Search method implements ActionListener: This method performs the action for the 
     * RadioButtons: name, index, and skill if selected. If one of these are selected
     * it calls the desired method from the World class, and performs task within that
     * method.The following methods for each RadioButton are the following:
     	* name: calls searchName method from the World class
     	* index: calls searchIndex method from the World class
     	* skill: calls searchSkill method from the World class
     */
    
    private class Search implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)		// invoked when an action occurs.
    	{
 
    		if(name.isSelected())	// if name is selected do the following
    		{
    			String tmp, results;					// tmp, and results declared as a string
    			tmp = target.getText();					// gets the text entered into the "Enter in a search item" JTextField 
    			results = world.searchName(tmp);		// gets the searchName method from the world class, and takes the tmp
    			textArea.setText("\n---Results after searching for Name:\n\n" + (results)); 	  // displays the results in the text area after search	
    		}
    		if(index.isSelected())	// if index is selected do the following
    		{
    			String tmp; String results;
    			tmp = target.getText();
    			results = world.indexSearch(tmp);	
    			textArea.setText("\n--- Results after searching for Index type:\n\n" + (results));	// displays the results in the text area after search	
    		}
    		if(skill.isSelected())	// if skill is selected do the following
    		{
    			String results, tmp;					// tmp, and results declared as a string
    			tmp = target.getText();					// gets the text entered into the "Enter in a search item" JTextField 
    			results = world.skillSearch(tmp);		// gets the skillSearch method from the world class, and takes the tmp
    			textArea.setText("\n--- Results after searching for Skill type:\n\n"+ (results));	// displays the results in the text area after search	
    		}		
    	}
    }

  //-------------------------------------------- Sort Method ------------------------------------------------//	  
    
    /**
     * Sort method implements ActionListener: This method performs the action for the 
     * RadioButtons: weight, width, length, and draft if selected. If one of these are selected
     * it calls the desired method from the World class, and performs task within that
     * method.The following methods for each RadioButton are the following:
      weight, width, length, draft
     */
    
    private class Sort implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)	// invoked when an action occurs.
    	{
        	if(target.getText().equals(""))	// if the JTextField is empty (no text)
        	{
        		JOptionPane.showMessageDialog(null, "Please enter in one of these fields to sort:   \nWeight,  Length,  Width,  Draft");
        		return;
        	}
        	
    		String results = "";		// results declared as a string
			int param = Ship.WEIGHT;	// param declared as an integer and calls the ship class and WEIGHT case in switch statement
			
    		if(weight.isSelected())	 	// if weight is selected do the following
    		{
    			param = Ship.WEIGHT;	// calls ship class and WEIGHT case in switch statement
    			results = "\n--- Results after sorting the ships weight:\n\n";	
    		}
    		if(length.isSelected())		// if length is selected do the following
    		{	
    			param = Ship.LENGTH;	// calls ship class and LENGTH case in switch statement
    			results = "\n--- Results after sorting the ships length:\n\n";	
    		}
    		if(width.isSelected())		// if width is selected do the following
    		{
    			param = Ship.WIDTH;		// calls ship class and WIDTH case in switch statement
    			results = "\n--- Results after sorting the ships width:\n\n";	
    		}
    		if(draft.isSelected())		// if draft is selected do the following
    		{
    			param = Ship.DRAFT;		// calls ship class and DRAFT case in switch statement
    			results = "\n--- Results after sorting the ships draft:\n\n";	
    		}
    		
    		Ship.compareWith = param;
    		results += world.sort();	// calls the sort method from the class World
    		textArea.setText(results);	// displays results after sorted
    	}
    }
   
    //-------------------------------------------- poulateTree Method ------------------------------------------------//	  

    /**
     * populateTree method: this method is used as a general-purpose node in a tree data structure. 
     * @param port
     * @param top
     */

    public void populateTree(SeaPort port, DefaultMutableTreeNode top)
    {
    	DefaultMutableTreeNode root = new DefaultMutableTreeNode(port.getName());	// calls getName method
    	top.add(root);				// adds to tree
    	createNodes(port, root);	// calls the createNodes method
    	this.revalidate();			// revalidates the component hierarchy up to the nearest validate root. 
    }
    
    //-------------------------------------------- createNodes Method ------------------------------------------------//	  

    /**
     * createNodes method
     * @param port
     * @param top
     */
    
    public void createNodes(SeaPort port, DefaultMutableTreeNode top) 
    {
 	   for(Dock dock: port.getDocks())						// for each element dock of type Dock in array getDocks()
 	   {
 		   top.add(new DefaultMutableTreeNode(dock));		// adds to tree
 	   }
 	   for(Ship ship: port.getShips())						// for each element ship of type Ship in array getShips()
 	   {
 		   top.add(new DefaultMutableTreeNode(ship));		// adds to tree
 	   }
 	   for(Person person: port.getPerson())					// for each element person of type Person in array getPersons()
 	   {
 		   top.add(new DefaultMutableTreeNode(person));		// adds to tree
 	   }	
 	   for(Ship que: port.getQue())							// for each element que of type Ship in array getQue()
 	   {
 		   top.add(new DefaultMutableTreeNode(que));		// adds to tree
 	   }
    }

    //-------------------------------------------- Main Method ------------------------------------------------//	  

	/**
	 * Main method that executes the program
     * @param args
     */

    public static void main(String[] args) 
    {
    	SeaPortProgram frame = new SeaPortProgram();				// new frame object
        frame.setTitle("Project 4: SeaPort");						// assigns "Project 4: SeaPort" as title to frame
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);					// sets GUI frame size
        frame.setLocationRelativeTo(null);							// sets location relative to null
        frame.setVisible(true);										// sets frame as visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// closes on exit
    }


}
