package adventofcodejava;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TreeReaderTest {
	@Test
	public void sumMetadataEntries_simpleInput() {
		// arrange
		List<Integer> input = Arrays.asList(StringUtils.split("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2", " "))
				.stream()
				.map(x -> Integer.parseInt(x))
				.collect(Collectors.toList());
		
		// act
		int result = TreeReader.sumMetadataEntries(input);
		
		// assert
		assertEquals(138, result);
	}
	
	@Test
	public void findRootNodeValue_simpleInput() {
		// arrange
		List<Integer> input = Arrays.asList(StringUtils.split("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2", " "))
				.stream()
				.map(x -> Integer.parseInt(x))
				.collect(Collectors.toList());
		
		// act
		int result = TreeReader.findRootNodeValue(input);
		
		// assert
		assertEquals(66, result);
	}
}
