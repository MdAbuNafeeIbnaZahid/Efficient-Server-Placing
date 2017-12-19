package server_placing;

import graph.Graph;
import graph.Node;
import network.Network;

import java.util.List;

/**
 * Created by nafee on 11/30/17.
 */
public interface ServerPlacing
{
    void placeServers(Network network, int serverCnt);

    List<Node> getNodeListForServerPlacing(Graph graph, int serverRange, int minServerCntReqWithinRange);

    int getServerCntForGoodConnectivity(Graph graph, int serverRange, int minServerCntReqWithinRange);
}
