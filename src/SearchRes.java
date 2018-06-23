import javax.swing.*;
import java.awt.*;
import java.lang.management.PlatformLoggingMXBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SearchRes extends JFrame {
    private Statement stat = null;
    private Connection ct = null;
    private ResultSet rs = null;
    private final String url = "jdbc:mysql://localhost/ExSM?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
    private final String user = "root";
    private final String passwd = "wyb980401";
    private JTextArea area;

    public SearchRes(String sql, int index) throws Exception{
        super("查询结果");

        area = new JTextArea();
        area.setFont(new Font("黑体", Font.PLAIN, 20));
        area.setLineWrap(true);
        //area.setPreferredSize(new Dimension(400, 400));这样会显示不了滚动条
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 400));
        JPanel panel = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/win.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        panel.add(scroll);
        setContentPane(panel);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct = DriverManager.getConnection(url, user, passwd);
            stat = ct.createStatement();
            rs = stat.executeQuery(sql);

            String display = "";
            switch (index){
                case 0:
                    while(rs.next()){
                        display += "课程名：" + rs.getString("name") + "    题目数：" + rs.getInt("ex_num") + "\n\n";
                    }break;
                case 1:
                    while(rs.next()){
                        display += "课程名：" + rs.getString("cour_name") + "\n章节名：" + rs.getString("name") + "    题目数：" + rs.getInt("ex_num") + "\n\n";
                    }break;
                case 2:
                    while(rs.next()){
                        display += "课程名：" + rs.getString("cour_name") + "   题型名：" + rs.getString("name") + "\n题目数：" + rs.getInt("ex_num") + "\n\n";
                    }break;
                case 3:
                    while(rs.next()){
                        display += "题号：" + rs.getInt("id") + "    分值：" + rs.getInt("score") + "    题型：" + rs.getString("type") + "\n"
                                    + "课程名：" + rs.getString("cour_name") + "\n章节名：" + rs.getString("ch_name") + "\n题目：" + rs.getString("info")
                                    + "\n答案：" + rs.getString("ans") +  "\n\n";
                    }break;
            }
            if(display.equals("")){
                display = "无结果";
            }
            area.setText(display);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
                if (ct != null) {
                    ct.close();
                    ct = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setSize(500, 500);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setVisible(true);
    }
}
