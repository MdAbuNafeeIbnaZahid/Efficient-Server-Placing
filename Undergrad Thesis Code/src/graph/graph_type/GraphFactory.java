package graph.graph_type;

import graph.Graph;
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

    public static Cycle getCycle(int nodeCnt)
    {
        if ( nodeCnt < 2 )
        {
            throw new IllegalArgumentException(" A cycle can't have less than 2 nodes ");
        }

        return new Cycle(nodeCnt);
    }

}
