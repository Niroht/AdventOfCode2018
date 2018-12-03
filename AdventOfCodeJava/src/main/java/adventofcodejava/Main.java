package adventofcodejava;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		solveDayThree();
	}
	
	private static void solveDayThree() {
		System.out.println("Day Three");
		
		List<String> input = Arrays.asList(Input.DAY_THREE.split(";"));
		
		System.out.println(AreaCalculator.findOverlapAreaSquareInches(input));
		System.out.println(AreaCalculator.findNonOverlapping(input));
	}
}
