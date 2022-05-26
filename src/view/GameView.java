package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.City;
import engine.Game;

public class GameView extends JFrame {
	private JTextField nameInput = new JTextField();
	private JButton startGameB = new JButton("Start Game");
	private ArrayList<JButton> cities = new ArrayList<>();
	private JPanel mainP;
	// Alaa s
	private JButton autoResolve = new JButton("Auto Resolve");
	private JPanel autoResolveLog = new JPanel();
	private JTextArea autoResolveLogText = new JTextArea();
	// Alaa e
	private JPanel ButtonsP;
	private JPanel headerP = new JPanel();
	private JTextArea header;
	private String chosenCity;
	private JButton playerCity = new JButton();
	private JButton city2 = new JButton();
	private JButton city3 = new JButton();
	private JButton playerDefendingArmy = new JButton();
	private JButton city2DefendingArmy = new JButton();
	private JButton city3DefendingArmy = new JButton();
	private JPanel besiegingCity2 = new JPanel();
	private JPanel besiegingCity3 = new JPanel();
	private JPanel idleArmy = new JPanel();
	private JPanel marchingArmy = new JPanel();
	//
	private JPanel others = new JPanel();
	private Boolean flag = false;
	private ArrayList<JButton> city2Units = new ArrayList<JButton>();
	private ArrayList<JButton> city3Units = new ArrayList<JButton>();
	private JButton initiateArmy = new JButton("initiateArmy");
	private JButton relocateUnit = new JButton("Relocate Unit");
	private JButton endTurn = new JButton("End Turn");

	public GameView() {
		this.setVisible(true);
		this.setLayout(new GridLayout(3, 1));
		mainP = new JPanel();
		mainP.setLayout(new GridLayout());
		mainP.setVisible(true);
		mainP.setSize(100, 50);

		ButtonsP = new JPanel();
		ButtonsP.setLayout(new GridLayout(0, 1));
		ButtonsP.setVisible(true);

		setTitle("Empire");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);

		JTextArea nameTitle = new JTextArea();
		nameTitle.setText("Enter Your Name");
		nameTitle.setEditable(false);

		JTextArea cityTitle = new JTextArea();
		cityTitle.setText("Choose Your City");
		cityTitle.setEditable(false);
		cityTitle.setAlignmentX(CENTER_ALIGNMENT);
		JButton b1 = new JButton();
		JButton b2 = new JButton();
		JButton b3 = new JButton();
		b1.setText("Cairo");
		b1.setSize(60, 20);
		b1.setAlignmentX(CENTER_ALIGNMENT);
		b2.setText("Sparta");
		b2.setSize(60, 20);
		b2.setAlignmentX(CENTER_ALIGNMENT);
		b3.setText("Rome");
		b3.setSize(60, 20);
		b3.setAlignmentX(CENTER_ALIGNMENT);

		nameInput.setBackground(Color.white);
		this.add(mainP);
		this.add(ButtonsP);
		mainP.add(nameTitle);
		mainP.add(nameInput);
		ButtonsP.add(cityTitle);
		ButtonsP.add(b1);
		ButtonsP.add(b2);
		ButtonsP.add(b3);
		mainP.add(startGameB);
		cities.add(b3);
		cities.add(b2);
		cities.add(b1);

