package it214project;

import java.awt.Color;
//container class holds the methods for grouping the components together,laying out the components 
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mainclass {

   
     JFrame window;
     Container con;
     JPanel titleNamePanel;
     JLabel titleNameLabel;
     JPanel startButtonPanel;
     JButton startButton;
     //it can plain or bold or italic
     Font titleFont= new Font("Times New Roman",Font.PLAIN,40);
     Font normalFont= new Font("Times New Roman",Font.PLAIN,25);;
    
     TitleScreenHandler tsHandler = new TitleScreenHandler();
     
     
     
    public static void main(String[] args){
    // declearing the constructor
    new Mainclass();
      
    }
      // constructor
    public Mainclass(){
    window=new JFrame();
    window.setSize(800,600);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setBackground(Color.LIGHT_GRAY);
    window.setLayout(null);
    window.setVisible(true);
    con=window.getContentPane();
    
    
    titleNamePanel=new JPanel();
    titleNamePanel.setBounds(10,10,750,600);
    titleNamePanel.setBackground(Color.LIGHT_GRAY);
    titleNameLabel=new JLabel("Press the button to start the Game ");
    titleNameLabel.setForeground(Color.white);
    titleNameLabel.setFont(titleFont);
    
    startButtonPanel= new JPanel();
    startButtonPanel.setBounds(300,400,200,200);
    startButtonPanel.setBackground(Color.LIGHT_GRAY);
    startButtonPanel.setVisible(true);
    
    startButton=new JButton("START");
    startButton.setBackground(Color.LIGHT_GRAY);
    startButton.setForeground(Color.black);
    startButton.setFont(normalFont);
    startButton.setVisible(true);
    //when i click the start button 
    startButton.addActionListener(tsHandler);
       
            
    titleNamePanel.add(titleNameLabel);
    startButtonPanel.add(startButton);
         
         con.add(titleNamePanel);
         con.add( startButtonPanel);
       
    }
    
    public void gamescreen(){
      JFrame obj=new JFrame();
    
    GamePlay gameplay= new GamePlay();
   /*titleNamePanel.setVisible(false);
   startButtonPanel.setVisible(false);*/

//size of the background //x,y,width,height  
    obj.setBounds(10,10,700,600);
    obj.setTitle("BRICK BREAKER GAME");
    //the use cannot re-size the frame
    obj.setResizable(false);
    //makes the frame appear on screen
    obj.setVisible(true);
    //specify the exit button //exit the app
    obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //passing gameplay
    obj.add(gameplay);
    
    
    }
    
    
    
   
 public class TitleScreenHandler implements ActionListener{
     
        public void actionPerformed(ActionEvent event){
        
           gamescreen();
        }
    
    
    
    }
    
     
}
