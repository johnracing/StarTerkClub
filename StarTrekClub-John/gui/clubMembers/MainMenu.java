package clubMembers;

import javax.swing.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame  implements  ItemListener
{

	private static final long serialVersionUID = 1L;
	JLabel forImage;
	private JPanel menuPanel = new JPanel();
	private JLabel welcome = new JLabel("          \n          ");
	private JLabel selectQuestion = new JLabel("Select from the following:");
	private JLabel blankJLabel1 = new JLabel("\n\n\n");
	private JLabel blankJLabel2 = new JLabel(" ");

	private JRadioButton buttonToAddNewMember = new JRadioButton("  Add New Member ");
	private JRadioButton buttonToViewMember = new JRadioButton("  View Member Details ");
	private JRadioButton buttonToViewStats = new JRadioButton("  View Age Statistics ");
	private JRadioButton buttonToEditMember = new JRadioButton("  Edit Member Details ");
	private JRadioButton buttonToExit = new JRadioButton("  Exit ");

	Font fontForWelcome = new Font("Monospaced", Font.BOLD,  30);
	Font fontForQuestion = new Font("Verdana", Font.BOLD,  16);
	Font fontForOptions = new Font("Verdana", Font.BOLD,  14);

	NewMembers newMemberWindow = new NewMembers();
	ViewDetails viewOneMember;
	ViewStats viewClubStats;
	EditDetails editMemberDetails;

	public MainMenu()
	{
		super("Waterford Star Trek Fan-Club");
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("images/stbg.jpg"))));

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(460, 480);
		setLocation(300, 300);
		this.setVisible(true); 
		this.setResizable(false);
		newMemberWindow.setVisible(false);
		viewOneMember = new ViewDetails();
		viewOneMember.setVisible(false);
		viewClubStats = new ViewStats();
		viewClubStats.setVisible(false);
		editMemberDetails = new EditDetails();
		editMemberDetails.setVisible(false);

		Container myWindow = this.getContentPane();

		setLayout(new FlowLayout());
		welcome.setFont(fontForWelcome);
		myWindow.add(welcome);

		menu();	

		this.setContentPane(myWindow);
	}



	public void itemStateChanged(ItemEvent event)
	{
		if (event.getItem() != null)
			playSelectSound();

		if(event.getItem() == buttonToAddNewMember)
		{
			this.setVisible(false);
			newMemberWindow.setVisible(true); 
		}	

		if(event.getItem() == buttonToViewMember)
		{
			this.setVisible(false);
			viewOneMember.setVisible(true);	
		}

		if(event.getItem() == buttonToViewStats)
		{
			this.setVisible(false);
			viewClubStats.setVisible(true);
		}

		if(event.getItem() == buttonToEditMember)
		{
			this.setVisible(false);
			editMemberDetails.setVisible(true);
		}

		if(event.getItem() == buttonToExit)
		{
			playExitSound();

			try {
				Thread.sleep(2500);
			} 
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}



	public void playSelectSound()
	{
		try 
		{
			AudioInputStream  audioClickOnButton = AudioSystem.getAudioInputStream(new File("gui/clubMembers/sounds/beep02.wav"));
			Clip clip = AudioSystem.getClip(); 
			clip.open(audioClickOnButton);
			clip.start();
		}
		catch(Exception ex)
		{
			System.out.println("Error with playing sound.");
			ex.printStackTrace( );
		}
	}

	public void playExitSound()
	{
		try 
		{
			AudioInputStream  audioOnExit = AudioSystem.getAudioInputStream(new File("gui/clubMembers/sounds/exit.wav"));
			Clip clip = AudioSystem.getClip(); 
			clip.open(audioOnExit);
			clip.start();
		}
		catch(Exception ex)
		{
			System.out.println("Error with playing sound.");
			ex.printStackTrace( );
		}
	}


	public void menu()
	{
		BoxLayout menuPane = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);
		menuPanel.setBackground(new Color(0, 0, 0));
		menuPanel.setLayout(menuPane);

		selectQuestion.setFont(fontForQuestion);
		selectQuestion.setForeground(Color.white);
		menuPanel.add(blankJLabel1);
		menuPanel.add(selectQuestion);
		menuPanel.add(blankJLabel2);

		buttonToAddNewMember.addItemListener(this);
		buttonToViewMember.addItemListener(this);
		buttonToViewStats.addItemListener(this);
		buttonToEditMember.addItemListener(this);
		buttonToExit.addItemListener(this);

		ButtonGroup mainButtonGroup = new ButtonGroup();

		buttonToAddNewMember.setOpaque(false);
		buttonToAddNewMember.setFont(fontForOptions);
		buttonToAddNewMember.setForeground(Color.white);
		mainButtonGroup.add(buttonToAddNewMember);
		menuPanel.add(buttonToAddNewMember);

		buttonToViewMember.setOpaque(false);
		buttonToViewMember.setFont(fontForOptions);
		buttonToViewMember.setForeground(Color.white);
		mainButtonGroup.add(buttonToViewMember);
		menuPanel.add(buttonToViewMember);

		buttonToViewStats.setOpaque(false);
		buttonToViewStats.setFont(fontForOptions);
		buttonToViewStats.setForeground(Color.white);
		mainButtonGroup.add(buttonToViewStats);
		menuPanel.add(buttonToViewStats);

		buttonToEditMember.setOpaque(false);
		buttonToEditMember.setFont(fontForOptions);
		buttonToEditMember.setForeground(Color.white);
		mainButtonGroup.add(buttonToEditMember);
		menuPanel.add(buttonToEditMember);	

		buttonToExit.setOpaque(false);
		buttonToExit.setFont(fontForOptions);
		buttonToExit.setForeground(Color.white);
		mainButtonGroup.add(buttonToExit);
		menuPanel.add(buttonToExit);


		add(menuPanel);	
	}
}

