package graph.graph_type;

import graph.Graph;
import graph.Node;

/**
 * Created by nafee on 1/6/18.
 */
public class Cycle extends Graph {
    public Cycle(int nodeCnt)
    {
        if ( nodeCnt < 2)
        {
            throw new IllegalArgumentException(" A cycle can't have less than two vertices ");
        }

        makeNodes(nodeCnt);
        eachNodeMakeAnEdgeWithPreviousNode();

        assert edgeCnt == nodeCnt : " In cycle number of nodes must be equal to number of edges ";
        assert doAllNodesHaveTwoEdges() : " In cycle all nodes must have two edges ";
        assert isConnected() : " A cycle graph must be connected";

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

    boolean doAllNodesHaveTwoEdges() {
        for (int a = 0; a < nodeCnt; a++)
        {
            Node currentNode = nodes.get(a);
            if ( ! currentNode.doesHaveTwoEdges() )
            {
                return false;
            }
        }
        return true;
    }


}
