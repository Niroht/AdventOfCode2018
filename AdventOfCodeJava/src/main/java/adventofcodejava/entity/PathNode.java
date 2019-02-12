package adventofcodejava.entity;

import java.awt.Point;

public class PathNode {
	private Point location;
	
	private PathNode root;
	
	private boolean hasEnemy;
	
	public PathNode(Point location) {
		this.location = location;
		this.root = this;
		this.hasEnemy = false;
	}
	
	public PathNode(Point location, PathNode root, boolean hasEnemy) {
		this.location = location;
		this.root = root;
		this.hasEnemy = hasEnemy;
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public PathNode getRoot() {
		return this.root;
	}
	
	public boolean getHasEnemy() {
		return this.hasEnemy;
	}
}
