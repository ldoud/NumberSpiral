package ldoud.exercise;

/**
 * Accepts an integer and prints the integers from 0 to that input integer in a spiral format.
 * 
 * For example, if supplied 24 the output would be:
 * 20 21 22 23 24
 * 19  6  7  8  9
 * 18  5  0  1 10
 * 17  4  3  2 11
 * 16 15 14 13 12
 *  
 * @author Leon Doud
 */
public class SpiralFormatter {
	
	// It is possible that some cells will be null even after filling in the spiral.
	// Using the wrapper class makes it easier to differentiate 
	// from the "zero" cell and cells that don't have values yet.
	private Integer[][] spiral;
	
	// Used to print out the spiral.
	private String formatPattern;
	
	// Represents the path taken to spiral out of the center.
	public enum Direction {
		EAST(0, 1),
		SOUTH(1, 0),
		WEST(0, -1),
		NORTH(-1, 0);

		private int rowModifier;
		private int columnModifier;
		
		private Direction(int rowMod, int colMod) {
			rowModifier = rowMod;
			columnModifier = colMod;
		}		
		
		/**
		 * Determines the next direction in the spiral.
		 * @return East, South, West then North (it wraps back to East from North)
		 */
		public Direction getNext() {
			return values()[(ordinal()+1) % values().length];
		}
		
		/**
		 * Determines the previous direction in the spiral.
		 * @return North, West, South then East (it wraps back to North from East)
		 */
		public Direction getPrevious() {		
			// Avoid the mod of -1 by adding the length of the array to ordinal.
			return values()[(ordinal() + values().length -1) % values().length];  
		}
		
		public int getRowModifer() {
			return rowModifier;
		}
		
		public int getColumnModifier() {
			return columnModifier;
		}
	}
	
	public SpiralFormatter(int max) {		
		int columnWidth = max % 10;
		formatPattern = "%"+columnWidth+"s";
		
		// Adding one to maxNumber because printing starts at zero.
		int numberOfColumns = (int)Math.ceil(Math.sqrt(max + 1));		
		// 1f is necessary so a remainder will be generated.
		int numberOfRows = (int)Math.ceil((max + 1f) / numberOfColumns);
		spiral = new Integer[numberOfRows][numberOfColumns];
		
		// Set the starting location to "zero" cell.
		int currentRow = Math.round(numberOfRows / 2f) - 1;
		int currentColumn = Math.round(numberOfColumns / 2f) - 1;
		spiral[currentRow][currentColumn] = new Integer(0);
		int nextNumber = 1;
		
		// Spiral east, south, west and north until the max number is reached.
		Direction currentDirection = Direction.EAST;
		while(nextNumber <= max) {
			int nextRow = currentRow + currentDirection.getRowModifer();
			int nextColumn = currentColumn + currentDirection.getColumnModifier();				
			Integer nextCellValue = spiral[nextRow][nextColumn];
			
			if (nextCellValue == null) {
				spiral[nextRow][nextColumn] = new Integer(nextNumber);
				
				// Advance row, column and number tracking variables.
				currentRow = nextRow;
				currentColumn = nextColumn;
				nextNumber += 1;
				
				// Attempt to change directions next time.
				currentDirection = currentDirection.getNext();
			}
			else {
				// Haven't passed the inner loop of the spiral yet.
				currentDirection = currentDirection.getPrevious();
			}
		}
	}
	
	public void print() {		
		for(int row=0; row < spiral.length; row++) {
			for(int column=0; column < spiral[row].length; column++) {
				Object valueToPrint = spiral[row][column];
				if (valueToPrint == null) {
					valueToPrint = "";
				}
				System.out.printf(formatPattern, valueToPrint);
			}
			System.out.printf("%n");
		}
	}
	
	private static void printUsage() {
		System.out.println("usage: java ldoud.exercise.SpiralFormatter IntegerGreaterThanZero");
	}
	
	public static void main(String[] args) {
		if (args.length == 1) {
			try {
				int maxNumber = Integer.parseInt(args[0]);
				
				// This will be skipped if the program arg isn't an integer.
				SpiralFormatter printer = new SpiralFormatter(maxNumber);
				printer.print();				
			} catch (NumberFormatException notAnInteger) {
				printUsage();
			}
		}
		else {
			printUsage();
		}
	}
}
