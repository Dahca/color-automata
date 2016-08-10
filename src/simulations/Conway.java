package simulations;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import cells.*;
import fields.*;
import rules.*;

public class Conway extends WrapAroundField2D<ColorCell2D> implements B3_S23<ColorCell2D>
{
	protected Random rand = new Random();
	public Conway(int w, int h, int box)
	{
		width = w;
		height = h;
		box_size = box;
		initBoard();
	}
	public void initBoard()
	{
		board = new ColorCell2D[width/box_size][height/box_size];
		for( int i = 0; i < board.length; i++)
		{
			for ( int j = 0; j < board[0].length; j++)
			{
				board[i][j] = new ColorCell2D();
				board[i][j].setRectangle(new Rectangle(i*box_size,j*box_size,box_size,box_size));
			}
		}
	}
	public void setCell(CellState state, int x, int y)
	{
		if( state == CellState.DEAD)
			setCell(state,x,y,Color.BLACK);
		else
			//setCell(state,x,y,new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)));
			setCell(state,x,y,Color.WHITE);
	}
	public void setCell(CellState state, int x, int y, Color c)
	{
		if( board == null )
			initBoard();
		board[x][y].setState(state);
		board[x][y].setColor(c);
	}
	@Override
	public ColorCell2D[][] step()
	{
		ColorCell2D[][] new_field = new ColorCell2D[board.length][board[0].length];
		for( int i = 0; i < width; i += box_size)
		{
			for ( int j = 0; j < height; j += box_size)
			{
				int x = (i/box_size);
				int y = (j/box_size);
				if( x >= new_field.length || y >=new_field[0].length)
					continue;
				new_field[x][y] = new ColorCell2D();
				new_field[x][y].setRectangle(new Rectangle(i,j,box_size,box_size));
			}
		}
		for( int x = 0; x < board.length; x++)
		{
			for( int y = 0; y < board[0].length; y++)
			{
				//if(board[x][y].getState() == CellState.ALIVE)
					//System.out.println(outcome(board,x,y));
				new_field[x][y].setState(outcome(board,x,y));
				new_field[x][y].setColor(colorOutcome(board,x,y,new_field[x][y].getState()));
			}
		}
		board = new_field;
		return board;
	}

	@Override
	public CellState outcome(ColorCell2D[][] field, int x, int y)
	{
		Point[] arr = getAdjacent(x,y);
		int alive = 0;
		for(Point p : arr)
		{
			ColorCell2D cell = field[p.x][p.y];
			switch(cell.getState())
			{
				case ALIVE:
					alive++;
				case DEAD:	
				default:	
			}
		}
		ColorCell2D current = field[x][y];
		if (current.getState() == CellState.ALIVE )
		{
			for( int i : LIVE )
			{
				//System.out.println("alive " + alive + " coords "+x+" "+y);
				//System.out.println(i);
				if (alive == i)
					return CellState.ALIVE;
			}
			return CellState.DEAD;
		}
		else if(current.getState() == CellState.DEAD )
		{
			for( int i : BORN )
			{
				if (alive == i)
					return CellState.ALIVE;
			}
			return CellState.DEAD;
		}
		return CellState.DEAD;
	}
	/**
	 * Use the new field
	 * @param field
	 * @param x
	 * @param y
	 * @return
	 */
	public Color colorOutcome(ColorCell2D[][] field, int x, int y, CellState next_state)
	{
		ColorCell2D cell = field[x][y];
		if(cell.getState() == CellState.ALIVE && next_state == CellState.ALIVE)
			return cell.getColor();
			//return Color.BLUE;
		//else, mutate
		if(next_state == CellState.DEAD)
		{
			return Color.BLACK;
		}
		//then new color is a mix of contributing cells colors
		if(cell.getState() == CellState.DEAD && next_state == CellState.ALIVE)
		{
			int r=0,g=0,b=0,rg=0,rb=0,gb=0;
			int bright=0, dark=0;
			Point[] arr = getAdjacent(x,y);
			int threshold = (int)Math.ceil((double)arr.length/2.0);
			for (Point p : arr)
			{
				if (board[x][y].getState() == CellState.ALIVE)
				{
					Color c = board[p.x][p.y].getColor();
					int red = c.getRed();
					int green = c.getGreen();
					int blue = c.getBlue();
					int avg = (red + green + blue)/3;
					//logic
					if( red > green && red > blue )
						r++;
					if( green > red && green > blue )
						g++;
					if( blue > red && blue > green )
						b++;
					if( red > blue && green > blue )
						rg++;
					if( red > green && blue > green )
						rb++;
					if( green > red && blue > red )
						gb++;
					if( avg <= 255/3 )
						dark++;
					if( avg >= 2*255/3 )
						bright++;
				}
			}
			//use these conditions to generate the colors
			int red=0, green=0, blue=0;
			int increment = (int) Math.ceil(255.0/3.0);
			int low,high;
			if(dark >= threshold)
			{
				low = 0;
				high = increment;
			}
			else if (bright >= threshold)
			{
				low = increment*2;
				high = 255;
			}
			else
			{
				low = increment;
				high = increment*2;
			}
			low = 0;
			high = 255;
			while(true)
			{
				red = low + rand.nextInt(high-low);
				green = low + rand.nextInt(high-low);
				blue = low + rand.nextInt(high-low);
				if( r >= threshold )
				{
					if( red <= green || red <= blue )
						continue;
				}
				if( g >= threshold )
				{
					if( green <= red || green <= blue )
						continue;
				}
				if( b >= threshold )
				{
					if( blue <= green || blue <= red )
						continue;
				}
				if( rg >= threshold )
				{
					if( red <= blue || green <= blue )
						continue;
				}
				if( rb >= threshold )
				{
					if( red <= green || blue <= green )
						continue;
				}
				if( gb >= threshold )
				{
					if( red >= green || red >= blue )
						continue;
				}
				break;
			}
			return ColorCell2D.genColor(red,green,blue);
		}
		return Color.BLACK;
	}
	
}
