import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Exam extends JFrame implements  ActionListener{

    private Statement stat = null;
    private Connection ct = null;
    private ResultSet rs = null;
    private final String url = "jdbc:mysql://localhost/ExSM?useSSL=false&serverTimezone=GMT%2B8";
    private final String user = "root";
    private final String passwd = "wyb980401";
    private final Font font = new Font("黑体", Font.PLAIN, 20);

    private JLabel[] label;
    private JTextField[] field;
    private JPanel[] panel;
    private int num;
    private JButton button;
    private JTextArea area;
    private String sql;
    private String cour_name;

    public Exam(String name){
        super("请输入试卷题型对应题数");
        cour_name = name;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct = DriverManager.getConnection(url, user, passwd);
            stat = ct.createStatement();
            sql = "select count(*) from type where cour_name = \"" + name  + "\"";
            rs = stat.executeQuery(sql);
            while(rs.next()){
                num = rs.getInt(1);
            }
            if(num == 0){
                JOptionPane.showMessageDialog(null, "请添加题型");
                return;
            }
            label = new JLabel[num];
            field = new JTextField[num];
            panel = new JPanel[num];

            sql = "select * from type where cour_name = \"" + name + "\"";
            rs = stat.executeQuery(sql);
            int i = 0;
            while(rs.next()){
                label[i] = new JLabel(rs.getString("name"));
                field[i] = new JTextField(20);
                label[i].setFont(font);
                field[i].setFont(font);
                panel[i] = new JPanel();
                panel[i].setLayout(new BoxLayout(panel[i], BoxLayout.X_AXIS));
                panel[i].setBackground(null);
                panel[i].setOpaque(false);
                panel[i].add(Box.createHorizontalStrut(20));
                panel[i].add(label[i]);
                panel[i].add(Box.createHorizontalStrut(20));
                panel[i].add(field[i]);
                panel[i].add(Box.createHorizontalStrut(50));
                i++;
            }

            JPanel p = new JPanel() {
                protected void paintComponent(Graphics g) {
                    ImageIcon icon = new ImageIcon("image/win.jpg");
                    Image img = icon.getImage();
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
                }
            };
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            p.add(Box.createVerticalStrut(50));
            for(i = 0; i < num; i++){
                p.add(panel[i]);
                p.add(Box.createVerticalStrut(10));
            }
            button = new JButton("生成试卷");
            button.setFont(font);
            area = new JTextArea();
            area.setFont(font);
            area.setLineWrap(true);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(400, 400));
            button.addActionListener(this);
            p.add(button);
            p.add(Box.createVerticalStrut(30));
            p.add(scroll);
            setContentPane(p);
            setSize(500, 800);
            Dimension screenSize = getToolkit().getScreenSize();
            setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
            setVisible(true);
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
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            String display = "试题：\n", ans = "\n答案：\n";
            for(int i = 0; i < num; i++){
                if(field[i].getText().equals("0") || field[i].getText().equals("")){
                    continue;
                }
                int j = 1;
                display += label[i].getText() + "\n";
                sql = "select * from exercise where type = \"" + label[i].getText() + "\" and cour_name = \"" + cour_name  + "\" order by rand() limit " + field[i].getText();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    ct = DriverManager.getConnection(url, user, passwd);
                    stat = ct.createStatement();
                    rs = stat.executeQuery(sql);

                    while(rs.next()){
                        Statement sstat = ct.createStatement();
                        sstat.executeUpdate("update exercise set times = times + 1 where id = " + rs.getInt("id"));
                        display += j + "、" + rs.getString("info") + "\n";
                        j++;
                        ans += rs.getString("ans") + "\n";
                    }
                    display += "\n";
                }catch (Exception except){
                    except.printStackTrace();
                    JOptionPane.showMessageDialog(null, "生成失败");
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
                    }catch (Exception except){
                        except.printStackTrace();
                    }
                }
            }
            area.setText(display + ans);
        }
    }

    public static void main(String[] args){
        Exam exam = new Exam("数据库");
        exam.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
