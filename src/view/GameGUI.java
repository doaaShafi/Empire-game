package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Game;
import engine.GameListener;
import engine.Player;
import engine.PlayerListener;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.ArmyListener;
import units.Unit;
//Alaa
//I have made changes in the following classes:-   GameGUI , Game, GameListener , GameView  

public class GameGUI implements ActionListener, GameListener, PlayerListener, ArmyListener {
	GameView gv;
	Game g;
	String chosenCityName;
	CityView cairo = new CityView("Cairo");
	CityView sparta = new CityView("Sparta");
	CityView rome = new CityView("Rome");
	JButton selectedCity;
	JButton selectedBuilding;
	JButton selectedArmy;
	// JButton selectedArmy;
	JButton selectedUnit;
	ArmyView cairoA = new ArmyView();
	ArmyView romeA = new ArmyView();
	ArmyView spartaA = new ArmyView();
	String choosenTarget;
	int armyCounter = 1;
	// Alaa s
	boolean autoResolve = false;
	String chosenCityForAutoResolve;
	Army chosenArmyForAutoResolve;
	// Alaa e

	private ArrayList<JButton> armiesInitiated = new ArrayList<JButton>();
	private ArrayList<Army> armies = new ArrayList<Army>();
	private ArrayList<ArmyView> view1 = new ArrayList<ArmyView>();

	private ArrayList<JButton> uni = new ArrayList<JButton>();
	private ArrayList<UnitView> view = new ArrayList<UnitView>();
	private ArrayList<Unit> recruitedUnits = new ArrayList<Unit>();

