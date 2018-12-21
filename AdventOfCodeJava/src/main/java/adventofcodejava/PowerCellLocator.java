package adventofcodejava;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import adventofcodejava.entity.PowerCell;

public class PowerCellLocator {
	public static Point findCellChunkWithLargestPowerFixedSize(int serialNumber) {
		List<PowerCell> powerGrid = new ArrayList<>();
		
		for (int yPosition = 1; yPosition <= 300; yPosition++) {
			for (int xPosition = 1; xPosition <= 300; xPosition++) {
				powerGrid.add(new PowerCell(new Point(xPosition, yPosition), serialNumber));
			}
		}
		
		List<PowerCell> nonEdgeCells = powerGrid
				.stream()
				.filter(x -> x.location.x > 1 && x.location.x < 300 && x.location.y > 1 && x.location.y < 300)
				.filter(x -> x.getTotalPower() >= 0)
				.collect(Collectors.toList());
		
		int highestChunkPower = 0;
		PowerCell highestChunkCorner = null;
		
		for (PowerCell cell: nonEdgeCells) {
			List<PowerCell> cellChunk = powerGrid
					.stream()
					.filter(x -> Math.abs(x.location.x - cell.location.x) <= 1 && Math.abs(x.location.y - cell.location.y) <= 1)
					.collect(Collectors.toList());
			
			int chunkPower = cellChunk.stream().mapToInt(PowerCell::getTotalPower).sum();
			
			if (chunkPower > highestChunkPower) {
				highestChunkPower = chunkPower;
				
				int minXValue = cellChunk.stream().mapToInt(x -> x.location.x).min().getAsInt();
				int minYValue = cellChunk.stream().mapToInt(x -> x.location.y).min().getAsInt();
				
				highestChunkCorner = cellChunk.stream().filter(x -> x.location.x == minXValue && x.location.y == minYValue).findFirst().get();
			}
		}
		
		return highestChunkCorner.location;
	}
	
	public static Pair<Point, Integer> findCellChunkWithLargestPowerNoLimit(int serialNumber) {
		int[][] powerGrid = new int[300][300];
		
		for (int yPosition = 0; yPosition < 300; yPosition++) {
			for (int xPosition = 0; xPosition < 300; xPosition++) {
				int rackId = xPosition + 10;
				int powerLevel = rackId * yPosition;
				powerLevel += serialNumber;
				
				powerLevel *= rackId;
				int hundredsDigit = (powerLevel % 1000) / 100;
				powerLevel = hundredsDigit;
				powerLevel -= 5;
				
				powerGrid[xPosition][yPosition] = powerLevel;
			}
		}
		
		int highestChunkPower = 0;
		Point highestChunkCorner = null;
		int highestPowerSideSize = 0;
		
		for (int yPosition = 0; yPosition < 299; yPosition++) {
			for (int xPosition = 0; xPosition < 299; xPosition++) {
				int maxXSide = 299 - xPosition;
				int maxYSide = 299 - yPosition;
				
				int maxSideSize = Math.min(maxYSide, maxXSide);
				
				for (int currentSideSize = maxSideSize; currentSideSize >= 1; currentSideSize--) {
					int chunkPower = 0;
					
					for (int currentY = yPosition; currentY <= yPosition + currentSideSize; currentY++) {
						for (int currentX = xPosition; currentX <= xPosition + currentSideSize; currentX++) {
							chunkPower += powerGrid[currentX][currentY];
						}
					}
					
					if (chunkPower > highestChunkPower) {
						highestChunkPower = chunkPower;
						highestChunkCorner = new Point(xPosition, yPosition);
						highestPowerSideSize = currentSideSize + 1;
					}
					
				}
			}
		}
				
		return Pair.of(highestChunkCorner, highestPowerSideSize);
	}
}
