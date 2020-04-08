import csis4463.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * For this assignment, you will need the puzzle.jar file and its documentation (docs.zip) 
 * from homework 4, and the Examples.java will be useful as well.
 *
 * In Java, implement any search algorithm that we saw in class to solve sliding tile
 * puzzles, such as the 8 puzzle, 15 puzzle, etc.
 *
 * You must not change the name, parameters, or return type of the solver method.
 * You are free to add as many helper methods as you find useful.
 *
 * You are free to use the MinHeapPQ class that is in the puzzle.jar (see its documentation).
 * It supports changing the priority of elements that are in the PQ, whereas the PQ implementation
 * in the Java API does not support that operation.
 *
 * Grading:
 * Your grade will be in the interval [0, 100] if you implement an algorithm that is guaranteed
 * to find the optimal path.  Otherwise, if you implement an algorithm that is not
 * guaranteed to find the optimal path (e.g., DFS), then your grade will be in the interval [0, 85]
 * (i.e., you lose 15 points for the non-optimal algorithms).
 *
 * If your code doesn't compile, then your grade will be in the interval [0, 60] depending upon the severity
 * of the syntax errors.  i.e., make sure your code compiles (you lose at least 40 points if it doesn't.
 *
 * After completing the solver method, write code to demonstrate that it works in the Homework5Main class.
 *
 * @author Eric Einhaus
 *
 */
public class Homework5 {
	
	/**
	 * Solves sliding tile puzzles with the algorithm of your choice.
	 *
	 * @return A path from the start state to the goal state.
	 */
	public ArrayList<SlidingTilePuzzle> solver(SlidingTilePuzzle start) {
		//A* Implementation with the Misplaced Tile Heuristic 
		
		//The Min Heap Priority Queue used
		MinHeapPQ<SlidingTilePuzzle> pq = new MinHeapPQ<SlidingTilePuzzle>();
		
		//HashMap used to keep track of all f values (current path from start to current position + Misplaced tile heuristic
		HashMap<SlidingTilePuzzle, Integer> fValues = new HashMap<SlidingTilePuzzle, Integer>();
		
		//HashMap used to keep track of all backpointers. This is helpful when wanting to find the path from start to goal state
		HashMap<SlidingTilePuzzle, SlidingTilePuzzle> back = new HashMap<SlidingTilePuzzle, SlidingTilePuzzle>();
		
		//Our puzzle instances from start to finish will be stored in this ArrayList
		ArrayList<SlidingTilePuzzle> path = new ArrayList<>();
		
		//Number of rows and columns of the puzzle
		int rows = start.numRows();
		int columns = start.numColumns();
		
		//A fully solved SlidingTilePuzzle. Will be helpful for the heuristic function below
		SlidingTilePuzzle solvedSlidingTilePuzzle = new SlidingTilePuzzle(rows, columns, 0);
		
		//add start to priority queue with priority 0, add its f value to the fValues HashMap, and its backpointer of null to the back HashMap.
		pq.offer(start, 0);
		fValues.put(start, 0);
		back.put(start, null);
		
		//Start of main algorithm
		while(!pq.isEmpty()) {
			int priority = pq.peekPriority(); 
			SlidingTilePuzzle puzzleWithLowestPri = pq.poll();
			// (puzzleWithLowestPri, priority)
			if (puzzleWithLowestPri.isGoalState()) {
				// stop and report success and print out the graph using the backpointer HashMap.
				path.add(puzzleWithLowestPri);
				SlidingTilePuzzle currentBack = back.get(puzzleWithLowestPri);
				while (!currentBack.equals(start)) {
					path.add(currentBack);
					currentBack = back.get(currentBack);
				}
				path.add(start);
				return path;
			}
			//if not the goal state, grab its successors, get their fPrime values, and depending on conditions below, add it to pq and fValues with priority fPrime
			//and keep track of its backpointer in this form -> (successor, polledLowestPriorityPuzzle)
			ArrayList<SlidingTilePuzzle> successors = puzzleWithLowestPri.getSuccessors();
			for (SlidingTilePuzzle s : successors) {
				//Let f’ = g(n') + h(n') = g(n) + cost(n,n') + h(n')
				int fPrime = priority + 1 + numOfMisplacedTiles(s, solvedSlidingTilePuzzle, rows, columns);
				//If n' not seen before, or n' previously expanded with f(n')>f’, or n' currently in PQ with f(n')>f’
				if (!fValues.containsKey(s) || (fValues.containsKey(s) && fValues.get(s) > fPrime) || (pq.inPQ(s) && fValues.get(s) > fPrime)) {
					//Then Place/promote n' on priority queue with priority f’ and update V to include (state=n', f ’, BackPtr=n).
					pq.offer(s, fPrime);
					fValues.put(s, fPrime);
					back.put(s, puzzleWithLowestPri);
				}	
			}
		}
		//If the pq is empty, this means no solution path exists. This will return an empty list.
		System.out.println("No solution path exists!");
		return path; //returns an empty list
	}
		//Using the misplaced tiles admissible heuristic, compare each tile piece by piece the solved and the unsolved puzzle. If they don't match
		//at that position, then increment the hValue. Return the hValue once iterated through all of the tiles.
		public int numOfMisplacedTiles(SlidingTilePuzzle unSolvedPuzzle, SlidingTilePuzzle solvedPuzzle, int rows, int columns) {
			int hValue = 0;
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < columns; j++) {
					int tileNumber = unSolvedPuzzle.getTile(i , j);
					int idealTile = solvedPuzzle.getTile(i, j);
					if (tileNumber != idealTile) {
						hValue++;
					}
				}
			}
			return hValue;
		}
		
		//Prints out each state from start to goal of the puzzle in the console in an organized way.
		public void printPath(ArrayList<SlidingTilePuzzle> puzz) {
			System.out.println("--------------------------------------------------\nGoal State Found!" + "\n\n" + "Solution path:" + "\n");
			for(int i = puzz.size() - 1; i >= 0; i--) {
				System.out.println(puzz.get(i));
				if (i > 0) {
					System.out.println("\t|\n\t|\n\t|\n\tV\n");
				}
			}
		}
}