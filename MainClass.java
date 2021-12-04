package brick.breaker.game;

/**
 *
 * @author sajaa
 */
// javax.swing is an library or pakcege and JFrame is class for frame

import javax.swing.JFrame;
    
    public class MainClass {
    public static void main(String[] args) {
        JFrame obj = new JFrame();// ()take string often as a title
        GamePlay gameplay = new GamePlay();
        obj.setBounds(500,100,1000,800);//obj.setBounds responsible for dimintion of frame
// first argument for lef distince 2nd for top 3rd for width 4th for height
        obj.setTitle("Saja's BrickBreakerGame");
        obj.setResizable(false);// make frame able to minimize 
        obj.setVisible(true);//setVisible to determine the window either visible or not
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//determine what will happen when click X
        obj.add(gameplay);//add tool to frame like panel or button or text-we need method of GamePlay 
    }
    
}
    
    

