package adventofcodejava;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class GameOfPlantLifeTest {
	@Test
	public void populationAfterIterations_simpleInput() {
		// arrange
		String initialState = "#..#.#..##......###...###";
		
		List<String> rules = Arrays.asList(
				"...## => #",
				"..#.. => #",
				".#... => #",
				".#.#. => #",
				".#.## => #",
				".##.. => #",
				".#### => #",
				"#.#.# => #",
				"#.### => #",
				"##.#. => #",
				"##.## => #",
				"###.. => #",
				"###.# => #",
				"####. => #"
				);
		
		// act
		long result = GameOfPlantLife.getSumOfPotsContainingPlants(initialState, rules, 20L);
		
		// assert
		assertEquals(325, result);
	}
}
