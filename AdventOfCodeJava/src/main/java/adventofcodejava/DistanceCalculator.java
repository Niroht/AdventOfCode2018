package adventofcodejava;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DistanceCalculator {
	public static int findSizeOfLargestNonInfiniteArea(List<Point> input) {
		int minXValue = input.stream().map(x -> x.x).min(Comparator.naturalOrder()).get();
		int maxXValue = input.stream().map(x -> x.x).max(Comparator.naturalOrder()).get();
		
		int minYValue = input.stream().map(x -> x.y).min(Comparator.naturalOrder()).get();
		int maxYValue = input.stream().map(x -> x.y).max(Comparator.naturalOrder()).get();
		
		List<Point> edgeDestinations = input
				.stream()
				.filter(x ->
					x.x == minXValue ||
					x.x == maxXValue ||
					x.y == minYValue ||
					x.y == maxYValue)
				.collect(Collectors.toList());
		
		Point currentPoint = new Point(minXValue, minYValue);
		
		Map<Point, List<Point>> potentialFiniteDestinationsAndAreaSizes = input
				.stream()
				.filter(x -> !edgeDestinations.contains(x))
				.collect(Collectors.toMap((Point x) -> x, (Point x) -> new ArrayList<>()));
		
		while (currentPoint.distance(maxXValue, maxXValue) != 0) {
			Point closestPoint = null;
			Integer distanceToClosestPoint = Integer.MAX_VALUE; 
			
			for (Point destinationPoint : input) {
				
				int distanceToCurrentPoint = Math.abs(destinationPoint.x - currentPoint.x) + Math.abs(destinationPoint.y - currentPoint.y);
				
				if (distanceToCurrentPoint == distanceToClosestPoint) {
					closestPoint = null;
				}
				if (distanceToCurrentPoint < distanceToClosestPoint) {
					closestPoint = destinationPoint;
					distanceToClosestPoint = distanceToCurrentPoint;
				}
			}
			
			if ((currentPoint.x == maxXValue || currentPoint.x == minXValue || currentPoint.y == maxYValue || currentPoint.y == minYValue) &&
					potentialFiniteDestinationsAndAreaSizes.containsKey(closestPoint)) {
				potentialFiniteDestinationsAndAreaSizes.remove(closestPoint);
			}
			
			if (potentialFiniteDestinationsAndAreaSizes.containsKey(closestPoint)){
				potentialFiniteDestinationsAndAreaSizes.get(closestPoint).add(new Point(currentPoint.x, currentPoint.y));
			}
			
			if (currentPoint.x < maxXValue) {
				currentPoint.x++;
			} else {
				currentPoint.x = minXValue;
				currentPoint.y++;
			}
		}
		
		return potentialFiniteDestinationsAndAreaSizes
				.entrySet()
				.stream()
				.map(x -> x.getValue().size())
				.max(Integer::max)
				.get();
	}
	
	public static int findAreaWithinRangeOfAllPoints(List<Point> input, int range) {
		int minXValue = input.stream().map(x -> x.x).min(Comparator.naturalOrder()).get();
		int maxXValue = input.stream().map(x -> x.x).max(Comparator.naturalOrder()).get();
		
		int minYValue = input.stream().map(x -> x.y).min(Comparator.naturalOrder()).get();
		int maxYValue = input.stream().map(x -> x.y).max(Comparator.naturalOrder()).get();

		
		Point currentPoint = new Point(minXValue, minYValue);
		
		int pointsWithinRangeCount = 0;
		while (currentPoint.distance(maxXValue, maxXValue) != 0) {
			
			int sumOfDistances = 0;
			for (Point destinationPoint : input) {
				
				sumOfDistances += Math.abs(destinationPoint.x - currentPoint.x) + Math.abs(destinationPoint.y - currentPoint.y);
				if (sumOfDistances >= range) {
					break;
				}
			}
			
			if (sumOfDistances < range) {
				pointsWithinRangeCount++;
			}
			
			if (currentPoint.x < maxXValue) {
				currentPoint.x++;
			} else {
				currentPoint.x = minXValue;
				currentPoint.y++;
			}
		}
		
		return pointsWithinRangeCount;
	}
}
