package units;

public interface ArmyListener {
	public void onRelocateUnit(Unit u, Army army);
	public void onHandleAttackedUnit();

}
