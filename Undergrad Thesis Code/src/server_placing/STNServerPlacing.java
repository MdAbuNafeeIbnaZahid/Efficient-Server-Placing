package server_placing;

import graph.Graph;
import graph.Node;
import graph.graph_traversal.CheckingDistance;
import network.Network;

import java.util.*;

/**
 * Created by nafee on 12/19/17.
 */
public class STNServerPlacing implements ServerPlacing {




    double weakNodeCoverCntWeight;
    double degreeWeight;

    public STNServerPlacing(double weakNodeCoverCntWeight, double degreeWeight)
    {
        this.weakNodeCoverCntWeight = weakNodeCoverCntWeight;
        this.degreeWeight = degreeWeight;
    }



    Map<Node, Integer> connectivityMap;



    @Override
    public void placeServers(Network network, int serverCnt) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Node> getNodeListForServerPlacing(Graph graph, int serverRange, int minServerReqWithinRange)
    {


        if  ( graph == null )
        {
            throw new IllegalArgumentException(" graph can't be null ");
        }

        if ( serverRange < 0 )
        {
            throw new IllegalArgumentException(" server range can't be smaller than zero ");
        }

        if ( minServerReqWithinRange < 0 )
        {
            throw new IllegalArgumentException(" minServerReqWithinRange can't be smaller than zero ");
        }

        connectivityMap = new HashMap<>();


        List<Node> nodeListForServerPlacing = new ArrayList<Node>();


        // Here I am making another copy of the List returned by graph;
        List<Node> nodeListOfGraph = new ArrayList<>( graph.getNodes() );
        assert nodeListOfGraph != null : nodeListOfGraph;
        assert ! nodeListOfGraph.isEmpty() : nodeListOfGraph;


        for ( Node node : nodeListOfGraph )
        {
            connectivityMap.put(node, 0);
        }


        List<Node> nonServerNodes = new ArrayList<>(nodeListOfGraph);
        while ( ! nonServerNodes.isEmpty()  )
        {
            Collection<Integer> serverCntListWithinRange = connectivityMap.values();
            int minServerCntWithinRange = Collections.min( serverCntListWithinRange );
            if ( minServerCntWithinRange >= minServerReqWithinRange )
            {
                break;
            }

            Node bestNodeToPlaceServer = getBestNodeToPlaceServer(nonServerNodes, minServerCntWithinRange, serverRange);

            if  ( bestNodeToPlaceServer == null )
            {
                System.out.println("CAUTION. My algorithm find no more node remaining to place server" +
                        " that can increase connectivity");
                break;
            }

            assert bestNodeToPlaceServer != null : " bestNodeToPlaceServer can't be null ";


            nodeListForServerPlacing.add(bestNodeToPlaceServer);

            CheckingDistance checkingDistance = new CheckingDistance();
            List<Node> nodeListWithinServerRangeOfBestNode =
                    checkingDistance.getListOfNodesWithinDistance(bestNodeToPlaceServer, serverRange);
            for ( Node node : nodeListWithinServerRangeOfBestNode )
            {
                connectivityMap.merge(node, 1, Integer::sum);
            }

            assert nonServerNodes.contains(bestNodeToPlaceServer);
            nonServerNodes.remove(bestNodeToPlaceServer);
        }

        return nodeListForServerPlacing;
    }

    private Node getBestNodeToPlaceServer(List<Node> nonServerNodes, int minServerCntWithinRange, int serverRange)
    {
        assert nonServerNodes != null;
        assert ! nonServerNodes.isEmpty();

        Node bestNodeToPlaceServer = null;
        double bestScore = 0;

        for (Node node : nonServerNodes)
        {
            int weakNodeCoverCnt = getNodeCntWithinRangeWithSpecificServerConnectivity(node,
                    minServerCntWithinRange, serverRange);

            int degree = node.getDegree();

            double score = getScore(weakNodeCoverCnt, degree);

            if ( score  > bestScore )
            {
                bestScore = weakNodeCoverCnt;
                bestNodeToPlaceServer = node;
            }
        }

        return bestNodeToPlaceServer;
    }

    double getScore(int weakNodeCoverCnt, int degree )
    {
        return weakNodeCoverCnt*weakNodeCoverCntWeight + degree*degreeWeight;
    }

    private int getNodeCntWithinRangeWithSpecificServerConnectivity( Node node, int serverConnectivity, int range )
    {
        assert node != null;
        assert connectivityMap != null;
        assert ! connectivityMap.isEmpty() ;

        CheckingDistance checkingDistance = new CheckingDistance();

        List<Node> nodeListWithinRange = checkingDistance.getListOfNodesWithinDistance(node, range);

        int ret = 0;

        for (Node nodeWithinDistance : nodeListWithinRange)
        {
            assert connectivityMap.containsKey(nodeWithinDistance);

            int currentServerConnectivity = connectivityMap.get(nodeWithinDistance);
            if ( currentServerConnectivity == serverConnectivity )
            {
                ret++;
            }
        }

        return ret;
    }

    @Override
    public int getServerCntForGoodConnectivity(Graph graph, int serverRange, int minServerCntReqWithinRange) {

        List<Node> nodeListForServerPlacing = getNodeListForServerPlacing(graph, serverRange, minServerCntReqWithinRange);

        int serverCnt = nodeListForServerPlacing.size();

        return serverCnt;
    }



    @Override
    public String toString() {
        return "STNServerPlacing{" +
                "weakNodeCoverCntWeight=" + weakNodeCoverCntWeight +
                ", degreeWeight=" + degreeWeight +
                '}';
    }
}
