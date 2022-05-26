package engine;

import units.Army;

public interface GameListener {
	public void onEndTurn(Game g);
	public void onTargetCity( Army army, String targetName );
	public void onAutoResolve(Army army, String city);
	public void onOccupy(Game g);
	public void onAutoResolveLog(String s);

}
