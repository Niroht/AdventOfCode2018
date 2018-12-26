package adventofcodejava.entity;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class PlantLifeCellTest {
	@Test
	@Parameters({
		"false, false, true, true",
		"false, true, false, false",
		"false, true, true, false",
		"false, true, true, true",
		"true, true, true, true",
	})
	public void nextStep_born(boolean leftmost, boolean left, boolean right, boolean rightmost) {
		// arrange
		List<Boolean> ruleNeighborStates = Arrays.asList(leftmost, left, right, rightmost);
		
		List<PlantLifeRule> rules = Arrays.asList(new PlantLifeRule(ruleNeighborStates, false, true));
		
		PlantLifeCell sut = new PlantLifeCell(false, rules, 1);
		
		sut.getNeighbors().addAll(
				Arrays.asList(
						new PlantLifeCell(leftmost, rules, 1), 
						new PlantLifeCell(left, rules, 1), 
						new PlantLifeCell(right, rules, 1), 
						new PlantLifeCell(rightmost, rules, 1)
						)
				);
		
		
		// act
		PlantLifeCell result = sut.nextStep();
		
		// assert
		assertTrue(result.getLiving());
	}
}
