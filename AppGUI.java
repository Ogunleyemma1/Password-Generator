package de.buw.se;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class AppGUI extends JFrame  {
	
	private static final long serialVersionUID = 1L;

    
    	//Defining the attributes for the password generator GUI
    	private JCheckBox checkBoxForLowerCase;
    	private JCheckBox checkBoxForUpperCase;
    	private JCheckBox checkBoxForNumbers;
    	private JCheckBox checkBoxForSpecialCharacters;
    	private JSpinner lengthOfPassword;
    	private JTextField passwordTextField;
    	private JButton generateButton;
    	private JButton copyButton;
        private JLabel passwordStrengthLabel;
    	
    	//Intializing the constructor for the GUI
    	public AppGUI() {
    		
    		setTitle("Password Generator");
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setSize(400, 600);
    		setLocationRelativeTo(null);
    		this.initialize (); //initialize the user interface		
    	}
    	
    	private void initialize() {
    		
    		//Initializing a checkbox to check for the requirements to generate password
    		this.checkBoxForLowerCase = new JCheckBox("Include LowerCase");
    		this.checkBoxForUpperCase = new JCheckBox("Include UpperCase");
    		this.checkBoxForNumbers = new JCheckBox("Include Numbers");
    		this.checkBoxForSpecialCharacters = new JCheckBox("Include Special Characters");
    		
    		//Setting the visual properties of the checkbox for all categories
    		this.checkBoxForLowerCase.setFocusPainted(false);
    		this.checkBoxForLowerCase.setBorderPainted(false);
    		this.checkBoxForLowerCase.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		
    		
    		this.checkBoxForUpperCase.setFocusPainted(false);
    		this.checkBoxForUpperCase.setBorderPainted(false);
    		this.checkBoxForUpperCase.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		
    		
    		this.checkBoxForNumbers.setFocusPainted(false);
    		this.checkBoxForNumbers.setBorderPainted(false);
    		this.checkBoxForNumbers.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		
    		
    		this.checkBoxForSpecialCharacters.setFocusPainted(false);
    		this.checkBoxForSpecialCharacters.setBorderPainted(false);
    		this.checkBoxForSpecialCharacters.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		
    		//Initializing an object to select length of password
    		this.lengthOfPassword = new JSpinner(new SpinnerNumberModel(16, 16, 30, 1));
    		
    		//Initializing an object to set the character of the password text field to 20, setting font and make it uneditable
    		this.passwordTextField = new JTextField(20);
    		this.passwordTextField.setFont(new Font("Arial", Font.PLAIN, 16));
    		this.passwordTextField.setEditable(false);
    		
    		//Initializing a generate button and settling the visual properties
    		this.generateButton = new JButton("Generate Password");
    		this.generateButton.setBackground(new Color(88, 24, 69));
    		this.generateButton.setForeground(Color.white);
    		this.generateButton.setFocusPainted(false);
    		this.generateButton.setBorderPainted(false);
    		this.generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		
    		//Allowing the generate button to listen to users action and perform action of generating password
    		this.generateButton.addActionListener(e -> generatePassword());
    		
    		//Implementing a Copy to Clipboard Button
    		this.copyButton = new JButton("Copy Password");
    		this.copyButton.setBackground(new Color(88, 24, 69));
    		this.copyButton.setForeground(Color.white);
    		this.copyButton.setFocusPainted(false);
    		this.copyButton.setBorderPainted(false);
    		this.copyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		
    		
    		//Allowing the copy button to listen to users action and perform action of copying password
    		this.copyButton.addActionListener(e -> copyPasswordToClipBoard());
    		
    		//Initializing a mainpanel for organizing and arranging the GUI and setting its visual properties
    		JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
    		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    		mainPanel.setBackground(new Color(147, 197, 114));
    		
    		//Adding the various conditions categories to the main panel
    		mainPanel.add(this.checkBoxForLowerCase);
    		mainPanel.add(this.checkBoxForUpperCase);
    		mainPanel.add(this.checkBoxForNumbers);
    		mainPanel.add(this.checkBoxForSpecialCharacters);
    		
    		//Creating a Panel within the main panel to display password and setting its visual properties
    		JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    		lengthPanel.setBackground(Color.white);
    		lengthPanel.add(new JLabel("Password Length"));
    		lengthPanel.add(this.lengthOfPassword);
    		mainPanel.add(lengthPanel);

            // Adding the password text field directly to the main panel
            mainPanel.add(this.passwordTextField);

    		
    		//Creating a panel for the botton to generate password
    		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    		buttonPanel.setBackground(Color.white);
    		
    		buttonPanel.add(this.generateButton);
    		mainPanel.add(buttonPanel);
    		

            //Initializing a panel and label to show password strength in GUI
            JPanel passwordStrengthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            passwordStrengthPanel.setBackground(Color.white);
            this.passwordStrengthLabel = new JLabel("Password Strength: ");
            this.passwordStrengthLabel.setFont(new Font("Arial", Font.BOLD, 14));
            passwordStrengthPanel.add(this.passwordStrengthLabel);
            mainPanel.add(passwordStrengthPanel);
    		
    		
    		//Creating a Panel for the button to copy to clipboard
    		JPanel copyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    		copyPanel.setBackground(Color.white);
    		copyPanel.add(this.copyButton);
    		mainPanel.add(copyPanel);
    		
    		
    		getContentPane().setBackground(Color.white);
    		add(mainPanel);
    			
    		
    	}
    	
    	//Introducing a method for generating password
    	private void generatePassword() {
    		
    		//method checking if the checkbox is selected
    		int length = (int) this.lengthOfPassword.getValue();
    		boolean includeLowerCase = this.checkBoxForLowerCase.isSelected();
    		boolean includeUpperCase = this.checkBoxForUpperCase.isSelected();
    		boolean includeNumber = this.checkBoxForNumbers.isSelected();
    		boolean includeSpecialCharacters = this.checkBoxForSpecialCharacters.isSelected();
    		
    		//Calling the AppCLI class method to generate password from the algorithm in the CLI class
    		String password = AppCLI.generatePassword(length, includeLowerCase, includeUpperCase, includeNumber, includeSpecialCharacters);
    		
    		
    		//Implementing a condition to check whether one of the boxes are checked
    		if(password.isEmpty()) {
    			
    			JOptionPane.showMessageDialog(this, "Please Check at Least One of the Boxes");
    		}
    		
    		else {
    			this.passwordTextField.setText(password);

                //Calling the method to check password strength
                String strength = checkPasswordStrength(password, 16, 30);
                //Update the password strength label
                this.passwordStrengthLabel.setText("Password Strength: " + strength);

                //Set the colour based on password strength
                switch(strength){
                    case "Strong" -> {
                        this.passwordStrengthLabel.setForeground(Color.GREEN);
                            }
                    
                    case "Medium" -> {
                        this.passwordStrengthLabel.setForeground(new Color(255, 170, 51));
                            }
                    case "Poor" ->{
                        this.passwordStrengthLabel.setForeground(Color.RED);
                    }
                    default -> {
                            }

                }
    		}
    	}

        //Defining a method to check password strength
        public static String checkPasswordStrength(String password, int minLength, int maxLength){

            //Definiting the criteria for password strength evaluation based on minimum password length specified in software description

            int interval = (maxLength - minLength) / 3;
            int lowerThreshold = minLength + interval;
            int upperThreshold = maxLength - interval;
            
            if(password.length() >=upperThreshold){

                return "Strong";
            }
            else if(password.length() >= lowerThreshold ){

                return "Medium";
            }
            else{

                return "Poor";
            }
        }
    	
    	
    	//Introducing a method to copy password to clipboard
    	private void copyPasswordToClipBoard() {
    		
    		String password = this.passwordTextField.getText();
    		
    		if(!password.isEmpty()) {
    			StringSelection selection = new StringSelection(password);
    			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    			clipboard.setContents(selection,  null);
    			JOptionPane.showMessageDialog(this, "Password Copied to Clipboard");
    		}
    		
    		else {
    			JOptionPane.showMessageDialog(this, "Password is Empty");
    		}
    	}
    

    
   //Implementing a method to initialize the GUI components of the program  
    
   public static void main(String[] args) {
       // launch();
    	
    	SwingUtilities.invokeLater(() -> {
    		
    		try {
    			
    			
    			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    		}
    		
    		catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
    			
    			ex.printStackTrace();
    		}
    		
    		AppGUI app = new AppGUI();
    		app.setVisible(true);
    		
    		
    		
    		
    	});
    }

    
}
