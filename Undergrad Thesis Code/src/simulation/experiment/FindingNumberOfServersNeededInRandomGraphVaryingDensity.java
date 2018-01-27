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
        String ret = "FindingNumberOfServersNeededInRandomGraphVaryingDensity";
        ret += "node=" + nodeCnt + ",";
        ret += "serverRange=" + serverRange + ",";
        ret += "serverDropAssumption=" + serverDropAssumption + ",";
        return ret;
    }



    @Override
    void createAndAssignGraph(double varyingParameter) {
        densityPercent = varyingParameter;
        System.out.println( "densityPercent = " + densityPercent);
        edgeCnt = MyUtil.getEdgeCnt(nodeCnt, densityPercent);
        graph = GraphFactory.getRandomGraph(nodeCnt, edgeCnt);

    }

    @Override
    String getXAxisLabel() {
        return "density";
    }
}
