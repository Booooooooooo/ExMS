import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton findButton;
    private JPanel toolPanel;
    private Font font = new Font("黑体", Font.PLAIN, 18);

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
        toolPanel.add(Box.createHorizontalStrut(5));
        toolPanel.add(addButton);
        toolPanel.add(Box.createHorizontalStrut(5));
        toolPanel.add(deleteButton);
        toolPanel.add(Box.createHorizontalStrut(5));
        toolPanel.add(modifyButton);
        toolPanel.add(Box.createHorizontalStrut(5));
        toolPanel.add(findButton);
        toolPanel.add(Box.createHorizontalStrut(600));
        toolPanel.setBackground(null);
        toolPanel.setOpaque(false);

        JPanel p = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("image/bg.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        p.add(toolPanel);
        setContentPane(p);
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
