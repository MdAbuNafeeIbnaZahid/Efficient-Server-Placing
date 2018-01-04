package simulation.experiment;

import deep_copy.UnoptimizedDeepCopy;
import graph.Graph;
import graph.Node;
import graph.graph_type.GraphFactory;
import helper_util.MyUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import server_placing.RandomServerPlacing;
import server_placing.STNServerPlacing;
import server_placing.ServerPlacing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nafee on 12/19/17.
 */
public abstract class FindingNumberOfServersNeeded extends Experiment {

    int simulationCnt = 5;
    int serverDropAssumption = 2;
    XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
    double densityPercent;
    Graph graph;

    public FindingNumberOfServersNeeded(int nodeCnt, int serverRange)
    {
        super(nodeCnt, serverRange);
        if ( nodeCnt <= 0 )
        {
            throw new IllegalArgumentException("nodeCnt can't be zero");
        }
    }



    private XYSeries getXySeries(ServerPlacing serverPlacing)
    {
        assert serverPlacing != null;
        System.out.println(serverPlacing);


        XYSeries xySeries = new XYSeries( serverPlacing.toString() );


        for (double varyingParameter = 20; varyingParameter <= 100; varyingParameter += 10)
        {

            createAndAssignGraph(varyingParameter);


            List<Integer> serverReqList = new ArrayList<Integer>();
            for ( int a = 0; a < simulationCnt; a++ )
            {
                Graph copyGraph = (Graph) UnoptimizedDeepCopy.copy(graph);
                int currentServerReq = serverPlacing.getServerCntForGoodConnectivity(copyGraph, serverRange, serverDropAssumption+1);
                assert currentServerReq >= 0;

                serverReqList.add( currentServerReq );
            }


            Double avgServerReq = serverReqList.stream().mapToDouble(val -> val).average().getAsDouble();

            System.out.println("serverReq = " + avgServerReq);


            xySeries.add(varyingParameter, avgServerReq);
        }

        return xySeries;
    }

    @Override
    public void doExperiment() {

       generateAndAddCurveInXYSeriesCollection(new RandomServerPlacing());
       generateAndAddCurveInXYSeriesCollection(new STNServerPlacing());

        createJFreeChart();
    }

    void generateAndAddCurveInXYSeriesCollection( ServerPlacing serverPlacing )
    {
        XYSeries xySeries = getXySeries(serverPlacing);
        xySeriesCollection.addSeries(xySeries);
    }

    abstract void createJFreeChart();

    abstract void createAndAssignGraph(double varyingParameter);
}
