package stream;

import graph.Path;
import graph.Site;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import utils.Helper;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;


public class Reader {

    public static List<Site> getNodes(String filePath) {
        File file = new File(filePath);

        // 如果文件不存在, 退出程序.
        assert file.exists();

        // 新建地点对象的列表.
        List<Site> siteList = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)){

            while (scanner.hasNext()){
                String[] items = scanner.nextLine().split("\\s+");

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
                String[] items = scanner.nextLine().split("\\s+");

                String nameA = items[0].trim();
                String nameB = items[1].trim();

                Site nodeA = Helper.queryInList(nameA, siteList);
                Site nodeB = Helper.queryInList(nameB, siteList);
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

    public static List<Path> getQueries(String filePath) {
        File file = new File(filePath);
        List<Path> pathList = new ArrayList<>();

        // 预检测, 防 bug.
        assert file.exists();

        try (Scanner scanner = new Scanner(file)){

            while (scanner.hasNext()) {
                String[] items = scanner.nextLine().split("\\s+");
                pathList.add(new Path(items[0], items[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return pathList;
    }

    public static List<String> getSolution(String filePath) {
        File file = new File(filePath);

        List<String> bestPath = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)){

            String[] sites = scanner.nextLine().split(":")[1].split("\\s+");

            for (String site : sites) {
                if (!(site.equals("") || site.equals(" ") || site.equals("\n"))) {
                    bestPath.add(site);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return bestPath;
    }
}
