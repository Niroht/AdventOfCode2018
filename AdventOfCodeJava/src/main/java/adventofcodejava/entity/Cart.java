package adventofcodejava.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public class Cart {
	private Point location;
	
	private Point velocity;
	
	private TurnDirection nextTurn;
	
	private List<TrackSegment> trackSegments = new ArrayList<>();
	
	public Cart(Point location, Point velocity) {
		this.location = location;
		this.velocity = velocity;
		
		this.nextTurn = TurnDirection.LEFT;
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public List<TrackSegment> getTrackSegments(){
		return this.trackSegments;
	}
	
	public void moveNext() {
		List<TrackSegment> matchingSegments = trackSegments
				.stream()
				.filter(x -> x.getLocation().equals(location))
				.collect(Collectors.toList());
		
		if (matchingSegments.size() != 1) {
			throw new IllegalArgumentException(CollectionUtils.isEmpty(matchingSegments) ? "Cart Derailed" : "Cart Split Tracks");
		}
		
		getNewVelocity(matchingSegments.get(0));
		
		location = new Point(location);
		location.translate(velocity.x, velocity.y);
	}

	private void getNewVelocity(TrackSegment currentSegment) {
		int currentXVelocity = velocity.x;
		int currentYVelocity = velocity.y;
		switch (currentSegment.getDirection()) {
			case POSITIVE_SLOPE:
				velocity.x = currentYVelocity;
				velocity.y = currentXVelocity;
				break;
			case NEGATIVE_SLOPE:
				velocity.x = -currentYVelocity;
				velocity.y = -currentXVelocity;
				break;
			case INTERSECTION:
				switch (nextTurn) {
				case LEFT:
					// switch pos/neg if moving on Y axis
					velocity.x = Math.abs(currentYVelocity) > 0 ? -currentYVelocity : currentYVelocity;
					velocity.y = currentXVelocity;
					nextTurn = TurnDirection.STRAIGHT;
					break;
				case STRAIGHT:
					nextTurn = TurnDirection.RIGHT;
					break;
				case RIGHT:
					// switch pos/neg if moving on X axis 
					velocity.x = currentYVelocity;
					velocity.y = Math.abs(currentXVelocity) > 0 ? -currentXVelocity : currentXVelocity;
					nextTurn = TurnDirection.LEFT;
					break;
				default:
					break;
				}
				break;
			default:
				break;
		}
	}
}
