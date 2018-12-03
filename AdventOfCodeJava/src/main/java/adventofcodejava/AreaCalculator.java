package adventofcodejava;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import adventofcodejava.entity.AreaClaim;

public class AreaCalculator {
	public static int findOverlapAreaSquareInches(List<String> input) {
		List<AreaClaim> claims = parseClaims(input);
		
		Map<Point, Integer> foundPoints = new HashMap<>();
		claims.forEach(claim -> 
			claim.getCoveredPoints().forEach(point -> {
				if (foundPoints.containsKey(point)) {
					int count = foundPoints.get(point);
					foundPoints.replace(point, count + 1);
				} else {
					foundPoints.put(point, 1);
				}
			})
		);
		
		return (int)foundPoints.entrySet().stream().filter(x -> x.getValue() > 1).count();
	}
	
	public static int findNonOverlapping(List<String> input) {
		List<AreaClaim> claims = parseClaims(input);
		
		Optional<AreaClaim> firstNonOverlapping = claims
				.stream()
				.filter(x -> claims
						.stream()
						.filter(y -> y.getId() != x.getId())
						.noneMatch(y -> y.getArea().intersects(x.getArea())))
				.findFirst();
		
		return firstNonOverlapping.isPresent() ? firstNonOverlapping.get().getId() : 0;
	}

	private static List<AreaClaim> parseClaims(List<String> input) {
		List<AreaClaim> claims = new ArrayList<>();
		
		for (String claimString : input) {
			String[] claimParts = claimString.split(" ");
			
			String[] originCoordinates = claimParts[2].replaceAll(":", "").split(",");
			Point origin = new Point(Integer.parseInt(originCoordinates[0]) + 1, Integer.parseInt(originCoordinates[1]) + 1);
			
			String[] dimensions = claimParts[3].split("x");
			
			claims.add(
					new AreaClaim(
							Integer.parseInt(claimParts[0].substring(1)), 
							origin, 
							Integer.parseInt(dimensions[0]), 
							Integer.parseInt(dimensions[1])));
		}
		return claims;
	}
}
