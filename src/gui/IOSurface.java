package gui;

import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;


public class IOSurface extends JFrame{

    private static int windowWidth = 600;
    private static int windowHeight = 660;

    private JLabel titleLabel = new JLabel("肥加菲的导航仪");

    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private PlotPanel centerPanel = new PlotPanel();

    // 定义新增节点的输入文本框.
    private JTextField sourceNode = new JTextField();
    private JTextField targetNode = new JTextField();

    public IOSurface(String inputDir, String outputDir) {

        // 控制按钮, 按下后开始读入新节点并开始作图.
        CtrlButton button = new CtrlButton(
                "查询最短路径", inputDir, outputDir,
                sourceNode, targetNode, centerPanel
        );

        this.setLayout(new BorderLayout());
//        northPanel.setBackground(Color.cyan);
//        centerPanel.setBackground(Color.lightGray);
//        southPanel.setBackground(Color.cyan);

        // 设置整个应用的图标.
        this.setIconImage(new ImageIcon("data/image/garfield.jpg").getImage());

        northPanel.setLayout(new FlowLayout());
        centerPanel.setLayout(new FlowLayout());
        southPanel.setLayout(new GridLayout(5, 3,20,20));

        northPanel.add(titleLabel);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        southPanel.add(new JLabel(""));
        southPanel.add(new JLabel(""));
        southPanel.add(new JLabel(""));
        southPanel.add(new JLabel("  出发地点名称:"));
        southPanel.add(sourceNode);
        southPanel.add(new JLabel("(例如: 法学院)"));
        southPanel.add(new JLabel("  达到地点名称:"));
        southPanel.add(targetNode);
        southPanel.add(new JLabel("(例如: 体育场)"));
        southPanel.add(new JLabel(""));
        southPanel.add(button);
        southPanel.add(new JLabel(""));

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

        this.setSize(windowWidth, windowHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

}


