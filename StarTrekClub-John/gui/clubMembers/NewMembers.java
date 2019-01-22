package clubMembers;

import java.io.Serializable;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.ListIterator;
import java.io.FileNotFoundException;


public class NewMembers extends JFrame  implements ActionListener, ItemListener
{
	private static final long serialVersionUID = 1L;
	private JPanel menuPanel = new JPanel();
	private JLabel newMember = new JLabel("Add Member");
	private JLabel memberFirstName = new JLabel("First Name : ");
	private JLabel memberSurname = new JLabel("Surname : ");
	private JLabel memberStreet = new JLabel("Street : ");
	private JLabel memberTown = new JLabel("Town : ");
	private JLabel memberCounty = new JLabel("County : ");
	private JLabel memberSubscription = new JLabel("Subscription : ");
	private JLabel memberPosition = new JLabel("Club position : ");
	private JLabel memberAge = new JLabel("Age : ");
	private JLabel aBlankSpace = new JLabel(" ");
	private String memberSubscriptionField = "";
	private JTextField memberFirstNameField = new JTextField(15);
	private JTextField memberSurnameField = new JTextField(15);
	private JTextField memberStreetField = new JTextField(15);
	private JTextField memberTownField = new JTextField(15);
	private JTextField memberCountyField = new JTextField(15);
	private JTextField memberAgeField = new JTextField(5);
	private JRadioButton memberSubscriptionYes = new JRadioButton("Yes");
	private JRadioButton memberSubscriptionNo = new JRadioButton("No");

	private JComboBox<String> memberClubPosition;
	private ButtonGroup memberSubscriptionGroup;
	private JButton memberMenuButton, memberBackButton;

	private LinkedList<Members> TrekClub, getMembersFromDocument;
	private Members addNewMember;
	int  testIntegerInput;
	File theClub = new File("myList.txt");
	Font fontForQuestion = new Font("Verdana", Font.BOLD,  16);
	Font fontForOptions = new Font("Verdana", Font.BOLD,  14);

