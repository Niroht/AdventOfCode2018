package adventofcodejava;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

public class CartRunnerTest {
	@Test
	public void findFirstCollision_orderMattersLoop() throws Exception {
		// arrange
		String input = "/-<<-\\\r\n"
				+ "|    |\r\n"
				+ "\\----/";
		
		// act
		Point result = CartRunner.findFirstCollision(input);
		
		// assert
		assertEquals(new Point(0,-1), result);
	}
	
	@Test
	public void findFirstCollision_noTurns() throws Exception {
		// arrange
		String input = "|\r\n" + 
					   "V\r\n" + 
					   "|\r\n" + 
					   "|\r\n" + 
					   "|\r\n" + 
					   "^\r\n" +
					   "|";
		
		// act
		Point result = CartRunner.findFirstCollision(input);
		
		// assert
		assertEquals(new Point(0,-3), result);
	}
	
	@Test
	public void findFirstCollision_simpleInput() throws Exception {
		// arrange
		String input = "/->-\\        \r\n" + 
					   "|   |  /----\\\r\n" + 
					   "| /-+--+-\\  |\r\n" + 
					   "| | |  | V  |\r\n" + 
					   "\\-+-/  \\-+--/\r\n" + 
					   "  \\------/   ";
		
		// act
		Point result = CartRunner.findFirstCollision(input);
		
		// assert
		assertEquals(new Point(7,-3), result);
	}
	
	@Test
	public void findLastCartAfterAllCollisions_simpleInput() throws Exception {
		// arrange
		String input = "/>-<\\  \r\n" + 
				"|   |  \r\n" + 
				"| /<+-\\\r\n" + 
				"| | | v\r\n" + 
				"\\>+</ |\r\n" + 
				"  |   ^\r\n" + 
				"  \\<->/\r\n";
		
		// act
		Point result = CartRunner.findLastCartAfterAllCollisions(input);
		
		// assert
		assertEquals(new Point(6,-4), result);
	}
}
