package view.elements;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * 这个类用于给父级容器创建一个可以被点击的区域，类似于碰撞箱。
 * @param fatherContaniner 主窗口 父级容器
 * @param x 区域的启始横坐标
 * @param y 区域的启示纵坐标
 * @param w 区域的宽度
 * @param h 区域的高度
 */

public abstract class FunArea {

    // 点击是事件接口
    @FunctionalInterface
    public interface Clickable{
        abstract public void onClick();
    }

    public int x;
    public int y;
    public int w;
    public int h;

    public FunArea(Container fatherContainer, int x, int y, int w, int h){

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        fatherContainer.addMouseListener(new MouseListener(){
            @Override
            /**
             * notes: 当使用MouseInfo.getPointerInfo().getLocation();point.x；point.y;获取的是全局的鼠标的位置
             *          而当我们使用e.getX()时，我们获取的是相对添加事件元素的坐标
             */
            public void mouseClicked(MouseEvent e) {
                if(e.getX()>=x && e.getX()<=x+w){
                    if(e.getY()>=y && e.getY()<=y+h){
                        onClick();
                    }
                }
                // TODO 提示坐标
                // System.out.println(e.getX() + "---" + e.getY());
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    // 设置点击事件
    abstract public void onClick();

}
