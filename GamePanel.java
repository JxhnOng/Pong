//Author: John Ong
//Date May 20th 2024
//Description: This is a pong game using java swing and awt.
//The purpose of this class is to connect all the other classes to eachother as well as being responsible for putting objects onto the window.


package Guipart2.Pong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {
  public static final int GAME_WIDTH = 800;
  public static final int GAME_HEIGHT = 800;
  public static int gameStarted = 1;

  public Thread gameThread;
  public Image image;
  public Graphics graphics;
  public Paddle paddle;
  public Paddle2 paddle2;
  public Ball ball;
  public ScoreText score;
  public Sfx sound;

  //constructor
  public GamePanel() {
    this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    paddle = new Paddle(GAME_WIDTH / 2, GAME_HEIGHT / 2);
    paddle2 = new Paddle2(GAME_WIDTH / 2, GAME_HEIGHT / 2);
    ball = new Ball(GAME_WIDTH / 2, GAME_HEIGHT / 2);
    score = new ScoreText(GAME_WIDTH / 2, 20);

    this.setFocusable(true);
    this.addKeyListener(this);
    gameThread = new Thread(this);
    gameThread.start();
  }

  public void paint(Graphics g) {
    // we are using "double buffering here" - if we draw images directly onto the
    // screen, it takes time and the human eye can actually notice flashes of lag as
    // each pixel on the screen is drawn one at a time. Instead, we are going to
    // draw images OFF the screen, then simply move the image on screen as needed.
    image = createImage(GAME_WIDTH, GAME_HEIGHT); // draw off screen
    graphics = image.getGraphics();
    draw(graphics);// update the positions of everything on the screen
    g.drawImage(image, 0, 0, this); // move the image on the screen

  }

  public void draw(Graphics g) {
    paddle.draw(g);
    paddle2.draw(g);
    ball.draw(g);
    score.draw(g);
  }

  public void move() {
    paddle.move();
    paddle2.move();
    ball.move();
  }

  // if a key is pressed, we'll send it over to the other classes for processing
  public void keyPressed(KeyEvent e) {
    paddle.keyPressed(e);
    paddle2.keyPressed(e);
    if (ScoreText.score > 1 || ScoreText.score2 > 1 || ScoreText.score == 0 && ScoreText.score2 == 0) {
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {

        Ball.setXDirection(1 * Ball.SPEED);
        Ball.setYDirection(0);
        gameStarted = 0;
        Sfx.start(4);
      }
      if (e.getKeyCode() == KeyEvent.VK_SPACE && ScoreText.gameOver) {
        ScoreText.score = 0;
        ScoreText.score2 = 0;

      }
    }
  }

  // if a key is released, we'll send it over to the other classes for processing
  public void keyReleased(KeyEvent e) {
    paddle.keyReleased(e);
    paddle2.keyReleased(e);
  }

  // left empty because we don't need it; must be here because it is required to be overridded by the KeyListener interface
  public void keyTyped(KeyEvent e) {
  }

  //check if objects collide
  public void checkCollision() {

    // force player paddles to remain on screen
    if (paddle.y <= 0) {
      paddle.y = 0;
    }
    if (paddle.y >= GAME_HEIGHT - Paddle.HEIGHT) {
      paddle.y = GAME_HEIGHT - Paddle.HEIGHT;
    }

    if (paddle2.y <= 0) {
      paddle2.y = 0;
    }
    if (paddle2.y >= GAME_HEIGHT - Paddle2.HEIGHT) {
      paddle2.y = GAME_HEIGHT - Paddle2.HEIGHT;
    }
    // forces ball to stay on screen as well as calculates collision with the pedal
    if (ball.y <= 0) { // ball ceiling
      ball.y = 0;
      Sfx.start(2);
      Ball.setYDirection(Ball.SPEED * 1);
    }
    if (ball.y >= GAME_HEIGHT - Ball.BALL_DIAMETER) { // ball floor
      ball.y = GAME_HEIGHT - Ball.BALL_DIAMETER;
      Sfx.start(2);
      Ball.setYDirection(Ball.SPEED * -1);
    }

    if (ball.x <= 0) { // ball left side
      pause();
      ScoreText.score2++;
    }
    if (ball.x + Ball.BALL_DIAMETER >= GAME_WIDTH) { // ball right
      pause2();
      ScoreText.score++;
    }

    // randomizes the angle of the ball after it hits the left paddle
    if (ball.intersects(paddle)) {
      Sfx.start(2);
      Ball.setXDirection(Ball.SPEED * 1);
      Ball.setYDirection(Ball.SPEED * (int) (Math.random() * (3 - (-3) + 1) + (-3))); 
    }
    // randomizes the angle of the ball after it hits the right paddle
    if (ball.intersects(paddle2)) {
      Sfx.start(2); // right paddle
      Ball.setXDirection(Ball.SPEED * -1);
      Ball.setYDirection(Ball.SPEED * (int) (Math.random() * (3 - (-3) + 1) + (-3)));
    }
  }
//After the ball hits the paddle's side it resets it to the middle then makes it move
  public void pause() {
    Sfx.start(1);
    ball.x = GAME_HEIGHT / 2;
    ball.y = GAME_WIDTH / 2;
    Ball.setXDirection(Ball.SPEED + 1);
    Ball.setYDirection(Ball.SPEED * 0);

  }
//After the ball hits the paddle's side it resets it to the middle then makes it move
  public void pause2() {
    Sfx.start(1);
    ball.x = GAME_HEIGHT / 2;
    ball.y = GAME_WIDTH / 2;
    Ball.setXDirection(Ball.SPEED * -1);
    Ball.setYDirection(Ball.SPEED * 0);
  }

  public void run() {
    // the CPU runs our game code too quickly - we need to slow it down! The
    // following lines of code "force" the computer to get stuck in a loop for short
    // intervals between calling other methods to update the screen.
    long lastTime = System.nanoTime();
    double amountOfTicks = 60;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long now;

    while (true) { // this is the infinite game loop
      now = System.nanoTime();
      delta = delta + (now - lastTime) / ns;
      lastTime = now;

      // only move objects around and update screen if enough time has passed
      if (delta >= 1) {
        move();
        checkCollision();
        repaint();
        delta--;
      }
    }
  }

}
