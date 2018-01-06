package simulation.experiment;

import graph.graph_type.GraphFactory;

/**
 * Created by nafee on 1/6/18.
 */
public class FindingNumberOfServersNeededInWheelVaryingNodeCnt extends FindingNumberOfServersNeeded {

    public FindingNumberOfServersNeededInWheelVaryingNodeCnt(int serverRange) {
        super(serverRange);
    }

    @Override
    public String getName() {
        String ret = "";
        ret += "FindingNumberOfServersNeededInWheelVaryingNodeCnt,";
        ret += "serverRange=" + serverRange + ",";
        ret += "serverDropAssumption=" + serverDropAssumption + ",";
        return ret;
    }

    @Override
    void createAndAssignGraph(double varyingParameter) {
        nodeCnt = (int)varyingParameter;
        assert nodeCnt >= 3 : " no. of nodes smaller than 3 in Wheel doesn't make sense ";

        graph = GraphFactory.getWheelGraph(nodeCnt);
    }

    @Override
    String getXAxisLabel() {
        return "nodeCnt";
    }
}
