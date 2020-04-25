
/** 
 * java swing 之JScrollPane面板 
 * 在设置界面时，可能会遇到在一个较小的容器窗体中显示一个较大部分的内容，这时可以使用 
 * JScrollPane面板，JscrollPane面板是带滚动条的面板，也是一种容器，但是常用于布置单个 
 * 控件，并且不可以使用布局管理器。如果需要在JScrollPane面板中放置多个控件，需要将多个 
 * 控件放置到JPanel 面板上，然后将JPanel面板作为一个整体控件添加到JScrollPane控件上。 
 * 
 * @author gao 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class JScrollPaneDemo extends JFrame{
    private static final long serialVersionUID = -2464279946232513886L;
    private JPanel contentPane;
    private JScrollPane scrollPane;
    public JScrollPaneDemo(){
        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        this.setContentPane(contentPane);
        scrollPane=new JScrollPane();
        contentPane.add(scrollPane,BorderLayout.CENTER);
        ButtonGroup choseblock = new ButtonGroup();
        JRadioButton block1 = new JRadioButton("晶莹");
        JRadioButton block2 = new JRadioButton("立方");
        choseblock.add(block1);
        choseblock.add(block2);
        JPanel Jpanel = new JPanel();
        Jpanel.setSize(123,900);
        Jpanel.setPreferredSize(new Dimension(123, 900));
        Jpanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 20));
        Jpanel.add(block1);
        Jpanel.add(block2);
        JLabel jlro = new JLabel("旋转");
        JLabel jld = new JLabel("向下");
        JLabel jll = new JLabel("向左");
        JLabel jlri = new JLabel("向右");
        JTextField jtro = new JTextField(2);
        JTextField jpd = new JTextField(2);
        JTextField jtl = new JTextField(2);
        JTextField jpri = new JTextField(2);
        Jpanel.add(jlro);
        Jpanel.add(jtro);
        Jpanel.add(jld );
        Jpanel.add(jpd );
        Jpanel.add(jll );
        Jpanel.add(jtl );
        Jpanel.add(jlri);
        Jpanel.add(jpri);
        //scrollPane.add(textArea);  
        scrollPane.setViewportView(Jpanel);
        this.setTitle("滚动面板使用");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 250, 200);
    }
    public static void main(String []args){
        JScrollPaneDemo example=new JScrollPaneDemo();
        example.setVisible(true);
    }
}