import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;

public class loginWin extends JFrame {
    private JLabel username;
    private JLabel pass;
    private JLabel title;
    private JTextField userText;
    private JPasswordField passText;
    private JPanel panel;
    private JPanel labelPanel;
    private JPanel textPanel;
    private Font font = new Font("黑体", Font.PLAIN, 25);
    private JButton confirmBtn;

    public loginWin(){
        super("请登录");
        ImageIcon titleIcon = new ImageIcon("image/title.png");
        title = new JLabel();
        title.setIcon(titleIcon);
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setBackground(null);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createHorizontalStrut(50));
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalStrut(50));

        username = new JLabel("用户名", JLabel.CENTER);
        username.setFont(font);
        pass = new JLabel("密码", JLabel.CENTER);
        pass.setFont(font);
        labelPanel = new JPanel();
        labelPanel.setBackground(null);
        labelPanel.setOpaque(false);
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(username);
        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(pass);

        userText = new JTextField(15);
        userText.setSize(15, 5);
        //userText.setPreferredSize(new Dimension(50, 5));
        userText.setFont(font);
        passText = new JPasswordField(15);
        passText.setSize(15, 5);
        //passText.setPreferredSize(new Dimension(50, 5));
        passText.setFont(new Font("宋体", Font.PLAIN, 25));
        textPanel = new JPanel();
        textPanel.setBackground(null);
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(Box.createVerticalStrut(15));
        textPanel.add(userText);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(passText);
        textPanel.add(Box.createVerticalStrut(15));

        panel = new JPanel();
        panel.setBackground(null);
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalStrut(50));
        panel.add(labelPanel);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(textPanel);
        panel.add(Box.createHorizontalStrut(50));

        confirmBtn = new JButton("确认登录");
        confirmBtn.setPreferredSize(new Dimension(70, 50));
        confirmBtn.setFont(font);
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(null);
        btnPanel.setOpaque(false);
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.add(Box.createHorizontalStrut(150));
        btnPanel.add(confirmBtn);
        btnPanel.add(Box.createHorizontalStrut(150));

        JPanel p = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/15240213.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        setContentPane(p);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(Box.createVerticalStrut(25));
        p.add(titlePanel);
        p.add(Box.createVerticalStrut(20));
        p.add(panel);
        p.add(Box.createVerticalStrut(20));
        p.add(btnPanel);
        p.add(Box.createVerticalStrut(15));
        setSize(500, 350);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setVisible(true);
    }

    public static void main(String args[]){
        loginWin login = new loginWin();
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
