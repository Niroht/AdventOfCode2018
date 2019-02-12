package adventofcodejava.utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class AdventPointUtil {

	private AdventPointUtil() {
	}
	
	public static int manhattanDistance(Point pointOne, Point pointTwo) {
		return Math.abs(pointOne.x - pointTwo.x) + Math.abs(pointOne.y - pointTwo.y);
	}
	
	public static Point stepTowardsDestinationManhattan(Point original, Point destination, List<Point> walls) {
		List<Pair<Integer, Point>> closestSteps = new ArrayList<>();
		
		List<Pair<Integer, Point>> openAdjacentPoints = Arrays.asList(
				Pair.of(1, new Point(original.x, original.y + 1)), //Up
				Pair.of(4, new Point(original.x, original.y - 1)), //Down
				Pair.of(2, new Point(original.x - 1, original.y)), //Left
				Pair.of(3, new Point(original.x + 1, original.y)) //Right
				);
		
		int closestPoint = Integer.MAX_VALUE;
		for(Pair<Integer, Point> adjacentPoint : openAdjacentPoints) {
			if (walls.stream().anyMatch(x -> x.equals(adjacentPoint.getRight()))) {
				continue;
			}
			
			int distance = manhattanDistance(adjacentPoint.getRight(), destination);
			
			if (distance < closestPoint) {
				closestPoint = distance;
				closestSteps.clear();
				closestSteps.add(adjacentPoint);
			} else if (distance == closestPoint) {
				closestSteps.add(adjacentPoint);
			}
		}
		
		if (closestSteps.size() == 1) {
			return closestSteps.get(0).getRight();
		}
		
		return closestSteps
				.stream()
				.min(Comparator.comparingInt((Pair<Integer, Point> x) -> x.getLeft()))
				.get()
				.getRight();
	}
}
