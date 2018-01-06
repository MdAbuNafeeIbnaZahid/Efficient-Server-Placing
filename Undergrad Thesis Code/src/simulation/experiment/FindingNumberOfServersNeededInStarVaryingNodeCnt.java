package simulation.experiment;

import graph.graph_type.GraphFactory;

/**
 * Created by nafee on 1/6/18.
 */
public class FindingNumberOfServersNeededInStarVaryingNodeCnt extends FindingNumberOfServersNeeded {

    public FindingNumberOfServersNeededInStarVaryingNodeCnt(int serverRange) {
        super(serverRange);
    }

    @Override
    public String getName() {
        String ret = "";
        ret += "FindingNumberOfServersNeededInStarVaryingNodeCnt,";
        ret += "serverRange=" + serverRange + ",";
        ret += "serverDropAssumption=" + serverDropAssumption + ",";
        return ret;
    }



    @Override
    void createAndAssignGraph(double varyingParameter) {
        nodeCnt = (int)varyingParameter;
        graph = GraphFactory.getStarGraph(nodeCnt);
    }

    @Override
    String getXAxisLabel() {
        return "nodeCnt";
    }
}
