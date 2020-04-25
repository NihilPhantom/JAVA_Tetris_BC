package view;
/**
 * 下降的俄罗斯方块视图
*/
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JPanel;

import model.Block;
import model.ImageModel;
import model.GameData;


public class GameCanvas extends JPanel{
    // 序列化
    private static final long serialVersionUID = -6353837386191637925L;

    GameData gameData;

    GameCanvas(GameData gameData){
        // 确定游戏区范围
        setBounds(MainWin.GAME_ROOTX, MainWin.GAME_ROOTY, 200, 360);
        // 将背景设置为透明
        setOpaque(false);
        // 获取游戏数据
        this.gameData = gameData;
    }

    @Override //重载绘制函数
    protected void paintComponent(Graphics g){
        for(Point point :(gameData.getBlock()).getPoints()){
            g.setColor(MainWin.COLORS[gameData.getCurrent()+1]);
            int _x = point.x * Block.SIZE;
            int _y = point.y * Block.SIZE - MainWin.WEIYI;
            g.fillRect(_x, _y, Block.SIZE, Block.SIZE);
            g.drawImage(ImageModel.MASK_IMG[gameData.blocksType].getImage(), _x, _y, Block.SIZE, Block.SIZE, null);
        }
    }
}