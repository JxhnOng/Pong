//Author: John Ong
//Date May 20th 2024
//Description: The pupose of this class is to accept control to control the paddle on the right. As well as constructing it.

import java.awt.*;
import java.awt.event.*;

public class Paddle2 extends Rectangle {
  public final int SPEED = 5; //Paddle speed
  public static final int HEIGHT = 100; //Paddle Height
  public static final int WIDTH = 10; //Paddle width
  public int yVelocity;

  public Paddle2(int x, int y) {
    super(GamePanel.GAME_WIDTH - 20, y, WIDTH, HEIGHT);
  }

  // updates the direction of the paddle based on user input
  // if the keyboard input isn't any of the options (w,s), then nothing
  // happens
  public void keyPressed(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_UP) {
      setYDirection(SPEED * -1);
      move();
    }

    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      setYDirection(SPEED);
      move();
    }
  }

  // called from GamePanel when any key is released (no longer being pressed down)
  // Makes the paddle stop moving in that direction
  public void keyReleased(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_UP) {
      setYDirection(0);
      move();
    }

    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      setYDirection(0);
      move();
    }
  }

 // called whenever the movement of the paddle changes in the y-direction (up/down)
  public void setYDirection(int yDirection) {
    yVelocity = yDirection;
  }

  // updates the current location of the paddle
  public void move() {
    y = y + yVelocity;
  }

  // draws the current location of the paddle to the screen
  public void draw(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(x, y, WIDTH, HEIGHT);
  }
}
