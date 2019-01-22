package clubMembers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;


public class EditDetails extends JFrame  implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JLabel memberValue = new JLabel("Enter ID : ");
	private JPanel menuPanel = new JPanel();
	private JPanel resultPanel = new JPanel();
	private JLabel newMember = new JLabel("Edit or Delete :");
	private JLabel blankJLabel1 = new JLabel("");
	private JLabel firstNameLabel = new JLabel("First Name : ");
	private JLabel surnameLabel = new JLabel("Surname : ");
	private JLabel streetLabel = new JLabel("Street : ");
	private JLabel townLabel = new JLabel("Town : ");
	private JLabel countyLabel = new JLabel("County : ");
	private JLabel positionLabel = new JLabel("Position : ");
	private JLabel subscriptionLabel = new JLabel("Paid Member : ");
	private JLabel ageLabel = new JLabel("Age : ");

	private JTextField memberInputField = new JTextField();
	private JTextField memberFirstNameField = new JTextField(15);
	private JTextField memberSurnameField = new JTextField(15);
	private JTextField memberStreetField = new JTextField(15);
	private JTextField memberTownField = new JTextField(15);
	private JTextField memberCountyField = new JTextField(15);
	private JTextField memberSubscriptionField = new JTextField(3);
	private JTextField memberPositionField = new JTextField(10);
	private JTextField memberAgeField = new JTextField(5);

	Font fontForQuestion = new Font("Verdana", Font.BOLD,  16);
	Font fontForOptions = new Font("Verdana", Font.BOLD,  14);
	private JButton memberMenuButton, memberBackButton, memberChangeButton, memberDeleteButton;
	private LinkedList<Members> TrekClub;
	int memberInputSearchAsInt;





	public EditDetails()
	{
		super("Waterford Star Trek Fan-Club- Edit Details");

		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("images/stbg-find.jpg"))));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(460, 480);
		setLocation(300, 300);
		this.setVisible(true);
		this.setResizable(false);
		Container myWindow = this.getContentPane();

		edit();

		this.setContentPane(myWindow);
	}




	public void edit()
	{
		menuPanel.setLayout(null);
		menuPanel.setBounds(100, 35, 300, 95);
		GridLayout menuPane = new GridLayout(0, 2, 5, 8);
		menuPanel.setBackground(new Color(0, 0, 0));
		menuPanel.setLayout(menuPane);
		newMember.setForeground(Color.red);
		newMember.setFont(fontForQuestion);
		menuPanel.add(newMember);
		menuPanel.add(blankJLabel1);


		memberValue.setForeground(Color.white);
		memberValue.setFont(fontForOptions);
		menuPanel.add(memberValue);
		menuPanel.add(memberInputField);

		memberBackButton = new JButton("BACK");
		memberBackButton.setForeground(Color.yellow);
		memberBackButton.setBackground(Color.blue);
		memberBackButton.setFont(fontForQuestion);
		menuPanel.add(memberBackButton);
		memberBackButton.addActionListener(this);

		memberMenuButton = new JButton("SUBMIT");
		memberMenuButton.setForeground(Color.yellow);
		memberMenuButton.setBackground(Color.blue);
		memberMenuButton.setFont(fontForQuestion);
		menuPanel.add(memberMenuButton);	
		memberMenuButton.addActionListener(this);

		resultPanel.setBounds(100, 135, 300, 285);
		GridLayout memberResultPanel = new GridLayout(0, 2, 2, 5);
		resultPanel.setLayout(memberResultPanel);
		resultPanel.setOpaque(false);
		firstNameLabel.setForeground(Color.white);
		firstNameLabel.setFont(fontForQuestion);
		resultPanel.add(firstNameLabel);
		memberFirstNameField.setFont(fontForQuestion);
		resultPanel.add(memberFirstNameField);

		surnameLabel.setForeground(Color.white);
		surnameLabel.setFont(fontForQuestion);
		resultPanel.add(surnameLabel);
		memberSurnameField.setFont(fontForQuestion);
		resultPanel.add(memberSurnameField);

		streetLabel.setForeground(Color.white);
		streetLabel.setFont(fontForQuestion);
		resultPanel.add(streetLabel);
		memberStreetField.setFont(fontForQuestion);
		resultPanel.add(memberStreetField);

		townLabel.setForeground(Color.white);
		townLabel.setFont(fontForQuestion);
		resultPanel.add(townLabel);
		memberTownField.setFont(fontForQuestion);
		resultPanel.add(memberTownField);

		countyLabel.setForeground(Color.white);
		countyLabel.setFont(fontForQuestion);
		resultPanel.add(countyLabel);
		memberCountyField.setFont(fontForQuestion);
		resultPanel.add(memberCountyField);

		subscriptionLabel.setForeground(Color.white);
		subscriptionLabel.setFont(fontForQuestion);
		resultPanel.add(subscriptionLabel);
		memberSubscriptionField.setFont(fontForQuestion);
		resultPanel.add(memberSubscriptionField);

		positionLabel.setForeground(Color.white);
		positionLabel.setFont(fontForQuestion);
		resultPanel.add(positionLabel);
		memberPositionField.setFont(fontForQuestion);
		resultPanel.add(memberPositionField);

		ageLabel.setForeground(Color.white);
		ageLabel.setFont(fontForQuestion);
		resultPanel.add(ageLabel);
		memberAgeField.setFont(fontForQuestion);
		resultPanel.add(memberAgeField);

		memberChangeButton = new JButton("CHANGE");
		memberChangeButton.setForeground(Color.yellow);
		memberChangeButton.setBackground(Color.red);
		memberChangeButton.setFont(fontForQuestion);
		resultPanel.add(memberChangeButton);
		memberChangeButton.addActionListener(this);

		memberDeleteButton = new JButton("DELETE");
		memberDeleteButton.setForeground(Color.yellow);
		memberDeleteButton.setBackground(Color.red);
		memberDeleteButton.setFont(fontForQuestion);
		resultPanel.add(memberDeleteButton);	
		memberDeleteButton.addActionListener(this);

		add(menuPanel);
		add(resultPanel);
	}


	public void actionPerformed(ActionEvent event) 
	{ 

		if (event.getSource() == memberBackButton)
		{
			try 
			{
				AudioInputStream  audioInputForBack = AudioSystem.getAudioInputStream(new File("gui/clubMembers/sounds/beep.wav"));
				Clip clip = AudioSystem.getClip(); 
				clip.open(audioInputForBack);
				clip.start();
			}
			catch(Exception ex)
			{
				System.out.println("Error with playing sound.");
				ex.printStackTrace( );
			}
			this.setVisible(false);
			new MainMenu();
		}

		if (event.getSource() == memberMenuButton)	
		{		
			try 
			{
				AudioInputStream  audioInputForError = AudioSystem.getAudioInputStream(new File("gui/clubMembers/sounds/submit.wav"));
				Clip clip = AudioSystem.getClip(); 
				clip.open(audioInputForError);
				clip.start();
			}
			catch(Exception ex)
			{
				System.out.println("Error with playing sound.");
				ex.printStackTrace( );
			}

			if (!memberInputField.getText().equals(""))
			{
				try
				{
					ObjectInputStream loadInMembersFromDocument = new ObjectInputStream(new FileInputStream("myList.txt")); 					
					TrekClub = (LinkedList<Members>) loadInMembersFromDocument.readObject();

				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error Loading File.", "FILE ERROR", JOptionPane.WARNING_MESSAGE);
				}

				memberInputSearchAsInt = (Integer.parseInt(memberInputField.getText())-1);  // object list starts at 0
				try 
				{
					Members foundMembers = TrekClub.get(memberInputSearchAsInt);

					memberFirstNameField.setText(foundMembers.getFirstName());
					memberSurnameField.setText(foundMembers.getSurname());
					memberStreetField.setText(foundMembers.getStreet());
					memberTownField.setText(foundMembers.getTown());
					memberCountyField.setText(foundMembers.getCounty());
					memberPositionField.setText(foundMembers.getPosition());
					memberSubscriptionField.setText(foundMembers.getSubscription());
					memberAgeField.setText("" +foundMembers.getAge());
				}
				catch (IndexOutOfBoundsException error)
				{
					JOptionPane.showMessageDialog(null, "No Such record Exists.", "NO ID EXISTS.", JOptionPane.WARNING_MESSAGE);
				}		
			}
		}



		if (event.getSource() == memberChangeButton)
		{
			int confirmUpdate = JOptionPane.showConfirmDialog(null, "Are you sure you wish to Update Details?","Confirm Update" ,JOptionPane.YES_NO_OPTION);
			
			if (confirmUpdate == 0)
			{ 
				memberInputSearchAsInt = (Integer.parseInt(memberInputField.getText())-1);  // object list starts at 0
				try 
				{
					Members foundMembers = TrekClub.get(memberInputSearchAsInt);
					foundMembers.setFirstName(memberFirstNameField.getText());
					foundMembers.setSurname(memberSurnameField.getText());
					foundMembers.setStreet(memberStreetField.getText());
					foundMembers.setTown(memberTownField.getText());
					foundMembers.setCounty(memberCountyField.getText());
					foundMembers.setPosition(memberPositionField.getText());
					foundMembers.setSubscription(memberSubscriptionField.getText());
					foundMembers.setAge(Integer.parseInt(memberAgeField.getText()));
				}
				catch (NumberFormatException nfe) 
				{
					JOptionPane.showMessageDialog(null, "Number format required", "wrong data type", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (memberFirstNameField.getText().equals("") || memberSurnameField.getText().equals("") || memberStreetField.getText().equals("") || memberTownField.getText().equals("") 
						|| memberCountyField.getText().equals("") ||  
						memberAgeField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "All fields must be filled in.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
				}	

				try 
				{
					ObjectOutputStream saveMembersToDocument = new ObjectOutputStream(new FileOutputStream("myList.txt"));
					saveMembersToDocument.writeObject(TrekClub);
				} 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Could Not Save File.", "SAVING ISSUE", JOptionPane.WARNING_MESSAGE);
				}
			}
			this.setVisible(false);
			new MainMenu();
		}


		if (event.getSource() == memberDeleteButton)	
		{
			int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you wish to Delete Member?","Confirm Delete" ,JOptionPane.YES_NO_OPTION);

			if (confirmDelete == 0)
			{ 
				memberInputSearchAsInt = (Integer.parseInt(memberInputField.getText())-1);  // object list starts at 0			
				TrekClub.remove(memberInputSearchAsInt);

				try 
				{
					ObjectOutputStream saveMembersToDocument = new ObjectOutputStream(new FileOutputStream("myList.txt"));
					saveMembersToDocument.writeObject(TrekClub);
				} 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Could Not Save File.", "SAVING ISSUE", JOptionPane.WARNING_MESSAGE);
				}
			}
			this.setVisible(false);
			new MainMenu();
		}
	}
}
