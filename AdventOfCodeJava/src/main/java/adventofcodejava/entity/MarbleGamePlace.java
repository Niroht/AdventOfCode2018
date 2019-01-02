package adventofcodejava.entity;

public class MarbleGamePlace extends DoublyLinkedCircularListItem<MarbleGamePlace> {	
	
	public MarbleGamePlace() {
		super();
	}
	
	public MarbleGamePlace(MarbleGamePlace left, MarbleGamePlace right, int value) {
		super(left, right, value);
	}
	
	public void insertRight(int value) {
		MarbleGamePlace newPlace = new MarbleGamePlace(this, this.getRight(1), value);
		
		super.insertRight(newPlace);
	}
}
