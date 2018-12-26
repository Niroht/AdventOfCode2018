package adventofcodejava.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlantLifeCell {
	private long id;
	
	private boolean living;
	
	private List<PlantLifeRule> rules;
	
	private List<PlantLifeCell> neighbors = new ArrayList<>();
	
	public PlantLifeCell(boolean living, List<PlantLifeRule> rules, long id) {
		this.living = living;
		this.rules = rules;
		this.id = id;
	}
	
	public PlantLifeCell nextStep() {
		Optional<PlantLifeRule> applicableRule = rules.stream().filter(x -> x.isRuleApplicable(neighbors, living)).findFirst();
		
		boolean nextStepLiving = false;
		
		if (applicableRule.isPresent()) {
			nextStepLiving = applicableRule.get().getRuleResult();
		}
		
		return new PlantLifeCell(nextStepLiving, rules, id);
	}
	
	public List<PlantLifeCell> getNeighbors(){
		return this.neighbors;
	}
	
	public boolean getLiving() {
		return this.living;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public static boolean ruleInterpreter(List<Boolean> neighborStates, boolean isLiving, boolean shouldLive) {
		return false;
	}
}
