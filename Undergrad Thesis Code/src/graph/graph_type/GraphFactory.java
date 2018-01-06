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

        Tree tree = new Tree(nodeCnt);
        assert tree.isTree();

        return new Tree(nodeCnt);
    }

    public static CycleGraph getCycleGraph(int nodeCnt)
    {
        if ( nodeCnt < 2 )
        {
            throw new IllegalArgumentException(" A cycle can't have less than 2 nodes ");
        }

        CycleGraph cycleGraph = new CycleGraph(nodeCnt);
        assert cycleGraph.isCycle();

        return new CycleGraph(nodeCnt);
    }

    private static NullGraph getNullGraph(int nodeCnt)
    {
        Validator.validateNodeCnt(nodeCnt);
        NullGraph nullGraph = new NullGraph(nodeCnt);

        assert nullGraph.getNodeCnt() ==  nodeCnt;
        assert nullGraph.getEdgeCnt() == 0;

        return nullGraph;
    }

    public static Graph getStarGraph(int nodeCnt)
    {
        if ( nodeCnt < 3 )
        {
            throw new IllegalArgumentException("Star graph less than 3 nodes doesn't make sense");
        }

        NullGraph nullGraph = new NullGraph(nodeCnt-1);
        assert nullGraph.getNodeCnt() == nodeCnt-1;
        assert nullGraph.getEdgeCnt() == 0;



        Graph starGraph = nullGraph.getNewGraphAddingAnotherNodeAndEdgesWithAllPreviousNodes();
        assert starGraph.getEdgeCnt() == nodeCnt-1;

        return starGraph;
    }

    public static Graph getWheelGraph(int nodeCnt)
    {
        if ( nodeCnt < 3 )
        {
            throw new IllegalArgumentException("Star graph less than 3 nodes doesn't make sense");
        }

        CycleGraph cycleGraph = new CycleGraph(nodeCnt-1);
        assert cycleGraph.isCycle();

        Graph wheelGraph = cycleGraph.getNewGraphAddingAnotherNodeAndEdgesWithAllPreviousNodes();
        return wheelGraph;

    }
}
