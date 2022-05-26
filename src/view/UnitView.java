package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UnitView extends JPanel{
	private JLabel info=new JLabel();
	private JButton initiateArmy=new JButton("initiateArmy");
	
	public JLabel getInfo() {
		return info;
	}

	public JButton getInitiateArmy() {
		return initiateArmy;
	}

	public UnitView(){
		this.add(info);
		this.add(initiateArmy);
	}

}
