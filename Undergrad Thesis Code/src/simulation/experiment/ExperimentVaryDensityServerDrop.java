package simulation.experiment;

import helper_util.MyUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import server_placing.ServerPlacing;
import simulation.MultiSimulation;

/**
 * Created by nafee on 12/2/17.
 */
public class ExperimentVaryDensityServerDrop extends Experiment {

    public ExperimentVaryDensityServerDrop(Integer nodeCnt,
            Integer serverCnt,
            Integer clientCnt,
            Integer serverRange,
            ServerPlacing serverPlacing
    )

    {
        super(nodeCnt, null, serverCnt, clientCnt, serverRange,
               null, serverPlacing );
    }



    @Override
    public String getName() {
        String ret = "";
        ret += "nodeCnt=" + nodeCnt + ",";
        ret += "serverCnt=" + serverCnt + ",";
        ret += "clientCnt=" + clientCnt + ",";
        ret += "serverRange=" + serverRange + ",";
        ret += "serverPlacing=" + serverPlacing + ",";
        return ret;
    }

    // This method sets the jFreeChartVariable
    @Override
    public void doExperiment() {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        int simulationCnt = 10;
        for (double densityPercent = 0; densityPercent <= 100; densityPercent+=20)
        {
            System.out.println("densityPercent = " + densityPercent);
            XYSeries xySeries = new XYSeries(densityPercent);
            int edgeCnt = (int) ( densityPercent * MyUtil.nc2(nodeCnt))/100;
            for (double serverDropPercent = 0; serverDropPercent <= 100; serverDropPercent+=10)
            {
                int serverDropCnt = (int) (serverDropPercent * serverCnt) / 100;
                MultiSimulation multiSimulation = new MultiSimulation(simulationCnt,
                        nodeCnt, edgeCnt,serverCnt, clientCnt, serverPlacing,
                        serverDropCnt, serverRange );
                multiSimulation.simulate();
                double clientCntWithinServerRange = multiSimulation.getClientCntWithinServerRange();
                double clientWithinServerRangePercent = (clientCntWithinServerRange * 100) / clientCnt;

                xySeries.add(serverDropPercent, clientWithinServerRangePercent);
            }

            xySeriesCollection.addSeries( xySeries );
        }

        jFreeChart = ChartFactory.createXYLineChart(this.getName(), "serverDropPercent",
                "clientWithinServerRangePercent", xySeriesCollection, PlotOrientation.VERTICAL, true,
                true, false);

    }
}
