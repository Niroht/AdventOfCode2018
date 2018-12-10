package adventofcodejava;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DistanceCalculatorTest {
	@Test
	public void findSizeOfLargestNonInfiniteArea_simpleInput() {
		// arrange
		List<Point> input = Arrays.asList(
				new Point(1, 1),
				new Point(1, 6),
				new Point(8, 3),
				new Point(3, 4),
				new Point(5, 5),
				new Point(8, 9)
				); 
		
		// act
		int result = DistanceCalculator.findSizeOfLargestNonInfiniteArea(input);
		
		// assert
		assertEquals(17, result);
	}
	
	@Test
	public void findAreaWithinRangeOfAllPoints_simpleInput() {
		// arrange
		List<Point> input = Arrays.asList(
				new Point(1, 1),
				new Point(1, 6),
				new Point(8, 3),
				new Point(3, 4),
				new Point(5, 5),
				new Point(8, 9)
				); 
		
		// act
		int result = DistanceCalculator.findAreaWithinRangeOfAllPoints(input, 32);
		
		// assert
		assertEquals(16, result);
	}
}
