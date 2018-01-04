package graph.graph_type;

import graph.Graph;

/**
 * Created by nafee on 1/4/18.
 */
public class GraphFactory
{
    public static RandomConnectedGraph getRandomConnectedGraph(int nodeCnt, int edgeCnt)
    {
        return new RandomConnectedGraph(nodeCnt, edgeCnt);
    }

    public static RandomGraph getRandomGraph(int nodeCnt, int edgeCnt)
    {
        return new RandomGraph(nodeCnt, edgeCnt);
    }
}
