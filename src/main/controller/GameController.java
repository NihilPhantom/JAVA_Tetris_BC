package controller;

/**
 * 游戏资源总调度
 */
import view.MainWin;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameData;
import model.ImageModel;

public class GameController {
    public static void main(String[] args) {
        // 加载游戏数据
        GameData gamedata = new GameData();

        // 加载主窗口
        MainWin mainwin = new MainWin(gamedata);

        // 初始化操作区
        OprationControl oprationControl = new OprationControl(gamedata, mainwin);
        mainwin.setOprationArea(oprationControl);

        // 初始化全局操作
        mainwin.addFunAera(ImageModel.SETTING_IMG.getImage(), oprationControl.onSettingClick);
        mainwin.addFunAera(ImageModel.LOGEIN_IMG.getImage(), oprationControl.onSigninclick);

        // 窗口事件监听
        mainwin.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent state) {
                if (state.getNewState() == 0) {
                    System.out.println("窗口恢复到初始状态");
                    mainwin.validate();
                }
            }
        });

        // 按键事件监听
        mainwin.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                // TODO 打印输入字符
                // System.out.println(code);
                if(code == oprationControl.rotaKey){
                    oprationControl.rotate.doClick();
                }
                if(code == oprationControl.downKey){
                    oprationControl.down.doClick();
                }
                if(code == oprationControl.leftKey){
                    oprationControl.left.doClick();
                }
                if(code == oprationControl.righKey){
                    oprationControl.right.doClick();
                }
            }

            public void keyTyped(KeyEvent e) {
                ;
            }

            public void keyReleased(KeyEvent e) {
                ;
            }

        });

        // 展示主窗
        mainwin.setVisible(true);
        mainwin.setFocusable(true);
    }
}

// 每秒下降控制
class DownControll extends Thread {
    int resttime;
    int sleepTime;
    GameData gamedata;
    MainWin mainwin;
    int score;
    OprationControl oprationControl;

    DownControll(OprationControl op, GameData gd, MainWin mw) {
        resttime = 200;
        sleepTime = 800;
        gamedata = gd;
        mainwin = mw;
        oprationControl = op;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (gamedata.state == 1) {
                    if (!gamedata.move(false, 1)) {
                        gamedata.deletLine();
                        score = gamedata.getScore();
                        (mainwin.getGameDataArea()).repaint();
                    }
                    (mainwin.getgameCanvas()).repaint();
                    sleep(sleepTime-(gamedata.getScore()<500?gamedata.getScore():500));
                } else if (gamedata.state == 3) {
                    mainwin.alert("游戏结束" + gamedata.getScore() + "分");
                    oprationControl.startStop.setText("新的");
                    (mainwin.getgameCanvas()).repaint();
                    gamedata.state = 0;
                } else if ((gamedata.state == 2)) {
                    sleep(resttime);
                    DownControll.yield();
                } else {
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
