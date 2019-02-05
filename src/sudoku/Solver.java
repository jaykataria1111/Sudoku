package sudoku;

import java.util.*;

import sudoku.Grid;


public class Solver 
{
	private Grid						problem;
	private ArrayList<Grid>				solutions;
	
	
	public Solver(Grid problem)
	{
		this.problem = problem;
	}
	
	
	public void solve()
	{
		solutions = new ArrayList<Grid>();
		solveRecurse(problem);
	}
	
		
	// 
	// FINISH THIS.
	//
	// Standard backtracking recursive solver.
	//
	private void solveRecurse(Grid grid)
	{		
      Evaluation eval = evaluate(grid);
		
		if (eval == Evaluation.ABANDON)
		{
			// Abandon evaluation of this illegal board.
			return;
			
		}
		else if (eval == Evaluation.ACCEPT)
		{
			// A complete and legal solution. Add it to solutions.
			solutions.add(grid);
		}
		else
		{
			// Here if eval == Evaluation.CONTINUE. Generate all 9 possible next grids. Recursively 
			// call solveRecurse() on those grids.
			ArrayList<Grid> sol = grid.next9Grids();
			for(Grid grid2 : sol)
			{
				Solver soln = new Solver(grid2);
				soln.solveRecurse(grid2);
				
			}
		}

	}
	
	//
	// COMPLETE THIS
	//
	// Returns Evaluation.ABANDON if the grid is illegal. 
	// Returns ACCEPT if the grid is legal and complete.
	// Returns CONTINUE if the grid is legal and incomplete.
	//
	public Evaluation evaluate(Grid grid)
	{
		if(grid.isLegal() && grid.isFull())
			return Evaluation.ACCEPT;
		 else if(!grid.isLegal() && grid.isFull())
			return Evaluation.ABANDON;
		 else
		    return Evaluation.CONTINUE;



	}

	
	public ArrayList<Grid> getSolutions()
	{
		return solutions;
	}
	
	
	public static void main(String[] args)
	{
		Grid g = TestGridSupplier.getPuzzle1();		// or any other puzzle
		Solver solver = new Solver(g);
		System.out.println("Will solve" + g);
		
		solver.solve();
		
		// Print out your solution, or test if it equals() the solution in TestGridSupplier.
		
		ArrayList<Grid> gr = solver.getSolutions();
		for(Grid ag : gr)
		{
			System.out.println(ag);
		}
	}
}