	public GameGUI() {

		gv = new GameView();
		gv.getStartGameB().addActionListener(this);
		for (int i = 0; i < gv.getCities().size(); i++) {
			gv.getCities().get(i).addActionListener(this);
		}
		gv.getCity2().addActionListener(this);
		gv.getCity3().addActionListener(this);
		gv.getPlayerCity().addActionListener(this);
		gv.getPlayerDefendingArmy().addActionListener(this);
		gv.getCity2DefendingArmy().addActionListener(this);
		gv.getCity3DefendingArmy().addActionListener(this);

		cairo.getUpgrade().addActionListener(this);
		cairo.getRecruit().addActionListener(this);
		cairo.getFarm().addActionListener(this);
		cairo.getArcheryRange().addActionListener(this);
		cairo.getMarket().addActionListener(this);
		cairo.getStable().addActionListener(this);
		cairo.getBarracks().addActionListener(this);

		sparta.getUpgrade().addActionListener(this);
		sparta.getRecruit().addActionListener(this);
		sparta.getFarm().addActionListener(this);
		sparta.getArcheryRange().addActionListener(this);
		sparta.getMarket().addActionListener(this);
		sparta.getStable().addActionListener(this);
		sparta.getBarracks().addActionListener(this);

		rome.getUpgrade().addActionListener(this);
		rome.getRecruit().addActionListener(this);
		rome.getFarm().addActionListener(this);
		rome.getArcheryRange().addActionListener(this);
		rome.getMarket().addActionListener(this);
		rome.getStable().addActionListener(this);
		rome.getBarracks().addActionListener(this);

		gv.getInitiateArmy().addActionListener(this);
		gv.getRelocateUnit().addActionListener(this);
		gv.getEndTurn().addActionListener(this);
		// Alaa s
		gv.getAutoResolve().addActionListener(this);
		// Alaa e

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JMenuItem) {
			// build
			JMenuItem m = (JMenuItem) e.getSource();
			if (m.getText().equals("Market") || m.getText().equals("Farm") || m.getText().equals("ArcheryRange")
					|| m.getText().equals("Stable") || m.getText().equals("Barracks"))
				onBuild(m.getText(), selectedCity.getText());
			// choose target
			else if (m.getText().equals("Cairo") || m.getText().equals("Rome") || m.getText().equals("Sparta")) {
				choosenTarget = m.getText();

			} else if (m.getText().equals("initiataArmy")) {
				// to do

			}

		} else {
			JButton b = (JButton) e.getSource();
			if (!gv.getFlag()) {
				if (!b.getText().equals("Start Game")) {
					chosenCityName = b.getText();
				} else {

					if (chosenCityName == null)
						chosenCityName = "Cairo";
					if (gv.getNameInput().getText().equals(""))
						try {
							g = new Game("Unknown", chosenCityName);
							gv.getNameInput().setText("Unknown");
						} catch (IOException e1) {
							// won't happen
						}
					else
						try {
							g = new Game(gv.getNameInput().getText(), chosenCityName);
						} catch (IOException e1) {
							// won't happen
						}

					gv.findChosenCity(chosenCityName);
					gv.gameStarted(g.getCurrentTurnCount(), g);
					g.getPlayer().setListener(this);
					g.setListener(this);
					updateHeaders();

				}
			} else {
				if (b.getText().equals("End Turn")) {
					onEndTurn(g);
				} // Alaa s
				else if (b.getText().equals("Auto Resolve")) {
					JOptionPane.showMessageDialog(null, "Please click on the army Then click on the city");
					autoResolve = true;
				} else if (autoResolve) {
					if (b.getText().equals("Army")) {
						chosenArmyForAutoResolve = armies.get(armiesInitiated.indexOf(b));

					} else if (chosenCityForAutoResolve == null && chosenArmyForAutoResolve != null) {
						chosenCityForAutoResolve = b.getText();

						if (!chosenArmyForAutoResolve.getCurrentLocation().equals(chosenCityForAutoResolve)) {
							JOptionPane.showMessageDialog(null, "Army hasn't reached city yet!");

						} else {
							onAutoResolve(chosenArmyForAutoResolve, chosenCityForAutoResolve);
						}
						autoResolve = false;
						chosenArmyForAutoResolve = null;
						chosenCityForAutoResolve = null;
					} else {
						JOptionPane.showMessageDialog(null,
								"Something went wrong! \n Please try again starting by clicking auto resolve");
						chosenArmyForAutoResolve = null;
						chosenCityForAutoResolve = null;
					}
				}

				else /* Alaa e */ if (b.getText().equals("Cairo") || b.getText().equals("Rome")
						|| b.getText().equals("Sparta")) {
					selectedCity = b;
					for (City c : g.getPlayer().getControlledCities()) {

						if (c.getName().equals(b.getText())) {
							if (b.getText().equals("Cairo"))
								cairo.setVisible(true);
							else if (b.getText().equals("Rome"))
								rome.setVisible(true);
							else
								sparta.setVisible(true);
						}

					}

				} else if (b.getText().equals("Farm") || b.getText().equals("Market")
						|| b.getText().equals("ArcheryRange") || b.getText().equals("Stable")
						|| b.getText().equals("Barracks")) {
					selectedBuilding = b;
				} else if (selectedBuilding != null && b.getText().equals("Upgrade Building")) {
					City city = null;
					for (City c : g.getPlayer().getControlledCities()) {
						if (c.getName().equals(selectedCity.getText())) {
							city = c;
						}
					}
					Building building = null;
					for (MilitaryBuilding b1 : city.getMilitaryBuildings()) {
						if (selectedBuilding.getText().equals("ArcheryRange") && b1 instanceof ArcheryRange) {
							building = b1;
						} else if (selectedBuilding.getText().equals("Barracks") && b1 instanceof Barracks) {
							building = b1;
						} else if (selectedBuilding.getText().equals("Stable") && b1 instanceof Stable) {
							building = b1;
						}

					}
					for (EconomicBuilding b1 : city.getEconomicalBuildings()) {
						if (selectedBuilding.getText().equals("Market") && b1 instanceof Market) {
							building = b1;
						} else if (selectedBuilding.getText().equals("Fram") && b1 instanceof Farm) {
							building = b1;
						}

					}
					onUpgradeBuilding(building);
					selectedBuilding = null;
				} else if (selectedBuilding != null
						&& (selectedBuilding.getText().equals("Stable")
								|| selectedBuilding.getText().equals("ArcheryRange")
								|| selectedBuilding.getText().equals("Barracks"))
						&& b.getText().equals("Recruit Unit")) {
					onRecruitUnit(selectedBuilding.getText(), selectedCity.getText());
					selectedBuilding = null;
					this.updateHeaders();

				} else if (b.getText().equals("Cairo defending army")) {
					cairoA.setVisible(true);
					selectedArmy = b;
				} else if (b.getText().equals("Rome defending army")) {
					romeA.setVisible(true);
					selectedArmy = b;
				} else if (b.getText().equals("Sparta defending army")) {
					spartaA.setVisible(true);
					selectedArmy = b;
				} else if (b.getText().equals("Army")) {
					int i = armiesInitiated.indexOf(b);
					ArmyView n = view1.get(i);
					n.setVisible(true);
					selectedArmy = b;
				} else if (selectedArmy != null && (b.getText().equals("Archer")) || (b.getText().equals("Cavalry"))
						|| (b.getText().equals("Infantry"))) {
					selectedUnit = b;
					int index = uni.indexOf(b);
					UnitView v = view.get(index);
					if (selectedArmy.getText().equals("Cairo Defending Army")) {
						cairoA.add(v);
						cairoA.repaint();
						cairoA.revalidate();
					} else if (selectedArmy.getText().equals("Rome Defending Army")) {
						romeA.add(v);
						romeA.repaint();
						romeA.revalidate();
					} else if (selectedArmy.getText().equals("Sparta Defending Army")) {
						spartaA.add(v);
						spartaA.repaint();
						spartaA.revalidate();
					}
				} else if (selectedArmy != null && selectedUnit != null && b.getText().equals("info")) {
					// intiate army
				} else if (selectedArmy != null && selectedUnit != null && b.getText().equals("initiateArmy")) {
					City city = null;
					if (selectedArmy.getText().equals("Cairo defending army")) {
						for (City c : g.getAvailableCities())
							if (c.getName().equals("Cairo"))
								city = c;
						int i = uni.indexOf(selectedUnit);
						Unit u = recruitedUnits.get(i);
						onInitiateArmy(city, u);
						this.updateHeaders();
						cairoA.getUnits().remove(selectedUnit);
					} else if (selectedArmy.getText().equals("Rome defending army")) {
						for (City c : g.getAvailableCities())
							if (c.getName().equals("Rome"))
								city = c;
						int i = uni.indexOf(selectedUnit);
						Unit u = recruitedUnits.get(i);
						onInitiateArmy(city, u);
						this.updateHeaders();
						romeA.getUnits().remove(selectedUnit);
					} else {
						for (City c : g.getAvailableCities())
							if (c.getName().equals("Sparta"))
								city = c;
						int i = uni.indexOf(selectedUnit);
						Unit u = recruitedUnits.get(i);
						onInitiateArmy(city, u);
						this.updateHeaders();
						spartaA.getUnits().remove(selectedUnit);
					}
					selectedUnit = null;

					// onInitiateArmy(city, unit);
				} else if (selectedArmy != null && choosenTarget != null && b.getText().equals("Set target")) {
					int i = armiesInitiated.indexOf(selectedArmy);
					Army a = armies.get(i);
					onTargetCity(a, choosenTarget);
					choosenTarget = null;
					this.updateHeaders();
				} else if (selectedArmy != null && selectedUnit != null && b.getText().equals("Relocate Unit")) {

				}

			}

		}

	}

	public static void main(String[] args) {
		GameGUI gg = new GameGUI();

	}

	@Override
	public void onBuild(String type, String city) {
		try {
			g.getPlayer().build(type, city);
			if (city.equals("Cairo"))
				cairo.addBuilding(type, this);
			else if (city.equals("Sparta"))
				sparta.addBuilding(type, this);
			else
				rome.addBuilding(type, this);
			gv.headerSetter(g.getCurrentTurnCount(), g.getPlayer().getTreasury(), g.getPlayer().getFood());
		} catch (NotEnoughGoldException e) {
			JOptionPane.showMessageDialog(null, "Error: You don't have enough gold");
		}

	}

	@Override
	public void onInitiateArmy(City city, Unit unit) {
		if (g.getPlayer().getControlledCities().contains(city)) {
			g.getPlayer().initiateArmy(city, unit);
			JButton a = new JButton("Army");
			a.addActionListener(this);
			armiesInitiated.add(a);
			gv.getIdleArmy().add(a);
			Army r = g.getPlayer().getControlledArmies().get(g.getPlayer().getControlledArmies().size() - 1);
			armies.add(r);
			ArmyView v = new ArmyView();
			v.getSetTarget().setVisible(true);
			v.getSetTarget().addActionListener(this);
			v.bar.setVisible(true);
			v.cairo.addActionListener(this);
			v.sparta.addActionListener(this);
			v.rome.addActionListener(this);
			view1.add(v);
			gv.repaint();
			gv.revalidate();
		}

	}

	@Override
	public void onLaySiege(Army army, City city) {
		try {
			g.getPlayer().laySiege(army, city);
			int i = armies.indexOf(army);
			JButton b = armiesInitiated.get(i);
			if (gv.getCity2().getText().equals(city.getName()))
				gv.getBesiegingCity2().add(b);
			else
				gv.getBesiegingCity3().add(b);
			gv.getMarchingArmy().remove(b);
			gv.repaint();
			gv.revalidate();

		} catch (TargetNotReachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FriendlyCityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	public void updateHeaders() {
		gv.headerSetter(g.getCurrentTurnCount(), g.getPlayer().getTreasury(), g.getPlayer().getFood());
		for (City c : g.getAvailableCities()) {
			if (c.getName().equals("Cairo"))
				cairoA.defendingheadersetter(c.getDefendingArmy());
			else if (c.getName().equals("Sparta"))
				spartaA.defendingheadersetter(c.getDefendingArmy());
			else if (c.getName().equals("Rome"))
				romeA.defendingheadersetter(c.getDefendingArmy());
		}
		for (int i = 0; i < view1.size(); i++) {
			view1.get(i).headerSetter(armies.get(i));
		}
	}

	public void onEndTurn(Game g) {
		g.endTurn();
		updateHeaders();
/*Alaa*/gv.getAutoResolveLog().remove(gv.getAutoResolveLogText());
	}

	@Override
	public void onTargetCity(Army army, String targetName) {
		g.targetCity(army, targetName);
		int i = armies.indexOf(army);
		JButton b = armiesInitiated.get(i);
		gv.getMarchingArmy().add(b);
		gv.getIdleArmy().remove(b);
		gv.repaint();
		gv.revalidate();

	}

	@Override
	// Alaa s
	public void onAutoResolve(Army army, String city) {
		try {
			Army def = null;
			for (City c : g.getAvailableCities()) {
				if (c.getName().equals(city))
					def = c.getDefendingArmy();
			}
			g.autoResolve(army, def);
			this.updateHeaders();
			if(army.getUnits().size()==0) {
				gv.getMarchingArmy().remove(armiesInitiated.get(armies.indexOf(army)));
				
			}

		} catch (FriendlyFireException e) {
			JOptionPane.showMessageDialog(null, "Error: You are attacking your own city!");
		}

	}
	
	public void onAutoResolveLog(String s) {
		//System.out.println(s);
		gv.autoResolveLogPanelSetter(s);
	}
	//Alaa e

	@Override
	public void onOccupy(Game g) {

	}

	@Override
	public void onUpgradeBuilding(Building b) {
		try {
			g.getPlayer().upgradeBuilding(b);

			if (selectedCity.equals("Cairo"))
				cairo.colorUpgrade(selectedBuilding);
			else if (selectedCity.equals("Sparta"))
				sparta.colorUpgrade(selectedBuilding);
			else
				rome.colorUpgrade(selectedBuilding);
			gv.headerSetter(g.getCurrentTurnCount(), g.getPlayer().getTreasury(), g.getPlayer().getFood());
		} catch (NotEnoughGoldException e) {
			JOptionPane.showMessageDialog(null, "Error: You don't have enough gold");
		} catch (BuildingInCoolDownException e) {
			JOptionPane.showMessageDialog(null, "Error: Building in Cool Down");
		} catch (MaxLevelException e) {
			JOptionPane.showMessageDialog(null, "Error: This is the maximum level");
		}

	}

	@Override
	public void onRecruitUnit(String type, String city) {
		try {
			String unitType;
			if (type.equals("Barracks"))
				unitType = "Infantry";
			else if (type.equals("ArcheryRange"))
				unitType = "Archer";
			else
				unitType = "Cavalry";
			g.getPlayer().recruitUnit(unitType, city);
			Unit u = null;
			for (City c : g.getAvailableCities()) {

				if (c.getName().toLowerCase().equals(city.toLowerCase()))
					u = c.getDefendingArmy().getUnits().get(0);
			}

			recruitedUnits.add(u);
			UnitView v = new UnitView();
			JButton unit = new JButton(unitType);
			v.getInfo().setText(u.getCurrentSoldierCount() + u.getLevel() + u.getMaxSoldierCount() + type);
			view.add(v);
			uni.add(unit);
			unit.addActionListener(this);
			if (selectedCity.getText().equals("Cairo"))
				cairoA.addUnit(selectedBuilding.getText().toLowerCase(), unit);
			else if (selectedCity.getText().equals("Rome"))
				romeA.addUnit(selectedBuilding.getText().toLowerCase(), unit);
			else
				spartaA.addUnit(selectedBuilding.getText().toLowerCase(), unit);
			gv.headerSetter(g.getCurrentTurnCount(), g.getPlayer().getTreasury(), g.getPlayer().getFood());

		} catch (BuildingInCoolDownException e) {
			JOptionPane.showMessageDialog(null, "Error: Building in Cool Down");
		} catch (MaxRecruitedException e) {
			JOptionPane.showMessageDialog(null, "you cant add a new unit to this army");
			e.printStackTrace();
		} catch (NotEnoughGoldException e) {
			JOptionPane.showMessageDialog(null, "Error: You don't have enough gold");
		}

	}

	@Override
	public void onRelocateUnit(Unit u, Army army) {
		try {
			army.relocateUnit(u);
		} catch (MaxCapacityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onHandleAttackedUnit() {
		// TODO Auto-generated method stub

	}

	// public void addActions(ArrayList<JButtons> )

}
