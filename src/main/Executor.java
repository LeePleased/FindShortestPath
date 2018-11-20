package main;

import gui.IOSurface;

import java.awt.Font;
import java.awt.GraphicsEnvironment;


public class Executor {

    // 读入文件的路径.
    private final static String readFileDir = "data/input";
    // 写入文件的路径.
    private final static String writeFileDir = "data/output";

    /**
     * 主程序 main 的入口.
     */
    public static void main(String[] args) {

//        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        Font[] fonts = e.getAllFonts();
//        for (Font font : fonts) {
//            String fontName = font.getFontName();
//            System.out.println(fontName);
//        }

        new IOSurface(readFileDir, writeFileDir);
    }
}
