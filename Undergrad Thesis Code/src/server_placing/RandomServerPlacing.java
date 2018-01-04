package server_placing;

import graph.Graph;
import graph.graph_traversal.CheckingDistance;
import helper_util.CollectionFromIterable;
import graph.Node;
import network.Network;

import java.util.*;

/**
 * Created by nafee on 11/30/17.
 */
public class RandomServerPlacing implements ServerPlacing {

    @Override
    public void placeServers(Network network, int serverCnt) {

        assert (! network.containsServer() )
                : "network.getServerCnt() = " + network.getServerCnt() ;
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

    @Override
    public List<Node> getNodeListForServerPlacing(Graph graph, int serverRange, int minServerCntReqWithinRange) {

        if  ( graph == null )
        {
            throw new IllegalArgumentException(" graph can't be null ");
        }

        if ( serverRange < 0 )
        {
            throw new IllegalArgumentException(" server range can't be smaller than zero ");
        }

        if ( minServerCntReqWithinRange < 0 )
        {
            throw new IllegalArgumentException(" minServerCntReqWithinRange can't be smaller than zero ");
        }

        List<Node> nodeListForServerPlacing = new ArrayList<Node>();


        // Here I am making another copy of the List returned by graph;
        List<Node> nodeListOfGraph = new ArrayList<>( graph.getNodes() );
        assert nodeListOfGraph != null : nodeListOfGraph;
        assert ! nodeListOfGraph.isEmpty() : nodeListOfGraph;

        Map<Node, Integer> connectivityMap = new HashMap<Node, Integer>();
        for ( Node node : nodeListOfGraph )
        {
            connectivityMap.put(node, 0);
        }

        // This will not shuffle the true copy of graph's node list
        // Because it is a new list of nodes, not reference to graph's node list
        Collections.shuffle(nodeListOfGraph);

        List<Node> nonServerNodes = new ArrayList<>( nodeListOfGraph );
        for ( Iterator<Node> nodeIterator = nonServerNodes.iterator(); nodeIterator.hasNext(); )
        {
            Collection<Integer> serverCntListWithinRange = connectivityMap.values();
            int minServerCntWithinRange = Collections.min( serverCntListWithinRange );
            if ( minServerCntWithinRange >= minServerCntReqWithinRange )
            {
                break;
            }



            Node nodeToPlaceServer = nodeIterator.next();
            assert ! nodeToPlaceServer.hasServer() : " this nodeToPlaceServer can't have server ";

            nodeIterator.remove();
            nodeListForServerPlacing.add( nodeToPlaceServer );

            CheckingDistance checkingDistance = new CheckingDistance();
            List<Node> nodeListWithinServerRangeOfChosenNode =
                    checkingDistance.getListOfNodesWithinDistance(nodeToPlaceServer, serverRange);
            for ( Node node : nodeListWithinServerRangeOfChosenNode )
            {
                connectivityMap.merge(node, 1, Integer::sum);
            }


        }

        return nodeListForServerPlacing;
    }

    @Override
    public int getServerCntForGoodConnectivity(Graph graph, int serverRange, int minServerCntReqWithinRange) {
        List<Node> nodeListForServerPlacing = getNodeListForServerPlacing(graph, serverRange, minServerCntReqWithinRange);

        int serverCnt = nodeListForServerPlacing.size();

        return serverCnt;
    }


    @Override
    public String toString() {
        return "RandomServerPlacing";
    }
}
