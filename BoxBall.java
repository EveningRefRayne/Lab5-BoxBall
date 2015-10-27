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
    private HashSet<BoxBall> balls;

    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int xSpeed, int ySpeed, int ballDiameter, Color ballColor, Canvas drawingCanvas, HashSet<BoxBall> balls)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        canvas = drawingCanvas;
        this.balls = balls;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
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
        findCollisions();
        draw();
    }
    
    /**
     * method for checking for collisions
     */
    private void findCollisions()
    {
        ArrayList<BoxBall> collisions = getBallCollisions();
        if(xPosition + (diameter/2) >= (canvas.getSize().width -30) || xPosition + diameter/2 <= 30)
        {
            xSpeed = -xSpeed;
        }
        if(yPosition + diameter/2 >= (canvas.getSize().height-30) || yPosition + diameter/2 <= 30)
        {
            ySpeed = -ySpeed;
        }
        for (BoxBall collision : collisions)
        {
            int x1 = xPosition;
            int y1 = yPosition;
            int x2 = collision.getXPos();
            int y2 = collision.getYPos();
            //the code for realistic collisions goes here
            //First convert to normalized vectors by sqRoot(x^2,y^2)
            //then do dot product as per: http://www.euclideanspace.com/maths/algebra/vectors/angleBetween/
            // to find the angle between its direction and the angle between the circles
            //then modify the xSpeed and ySpeed variables according to the new direction
        }
    }
        

    /**
     * return the horizontal position of this ball
     */
    public int getXPos()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPos()
    {
        return yPosition;
    }
    
    /**
     * returns the radius
     */
    public int getDiameter()
    {
        return diameter;
    }
    
    /**
     * Returns any collisions with other balls
     */
    public ArrayList<BoxBall> getBallCollisions()
    {
        ArrayList<BoxBall> nearbyList = new ArrayList<BoxBall>();
        for (BoxBall check : balls)
        {
            if (check == this)
            {
                //it's the same object
            }
            else
            {
                
                if(collisionMath(check))
                {
                    nearbyList.add(check);
                }
            }
        }
        return nearbyList;
    }
    
    /*
     * Does the collision calculations
     */
    private boolean collisionMath(BoxBall check)
    {
        int x1 = xPosition;
        int y1 = yPosition;
        int x2 = check.getXPos();
        int y2 = check.getYPos();
        double rad1 = diameter / 2.0;
        double rad2 = check.getDiameter() / 2.0;
        double distance = Math.sqrt(Math.abs((x1-x2)*(x1-x2)) + Math.abs((y1-y2)*(y1-y2)));
        double closestPoint = distance - (rad1+rad2);
        if (closestPoint <=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
}
