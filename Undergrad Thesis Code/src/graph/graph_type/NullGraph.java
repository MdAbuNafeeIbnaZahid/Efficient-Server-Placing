package graph.graph_type;

import graph.Graph;
import helper_util.Validator;

/**
 * Created by nafee on 1/6/18.
 */
public class NullGraph extends Graph
{
    public static final int NEIGHBOR_COUNT_IN_NULL_GRAPH = 0;

    public NullGraph(int nodeCnt)
    {
        Validator.validateNodeCnt(nodeCnt);
        makeNodes(nodeCnt);

        assert this.nodeCnt == nodeCnt : " Null graph is not constructed with specified number of" +
                " nodes ";
        assert this.edgeCnt == 0 : "Null graph can't contain any edge";
        assert doAllNodesHaveSpecificEdgeCount(NEIGHBOR_COUNT_IN_NULL_GRAPH) : "Null graph can't contain any edge" ;
    }
}
