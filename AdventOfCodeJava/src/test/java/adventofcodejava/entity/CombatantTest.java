package adventofcodejava.entity;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CombatantTest {
	@Test
	public void nextTurn_doNothingIfNoTargets() {
		// arrange
		CombatField field = new CombatField("E..", 3);
		
		Combatant combatant = field.getAllCombatants().get(0);
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(new Point(0,0), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_doNothingIfNoEnemyTargets() {
		// arrange
		CombatField field = new CombatField("E.E", 3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(0, 0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(new Point(0, 0), combatant.getLocation());
	}

	@Test
	public void nextTurn_moveTowardsEnemyTarget() {
		// arrange
		CombatField field = new CombatField("E.G", 3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(0, 0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(new Point(1,0), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_movesAndAttacksInSameTurn() {
		// arrange
		CombatField field = new CombatField("E.G", 3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(0, 0))).findFirst().get();
		Combatant enemy = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(2, 0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(197, enemy.getHitPoints());
	}
	
	@Test
	public void nextTurn_doNotInterceptAlly() {
		// arrange
		CombatField field = new CombatField("EEG", 3);
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(0, 0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(new Point(0,0), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_doNotMoveIfInRangeOfEnemy() {
		// arrange
		CombatField field = new CombatField("EGG", 3);
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(0, 0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(new Point(0,0), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_moveTowardsClosestEnemy() {
		// arrange
		CombatField field = new CombatField(
				"E..G\r\n" + 
				"....\r\n" + 
				"G...",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(0, 0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(new Point(0,-1), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_multipleClosestEnemies_readingDirection() {
		// arrange
		CombatField field = new CombatField(
				"..E..\r\n" + 
				".....\r\n" +
				"G...G",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(2, 0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(new Point(1,0), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_multipleShortestPaths_readingDirectionRight() {
		// arrange
		CombatField field = new CombatField(
				"E.\r\n" + 
				".G",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(0, 0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* .E
		 * .G
		 */
		assertEquals(new Point(1,0), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_multipleShortestPaths_readingDirectionUp() {
		// arrange
		CombatField field = new CombatField(
				"G.\r\n" + 
				".E",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1, -1))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* G E
		 * . .
		 */
		assertEquals(new Point(1,0), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_multipleShortestPaths_readingDirectionLeft() {
		// arrange
		CombatField field = new CombatField(
				".E\r\n" + 
				"G.",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1, 0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* E .
		 * G .
		 */
		assertEquals(new Point(0,0), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_multipleShortestPaths_firstEnemyInReadingOrder() {
		// arrange
		CombatField field = new CombatField(
				"##G.#\r\n" + 
				"###.#\r\n" +
				"#.E.#\r\n" +
				"#.###\r\n" +
				"#.G##",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(2, -2))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* ##G.#
		 * ###.#
		 * #..E#
		 * #.###
		 * #.G##
		 */
		assertEquals(new Point(3,-2), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_multipleShortestPaths_moreThanOneDestination() {
		// arrange
		CombatField field = new CombatField(
				"##G.#\r\n" + 
				"###.#\r\n" +
				"#.E.#\r\n" +
				"#.###\r\n" +
				"#.G##",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(2, -2))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* ##G.#
		 * ###.#
		 * #..E#
		 * #.###
		 * #.G##
		 */
		assertEquals(new Point(3,-2), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_wallInWay_movesAroundWall() {
		// arrange
		CombatField field = new CombatField(
				"#####\r\n" + 
				"#E#G#\r\n" + 
				"#.#.#\r\n" + 
				"#...#\r\n" + 
				"#####",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1, -1))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* #####
		 * #.#G#
		 * #E#.#
		 * #...#
		 * #####
		 */
		assertEquals(new Point(1,-2), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_wallInWay_movesAroundWallWithDeadEnd() {
		// arrange
		CombatField field = new CombatField(
				"#####\r\n" + 
				"#.#G#\r\n" + 
				"#E#.#\r\n" + 
				"#...#\r\n" + 
				"#####",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1, -2))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* #####
		 * #.#G#
		 * #.#.#
		 * #E..#
		 * #####
		 */
		assertEquals(new Point(1,-3), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_wallInWay_movesTowardsNearestReachableEnemy() {
		// arrange
		CombatField field = new CombatField(
				"######\r\n" + 
				"#.E#G#\r\n" + 
				"#..#.#\r\n" + 
				"#G...#\r\n" + 
				"######",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(2, -1))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* ######
		 * #E.#G#
		 * #..#.#
		 * #G...#
		 * ######
		 */
		assertEquals(new Point(1,-1), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_wallInWay_movesTowardsNearestReachableEnemy_sameDirectionAsClosestNonReachable() {
		// arrange
		CombatField field = new CombatField(
				"######\r\n" + 
				"#.E#G#\r\n" + 
				"#..#.#\r\n" + 
				"#....#\r\n" +
				"#.G..#\r\n" +
				"######",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(2, -1))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* ######
		 * #..#G#
		 * #.E#.#
		 * #....#
		 * #.G..#
		 * ######
		 */
		assertEquals(new Point(2,-2), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_alliesInWay() {
		// arrange
		CombatField field = new CombatField(
				"#######\r\n" + 
				"#...G.#\r\n" + 
				"#..G.G#\r\n" + 
				"#.#.#G#\r\n" + 
				"#...#E#\r\n" + 
				"#.....#\r\n" + 
				"#######",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(4, -1))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* #######   
		   #..G..#
		   #..G.G#
		   #.#.#G#
		   #...#E#
		   #.....#   
		   #######
		 */
		assertEquals(new Point(3,-1), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_shorterPath() {
		// arrange
		CombatField field = new CombatField(
				"#######\r\n" + 
				"#...G.#\r\n" + 
				"#..G.G#\r\n" + 
				"#.#.#G#\r\n" + 
				"#...#E#\r\n" + 
				"#.....#\r\n" + 
				"#######",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(3, -2))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* #######   
		   #...G.#
		   #....G#
		   #.#G#G#
		   #...#E#
		   #.....#   
		   #######
		 */
		assertEquals(new Point(3,-3), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_doesNotMoveIfNoSpacesAdjacentToEnemy() {
		// arrange
		CombatField field = new CombatField(
				"#######\r\n" + 
				"#G....#\r\n" + 
				"#.G...#\r\n" + 
				"#.#.#G#\r\n" + 
				"#...#E#\r\n" + 
				"#....G#\r\n" + 
				"#######",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1, -1))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		/* #######   
		   #G....#
		   #.G...#
		   #.#.#G#
		   #...#E#
		   #....G#   
		   #######
		 */
		assertEquals(new Point(1,-1), combatant.getLocation());
	}
	
	@Test
	public void nextTurn_attacksAdjacentEnemy() {
		// arrange
		CombatField field = new CombatField("EG",3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(0,0))).findFirst().get();
		Combatant enemy = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1,0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(197,  enemy.getHitPoints());
	}
	
	@Test
	public void nextTurn_doesNotAttackAdjacentAlly() {
		// arrange
		CombatField field = new CombatField("EE", 3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(0,0))).findFirst().get();
		Combatant enemy = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1,0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(200,  enemy.getHitPoints());
	}
	
	@Test
	public void nextTurn_attacksAdjacentEnemyInReadingOrder_left() {
		// arrange
		CombatField field = new CombatField("GEG", 3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1,0))).findFirst().get();
		Combatant enemy = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(0,0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(197,  enemy.getHitPoints());
	}
	
	@Test
	public void nextTurn_attacksAdjacentEnemyInReadingOrder_up() {
		// arrange
		CombatField field = new CombatField(
				".G.\r\n" + 
				".EG",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1,-1))).findFirst().get();
		Combatant enemy = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1,0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(197,  enemy.getHitPoints());
	}
	
	@Test
	public void nextTurn_attacksAdjacentEnemyInReadingOrder_right() {
		// arrange
		CombatField field = new CombatField(
				".EG\r\n" + 
				".G.",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1,0))).findFirst().get();
		Combatant enemy = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(2,0))).findFirst().get();
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(197,  enemy.getHitPoints());
	}
	
	@Test
	public void nextTurn_attacksWeakestEnemy() {
		// arrange
		CombatField field = new CombatField(
				".EG\r\n" + 
				".G.",
				3);
		
		Combatant combatant = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1,0))).findFirst().get();
		Combatant enemy = field.getAllCombatants().stream().filter(x -> x.getLocation().equals(new Point(1,-1))).findFirst().get();
		enemy.receiveDamage(3);
		
		// act
		combatant.nextTurn();
		
		// assert
		assertEquals(194,  enemy.getHitPoints());
	}
}
