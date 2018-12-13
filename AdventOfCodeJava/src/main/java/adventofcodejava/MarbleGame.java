package adventofcodejava;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class MarbleGame {
	public static int findHighestScore(int playerCount, int highestMarble) {
		List<Integer> placedMarbles = new ArrayList<>();
		List<Integer> scores = new ArrayList<>();
		
		for (int i = 0; i < playerCount; i++) {
			scores.add(0);
		}
		
		int currentPlayer = 0;
		
		int currentIndex = 0;
		for (int i = 0; i <= highestMarble; i++) {
			if (i == 0 || i % 23 != 0) {
				currentIndex = getIndexAfterNormalPlay(placedMarbles, currentIndex, i);
			} else {
				currentIndex = getIndexAfterScoringPlay(placedMarbles, scores, currentPlayer, currentIndex, i);
			}
			
			currentPlayer = currentPlayer == playerCount - 1 ? 0 : currentPlayer + 1;
		}
		
		return scores.stream().max(Comparator.naturalOrder()).get();
	}

	private static int getIndexAfterScoringPlay(List<Integer> placedMarbles, List<Integer> scores, int currentPlayer,
			int currentIndex, int i) {
		scores.set(currentPlayer, scores.get(currentPlayer) + i);
		
		int desiredIndex = currentIndex - 7;
		int actualIndex = desiredIndex > 0 ? desiredIndex : desiredIndex + placedMarbles.size();
		
		scores.set(currentPlayer, scores.get(currentPlayer) + placedMarbles.get(actualIndex));
		
		placedMarbles.remove(actualIndex);
		return actualIndex;
	}

	private static int getIndexAfterNormalPlay(List<Integer> placedMarbles, int currentIndex, int i) {
		int desiredIndex = currentIndex + 2;
		
		int maxIndex = CollectionUtils.isEmpty(placedMarbles) ? 0 : placedMarbles.size() - 1;
		int actualIndex;
		if (CollectionUtils.isEmpty(placedMarbles)) {
			actualIndex = 0;
		} else if (desiredIndex > maxIndex) {
			int diff = desiredIndex - maxIndex;
			actualIndex = diff == 1 ? 0 : 1;
		} else {
			actualIndex = desiredIndex;
		}
		
		placedMarbles.add(actualIndex, i);
		currentIndex = actualIndex;
		return currentIndex;
	}
}
