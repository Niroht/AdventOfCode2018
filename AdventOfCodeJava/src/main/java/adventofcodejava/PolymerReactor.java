package adventofcodejava;

import org.apache.commons.lang3.CharUtils;

import adventofcodejava.utils.AdventStringUtils;

public class PolymerReactor {
	public static int findSizeOfCollapsedPolymer(String polymer) {
		StringBuilder workingPolymer = new StringBuilder(polymer);
		
		String reactedPolymer = collapsePolymer(workingPolymer);
		
		return reactedPolymer.length();
	}
	
	private static String collapsePolymer(StringBuilder workingPolymer) {
		StringBuilder reactedPolymer = new StringBuilder();
		
		for (int i = 0; i < workingPolymer.length(); i++) {
			
			if (i == workingPolymer.length() - 1 || !AdventStringUtils.equalsOppositeCase(workingPolymer.charAt(i), workingPolymer.charAt(i + 1))) {
				reactedPolymer.append(workingPolymer.charAt(i));
			} else if (i < workingPolymer.length() - 1){
				i++;
			}
		}
		
		if (reactedPolymer.length() == 0 || reactedPolymer.length() == workingPolymer.length()) {
			return reactedPolymer.toString();
		}
		
		return collapsePolymer(reactedPolymer);
	}
}
