package adventofcodejava.entity;

import java.awt.Point;

public class CombatTile {
	private Point location;
	
	private boolean canEnter;
	
	private Combatant occupant;
	
	public CombatTile(Point location, boolean canEnter, Combatant occupant) {
		this.location = location;
		this.canEnter = canEnter;
		this.occupant = occupant;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public boolean getCanEnter() {
		return canEnter;
	}
	
	public Combatant getOccupant() {
		return occupant;
	}
	
	public void removeOccupant() {
		this.occupant = null;
	}
	
	public void setOccupant(Combatant combatant) {
		this.occupant = combatant;
	}
}
