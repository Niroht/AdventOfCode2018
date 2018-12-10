package adventofcodejava;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		solveDayThree();
		
		solveDayFour();
		
		solveDayFive();
		
		solveDaySix();
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
		
		System.out.println("Part 2 Disabled - need to optimize!");
		//System.out.println(PolymerReactor.removeOptimumUnitTypeThenCollapse(Input.DAY_FIVE));
	}
	
	private static void solveDaySix() {
		System.out.println("Day Six");
		
		List<String> inputString = Arrays.asList(Input.DAY_SIX.split(";"));
		
		List<Point> inputPoints = inputString.stream().map(x -> {
			String[] parts = x.split(",");
			return new Point(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
		}).collect(Collectors.toList());
		
		System.out.println(DistanceCalculator.findSizeOfLargestNonInfiniteArea(inputPoints));
		System.out.println(DistanceCalculator.findAreaWithinRangeOfAllPoints(inputPoints, 10000));
	}
}
