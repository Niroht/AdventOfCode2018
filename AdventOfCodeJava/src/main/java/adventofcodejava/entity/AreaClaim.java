package adventofcodejava.entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class AreaClaim {
	private Point origin;
	
	private int width;
	
	private int height;
	
	private int id;
	
	Rectangle area;
	
	List<Point> coveredPoints = new ArrayList<>();

	public AreaClaim(int id, Point origin, int width, int height) {
		super();
		this.id = id;
		this.origin = origin;
		this.width = width;
		this.height = height;
		
		area = new Rectangle(origin.x + 1, origin.y + 1, width, height);
	}
	
	public int getId() {
		return id;
	}
	
	public Rectangle getArea() {
		return area;
	}
	
	public List<Point> getCoveredPoints(){
		if (CollectionUtils.isNotEmpty(coveredPoints)){
			return coveredPoints;
		}
		
		for (int row = origin.y; row < origin.y + height; row++) {

			for (int column = origin.x; column < origin.x + width; column++) {
				coveredPoints.add(new Point(column, row));
			}
		}
		
		return coveredPoints;
	}
}
