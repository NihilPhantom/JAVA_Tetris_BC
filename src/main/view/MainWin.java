package view;

/**
 * 窗口总调配
 */
import java.awt.Image;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.OprationControl;


import javax.swing.ImageIcon;
import view.elements.FunArea;
import model.GameData;
import model.ImageModel;

public class MainWin extends JFrame {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 5215859506133471743L;

    // 静态变量
    static final int WIN_WEIGTH = 360; // 窗口宽度
    static final int WIN_HEIGHR = 600; // 窗口高度
    static final int GAME_ROOTX = 15; // 游戏主窗口的横坐标
    static final int GAME_ROOTY = 30; // 游戏主窗口的纵坐标
    static final int opacity = 100;   // 格子透明度
    static final int WEIYI = 40;      // 由于数组尺寸冗余，做出的绘图区位移
    /**
     * 配色方案
     */
    public static Color[] COLORS = new Color[] { new Color(0, 0, 0, opacity), new Color(255, 0, 0, opacity),
            new Color(0, 255, 0, opacity), new Color(0, 0, 255, opacity), new Color(0, 255, 255, opacity),
            new Color(255, 0, 255, opacity), new Color(255, 255, 0, opacity), new Color(150, 150, 150, opacity) };

    // 声明变量
    private GameData gameData;
    private Container mainwin; // 背景层
    private GamePanel gamePanel; // 游戏内容Panel
    private OprationArea oprationArea; // 操作按钮Panel
    private GameCanvas gameCanvas; // 下落的物块区
    private GameDataArea gameDataArea; // 游戏其余数据区
    private PlayersPanel playersPanel; // 玩家数据区

    public MainWin(GameData gameData) {
        mainwin = getLayeredPane(); // initialize variable 变量初始化
        setTitle("Test V1.0.0"); // set up 设置窗口基本属性
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 50, WIN_WEIGTH, WIN_HEIGHR);
        setResizable(false); // 设置主窗口不可以拉伸
        setLayout(null); // 设置主窗口为自由布局
        setBgImage(); // set background image 设置游戏背景
        this.gameData = gameData; // 加载游戏数据
        setPlayerList(); // 加载玩家参数
    }

    // 设置背景图片
    void setBgImage() {
        ImageIcon imgic = ImageModel.BACK_IMAGE;
        JLabel imgl = new JLabel(imgic);
        imgl.setBounds(-10, -2, WIN_WEIGTH, WIN_HEIGHR);
        getContentPane().add(imgl);
    }

    // 设置界面功能区背景
    void drawGameAera() {
        gamePanel = new GamePanel();
        gamePanel.setSize(WIN_WEIGTH, WIN_HEIGHR);
        gamePanel.setOpaque(false);
        mainwin.add(gamePanel);
    }

    /**
     * 加载玩家参数
     */
    private void setPlayerList() {
        playersPanel = new PlayersPanel(gameData);
        mainwin.add(playersPanel);
    }

    // 设置数据更新的图层
    public void setDynamic() {
        gameCanvas = new GameCanvas(this.gameData);
        gameDataArea = new GameDataArea(this.gameData);
        mainwin.add(gameCanvas);
        mainwin.add(gameDataArea);
    }

    public void setOprationArea(OprationControl oprationControl) {
        this.oprationArea = new OprationArea(oprationControl);
        mainwin.add(oprationArea); // add OprationArea 添加界面控制按钮
        drawGameAera(); // set GamePanel 游戏主界面布局
        setStaticBackGround();
    }

    /**
     * 设置整体界面层次
     */
    public void setZindex() {
        mainwin.setComponentZOrder(gamePanel, 1);
        mainwin.setComponentZOrder(gameCanvas, 0);
        mainwin.setComponentZOrder(gameDataArea, 0);
        mainwin.setComponentZOrder(oprationArea, 0);
    }

    /**
     * 设置静态界面层次
     */
    void setStaticBackGround() {
        mainwin.setComponentZOrder(gamePanel, 1);
        mainwin.setComponentZOrder(oprationArea, 0);
        mainwin.validate();
    }

    /**
     * 添加函数区
     */
    public void addFunAera(Image img, FunArea.Clickable fun) {
        gamePanel.addFunAera(img, fun);
    }

    // getter
    public OprationArea getOpration() {
        return oprationArea;
    }

    public Container getCon() {
        return mainwin;
    }

    public GameCanvas getgameCanvas() {
        return gameCanvas;
    }

    public GameDataArea getGameDataArea() {
        return this.gameDataArea;
    }

    //
    public void alert(String string) {
        System.out.println("1234567");
        int backstate = gameData.state;
        gameData.state = 1;
        AlertFrame af = new AlertFrame(this, true) {
            // 序列化
            private static final long serialVersionUID = 5650990856441760215L;
            @Override
            public void goahead() {
                this.closeDialog();
            }
        };
        JLabel jl = new JLabel(string);
        jl.setFont(new Font("宋体", Font.PLAIN, 36));
        af.addElement(jl, 27, 52, 270, 144);
        af.setOKText("确定");
        af.showDialog();
        gameData.state = backstate;
    }
}