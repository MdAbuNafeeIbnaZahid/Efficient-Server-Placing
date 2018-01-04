package graph.graph_type;

import helper_util.Validator;

/**
 * Created by nafee on 1/4/18.
 */
public class GraphFactory
{



    public static RandomConnectedGraph getRandomConnectedGraph(int nodeCnt, int edgeCnt)
    {
        Validator.validateGraphConstructorInput(nodeCnt, edgeCnt);
        return new RandomConnectedGraph(nodeCnt, edgeCnt);
    }

    public static RandomGraph getRandomGraph(int nodeCnt, int edgeCnt)
    {
        Validator.validateGraphConstructorInput(nodeCnt, edgeCnt);
        return new RandomGraph(nodeCnt, edgeCnt);
    }

    public static Tree getTree(int nodeCnt)
    {
        Validator.validateNodeCnt(nodeCnt);
        return new Tree(nodeCnt);
    }
}
