package controller;

import javax.swing.ButtonGroup;
/**
 * 操作按键控制区，对点击事件的处理
 */
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import model.GameData;
import model.ImageModel;
import view.AlertFrame;
import view.MainWin;
import view.elements.FunArea;
import view.elements.ImageButtom;

public class OprationControl {
    /**
     * 游戏数据
     */
    GameData gameData;
    MainWin mainwin;
    DownControll downControl;
    public int downKey;
    public int rotaKey;
    public int leftKey;
    public int righKey;

    /**
     * 添加设置事件
     */
    class Setting implements FunArea.Clickable {
        @Override
        public void onClick() {
            int statebake = gameData.state;
            gameData.state = 2;
            ButtonGroup choseblock = new ButtonGroup();
            JRadioButton block1 = new JRadioButton("晶莹");
            JRadioButton block2 = new JRadioButton("立方");

            choseblock.add(block1);
            choseblock.add(block2);
            JPanel Jpanel = new JPanel();
            Jpanel.setSize(123,160);
            Jpanel.setPreferredSize(new Dimension(123, 160));
            Jpanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 20));
            JLabel jlchoseblock = new JLabel("设置格子主题：");
            Jpanel.add(jlchoseblock);
            Jpanel.add(block1);
            Jpanel.add(block2);
            if(gameData.blocksType == 0){
                block1.setSelected(true);
            }else{
                block2.setSelected(true);
            }
            JLabel jlSetKeys = new JLabel("按键设置：(不填写默认为方向键)                     ");
            Jpanel.add(jlSetKeys);
            JLabel jlro = new JLabel("旋转");
            JLabel jld = new JLabel("向下");
            JLabel jll = new JLabel("向左");
            JLabel jlri = new JLabel("向右");
            JTextField jtro = new JTextField((rotaKey==38)?"":""+(char)rotaKey,2);
            JTextField jtd = new JTextField((downKey==40)?"":""+(char)downKey,2);
            JTextField jtl = new JTextField((leftKey==37)?"":""+(char)leftKey,2);
            JTextField jpri = new JTextField((righKey==39)?"":""+(char)righKey,2);
            Jpanel.add(jlro);
            Jpanel.add(jtro);
            Jpanel.add(jld );
            Jpanel.add(jtd );
            Jpanel.add(jll );
            Jpanel.add(jtl );
            Jpanel.add(jlri);
            Jpanel.add(jpri);
            Jpanel.setBackground(Color.white);
            JScrollPane jsp = new JScrollPane();
            jsp.setBackground(Color.white);
            jsp.setSize(290, 130);
            jsp.setViewportView(Jpanel);
            AlertFrame af = new AlertFrame(mainwin, true,
             new Container[]{jsp},
             new int[]{27, 52, 290, 130}) {
                private static final long serialVersionUID = -3163280776393079548L; // 序列化
                @Override
                public void goahead() {
                    if(!jtd.getText().equals("")){
                        downKey = jtd.getText().toUpperCase().charAt(0);
                    }else{
                        downKey = KeyEvent.VK_DOWN;
                    }
                    if(!jtro.getText().equals("")){
                        rotaKey = jtro .getText().toUpperCase().charAt(0);
                    }else{
                        rotaKey = KeyEvent.VK_UP;
                    }
                    if(!jtl .getText().equals("")){
                        leftKey = jtl .getText().toUpperCase().charAt(0);
                    }else{
                        leftKey = KeyEvent.VK_LEFT;
                    }
                    if(!jpri.getText().equals("")){
                        righKey = jpri.getText().toUpperCase().charAt(0);
                    }else{
                        righKey = KeyEvent.VK_RIGHT;
                    }
                    if(block1.isSelected()){
                        gameData.blocksType = 0;
                    }else{
                        gameData.blocksType = 1;
                    }
                    closeDialog();
                }
            };
            af.setOKText("使用");
            af.showDialog();
            gameData.state = statebake;
        }
    };

    public Setting onSettingClick = new Setting();

    /**
     * 添加登录事件
     */
    class Signin implements FunArea.Clickable {
        @Override
        public void onClick() {
            int statebake = gameData.state;
            gameData.state = 2;
            JLabel jb1 = new JLabel("昵称：");
            JLabel jb2 = new JLabel("密码：");
            JTextField jta = new JTextField(gameData.playername=="未知玩家"?"":gameData.playername,15);
            JPasswordField jpa = new JPasswordField(15);
            jpa.setEchoChar('*');
            AlertFrame af = new AlertFrame(mainwin, true, new Container[] { jb1, jb2, jta, jpa },
                    new int[] { 40, 60, 70, 30, 40, 116, 70, 30, 120, 60, 120, 30, 120, 116, 120, 30 }) {
                private static final long serialVersionUID = -3163280776393079548L; // 序列化

                @Override
                public void goahead() {
                    if(((JTextField) this.extreElements[2]).getText()=="") {
                        this.note("名字为空，以“未知玩家”进行记录");
                    }else if (gameData.testPlayer(((JTextField) this.extreElements[2]).getText(),
                            ((JPasswordField) this.extreElements[3]).getPassword())) {
                                closeDialog();
                    }else{
                        this.note("只用拥有密码才可以使用该昵称");
                    }
                }
            };
            af.setOKText("登录/注册");
            af.showDialog();
            gameData.state = statebake;
        }
    };

    public Signin onSigninclick = new Signin();

    abstract class OprationButtom extends ImageButtom {
        private static final long serialVersionUID = 4966748868577177335L; // 序列化

        OprationButtom(ImageIcon image) {
            super(image, 42, 42);
        }

        @Override // 添加点击事件
        public void doClick() {
            if (gameData.state == 1) {
                onClick();
                (mainwin.getgameCanvas()).repaint();
            }
        }
        /**
         * 需要重写的事件
         */
        abstract void onClick();
    }

    /**
     * 添加旋转事件
     */
    public OprationButtom rotate;
    public OprationButtom left;
    public OprationButtom right;
    public OprationButtom down;
    public JButton startStop = new JButton("开始");
    OprationControl op;

    OprationControl(GameData gameData, MainWin mainwin) {
        /**
         * 接收游戏数据
         */
        this.gameData = gameData;

        /**
         * 接收游戏面板
         */
        this.mainwin = mainwin;

        /**
         * 备份一个自己一遍传值
         */
        op = this;

        /**
         * 初始按键值
         */
        downKey = KeyEvent.VK_DOWN;
        rotaKey = KeyEvent.VK_UP;
        leftKey = KeyEvent.VK_LEFT;
        righKey = KeyEvent.VK_RIGHT;

        /**
         * 游戏进行状态控制
         */
        startStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (gameData.state) {
                    case 0:
                        startStop.setText("暂停");
                        // 窗口更新与数据绑定
                        gameData.init();
                        mainwin.setDynamic();
                        mainwin.setZindex();
                        downControl = new DownControll(op, gameData, mainwin);
                        downControl.start();
                        gameData.setState(1);
                        break;

                    case 1:
                        startStop.setText("继续");
                        gameData.setState(2);
                        break;

                    case 2:
                        startStop.setText("暂停");
                        gameData.setState(1);
                        break;
                }
                mainwin.requestFocus();
            }
        });

        /**
         * 绑定旋转事件
         */
        rotate = new OprationButtom(ImageModel.ROTATE_IMG) {
            private static final long serialVersionUID = 1L; // 序列化

            @Override // 添加点击事件
            public void onClick() {
                gameData.Rotate();
            }
        };

        /**
         * 绑定左移事件
         */
        left = new OprationButtom(ImageModel.LEFT_IMG) {
            private static final long serialVersionUID = 1L; // 序列化
            @Override // 添加点击事件
            public void onClick() {
                gameData.move(true, -1);
            }
        };

        /**
         * 绑定右移事件
         */
        right = new OprationButtom(ImageModel.RIHGT_IMG) {
            private static final long serialVersionUID = 1L; // 序列化

            @Override // 添加点击事件
            public void onClick() {
                gameData.move(true, 1);
            }
        };

        /**
         * 绑定下移事件
         */
        down = new OprationButtom(ImageModel.DOWN_IMG) {
            private static final long serialVersionUID = 1L; // 序列化

            @Override // 添加点击事件
            public void onClick() {
                if (!gameData.move(false, 1)) {
                    gameData.deletLine();
                    (mainwin.getGameDataArea()).repaint();
                }
                ;
            }
        };
    }
}
