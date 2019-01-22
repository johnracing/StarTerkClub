package clubMembers;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.*;

public class ViewStats extends JFrame  implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JPanel menuPanel = new JPanel();
	private JLabel title = new JLabel("Age Statistics");
	private JLabel averageAge = new JLabel("Average Age : ");
	private JLabel numberOfSeniors = new JLabel("No. of Seniors : ");
	private JLabel overEighteens = new JLabel("No. of Over 18's : ");
	private JLabel underTwelves = new JLabel("No. of under 12's : ");
	private JLabel totalNumberOfMembers= new JLabel("Total members : ");
	private JLabel blankJLabel1 = new JLabel(" ");
	private JLabel blankJLabel2 = new JLabel(" ");

	Font fontForTitle = new Font("Verdana", Font.BOLD,  16);
	Font fontForJLabels = new Font("Verdana", Font.BOLD,  14);
	Font fontForResults = new Font("Monospaced", Font.BOLD,  16);
	private JTextField textAverageAge = new JTextField();
	private JTextArea  textNumberOfSeniors = new JTextArea ();
	private JTextArea  textNumbersofOver18s = new JTextArea ();
	private JTextArea  textNumberOfUnder12 = new JTextArea ();
	private JTextArea  textTotalOfMembers = new JTextArea ();
	private JButton memberBackButton;
	private LinkedList<Members> StarTrekClub;
	private int clubAverageAge, clubNumberOfSeniors, clubNumbersofOver18s, clubNumberOfUnder12, clubTotalOfMembers, getEachAge;

	public ViewStats() 
	{
		super("Waterford Star Trek Fan-Club- View Stats");
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("images/statsbg.jpg"))));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(460, 480);
		setLocation(300, 300);
		this.setVisible(true);
		this.setResizable(false);
		Container myWindow = this.getContentPane();

		stats();

		this.setContentPane(myWindow);
	}

	public void stats()
	{
		menuPanel.setLayout(null);
		menuPanel.setBounds(100, 40, 330, 280);
		GridLayout menuPane = new GridLayout(0, 2, 5, 8);
		menuPanel.setBackground(new Color(0, 0, 0));
		menuPanel.setLayout(menuPane);
		title.setForeground(Color.red);
		title.setFont(fontForTitle);
		menuPanel.add(title);
		menuPanel.add(blankJLabel1);
		menuPanel.setOpaque(false);
		calculations();




		averageAge.setForeground(Color.white);
		averageAge.setFont(fontForJLabels);
		menuPanel.add(averageAge);
		textAverageAge.setBackground(new Color(0, 0, 0));
		textAverageAge.setForeground(Color.white);
		textAverageAge.setEditable(false);
		textAverageAge.setFont(fontForResults);
		menuPanel.add(textAverageAge);

		numberOfSeniors.setForeground(Color.white);
		numberOfSeniors.setFont(fontForJLabels);
		menuPanel.add(numberOfSeniors);		
		textNumberOfSeniors.setBackground(new Color(0, 0, 0));
		textNumberOfSeniors.setForeground(Color.white);
		textNumberOfSeniors.setEditable(false);
		textNumberOfSeniors.setFont(fontForResults);
		menuPanel.add(textNumberOfSeniors);

		overEighteens.setForeground(Color.white);
		overEighteens.setFont(fontForJLabels);
		menuPanel.add(overEighteens);
		textNumbersofOver18s.setBackground(new Color(0, 0, 0));
		textNumbersofOver18s.setForeground(Color.white);
		textNumbersofOver18s.setEditable(false);
		textNumbersofOver18s.setFont(fontForResults);
		menuPanel.add(textNumbersofOver18s);

		underTwelves.setForeground(Color.white);
		underTwelves.setFont(fontForJLabels);
		menuPanel.add(underTwelves);
		textNumberOfUnder12.setBackground(new Color(0, 0, 0));
		textNumberOfUnder12.setForeground(Color.white);
		textNumberOfUnder12.setEditable(false);
		textNumberOfUnder12.setFont(fontForResults);
		menuPanel.add(textNumberOfUnder12);

		totalNumberOfMembers.setForeground(Color.white);
		totalNumberOfMembers.setFont(fontForJLabels);
		menuPanel.add(totalNumberOfMembers);
		textTotalOfMembers.setBackground(new Color(0, 0, 0));
		textTotalOfMembers.setForeground(Color.white);
		textTotalOfMembers.setEditable(false);
		textTotalOfMembers.setFont(fontForResults);
		menuPanel.add(textTotalOfMembers);

		menuPanel.add(blankJLabel2);

		memberBackButton = new JButton("BACK");
		memberBackButton.setForeground(Color.yellow);
		memberBackButton.setBackground(Color.blue);
		memberBackButton.setFont(fontForJLabels);
		menuPanel.add(memberBackButton);
		memberBackButton.addActionListener(this);


		add(menuPanel);
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
	}

	public void calculations()
	{
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
		clubTotalOfMembers = StarTrekClub.size();
		textTotalOfMembers.setText("" + clubTotalOfMembers);

		while (iterator.hasNext())
		{
			getEachAge = iterator.next().getAge();
			clubAverageAge = clubAverageAge + getEachAge;
			if (getEachAge >= 65)
			{
				clubNumberOfSeniors ++;
			}
			if (getEachAge >= 18)
			{
				clubNumbersofOver18s ++;
			}
			if (getEachAge <= 12)
			{
				clubNumberOfUnder12 ++;
			}

		}
		clubAverageAge = clubAverageAge / clubTotalOfMembers;
		textAverageAge.setText("" + clubAverageAge);
		textNumberOfSeniors.setText("" + clubNumberOfSeniors);
		textNumbersofOver18s.setText("" + clubNumbersofOver18s);
		textNumberOfUnder12.setText("" + clubNumberOfUnder12);
	}
}
