package adventofcodejava;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StepReaderTest {
	@Test
	public void findOrderOfSteps_simpleInput() {
		// arrange
		List<String> input = Arrays.asList(
				"Step C must be finished before step A can begin.",
				"Step C must be finished before step F can begin.",
				"Step A must be finished before step B can begin.",
				"Step A must be finished before step D can begin.",
				"Step B must be finished before step E can begin.",
				"Step D must be finished before step E can begin.",
				"Step F must be finished before step E can begin."
				);
		
		// act
		String result = StepReader.findOrderOfSteps(input);
		
		// assert
		assertEquals("CABDFE", result);
	}
	
	@Test
	public void findExecutionTime_simpleInput() {
		// arrange
		List<String> input = Arrays.asList(
				"Step C must be finished before step A can begin.",
				"Step C must be finished before step F can begin.",
				"Step A must be finished before step B can begin.",
				"Step A must be finished before step D can begin.",
				"Step B must be finished before step E can begin.",
				"Step D must be finished before step E can begin.",
				"Step F must be finished before step E can begin."
				);
		
		// act
		int result = StepReader.findExecutionTime(input, 0, 2);
		
		// assert
		assertEquals(15, result);
	}
}
