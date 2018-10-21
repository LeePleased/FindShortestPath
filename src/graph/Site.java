package node;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;


public class Site {

    // 地点的标识名.
    private final String name;

    // 地点的坐标位置.
    private final double coordinateX, coordinateY;

    // 该节点的邻接节点集合.
    private Set<Site> adjacencyNodes = new HashSet<>();

    public Site(String name, double coordinateX, double coordinateY){
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public String getName() {
        return name;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    /**
     * 向节点的邻接集合中增加一个节点.
     */
    public void addNode(Site node) {
        this.adjacencyNodes.add(node);
    }

    /**
     * 返回该节点的邻接节点的集合.
     */
    public Set<Site> getAdjacencyNodes() {
        return Collections.unmodifiableSet(this.adjacencyNodes);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Site))
            return false;

        // 只有同样是 Site 才可能是等价的.
        return this.name.equals(((Site)o).getName());
    }
}
