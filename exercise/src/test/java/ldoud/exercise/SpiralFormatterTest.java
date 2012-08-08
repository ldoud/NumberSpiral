package ldoud.exercise;

import java.util.Arrays;

import junit.framework.Assert;
import ldoud.exercise.SpiralFormatter.Direction;

import org.junit.Test;

public class SpiralFormatterTest {

	/**
	 * Test a spiral that ends at zero.
	 */
	@Test
	public void testMaxOfZero() {
		SpiralFormatter printer = new SpiralFormatter(0);
		Integer[][] spiral = printer.getSpiral();
		Assert.assertEquals("Number of rows", 1, spiral.length);
		Assert.assertEquals("Number of columns", 1, spiral[0].length);
		
		Integer[] expected = {0};
		assertArray("Row 1 values", expected, spiral[0]);
	}
	
	/**
	 * Test a spiral that ends at one.
	 */
	@Test
	public void testMaxOf1() {
		SpiralFormatter printer = new SpiralFormatter(1);
		Integer[][] spiral = printer.getSpiral();
		Assert.assertEquals("Number of rows", 1, spiral.length);
		Assert.assertEquals("Number of columns", 2, spiral[0].length);
		
		Integer[] expected = {0, 1};
		assertArray("Row 1 values", expected, spiral[0]);
	}
	
	
	/**
	 * Test a square with a length of 2.
	 */
	@Test
	public void testMaxof3() {
		SpiralFormatter printer = new SpiralFormatter(3);
		Integer[][] spiral = printer.getSpiral();
		Assert.assertEquals("Number of rows", 2, spiral.length);
		Assert.assertEquals("Number of columns", 2, spiral[0].length);
		
		Integer[] row1 = {0, 1};
		assertArray("Row 1 values", row1, spiral[0]);
		
		Integer[] row2 = {3, 2};
		assertArray("Row 2 values", row2, spiral[1]);
	}
	
	/**
	 * Test a square with a length of 3.
	 */
	@Test
	public void testMaxOf8() {
		SpiralFormatter printer = new SpiralFormatter(8);
		Integer[][] spiral = printer.getSpiral();
		Assert.assertEquals("Number of rows", 3, spiral.length);
		Assert.assertEquals("Number of columns", 3, spiral[0].length);
		
		Integer[] row1 = {6, 7, 8};
		assertArray("Row 1 values", row1, spiral[0]);
		
		Integer[] row2 = {5, 0, 1};
		assertArray("Row 2 values", row2, spiral[1]);
		
		Integer[] row3 = {4, 3, 2};
		assertArray("Row 3 values", row3, spiral[2]);
	}
	
	/**
	 * Test a square with a length of 3 and has a negative final number.
	 */
	@Test
	public void testMaxOfNegative8() {
		SpiralFormatter printer = new SpiralFormatter(-8);
		Integer[][] spiral = printer.getSpiral();
		Assert.assertEquals("Number of rows", 3, spiral.length);
		Assert.assertEquals("Number of columns", 3, spiral[0].length);
		
		Integer[] row1 = {-6, -7, -8};
		assertArray("Row 1 values", row1, spiral[0]);
		
		Integer[] row2 = {-5, 0, -1};
		assertArray("Row 2 values", row2, spiral[1]);
		
		Integer[] row3 = {-4, -3, -2};
		assertArray("Row 3 values", row3, spiral[2]);
	}
	
	/**
	 * Test a non-square spiral
	 */
	@Test
	public void testMaxOf19() {
		SpiralFormatter printer = new SpiralFormatter(19);
		Integer[][] spiral = printer.getSpiral();
		Assert.assertEquals("Number of rows", 4, spiral.length);
		Assert.assertEquals("Number of columns", 5, spiral[0].length);
		
		Integer[] row1 = {19, 6, 7, 8, 9};
		assertArray("Row 1 values", row1, spiral[0]);
		
		Integer[] row2 = {18, 5, 0, 1, 10};
		assertArray("Row 2 values", row2, spiral[1]);
		
		Integer[] row3 = {17, 4, 3, 2, 11};
		assertArray("Row 3 values", row3, spiral[2]);
		
		Integer[] row4 = {16, 15, 14, 13, 12};
		assertArray("Row 4 values", row4, spiral[3]);
	}
	
	/**
	 * Test a spiral that has a column and 2 rows that are not filled completely.
	 */
	@Test
	public void testMaxOf17() {
		SpiralFormatter printer = new SpiralFormatter(17);
		Integer[][] spiral = printer.getSpiral();
		Assert.assertEquals("Number of rows", 4, spiral.length);
		Assert.assertEquals("Number of columns", 5, spiral[0].length);
		
		Integer[] row1 = {null, 6, 7, 8, 9};
		assertArray("Row 1 values", row1, spiral[0]);
		
		Integer[] row2 = {null, 5, 0, 1, 10};
		assertArray("Row 2 values", row2, spiral[1]);
		
		Integer[] row3 = {17, 4, 3, 2, 11};
		assertArray("Row 3 values", row3, spiral[2]);
		
		Integer[] row4 = {16, 15, 14, 13, 12};
		assertArray("Row 4 values", row4, spiral[3]);
	}
	
	/**
	 * Test a spiral that has a corner cell that isn't fill completely.
	 */
	@Test
	public void testMaxOf7() {
		SpiralFormatter printer = new SpiralFormatter(7);
		Integer[][] spiral = printer.getSpiral();
		Assert.assertEquals("Number of rows", 3, spiral.length);
		Assert.assertEquals("Number of columns", 3, spiral[0].length);
		
		Integer[] row1 = {6, 7, null};
		assertArray("Row 1 values", row1, spiral[0]);
		
		Integer[] row2 = {5, 0, 1};
		assertArray("Row 2 values", row2, spiral[1]);
		
		Integer[] row3 = {4, 3, 2};
		assertArray("Row 3 values", row3, spiral[2]);
		
	}
	
	/**
	 * Determine which direction to go in next.
	 */
	@Test
	public void testNextDirection() {
		Assert.assertEquals("Next value for East", Direction.SOUTH, Direction.EAST.getNext());
		Assert.assertEquals("Next value for Sout", Direction.WEST, Direction.SOUTH.getNext());
		Assert.assertEquals("Next value for West", Direction.NORTH, Direction.WEST.getNext());
		Assert.assertEquals("Next value for North", Direction.EAST, Direction.NORTH.getNext());
	}
	
	/**
	 * Determine which direction was prior to the current one.
	 */
	@Test
	public void testNextPrevious() {
		Assert.assertEquals("Prior value for East", Direction.NORTH, Direction.EAST.getPrevious());
		Assert.assertEquals("Prior value for Sout", Direction.EAST, Direction.SOUTH.getPrevious());
		Assert.assertEquals("Prior value for West", Direction.SOUTH, Direction.WEST.getPrevious());
		Assert.assertEquals("Prior value for North", Direction.WEST, Direction.NORTH.getPrevious());
	}

	private void assertArray(String mesg, Integer[] expected, Integer[] actual) {
		boolean passed = expected.length == actual.length;
		
		for(int index=0; index < expected.length && passed; index++) {
			
			if (expected[index] != null) {
				passed = expected[index].equals(actual[index]);
			}
			else {
				passed = actual[index] == null;
			}
		}
		
		if (!passed) {
			Assert.failNotEquals(mesg, Arrays.toString(expected), Arrays.toString(actual));
		}
	}
}
