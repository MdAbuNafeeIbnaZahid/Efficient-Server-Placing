package graph.graph_type;

import helper_util.MyUtil;

/**
 * Created by nafee on 1/4/18.
 */
public class RandomConnectedGraph extends RandomGraph {



    public RandomConnectedGraph(int nodeCnt, int edgeCnt) {

        super(nodeCnt, edgeCnt);
        if ( nodeCnt < 0 || edgeCnt < 0 || edgeCnt > MyUtil.nc2(nodeCnt) )
        {
            throw new IllegalArgumentException("nodeCnt = " + nodeCnt + ", edgeCnt = " + edgeCnt);
        }


        assert isConnected() : " Random ** Connected ** Graph can't be disconnected ";
    }
}
