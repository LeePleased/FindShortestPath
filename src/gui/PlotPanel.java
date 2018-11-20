package gui;

import java.util.List;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JPanel;

import graph.Site;
import stream.Reader;


public class PlotPanel extends JPanel {

    // 坐标增幅大小.
    private int paddingWidth = 160;     // 纵向偏移.
    private int paddingHeight = 295;    // 横向偏移.
    private int scaleWidth = 100;       // 纵向偏移.
    private int scaleHeight = 160;      // 横向尺锁.

    // 地图中所有的节点列表.
    private List<Site> siteList;

    // 回答查询路径中的节点名称.
    private List<String> nameList;
    // 是否绘制回复节点路径.
    private Boolean ifPlotQuery = false;

    public PlotPanel() {
        setSiteList();
    }

    public void setSiteList() {
        this.siteList = Reader.getNodes("data/input/node.txt");
        Reader.getEdges("data/input/edge.txt", this.siteList);
        repaint();  // 重新绘制地图.
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
        ifPlotQuery = true;
        repaint();  // 重新绘制地图.
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Site siteA : siteList) {
            if (!ifPlotQuery)
                plotNode(g, siteA, true, 15);
            else
                plotNode(g, siteA, true, 10);

            for (Site siteB : siteA.getAdjacencyNodes()) {
                plotEdge(g, siteA, siteB, true);
            }
        }

        if (ifPlotQuery) {
            for (int i = 0; i < nameList.size() - 1; i += 1) {
                Site sourceSite = getSiteByName(nameList.get(i));
                Site targetSite = getSiteByName(nameList.get(i + 1));

                plotEdge(g, sourceSite, targetSite, false);
            }
        }

        ifPlotQuery = false;
    }

    private void plotNode(Graphics g, Site site, Boolean flag, int fontSize) {
        int siteX = resizeWidth(site.getCoordinateX()) + paddingWidth;
        int siteY = resizeHeight(site.getCoordinateY()) + paddingHeight;

        ((Graphics2D) g).setStroke(new BasicStroke(2.0f));
        if (flag) {
            g.setColor(Color.BLUE);
            g.setFont(new Font("Dialog", Font.ITALIC, fontSize));
        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Dialog", Font.BOLD, fontSize));
        }
        g.drawString(site.getName(), siteY, siteX);
    }

    private void plotEdge(Graphics g, Site a, Site b, Boolean flag) {
        int aX = resizeWidth(a.getCoordinateX()) + paddingWidth;
        int aY = resizeHeight(a.getCoordinateY()) + paddingHeight;
        int bX = resizeWidth(b.getCoordinateX()) + paddingWidth;
        int bY = resizeHeight(b.getCoordinateY()) + paddingHeight;

        if (flag) {
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(1.0f));
        } else {
            g.setColor(Color.YELLOW);
            ((Graphics2D) g).setStroke(new BasicStroke(4.0f));
        }

        g.drawLine(aY, aX, bY, bX);

        if (!flag) {
            plotNode(g, a, false, 23);
            plotNode(g, b, false, 23);
        }
    }

    private int resizeWidth(double coordinate) {
        return (int)(scaleWidth * coordinate);
    }

    private int resizeHeight(double coordinate) {
        return (int)(scaleHeight * coordinate);
    }

    private Site getSiteByName(String name) {
        for (Site site : this.siteList) {
            if (site.getName().equals(name)) {
                return site;
            }
        }
        return null;
    }
}
