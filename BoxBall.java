import java.awt.*;
import java.util.HashSet;
import java.util.ArrayList;



/**
 * Write a description of class BoxBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoxBall
{
    // instance variables - replace the example below with your own
    
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private int xSpeed;
    private int ySpeed;
    private Canvas canvas;

    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        canvas = drawingCanvas;
    }
    
     /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        erase();
        yPosition += ySpeed;
        xPosition += xSpeed;
        checkCollisions();
        draw();
    }
    
    /**
     * method for checking for collisions
     */
    private void checkCollisions()
    {
        ArrayList<BoxBall> collisions;
    }
        

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
