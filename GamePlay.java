package it214project;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//extends means inherits all properties of JPanel
//implements keyword means implements the interfaces in the class

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    
  
    //the game shouldn't play by itself
    private boolean play = false;
   //starting scores should be 0
    private int score = 0;
    
    private int totalbricks = 28;
    
    //timer class for settings the time of ball how it will be fast should move
    private Timer Timer;
    //speed to the timer that has to be end
    private int delay = 6;
    //starting position of the slider
    private int playerX = 310;
    //starting position of the ball
    private int ballposX = 120;
    private int ballposY = 350;
   //x-direction of the ball
    private int ballXdir = -1;
    private int ballYdir = -2;
    
    private MapGenerator map;
   //constructor
    public GamePlay() {
         //object of mapgenerator class (r,c)      
        map = new MapGenerator(4, 7);
      //notified whenever change the state of key   
        addKeyListener(this);
        //from Jpanel class it will not change anything if we set it true
        setFocusable(true);
        //decide whether or not focus traversal keys which's next or previous
        setFocusTraversalKeysEnabled(false);
       //create an object of the timer & the speed of it in the delay var
        Timer = new Timer(delay, this);//timer small
        //start the timer causing it to start sending action events to its listener
        Timer.start();
      
      
    }
    //recives graphics object
     public void paint(Graphics g) {
         //background
         g.setColor(Color.pink);
         //start position,end position,width,height
        g.fillRect(1, 1, 692, 592);
        //drawing or generating a map
        map.draw((Graphics2D) g);
        //border
        g.setColor(Color.cyan);
        //start position,end position,width,heigh
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);
     
       //the paddle or pannel or slider
        g.setColor(Color.cyan);
        g.fillRect(playerX, 550, 100, 8);

        //ball
        g.setColor(Color.gray);
        g.fillOval(ballposX, ballposY, 20, 20);
     
        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("    Game Over Score: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("   Press Enter to Restart", 230, 350);
        }
        //if we finish our game and we won
        if(totalbricks == 0){
            play = false;
            ballYdir = -2;
            ballXdir = -1;
            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    you won: "+score,190,300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("   Press Enter to Restart", 230, 350);


        }
     //causes the JFrame window to be destroyed and cleaned up by OP
        g.dispose();

     }
    //comes from actionlistener
    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();

        //to detecting the intersection or touching between the ball and the paddle
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }
//level a in
            A:
            for (int i = 0; i < map.map.length; i++) {
                   // the 1st map is the object we have created in this class to access the map array in other class
                    //j less than the length of row it will return no of columns
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.bricksWidth + 80;
                        int brickY = i * map.bricksHeight + 50;
                        int bricksWidth = map.bricksWidth;
                        int bricksHeight = map.bricksHeight;
                         //to create rectangle between bricks
                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        // create rectangle around the ball in order to detecet the interscetion
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickrect = rect;
                       //to check if its intersects or not so if the ball intersects brick we going to call the function
                       // and change it's value to 0
                        if (ballrect.intersects(brickrect)) {
                            map.setBricksValue(0, i, j);
                            totalbricks--;
                            score += 5;
                            if (ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth) {
                                // to move the ball to opsite direction                              
                                ballXdir = -ballXdir;
                            } else {//to move the ball towards right or bottom or top
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }


                }
            }
 
            //to control the position of the ball

            ballposX += ballXdir;
            ballposY += ballYdir;
           //left border of the ball
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            //top border of the ball
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            //right border
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }
        }
        //this method recall the paint method and draw the paddle again when I press the arrow key left and right
        repaint();
        
    
    }

    @Override
    public void keyTyped(KeyEvent e) {

       }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
   //this code for enter key if we press it will redrawn the game again
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) { // if the game over
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalbricks = 28;
                //to regenerate the map
                map = new MapGenerator(4, 7);

                repaint();
            }
        }

        }

        public void moveRight ()
        {
            
            play = true;
            //if it press to right than it should move 20px to right
            playerX += 20;
        }
        public void moveLeft ()
        {
            play = true;
             //if it press to left than it should move 20px to left
            playerX -= 20;
        }

   
  
    
}
