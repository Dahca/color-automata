package com.dahca.colorautomata.simulations;

import javax.swing.*;

import com.dahca.colorautomata.cells.CellState;
import com.dahca.colorautomata.cells.ColorCell2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Display extends JFrame
{
	private Pane p;
	class Pane extends Canvas implements Runnable, KeyListener, MouseListener
	{
		//A comment!
		//Another comment for testing
		private Conway c;
		private Image im;
		private Graphics2D gr;
		private boolean init = false;
		private boolean paused = true;

		private int[][] glidergun = {
				{24},
				{22,24},
				{12,13,20,21,34,35},
				{11,15,20,21,34,35},
				{0,1,10,16,20,21},
				{0,1,10,14,16,17,22,24},
				{10,16,24},
				{11,15},
				{12,13}
		};
		private int[][] r_peppermint = {
				{1,2},
				{2,3},
				{2}
		};
		private int[][] lightweight = {
				{2,5},
				{1},
				{1,5},
				{1,2,3,4}
		};
		private int[][] mediumweight = {
				{4},
				{2,6},
				{1},
				{1,6},
				{1,2,3,4,5}
		};
		private int[][] heavyweight = {
				{4,5},
				{2,7},
				{1},
				{1,7},
				{1,2,3,4,5,6}
		};
		private int[][] gunship = {
				{8,9,11},
				{5,6,8,10,11,13,14,15},
				{2,3,4,5,8,9,16},
				{1,6,10,14,15},
				{2,3}
		};
		private int[][] loaf = {
				{2,3,6,8,9},
				{1,4,7,8},
				{2,4},
				{3},
				{9},
				{7,8,9},
				{6},
				{7},
				{8,9}
		};
		public Pane(int x, int y)
		{
			//make initial board
			int[][][] arr = {lightweight,loaf,heavyweight,mediumweight};
			int y_offset = 3;
			int x_offset = 5;
			c = new Conway(x,y,12);
			for( int[][] arrsub : arr)
			{
				for( int i = 0; i < arrsub.length; i++)
				{
					for( int j = 0; j < arrsub[i].length; j++)
					{
						c.setCell(CellState.ALIVE, arrsub[i][j]+x_offset, i+y_offset);
					}
				}
				y_offset+=12;
				x_offset+=5;
			}
		}
		public void nextStep()
		{
			paint(getGraphics());
			c.step();
		}
		public void paint( Graphics g )
		{
			if( !init )
				init();
			gr.setColor(Color.BLACK);
			gr.fillRect(0,0,im.getWidth(this),im.getHeight(this));
			ColorCell2D[][] arr = c.getCells();
			for( ColorCell2D[] arr2 : arr )
			{
				for( ColorCell2D cell : arr2 )
				{
					gr.setColor(cell.getColor());
					gr.fill(cell.getRectangle());
					gr.setColor(Color.GRAY);
					gr.draw(cell.getRectangle());
				}
			}
			g.drawImage(im, 0, 0, this);
		}
		public void init()
		{
			this.addMouseListener(this);
			this.addKeyListener(this);
			init = true;
			im = createImage(this.getWidth(), this.getHeight());
			gr = (Graphics2D)im.getGraphics();
		}
		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			while(true)
			{
				try
				{
					Thread.sleep(100);
					if(!paused)
						nextStep();
					if(paused)
						repaint();
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		@Override
		public void mouseClicked(MouseEvent e)
		{
			// TODO Auto-generated method stub
			int x = (int) ((int)e.getX()/c.getCells()[0][0].getRectangle().getHeight());
			int y = (int) ((int)e.getY()/c.getCells()[0][0].getRectangle().getHeight());
			CellState cs = CellState.ALIVE;
			if( c.getCells()[x][y].getState() == CellState.ALIVE)
				cs = CellState.DEAD;
			c.setCell(cs, x, y);
			this.repaint();
		}
		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}
		@Override
		public void keyTyped(KeyEvent e)
		{
			// TODO Auto-generated method stub

		}
		@Override
		public void keyPressed(KeyEvent e)
		{
			// TODO Auto-generated method stub
			if(e.getKeyChar() == ' ')
				paused = !paused;
			//one step
			if(e.getKeyChar() == '.')
				this.nextStep();
		}
		@Override
		public void keyReleased(KeyEvent e)
		{
			// TODO Auto-generated method stub

		}
	}
	public Display(int x, int y)
	{
		p = new Pane(x,y);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(x,y);
		this.setResizable(false);
		this.add(p);
		this.setVisible(true);
	}
	public Pane getPane()
	{
		return p;
	}

	//main
	public static void main(String[] args)
	{
		Display display = new Display(1000,600);
		//set some initial conditions
		display.getPane().run();
	}
}
