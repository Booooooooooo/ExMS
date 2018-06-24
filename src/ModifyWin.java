import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.BasicPermission;
import java.security.acl.Acl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.jar.JarFile;

public class ModifyWin extends JFrame implements ActionListener {

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

    private boolean ok = false;

    public boolean isOk() {
        return ok;
    }

    public ModifyWin(){
        super("修改");

        JLabel label = new JLabel("原课程名：");
        JLabel label1 = new JLabel("新课程名：");
        field = new JTextField(20);
        field1 = new JTextField(20);
        field.setPreferredSize(new Dimension(20, 5));
        field1.setPreferredSize(new Dimension(20, 5));
        label.setFont(font);
        label1.setFont(font);
        field.setFont(font);
        field1.setFont(font);
        JPanel up = new JPanel();
        up.setBackground(null);
        up.setOpaque(false);
        up.setLayout(new BoxLayout(up, BoxLayout.X_AXIS));
        up.add(Box.createHorizontalStrut(5));
        up.add(label);
        up.add(Box.createHorizontalStrut(15));
        up.add(field);
        JPanel upd = new JPanel();
        upd.setBackground(null);
        upd.setOpaque(false);
        upd.setLayout(new BoxLayout(upd, BoxLayout.X_AXIS));
        upd.add(Box.createHorizontalStrut(5));
        upd.add(label1);
        upd.add(Box.createHorizontalStrut(15));
        upd.add(field1);
        button = new JButton("确认");
        button.addActionListener(this);
        button.setFont(font);
        coursePanel = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/win.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.add(Box.createVerticalStrut(120));
        coursePanel.add(up);
        coursePanel.add(Box.createVerticalStrut(30));
        coursePanel.add(upd);
        coursePanel.add(Box.createVerticalStrut(50));
        coursePanel.add(button);
        coursePanel.add(Box.createVerticalStrut(100));

        JLabel label3 = new JLabel("课 程 名：");
        JLabel label2 = new JLabel("原题型名：");
        JLabel label4 = new JLabel("新题型名：");
        field3 = new JTextField(20);
        field2 = new JTextField(20);
        field4 = new JTextField(20);
        button2 = new JButton("确认");
        button2.addActionListener(this);
        label3.setFont(font);
        label2.setFont(font);
        label4.setFont(font);
        field3.setFont(font);
        field2.setFont(font);
        field4.setFont(font);
        button2.setFont(font);
        JPanel pup1 = new JPanel();
        pup1.setLayout(new BoxLayout(pup1, BoxLayout.X_AXIS));
        pup1.setBackground(null);
        pup1.setOpaque(false);
        pup1.add(Box.createHorizontalStrut(20));
        pup1.add(label3);
        pup1.add(Box.createHorizontalStrut(10));
        pup1.add(field3);
        JPanel pup2 = new JPanel();
        pup2.setLayout(new BoxLayout(pup2, BoxLayout.X_AXIS));
        pup2.setBackground(null);
        pup2.setOpaque(false);
        pup2.add(Box.createHorizontalStrut(20));
        pup2.add(label2);
        pup2.add(Box.createHorizontalStrut(10));
        pup2.add(field2);
        JPanel pup3 = new JPanel();
        pup3.setLayout(new BoxLayout(pup3, BoxLayout.X_AXIS));
        pup3.setBackground(null);
        pup3.setOpaque(false);
        pup3.add(Box.createHorizontalStrut(20));
        pup3.add(label4);
        pup3.add(Box.createHorizontalStrut(10));
        pup3.add(field4);
        typePanel = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/win.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));
        typePanel.add(Box.createVerticalStrut(80));
        typePanel.add(pup1);
        typePanel.add(Box.createVerticalStrut(30));
        typePanel.add(pup2);
        typePanel.add(Box.createVerticalStrut(30));
        typePanel.add(pup3);
        typePanel.add(Box.createVerticalStrut(40));
        typePanel.add(button2);
        typePanel.add(Box.createVerticalStrut(50));

        JLabel label5 = new JLabel("课 程 名：");
        JLabel label6 = new JLabel("原章节名：");
        JLabel label7 = new JLabel("新章节名：");
        field5 = new JTextField(20);
        field6 = new JTextField(20);
        field7 = new JTextField(20);
        button1 = new JButton("确认");
        button1.addActionListener(this);
        label5.setFont(font);
        label6.setFont(font);
        label7.setFont(font);
        field5.setFont(font);
        field6.setFont(font);
        field7.setFont(font);
        button1.setFont(font);
        JPanel pup4 = new JPanel();
        pup4.setLayout(new BoxLayout(pup4, BoxLayout.X_AXIS));
        pup4.setBackground(null);
        pup4.setOpaque(false);
        pup4.add(Box.createHorizontalStrut(20));
        pup4.add(label5);
        pup4.add(Box.createHorizontalStrut(10));
        pup4.add(field5);
        JPanel pup5 = new JPanel();
        pup5.setLayout(new BoxLayout(pup5, BoxLayout.X_AXIS));
        pup5.setBackground(null);
        pup5.setOpaque(false);
        pup5.add(Box.createHorizontalStrut(20));
        pup5.add(label6);
        pup5.add(Box.createHorizontalStrut(10));
        pup5.add(field6);
        JPanel pup6 = new JPanel();
        pup6.setBackground(null);
        pup6.setOpaque(false);
        pup6.setLayout(new BoxLayout(pup6, BoxLayout.X_AXIS));
        pup6.add(Box.createHorizontalStrut(20));
        pup6.add(label7);
        pup6.add(Box.createHorizontalStrut(10));
        pup6.add(field7);
        chPanel = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/win.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        chPanel.setLayout(new BoxLayout(chPanel, BoxLayout.Y_AXIS));
        chPanel.add(Box.createVerticalStrut(80));
        chPanel.add(pup4);
        chPanel.add(Box.createVerticalStrut(30));
        chPanel.add(pup5);
        chPanel.add(Box.createVerticalStrut(30));
        chPanel.add(pup6);
        chPanel.add(Box.createVerticalStrut(40));
        chPanel.add(button1);
        chPanel.add(Box.createVerticalStrut(50));

        JLabel label8 = new JLabel("原题目题号:");
        JLabel label9 = new JLabel("     题型：");
        JLabel label10 = new JLabel("     分值：");
        JLabel label11 = new JLabel("     课程：");
        JLabel label12 = new JLabel("     章节：");
        JLabel label13 = new JLabel("     题目：");
        JLabel label14 = new JLabel("     答案:");
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
        pane.addTab("修改课程", coursePanel);
        pane.addTab("修改章节", chPanel);
        pane.addTab("修改题型", typePanel);
        pane.addTab("修改题目", exPanel);
        pane.setFont(font);
        pane.setPreferredSize(new Dimension(450, 400));

        JPanel p = new JPanel(){
            protected void paintComponent(Graphics g){
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
    public void actionPerformed(ActionEvent event){
        System.out.println(event.getSource().toString());
        String sql = "";
        String sqlco = "";
        String sqlch = "";
        String sqlty = "";
        if(event.getSource() == button){
            //课程
            String course = field.getText();
            String newcourse = field1.getText();
            //System.out.println(0);
            sql = "update course set name = \"" + newcourse + "\" where name = \"" + course + "\"";
        }else if(event.getSource() == button1){
            //章节
            String course = field5.getText();
            String ch = field6.getText();
            String newch = field7.getText();
            //System.out.println(1);
            sql = "update charpter set name = \"" + newch + "\" where cour_name =  \"" + course + "\" and name = \"" + ch + "\"";
        }else if(event.getSource() == button2){
            //题型
            // System.out.println(2);
            String course = field3.getText();
            String type = field2.getText();
            String newtype = field4.getText();
            //System.out.println(course);
            //System.out.println(type);
            sql = "update type set name = \"" + newtype + "\" where cour_name =  \"" + course + "\" and name = \"" + type + "\"";
            sqlty = "update exercise set type = \"" + newtype + "\" where cour_name  = \"" + course + "\" and type = \"" + type + "\"";
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct = DriverManager.getConnection(url, user, passwd);
            stat = ct.createStatement();
            if(!sql.equals("")) {
                stat.executeUpdate(sql);
                clear();
            }
            if(!sqlty.equals("")){
                stat.executeUpdate(sqlty);
                clear();
            }
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "修改失败");
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
        if(event.getSource() == button3){
            //题目
            //System.out.println(3);
            String id = field8.getText();
            String type = field9.getText();
            String score = field10.getText();
            String course = field11.getText();
            String ch = field12.getText();
            String info = area.getText();
            String ans = area1.getText();
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                ct = DriverManager.getConnection(url, user, passwd);
                stat = ct.createStatement();

                if(!type.equals("")){
                    sql = "update type set ex_num = ex_num - 1 where name in (select type from exercise where id = " + id + ") and cour_name in (select cour_name from exercise where id = " + id + ")";
                    stat.executeUpdate(sql);
                    sql = "update type set ex_num = ex_num + 1 where name = \"" + type + "\" and cour_name in (select cour_name from exercise where id = " + id + ")";
                    stat.executeUpdate(sql);
                    sql = "update exercise set type = \"" + type +"\" where id = " + id;
                    stat.executeUpdate(sql);
                }
                if(!score.equals("")){
                    sql = "update exercise set score = " + score + " where id = " + id;
                    stat.executeUpdate(sql);
                }
                if(!course.equals("")){
                    sql = "update course set ex_num = ex_num - 1 where name in (select cour_name from exercise where id = " +  id + ")";
                    stat.executeUpdate(sql);
                    sql = "update course set ex_num = ex_num + 1 where name = \"" + course + "\"";
                    stat.executeUpdate(sql);
                    sql = "update charpter set ex_num = ex_num - 1 where cour_name in (select cour_name from exercise where id = " + id + ") and name in (select ch_name from exercise where id = " + id + ")";
                    stat.executeUpdate(sql);
                    sql = "update charpter set ex_num = ex_num + 1 where cour_name = \"" + course + "\" and name in (select ch_name from exercise where id = " + id + ")";
                    stat.executeUpdate(sql);
                    sql = "update exercise set cour_name = \"" + course + "\" where id = " + id;
                    stat.executeUpdate(sql);
                }
                if(!ch.equals("")){
                    sql = "update charpter set ex_num = ex_num - 1 where name in (select ch_name from exercise where id = " + id + ") and cour_name in(select cour_name from exercise where id = " + id +")";
                    stat.executeUpdate(sql);
                    sql = "update charpter set ex_num = ex_num + 1 where name = \"" +  ch + "\" and cour_name in (select cour_name from exercise where id = " + id + ")";
                    stat.executeUpdate(sql);
                    sql = "update exercise set ch_name = \"" + ch + "\" where id = " + id;
                    stat.executeUpdate(sql);
                }
                if(!info.equals("")){
                    sql = "update exercise set info = \"" + info + "\" where id = " + id;
                    stat.executeUpdate(sql);
                }
                if(!ans.equals("")){
                    sql = "update exercise set ans = \"" + ans + "\" where id = " + id;
                    stat.executeUpdate(sql);
                }
                clear();
            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "修改失败");
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
        }

    }

    private void clear(){
        JOptionPane.showMessageDialog(this, "修改成功");
        ok = true;
        field.setText("");
        field1.setText("");
        field2.setText("");
        field3.setText("");
        field4.setText("");
        field5.setText("");
        field6.setText("");
        field7.setText("");
        field8.setText("");
        field9.setText("");
        field10.setText("");
        field11.setText("");
        field12.setText("");
        area.setText("");
        area1.setText("");
    }

    public static void main(String[] args){
        ModifyWin modifyWin = new ModifyWin();
        modifyWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
