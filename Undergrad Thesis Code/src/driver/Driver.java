package driver;

import simulation.experiment.Experiment;
import simulation.experiment.ExperimentFactory;
import simulation.experiment.FindingNumberOfServersNeeded;

/**
 * Created by nafee on 11/29/17.
 */
public class Driver {


    public static void main(String[] args) {


        int serverRange;
        Experiment experiment;

        serverRange = 1;
        experiment = ExperimentFactory.getFindingNumberOfServersNeededInWheelVaryingNodeCnt(serverRange);
        experiment.doExperimentAndSaveChartAsImage();






        serverRange = 1;
        experiment = ExperimentFactory.getFindingNumberOfServersNeededInStarVaryingNodeCnt(serverRange);
        experiment.doExperimentAndSaveChartAsImage();


        serverRange = 5;
        experiment = ExperimentFactory.getFindingNumberOfServersNeededInCycleVaryingNodeCnt(serverRange);
        experiment.doExperimentAndSaveChartAsImage();



        serverRange = 5;
        experiment = ExperimentFactory.getFindingNumberOfServersNeededInTreeVaryingNode(serverRange);
        experiment.doExperimentAndSaveChartAsImage();





        serverRange = 2;
        for (int nodeCnt = 50; nodeCnt <= 200; nodeCnt += 25)
        {
            experiment =
                    ExperimentFactory.getFindingNumberOfServersNeededInRandomGraphVaryingDensity(nodeCnt, serverRange);
            experiment.doExperimentAndSaveChartAsImage();
        }



//        ServerPlacing serverPlacing = new RandomServerPlacing();
//        Experiment experiment = new FindingServerDropCntInRandomConnectedGraphVaVaryingDensity(
//                200, 10, 100, 1, serverPlacing
//        );
//        experiment.doExperimentAndSaveChartAsImage();





    }
}
