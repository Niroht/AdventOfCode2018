package adventofcodejava;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import adventofcodejava.utils.AdventStringUtils;

public class PolymerReactor {
	public static int findSizeOfCollapsedPolymer(String polymer) {
		String reactedPolymer = collapsePolymer(polymer);
		
		return reactedPolymer.length();
	}
	
	public static int removeOptimumUnitTypeThenCollapse(String polymer) {
		Set<Character> uniqueCharacters = new TreeSet<>();
		for(char character : polymer.toCharArray()) {
			uniqueCharacters.add(character);
		}
		
		Integer lowestPolymerCount = null;
		
		for(Character character: uniqueCharacters) {
			String strippedString = polymer.replaceAll("(?i)" + character.toString(), "");
			
			int polymerCount = collapsePolymer(strippedString).length();
			if (lowestPolymerCount == null || lowestPolymerCount > polymerCount) {
				lowestPolymerCount = polymerCount;
			}
		}

		return lowestPolymerCount;
	}
	
	private static String collapsePolymer(String workingPolymer) {
		String reactedPolymer = workingPolymer;
		int previousStepLength = workingPolymer.length() + 1;
		
		while (reactedPolymer.length() != previousStepLength) {
			
			previousStepLength = StringUtils.length(reactedPolymer);
			
			reactedPolymer = runSingleReaction(reactedPolymer);
		}
		
		return reactedPolymer;
	}

	private static String runSingleReaction(String workingPolymer) {
		StringBuilder reactedPolymer = new StringBuilder();
		
		for (int i = 0; i < workingPolymer.length(); i++) {
			
			if (i == workingPolymer.length() - 1 || !AdventStringUtils.equalsOppositeCase(workingPolymer.charAt(i), workingPolymer.charAt(i + 1))) {
				reactedPolymer.append(workingPolymer.charAt(i));
			} else if (i < workingPolymer.length() - 1){
				i++;
			}
		}
		
		return reactedPolymer.toString();
	}
}
