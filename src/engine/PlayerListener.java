package engine;

import buildings.Building;
import units.Army;
import units.Unit;

public interface PlayerListener {
	public void onBuild(String type, String city);
	public void onRecruitUnit(String type, String city);
	public void onUpgradeBuilding(Building b );
	public void onInitiateArmy(City city, Unit unit);
	public void onLaySiege(Army army, City city);

}
