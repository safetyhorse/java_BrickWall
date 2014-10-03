import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BrickWall {

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new BrickFrame();
				frame.setTitle("Bricks Are Rocks!");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});

	}

}

class BrickFrame extends JFrame
{
	public BrickFrame()
	{
		add(new BrickPanel());
		pack();
	}
}


class BrickPanel extends JPanel
{
	//final double BRICKS_PER_ROW = 5;
	//final double NUM_OF_ROWS = 50;
	final int brickWidth = 36;
	final int brickHeight = 13;
	
	Random generator = new Random(System.currentTimeMillis());
	
	public BrickPanel()
	{
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		Brick.setBrickCount(0);
		
		g2D.setColor(Color.darkGray);
		g2D.fillRect(0, 0, getWidth(), getHeight());
		
		int heightOfScreen = getHeight();
		int numOfRows = heightOfScreen / brickHeight;
		
		int widthOfScreen = getWidth();
		int numOfCols = widthOfScreen / brickWidth;
		
		int red = 0, blue = 0, green = 0;
		
		for (int row = 0; row < numOfRows; row++)
		{
			if(row % 2 == 0)
			{
				for (int col = 0; col < numOfCols; col ++)
				{
					red = generator.nextInt(60)+185;
					//blue = generator.nextInt(256);
					//green = generator.nextInt(256);
					Color c = new Color(red,blue,green);
					
					Brick brick = new Brick(
							col*brickWidth,
							row*brickHeight,
							brickWidth,
							brickHeight);
					g2D.setColor(c);
					g2D.setStroke(new BasicStroke(brick.getBorderWidth()));
					g2D.fill(brick);
					g2D.setColor(brick.getBorderColor());
					g2D.draw(brick);
				}
				
			}
			else
			{
				for (int col = 0; col < numOfCols-1; col ++)
				{
					red = generator.nextInt(60)+185;
					//blue = generator.nextInt(256);
					//green = generator.nextInt(256);
					Color c = new Color(red,blue,green);
					
					Brick brick = new Brick(
							col*brickWidth+(brickWidth/2),
							row*brickHeight,
							brickWidth,
							brickHeight);
					g2D.setColor(c);
					g2D.setStroke(new BasicStroke(brick.getBorderWidth()));
					g2D.fill(brick);
					g2D.setColor(brick.getBorderColor());
					g2D.draw(brick);
				}
				
			}
			
		}
		
		
		//display number of bricks
		g2D.setColor(Color.black);
		g2D.setFont(new Font("Stencil",Font.BOLD,36));
		g2D.drawString("There are " + Brick.getBrickCount(), 50, 80);
		
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(600,400);
	}
}

class Brick extends Rectangle2D.Double
{
	private Color brickColor;
	private Color borderColor;
	private float borderWidth;
	private static int brickCount = 1;
	
	Brick(double x, double y, double width, double height)
	{
		super(x,y,width,height);
		setBrickColor(Color.red);
		setBorderColor(Color.gray);
		setBorderWidth(2.0f);
		setBrickCount(getBrickCount() + 1);
	}

	public Color getBrickColor() {
		return brickColor;
	}

	public void setBrickColor(Color brickColor) {
		this.brickColor = brickColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public float getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}

	public static int getBrickCount() {
		return brickCount;
	}

	public static void setBrickCount(int brickCount) {
		Brick.brickCount = brickCount;
	}
}
