package stream;

import node.Site;
import utils.helper;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;


public class Reader {

    public static List getNodes(String filePath) {
        File file = new File(filePath);

        // 如果文件不存在, 退出程序.
        assert file.exists();

        // 新建地点对象的列表.
        List<Site> siteList = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)){

            while (scanner.hasNext()){
                String[] items = scanner.nextLine().split("\t");

                siteList.add(new Site(
                        items[0].trim(),
                        Double.valueOf(items[1].trim()),
                        Double.valueOf(items[2].trim())
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return siteList;
    }

    public static void getEdges(String filePath, List<Site> siteList) {
        File file = new File(filePath);

        // 预检测, 防止出 bug.
        assert file.exists();

        try (Scanner scanner = new Scanner(file)){

            while (scanner.hasNext()) {
                String[] items = scanner.nextLine().split("\t");

                String nameA = items[0].trim();
                String nameB = items[1].trim();

                Site nodeA = helper.queryInList(nameA, siteList);
                Site nodeB = helper.queryInList(nameB, siteList);
                if (nodeB == null || nodeA == null) {
                    System.out.println("查询节点不存在, 请检查 edge.txt 的输入格式.");
                    System.exit(0);
                }

                // 记录邻接信息.
                nodeA.addNode(nodeB);
                nodeB.addNode(nodeA);

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
}
