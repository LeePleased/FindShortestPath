package graph;

import utils.Measure;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Dijstra {

    // 定义无穷距离.
    public final static double INF_DISTANCE_VALUE = 1e9;

    // 定义源节点和图节点信息.
    private final String sourceNodeName;
    private final List<Site> siteList;

    // 定义节点的总数.
    private final int totalNodeNumber;
    private final Integer sourceIndex;

    // 定义距离向量和回溯向量.
    private Double[] minDistanceVector;
    private Integer[] parentNodeVector;


    public Dijstra(String sourceNodeName, final List<Site> siteList) {
        this.sourceNodeName = sourceNodeName;
        this.siteList = siteList;

        this.totalNodeNumber = siteList.size();
        this.sourceIndex = nameToIndex(this.sourceNodeName);

        minDistanceVector = new Double[this.totalNodeNumber];
        for (int index = 0; index < this.totalNodeNumber; index += 1) {
            minDistanceVector[index] = INF_DISTANCE_VALUE;
        }

        parentNodeVector = new Integer[this.totalNodeNumber];
        for (int index = 0; index < this.totalNodeNumber; index += 1) {
            // -1 表示没有可回溯的父节点.
            parentNodeVector[index] = -1;
        }
    }

    public String getSourceNodeName() {
        return sourceNodeName;
    }

    /**
     * 用 Dijstra 算法构建最短路径图.
     */
    public void buildGraph() {

        // 源节点可回溯至本身.
        this.parentNodeVector[sourceIndex] = sourceIndex;
        // 源节点的损失为 0.
        this.minDistanceVector[sourceIndex] = 0.0;

        // 记录是否访问到某个节点过.
        Boolean[] visitedVector = new Boolean[this.totalNodeNumber];
        for (int index = 0; index < this.totalNodeNumber; index += 1) {
            visitedVector[index] = false;
        }

        // 迭代优化网络权重.
        for (int epoch = 0; epoch <= totalNodeNumber; epoch += 1) {
            int currentBestIndex = -1;
            double currentMinDistance = Dijstra.INF_DISTANCE_VALUE;

            for (int index = 0; index < this.totalNodeNumber; index += 1) {
                // 如果没有访问过而且比当前最短路径还小.
                if(!visitedVector[index] && this.minDistanceVector[index] < currentMinDistance) {
                    currentBestIndex = index;
                    currentMinDistance = this.minDistanceVector[index];
                }
            }

            // 如果网络已经收敛则退出.
            if (currentBestIndex == -1) {
                break;
            }
            visitedVector[currentBestIndex] = Boolean.TRUE;

            // 松弛更新其他节点.
            Site currentSite = siteList.get(currentBestIndex);
            for (Site site : currentSite.getAdjacencyNodes()) {
                double distance = Measure.normDistance(currentSite, site);
                Integer index = nameToIndex(site.getName());

                if (index == null) {
                    System.out.println("程序逻辑错误, 已退出.");
                    System.exit(0);
                }

                if (distance + minDistanceVector[currentBestIndex] < minDistanceVector[index]) {
                    minDistanceVector[index] = distance + minDistanceVector[currentBestIndex];
                    // 更新回溯节点.
                    this.parentNodeVector[index] = currentBestIndex;
                }
            }
        }

    }

    /**
     * 查询最短距离.
     */
    public Double queryMinDistance(String name) {
        Integer nodeIndex = nameToIndex(name);

        if (nodeIndex == null) {
            System.out.println("查询节点 " + name + " 不存在, 请检查 query.txt 文件.");
            return null;
        }

        return this.minDistanceVector[nodeIndex];
    }

    /**
     * 查询最短路径.
     */
    public List<String> queryBestRoute(String name) {

        // 初始化回溯路径.
        List<String> reverseNamePath = new ArrayList<>();
        reverseNamePath.add(name);

        Integer currentIndex = nameToIndex(name);
        if (currentIndex == null) {
            System.out.println("查询节点 " + name + " 不存在, 请检查 query.txt 文件.");
            return null;
        }

        while (!currentIndex.equals(this.sourceIndex)) {
            int nextIndex = this.parentNodeVector[currentIndex];

            // 查询节点不连通.
            if (nextIndex == -1){
                return null;
            }

            reverseNamePath.add(this.siteList.get(nextIndex).getName());
            currentIndex = nextIndex;
        }

        // 将序列逆转.
        Collections.reverse(reverseNamePath);
        return reverseNamePath;
    }

    /**
     * 获取给定标识名节点在 siteList 中的序号.
     */
    private Integer nameToIndex(String name) {
        for (int index = 0; index < this.totalNodeNumber; index += 1) {
            if (this.siteList.get(index).getName().equals(name)) {
                return index;
            }
        }

        // 如果不存在, 则返回 null.
        return null;
    }

}
