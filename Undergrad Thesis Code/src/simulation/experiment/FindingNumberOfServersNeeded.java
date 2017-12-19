package simulation.experiment;

import deep_copy.UnoptimizedDeepCopy;
import graph.Graph;
import graph.Node;
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
public class FindingNumberOfServersNeeded extends Experiment {

    int simulationCnt = 5;

    public FindingNumberOfServersNeeded(int nodeCnt)
    {
        super(nodeCnt);
        if ( nodeCnt <= 0 )
        {
            throw new IllegalArgumentException("nodeCnt can't be zero");
        }
    }

    @Override
    public String getName() {
        String ret = "";
        ret += "node=" + nodeCnt + ",";
        return ret;
    }

    private XYSeries getXySeries(ServerPlacing serverPlacing)
    {
        assert serverPlacing != null;
        System.out.println(serverPlacing);
        XYSeries xySeries = new XYSeries( serverPlacing.toString() );
        assert serverPlacing != null;
        for (double densityPercent = 20; densityPercent <= 100; densityPercent += 10)
        {
            System.out.println( "densityPercent = " + densityPercent);

            int edgeCnt = MyUtil.getEdgeCnt(nodeCnt, densityPercent);
            //System.out.println( "edgeCnt = " + edgeCnt );
            Graph graph = new Graph(nodeCnt, edgeCnt);
            //assert graph.isConnected();

            List<Integer> serverReqList = new ArrayList<Integer>();
            for ( int a = 0; a < simulationCnt; a++ )
            {
                Graph copyGraph = (Graph) UnoptimizedDeepCopy.copy(graph);
                int currentServerReq = serverPlacing.getServerCntForGoodConnectivity(copyGraph, 1, 3);
                assert currentServerReq >= 0;

                serverReqList.add( currentServerReq );
            }


            Double avgServerReq = serverReqList.stream().mapToDouble(val -> val).average().getAsDouble();

            System.out.println("serverReq = " + avgServerReq);


            xySeries.add(densityPercent, avgServerReq);
        }

        return xySeries;
    }

    @Override
    public void doExperiment() {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();

        ServerPlacing randomServerPlacing = new RandomServerPlacing();
        XYSeries randomServerPlacingCurve = getXySeries(randomServerPlacing);
        xySeriesCollection.addSeries(randomServerPlacingCurve);


        ServerPlacing STNServerPlacing = new STNServerPlacing();
        XYSeries STNServerPlacingCurve = getXySeries(STNServerPlacing);
        xySeriesCollection.addSeries(STNServerPlacingCurve);

        jFreeChart = ChartFactory.createXYLineChart(this.getName(), "densityPercent",
                "serversNeeded", xySeriesCollection, PlotOrientation.VERTICAL, true,
                true, false);

    }
}
