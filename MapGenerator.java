package it214project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
    //2D array that contain all the bricks
    public int map[][];
    public int bricksWidth;
    public int bricksHeight;
    //constructor of mapGenerator that recieves rows and columns for a particular number of bricks
    public MapGenerator(int row , int col){
        map = new int[row][col];
        //rows 
        for (int[] map1 : map) {
            //columns
             for (int j = 0; j < map[0].length; j++) {
                map1[j] = 1;//1 will detect that the particular brick have not touching the ball
             }
         }
        bricksWidth = 540/col;
        bricksHeight = 180/row;
    }
    //function to draw the bricks
    public void draw(Graphics2D g) {
        //
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    // to draw the map
                    g.setColor(Color.white);
                    g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                }
            }

        }
    }
     
    //this function to let the ball intersects with the bricks
    public void setBricksValue(int value,int row,int col)
    { 
        map[row][col] = value;

    }
    
   
}
