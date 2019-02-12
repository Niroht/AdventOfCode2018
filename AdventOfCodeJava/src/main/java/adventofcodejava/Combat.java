package adventofcodejava;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import adventofcodejava.entity.CombatField;
import adventofcodejava.entity.CombatTile;
import adventofcodejava.entity.Combatant;
import adventofcodejava.entity.Elf;
import adventofcodejava.entity.Goblin;

public class Combat {
	public static int sumOfRoundsAndHitPoints(String map) {
		CombatField combatField = new CombatField(map, 3);
		
		runCombatUntilEnd(combatField);
		
		return calculateScoreAtEndOfCombat(combatField);
	}
	
	public static int minimumPowerForElfVictory(String map) {
		int power = 4;
		while (true) {
			CombatField combatField = new CombatField(map, power);
			
			runCombatUntilEndOrElfDefeated(combatField);
			
			if (combatField.getAllCombatants().stream().allMatch(x -> x instanceof Elf)) {
				return calculateScoreAtEndOfCombat(combatField);
			}
			
			power++;
		}
	}
	
	private static void runCombatUntilEndOrElfDefeated(CombatField combatField) {
		while (true) {
			List<Combatant> availableCombatants = new ArrayList<>(combatField.getAllCombatants())
					.stream()
					.sorted(
							Comparator.comparing((Combatant x) -> x.getLocation().y)
							.reversed()
							.thenComparing(x -> x.getLocation().x))
					.collect(Collectors.toList());
			for (Combatant combatant: availableCombatants) {
				if (CollectionUtils.isEmpty(combatField.getOtherCombatants(combatant))) {
					return;
				}
				
				if (combatant.getHitPoints() > 0) {
					combatant.nextTurn();
					
					boolean elfDefeated = combatField
							.getAllCombatants()
							.stream()
							.anyMatch(x -> x instanceof Elf && x.getHitPoints() <= 0);
					
					if (elfDefeated) {
						return;
					}
					
					combatField.removeDefeatedCombatants();
				}
			}
			
			combatField.incrementRounds();
		}
	}
	
	private static void runCombatUntilEnd(CombatField combatField) {
		while (true) {
			List<Combatant> availableCombatants = new ArrayList<>(combatField.getAllCombatants())
					.stream()
					.sorted(
							Comparator.comparing((Combatant x) -> x.getLocation().y)
							.reversed()
							.thenComparing(x -> x.getLocation().x))
					.collect(Collectors.toList());
			for (Combatant combatant: availableCombatants) {
				if (CollectionUtils.isEmpty(combatField.getOtherCombatants(combatant))) {
					return;
				}
				
				if (combatant.getHitPoints() > 0) {
					combatant.nextTurn();
					combatField.removeDefeatedCombatants();
				}
			}
			
			combatField.incrementRounds();
		}
	}

	private static int calculateScoreAtEndOfCombat(CombatField combatField) {
		int hitPoints = combatField
				.getAllCombatants()
				.stream()
				.filter(x -> x.getHitPoints() > 0)
				.mapToInt(Combatant::getHitPoints)
				.sum() ;
		
		return combatField.getRounds() * hitPoints;
	}
}
