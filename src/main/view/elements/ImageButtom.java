package view.elements;

// import java.awt.Graphics;


/**
 * 图片按钮
 */
import java.awt.RenderingHints;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public abstract class ImageButtom extends JPanel{
    /**
     * 图片
     */
    ImageIcon img;
    /**
     * 绑定点击响应
     */
    FunArea funArea;
    int weight;
    int height;
    // 序列化
    private static final long serialVersionUID = 2191075283557461594L;
    // 初始化
    public ImageButtom(ImageIcon img, int x, int y){
        super();                      // 继承一下;
        setOpaque(false);
        this.img = img;
        this.weight = x;
        this.height = y;
        /**
         * 创建事件监听
         */
        funArea = new FunArea(this, 0, 0, x, y){
            @Override
            public void onClick() {
                doClick();
            }
        };
    }
    @Override
    protected void paintComponent(Graphics g){
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(img.getImage(), 0,0, weight, height, null);
    }
    public ImageButtom() {
	}
	/**
     * 可重载的点击事件
     */
    public abstract void doClick();
}