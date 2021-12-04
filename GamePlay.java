package brick.breaker.game;

/*
Class Rectangle. A Rectangle specifies an area in a coordinate space that is enclosed by the Rectangle object's
upper-left point (x,y) in the coordinate space, its width, and its height. ... The isEmpty() method will return true for such a Rectangle . */
import javax.swing.JPanel;//container store component for good design
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//interacte with mouse 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;//interacte with keyboard
import java.awt.BasicStroke;
public class GamePlay extends JPanel implements KeyListener, ActionListener {
    
     private boolean play = false;
    private int score = 0;//start score
    private int totalbricks = 21;
    private Timer Timer;//for how fast ball move
    private int delay = 8;//speed that give to Timer
    private int playerX = 420;//start position of slider
    private int ballposX = 120;//start position of a ball in x-aixs
    private int ballposY = 350;//start position of a ball in y-aixs
    private int ballXdir = -2;
    private int ballYdir = -3;
    private MapGenerator map;
    
//constractur
    public GamePlay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);// focus in spasific component in panel
        setFocusTraversalKeysEnabled(false);//the component with the keyboard focus is generally prominent 
        Timer = new Timer(delay, this);//timer small
        Timer.start();//to start move
    }
    //function for set color to enviroment, and for draw difirent sgape like ball
     public void paint(Graphics g) {
         g.setColor(new Color(204,255,255)); //panel backgraound
        g.fillRect(1, 1, 1000,800); //fill rectingle for panel
         //to draw bricks
        map.draw((Graphics2D) g);

        g.setColor(Color.white);//edges color or border
        g.fillRect(0, 0, 5,800);
        g.fillRect(0, 0, 1000, 5);
        g.fillRect(990, 0, 5,800);
       
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 40));//for counter
                                //x   y
        g.drawString("" + score, 920, 45);//draws a string on the graphics /Graphics function
 //paddel
        Graphics2D g2d=(Graphics2D)g;
        g.setColor(new Color(255,255,200));
        g.fillRect(playerX, 735, 210, 20);
        g2d.setStroke(new BasicStroke(3));//border of bricks
                    g2d.setColor(new Color(255,255,255));
                    g2d.drawRect(playerX,735,210,20);

        //ball
        g.setColor(new Color(204,153,255));
        g.fillOval(ballposX, ballposY, 36, 36);//for oval

        if (ballposY > 760) {
            play = false;
            ballXdir = 0;//ball stop
            ballYdir = 0;
            g.setColor(Color.black);
            g.setFont(new Font("SansSerif", Font.BOLD, 35));
            g.drawString("    Game Over Score: " + score, 313, 350);

            g.setFont(new Font("SansSerif", Font.BOLD, 35));
            g.drawString("   Press Enter to Restart", 310, 400);
        }
        if(totalbricks == 0){
            play = false;
            ballYdir = 0;//before -1/-2
            ballXdir = 0;
            g.setColor(Color.black);
            g.setFont(new Font("SansSerif",Font.BOLD,35));
            g.drawString("    you won: "+score,350,300);

            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart",318,400);


        }

        g.dispose();//causes the JFrame window to be destroyed and cleaned up by the operating system. 

     }
    
    @Override// invoke the method when click on an object
    public void actionPerformed(ActionEvent e) {//function for actionlisiner and aoutomaticly call
        Timer.start();

        if (play) {// must put rectengle about ball to make intersection between same shape
            if (new Rectangle(ballposX, ballposY, 36, 36).intersects(new Rectangle(playerX, 735,210,20))) {
                ballYdir = -ballYdir;
            }

            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.bricksWidth +187;
                        int brickY = i * map.bricksHeight +70;
                        int bricksWidth = map.bricksWidth;
                        int bricksHeight = map.bricksHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 35,35);//araound ball
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) {
                            map.setBricksValue(0, i, j);
                            totalbricks--;
                            score += 5;
                            if (ballposX + 28<= brickrect.x || ballposX + 5 >= brickrect.x + bricksWidth) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }


                }
            }


            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX >960) {
                ballXdir = -ballXdir;
            }
        }
        repaint();//redraw component again
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
            if (playerX >765) {//check to ensure paddel not go outside paddel <= before
                playerX = 765;
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

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                ballposX = 120;
                ballposY = 350;
                ballXdir = -2;
                ballYdir = -3;
                score = 0;
                playerX =420;
                totalbricks = 21;
                map = new MapGenerator(3, 7);

                repaint();
            }
        }


        }

        public void moveRight ()
        {
            play = true;
            playerX += 20;
        }
        public void moveLeft ()
        {
            play = true;
            playerX -= 20;
        }
        
    
    
}
