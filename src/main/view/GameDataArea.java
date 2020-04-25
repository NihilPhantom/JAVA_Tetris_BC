package view;

import java.awt.Point;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.GameData;
import model.ImageModel;

public class GameDataArea extends JPanel{
    /**
     * 序列化
     */
    private static final long serialVersionUID = -2547962449511582903L;

    /**
     * 格子的大小
     */
    static int BLOCK_SIZE = 20;

    /**
     * 下一个定位
     */
    static int NEXT_X = 188;
    static int NEXT_Y = 180;

    /**
     * 游戏数据
     */
    private GameData gameData;

    /**
     * 初始化基本配置
     * @gamedata 游戏数据
     */
    public GameDataArea(GameData gameData){
        setOpaque(false);  // 设置窗体透明
        setBounds(0, 0, MainWin.WIN_WEIGTH, MainWin.WIN_HEIGHR); // 设置窗口位置信息；
        this.gameData = gameData; //绑定游戏数据
    }

    /**
     * 重写绘制方法，更新游戏数据
     */
    @Override
    public void paint(Graphics g){
        // 继承一下
        super.paint(g);

        // 绘制存在的方块方块们
        // g.setColor(new Color(150,150,150));
        int _x;
        int _y;
        for(int i=2; i<20 ;i++){
            for(int j=0; j<10; j++){
                if(gameData.getExistBlocks()[i][j]!=0){
                    g.setColor(MainWin.COLORS[gameData.getExistBlocks()[i][j]]);
                    _x = MainWin.GAME_ROOTX + j*BLOCK_SIZE;
                    _y = MainWin.GAME_ROOTY-MainWin.WEIYI + i*BLOCK_SIZE;
                    g.fillRect(_x, _y, BLOCK_SIZE, BLOCK_SIZE);
                    g.drawImage(ImageModel.MASK_IMG[gameData.blocksType].getImage(), _x, _y, BLOCK_SIZE, BLOCK_SIZE, null);
                }
            }
        }

        // 绘制下一个形状;
        int nextID = gameData.getNext();
        int nextDeviate = GameData.DEVIATE[nextID];
        g.setColor(MainWin.COLORS[gameData.getNext()+1]);
        for(Point point :(GameData.BLOCKS[nextID]).getPoints()) {
            _x = NEXT_X + point.x * BLOCK_SIZE + (BLOCK_SIZE>>1) * nextDeviate;
            _y = NEXT_Y + point.y * BLOCK_SIZE + (BLOCK_SIZE>>1) * nextDeviate;
            g.fillRect(_x, _y, BLOCK_SIZE, BLOCK_SIZE);
            g.drawImage(ImageModel.MASK_IMG[gameData.blocksType].getImage(), _x, _y, BLOCK_SIZE, BLOCK_SIZE, null);
        }
        // 更新玩家的分数
        g.setFont(new Font("黑体", Font.PLAIN, 30));
        g.drawString(""+gameData.getScore(), 245, 88);
    }
}
