package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CityView extends JFrame {
	private JButton upgrade;
	private JButton recruit;
	private JMenu build ;
	private JMenuItem farm;
	private JMenuItem market;
	private JMenuItem archeryRange;
	private JMenuItem stable;
	private JMenuItem barracks;
	private JPanel buildings = new JPanel();
	
	
	public CityView(String cityName) {
		this.setTitle(cityName);
		this.setBounds(600,60,400,500);
		JPanel main = new JPanel();
		 upgrade = new JButton("Upgrade Building");
		 recruit = new JButton("Recruit Unit");
		 build = new JMenu("Build");
		 farm = new JMenuItem("Farm");
		 market = new JMenuItem("Market");
		 archeryRange = new JMenuItem("ArcheryRange");
		 stable = new JMenuItem("Stable");
		 barracks = new JMenuItem("Barracks");
		JMenuBar bar = new JMenuBar();
		bar.add(build);
		
		build.add(market);
		build.add(farm);
		build.add(archeryRange);
		build.add(stable);
		build.add(barracks);
		
		main.add(upgrade);
		main.add(recruit);
		main.add(bar);
		this.add(main);
		this.add(buildings,BorderLayout.SOUTH);
		buildings.setLayout(new GridLayout(2,5));
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(false);
		validate();
		repaint();
		
	}
	
   public void addBuilding (String type, GameGUI g) {
	   JButton newBuilding = new JButton(type);
	   newBuilding.addActionListener(g);
	   buildings.add(newBuilding);
	   
	   if(type.equals("ArcheryRange"))
		   build.remove(archeryRange);
	   else if(type.equals("Market"))
		   build.remove(market);
	   else if(type.equals("Stable"))
		   build.remove(stable);
	   else if(type.equals("Farm"))
		   build.remove(farm);
	   else
		   build.remove(barracks);
	   
	   validate();
	   repaint();
   }
   
   public void colorUpgrade (JButton upgraded) {
	
	   upgraded.setBackground(Color.RED);
   }
	
	
	
	public JButton getUpgrade() {
		return upgrade;
	}



	public JButton getRecruit() {
		return recruit;
	}



	public JMenu getBuild() {
		return build;
	}



	public JMenuItem getFarm() {
		return farm;
	}



	public JMenuItem getMarket() {
		return market;
	}



	public JMenuItem getArcheryRange() {
		return archeryRange;
	}



	public JMenuItem getStable() {
		return stable;
	}



	public JMenuItem getBarracks() {
		return barracks;
	}



	public static void main(String[] args) {
//		CityView c = new CityView(title);
	}
	

}
