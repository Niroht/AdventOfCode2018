package adventofcodejava;

import adventofcodejava.entity.RecipePlace;

public class RecipeScorer {
	public static String scoresOfNextTenRecipes(int recipeCount) {
		RecipePlace placeOne = new RecipePlace(3);
		placeOne.insertRight(7);
		RecipePlace placeTwo = placeOne.getRight(1);
		
		// The first two recipes are always known as 3 and 7. Start looping after them
		int recipes = 2;
		
		StringBuilder result = new StringBuilder();
		
		RecipePlace furthestRight = placeTwo;
		while (recipes < recipeCount + 10) {
			int score = placeOne.getValue() + placeTwo.getValue();
			String scoreAsString = String.valueOf(score);
			
			for (char digit : scoreAsString.toCharArray()) {
				int value = Integer.parseInt(String.valueOf(digit));
				
				furthestRight.insertRight(value);
				furthestRight = furthestRight.getRight(1);
				
				recipes++;
				
				if (recipes > recipeCount && recipes <= recipeCount + 10) {
					result.append(value);
				}				
			}
			
			placeOne = placeOne.getRight(1 + placeOne.getValue());
			placeTwo = placeTwo.getRight(1 + placeTwo.getValue());
		}
		
		
		
		return result.toString();
	}
	
	public static int stepsToDesiredScore(String desiredScore) {
		RecipePlace placeOne = new RecipePlace(3);
		placeOne.insertRight(7);
		RecipePlace placeTwo = placeOne.getRight(1);
		
		// The first two recipes are always known as 3 and 7. Start looping after them
		int leftRecipes = 2;
		int allRecipes = 2;
		
		RecipePlace furthestRight = placeTwo;
		
		boolean foundMatch = false;
		StringBuilder potentialMatch = new StringBuilder();
		while (!foundMatch) {
			int score = placeOne.getValue() + placeTwo.getValue();
			String scoreAsString = String.valueOf(score);
			
			for (char digit : scoreAsString.toCharArray()) {
				int value = Integer.parseInt(String.valueOf(digit));
				
				furthestRight.insertRight(value);
				furthestRight = furthestRight.getRight(1);
				
				boolean scoreMatchesDesired = String.valueOf(value).equals(String.valueOf(desiredScore.charAt(potentialMatch.length())));
				
				allRecipes++;
				if (scoreMatchesDesired) {
					potentialMatch.append(String.valueOf(value));
				} else {
					potentialMatch = new StringBuilder();
					
					if (String.valueOf(value).equals(String.valueOf(desiredScore.charAt(0)))) {
						potentialMatch.append(String.valueOf(value));
						leftRecipes = allRecipes - 1;
					} else {
						leftRecipes = allRecipes;
					}
				}
				
				if (potentialMatch.length() >= desiredScore.length()) {
					foundMatch = true;
					break;
				}
						
			}
			
			placeOne = placeOne.getRight(1 + placeOne.getValue());
			placeTwo = placeTwo.getRight(1 + placeTwo.getValue());
		}
		
		return leftRecipes;
	}
}
