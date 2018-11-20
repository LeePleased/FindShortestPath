package graph;

import java.util.List;

public class Path {

    private final String sourceNodeName;
    private final String targetNodeName;

    public double shortestPath = Dijstra.INF_DISTANCE_VALUE;
    public List<String> routeList = null;

    public Path(String sourceNodeName, String targetNodeName) {
        this.sourceNodeName = sourceNodeName;
        this.targetNodeName = targetNodeName;
    }

    public String getSourceNodeName() {
        return sourceNodeName;
    }

    public String getTargetNodeName() {
        return targetNodeName;
    }
}
