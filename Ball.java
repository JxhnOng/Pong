//Author: John Ong
//Date May 20th 2024
//Description: This class controls the ball's "physics" (there are no actual physics involved). As well as constructs the ball.


package Guipart2.Pong;

import java.awt.*;

public class Ball extends Rectangle {

  public static int yVelocity;
  public static int xVelocity;
  public static final int SPEED = 3; // movement speed of ball
  public static final int BALL_DIAMETER = 20; // size of ball

  // constructor creates ball at given location with given dimensions
  public Ball(int x, int y) {
    super(x, y, BALL_DIAMETER, BALL_DIAMETER);
    setXDirection(0);
    setYDirection(0);
  }

  // called whenever the movement of the ball changes in the y-direction (up/down)
  public static void setYDirection(int yDirection) {
    yVelocity = yDirection;
  }

  // called whenever the movement of the ball changes in the x-direction (left/right)
  public static void setXDirection(int xDirection) {
    xVelocity = xDirection;
  }

  // called frequently from both PlayerBall class and GamePanel class updates the current location of the ball
  public void move() {
    y = y + yVelocity;
    x = x + xVelocity;
  }

  // called frequently from the GamePanel class draws the current location of the ball to the screen
  public void draw(Graphics g) {
    g.setColor(Color.black);
    g.fillOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
  }

}