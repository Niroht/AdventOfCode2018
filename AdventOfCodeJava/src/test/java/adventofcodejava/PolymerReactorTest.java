package adventofcodejava;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PolymerReactorTest {
	@Test
	public void findSizeOfCollapsedPolymer_singleReaction() {
		// arrange
		String input = "aA";
		
		// act
		int result = PolymerReactor.findSizeOfCollapsedPolymer(input);
		
		// assert
		assertEquals(0, result);
	}
	
	@Test
	public void findSizeOfCollapsedPolymer_TwoReactions() {
		// arrange
		String input = "abBA";
		
		// act
		int result = PolymerReactor.findSizeOfCollapsedPolymer(input);
		
		// assert
		assertEquals(0, result);
	}
	
	@Test
	public void findSizeOfCollapsedPolymer_noReactionDifferentTypes() {
		// arrange
		String input = "abAB";
		
		// act
		int result = PolymerReactor.findSizeOfCollapsedPolymer(input);
		
		// assert
		assertEquals(4, result);
	}
	
	@Test
	public void findSizeOfCollapsedPolymer_noReactionSameTypes() {
		// arrange
		String input = "aabAAB";
		
		// act
		int result = PolymerReactor.findSizeOfCollapsedPolymer(input);
		
		// assert
		assertEquals(6, result);
	}
	
	@Test
	public void findSizeOfCollapsedPolymer_complexExample() {
		// arrange
		String input = "dabAcCaCBAcCcaDA";
		
		// act
		int result = PolymerReactor.findSizeOfCollapsedPolymer(input);
		
		// assert
		assertEquals(10, result);
	}
	
	@Test
	public void removeOptimumUnitTypeThenCollapse_complexExample() {
		// arrange
		String input = "dabAcCaCBAcCcaDA";
		
		// act
		int result = PolymerReactor.removeOptimumUnitTypeThenCollapse(input);
		
		// assert
		assertEquals(4, result);
	}
}
