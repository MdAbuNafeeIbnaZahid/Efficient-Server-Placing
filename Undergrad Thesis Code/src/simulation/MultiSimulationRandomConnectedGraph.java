package simulation;

import deep_copy.UnoptimizedDeepCopy;
import graph.Graph;
import graph.graph_type.GraphFactory;
import network.Network;
import server_placing.ServerPlacing;

/**
 * Created by nafee on 12/1/17.
 */
public class MultiSimulationRandomConnectedGraph
{
    int simulationCnt;

    int nodeCnt;
    int edgeCnt;
    int serverCnt;
    int clientCnt;

    ServerPlacing serverPlacing;

    int serverDropCnt;

    int serverRange;

    // The method simulate sets the variable clientCntWithinServerRange
    private double clientCntWithinServerRange = -1;

    public double getClientCntWithinServerRange()
    {
        if  (clientCntWithinServerRange < 0)
        {
            throw new RuntimeException(" Multisimulation is not simulated yet ");
        }
        return clientCntWithinServerRange;
    }


    public MultiSimulationRandomConnectedGraph(int simulationCnt, int nodeCnt, int edgeCnt, int serverCnt, int clientCnt,
                                               ServerPlacing serverPlacing, int serverDropCnt, int serverRange)

    {
        this.simulationCnt = simulationCnt;
        this.nodeCnt = nodeCnt;
        this.edgeCnt = edgeCnt;
        this.serverCnt = serverCnt;
        this.clientCnt = clientCnt;
        this.serverPlacing = serverPlacing;
        this.serverDropCnt = serverDropCnt;
        this.serverRange = serverRange;
    }





    // The method simulate sets the variable clientCntWithinServerRange
    public void simulate()
    {
        double clientCntWithinServerRangeSum = 0;
        for (int a = 1; a <= simulationCnt; a++)
        {
            Graph randomConnectedGraph = GraphFactory.getRandomConnectedGraph(nodeCnt, edgeCnt);
            Network network = new Network(randomConnectedGraph, serverCnt, clientCnt, serverPlacing);
            SingleSimulation singleSimulation = new SingleSimulation(network, serverDropCnt, serverRange );
            singleSimulation.simulate();

            int curClientCntWithinServerRange = singleSimulation.getClientCntWithinServerRange();
            assert (curClientCntWithinServerRange >= 0 && curClientCntWithinServerRange <= clientCnt ) :
                    curClientCntWithinServerRange;

            clientCntWithinServerRangeSum += curClientCntWithinServerRange;
        }

        this.clientCntWithinServerRange = clientCntWithinServerRangeSum / simulationCnt;
    }
}
