package adventofcodejava.entity;

public class DoublyLinkedCircularListItem<T extends DoublyLinkedCircularListItem<T>> {
	private T left;
	
	private T right;
	
	private int value;
	
	protected DoublyLinkedCircularListItem() {
		this.left = (T)this; 
		this.right = (T)this;
		
		this.value = 0;
	}
	
	protected DoublyLinkedCircularListItem(int value) {
		this.left = (T)this; 
		this.right = (T)this;
		
		this.value = value;
	}
	
	protected DoublyLinkedCircularListItem(T left, T right, int value) {
		this.left = left;
		this.right = right;
		
		this.value = value;
	}
	
	public void setLeft(T place) {
		this.left = place;
	}
	
	public void setRight(T place) {
		this.right = place;
	}
	
	public T getLeft(int distance) {
		switch (distance) {
			case 0:
				return (T)this;
			case 1:
				return this.left;
			default:
				return this.left.getLeft(distance -1);
		}
	}
	
	public T getRight(int distance) {
		switch (distance) {
			case 0:
				return (T)this;
			case 1:
				return this.right;
			default:
				return this.right.getRight(distance -1);
		}
	}
	
	protected void insertRight(T newItem) {
		
		this.right.setLeft(newItem);
		this.right = newItem;
	}
	
	public void remove() {
		this.left.setRight(this.right);
		this.right.setLeft(this.left);
	}
	
	public int getValue() {
		return this.value;
	}
}
