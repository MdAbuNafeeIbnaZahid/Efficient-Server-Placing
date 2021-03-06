package simulation.experiment;

import graph.graph_type.GraphFactory;

/**
 * Created by nafee on 1/6/18.
 */
public class FindingNumberOfServersNeededInCycleVaryingNodeCnt extends FindingNumberOfServersNeeded {

    public FindingNumberOfServersNeededInCycleVaryingNodeCnt(int serverRange) {
        super(serverRange);
    }

    @Override
    public String getName() {
        String ret = "";
        ret += "FindingNumberOfServersNeededInCycleVaryingNodeCnt,";
        ret += "serverRange=" + serverRange + ",";
        ret += "serverDropAssumption=" + serverDropAssumption + ",";
        return ret;
    }


    @Override
    void createAndAssignGraph(double varyingParameter) {
        nodeCnt = (int)varyingParameter;
        graph = GraphFactory.getCycleGraph(nodeCnt);
    }

    @Override
    String getXAxisLabel() {
        return "nodeCnt";
    }
}
