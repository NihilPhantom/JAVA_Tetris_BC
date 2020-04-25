package view.elements;
/**
 * 网格组布局的Panel
 */
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class GridBagPanel extends JPanel{
    // 序列化
    private static final long serialVersionUID = -1410281569702346504L;
    // 应用网格组布局
    protected GridBagPanel() {
        setLayout(new GridBagLayout());
    }

    // 获取单元格位置
    protected GridBagConstraints setPostion (int x, int y, int w, int h){
        return setPostion(x,y,w,h,0);
    }
    protected GridBagConstraints setPostion (int x, int y, int w, int h, int paddingtop){
        GridBagConstraints g1 = new GridBagConstraints();
        g1.insets = new Insets(paddingtop, 0, 0, 0);
        g1.gridx = x;
        g1.gridy = y;
        g1.gridwidth = w;
        g1.gridheight = h;
        g1.weighty = 1;
        g1.weightx = 1;
        g1.fill = GridBagConstraints.BOTH;
        return g1;
    }
}