package adventofcodejava;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		solveDayThree();
		
		solveDayFour();
		
		solveDayFive();
	}
	
	private static void solveDayThree() {
		System.out.println("Day Three");
		
		List<String> input = Arrays.asList(Input.DAY_THREE.split(";"));
		
		System.out.println(AreaCalculator.findOverlapAreaSquareInches(input));
		System.out.println(AreaCalculator.findNonOverlapping(input));
	}
	
	private static void solveDayFour() {
		System.out.println("Day Four");
		
		List<String> input = Arrays.asList(Input.DAY_FOUR.split(";"));
		
		System.out.println(GuardScheduleReader.multiplyBestGuardAndMinute(input));
		System.out.println(GuardScheduleReader.multiplyMostConsistentGuardAndMinute(input));
	}
	
	private static void solveDayFive() {
		System.out.println("Day Five");
		
		System.out.println(PolymerReactor.findSizeOfCollapsedPolymer(Input.DAY_FIVE));
	}
}
