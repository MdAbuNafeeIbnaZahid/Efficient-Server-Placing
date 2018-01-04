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
        if ( serverRange < 0 )
        {
            throw new IllegalArgumentException(" serverRange can't be negative ");
        }

        return new FindingNumberOfServersNeededInRandomGraphVaryingDensity(nodeCnt, serverRange);
    }
}
