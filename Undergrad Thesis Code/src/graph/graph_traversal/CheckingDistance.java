package graph.graph_traversal;

import cutomized_util.DefaultHashMap;
import graph.Node;

import java.util.*;

/**
 * Created by nafee on 11/29/17.
 */
public class CheckingDistance {

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

        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add( node );
        Map<Node, Integer> layerMap = new HashMap<Node, Integer>();
        layerMap.put(node, 0);
        while (queue.size()>0)
        {
            Node currentNode = queue.poll();
            int currentNodeDis = layerMap.get(currentNode);
            if (currentNodeDis > distance)
            {
                continue;
            }

            assert (currentNodeDis <= distance) :
                    ("currentNodeDis = " + currentNodeDis + ", distance = " + distance);

            if ( currentNode.hasAliveServer() )
            {
                return true;
            }

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
        return false;
    }

}
