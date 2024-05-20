//Author: John Ong
//Date May 20th 2024
//Description: The pupose of this class is to display the text seen on the windows such as, the score, the menu text and the game over screen. It is also in charge of keeping track of the score

package Guipart2.Pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

//This class deals with the score and all the displayed text
public class ScoreText {

    private int window_Width, window_Height;
    static int score, score2; //tracks the score for both paddles
    public static boolean gameOver;

    public ScoreText(int w, int h) {
        window_Width = w;
        window_Height = h;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        if (GamePanel.gameStarted == 1) {
            g.setFont(new Font("Roboto", Font.PLAIN, 13));
            g.drawString("Use W and S to control the Paddle to the left. ", window_Width / 2 - 120, window_Height + 100);
            g.drawString("Use Up Arrow and Down Arrow to control the Paddle to the right. ", window_Width / 2 + 220, window_Height + 100);
            g.setFont(new Font("Roboto", Font.PLAIN, 30));
            g.drawString("Press [SPACE] to Start ", window_Width / 2 + 80, window_Height + 250);
            g.setFont(new Font("Roboto", Font.PLAIN, 20));
            g.drawString("First to 6 Wins ", window_Width / 2 + 150, window_Height + 450);
        }
        g.drawLine(400, 0, 400, 800);
        g.setFont(new Font("Roboto", Font.BOLD, 30));
        g.drawString("" + score, window_Width / 2 + 150, window_Height + 20);
        g.drawString("" + score2, (window_Width / 2) + 230, window_Height + 20);
// This checks if either Player A or Player B has a score greater than 5
// If they do, it means that the game is over
        if (score > 5 || score2 > 5) {
            //displays text
            g.setFont(new Font("Roboto", Font.BOLD, 60));
            g.drawString("Game Over", window_Width / 2 + 30, window_Height + 200);
            g.setFont(new Font("Roboto", Font.PLAIN, 30));
            g.drawString("Press [SPACE] to Play Again", window_Width / 2 + 30, window_Height + 350);
            //freezes the ball
            Ball.setXDirection(0);
            Ball.setYDirection(0);
            
            gameOver = true;
        }
    }
}
