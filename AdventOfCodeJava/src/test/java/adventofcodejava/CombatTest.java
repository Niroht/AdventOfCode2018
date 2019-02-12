package adventofcodejava;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CombatTest {
	@Test
	public void sumOfRoundsAndHitPoints_simpleInput() {
		// arrange
		
		// act
		int result = Combat.sumOfRoundsAndHitPoints(
				"#######\r\n" + 
				"#.G...#\r\n" + 
				"#...EG#\r\n" + 
				"#.#.#G#\r\n" + 
				"#..G#E#\r\n" + 
				"#.....#\r\n" + 
				"#######"
				);
		
		// assert
		assertEquals(27730, result);
	}
	
	@Test
	public void sumOfRoundsAndHitPoints_simpleInput_ElvesWin() {
		// arrange
		
		// act
		int result = Combat.sumOfRoundsAndHitPoints(
				"#######\r\n" + 
				"#G..#E#\r\n" + 
				"#E#E.E#\r\n" + 
				"#G.##.#\r\n" + 
				"#...#E#\r\n" + 
				"#...E.#\r\n" + 
				"#######"
				);
		
		// assert
		assertEquals(36334, result);
	}
	
	@Test
	public void sumOfRoundsAndHitPoints_simpleInput_ThirdScenario() {
		// arrange
		
		// act
		int result = Combat.sumOfRoundsAndHitPoints(
				"#######\r\n" + 
				"#E..EG#\r\n" + 
				"#.#G.E#\r\n" + 
				"#E.##E#\r\n" + 
				"#G..#.#\r\n" + 
				"#..E#.#\r\n" + 
				"#######"
				);
		
		// assert
		assertEquals(39514, result);
	}
	
	@Test
	public void sumOfRoundsAndHitPoints_simpleInput_FourthScenario() {
		// arrange
		
		// act
		int result = Combat.sumOfRoundsAndHitPoints(
				"#######\r\n" + 
				"#E.G#.#\r\n" + 
				"#.#G..#\r\n" + 
				"#G.#.G#\r\n" + 
				"#G..#.#\r\n" + 
				"#...E.#\r\n" + 
				"#######"
				);
		
		// assert
		assertEquals(27755, result);
	}
	
	@Test
	public void sumOfRoundsAndHitPoints_simpleInput_FifthScenario() {
		// arrange
		
		// act
		int result = Combat.sumOfRoundsAndHitPoints(
				"#######\r\n" + 
				"#.E...#\r\n" + 
				"#.#..G#\r\n" + 
				"#.###.#\r\n" + 
				"#E#G#G#\r\n" + 
				"#...#G#\r\n" + 
				"#######"
				);
		
		// assert
		assertEquals(28944, result);
	}
	
	@Test
	public void sumOfRoundsAndHitPoints_simpleInput_SixthScenario() {
		// arrange
		
		// act
		int result = Combat.sumOfRoundsAndHitPoints(
				"#########\r\n" + 
				"#G......#\r\n" + 
				"#.E.#...#\r\n" + 
				"#..##..G#\r\n" + 
				"#...##..#\r\n" + 
				"#...#...#\r\n" + 
				"#.G...G.#\r\n" + 
				"#.....G.#\r\n" + 
				"#########"
				);
		
		// assert
		assertEquals(18740, result);
	}
	
	@Test
	public void minimumPowerForElfVictory_simpleInput() {
		// arrange
		
		// act
		int result = Combat.minimumPowerForElfVictory(
				"#######\r\n" + 
				"#.G...#\r\n" + 
				"#...EG#\r\n" + 
				"#.#.#G#\r\n" + 
				"#..G#E#\r\n" + 
				"#.....#\r\n" + 
				"#######"
				);
		
		// assert
		assertEquals(4988, result);
	}
	
	@Test
	public void minimumPowerForElfVictory_secondScenario() {
		// arrange
		
		// act
		int result = Combat.minimumPowerForElfVictory(
				"#######\r\n" + 
				"#E..EG#\r\n" + 
				"#.#G.E#\r\n" + 
				"#E.##E#\r\n" + 
				"#G..#.#\r\n" + 
				"#..E#.#\r\n" + 
				"#######"
				);
		
		// assert
		assertEquals(31284, result);
	}
	
	@Test
	public void minimumPowerForElfVictory_thirdScenario() {
		// arrange
		
		// act
		int result = Combat.minimumPowerForElfVictory(
				"#######\r\n" + 
				"#E.G#.#\r\n" + 
				"#.#G..#\r\n" + 
				"#G.#.G#\r\n" + 
				"#G..#.#\r\n" + 
				"#...E.#\r\n" + 
				"#######"
				);
		
		// assert
		assertEquals(3478, result);
	}
	
	@Test
	public void minimumPowerForElfVictory_fourthScenario() {
		// arrange
		
		// act
		int result = Combat.minimumPowerForElfVictory(
				"#######\r\n" + 
				"#.E...#\r\n" + 
				"#.#..G#\r\n" + 
				"#.###.#\r\n" + 
				"#E#G#G#\r\n" + 
				"#...#G#\r\n" + 
				"#######"
				);
		
		// assert
		assertEquals(6474, result);
	}
	
	@Test
	public void minimumPowerForElfVictory_fifthScenario() {
		// arrange
		
		// act
		int result = Combat.minimumPowerForElfVictory(
				"#########\r\n" + 
				"#G......#\r\n" + 
				"#.E.#...#\r\n" + 
				"#..##..G#\r\n" + 
				"#...##..#\r\n" + 
				"#...#...#\r\n" + 
				"#.G...G.#\r\n" + 
				"#.....G.#\r\n" + 
				"#########"
				);
		
		// assert
		assertEquals(1140, result);
	}
}
