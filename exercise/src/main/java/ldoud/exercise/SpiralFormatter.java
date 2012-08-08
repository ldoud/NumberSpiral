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
	
	private int[][] spiral;
	
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
		 * Controls the next direction in the spiral.
		 * @return East, South, West then North (it wraps back to East from North)
		 */
		public Direction getNext() {
			return values()[(ordinal()+1) % values().length];
		}
		
		/**
		 * Controls the previous direction in the spiral.
		 * @return East, South, West then North (it wraps back to East from North)
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
		// Adding one to maxNumber because printing starts at zero.
		int numberOfColumns = (int)Math.ceil(Math.sqrt(max + 1));		
		// 1f is necessary so a remainder will be generated.
		int numberOfRows = (int)Math.ceil((max + 1f) / numberOfColumns);
		spiral = new int[numberOfRows][numberOfColumns];
		
		// Default starting location to center cell
		int centerRow = Math.round(numberOfRows / 2f) - 1;
		int centerColumn = Math.round(numberOfColumns / 2f) - 1;
		
		// Start with zero at the center
		spiral[centerRow][centerColumn] = 0;
		int nextNumber = 1;
		
		// Spiral east, south, west and north
		int currentRow = centerRow;
		int currentColumn = centerColumn;
		Direction currentDirection = Direction.EAST;
		while(nextNumber <= max) {
			int nextRow = currentRow + currentDirection.getRowModifer();
			int nextColumn = currentColumn + currentDirection.getColumnModifier();				
			int nextCellValue = spiral[nextRow][nextColumn];
			
			if (nextCellValue == 0 && (nextRow != centerRow || nextColumn != centerColumn)) {
				spiral[nextRow][nextColumn] = nextNumber;
				
				// Advance row, column and number tracking variables.
				currentRow = nextRow;
				currentColumn = nextColumn;
				nextNumber += 1;
				
				// Attempt to change directions next time
				currentDirection = currentDirection.getNext();
			}
			else {
				// Haven't passed the inner loop of the spiral yet.
				currentDirection = currentDirection.getPrevious();
			}
		}
	}
	
	public void print() {
		StringBuffer output = new StringBuffer();
		for(int row=0; row < spiral.length; row++) {
			for(int column=0; column < spiral[row].length; column++) {
				output.append(spiral[row][column]);
				output.append(" ");
			}
			System.out.println(output);
			output = new StringBuffer();
		}
	}
	
	public static void main(String[] args) {
		SpiralFormatter printer = new SpiralFormatter(24);
		printer.print();
	}
}
