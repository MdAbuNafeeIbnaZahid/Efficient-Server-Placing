package simulation.experiment;

import helper_util.MyUtil;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;
import server_placing.RandomServerPlacing;
import server_placing.ServerPlacing;
import simulation.MultiSimulation;

import java.io.File;

/**
 * Created by nafee on 12/2/17.
 */
public abstract class Experiment
{
    Integer nodeCnt;
    Integer edgeCnt;
    Integer serverCnt;
    Integer clientCnt;
    Integer serverRange;
    Integer serverDropCnt;
    ServerPlacing serverPlacing;

    JFreeChart jFreeChart;

    public Experiment(int nodeCnt)
    {
        this.nodeCnt = nodeCnt;
    }

    public Experiment(Integer nodeCnt, Integer edgeCnt,
                      Integer serverCnt,
                      Integer clientCnt, Integer serverRange,
                      Integer serverDropCnt,
                      ServerPlacing serverPlacing)
    {
        this.nodeCnt = nodeCnt;
        this.edgeCnt = edgeCnt;
        this.serverCnt = serverCnt;
        this.clientCnt = clientCnt;
        this.serverRange = serverRange;
        this.serverDropCnt = serverDropCnt;
        this.serverPlacing = serverPlacing;
    }


    public abstract String getName();
    public abstract void doExperiment();

    public void saveChartAsImage()
    {
        int width = 1280;
        int height = 960;
        File file = new File( getName() + ".png");
        try
        {
            ChartUtilities.saveChartAsPNG(file, jFreeChart, width, height);
        }
        catch (Exception e)
        {
            System.out.println("Couldn't save chart as png image");
            System.out.println(e);
        }

    }

}
