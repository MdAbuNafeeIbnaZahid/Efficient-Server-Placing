package graph.graph_type;

import graph.Graph;
import graph.Node;

/**
 * Created by nafee on 1/6/18.
 */
public class CycleGraph extends Graph {




    public CycleGraph(int nodeCnt)
    {
        if ( nodeCnt < 2)
        {
            throw new IllegalArgumentException(" A cycle can't have less than two vertices ");
        }

        makeNodes(nodeCnt);
        eachNodeMakeAnEdgeWithPreviousNode();

        assert isCycle();

    }

    void eachNodeMakeAnEdgeWithPreviousNode()
    {
        for (int i = 0; i < nodeCnt; i++)
        {
            makeEdgeWithPreviousNode(i);
        }
    }

    void makeEdgeWithPreviousNode(int nodeNum)
    {
        assert (nodeNum >= 0) && (nodeNum < nodeCnt) : " invalid nodeNum ";
        int prevNodeNum = ( nodeNum - 1 + nodeCnt ) % nodeCnt;

        addEdge(prevNodeNum, nodeNum);
    }

}
