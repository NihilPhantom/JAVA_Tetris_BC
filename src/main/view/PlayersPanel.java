package view;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.GameData;


class PlayersPanel extends JPanel{

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1593197927294329594L;

    /**
     * 玩家列表定位
     */
    static int ROOT_X1 = 25;
    static int ROOT_X2 = 100;
    static int ROOT_Y = 15;
    static int FOUT_SIZE = 20;

    /**
     * 游戏数据
     */
    GameData gameData;

    /**
     * 初始化
     */
    PlayersPanel(GameData gameData){
        setBounds(15, 445, 200, 130);
        this.gameData = gameData;
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setFont(new Font("黑体", Font.PLAIN, 30));
        g.setColor(new Color(80, 80, 80));
        g.drawString(""+gameData.getScore(), 245, 88);

        int i = 0;
        g.setFont(new Font("黑体", Font.PLAIN, 15));
        for(String name: gameData.getPlayersName()){
            g.drawString(name, ROOT_X1, ROOT_Y + i*FOUT_SIZE);
            i++;
        }
        i = 0;
        for(int score: gameData.getPlayerScore()){
            g.drawString("....  " + score, ROOT_X2, ROOT_Y + i*FOUT_SIZE);
            i++;
        }
    }
    
}