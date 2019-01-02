package adventofcodejava.entity;

public class RecipePlace extends DoublyLinkedCircularListItem<RecipePlace>{
	public RecipePlace(int value) {
		super(value);
	}
	
	public RecipePlace(RecipePlace left, RecipePlace right, int value) {
		super(left, right, value);
	}
	
	public void insertRight(int value) {
		RecipePlace newPlace = new RecipePlace(this, this.getRight(1), value);
		
		super.insertRight(newPlace);
	}
}
