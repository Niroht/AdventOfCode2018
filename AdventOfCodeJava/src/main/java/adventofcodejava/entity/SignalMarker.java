package adventofcodejava.entity;

import java.awt.Point;

public class SignalMarker {
	public Point currentLocation;
	
	public Point velocity;
	
	public SignalMarker(Point location, Point velocity) {
		this.currentLocation = location;
		this.velocity = velocity;
	}
}
