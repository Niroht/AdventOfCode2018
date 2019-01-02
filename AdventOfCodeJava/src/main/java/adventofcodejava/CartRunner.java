package adventofcodejava;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import adventofcodejava.entity.Cart;
import adventofcodejava.entity.TrackDirection;
import adventofcodejava.entity.TrackSegment;

public class CartRunner {
	public static Point findFirstCollision(String input) {
		
		List<TrackSegment> trackSegments = new ArrayList<>();
		List<Cart> carts = new ArrayList<>();
		
		buildTracksAndCarts(input, trackSegments, carts);
		
		while (true) {
			carts.sort(
					Comparator.comparingInt((Cart cart) -> cart.getLocation().y)
					.reversed()
					.thenComparingInt(x -> x.getLocation().x));
			
			for(Cart cart : carts) {
				cart.moveNext();
				
				List<Cart> collisions = carts
						.stream()
						.filter(otherCart -> otherCart != cart && otherCart.getLocation().equals(cart.getLocation()))
						.collect(Collectors.toList());
				
				if (CollectionUtils.isNotEmpty(collisions)) {
					return cart.getLocation();
				}	
			}
		}
	}
	
	public static Point findLastCartAfterAllCollisions(String input) {
		
		List<TrackSegment> trackSegments = new ArrayList<>();
		List<Cart> carts = new ArrayList<>();
		
		buildTracksAndCarts(input, trackSegments, carts);
		
		while (true) {
			carts.sort(
					Comparator.comparingInt((Cart cart) -> cart.getLocation().y)
					.reversed()
					.thenComparingInt(x -> x.getLocation().x));
			
			List<Cart> cartsToRemove = new ArrayList<>();
			
			for(Cart cart : carts) {
				if (cartsToRemove.contains(cart)) {
					continue;
				}
				
				cart.moveNext();
				
				List<Cart> collisions = carts
						.stream()
						.filter(otherCart -> otherCart != cart && otherCart.getLocation().equals(cart.getLocation()))
						.collect(Collectors.toList());
				
				if (CollectionUtils.isNotEmpty(collisions)) {
					cartsToRemove.add(cart);
					cartsToRemove.addAll(collisions);
				}	
			}
			
			carts.removeAll(cartsToRemove);
			
			if (carts.size() == 1) {
				return carts.get(0).getLocation();
			}
		}
	}

	private static void buildTracksAndCarts(String input, List<TrackSegment> trackSegments, List<Cart> carts) {
		List<String> inputRows = Arrays.asList(input.split("\r\n"));
		
		int rowSize = inputRows.get(0).length();
		
		for (int currentY = 0; currentY > -inputRows.size(); currentY--) {
			for (int currentX = 0; currentX < rowSize; currentX++) {
				Point currentLocation = new Point(currentX, currentY);
				
				char currentSegmentChar = inputRows.get(-currentY).charAt(currentX);
				switch (currentSegmentChar) {
					case '/':
						trackSegments.add(new TrackSegment(TrackDirection.POSITIVE_SLOPE, currentLocation));
						break;
					case '\\':
						trackSegments.add(new TrackSegment(TrackDirection.NEGATIVE_SLOPE, currentLocation));
						break;
					case '-':
						trackSegments.add(new TrackSegment(TrackDirection.HORIZONTAL, currentLocation));
						break;
					case '|':
						trackSegments.add(new TrackSegment(TrackDirection.VERTICAL, currentLocation));
						break;
					case '+':
						trackSegments.add(new TrackSegment(TrackDirection.INTERSECTION, currentLocation));
						break;
					case '>':
						trackSegments.add(new TrackSegment(TrackDirection.HORIZONTAL, currentLocation));
						carts.add(new Cart(currentLocation, new Point(1, 0)));
						break;
					case '<':
						trackSegments.add(new TrackSegment(TrackDirection.HORIZONTAL, currentLocation));
						carts.add(new Cart(currentLocation, new Point(-1, 0)));
						break;
					case 'V':
					case 'v':
						trackSegments.add(new TrackSegment(TrackDirection.VERTICAL, currentLocation));
						carts.add(new Cart(currentLocation, new Point(0, -1)));
						break;
					case '^':
						trackSegments.add(new TrackSegment(TrackDirection.VERTICAL, currentLocation));
						carts.add(new Cart(currentLocation, new Point(0, 1)));
						break;
					default:
						break;
				}
			}
		}
		
		carts.forEach(x -> x.getTrackSegments().addAll(trackSegments));
	}
}
