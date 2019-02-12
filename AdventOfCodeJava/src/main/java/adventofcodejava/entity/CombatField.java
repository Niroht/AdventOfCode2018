package adventofcodejava.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CombatField {
	List<CombatTile> map = new ArrayList<>();
	
	List<Combatant> combatants = new ArrayList<>();
	
	private int rounds = 0;
	
	public CombatField(String newlineDelimitedMapString, int elfPower) {
		String[] mapStringRows = newlineDelimitedMapString.split(System.lineSeparator());
		
		for (int yPosition = 0; yPosition > -mapStringRows.length; yPosition--) {
			String rowString = mapStringRows[-yPosition];
			for (int xPosition = 0; xPosition < rowString.length(); xPosition++) {
				char currentChar = rowString.charAt(xPosition);
				
				Combatant tileOccupant = null;
				
				if ('E' == currentChar) {
					tileOccupant = new Elf(new Point(xPosition, yPosition), this, elfPower); 
				} else if ('G' == currentChar) {
					tileOccupant = new Goblin(new Point(xPosition, yPosition), this);
				}
				
				if (tileOccupant != null) {
					combatants.add(tileOccupant);
				}
				
				map.add(new CombatTile(new Point(xPosition, yPosition), '#' != currentChar, tileOccupant));
				
			}
		}
	}
	
	public void incrementRounds() {
		rounds++;
	}
	
	public int getRounds() {
		return rounds;
	}
	
	public List<CombatTile> getMap(){
		return this.map;
	}
	
	public List<Combatant> getAllCombatants(){
		return this.combatants;
	}
	
	
	public List<Combatant> getOtherCombatants(Combatant combatant){
		return this
				.combatants
				.stream()
				.filter(x -> x != combatant && x.getClass() != combatant.getClass())
				.collect(Collectors.toList());
	}
	
	public void moveCombatant(Point oldPosition, Combatant occupant) {
		map.stream().filter(x -> x.getLocation().equals(oldPosition)).findFirst().get().removeOccupant();
		map.stream().filter(x -> x.getLocation().equals(occupant.getLocation())).findFirst().get().setOccupant(occupant);
	}
	
	public void removeDefeatedCombatants() {
		map.forEach(x -> {
			Combatant occupant = x.getOccupant();
			if (occupant != null && occupant.getHitPoints() <= 0) {
				x.removeOccupant();
				this.combatants.remove(occupant);
			}
		});
	}
}
