package model;

/**
 * 俄罗斯方块模板
*/

import java.awt.Point;

public class Block{

    Point[] points;         // 格子顶点集
    static int INIT_X = 4;  // 初始化横坐标
    static int INIT_Y = 1;  // 初始化纵坐标
    public static int SIZE = 20;   // 每一个格子的大小 单位px
    int shiftX;
    int shiftY;


    // 构造器: 仅用于设置7类基础方块
    Block(int[] x, int[] y){
        points = new Point[4];
        for(int i=0; i<4; i++){
            points[i] = new Point(x[i]+INIT_X, y[i]+INIT_Y);
        }
    }


    /**
     * 拷贝构造方法，用于每次生成的方块实例
    */
    public Block(Block b){
        points = new Point[4];
        for(int i=0; i<4; i++){
            points[i] = new Point((b.points[i]).x, (b.points[i]).y);
        }
        shiftX = INIT_X;
        shiftY = INIT_Y;
    }


    /**
     * 移动方法
     * @param ishrizontal 是否水平移动
     * @param step        移动的格数
     */
    public void move(Boolean ishrizontal, int step){
        if(ishrizontal){
            for(int i=0; i<4; i++){
                points[i].x += step;
            }
            shiftX += step;
        }else{
            for(int i=0; i<4; i++){
                points[i].y += step;
            }
            shiftY += step;
        }
    }

    
    /**
     * 顺时针旋转
     * 1. 将坐标减去偏置变为原始坐标
     * 2. 利用旋转公式进行旋转 y=x; x=-y;
     * 3. 再将原始坐标加上偏置转化为实体坐标
     */
    public void rotate(){
        for(int i=0; i<4; i++){
            points[i].x -= shiftX;
            points[i].y -= shiftY;
            int temp    = points[i].y;
            points[i].y = points[i].x;
            points[i].x = -temp;
            points[i].x += shiftX;
            points[i].y += shiftY;
        }
    }

    // getter
    public Point[] getPoints(){
        return points;
    }
}