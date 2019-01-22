package clubMembers;

import java.io.Serializable;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.Font;


public class ViewDetails extends JFrame  implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JPanel menuPanel = new JPanel();
	private JPanel resultPanel = new JPanel();
	private JLabel memberPosition = new JLabel("Select field : ");
	private JLabel memberValue = new JLabel("Enter Value : ");
	private JLabel newMember = new JLabel("View Member");
	private JLabel memberResult = new JLabel("Member Details");
	private JLabel blankJLabel1 = new JLabel(" ");
	private JLabel blankJLabel2 = new JLabel(" ");
	Font fontForQuestion = new Font("Verdana", Font.BOLD,  16);
	Font fontForOptions = new Font("Verdana", Font.BOLD,  14);
	private JTextField memberInputField = new JTextField();
	private JTextArea  memberDetailsField = new JTextArea ("Type 'all' to view all members", 16, 58);
	private JButton memberMenuButton, memberBackButton;
	private JComboBox<String> memberField;
	private LinkedList<Members> StarTrekClub;
	private String memberDetailsReturned;
	private String  multipleMemberDetailsReturned = "";
	int placeInArrayList;





	public ViewDetails()
	{
		super("Waterford Star Trek Fan-Club- View Details");
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("images/stbg-find.jpg"))));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(460, 480);
		setLocation(300, 300);
		this.setVisible(true);
		this.setResizable(false);
		Container myWindow = this.getContentPane();

		search();

		this.setContentPane(myWindow);
	}




	public void search()
	{
		menuPanel.setLayout(null);
		menuPanel.setBounds(100, 40, 300, 150);
		GridLayout menuPane = new GridLayout(0, 2, 5, 8);
		menuPanel.setBackground(new Color(0, 0, 0));
		menuPanel.setLayout(menuPane);
		newMember.setForeground(Color.red);
		newMember.setFont(fontForQuestion);
		menuPanel.add(newMember);
		menuPanel.add(blankJLabel1);


		memberPosition.setForeground(Color.white);
		memberPosition.setFont(fontForOptions);
		menuPanel.add(memberPosition);
		memberField = new JComboBox<String>();
		memberField.addItem("First Name");
		memberField.addItem("Surname");
		memberField.addItem("Position");
		menuPanel.add(memberField);

		memberValue.setForeground(Color.white);
		memberValue.setFont(fontForOptions);
		menuPanel.add(memberValue);
		menuPanel.add(memberInputField);

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
		memberResult.setForeground(Color.white);
		memberResult.setFont(fontForQuestion);
		menuPanel.add(memberResult);
		menuPanel.add(blankJLabel2);

		resultPanel.setBounds(100, 190, 300, 450);
		GridLayout memberResultPanel = new GridLayout(2, 1, 2, 0);
		resultPanel.setLayout(memberResultPanel);
		resultPanel.setOpaque(false);
		memberDetailsField.setBackground(new Color(0, 0, 0));
		memberDetailsField.setForeground(Color.white);
		memberDetailsField.setFont(fontForQuestion);
		memberDetailsField.setEditable(false);
		memberDetailsField.setLineWrap(true);
		memberDetailsField.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(memberDetailsField);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		resultPanel.add(scroll);

		add(menuPanel);
		add(resultPanel);
	}


	// search through list and find result
	public void actionPerformed(ActionEvent event) 
	{ 

		if (event.getSource() == memberMenuButton)	
		{
			multipleMemberDetailsReturned = "";
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

			try
			{
				ObjectInputStream loadInMembersFromDocument = new ObjectInputStream(new FileInputStream("myList.txt")); 					
				StarTrekClub = (LinkedList<Members>) loadInMembersFromDocument.readObject();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Error Loading File.", "FILE ERROR", JOptionPane.WARNING_MESSAGE);
			}

			ListIterator<Members> iterator = StarTrekClub.listIterator();

			//TODO   can't see last member in the LinkList
			try
			{
				if (memberField.getSelectedItem().equals("First Name"))
				{
					while (iterator.hasNext())
					{					
						if (memberInputField.getText().equals("all"))
						{
							memberDetailsField.setText(StarTrekClub.toString());
							return;
						}
						if (iterator.next().getFirstName().equals(memberInputField.getText()))
						{
							iterator.previous();
							memberDetailsReturned = iterator.next().toString();
							placeInArrayList = StarTrekClub.indexOf(iterator.next());

							if (multipleMemberDetailsReturned.equals (""))
							{
								multipleMemberDetailsReturned = "Club ID : " + placeInArrayList +memberDetailsReturned + "\n";				
							}
							else  
							{
								multipleMemberDetailsReturned = multipleMemberDetailsReturned +  "Club ID : " + placeInArrayList + memberDetailsReturned + "\n";		
							}
						}
					}
					memberDetailsField.setText(multipleMemberDetailsReturned);
					return;	
				}

				if (memberField.getSelectedItem().equals("Surname"))
				{
					while (iterator.hasNext())
					{					
						if (iterator.next().getSurname().equals(memberInputField.getText()))
						{
							iterator.previous();
							memberDetailsReturned = iterator.next().toString();
							placeInArrayList = StarTrekClub.indexOf(iterator.next());

							if (multipleMemberDetailsReturned.equals (""))
							{
								multipleMemberDetailsReturned = "Club ID : " + placeInArrayList +memberDetailsReturned + "\n";				
							}
							else  
							{
								multipleMemberDetailsReturned = multipleMemberDetailsReturned +  "Club ID : " + placeInArrayList + memberDetailsReturned + "\n";		
							}
						}
					}
					memberDetailsField.setText(multipleMemberDetailsReturned);
					return;	
				}


				if (memberField.getSelectedItem().equals("Position"))
				{
					while (iterator.hasNext())
					{
						if (iterator.next().getPosition().equals(memberInputField.getText()))
						{
							iterator.previous();
							memberDetailsReturned = iterator.next().toString();
							placeInArrayList = StarTrekClub.indexOf(iterator.next());

							if (multipleMemberDetailsReturned.equals (""))
							{
								multipleMemberDetailsReturned = "Club ID : " + placeInArrayList +memberDetailsReturned + "\n";				
							}
							else  
							{
								multipleMemberDetailsReturned = multipleMemberDetailsReturned +  "Club ID : " + placeInArrayList + memberDetailsReturned + "\n";		
							}
						}
						memberDetailsField.setText(multipleMemberDetailsReturned);
						return;	
					}
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Something went wrong.\n Please try again", "Error", JOptionPane.WARNING_MESSAGE);
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
