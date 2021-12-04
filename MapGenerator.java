package brick.breaker.game;



    import java.awt.BasicStroke;//class for outlines of graphics primitives
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author chinm
 */
public class MapGenerator {
     public int map[][];//contain all bricks
    public int bricksWidth;
    public int bricksHeight;
    //argument in  class gameplay
    //asign 1 to every array element
    public MapGenerator(int row , int col){
        map = new int[row][col];
         for (int[] map1 : map) {
             for (int j = 0; j < map[0].length; j++) {
                 map1[j] = 1;
             }
         }
        bricksWidth = 640/col;
        bricksHeight = 230/row;
    }
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
               if (map[i][j] > 0) {//brick exist 
                    g.setColor(new Color(255,204,229));
                    g.fillRect(j * bricksWidth + 187, i * bricksHeight + 70, bricksWidth, bricksHeight);//fill the shape with color

                    g.setStroke(new BasicStroke(3));//border of bricks
                    g.setColor(Color.white);
                    g.drawRect(j * bricksWidth + 187, i * bricksHeight + 70, bricksWidth, bricksHeight);//just draw structure

                }
            }

        }
    }
    public void setBricksValue(int value,int row,int col)//give a value of brick either 0 or 1 either hit ot not
    {
        map[row][col] = value;

    }
}
