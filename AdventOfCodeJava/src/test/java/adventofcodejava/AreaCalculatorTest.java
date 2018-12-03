package adventofcodejava;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AreaCalculatorTest {
	@Test
	public void findTotalOverlap_simpleUseCase() {
		// arrange
		List<String> input = Arrays.asList(
			"#1 @ 1,3: 4x4",
			"#2 @ 3,1: 4x4",
			"#3 @ 5,5: 2x2"
			); 
		
		// act
		int result = AreaCalculator.findOverlapAreaSquareInches(input);
		
		// assert
		assertEquals(4, result);
	}
	
	@Test
	public void findNonOverlapping_simpleUseCase() {
		// arrange
		List<String> input = Arrays.asList(
			"#1 @ 1,3: 4x4",
			"#2 @ 3,1: 4x4",
			"#3 @ 5,5: 2x2",
			"#4 @ 0,2: 4x4"
			); 
		
		// act
		int result = AreaCalculator.findNonOverlapping(input);
		
		// assert
		assertEquals(3, result);
	}
}
