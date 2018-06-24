import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.corba.se.spi.monitoring.StatisticMonitoredAttribute;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Comparator;
import java.util.Vector;

public class MainWindow extends JFrame implements ActionListener{

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
    private JButton refreshButton;
    private JButton examButton;
    private JPanel toolPanel;
    private Font font = new Font("黑体", Font.PLAIN, 18);
    private List list;
    private JTree tree;
    private JTabbedPane pane;
    private JPanel type;
    private JPanel info;
    private JTextArea typeInfo;
    private JTextArea exInfo;
    private JScrollPane scroll;
    private DefaultMutableTreeNode top;
    private JPanel p;
    private JPanel panel;
    private DefaultTreeModel dt;

    private AddWin addWin;
    private DelWin delWin;
    private ModifyWin modifyWin;
    private SearchWin searchWin;
    private Exam exam;

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
        addButton.addActionListener(this);
        deleteButton = new JButton();
        deleteButton.addActionListener(this);
        deleteButton.setFont(font);
        deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteButton.setText("删除");
        deleteButton.setIcon(new ImageIcon("image/delete.png"));
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        modifyButton = new JButton();
        modifyButton.addActionListener(this);
        modifyButton.setFont(font);
        modifyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        modifyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        modifyButton.setText("修改");
        modifyButton.setIcon(new ImageIcon("image/modify.png"));
        modifyButton.setContentAreaFilled(false);
        modifyButton.setFocusPainted(false);
        modifyButton.setBorderPainted(false);
        findButton = new JButton();
        findButton.addActionListener(this);
        findButton.setFont(font);
        findButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        findButton.setHorizontalTextPosition(SwingConstants.CENTER);
        findButton.setText("查找");
        findButton.setIcon(new ImageIcon("image/find.png"));
        findButton.setContentAreaFilled(false);
        findButton.setFocusPainted(false);
        findButton.setBorderPainted(false);
        refreshButton = new JButton();
        refreshButton.addActionListener(this);
        refreshButton.setFont(font);
        refreshButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        refreshButton.setHorizontalTextPosition(SwingConstants.CENTER);
        refreshButton.setText("刷新");
        refreshButton.setIcon(new ImageIcon("image/refresh.png"));
        refreshButton.setContentAreaFilled(false);
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        examButton = new JButton();
        examButton.addActionListener(this);
        examButton.setFont(font);
        examButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        examButton.setHorizontalTextPosition(SwingConstants.CENTER);
        examButton.setText("生成试题");
        examButton.setIcon(new ImageIcon("image/exam.png"));
        examButton.setContentAreaFilled(false);
        examButton.setFocusPainted(false);
        examButton.setBorderPainted(false);
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
        toolPanel.add(Box.createHorizontalStrut(5));
        toolPanel.add(refreshButton);
        toolPanel.add(Box.createHorizontalStrut(5));
        toolPanel.add(examButton);
        toolPanel.add(Box.createHorizontalStrut(80));
        toolPanel.setBackground(null);
        toolPanel.setOpaque(false);

        typeInfo = new JTextArea();
        typeInfo.setFont(font);
        exInfo = new JTextArea();
        exInfo.setLineWrap(true);
        //exInfo.setSize(500, 500);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(5));
        panel.add(toolPanel);
        type = new JPanel();
        type.setOpaque(false);
        type.setBackground(null);
        type.add(typeInfo);
        info = new JPanel();
        info.setBackground(null);
        info.setOpaque(false);
        JScrollPane scr = new JScrollPane(exInfo);
        scr.setPreferredSize(new Dimension(500, 750));
        exInfo.setFont(font);
        info.add(scr);
        UIManager.put("TabbedPane.contentOpaque", false);
        pane = new JTabbedPane();
        pane.addTab("查看题型", type);
        pane.addTab("查看题目", info);
        pane.setFont(font);
        pane.setPreferredSize(new Dimension(600, 800));
        panel.add(Box.createVerticalStrut(5));
        panel.add(pane);
        panel.setBackground(null);
        panel.setOpaque(false);

        DefaultMutableTreeNode[] node = null;
        DefaultMutableTreeNode[] sonNodes = null;
        top = new DefaultMutableTreeNode("课程管理");
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
                ResultSet rrs = sstat.executeQuery("select count(*) from charpter where cour_name = \"" + name + "\"");

                while(rrs.next()){
                    sonNodes = new DefaultMutableTreeNode[rrs.getInt(1)];
                }
                rrs = sstat.executeQuery("select charpter.name from charpter where cour_name = \"" + name + "\"");
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
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if(selectNode == null) return;
                Object object = selectNode.getUserObject();
                String name = object.toString();
                String sqlType, sqlEx;
                if(selectNode.isLeaf()){
                    sqlEx = "select * from exercise, charpter where exercise.cour_name = charpter.cour_name  and charpter.name = \"" + name + "\" and ch_name = \"" + name +"\"";
                    sqlType = "";
                }
                else{
                    sqlEx = "select * from exercise where cour_name = \"" + name + "\"";
                    sqlType = "select * from type where cour_name = \"" + name + "\"";
                }
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    ct = DriverManager.getConnection(url, user, passwd);
                    stat = ct.createStatement();
                    rs = stat.executeQuery(sqlEx);

