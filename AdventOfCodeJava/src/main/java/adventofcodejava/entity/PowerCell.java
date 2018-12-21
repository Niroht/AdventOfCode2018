package adventofcodejava.entity;

import java.awt.Point;

public class PowerCell {
	public Point location;
	
	private int totalPower;
	
	public PowerCell(Point location, int serialNumber) {
		this.location = location;
		
		int rackId = location.x + 10;
		int powerLevel = rackId * location.y;
		powerLevel += serialNumber;
		
		powerLevel *= rackId;
		int hundredsDigit = (powerLevel % 1000) / 100;
		powerLevel = hundredsDigit;
		powerLevel -= 5;
		
		totalPower = powerLevel;
	}
	
	public int getTotalPower() {
		return totalPower;
	}
}
