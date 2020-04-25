package model;

import java.awt.Point;
import java.util.List;
import java.util.Random;


/**
 * 玩家在进行游戏时产生的所有数据都通过这个类，在内存中实例；
*/
public class GameData{
    /**
     * 常量方块，包含每个方块之间的相对位置信息
     */
    public static Block[] BLOCKS = new Block[]{
        new Block(new int[]{-1, 0, 1, 1}, new int[]{ 0, 0, 0,-1}),
        new Block(new int[]{-1, 0, 1, 2}, new int[]{ 0, 0, 0, 0}),
        new Block(new int[]{-1,-1, 0, 1}, new int[]{-1, 0, 0, 0}),
        new Block(new int[]{-1, 0, 0, 1}, new int[]{ 0, 0,-1,-1}),
        new Block(new int[]{ 0, 0, 1, 1}, new int[]{ 0,-1, 0,-1}),
        new Block(new int[]{-1, 0, 0, 1}, new int[]{-1, 0,-1, 0}),
        new Block(new int[]{-1, 0, 0, 1}, new int[]{ 0, 0,-1, 0})
    };

    /**
     * 旋转中心距真实中心的偏移量，用于居中输出,单位为1/2
     */
    public static int[] DEVIATE = new int[]{0, -1, 0, 0, -1, 0, 0};

    /**
     * playData
     */
    PlayerData playerData;
    public String playername;
    String playerpassword;
    
    /**
     * 当前的方块 包含当前方块的位置和形状
    */
    Block currentBlock;

    /**
     * 当前累积的方块
    */
    int[][] existBlocks;

    /**
     * 换行数组：记录每一行需要下降的格数
     */
    int[] deletNum;

    /**
     * 随机数产生器
    */
    Random rand;

    /**
     * 预设分值
     */
    static int[] scores = new int[]{0, 10, 30, 60, 100};
    /**
     * 玩家的分数
     */
    int score;

    /**
     * 下一个物块的id
     */
    int next;

    /**
     * 现在物块的id
     */
    int currentid;
    /**
     * 格子样式
     */
    public int blocksType;
    /**
     * 游戏状态 0.没有游戏进行  1。游戏中...  2.游戏暂停   3.游戏结束
     */
    public volatile int state;

    /**
     * 初始化随机过程。初始画存在的方块矩阵
     */
    public GameData(){
        state = 0;
        blocksType = 0;
        playername = "未知玩家";
        playerpassword = "12345";
        playerData = new PlayerData(playername, playerpassword);
        init();
    }
    /**
     * 初始化
     */
    public void init(){
        existBlocks = new int[20][10];
        rand = new Random();
        next = rand.nextInt(7);
        currentid = next;
        score = 0;
        deletNum = new int[20];
        randomBlock();
    }

    /**
     * 随机生成方块
     */
    public void randomBlock(){
        currentid = next;
        currentBlock =  new Block(BLOCKS[next]);
        next = rand.nextInt(7);
        //TODO 提示代码
        // System.out.println("下一个是" + next);
    }

    /**
     * currentBlock的移动方法
     * 1. 判断是否可以移动
     * 2. 可以移动时调用移动方法
     * @return 是否到达底部
    */
    public Boolean move(Boolean ishrizontal, int step){
        Boolean islegal = true;
        try{
            if(ishrizontal){
                for(int i=0; i<4; i++){
                    if(currentBlock.points[i].x + step > 9||
                    currentBlock.points[i].x + step < 0 ||
                    existBlocks[currentBlock.points[i].y][currentBlock.points[i].x + step] != 0){
                        islegal = false;
                        break;
                    };
                }
            }else{
                for(int i=0; i<4; i++){
                    if(currentBlock.points[i].y + step>19 ||
                    existBlocks[currentBlock.points[i].y + step ][currentBlock.points[i].x ]!=0){
                        saveBlock();
                        randomBlock();
                        islegal = false;
                        return false;
                    };
                }
            }
            if(islegal){
                currentBlock.move(ishrizontal, step);
            }
        }catch(IndexOutOfBoundsException e){
            System.out.println("超界了，不过不影响游戏");
        }
        return true;
    }

    /**
     * 旋转方法
     * 1. 判断是否可以旋转
     * 2. 可以旋转调用Block的旋转方法
    */
    public void Rotate(){
        for(int i=0; i<4; i++){
            int y = (currentBlock.points[i].x - currentBlock.shiftX) + currentBlock.shiftY;
            int x = -(currentBlock.points[i].y - currentBlock.shiftY) + currentBlock.shiftX;
            try{
                if(y<0|| y>20 || x<0 || x>10 ||existBlocks[y][x]!=0){
                    return;
                }
            }catch(IndexOutOfBoundsException e){
                System.out.println("超界了，不过不影响游戏");
                return;
            }
        }
        currentBlock.rotate();
    }

    /**
     * 保存Block，用于将CurrentBlock写入到existBlocks里
     * 
    */
    void saveBlock(){
        for(Point point: currentBlock.points){
            existBlocks[point.y][point.x] = getCurrent() + 1;
        }
    }



    /**
     * 消行方法，并计算是否游戏结束；
     */
    public void deletLine(){
        boolean canDelet;
        // 计算要消的行数数组
        for(int i=19; i>=2; i--){
            canDelet = true;
            for(int j=0; j<10 ;j++){
                if(existBlocks[i][j] == 0){
                    canDelet = false;
                    break;
                }
            }
            if(i != 0){
                if(canDelet){
                    deletNum[i-1] = deletNum[i] + 1;
                }else{
                    deletNum[i-1] = deletNum[i];
                }
            }
        }
        // 进行消行
        for(int i=19; i>=2; i--){
            if(deletNum[i] == 0){
                continue;
            }
            for(int j=0; j<10 ;j++){
                existBlocks[i+deletNum[i]][j] = existBlocks[i][j];
            }
        }
        for(int i=0; i<9; i++){
            if(existBlocks[2][i] != 0){
                state = 3;
                saveScore();
                playerData = new PlayerData(playername, playerpassword);
                return;
            }
        }
        score += scores[deletNum[2]];
        deletNum = new int[20];
    }
    /**
     * 保存分数
     */
    void saveScore(){
        playerData.savedata(score);
    }
    // getter
    public Block getBlock(){
        return currentBlock;
    }
    public int[][] getExistBlocks(){
        return existBlocks;
    }
    public int getScore(){
        return this.score;
    }
    public int getNext(){
        return next;
    }
    public int getCurrent(){
        return currentid;
    }
    // getter
    public List<String> getPlayersName(){
        return playerData.playersName;
    }
    public List<Integer> getPlayerScore(){
        return playerData.playerScore;
    };

	public void setState(int state) {
        this.state = state;
    }
    /**
     * 检查用户是否合法
     * @param text
     * @param password
     * @return
     */
	public boolean testPlayer(String text, char[] password) {
        String pass = new String(password);
		if(playerData.signin(text, pass)){
            playername = text;
            playerpassword = pass;
            return true;
        };
        return false;
	}
}

