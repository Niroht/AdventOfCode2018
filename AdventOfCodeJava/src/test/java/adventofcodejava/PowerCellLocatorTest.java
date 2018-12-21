package adventofcodejava;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class PowerCellLocatorTest {
	@Test
	@Ignore(value = "Needs optimization")
	@Parameters({
		"18, 33, 45",
		"42, 21, 61"
		})
	public void findCellChunkWithLargestPowerFixedSize_simpleInput(int serialNo, int expectedX, int expectedY) {
		// arrange
		Point expected = new Point(expectedX, expectedY);
		
		// act
		Point result = PowerCellLocator.findCellChunkWithLargestPowerFixedSize(serialNo);
		
		// assert
		assertEquals(expected, result);
	}
	
	@Test
	@Parameters({
		"18, 90, 269, 16",
		"42, 232, 251, 12"
		})
	public void findCellChunkWithLargestPowerNoLimit_simpleInput(int serialNo, int expectedX, int expectedY, Integer expectedSize) {
		// arrange
		Point expectedLocation = new Point(expectedX, expectedY);
		
		// act
		Pair<Point, Integer> result = PowerCellLocator.findCellChunkWithLargestPowerNoLimit(serialNo);
		
		// assert
		assertEquals(expectedLocation, result.getLeft());
		assertEquals(expectedSize, result.getRight());
	}
}
