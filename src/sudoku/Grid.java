package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;


	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}


	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. Youâ€™ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}


	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Donâ€™t change
	// â€œthisâ€� grid. Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell=0;
		int yOfNextEmptyCell=0;
       boolean found = false;
		// Find x,y of an empty cell.
		for(int i =0 ;i<9 ; i++)
		{
			for(int j =0 ;j<9 ; j++)
			{
				if(values[i][j] == 0)
				{
					xOfNextEmptyCell = i;
					yOfNextEmptyCell = j;
					found = true;
					break;
				}
				if(found)
					break;
			}
		}
		// Construct array list to contain 9 new grids.
		ArrayList<Grid> grids = new ArrayList<Grid>();
		for(int i =0 ; i<9; i++)
		{
			Grid gr = new Grid(this);
			this.values[xOfNextEmptyCell][yOfNextEmptyCell] = i;
			grids.add(gr);
		}
		// Create 9 new grids as described in the comments above. Add them to grids.

		return grids;

	}


	//
	// COMPLETE THIS
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean isLegal()
	{
		boolean flag = true;
		final int ROW_SUM = 45;
		// Check every row. If you find an illegal row, return false.
		int sumRow = 0;
		for(int i=0;i<9;i++)
		{
			sumRow = 0;
			for(int j = 0;j<9 ;j++)
			{
				int elem =  values[i][j];
				sumRow = sumRow + elem;
				if (elem == 0)
					flag = true;
			}
			if(!flag && sumRow!=ROW_SUM)
			{
				return false;
			}
		}
		// Check every column. If you find an illegal column, return false.
		boolean flag1 = true;
		int colRow = 0;
		for(int i=0;i<9;i++)
		{
			colRow = 0;
			for(int j = 0;j<9 ;j++)
			{
			  int 	elem = values[j][i];
				colRow = colRow + elem;
				if(elem == 0)
					flag1 = true;
				
			}
			if(!flag && colRow!=ROW_SUM)
			{
				return false;
			}
		}
		// Check every block. If you find an illegal block, return false.
		boolean flag2 = false;
		int sum  = 0;
		for(int a=3;a<10;a=a+3)
		{

			for(int i=0;i<a;i++)
			{
				for(int b=3;b<10;b=b+3)
				{
					for(int j=0;j<b;j++)
					{
						int elem = values[i][j];
						sum = sum + elem;
						if(elem == 0)
							flag2 = true;
							
					}
					if (!flag2 && sum != ROW_SUM)
						return false;
				}
			}

		}
		// All rows/cols/blocks are legal.
		return true;



	}


	//
	// COMPLETE THIS
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{

      for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(values[i][j]==0)
					return false;
			}
		}
		return true;


	}


	//
	// COMPLETE THIS
	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{

		Grid that = (Grid) x;
		return this.values.equals(that.values);


	}
}
