package stream;

import graph.Dijstra;
import graph.Path;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Writer {

    public static void writeAnswer(String filePath, List<Path> pathList) {
        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file)) {

            for (Path path : pathList) {
                String sourceNode = path.getSourceNodeName();
                String targetNode = path.getTargetNodeName();

                writer.write(sourceNode + " ---> " + targetNode + ",\t");
                if (path.shortestPath < Dijstra.INF_DISTANCE_VALUE) {
                    writer.write("minimum cost is " + path.shortestPath + " for path:\t");

                    for (String siteName : path.routeList) {
                        writer.write(siteName + " ");
                    }
                } else {
                    writer.write("there is no available route.");
                }
                writer.write('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
