package view;

/**
 *  游戏界面分块背景,一些静态的东西都在这里绘制
 */

import java.util.List;
import java.util.ArrayList;

import java.awt.Font;
import java.awt.Image;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;

import javax.swing.JPanel;
import view.elements.FunArea;

class GamePanel extends JPanel {
    // 功能区: 程序预设置六个扩展功能，功能过多会影响整体美观。
    private List<ImgFunArea> funAreas; 
    private static final long serialVersionUID = 1L;
    static int FUN_ROOT_X = 213;
    static int FUN_ROOT_Y = 470;
    static int Fun_SIZE = 40;
    // 构造函数，注册一下功能区
    GamePanel() {
        funAreas = new ArrayList<ImgFunArea>();
    }

    // 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 255, 255, 255));
        // 设置主屏边框
        ((Graphics2D) g).setStroke(new BasicStroke(3L));
        g.drawRect(13, 28, 203, 363);
        g.drawRect(13, 403, 203, 133);
        g.setColor(new Color(150, 150, 150, 70));
        // 游戏主屏
        g.fillRect(MainWin.GAME_ROOTX, MainWin.GAME_ROOTY, 200, 360);
        // 排名区
        g.fillRect(15, 405, 200, 130);
        // 右边排版
        g.fillRect(223, 20, 105, 400);
        g.setColor(new Color(2, 2, 2, 30));
        // 得分区
        g.fillRect(233, 30, 90, 70);
        // 提示区
        g.fillRect(233, 105, 90, 140);
        // 
        g.fillRect(233, 255, 90, 90);
        // 全局操作区
        for(ImgFunArea imgFunArea: funAreas){
            g.drawImage(imgFunArea.getImage(), imgFunArea.x, imgFunArea.y, Fun_SIZE, Fun_SIZE, null);
        }
        // 文字
        g.setColor(new Color(2, 2, 2, 100));
        g.setFont(new Font("黑体", Font.PLAIN, 20));
        g.drawString("得分", 240, 55);
        g.drawString("下一个", 236, 140);
        g.drawString("荣誉榜", 25, 435);

    }

    public void addFunAera(Image img, FunArea.Clickable fun) {
        int numOfFun = funAreas.size();
        if (numOfFun < 7) {
            int x = FUN_ROOT_X + ((5 - numOfFun)%3)*Fun_SIZE;
            int y = FUN_ROOT_Y + ((5 - numOfFun)/3)*Fun_SIZE;
            funAreas.add(new ImgFunArea(this, img, x, y, Fun_SIZE, Fun_SIZE){
                @Override
                public void onClick(){
                    fun.onClick();
                }
            });
        } else {
            try {
                throw (new Exception("添加的功能个数超过预定的绘制空间"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



abstract class ImgFunArea extends FunArea{
    /**
     *  设置带有图片的功能区
     */
    Image img;
    // 继承功能区
    ImgFunArea(Container fatherContainer, Image img,  int x, int y, int w, int h){
        super(fatherContainer, x, y, w, h);
        this.img = img;
    }
    // getter
    Image getImage(){
        return img;
    }
}