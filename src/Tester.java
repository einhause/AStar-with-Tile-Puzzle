

import csis4463.*;

/**
 * See comments in Homework5.java first.
 * 
 * @author Eric Einhaus
 */
public class Homework5Main {
	
	public static void main(String[] args) {
		//Creating instance of hw5
		Homework5 hw5 = new Homework5();
		
		//8 puzzle test cases for 2, 4, 6, 8, 10, and 12 moves remaining
		SlidingTilePuzzle puzzle3_2 = new SlidingTilePuzzle(3, 3, 2);
		System.out.println("--------------------------------------------------\nA 3x3 Puzzle with 2 moves remaining:");
		hw5.printPath(hw5.solver(puzzle3_2));
		SlidingTilePuzzle puzzle3_4 = new SlidingTilePuzzle(3, 3, 4);
		System.out.println("--------------------------------------------------\nA 3x3 Puzzle with 4 moves remaining:");
		hw5.printPath(hw5.solver(puzzle3_4));
		SlidingTilePuzzle puzzle3_6 = new SlidingTilePuzzle(3, 3, 6);
		System.out.println("--------------------------------------------------\nA 3x3 Puzzle with 6 moves remaining:");
		hw5.printPath(hw5.solver(puzzle3_6));
		SlidingTilePuzzle puzzle3_8 = new SlidingTilePuzzle(3, 3, 8);
		System.out.println("--------------------------------------------------\nA 3x3 Puzzle with 8 moves remaining:");
		hw5.printPath(hw5.solver(puzzle3_8));
		SlidingTilePuzzle puzzle3_10 = new SlidingTilePuzzle(3, 3, 10);
		System.out.println("--------------------------------------------------\nA 3x3 Puzzle with 10 moves remaining:");
		hw5.printPath(hw5.solver(puzzle3_10));
		SlidingTilePuzzle puzzle3_12 = new SlidingTilePuzzle(3, 3, 12);
		System.out.println("--------------------------------------------------\nA 3x3 Puzzle with 12 moves remaining:");
		hw5.printPath(hw5.solver(puzzle3_12));
		
		
		//16 puzzle test cases of 6, 10, and 12 moves remaining
		SlidingTilePuzzle puzzle4_6 = new SlidingTilePuzzle(4, 4, 6);
		System.out.println("--------------------------------------------------\nA 4x4 Puzzle with 6 moves remaining:");
		hw5.printPath(hw5.solver(puzzle4_6));
		SlidingTilePuzzle puzzle4_10 = new SlidingTilePuzzle(4, 4, 10);
		System.out.println("--------------------------------------------------\nA 4x4 Puzzle with 10 moves remaining:");
		hw5.printPath(hw5.solver(puzzle4_10));
		SlidingTilePuzzle puzzle4_12 = new SlidingTilePuzzle(4, 4, 12);
		System.out.println("--------------------------------------------------\nA 4x4 Puzzle with 12 moves remaining:");
		hw5.printPath(hw5.solver(puzzle4_12));
		
		
		//25 puzzle test cases with 6 and 12 moves remaining
		SlidingTilePuzzle puzzle5_6 = new SlidingTilePuzzle(5, 5, 6);
		System.out.println("--------------------------------------------------\nA 5x5 Puzzle with 6 moves remaining:");
		hw5.printPath(hw5.solver(puzzle5_6));
		SlidingTilePuzzle puzzle5_12 = new SlidingTilePuzzle(5, 5, 12);
		System.out.println("--------------------------------------------------\nA 5x5 Puzzle with 12 moves remaining:");
		hw5.printPath(hw5.solver(puzzle5_12));
		
		//36 puzzle test case with 12 moves remaining
		SlidingTilePuzzle puzzle6_12 = new SlidingTilePuzzle(6, 6, 12);
		System.out.println("--------------------------------------------------\nA 6x6 Puzzle with 12 moves remaining:");
		hw5.printPath(hw5.solver(puzzle6_12));
		
		//64 puzzle test case with 16 moves remaining
		SlidingTilePuzzle puzzle8_16 = new SlidingTilePuzzle(8, 8, 16);
		System.out.println("--------------------------------------------------\nA 8x8 Puzzle with 16 moves remaining:");
		hw5.printPath(hw5.solver(puzzle8_16));
	}
}