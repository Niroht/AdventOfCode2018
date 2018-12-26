package adventofcodejava.entity;

import java.util.List;

public class PlantLifeRule {
	private List<Boolean> neighborStates;
	
	private boolean originalStateLiving;
	
	private boolean ruleResult;
	
	public PlantLifeRule(List<Boolean> neighborStates, boolean originalStateLiving, boolean ruleResult) {
		this.neighborStates = neighborStates;
		this.originalStateLiving = originalStateLiving;
		this.ruleResult = ruleResult;
	}
	
	public boolean isRuleApplicable(List<PlantLifeCell> neighbors, boolean isLiving) {
		if (originalStateLiving != isLiving || neighbors.size() != neighborStates.size()) {
			return false;
		}
		
		for(int i = 0; i < neighbors.size(); i++){
			if (!neighborStates.get(i).equals(neighbors.get(i).getLiving())) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean getRuleResult() {
		return this.ruleResult;
	}
}
