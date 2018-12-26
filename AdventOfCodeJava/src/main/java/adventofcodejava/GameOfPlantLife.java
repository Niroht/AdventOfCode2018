package adventofcodejava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import adventofcodejava.entity.PlantLifeCell;
import adventofcodejava.entity.PlantLifeRule;

public class GameOfPlantLife {
	public static long getSumOfPotsContainingPlants(String initialState, List<String> rulesString, Long steps) {
		List<PlantLifeRule> rules = new ArrayList<>();
		
		for (String rule : rulesString) {
			List<Boolean> neighborStates = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				if (i == 2) {
					continue;
				}				
				
				boolean hasPlant = rule.charAt(i) == '#';
				
				neighborStates.add(hasPlant);
			}
			
			rules.add(new PlantLifeRule(neighborStates, rule.charAt(2) == '#', rule.charAt(rule.length() - 1) == '#'));
		}
		
		
		List<PlantLifeCell> plants = buildPotListFromString(initialState, rules);
		
		for (long step = 0; step < steps; step++) {
			List<PlantLifeCell> newPlants = new ArrayList<>();
			
			PlantLifeCell twoLeftCell = new PlantLifeCell(false, rules, plants.get(0).getId() - 2);
			
			PlantLifeCell oneLeftCell = new PlantLifeCell(false, rules, plants.get(0).getId() - 1);
			
			twoLeftCell.getNeighbors().add(new PlantLifeCell(false, rules, plants.get(0).getId() - 4));
			twoLeftCell.getNeighbors().add(new PlantLifeCell(false, rules, plants.get(0).getId() - 3));
			twoLeftCell.getNeighbors().add(oneLeftCell);
			twoLeftCell.getNeighbors().add(plants.get(0));

			oneLeftCell.getNeighbors().add(new PlantLifeCell(false, rules, plants.get(0).getId() - 3));
			oneLeftCell.getNeighbors().add(twoLeftCell);
			oneLeftCell.getNeighbors().add(plants.get(0));
			oneLeftCell.getNeighbors().add(plants.get(1));
			
			PlantLifeCell twoLeftNextStep = twoLeftCell.nextStep();
			PlantLifeCell oneLeftNextStep = oneLeftCell.nextStep();
			if (twoLeftNextStep.getLiving()) {
				newPlants.add(twoLeftCell);
				newPlants.add(oneLeftCell);
			} else if (oneLeftNextStep.getLiving()) {
				newPlants.add(oneLeftNextStep);
			}
			
			PlantLifeCell oneRightCell = new PlantLifeCell(false, rules, plants.get(plants.size() - 1).getId() + 1);
			
			PlantLifeCell twoRightCell = new PlantLifeCell(false, rules, plants.get(plants.size() - 1).getId() + 2);
			
			oneRightCell.getNeighbors().add(plants.get(plants.size() - 2));
			oneRightCell.getNeighbors().add(plants.get(plants.size() - 1));
			oneRightCell.getNeighbors().add(twoRightCell);
			oneRightCell.getNeighbors().add(new PlantLifeCell(false, rules, plants.get(plants.size() - 1).getId() + 3));

			twoRightCell.getNeighbors().add(plants.get(plants.size() - 1));
			twoRightCell.getNeighbors().add(oneRightCell);
			twoRightCell.getNeighbors().add(new PlantLifeCell(false, rules, plants.get(plants.size() - 1).getId() + 3));
			twoRightCell.getNeighbors().add(new PlantLifeCell(false, rules, plants.get(plants.size() - 1).getId() + 4));
			
			for (int pot = 0; pot < plants.size(); pot++) {
				PlantLifeCell currentCell = plants.get(pot);
				if (pot == 0) {
					currentCell.getNeighbors().add(twoLeftCell);
					currentCell.getNeighbors().add(oneLeftCell);
					currentCell.getNeighbors().add(plants.get(pot + 1));
					currentCell.getNeighbors().add(plants.get(pot + 2));
				} else if (pot == 1) {
					currentCell.getNeighbors().add(oneLeftCell);
					currentCell.getNeighbors().add(plants.get(pot - 1));
					currentCell.getNeighbors().add(plants.get(pot + 1));
					currentCell.getNeighbors().add(plants.get(pot + 2));
				} else if (pot == plants.size() - 2) {
					currentCell.getNeighbors().add(plants.get(pot - 2));
					currentCell.getNeighbors().add(plants.get(pot - 1));
					currentCell.getNeighbors().add(plants.get(pot + 1));
					currentCell.getNeighbors().add(oneRightCell);					
				} else if (pot == plants.size() - 1) {
					currentCell.getNeighbors().add(plants.get(pot - 2));
					currentCell.getNeighbors().add(plants.get(pot - 1));
					currentCell.getNeighbors().add(oneRightCell);
					currentCell.getNeighbors().add(twoRightCell);					
				} else {
					currentCell.getNeighbors().add(plants.get(pot - 2));
					currentCell.getNeighbors().add(plants.get(pot - 1));
					currentCell.getNeighbors().add(plants.get(pot + 1));
					currentCell.getNeighbors().add(plants.get(pot + 2));
				}
				
				newPlants.add(currentCell.nextStep());
			}
			
			
			PlantLifeCell twoRightNextStep = twoRightCell.nextStep();
			PlantLifeCell oneRightNextStep = oneRightCell.nextStep();
			if (twoRightNextStep.getLiving()) {
				newPlants.add(oneRightNextStep);
				newPlants.add(twoRightNextStep);
			} else if (oneRightNextStep.getLiving()) {
				newPlants.add(oneRightNextStep);
			}
			
			boolean leftTrimmed = false;
			
			List<PlantLifeCell> newPlantsTrimmed = new ArrayList<>();
			for (PlantLifeCell cell : newPlants) {
				if (cell.getLiving() || leftTrimmed) {
					leftTrimmed = true;
					newPlantsTrimmed.add(cell);
				}
			}
			
			boolean sequenceRepeated = plants.stream().map(x -> x.getLiving()).collect(Collectors.toList())
					.equals(newPlantsTrimmed.stream().map(x -> x.getLiving()).collect(Collectors.toList())); 
			
			if (sequenceRepeated) {
				for (PlantLifeCell plant : plants) {
					plant.setId(plant.getId() + steps - step);
				}
				break;
			}
			
			plants = newPlantsTrimmed;
		}
		
		return plants.stream().filter(PlantLifeCell::getLiving).mapToLong(PlantLifeCell::getId).sum();
	}

	private static List<PlantLifeCell> buildPotListFromString(String initialState, List<PlantLifeRule> rules) {
		List<PlantLifeCell> plants = new ArrayList<>();
		for (int i = 0; i < initialState.length(); i++) {
			plants.add(new PlantLifeCell(initialState.charAt(i) == '#', rules, i));
		}
		return plants;
	}
}
