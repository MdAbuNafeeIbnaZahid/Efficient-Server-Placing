package driver;

import simulation.experiment.Experiment;
import simulation.experiment.FindingNumberOfServersNeededInRandomGraphVaryingDensity;

/**
 * Created by nafee on 11/29/17.
 */
public class Driver {


    public static void main(String[] args) {


        for (int nodeCnt = 50; nodeCnt <= 200; nodeCnt += 25)
        {
            Experiment experiment = new FindingNumberOfServersNeededInRandomGraphVaryingDensity(nodeCnt, 1);
            experiment.doExperiment();
            experiment.saveChartAsImage();
        }



//        ServerPlacing serverPlacing = new RandomServerPlacing();
//        Experiment experiment = new ExperimentVaryDensityServerDropInRandomConnectedGraph(
//                200, 10, 100, 1, serverPlacing
//        );
//        experiment.doExperiment();
//        experiment.saveChartAsImage();






//        int nodeCnt = 200;
//        int serverCnt = 10;
//        int clientCnt = 100;
//        int serverRange = 1;
//        ServerPlacing serverPlacing = new RandomServerPlacing();
//
//        System.out.println("nodeCnt = " + nodeCnt);
//        System.out.println("serverCnt = " + serverCnt);
//        System.out.println("clientCnt = " + clientCnt);
//        System.out.println("serverRange = " + serverRange);
//
//        System.out.println();
//
//        for (int densityPercent = 0; densityPercent <= 20; densityPercent += 1 )
//        {
//            int edgeCnt = (densityPercent * MyUtil.nc2(nodeCnt) )/100;
//            System.out.println("densityPercent = " + densityPercent);
//            System.out.println();
//
//            for (int serverDropPercent = 10; serverDropPercent <= 100; serverDropPercent+=10)
//            {
//                int serverDropCnt = (serverDropPercent * serverCnt)/100;
//                MultiSimulationRandomConnectedGraph multiSimulation = new MultiSimulationRandomConnectedGraph(5,
//                        nodeCnt, edgeCnt, serverCnt, clientCnt, serverPlacing, serverDropCnt,
//                        serverRange);
//                multiSimulation.simulate();
//                double clientCntWithinServerRange = multiSimulation.getClientCntWithinServerRange();
//                System.out.println(serverDropPercent + " " + clientCntWithinServerRange);
//            }
//
//            System.out.println();
//
//        }
    }
}
