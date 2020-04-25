package view;

/**
 * 操作按键区
 */
import view.elements.GridBagPanel;

import java.awt.Font;

import controller.OprationControl;

public class OprationArea extends GridBagPanel{
    private static final long serialVersionUID = 5498945643539618376L;
    OprationArea(OprationControl oprationControl){
        setOpaque(false);
        setBounds(233, 260, 90, 150);
        add(oprationControl.left, setPostion(1, 1, 1, 1));
        add(oprationControl.down, setPostion(1, 2, 1, 1));
        add(oprationControl.right, setPostion(2, 1, 1, 1));
        add(oprationControl.rotate, setPostion(2, 2, 1, 1));
        oprationControl.startStop.setContentAreaFilled(false);
        oprationControl.startStop.setFont(new Font("华文行楷", 1, 23));
        add(oprationControl.startStop, setPostion(1, 4, 2, 1, 7));
    }
    
}