                    String displayEx = "";
                    while(rs.next()){
                        displayEx += "题号：" + rs.getInt("id") + "\n题型：" + rs.getString("type")
                                + "\n建立日期:" + rs.getString("date") + "\n分值：" + rs.getInt("score")
                                + "\n抽取次数：" + rs.getInt("times") + "\n题目：" + rs.getString("info")
                                + "\n答案:" + rs.getString("ans") + "\n\n";
                    }
                    if(displayEx.equals("")){
                        displayEx = "无题目";
                    }
                    exInfo.setText(displayEx);

                    String displayType = "";
                    if(sqlType.equals("")){
                        typeInfo.setText("请选择课程");
                    }else{
                        rs = stat.executeQuery(sqlType);
                        while(rs.next()){
                            displayType += rs.getString("name") + "\t" + rs.getInt("ex_num") + "题\t\n";
                        }
                        if(displayType.equals("")){
                            displayType = "无题目";
                        }
                        typeInfo.setText(displayType);
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
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
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        tree.setFont(font);
        scroll = new JScrollPane(tree);
        dt = new DefaultTreeModel(top);

        p = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/bg.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(Box.createHorizontalStrut(5));
        p.add(scroll);
        p.add(Box.createHorizontalStrut(10));
        p.add(panel);
        setContentPane(p);
        setSize(1000, 1000);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == addButton){
            addWin = new AddWin();
        }else if(event.getSource() == deleteButton){
            delWin = new DelWin();
        }else if(event.getSource() == modifyButton){
            modifyWin = new ModifyWin();
        }else if(event.getSource() == findButton){
            searchWin = new SearchWin();
        }else if(event.getSource() == refreshButton){
            refresh();
        }else if(event.getSource() == examButton){
            DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if(selectNode == null) return;
            Object object = selectNode.getUserObject();
            String name = object.toString();
            if(selectNode.isLeaf() || name.equals("")){
                JOptionPane.showMessageDialog(null, "请选择课程");
            }else{
                exam = new Exam(name);
            }
        }
    }

    private void refresh(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct = DriverManager.getConnection(url, user, passwd);
            stat = ct.createStatement();
            rs = stat.executeQuery("select count(*) from course");

            DefaultMutableTreeNode[] node = null;
            DefaultMutableTreeNode[] sonNodes= null;
            top.removeAllChildren();
            while(rs.next()){
                node = new DefaultMutableTreeNode[rs.getInt(1)];
            }

            int i = 0;
            rs = stat.executeQuery("select name from course");
            while(rs.next()){
                String name = rs.getString("name");
                node[i] = new DefaultMutableTreeNode(name);
                Statement sstat = ct.createStatement();
                ResultSet rrs = sstat.executeQuery("select count(*) from charpter where cour_name = \"" + name + "\"");

                while(rrs.next()){
                    sonNodes = new DefaultMutableTreeNode[rrs.getInt(1)];
                }
                rrs = sstat.executeQuery("select charpter.name from charpter where cour_name = \"" + name + "\"");
                int j = 0;
                while(rrs.next()){
                    sonNodes[j] = new DefaultMutableTreeNode(rrs.getString("name"));
                    node[i].add(sonNodes[j++]);
                }
                top.add(node[i++]);
            }

            DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if(selectNode == null) return;
            Object object = selectNode.getUserObject();
            String name = object.toString();
            String sqlEx, sqlType;
            if(selectNode.isLeaf()){
                sqlEx = "select * from exercise, charpter where exercise.cour_name = charpter.cour_name  and charpter.name = \"" + name + "\" and ch_name = \"" + name +"\"";
                sqlType = "";
            }
            else{
                sqlEx = "select * from exercise where cour_name = \"" + name + "\"";
                sqlType = "select * from type where cour_name = \"" + name + "\"";
            }

            rs = stat.executeQuery(sqlEx);
            String displayEx = "";
            while(rs.next()){
                displayEx += "题号：" + rs.getInt("id") + "\n题型：" + rs.getString("type")
                        + "\n建立日期:" + rs.getString("date") + "\n分值：" + rs.getInt("score")
                        + "\n抽取次数：" + rs.getInt("times") + "\n题目：" + rs.getString("info")
                        + "\n答案:" + rs.getString("ans") + "\n\n";
            }
            if(displayEx.equals("")){
                displayEx = "无题目";
            }
            exInfo.setText(displayEx);

            String displayType = "";
            if(sqlType.equals("")){
                typeInfo.setText("请选择课程");
            }else{
                rs = stat.executeQuery(sqlType);
                while(rs.next()){
                    displayType += rs.getString("name") + "\t" + rs.getInt("ex_num") + "题\t\n";
                }
                if(displayType.equals("")){
                    displayType = "无题目";
                }
                typeInfo.setText(displayType);
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
        dt.reload();
        tree.setModel(dt);
        scroll.repaint();
        p.repaint();
    }

    public static void main(String[] args){
        MainWindow mainwindow = new MainWindow();
        mainwindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
