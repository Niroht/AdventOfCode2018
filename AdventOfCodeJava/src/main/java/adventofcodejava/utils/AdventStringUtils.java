package adventofcodejava.utils;

import org.apache.commons.lang3.StringUtils;

public class AdventStringUtils {
	
	public static boolean equalsOppositeCase(String string1, String string2) {
		// Filter out strings that are completely different
		if (!StringUtils.equalsIgnoreCase(string1, string2)) {
			return false;
		}
		
		// Any strings that make it here will equalsIgnoreCase, and not equals if casing mismatches
		return !StringUtils.equals(string1, string2);
	}
	
	public static boolean equalsOppositeCase(Character char1, Character char2) {
		String string1 = char1.toString();
		String string2 = char2.toString();
		
		// Filter out strings that are completely different
		if (!StringUtils.equalsIgnoreCase(string1, string2)) {
			return false;
		}
		
		// Any strings that make it here will equalsIgnoreCase, and not equals if casing mismatches
		return !StringUtils.equals(string1, string2);
	}
}
