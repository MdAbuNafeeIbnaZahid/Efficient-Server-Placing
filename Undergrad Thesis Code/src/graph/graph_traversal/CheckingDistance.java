package graph.graph_traversal;

import graph.Node;

import java.util.*;

/**
 * Created by nafee on 11/29/17.
 */
public class CheckingDistance {

    List<Node> getListOfNodesWithinDistance(Node node, int distance)
    {
        if ( node == null )
        {
            throw new IllegalArgumentException("node can't be null");
        }

        if (distance < 0 )
        {
            throw new IllegalArgumentException("distance must be non negative");
        }

        List<Node> nodesWithinDistance = new ArrayList<Node>();

        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add( node );
        Map<Node, Integer> layerMap = new HashMap<Node, Integer>();
        layerMap.put(node, 0);
        while ( ! queue.isEmpty() )
        {
            Node currentNode = queue.poll();
            int currentNodeDis = layerMap.get(currentNode);
            if (currentNodeDis > distance)
            {
                continue;
            }

            assert (currentNodeDis <= distance) :
                    ("currentNodeDis = " + currentNodeDis + ", distance = " + distance);

            nodesWithinDistance.add( currentNode );


            Iterable<Node> neighborsOfCurrentNode = currentNode.getAdjacents();
            for ( Node neighbor : neighborsOfCurrentNode )
            {
                int neighborDistance = layerMap.getOrDefault(neighbor, Integer.MAX_VALUE);

                if ( currentNodeDis + 1 > neighborDistance )
                {
                    continue;
                }

                assert (currentNodeDis + 1 <= neighborDistance);

                queue.add(neighbor);
                layerMap.put(neighbor, currentNodeDis+1);
            }
        }

        return nodesWithinDistance;
    }

    private List<Node> getNodeListWithAliveServer(List<Node> nodeList)
    {
        List<Node> nodeListWithAliveServer = new ArrayList<Node>();
        for (Node node : nodeList)
        {
            if ( node.hasAliveServer() )
            {
                nodeListWithAliveServer.add(node);
            }
        }

        return nodeListWithAliveServer;
    }


    public int getCntOfAliveServerWithinDistance(Node node, int distance)
    {
        if ( node == null )
        {
            throw new IllegalArgumentException("node can't be null");
        }

        if (distance < 0 )
        {
            throw new IllegalArgumentException("distance must be non negative");
        }

        List<Node> nodeListWithinDistance = getListOfNodesWithinDistance(node, distance);
        List<Node> nodeWithAliveServerListWithinDistance = getNodeListWithAliveServer(nodeListWithinDistance);

        int cntOfAliveServerWithinDistance = nodeWithAliveServerListWithinDistance.size();

        return cntOfAliveServerWithinDistance;

    }

    public boolean hasAliveServerWithinDistance(Node node, int distance)
    {
        if ( node == null )
        {
            throw new IllegalArgumentException("node can't be null");
        }

        if (distance < 0 )
        {
            throw new IllegalArgumentException("distance must be non negative");
        }

        int aliveServerCntWithinDistance = getCntOfAliveServerWithinDistance(node, distance);
        assert aliveServerCntWithinDistance >= 0 : aliveServerCntWithinDistance;

        boolean ret = (aliveServerCntWithinDistance > 0);

        return ret;

    }

}
