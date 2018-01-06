package simulation.experiment;

import graph.graph_type.GraphFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;

/**
 * Created by nafee on 1/6/18.
 */
public class FindingNumberOfServersNeededInTreeVaryingNodeCnt extends FindingNumberOfServersNeeded
{


    public FindingNumberOfServersNeededInTreeVaryingNodeCnt(int serverRange) {
        super(serverRange);
    }

    @Override
    public String getName() {
        String ret = "";
        ret += "FindingNumberOfServersNeededInTreeVaryingNodeCnt,";
        ret += "serverRange=" + serverRange + ",";
        ret += "serverDropAssumption=" + serverDropAssumption + ",";
        return ret;
    }

    @Override
    void createJFreeChart() {
        jFreeChart = ChartFactory.createXYLineChart(this.getName(), "nodeCnt",
                "serversNeeded", xySeriesCollection, PlotOrientation.VERTICAL, true,
                true, false);
    }

    @Override
    void createAndAssignGraph(double varyingParameter) {
        nodeCnt = (int)varyingParameter;
        graph = GraphFactory.getTree(nodeCnt);
    }
}
