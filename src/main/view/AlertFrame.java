package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.BorderLayout;
// import java.util.List;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.elements.FunArea;

/**
 * 这是一个用于弹框的类
 */

public abstract class AlertFrame extends JDialog {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -1952717004495243032L;

    /**
     * 布局块,为了解决自由布局下label无法定位的情况
     */
    class LayoutPanel extends JPanel {
        // 序列化
        private static final long serialVersionUID = 4515814262204592437L;

        LayoutPanel(Container c) {
            setLayout(new BorderLayout(0,0));
            setOpaque(false);
            add(c,BorderLayout.CENTER);
        }
    }
    private Font defaultFont;
    private ImageIcon bgimg = new ImageIcon("imgs/alert.png");
    protected Container[] extreElements;
    private JLabel bgLabel;
    private JPanel maincontainer;
    JLabel jl;
    JLabel notejl;

    private Container container;

    /**
     * 
     * @param owner      父级
     * @param modal      是否阻塞
     * @param containers 要加入的元素列表
     * @param position   设置元素的位置{x1, y1, w1, h1, x2, y2, w2, h2, x3....}
     */
    public AlertFrame(Frame owner, boolean modal, Container[] containers, int[] position) {
        this(owner, modal);
        this.extreElements = containers;
        int i = 0;
        for (Container container : extreElements) {
            try{
                container.setFont(defaultFont);
            }catch(Exception e){
                e.printStackTrace();
            }
            addElement(container, position[i + 0], position[i + 1], position[i + 2], position[i + 3]);
            i += 4;
        }
    }

    public AlertFrame(Frame owner, boolean modal) {
        super(owner, modal); // 继承父类的构造函数
        setLayout(null); // 自由布局
        container = getContentPane(); // 获取主容器
        bgLabel = new JLabel(bgimg); // 添加图片
        bgLabel.setBounds(0, 0, 340, 247); // 设置位置
        container.add(bgLabel); // 添加图片标签
        setBounds(0, 0, 340, 247); // 设置窗口大小
        setUndecorated(true); // 取消窗口按键
        setLocationRelativeTo(this.getParent()); // 在父级内部展示
        defaultFont = new Font("黑体", Font.PLAIN, 14);
        maincontainer = new JPanel(); // 初始化主控件区
        maincontainer.setBounds(0, 0, 340, 247); // 设置控件全屏
        maincontainer.setOpaque(false); // 背景透明
        maincontainer.setLayout(null);
        container.add(maincontainer); // 加入事件
        notejl = new JLabel(""); // 创建文本
        notejl.setForeground(Color.red);
        notejl.setFont(new Font("黑体", Font.PLAIN, 12)); // 设置JLabel的字体
        addElement(notejl, 0, 190, 250, 40);
        container.setComponentZOrder(maincontainer, 0);
        container.setComponentZOrder(bgLabel, 1);

        /**
         * 关闭事件绑定
         */
        new FunArea(maincontainer, 265, 0, 75, 43) {
            @Override
            public void onClick() {
                closeDialog();
            }
        };

        /**
         * 默认按钮事件
         */
        new FunArea(maincontainer, 210, 190, 114, 43) {
            @Override
            public void onClick() {
                goahead();
            }
        };
    }

    /**
     * 添加元素
     */
    public void addElement(Container container, int x, int y, int w, int h) {
        LayoutPanel lp = new LayoutPanel(container);
        lp.setBounds(x, y, w, h);
        maincontainer.add(lp);
    }

    public void addElement(Container container) {
        // addElement(container, conta)
    }

    /**
     * 显示提示框；
     */
    public void showDialog() {
        validate();
        setVisible(true);
    }

    /**
     * 添加默认按钮文本
     */
    public void setOKText(String s) {
        jl = new JLabel(s, JLabel.CENTER); // 创建文本
        jl.setFont(new Font("黑体", Font.PLAIN, 22)); // 设置JLabel的字体
        addElement(jl, 210, 190, 114, 43);
    }

    /**
     * 默认按钮被点击的触发的事件
     */
    abstract public void goahead();

    /**
     * 提示信息
     */
    protected void note(String s){
        notejl.setText(s);
        validate();
        // repaint();
    }

    /**
     * 关闭提示框
     */
    public void closeDialog() {
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dispose();
    }

};
