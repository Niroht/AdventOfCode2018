package adventofcodejava.entity;

import java.awt.Point;

public class TrackSegment {
	private TrackDirection direction;
	
	private Point location;
	
	public TrackSegment(TrackDirection direction, Point location) {
		this.direction = direction;
		this.location = location;
	}
	
	public TrackDirection getDirection() {
		return this.direction;
	}
	
	public Point getLocation() {
		return this.location;
	}
}
