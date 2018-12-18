package adventofcodejava;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import adventofcodejava.entity.SignalMarker;

public class SignalReader {
	public static void readSignal(List<String> input) {
		List<SignalMarker> signalMarkers = new ArrayList<>();
		
		Pattern withinBracesPattern = Pattern.compile("((?<=<).*?)(?=>)");
		
		for (String signalString : input) {
			Matcher matcher = withinBracesPattern.matcher(signalString);
			matcher.find();
			String positionString = matcher.group(1);
			
			matcher.find();
			String velocityString = matcher.group(1);
			
			signalMarkers.add(new SignalMarker(
				getPointFromCommaDelimitedString(positionString),
				getPointFromCommaDelimitedString(velocityString)
				));
		}
		
		try(Scanner scanner = new Scanner(System.in)) {
			int seconds = 0;
			
			while (true) {
				Map<Integer, List<SignalMarker>> instancesOfSameXValue = signalMarkers
						.stream()
						.collect(Collectors.groupingBy(x -> x.currentLocation.x));
				
				if (instancesOfSameXValue.entrySet().stream().anyMatch(x -> x.getValue().size() > 9)) {
					System.out.println("Potential Word Detected At " + seconds +" Seconds. Building...");
					String signalDisplay = getSignalDisplay(signalMarkers);
					System.out.println(signalDisplay);
					String userInput = scanner.nextLine();
					
					if (StringUtils.isNotBlank(userInput)){
						break;
					}
				}
				
				signalMarkers.forEach(marker -> {
					marker.currentLocation.x += marker.velocity.x;
					marker.currentLocation.y += marker.velocity.y;
				});
				
				seconds++;
			}	
		}
	}
	
	private static String getSignalDisplay(List<SignalMarker> markers) {
		StringBuilder outputDisplayString = new StringBuilder();
		
		int minY = markers.stream().mapToInt(x -> x.currentLocation.y).min().getAsInt();
		int maxY = markers.stream().mapToInt(x -> x.currentLocation.y).max().getAsInt();
		int minX = markers.stream().mapToInt(x -> x.currentLocation.x).min().getAsInt();
		int maxX = markers.stream().mapToInt(x -> x.currentLocation.x).max().getAsInt();
		
		for (int yLocation = minY; yLocation <= maxY; yLocation++) {
			StringBuilder rowDisplay = new StringBuilder();
			
			for (int xLocation = minX; xLocation <= maxX; xLocation++) {
				Point location = new Point(xLocation, yLocation);
				if (markers.stream().anyMatch(x -> x.currentLocation.equals(location))) {
					rowDisplay.append("#");
				} else {
					rowDisplay.append(".");
				}
			}
			
			rowDisplay.append(System.lineSeparator());
			outputDisplayString.append(rowDisplay.toString());
		}
		
		outputDisplayString.append(System.lineSeparator());
		return outputDisplayString.toString();
	}
	
	private static Point getPointFromCommaDelimitedString(String commaDelimited) {
		String[] pointParts = commaDelimited.split(",");
		return new Point(Integer.parseInt(pointParts[0].trim()), Integer.parseInt(pointParts[1].trim()));
	}
}
