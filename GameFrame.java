//Author: John Ong
//Date May 20th 2024
//Description: The pupose of this class is to set up the window
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

  GamePanel panel;

  public GameFrame(){
    panel = new GamePanel(); //run GamePanel constructor
    this.add(panel);
    this.setTitle("Pong"); //set title for frame
    this.setResizable(false); //frame can't change size
    this.setBackground(Color.white);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X button will stop program execution
    this.pack();//makes components fit in window - don't need to set JFrame size, as it will adjust accordingly
    this.setVisible(true); //makes window visible to user
    this.setLocationRelativeTo(null);//set window in middle of screen
  }
  
}