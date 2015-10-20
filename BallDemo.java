import java.awt.Color;
import java.util.HashSet;
import java.util.Random;


/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Scott Taylor
 * @version 1.0 
 * @since 10/19/2015
 */

public class BallDemo   
{
    private Canvas myCanvas;
    final int OFFSET = 15;
    private HashSet<BoxBall> balls;
    Random rng;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }

    /**
     * Simulates balls bouncing around inside a box
     */
    
    public void boxBounce()
    {
        drawBox();
        populateBalls();
        while(true)
        {
            for(BoxBall ball : balls)
            {
                ball.move();
            }
            drawBox();
        }
    }
    
    /**
     * Draws the box around the canvas.
     */
    
    private void drawBox()
    {
        java.awt.Dimension canvasSize = myCanvas.getSize();
        myCanvas.setVisible(true);
        myCanvas.drawLine(OFFSET, OFFSET, canvasSize.width - OFFSET, OFFSET);
        myCanvas.drawLine(canvasSize.width - OFFSET, OFFSET, canvasSize.width - OFFSET, canvasSize.height - OFFSET);
        myCanvas.drawLine(canvasSize.width - OFFSET, canvasSize.height - OFFSET, OFFSET, canvasSize.height - OFFSET);
        myCanvas.drawLine(OFFSET, canvasSize.height - OFFSET, OFFSET, OFFSET);
    }
    
    /**
     * Populates the list of balls within the box
     */
    public void populateBalls()
    {
        rng = new Random();
        balls = new HashSet<BoxBall>();
        for(int i = 0 ; i < 500; i++)
        {balls.add(new BoxBall(rng.nextInt(OFFSET, myCanvas.getSize().width - OFFSET),
        rng.nextInt(myCanvas.getSize().height - OFFSET*10) + OFFSET*10, 10 + rng.nextInt(20),
        new Color(rng.nextInt(200),rng.nextInt(200),rng.nextInt(200)),myCanvas));}
    }
}
