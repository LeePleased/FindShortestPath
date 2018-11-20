package utils;

import graph.Path;
import graph.Site;
import graph.Dijstra;
import stream.Reader;
import stream.Writer;

import java.util.List;

public class Helper {

    /**
     * 按标识名在一个列表中查询.
     */
    public static Site queryInList(String name, List<Site> siteList) {
        for(Site site : siteList) {
            if (site.getName().equals(name)) {
                return site;
            }
        }

        // 如果查询不存在, 则返回 null.
        return null;
    }

    /* 读取 input 的数据并将算法输出打印在 output 中. */
    public static void sloveQuery(String readFileDir, String writeFileDir) {

        // 读入 node.txt 中的节点数据.
        String nodeFilePath = readFileDir + "/node.txt";
        List<Site> siteList = Reader.getNodes(nodeFilePath);

        // 读入 edge.txt 中的边数据.
        String edgeFilePath = readFileDir + "/edge.txt";
        Reader.getEdges(edgeFilePath, siteList);

        // 读入查询信息.
        String queryFilePath = readFileDir + "/query.txt";
        List<Path> pathList = Reader.getQueries(queryFilePath);

        // 回答查询信息.
        for (Path path : pathList) {
            String sourceNode = path.getSourceNodeName();
            String targetNode = path.getTargetNodeName();

            // 实例化图对象.
            Dijstra dijstra = new Dijstra(sourceNode, siteList);
            dijstra.buildGraph();

            // 最短路径算法回答查询.
            path.shortestPath = dijstra.queryMinDistance(targetNode);
            if (path.shortestPath < Dijstra.INF_DISTANCE_VALUE) {
                path.routeList = dijstra.queryBestRoute(targetNode);
            }
        }

        // 将答案写出.
        String answerFilePath = writeFileDir + "/suggest.txt";
        Writer.writeAnswer(answerFilePath, pathList);
    }

}