	public NewMembers()
	{
		super("Waterford Star Trek Fan-Club- New Members");
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("images/stbg-new.jpg"))));	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(460, 480);
		setLocation(300, 300);
		this.setVisible(true);
		this.setResizable(true);
		Container myWindow = this.getContentPane();

		TrekClub = new LinkedList<Members>();
		addMembers();	

		this.setContentPane(myWindow);
	}



	public void addMembers()
	{
		menuPanel.setLayout(null);
		menuPanel.setBounds(100, 50, 300, 365);
		GridLayout menuPane = new GridLayout(0, 2, 2, 7);
		menuPanel.setOpaque(false);
		menuPanel.setLayout(menuPane);
		newMember.setForeground(Color.red);
		newMember.setFont(fontForQuestion);
		menuPanel.add(newMember);
		menuPanel.add(aBlankSpace);

		memberPosition.setForeground(Color.white);
		memberPosition.setFont(fontForOptions);
		menuPanel.add(memberPosition);
		memberClubPosition = new JComboBox<String>();
		memberClubPosition.addItem("Member");
		memberClubPosition.addItem("Chairman");
		memberClubPosition.addItem("Secretary");
		memberClubPosition.addItem("Treasurer");
		menuPanel.add(memberClubPosition);

		memberFirstName.setForeground(Color.white);
		memberFirstName.setFont(fontForOptions);
		menuPanel.add(memberFirstName);
		menuPanel.add(memberFirstNameField);
		

		memberSurname.setForeground(Color.white);
		memberSurname.setFont(fontForOptions);
		menuPanel.add(memberSurname);
		menuPanel.add(memberSurnameField);
		

		memberStreet.setForeground(Color.white);
		memberStreet.setFont(fontForOptions);
		menuPanel.add(memberStreet);
		menuPanel.add(memberStreetField);

		memberTown.setForeground(Color.white);
		memberTown.setFont(fontForOptions);
		menuPanel.add(memberTown);
		menuPanel.add(memberTownField);

		memberCounty.setForeground(Color.white);
		memberCounty.setFont(fontForOptions);
		menuPanel.add(memberCounty);
		menuPanel.add(memberCountyField);


		memberSubscription.setForeground(Color.white);
		memberSubscription.setFont(fontForOptions);
		menuPanel.add(memberSubscription);

		JPanel memberSubscriptionPanel = new JPanel();
		memberSubscriptionPanel.setOpaque(false);
		memberSubscriptionPanel.setForeground(Color.white);
		memberSubscriptionGroup = new ButtonGroup();
		memberSubscriptionYes.addItemListener(this);          
		memberSubscriptionNo.addItemListener(this);
		memberSubscriptionYes.setForeground(Color.white);
		memberSubscriptionNo.setForeground(Color.white);
		memberSubscriptionYes.setOpaque(false);
		memberSubscriptionNo.setOpaque(false);
		memberSubscriptionYes.setFont(fontForOptions);
		memberSubscriptionNo.setFont(fontForOptions);	
		memberSubscriptionNo.setSelected(true);
		memberSubscriptionGroup.add(memberSubscriptionYes);
		memberSubscriptionGroup.add(memberSubscriptionNo);
		memberSubscriptionPanel.add(memberSubscriptionYes);
		memberSubscriptionPanel.add(memberSubscriptionNo);
		menuPanel.add(memberSubscriptionPanel);

		memberAge.setForeground(Color.white);
		memberAge.setFont(fontForOptions);
		menuPanel.add(memberAge);
		menuPanel.add(memberAgeField);

		memberBackButton = new JButton("BACK");
		memberBackButton.setForeground(Color.red);
		memberBackButton.setBackground(Color.green);
		memberBackButton.setFont(fontForQuestion);
		menuPanel.add(memberBackButton);
		memberBackButton.addActionListener(this);
		
		
		memberMenuButton = new JButton("SUBMIT");
		memberMenuButton.setForeground(Color.red);
		memberMenuButton.setBackground(Color.green);
		memberMenuButton.setFont(fontForQuestion);

		menuPanel.add(memberMenuButton);	
		memberMenuButton.addActionListener(this);
		add(menuPanel);
	}

	public void itemStateChanged(ItemEvent event)
	{
		if(event.getItem() == memberSubscriptionYes)
			memberSubscriptionField ="yes";
		else
			memberSubscriptionField ="no";
	}




	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == memberMenuButton)	
		{
			try
			{
				testIntegerInput = Integer.parseInt(memberAgeField.getText());
			}
			catch (NumberFormatException nfe) 
			{
				JOptionPane.showMessageDialog(null, "Number format required", "wrong data type", JOptionPane.WARNING_MESSAGE);
				return;
			}


			if (memberFirstNameField.getText().equals("") || memberSurnameField.getText().equals("")|| memberStreetField.getText().equals("") || memberTownField.getText().equals("") 
					|| memberCountyField.getText().equals("")  ||  
					memberClubPosition.getSelectedItem().equals("") || memberAgeField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "All fields must be filled in.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);

				try 
				{
					AudioInputStream  audioInputForError = AudioSystem.getAudioInputStream(new File("gui/clubMembers/sounds/error.wav"));
					Clip clip = AudioSystem.getClip(); 
					clip.open(audioInputForError);
					clip.start();
				}
				catch(Exception ex)
				{
					System.out.println("Error with playing sound.");
					ex.printStackTrace( );
				}
				return;
			}

			if (!memberFirstNameField.getText().equals("") && !memberSurnameField.getText().equals("") && !memberStreetField.getText().equals("") && !memberTownField.getText().equals("") 
					&& !memberCountyField.getText().equals("")&& 
					!memberClubPosition.getSelectedItem().equals("") && !memberAgeField.getText().equals(""))	
			{

				try
				{
					ObjectInputStream loadInMembersFromDocument = new ObjectInputStream(new FileInputStream("myList.txt")); 					
					getMembersFromDocument = (LinkedList<Members>) loadInMembersFromDocument.readObject();
					TrekClub.addAll(getMembersFromDocument);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error Loading File.", "FILE ERROR", JOptionPane.WARNING_MESSAGE);
				}

				addNewMember = new Members(memberFirstNameField.getText(), memberSurnameField.getText(), memberStreetField.getText(), memberTownField.getText(), memberCountyField.getText(),
						 memberSubscriptionField, memberClubPosition.getSelectedItem().toString(), Integer.parseInt(memberAgeField.getText()));
				TrekClub.add(addNewMember);	

				try 
				{
					ObjectOutputStream saveMembersToDocument = new ObjectOutputStream(new FileOutputStream("myList.txt"));
					saveMembersToDocument.writeObject(TrekClub);
				} 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Could Not Save File.", "SAVING ISSUE", JOptionPane.WARNING_MESSAGE);
				}
				this.setVisible(false);
				new MainMenu();
			}
		} 
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
	}
}
