/* Corey Hicks
 * 15 May 2018
 * CIS 404
 * 
 * Create a Swing application that looks and behaves like the example located at the top of this assignment. The following source code is provided to get you started. 
 * Complete the following code by adding appropriate ActionListeners to each button or modifying the current ActionListeners to make this program complete. The 
 * functionality you are looking for is the following:
 * 
 * 1 The Previous button will iterate through the data array moving to the previous array element each time the button is clicked and will then update the GUI with the 
 * newly selected data. If the Previous button is selected while the array is currently positioned at the first element, your program should then move the last 
 * element and update the display with the newly selected data. 
 * 
 * 2 The Next button will iterate through the data array moving to the next array element each time the button is clicked and will then update the GUI with the newly 
 * selected data. If the Next button is selected while the array is currently positioned at the last element, your program should then move the first element and 
 * update the display with the newly selected data.
 * 
 * 3 When the Reset button is selected you should move to the first element in the array and update the display.	
 * 
 * 		Logic for the action listeners uses if/else statements to determine which position the array is in before iterating and displaying values in the text boxes. 
 * Reset always sets the iteration back to the first position of the array and displays the values.	*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Assignment01 extends JFrame {

	// creates new buttons, labels, and text fields
	private JButton buttonPrev = new JButton("Prev");
	private JButton buttonReset = new JButton("Reset");
	private JButton buttonNext = new JButton("Next");
	private JLabel labelHeader = new JLabel("Database Browser", JLabel.CENTER);
	private JLabel labelName = new JLabel("Name");
	private JLabel labelAddress = new JLabel("Address");
	private JLabel labelCity = new JLabel("City");
	private JLabel labelState = new JLabel("State");
	private JLabel labelZip = new JLabel("Zip");
	private JTextField textFieldName = new JTextField();
	private JTextField textFieldAddress = new JTextField();
	private JTextField textFieldCity = new JTextField();
	private JTextField textFieldState = new JTextField();
	private JTextField textFieldZip = new JTextField();

	// creates new object array and sets 5 values into the array
	DataClass[] DataClassArray = { 
			
			new DataClass("Fred", "Savage", "Chicago", "IL", "60621"),
			new DataClass("George", "Clooney", "Lexington", "KY", "40507"),
			new DataClass("Denzel", "Washington", "Mount Vernon", "NY", "10551"),
			new DataClass("Leonardo", "DiCaprio", "Los Angeles", "CA", "90009"), 
			new DataClass("Matt", "Damon", "Cambridge", "MA", "02138")
	};

	int arrayPointer = 0;

	public Assignment01 (String title) {

		// sets frame object boundaries
		super (title);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		JPanel cp = (JPanel) getContentPane();
		labelHeader.setFont(new Font("TimesRoman", Font.BOLD, 24));
		labelHeader.setBounds(40, 10, 300, 50);
		buttonPrev.setBounds(30, 250, 80, 25);
		buttonReset.setBounds(150, 250, 80, 25);
		buttonNext.setBounds(270, 250, 80, 25);
		labelName.setBounds(10, 80, 80, 25);
		labelAddress.setBounds(10, 110, 80, 25);
		labelCity.setBounds(10, 140, 80, 25);
		labelState.setBounds(10, 170, 80, 25);
		labelZip.setBounds(10, 200, 80, 25);
		textFieldName.setBounds(120, 80, 250, 25);
		textFieldAddress.setBounds(120, 110, 250, 25);
		textFieldCity.setBounds(120, 140, 250, 25);
		textFieldState.setBounds(120, 170, 250, 25);
		textFieldZip.setBounds(120, 200, 250, 25);

		// adds frame objects to JPanel
		cp.setLayout(null);
		cp.add(labelHeader);
		cp.add(buttonPrev);
		cp.add(buttonReset);
		cp.add(buttonNext);
		cp.add(labelName);
		cp.add(textFieldName);
		cp.add(labelAddress);
		cp.add(textFieldAddress);
		cp.add(labelCity);
		cp.add(textFieldCity);
		cp.add(labelState);
		cp.add(textFieldState);
		cp.add(labelZip);
		cp.add(textFieldZip);

		// sets the array to zero to display the first array object
		setFields (arrayPointer);
		
		addWindowListener (new WindowAdapter() {

			public void windowClosing (WindowEvent evt) {

				shutDown();
			}
		});

		buttonPrev.addActionListener (new ActionListener() {

			public void actionPerformed (ActionEvent evt1) {

				// checks if the pointer is lower than zero
				if (arrayPointer > 0) {

					// iterates the pointer position
					arrayPointer--;
				}
				
				// sets pointer to the last position in the array
				else if (arrayPointer == 0) {
					
					arrayPointer = DataClassArray.length - 1;
				}

				// sets fields based on pointer position
				setFields (arrayPointer);
			}
		});
		
		buttonNext.addActionListener (new ActionListener() {

			public void actionPerformed (ActionEvent evt2) {
				
				// checks if the pointer is lower than the array length
				if (arrayPointer < DataClassArray.length - 1) {

					// iterates the pointer position
					arrayPointer++;
				}
				
				// sets pointer to the first position in the array
				else {
					
					arrayPointer = 0;
				}

				// sets fields based on pointer position
				setFields (arrayPointer);
			}
		});
		
		this.buttonReset.addActionListener (new ActionListener() {

			public void actionPerformed (ActionEvent evt3) {

				// sets array back to zero and displays the first array object
				arrayPointer = 0; 
				
                setFields (arrayPointer);
			}				
		});
	}

	// method to set array objects into appropriate text fields based on array position
	private void setFields (int position) {

		textFieldName.setText(DataClassArray[position].getName());
		textFieldAddress.setText(DataClassArray[position].getAddress());
		textFieldCity.setText(DataClassArray[position].getCity());
		textFieldState.setText(DataClassArray[position].getState());
		textFieldZip.setText(DataClassArray[position].getZip());
	}

	// prompts the user to press yes to exit
	private void shutDown() {

		int returnVal = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?");

		if (returnVal == JOptionPane.YES_OPTION) {

			System.exit(0);
		}
	}

	public static void main(String args[]) {

		Assignment01 myAssignment = new Assignment01("Database Browser");

		myAssignment.setSize(400, 350);
		myAssignment.setVisible(true);
	}
}

class DataClass {

	// private string fields
	private String name;
	private String address; 
	private String city; 
	private String state; 
	private String zipCode;

	// data class constructor that accepts 5 string values
	DataClass(String name, String address, String city, String state, String zipCode) {

		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	// get methods for the data class constructor
	String getName() {
		
		return this.name;
	}

	String getAddress() {
		
		return this.address;
	}

	String getCity() {
		
		return this.city;
	}

	String getState() {
		
		return this.state;
	}

	String getZip() {
		
		return this.zipCode;
	}
}