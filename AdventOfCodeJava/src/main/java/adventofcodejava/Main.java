package adventofcodejava;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

public class Main {

	public static void main(String[] args) {
		solveDayThree();
		
		solveDayFour();
		
		solveDayFive();
		
		solveDaySix();
		
		solveDaySeven();
		
		solveDayEight();
		
		solveDayNine();
		
		System.out.println("Day Ten Disabled - Requires Manual Input");
		//solveDayTen();
		
		System.out.println("Day Eleven Disabled - Needs Optimized");
		//solveDayEleven();
		
		solveDayTwelve();
		
		System.out.println("Day Thirteen Disabled - Optimize");
		//solveDayThirteen();
		
		solveDayFourteen();
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
	
	private static void solveDaySeven() {
		System.out.println("Day Seven");
		
		List<String> inputString = Arrays.asList(Input.DAY_SEVEN.split(";"));
		
		System.out.println(StepReader.findOrderOfSteps(inputString));
		System.out.println(StepReader.findExecutionTime(inputString, 60, 5));
	}
	
	private static void solveDayEight() {
		System.out.println("Day Eight");
		
		List<Integer> inputStringAsInt = Arrays.asList(Input.DAY_EIGHT.split(" ")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
		System.out.println(TreeReader.sumMetadataEntries(inputStringAsInt));
		System.out.println(TreeReader.findRootNodeValue(inputStringAsInt));
	}
	
	private static void solveDayNine() {
		System.out.println("Day Nine");
		
		System.out.println(MarbleGame.findHighestScore(455, 71223));
		System.out.println(MarbleGame.findHighestScore(455, 7122300));
	}
	
	private static void solveDayTen() {
		System.out.println("Day Ten");
		
		List<String> inputList = Arrays.asList(Input.DAY_TEN.split(";"));
		SignalReader.readSignal(inputList);
	}
	
	private static void solveDayEleven() {
		System.out.println("Day Eleven");
		
		//Point partOneResult = PowerCellLocator.findCellChunkWithLargestPowerFixedSize(1955);
		//System.out.println(partOneResult.x + ", " + partOneResult.y);
		
		Pair<Point, Integer> partTwoResult = PowerCellLocator.findCellChunkWithLargestPowerNoLimit(1955);
		System.out.println(partTwoResult.getLeft().x + ", " + partTwoResult.getLeft().y + ", " + partTwoResult.getRight());
	}
	
	private static void solveDayTwelve() {
		System.out.println("Day Twelve");
		
		List<String> rules = Arrays.asList(Input.DAY_TWELVE_RULES.split(";"));
		
		System.out.println(GameOfPlantLife.getSumOfPotsContainingPlants(Input.DAY_TWELVE_INITIAL_STATE, rules, 20L));
		
		System.out.println(GameOfPlantLife.getSumOfPotsContainingPlants(Input.DAY_TWELVE_INITIAL_STATE, rules, 50000000000L));
	}
	
	private static void solveDayThirteen() {
		System.out.println("Day Thirteen");
		
		Point partOneResult = CartRunner.findFirstCollision(Input.DAY_THIRTEEN.replace(";", "\r\n")); 
		System.out.println(partOneResult.x + "," + -partOneResult.y);
		
		Point partTwoResult = CartRunner.findLastCartAfterAllCollisions(Input.DAY_THIRTEEN.replace(";", "\r\n")); 
		System.out.println(partTwoResult.x + "," + -partTwoResult.y);
	}
	
	private static void solveDayFourteen() {
		System.out.println("Day Fourteen");
		
		System.out.println(RecipeScorer.scoresOfNextTenRecipes(825401));
		System.out.println("Part 2 Disabled - Needs extra memory allocation");
		//System.out.println(RecipeScorer.stepsToDesiredScore("825401"));
	}
}