		idleArmy.add(initiateArmy);
		idleArmy.add(relocateUnit);
		validate();
		repaint();

	}
	//Alaa s

	public JPanel getAutoResolveLog() {
		return autoResolveLog;
	}

	public void setAutoResolveLog(JPanel autoResolveLog) {
		this.autoResolveLog = autoResolveLog;
	}
	//Alaa e

	public JButton getRelocateUnit() {
		return relocateUnit;
	}

	public void setRelocateUnit(JButton relocateUnit) {
		this.relocateUnit = relocateUnit;
	}

	public void findChosenCity(String cityName) {
		chosenCity = cityName;
	}

	public void gameStarted(int currentTurn, Game game) {
//		this.ButtonsP.setVisible(false);
//		this.mainP.setVisible(false);
//		 gameP.add(new JLabel(new ImageIcon("paper.jpg")));
		flag = true;
		this.remove(ButtonsP);
		this.remove(mainP);

		this.setLayout(new BorderLayout());
		header = new JTextArea();
		header.setEditable(false);
		header.setBackground(Color.GRAY);

		header.setText("Player Name:\t" + nameInput.getText() + "\t\t\t\t Current Turn:\t" + currentTurn + "\t\tGold:  "
				+ "5000" + "\t\tFood:  " + "0");

		JPanel mapP = new JPanel();
		mapP.setBackground(Color.black);
		mapP.setLayout(new GridLayout(0, 3));

		JTextArea idleText = new JTextArea("Idle Army:");
		idleArmy.add(idleText);
		JTextArea marchingText = new JTextArea("Marching Army:");
		marchingArmy.add(marchingText);

		JTextArea city2Besieging;
		JTextArea city3Besieging;

		if (chosenCity.equals("Cairo")) {
			playerCity.setText(chosenCity);

			city2.setText("Sparta");
			city2Besieging = new JTextArea("Besieging Sparta:");
			besiegingCity2.add(city2Besieging);

			city3.setText("Rome");
			city3Besieging = new JTextArea("Besieging Rome:");
			besiegingCity3.add(city3Besieging);

		} else if (chosenCity.equals("Sparta")) {
			playerCity.setText(chosenCity);

			city2.setText("Cairo");
			city2Besieging = new JTextArea("Besieging Cairo:");
			besiegingCity2.add(city2Besieging);

			city3.setText("Rome");
			city3Besieging = new JTextArea("Besieging Rome:");
			besiegingCity3.add(city3Besieging);

		} else {
			playerCity.setText(chosenCity);
			city2.setText("Cairo");
			city2Besieging = new JTextArea("Besieging Cairo:");
			besiegingCity2.add(city2Besieging);

			city3.setText("Sparta");
			city3Besieging = new JTextArea("Besieging Sparta:");
			besiegingCity3.add(city3Besieging);
		}

		this.add(mapP);
		mapP.add(city3);
		mapP.add(others);
		others.add(city3DefendingArmy);
		others.add(city2DefendingArmy);
		city3DefendingArmy.setText(city3.getText() + " defending army");
		city2DefendingArmy.setText(city2.getText() + " defending army");
		// Alaa start
		others.add(autoResolve);
		// Alaa end
		mapP.add(city2);
		mapP.add(besiegingCity3);
		mapP.add(marchingArmy);
		mapP.add(besiegingCity2);
		mapP.add(idleArmy);
		idleArmy.add(playerDefendingArmy);
		playerDefendingArmy.setText(chosenCity + " defending army");
		mapP.add(playerCity);
		mapP.add(new JPanel());
		this.add(headerP, BorderLayout.NORTH);
		headerP.add(header, BorderLayout.NORTH);
		headerP.add(endTurn);
		headerP.setBackground(Color.GRAY);

		/*
		 * for(City c: game.getAvailableCities()) {
		 * if(!game.getPlayer().getControlledCities().contains(c)) {
		 * if(city2.getText().toLowerCase().equals(c.getName())) for(int
		 * i=0;i<c.getDefendingArmy().getUnits().size();i++) { JButton b=new JButton();
		 * if(c.getD) b.setText(c.getDefendingArmy().getUnits().get(i)); }
		 * 
		 * }
		 * 
		 * }
		 */

		validate();
		repaint();

	}

//	public void cairoView () {
//		JButton upgrade = new JButton("Upgrade Building");
//		JButton recruit = new JButton("Recruit Unit");
//		JMenu build = new JMenu("Build");
//		JMenuItem farm = new JMenuItem("Farm");
//		JMenuItem market = new JMenuItem("Market");
//		JMenuItem archeryRange = new JMenuItem("ArcheryRange");
//		JMenuItem stable = new JMenuItem("Stable");
//		JMenuItem barracks = new JMenuItem("Barracks");
//		
//		build.add(market);
//		build.add(farm);
//		build.add(archeryRange);
//		build.add(stable);
//		build.add(barracks);
//		
//	}

	public void headerSetter(int currentTurn, double gold, double food) {
		header.setText("Player Name:\t" + nameInput.getText() + "\t\t\t\t Current Turn:\t" + currentTurn + "\t\tGold:  "
				+ gold + "\t\tFood:  " + food);
	}
// Alaa s
	public void autoResolveLogPanelSetter(String s) {
		others.add(autoResolveLog);
		autoResolveLogText.setText(s);
		autoResolveLog.add(autoResolveLogText);
	}
	//Alaa e

	public JButton getPlayerCity() {
		return playerCity;
	}

	public JButton getCity2() {
		return city2;
	}

	public JButton getCity3() {
		return city3;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public ArrayList<JButton> getCities() {
		return cities;
	}

	public JTextField getNameInput() {
		return nameInput;
	}

	public JButton getStartGameB() {
		return startGameB;
	}

	public JPanel getBesiegingCity2() {
		return besiegingCity2;
	}

	public JPanel getBesiegingCity3() {
		return besiegingCity3;
	}

	public JPanel getIdleArmy() {
		return idleArmy;
	}

	public JPanel getMarchingArmy() {
		return marchingArmy;
	}

	public JButton getPlayerDefendingArmy() {
		return playerDefendingArmy;
	}

	public JButton getCity2DefendingArmy() {
		return city2DefendingArmy;
	}

	public JButton getCity3DefendingArmy() {
		return city3DefendingArmy;
	}
	/*
	 * public void addArmy() { JButton army=new JButton("Army");
	 * this.armiesInitiated.add(army); idleArmy.add(army); }
	 */

	public static void main(String[] args) {
//	    GameView g = new GameView();
//		g.gameStarted();

	}

	// Alaa s

	public JButton getAutoResolve() {
		return autoResolve;
	}

	public JTextArea getAutoResolveLogText() {
		return autoResolveLogText;
	}

	public void setAutoResolveLogText(JTextArea autoResolveLogText) {
		this.autoResolveLogText = autoResolveLogText;
	}

	// Alaa e
	public JButton getEndTurn() {
		return endTurn;
	}

	public JButton getInitiateArmy() {
		return initiateArmy;
	}

}
