import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SearchWin extends JFrame implements ActionListener {

    private Statement stat = null;
    private Connection ct = null;
    private ResultSet rs = null;
    private final String url = "jdbc:mysql://localhost/ExSM?useSSL=false&serverTimezone=GMT%2B8";
    private final String user = "root";
    private final String passwd = "wyb980401";

    private JPanel coursePanel;
    private JPanel exPanel;
    private JPanel typePanel;
    private JPanel chPanel;
    private Font font = new Font("黑体", Font.PLAIN, 20);
    private JButton button;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField field;
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private JTextField field5;
    private JTextField field6;
    private JTextField field7;
    private JTextField field8;
    private JTextField field9;
    private JTextField field10;
    private JTextField field11;
    private JTextField field12;
    private JTextArea area;
    private JTextArea area1;
    private SearchRes searchRes;

    private boolean ok = false;

    public boolean isOk() {
        return ok;
    }

    public SearchWin() {
        super("查找");

        JLabel label = new JLabel("课程名：");
        field = new JTextField(20);
        field.setPreferredSize(new Dimension(20, 5));
        label.setFont(font);
        field.setFont(font);
        JPanel up = new JPanel();
        up.setBackground(null);
        up.setOpaque(false);
        up.setLayout(new BoxLayout(up, BoxLayout.X_AXIS));
        up.add(Box.createHorizontalStrut(5));
        up.add(label);
        up.add(Box.createHorizontalStrut(15));
        up.add(field);
        button = new JButton("确认");
        button.addActionListener(this);
        button.setFont(font);
        coursePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon("image/win.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.add(Box.createVerticalStrut(120));
        coursePanel.add(up);
        coursePanel.add(Box.createVerticalStrut(50));
        coursePanel.add(button);
        coursePanel.add(Box.createVerticalStrut(110));

        JLabel label1 = new JLabel("课程名:");
        JLabel label2 = new JLabel("题  型:");
        field1 = new JTextField(20);
        field2 = new JTextField(20);
        button2 = new JButton("确认");
        button2.addActionListener(this);
        label1.setFont(font);
        label2.setFont(font);
        field1.setFont(font);
        field2.setFont(font);
        button2.setFont(font);
        JPanel pup1 = new JPanel();
        pup1.setLayout(new BoxLayout(pup1, BoxLayout.X_AXIS));
        pup1.setBackground(null);
        pup1.setOpaque(false);
        pup1.add(Box.createHorizontalStrut(20));
        pup1.add(label1);
        pup1.add(Box.createHorizontalStrut(10));
        pup1.add(field1);
        JPanel pup2 = new JPanel();
        pup2.setLayout(new BoxLayout(pup2, BoxLayout.X_AXIS));
        pup2.setBackground(null);
        pup2.setOpaque(false);
        pup2.add(Box.createHorizontalStrut(20));
        pup2.add(label2);
        pup2.add(Box.createHorizontalStrut(10));
        pup2.add(field2);
        typePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon("image/win.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));
        typePanel.add(Box.createVerticalStrut(80));
        typePanel.add(pup1);
        typePanel.add(Box.createVerticalStrut(40));
        typePanel.add(pup2);
        typePanel.add(Box.createVerticalStrut(30));
        typePanel.add(button2);
        typePanel.add(Box.createVerticalStrut(80));

        JLabel label3 = new JLabel("课程名：");
        JLabel label4 = new JLabel("章节名：");
        field3 = new JTextField(20);
        field4 = new JTextField(20);
        button1 = new JButton("确认");
        button1.addActionListener(this);
        label3.setFont(font);
        label4.setFont(font);
        field3.setFont(font);
        field4.setFont(font);
        button1.setFont(font);
        JPanel pup3 = new JPanel();
        pup3.setLayout(new BoxLayout(pup3, BoxLayout.X_AXIS));
        pup3.setBackground(null);
        pup3.setOpaque(false);
        pup3.add(Box.createHorizontalStrut(20));
        pup3.add(label3);
        pup3.add(Box.createHorizontalStrut(10));
        pup3.add(field3);
        JPanel pup4 = new JPanel();
        pup4.setLayout(new BoxLayout(pup4, BoxLayout.X_AXIS));
        pup4.setBackground(null);
        pup4.setOpaque(false);
        pup4.add(Box.createHorizontalStrut(20));
        pup4.add(label4);
        pup4.add(Box.createHorizontalStrut(10));
        pup4.add(field4);
        chPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon("image/win.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        chPanel.setLayout(new BoxLayout(chPanel, BoxLayout.Y_AXIS));
        chPanel.add(Box.createVerticalStrut(80));
        chPanel.add(pup3);
        chPanel.add(Box.createVerticalStrut(40));
        chPanel.add(pup4);
        chPanel.add(Box.createVerticalStrut(30));
        chPanel.add(button1);
        chPanel.add(Box.createVerticalStrut(80));

        JLabel label8 = new JLabel("题号:");
        JLabel label9 = new JLabel("题型：");
        JLabel label10 = new JLabel("分值：");
        JLabel label11 = new JLabel("课程：");
        JLabel label12 = new JLabel("章节：");
        JLabel label13 = new JLabel("题目：");
        JLabel label14 = new JLabel("答案:");
        field8 = new JTextField(20);
        field9 = new JTextField(20);
        field10 = new JTextField(20);
        field11 = new JTextField(20);
        field12 = new JTextField(20);
        area = new JTextArea();
        area.setLineWrap(true);
        area1 = new JTextArea();
        area1.setLineWrap(true);
        button3 = new JButton("确认");
        button3.addActionListener(this);
        label8.setFont(font);
        label9.setFont(font);
        label10.setFont(font);
        label11.setFont(font);
        label12.setFont(font);
        label13.setFont(font);
        label14.setFont(font);
        field8.setFont(font);
        field9.setFont(font);
        field10.setFont(font);
        field11.setFont(font);
        field12.setFont(font);
        area.setFont(font);
        area.setPreferredSize(new Dimension(20, 50));
        area1.setFont(font);
        area1.setPreferredSize(new Dimension(20, 50));
        JScrollPane scroll1 = new JScrollPane(area);
        JScrollPane scroll2 = new JScrollPane(area1);
        button3.setFont(font);
        JPanel left = new JPanel();
        left.setBackground(null);
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(label8);
        left.add(Box.createVerticalStrut(15));
        left.add(label9);
        left.add(Box.createVerticalStrut(15));
        left.add(label10);
        left.add(Box.createVerticalStrut(15));
        left.add(label11);
        left.add(Box.createVerticalStrut(15));
        left.add(label12);
        left.add(Box.createVerticalStrut(15));
        left.add(label13);
        left.add(Box.createVerticalStrut(20));
        left.add(label14);
        left.add(Box.createVerticalStrut(25));
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBackground(null);
        right.setOpaque(false);
        right.add(field8);
        right.add(Box.createVerticalStrut(10));
        right.add(field9);
        right.add(Box.createVerticalStrut(10));
        right.add(field10);
        right.add(Box.createVerticalStrut(10));
        right.add(field11);
        right.add(Box.createVerticalStrut(10));
        right.add(field12);
        right.add(Box.createVerticalStrut(10));
        right.add(scroll1);
        right.add(Box.createVerticalStrut(10));
        right.add(scroll2);
        JPanel eup = new JPanel();
        eup.setBackground(null);
        eup.setOpaque(false);
        eup.setLayout(new BoxLayout(eup, BoxLayout.X_AXIS));
        eup.add(Box.createHorizontalStrut(20));
        eup.add(left);
        eup.add(Box.createHorizontalStrut(10));
        eup.add(right);
        eup.add(Box.createHorizontalStrut(20));
        button3 = new JButton("确认");
        button3.addActionListener(this);
        button3.setFont(font);
        exPanel = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/win.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        exPanel.setLayout(new BoxLayout(exPanel, BoxLayout.Y_AXIS));
        exPanel.add(Box.createVerticalStrut(25));
        exPanel.add(eup);
        exPanel.add(Box.createVerticalStrut(5));
        exPanel.add(button3);
        exPanel.add(Box.createVerticalStrut(5));


        JTabbedPane pane = new JTabbedPane();
        pane.addTab("查找课程", coursePanel);
        pane.addTab("查找章节", chPanel);
        pane.addTab("查找题型", typePanel);
        pane.addTab("查找题目", exPanel);
        pane.setFont(font);
        pane.setPreferredSize(new Dimension(450, 400));

        JPanel p = new JPanel() {
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon("image/win.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        p.add(pane);
        setContentPane(p);
        setSize(500, 500);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        System.out.println(event.getSource().toString());
        String sql = "";

        if (event.getSource() == button) {
            //课程
            String course = field.getText();
            //System.out.println(0);
            sql = "select * from course where name = \"" + course + "\"";
            try{
                searchRes = new SearchRes(sql, 0);
                clear();
            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "查询失败");
            }
        } else if (event.getSource() == button1) {
            //章节
            String course = field3.getText();
            String ch = field4.getText();
            //System.out.println(1);
            if(!course.equals("") && !ch.equals("")){
                sql = "select * from charpter where name = \"" + ch + "\" and cour_name =  \"" + course + "\"";
            }else if(!course.equals("")){
                sql = "select * from charpter where cour_name = \"" + course + "\"";
            }else{
                sql = "select * from charpter where name = \"" + ch + "\"";
            }
            try{
                searchRes = new SearchRes(sql, 1);
                clear();
            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "查询失败");
            }
        } else if (event.getSource() == button2) {
            //题型
            // System.out.println(2);
            String course = field1.getText();
            String type = field2.getText();
            //System.out.println(course);
            //System.out.println(type);
            if(!course.equals("") && !type.equals("")){
                sql = "select * from type where name = \"" + type + "\" and cour_name =  \"" + course + "\"";
            }else if(!course.equals("")){
                sql = "select * from type where cour_name = \"" + course + "\"";
            }else{
                sql = "select * from type where name = \"" + type + "\"";
            }
            try{
                searchRes = new SearchRes(sql, 2);
                clear();
            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "查询失败");
            }
        } else if (event.getSource() == button3) {
            //题目
            //System.out.println(3);
            String id = field8.getText();
            String type = field9.getText();
            String score = field10.getText();
            String course = field11.getText();
            String ch = field12.getText();
            String info = area.getText();
            String ans = area1.getText();
            boolean first = true;
            sql = "select * from exercise where ";
            if (!id.equals("")) {
                if(first){
                    sql += " id = " + id;
                    first = false;
                }
            }
            if(!type.equals("")){
                if(first){
                    first = false;
                }else{
                    sql += " and ";
                }
                sql += " type = \"" + type + "\"";
            }
            if(!score.equals("")){
                if(first){
                    first = false;
                }else{
                    sql += " and ";
                }
                sql += " score = " + score;
            }
            if(!course.equals("")){
                if(first){
                    first = false;
                }else{
                    sql += " and ";
                }
                sql += " cour_name = \"" + course + "\"";
            }
            if(!ch.equals("")){
                if(first){
                    first = false;
                }else{
                    sql += " and ";
                }
                sql += " ch_name = \"" + ch + "\"";
            }
            if(!info.equals("")){
                if(first){
                    first = false;
                }else{
                    sql += " and ";
                }
                sql += " info = \"" + info + "\"";
            }
            if(!ans.equals("")){
                if(first){
                    first = false;
                }else{
                    sql += " and ";
                }
                sql += " ans = \"" + ans + "\"";
            }
            try{
                searchRes = new SearchRes(sql, 3);
                clear();
            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "查询失败");
            }
        }

    }

    private void clear(){
        JOptionPane.showMessageDialog(null, "查询成功");
        ok = true;
        field.setText("");
        field1.setText("");
        field2.setText("");
        field3.setText("");
        field4.setText("");
        field8.setText("");
        field9.setText("");
        field10.setText("");
        field11.setText("");
        field12.setText("");
        area.setText("");
        area1.setText("");
    }

    public static void main(String[] args) {
        SearchWin searchWin = new SearchWin();
        searchWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}