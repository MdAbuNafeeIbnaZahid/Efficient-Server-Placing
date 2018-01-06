package simulation.experiment;

import helper_util.Validator;

/**
 * Created by nafee on 1/4/18.
 */
public class ExperimentFactory
{
    public static FindingNumberOfServersNeededInRandomGraphVaryingDensity
        getFindingNumberOfServersNeededInRandomGraphVaryingDensity(int nodeCnt, int serverRange)
    {
        Validator.validateNodeCnt(nodeCnt);
        Validator.validateServerRange(serverRange);

        return new FindingNumberOfServersNeededInRandomGraphVaryingDensity(nodeCnt, serverRange);
    }

    public static FindingNumberOfServersNeededInTreeVaryingNodeCnt
        getFindingNumberOfServersNeededInTreeVaryingNode(int serverRange)
    {
        Validator.validateServerRange(serverRange);

        return new FindingNumberOfServersNeededInTreeVaryingNodeCnt(serverRange);
    }

    public static FindingNumberOfServersNeededInCycleVaryingNodeCnt
        getFindingNumberOfServersNeededInCycleVaryingNodeCnt(int serverRange)
    {
        Validator.validateServerRange(serverRange);

        return new FindingNumberOfServersNeededInCycleVaryingNodeCnt(serverRange);
    }

    public static FindingNumberOfServersNeededInStarVaryingNodeCnt
        getFindingNumberOfServersNeededInStarVaryingNodeCnt(int serverRange)
    {
        Validator.validateServerRange(serverRange);

        return new FindingNumberOfServersNeededInStarVaryingNodeCnt(serverRange);
    }

    public static FindingNumberOfServersNeededInWheelVaryingNodeCnt
        getFindingNumberOfServersNeededInWheelVaryingNodeCnt(int serverRange)
    {
        Validator.validateServerRange(serverRange);

        return new FindingNumberOfServersNeededInWheelVaryingNodeCnt(serverRange);
    }
}
