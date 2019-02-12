package adventofcodejava.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;

import adventofcodejava.utils.AdventPointUtil;

public abstract class Combatant {
	private Point location;
	
	private CombatField field;
	
	private int hitPoints;
	
	private int power;
	
	protected Combatant(Point location, CombatField field, int power) {
		this.location = location;
		this.field = field;
		
		this.hitPoints = 200;
		this.power = power;
	}
	
	protected Combatant(Point location, CombatField field) {
		this.location = location;
		this.field = field;
		
		this.hitPoints = 200;
		this.power = 3;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public int getHitPoints() {
		return hitPoints;
	}
	
	public void receiveDamage(int power) {
		hitPoints -= power;
	}
	
	public void nextTurn() {
		Point nextLocation = findNextPointOnRouteToClosestEnemy();
		
		if (!nextLocation.equals(location)) {
			Point priorLocation = new Point(location.x, location.y);
			location = new Point(nextLocation.x, nextLocation.y);
			
			field.moveCombatant(priorLocation, this);
		}
		
		List<Combatant> adjacentEnemies = field
				.getOtherCombatants(this)
				.stream()
				.filter(x -> AdventPointUtil.manhattanDistance(location, x.getLocation()) == 1)
				.collect(Collectors.toList());
		
		if (CollectionUtils.isNotEmpty(adjacentEnemies)) {
			List<Combatant> weakestEnemies = new ArrayList<>();
			
			adjacentEnemies.forEach(x -> {
				if (CollectionUtils.isEmpty(weakestEnemies) || x.getHitPoints() < weakestEnemies.get(0).getHitPoints()) {
					weakestEnemies.clear();
					weakestEnemies.add(x);
				} else if (weakestEnemies.get(0).getHitPoints() == x.getHitPoints()) {
					weakestEnemies.add(x);
				}
			});
			
			
			weakestEnemies
			.stream()
			.sorted(
					Comparator.comparing((Combatant x) -> x.getLocation().y)
					.reversed()
					.thenComparing(x -> x.getLocation().x))
			.findFirst()
			.get()
			.receiveDamage(power);
		}
	}
	
	private Point findNextPointOnRouteToClosestEnemy() {
		List<Combatant> enemies = field.getOtherCombatants(this);
		
		if (CollectionUtils.isEmpty(enemies) || enemies.stream().anyMatch(x -> AdventPointUtil.manhattanDistance(location, x.getLocation()) == 1)) {
			return location;
		}
		
		PathNode rootNode = new PathNode(location);
		
		List<Point> pointsHit = new ArrayList<>();
		
		List<PathNode> workingNodes = new ArrayList<>();
		workingNodes.add(rootNode);
		
		boolean foundEnemy = false;
		int currentScore = 1;
		
		while (!foundEnemy && CollectionUtils.isNotEmpty(workingNodes)) {
			List<PathNode> newWorkingNodes = new ArrayList<>();
			
			for(PathNode node : workingNodes) {
				List<Point> neighbors = Arrays.asList(
						new Point(node.getLocation().x, node.getLocation().y + 1),
						new Point(node.getLocation().x - 1, node.getLocation().y),
						new Point(node.getLocation().x + 1, node.getLocation().y),
						new Point(node.getLocation().x, node.getLocation().y - 1)
						);
				
				for (Point neighbor : neighbors) {
					Optional<CombatTile> maybeTile = field.getMap().stream().filter(x -> x.getLocation().equals(neighbor)).findFirst();
					
					if (pointsHit.contains(neighbor) || !maybeTile.isPresent() || !maybeTile.get().getCanEnter()) {
						continue;
					}
					
					CombatTile tile = maybeTile.get();
					
					boolean containsAlly = tile.getOccupant() != null && tile.getOccupant().getClass().equals(this.getClass());
					boolean containsEnemy = tile.getOccupant() != null && !tile.getOccupant().getClass().equals(this.getClass());
					
					PathNode firstStepNode = currentScore == 1 ? new PathNode(neighbor) : node.getRoot();
					
					if (containsEnemy) {
						foundEnemy = true;
						newWorkingNodes.add(new PathNode(neighbor, firstStepNode, containsEnemy));
					} else if (!containsAlly) {
						newWorkingNodes.add(new PathNode(neighbor, firstStepNode, containsEnemy));
					}
					
					if (!foundEnemy) {
						pointsHit.add(neighbor);
					}
					
				}
			}
			
			currentScore++;
			workingNodes = newWorkingNodes;
		}
		
		if (CollectionUtils.isEmpty(workingNodes)) {
			return location;
		}
		
		List<PathNode> pathsSortedByFirstEnemyReadingDirection = workingNodes
				.stream()
				.filter(PathNode::getHasEnemy)
				.sorted(
						Comparator.comparing((PathNode x) -> x.getLocation().y)
						.reversed()
						.thenComparing(x -> x.getLocation().x))
				.collect(Collectors.toList());
		
		return pathsSortedByFirstEnemyReadingDirection
				.stream()
				.filter(x -> x.getLocation().equals(pathsSortedByFirstEnemyReadingDirection.get(0).getLocation()))
				.map(x -> x.getRoot())
				.sorted(
						Comparator.comparing((PathNode x) -> x.getLocation().y)
						.reversed()
						.thenComparing(x -> x.getLocation().x))
				.findFirst()
				.get()
				.getLocation();
	}
	
	@Override
	public String toString() {
		return String.format("%s Hit Points: %s; Location: %s", this.getClass().getSimpleName(), this.hitPoints, this.location);
	}
}
