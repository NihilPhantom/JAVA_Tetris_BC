package model;
/**
 * 图片类信息获取
 */
import javax.swing.ImageIcon;

public class ImageModel {
    /**
     * notes：这里用ImageIcon来读取图片，以避免ImageIO.read时需要错误控制。
     *          您可以使用ImageIcon.getImg()来获取Image对象
     */
    public static ImageIcon BACK_IMAGE = new ImageIcon("imgs/back3.png");
    public static ImageIcon LEFT_IMG = new ImageIcon("imgs/left.png");
    public static ImageIcon RIHGT_IMG = new ImageIcon("imgs/right.png");
    public static ImageIcon DOWN_IMG = new ImageIcon("imgs/down.png");
    public static ImageIcon ROTATE_IMG = new ImageIcon("imgs/rotate.png");
    public static ImageIcon SETTING_IMG = new ImageIcon("imgs/setting.png");
    public static ImageIcon LOGEIN_IMG = new ImageIcon("imgs/signin.png");
    public static ImageIcon[] MASK_IMG = new ImageIcon[]{
        new ImageIcon("imgs/mask0.png"),
        new ImageIcon("imgs/mask1.png"),
    }
    ;
}