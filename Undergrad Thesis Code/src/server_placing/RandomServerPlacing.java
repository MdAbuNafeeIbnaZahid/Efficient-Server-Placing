package server_placing;

import cutomized_util.CollectionFromIterable;
import graph.Node;
import network.Network;
import network.server.Server;

import java.util.Collections;
import java.util.List;

/**
 * Created by nafee on 11/30/17.
 */
public class RandomServerPlacing implements ServerPlacing {

    @Override
    public void placeServers(Network network, int serverCnt) {

        assert ! network.containsServer() : network.getServerCnt() ;
        assert serverCnt >= 0 : serverCnt ;
        assert serverCnt <= network.getNodeCnt() : "serverCnt = " + serverCnt
                + ", network.getNodeCnt() = " + network.getNodeCnt();

        List<Node> nodes = CollectionFromIterable.<Node>makeList(network.getNodes());
        Collections.shuffle( nodes );
        nodes = nodes.subList(0, serverCnt);

        for (Node node : nodes)
        {
            network.addNewServerInNetwork(node);
        }
    }


}
