package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartOfGame extends JFrame{
	
	public StartOfGame() {
		JPanel mainP = new JPanel();
		mainP.setLayout(new GridLayout(5,1));
		mainP.setVisible(true);
		mainP.setSize(100, 50);
		mainP.setBackground(Color.RED);
		this.setVisible(true);
		this.add(mainP);
		
		JButton b = new JButton();
		b.setPreferredSize(new Dimension(40, 40));
		mainP.add(b);
	}
	
	public static void main(String[] args) {
		StartOfGame s = new StartOfGame();
	}

}
