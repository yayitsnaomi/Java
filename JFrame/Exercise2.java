package hw1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JComponent;

@SuppressWarnings("serial")

public class Exercise2 {

	public static void draw(Graphics g)
	{  
		int valid = 1;
		while(valid != 0) {
			try {
				Scanner in = new Scanner(System.in);
				System.out.println("Enter Grid Dimension:");
				int GridDimension = 0;
				GridDimension = in.nextInt();
				while(GridDimension % 2 != 0 | GridDimension < 1) {
					System.out.println("Please enter an even number >= 2!");
					GridDimension = in.nextInt();
				}
				valid = 0;
				in.close();
				
				System.out.println("Thank you! Please enjoy your visualization! :) ");
				
				for (int row = 0;row<GridDimension;row++)
				{
					for (int column = 0;column<GridDimension/2;column++)
					{
						g.setColor(Color.GREEN);
						g.fillOval(row*60 + 50,column*60 + 50, 50,50);	
					}
					for (int column = GridDimension/2;column<GridDimension;column++)
					{
						g.setColor(Color.BLACK);
						g.fillOval(row*60 + 50,column*60 + 50, 50,50);	
					}
				}
				for (int row = GridDimension/2;row<GridDimension;row++)
				{
					for (int column = 0;column<GridDimension/2;column++)
					{
						g.setColor(Color.BLACK);
						g.fillOval(row*60 + 50,column*60 + 50, 50,50);	
					}
					for (int column = GridDimension/2;column<GridDimension;column++)
					{
						g.setColor(Color.RED);
						g.fillOval(row*60 + 50,column*60 + 50, 50,50);	
					}
				}
			}
			catch (Exception e) {
				System.out.println("Please select a valid number for the grid size");
			}
		}
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		final int FRAME_WIDTH = 800;
		final int FRAME_HEIGHT = 800;

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent component = new JComponent()
		{
			public void paintComponent(Graphics graph)
			{
				draw(graph);
			}
		};     
		frame.add(component);
		frame.setVisible(true);
	}   

}
