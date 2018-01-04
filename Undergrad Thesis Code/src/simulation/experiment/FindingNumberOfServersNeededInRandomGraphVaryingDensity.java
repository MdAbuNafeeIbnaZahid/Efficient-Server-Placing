package simulation.experiment;

import graph.graph_type.GraphFactory;
import helper_util.MyUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;

/**
 * Created by nafee on 1/4/18.
 */
public class FindingNumberOfServersNeededInRandomGraphVaryingDensity extends FindingNumberOfServersNeeded
{

    public FindingNumberOfServersNeededInRandomGraphVaryingDensity(int nodeCnt, int serverRange) {
        super(nodeCnt, serverRange);
    }

    @Override
    public String getName() {
        String ret = "";
        ret += "node=" + nodeCnt + ",";
        ret += "serverRange=" + serverRange + ",";
        ret += "serverDropAssumption=" + serverDropAssumption + ",";
        return ret;
    }

    void createJFreeChart()
    {
        jFreeChart = ChartFactory.createXYLineChart(this.getName(), "densityPercent",
                "serversNeeded", xySeriesCollection, PlotOrientation.VERTICAL, true,
                true, false);

    }

    @Override
    void createAndAssignGraph(double varyingParameter) {
        densityPercent = varyingParameter;
        System.out.println( "densityPercent = " + densityPercent);
        edgeCnt = MyUtil.getEdgeCnt(nodeCnt, densityPercent);
        graph = GraphFactory.getRandomGraph(nodeCnt, edgeCnt);

    }
}
