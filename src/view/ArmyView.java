package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import units.Army;
import units.Unit;

public class ArmyView extends JFrame {
	JButton checkTarget=new JButton("Target");
	JButton checkDistance=new JButton("Distance to target ");
	private JPanel units;
	
	public JPanel getUnits() {
		return units;
	}

	public void setUnits(JPanel units) {
		this.units = units;
	}
	private ArrayList <JButton>uni=new ArrayList<JButton>();
	private ArrayList <UnitView>view=new ArrayList<UnitView>();
	private JButton setTarget=new JButton("Set target");
	JMenuBar bar=new JMenuBar();
	JMenu choose=new JMenu("Choose Target");
	JMenuItem cairo=new JMenuItem("Cairo");
	JMenuItem rome=new JMenuItem("Rome");
	JMenuItem sparta=new JMenuItem("Sparta");
	JTextArea header = new JTextArea();
	
	
	
	public ArmyView() {
		this.setBounds(600,60,400,500);
		JPanel main = new JPanel();
	//	main.add(checkDistance);
	//	main.add(checkTarget);
		//header.setBackground(Color.lightGray);
		header.setEditable(false);
		main.add(header);
		this.add(main);
		units=new JPanel();
		this.add(units,BorderLayout.SOUTH);
		units.setLayout(new GridLayout(2,5));
		main.add(setTarget);
		setTarget.setVisible(false);
		choose.add(cairo);
		choose.add(rome);
		choose.add(sparta);
		bar.add(choose);
		main.add(bar);
		bar.setVisible(false);
		revalidate();
		repaint();
		
		
	}
	
	public void defendingheadersetter(Army army) {
		header.setText(army.defendingtoString());
	}
	public void headerSetter(Army army) {
		
		header.setText(army.toString());
	}
	public void addUnit(String type,JButton u) {
		units.add(u);
		revalidate();
		repaint();
	}
	
	public ArrayList<JButton> getUni() {
		return uni;
	}
	public void setUni(ArrayList<JButton> uni) {
		this.uni = uni;
	}
	public static void main(String[] args) {
		new ArmyView();
	}
	public ArrayList<UnitView> getView() {
		return view;
	}
	public JButton getSetTarget() {
		return setTarget;
	}
	
	

}
