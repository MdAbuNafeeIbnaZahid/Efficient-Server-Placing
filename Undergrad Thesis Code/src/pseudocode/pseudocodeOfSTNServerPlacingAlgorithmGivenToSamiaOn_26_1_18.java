


package pseudocode;

/**
 * Created by nafee on 1/26/18.
 */
public class pseudocodeOfSTNServerPlacingAlgorithmGivenToSamiaOn_26_1_18 {


    placeServer(Graph graph, int serverRange, int serverDropAssumption)
    {
        For each node n in graph
            serverCountWithinRange[n] = 0;

        serverNodes = {}
        nonServerNodes = {All nodes of graph}

        while( There is such a non server node n such that
            serverCountWithinRange[n] <= serverDropAssumption)
        {
            placeAnotherServer();
        }
    }



    placeAnotherServer()
    {
        leastConnectedNonServerNodes = {All non server nodes n such that it has the lowest
        serverCountWithinRange[n]  };


        nodeToPlaceNewServer = getNodeToPlaceNewServer();


        place the server in nodeToPlaceNewServer;
        increment the value of serverCountWithinRange value of all nodes covered by the
            newly placed server;
        remove nodeToPlaceNewServer from the set nonServerNodes;
        add nodeToPlaceNewServer to the set serverNodes;
    }

    Node getNodeToPlaceNewServer()
    {
        Node ret = null;
        int maxNumberOfLeastConnectedNonServerNodesWithinServerRange = 0;

        for each node n in nonServerNodes
        {
            run a bfs from n;
            int numberOfLeastConnectedNonServerNodesWithinRange =
                    count how many least Connected non Server Nodes are within server
            Range of n;

            if (numberOfLeastConnectedNonServerNodesWithinRange >=
                    maxNumberOfLeastConnectedNonServerNodesWithinServerRange) {
                ret = n;
                maxNumberOfLeastConnectedNonServerNodesWithinServerRange =
                        numberOfLeastConnectedNonServerNodesWithinRange;
            }
        }


        return  ret;
    }



}
