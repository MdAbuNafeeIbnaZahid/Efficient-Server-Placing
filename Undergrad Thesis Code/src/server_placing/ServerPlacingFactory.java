package server_placing;

/**
 * Created by nafee on 3/26/18.
 */
public class ServerPlacingFactory {
    public static RandomServerPlacing getRandomServerPlacing()
    {
        return new RandomServerPlacing();
    }

    public static STNServerPlacing getSTNServerPlacing(double weakNodeCoverCntWeight, double degreeWeight)
    {
        return new STNServerPlacing(weakNodeCoverCntWeight, degreeWeight);
    }

    public static PaperServerPlacing getPaperServerPlacing()
    {
        return new PaperServerPlacing();
    }
}
