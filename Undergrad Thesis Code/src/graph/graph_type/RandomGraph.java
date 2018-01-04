package graph.graph_type;

import graph.Graph;
import graph.Node;
import helper_util.MyUtil;

import java.util.ArrayList;

/**
 * Created by nafee on 1/4/18.
 */
public class RandomGraph extends Graph {

    public RandomGraph(int nodeCnt, int edgeCnt)
    {
        if (nodeCnt < 0 || edgeCnt < 0)
        {
            throw new IllegalArgumentException("nodeCnt = " + nodeCnt +
                    ", edgeCnt = " + edgeCnt);
        }

        if ( edgeCnt > MyUtil.nc2(nodeCnt))
        {
            throw new IllegalArgumentException();
        }

        this.nodeCnt = nodeCnt;
        nodes = new ArrayList<Node>();

        // Here we are assuming 0 based indexing of vertices
        for (int a = 0; a < nodeCnt; a++ )
        {
            Node node = new Node(a);
            nodes.add(node);
        }

        this.edgeCnt = edgeCnt;
        makeRandomEdges(edgeCnt);
    }

}
