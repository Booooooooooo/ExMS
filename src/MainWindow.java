import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class MainWindow extends JFrame {

    private Statement stat = null;
    private Connection ct = null;
    private ResultSet rs = null;
    private final String url = "jdbc:mysql://localhost/ExSM?useSSL=false&serverTimezone=GMT%2B8";
    private final String user = "root";
    private final String passwd = "wyb980401";

    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton findButton;
    private JPanel toolPanel;
    private Font font = new Font("黑体", Font.PLAIN, 18);
    private List list;
    private JTree tree;
    private DefaultMutableTreeNode[] node;
    private DefaultMutableTreeNode[] sonNodes;
    private JTabbedPane pane;


    public MainWindow(){
        super("题库管理系统");

        addButton = new JButton();
        addButton.setFont(font);
        addButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        addButton.setHorizontalTextPosition(SwingConstants.CENTER);
        addButton.setText("添加");
        addButton.setIcon(new ImageIcon("image/add.png"));
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        deleteButton = new JButton();
        deleteButton.setFont(font);
        deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteButton.setText("删除");
        deleteButton.setIcon(new ImageIcon("image/delete.png"));
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        modifyButton = new JButton();
        modifyButton.setFont(font);
        modifyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        modifyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        modifyButton.setText("修改");
        modifyButton.setIcon(new ImageIcon("image/modify.png"));
        modifyButton.setContentAreaFilled(false);
        modifyButton.setFocusPainted(false);
        modifyButton.setBorderPainted(false);
        findButton = new JButton();
        findButton.setFont(font);
        findButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        findButton.setHorizontalTextPosition(SwingConstants.CENTER);
        findButton.setText("查找");
        findButton.setIcon(new ImageIcon("image/find.png"));
        findButton.setContentAreaFilled(false);
        findButton.setFocusPainted(false);
        findButton.setBorderPainted(false);
        toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.X_AXIS));
        //toolPanel.add(Box.createHorizontalStrut(5));
        toolPanel.add(addButton);
        toolPanel.add(Box.createHorizontalStrut(5));
        toolPanel.add(deleteButton);
        toolPanel.add(Box.createHorizontalStrut(5));
        toolPanel.add(modifyButton);
        toolPanel.add(Box.createHorizontalStrut(5));
        toolPanel.add(findButton);
        toolPanel.add(Box.createHorizontalStrut(100));
        toolPanel.setBackground(null);
        toolPanel.setOpaque(false);

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("课程管理");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct = DriverManager.getConnection(url, user, passwd);
            stat = ct.createStatement();
            rs = stat.executeQuery("select count(*) from course");

            while(rs.next()){
                node = new DefaultMutableTreeNode[rs.getInt(1)];
            }

            int i = 0;
            rs = stat.executeQuery("select name from course");
            while(rs.next()){
                String name = rs.getString("name");
                node[i] = new DefaultMutableTreeNode(name);
                Statement sstat = ct.createStatement();
                ResultSet rrs = sstat.executeQuery("select count(*) from charpter, course where course.name = \"" + name + "\" and course.id = charpter.cour_id");

                while(rrs.next()){
                    sonNodes = new DefaultMutableTreeNode[rrs.getInt(1)];
                }
                rrs = sstat.executeQuery("select charpter.name from charpter, course where course.name = \"" + name + "\" and course.id = charpter.cour_id");
                int j = 0;
                while(rrs.next()){
                    sonNodes[j] = new DefaultMutableTreeNode(rrs.getString("name"));
                    node[i].add(sonNodes[j++]);
                }
                top.add(node[i++]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(rs != null){
                    rs.close();
                    rs = null;
                }
                if(stat != null){
                    stat.close();
                    stat = null;
                }
                if(ct != null){
                    ct.close();
                    ct = null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        tree = new JTree(top);
        tree.setFont(font);
        JScrollPane scroll = new JScrollPane(tree);

        JPanel panel = new JPanel();
        panel.add(toolPanel, BorderLayout.NORTH);
        panel.setBackground(null);
        panel.setOpaque(false);

        JPanel pType = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/bg.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        pType.setLayout(new BoxLayout(pType, BoxLayout.X_AXIS));
        pType.add(Box.createHorizontalStrut(5));
        pType.add(scroll);
        pType.add(Box.createHorizontalStrut(50));
        pType.add(panel);
        JPanel pEx = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/b.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        pane = new JTabbedPane();
        pane.addTab("查看题型", pType);
        pane.addTab("查看题目", pEx);
        pane.setFont(font);
        pane.setBackground(Color.white);
        add(pane);
        setSize(1000, 1000);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setVisible(true);
    }

    public static void main(String[] args){
        MainWindow mainwindow = new MainWindow();
        mainwindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}