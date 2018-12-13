package adventofcodejava;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class MarbleGameTest {
	@Test
	@Parameters({
		"5, 25, 32",
		"10, 1618, 8317",
		"13, 7999, 146373",
		"17, 1104, 2764",
		"21, 6111, 54718",
		"30, 5807, 37305"
		})
	public void findHighestScore(int playerCount, int highestMarble, int expected) {
		// arrange
		
		// act
		int result = MarbleGame.findHighestScore(playerCount, highestMarble);
		
		// assert
		assertEquals(expected, result);
	}
}
