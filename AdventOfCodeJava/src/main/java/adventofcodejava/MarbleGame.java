package adventofcodejava;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import adventofcodejava.entity.MarbleGamePlace;

public class MarbleGame {
	public static Long findHighestScore(int playerCount, int highestMarble) {
		MarbleGamePlace currentPlace = new MarbleGamePlace();
		List<Long> scores = new ArrayList<>();
		
		for (int i = 0; i < playerCount; i++) {
			scores.add(0L);
		}
		
		int currentPlayer = 0;
		
		for (int i = 1; i <= highestMarble; i++) {
			if (i == 0 || i % 23 != 0) {
				currentPlace = getCurrentPlaceAfterNormalPlay(currentPlace, i);
			} else {
				currentPlace = getIndexAfterScoringPlay(currentPlace, scores, currentPlayer, i);
			}
			
			currentPlayer = currentPlayer == playerCount - 1 ? 0 : currentPlayer + 1;
		}
		
		return scores.stream().max(Comparator.naturalOrder()).get();
	}

	private static MarbleGamePlace getIndexAfterScoringPlay(MarbleGamePlace currentPlace, List<Long> scores, int currentPlayer,
			int currentValue) {
		scores.set(currentPlayer, scores.get(currentPlayer) + currentValue);
		
		MarbleGamePlace scoringPlace = currentPlace.getLeft(7);
		scores.set(currentPlayer, scores.get(currentPlayer) + scoringPlace.getValue());
		
		MarbleGamePlace newPlace = scoringPlace.getRight(1);
		
		scoringPlace.remove();
		return newPlace;
	}

	private static MarbleGamePlace getCurrentPlaceAfterNormalPlay(MarbleGamePlace currentPlace, int currentValue) {
		MarbleGamePlace nextPlace = currentPlace.getRight(1);
		
		nextPlace.insertRight(currentValue);
		
		return nextPlace.getRight(1);
	}
}
