import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DelWin extends JFrame implements ActionListener {

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
    private JTextArea area;
    private JTextArea area1;

    private boolean ok = false;

    public boolean isOk() {
        return ok;
    }

    public DelWin(){
        super("删除");

        JLabel label = new JLabel("删除的课程名：");
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
        chPanel = new JPanel(){
            protected void paintComponent(Graphics g){
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

        JLabel label10 = new JLabel("题号:");
        field5 = new JTextField(20);
        button3 = new JButton("确认");
        button3.addActionListener(this);
        label10.setFont(font);
        field5.setFont(font);
        JScrollPane scroll1 = new JScrollPane(area);
        button3.setFont(font);
        JPanel eup = new JPanel();
        eup.setBackground(null);
        eup.setOpaque(false);
        eup.setLayout(new BoxLayout(eup, BoxLayout.X_AXIS));
        eup.add(Box.createHorizontalStrut(5));
        eup.add(label10);
        eup.add(Box.createHorizontalStrut(15));
        eup.add(field5);
        exPanel = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/win.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        exPanel.setLayout(new BoxLayout(exPanel, BoxLayout.Y_AXIS));
        exPanel.add(Box.createVerticalStrut(120));
        exPanel.add(eup);
        exPanel.add(Box.createVerticalStrut(50));
        exPanel.add(button3);
        exPanel.add(Box.createVerticalStrut(110));

        JTabbedPane pane = new JTabbedPane();
        pane.addTab("删除课程", coursePanel);
        pane.addTab("删除章节", chPanel);
        pane.addTab("删除题型", typePanel);
        pane.addTab("删除题目", exPanel);
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
            //System.out.println(0);
            sql = "delete from course where name = \"" + course + "\"";
        }else if(event.getSource() == button1){
            //章节
            String course = field3.getText();
            String ch = field4.getText();
            //System.out.println(1);
            sql = "delete from charpter where name = \"" + ch + "\" and cour_name =  \"" + course + "\"";
        }else if(event.getSource() == button2){
            //题型
            // System.out.println(2);
            String course = field1.getText();
            String type = field2.getText();
            //System.out.println(course);
            //System.out.println(type);
            sql = "delete from type where name = \"" + type + "\" and cour_name =  \"" + course + "\"";
            sqlty = "delete from exercise where type = \"" + type + "\" and cour_name  = \"" + course + "\"";
        }else if(event.getSource() == button3){
            //题目
            //System.out.println(3);
            String id = "";
            id = field5.getText();
            if(!id.equals("")){
                sql = "delete from exercise where id = " + id;
                sqlco = "update course set ex_num = ex_num - 1 where name in (select cour_name from exercise where id = " + id +")";
                sqlch = "update charpter set ex_num = ex_num - 1 where name in (select ch_name from exercise where id = " + id +")";
                sqlty = "update type set ex_num = ex_num - 1 where name in (select type from exercise where id = " +id + ") and cour_name in (select cour_name from exercise where id = " + id +")";
            }
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct = DriverManager.getConnection(url, user, passwd);
            stat = ct.createStatement();

            stat.executeUpdate(sql);
            if(!sqlco.equals("")){
                stat.executeUpdate(sqlco);
                stat.executeUpdate(sqlch);
                stat.executeUpdate(sqlty);
            }
            else if(!sqlty.equals("")){
                stat.executeUpdate(sqlty);
            }
            JOptionPane.showMessageDialog(this, "删除成功");
            ok = true;
            field.setText("");
            field1.setText("");
            field2.setText("");
            field3.setText("");
            field4.setText("");
            field5.setText("");
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "删除失败");
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
    public static void main(String[] args){
        DelWin delWin = new DelWin();
        delWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
