package driver;

import simulation.experiment.Experiment;
import simulation.experiment.ExperimentFactory;
import simulation.experiment.FindingNumberOfServersNeeded;

/**
 * Created by nafee on 11/29/17.
 */
public class Driver {


    public static void main(String[] args) {

        int serverRange = 5;
        Experiment experiment = ExperimentFactory.getFindingNumberOfServersNeededInCycleVaryingNodeCnt(5);
        experiment.doExperimentAndSaveChartAsImage();

//        int serverRange = 5;
//        Experiment experiment = ExperimentFactory.getFindingNumberOfServersNeededInTreeVaryingNode(serverRange);
//        experiment.doExperimentAndSaveChartAsImage();





//        int serverRange = 1;
//        for (int nodeCnt = 50; nodeCnt <= 200; nodeCnt += 25)
//        {
//            Experiment experiment =
//                    ExperimentFactory.getFindingNumberOfServersNeededInRandomGraphVaryingDensity(nodeCnt, serverRange);
//            experiment.doExperimentAndSaveChartAsImage();
//        }



//        ServerPlacing serverPlacing = new RandomServerPlacing();
//        Experiment experiment = new FindingServerDropCntInRandomConnectedGraphVaVaryingDensity(
//                200, 10, 100, 1, serverPlacing
//        );
//        experiment.doExperimentAndSaveChartAsImage();





    }
}
