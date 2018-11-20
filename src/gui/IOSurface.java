package gui;

import java.awt.*;

import javax.swing.*;


public class IOSurface extends JFrame{

    private static int windowWidth = 600;
    private static int windowHeight = 660;

    private JLabel titleLabel = new JLabel("肥加菲的导航仪");

    private JPanel topPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private PlotPanel centerPanel = new PlotPanel();

    JLayeredPane layeredPane = new JLayeredPane();

    // 定义新增节点的输入文本框.
    private JTextField sourceNode = new JTextField();
    private JTextField targetNode = new JTextField();

    public IOSurface(String inputDir, String outputDir) {

        // 控制按钮, 按下后开始读入新节点并开始作图.
        CtrlButton button = new CtrlButton(
                "查询最短路径", inputDir, outputDir,
                sourceNode, targetNode, centerPanel
        );
        button.setForeground(Color.RED);

        topPanel.setLayout(new BorderLayout());
//        northPanel.setBackground(Color.cyan);
//        centerPanel.setBackground(Color.lightGray);
//        southPanel.setBackground(Color.cyan);

        // 设置整个应用的图标.
        this.setIconImage(new ImageIcon("data/image/2.jpg").getImage());

        northPanel.setLayout(new FlowLayout());
        centerPanel.setLayout(new FlowLayout());
        southPanel.setLayout(new GridLayout(5, 3,20,20));

        northPanel.add(titleLabel);
        titleLabel.setFont(new Font("Arabic", Font.BOLD, 40));

        southPanel.add(new JLabel(""));
        southPanel.add(new JLabel(""));
        southPanel.add(new JLabel(""));
        JLabel sourceLabel = new JLabel("                   出发地点名称:");
        southPanel.add(sourceLabel);
//        sourceLabel.setForeground(Color.red);
//        sourceLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        southPanel.add(sourceNode);
        southPanel.add(new JLabel("(例如: 法学院)"));
        JLabel targetLabel = new JLabel("                   达到地点名称:");
        southPanel.add(targetLabel);
        southPanel.add(targetNode);
//        targetLabel.setForeground(Color.RED);
//        targetLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        southPanel.add(new JLabel("(例如: 体育场)"));
        southPanel.add(new JLabel(""));
        southPanel.add(button);
        southPanel.add(new JLabel(""));

        topPanel.add(northPanel, BorderLayout.NORTH);
        topPanel.add(centerPanel, BorderLayout.CENTER);
        topPanel.add(southPanel, BorderLayout.SOUTH);

        layeredPane.add(topPanel, JLayeredPane.DRAG_LAYER);
        topPanel.setBounds(0 , 0, windowWidth, windowHeight);

        topPanel.setOpaque(false);
        northPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        southPanel.setOpaque(false);
        sourceNode.setOpaque(false);
        targetNode.setOpaque(false);
        button.setOpaque(false);

        ImageIcon image = new ImageIcon("data/image/3.jpg");
        image.setImage(image.getImage().getScaledInstance(
                image.getIconWidth(), image.getIconHeight(), Image.SCALE_DEFAULT
        ));
        JLabel label = new JLabel(image);
        layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);
        label.setBounds(-150,-227, image.getIconWidth(), image.getIconHeight());

        this.setLayeredPane(layeredPane);
        this.setSize(windowWidth, windowHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

}
