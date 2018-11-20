package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import utils.Helper;
import stream.Reader;
import stream.Writer;

import java.util.List;


public class CtrlButton extends JButton {

    // 两个输入文本框.
    private JTextField sourceText;
    private JTextField targetText;

    // 绘制地址的画板 panel.
    private PlotPanel plotPanel;

    public CtrlButton(String text, String inputDir, String outputDir,
                      JTextField sourceText, JTextField targetText, PlotPanel plotPanel) {
        super(text);
        this.sourceText = sourceText;
        this.targetText = targetText;
        this.plotPanel = plotPanel;

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 读入查询信息.
                String sourceName = sourceText.getText().trim();
                String targetName = targetText.getText().trim();

                try {
                    // 将查询信息写入文件.
                    Writer.writeQuery(inputDir + "/query.txt", sourceName, targetName);

                    // 调用算法查询最佳路径.
                    Helper.sloveQuery(inputDir, outputDir);

                    // 读入算法输出文件, 得到路径.
                    List<String> bestPath = Reader.getSolution("data/output/suggest.txt");

                    // 重新绘制原图和目标路径.
                    plotPanel.setSiteList();
                    plotPanel.setNameList(bestPath);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    plotPanel.setSiteList();
                }

                // 清空查询信息.
                sourceText.setText("");
                targetText.setText("");
            }
        });
    }

}
