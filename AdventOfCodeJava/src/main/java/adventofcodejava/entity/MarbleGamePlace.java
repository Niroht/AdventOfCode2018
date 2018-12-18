package adventofcodejava.entity;

public class MarbleGamePlace {
	private MarbleGamePlace left;
	
	private MarbleGamePlace right;
	
	private int value;
	
	public MarbleGamePlace() {
		this.left = this;
		this.right = this;
		
		this.value = 0;
	}
	
	public MarbleGamePlace(MarbleGamePlace left, MarbleGamePlace right, int value) {
		this.left = left;
		this.right = right;
		
		this.value = value;
	}
	
	public void setLeft(MarbleGamePlace place) {
		this.left = place;
	}
	
	public void setRight(MarbleGamePlace place) {
		this.right = place;
	}
	
	public MarbleGamePlace getLeft(int distance) {
		switch (distance) {
			case 0:
				return this;
			case 1:
				return this.left;
			default:
				return this.left.getLeft(distance -1);
		}
	}
	
	public MarbleGamePlace getRight(int distance) {
		switch (distance) {
			case 0:
				return this;
			case 1:
				return this.right;
			default:
				return this.right.getLeft(distance -1);
		}
	}
	
	public void insertRight(int value) {
		MarbleGamePlace newPlace = new MarbleGamePlace(this, this.right, value);
		
		this.right.setLeft(newPlace);
		this.right = newPlace;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void remove() {
		this.left.setRight(this.right);
		this.right.setLeft(this.left);
	}
}
