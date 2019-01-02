package adventofcodejava;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RecipeScorerTest {
	@Test
	@Parameters({
		"9, 5158916779",
		"5, 0124515891",
		"18, 9251071085",
		"2018, 5941429882"
		})
	public void scoresOfNextTenRecipes_simpleInput(int steps, String expected) {
		// arrange
		
		// act
		String result = RecipeScorer.scoresOfNextTenRecipes(steps);
		
		// assert
		assertEquals(expected, result);
	}
	
	@Test
	@Parameters({
		"9, 51589",
		"5, 01245",
		"18, 92510",
		"2018, 59414"
		})
	public void stepsToDesiredScores_simpleInput(int steps, String scoreToIdentify) {
		// arrange
		
		// act
		int result = RecipeScorer.stepsToDesiredScore(scoreToIdentify);
		
		// assert
		assertEquals(steps, result);
	}
}
