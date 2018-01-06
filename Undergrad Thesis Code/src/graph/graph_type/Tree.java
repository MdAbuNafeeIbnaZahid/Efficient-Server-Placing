package graph.graph_type;

import graph.Graph;
import helper_util.Validator;

import java.util.Random;

/**
 * Created by nafee on 1/4/18.
 */
public class Tree extends Graph {

    Tree(int nodeCnt)
    {
        Validator.validateNodeCnt(nodeCnt);

        makeNodes(nodeCnt);
        for (int i = 1; i < nodeCnt; i++)
        {
            addEdgeWithAPreviousNode(i);
        }

        assert this.isConnected() : " Tree must be connected ";
        assert ( this.edgeCnt == this.nodeCnt-1 ) : " tree edgeCount must be one less than nodeCount ";

    }

    void addEdgeWithAPreviousNode(int nodeNum)
    {
        assert nodeNum > 0 && nodeNum < nodeCnt : " nodeNum = " + nodeNum;
        Random random = new Random();
        int prevNode = random.nextInt(nodeNum);
        addEdge(prevNode, nodeNum);
    }

}
