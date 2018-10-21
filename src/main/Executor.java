package main;

import graph.Dijstra;
import graph.Site;
import graph.Path;
import stream.Reader;
import stream.Writer;

import java.util.List;


public class Executor {

    /**
     * 主程序的入口函数.
     */
    public static void main(String[] args){

        // 读入文件的路径.
        String readFileDir = "data/input";
        // 写入文件的路径.
        String writeFileDir = "data/output";

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
