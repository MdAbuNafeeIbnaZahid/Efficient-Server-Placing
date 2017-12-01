package network.client;

import graph.Node;
import graph.graph_traversal.CheckingDistance;

/**
 * Created by nafee on 11/29/17.
 */
public class Client
{
    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        if (node == null)
        {
            throw new IllegalArgumentException();
        }

        if (this.node != null)
        {
            throw new Error(); // can't set the node once it is set
        }

        this.node = node;
    }

    public boolean hasAliveServerWithinDistance(int distance)
    {
        if ( distance < 0 )
        {
            throw new IllegalArgumentException("distance = " + distance
            + ", distance must be non negative");
        }

        CheckingDistance checkingDistance = new CheckingDistance();

        boolean ret = checkingDistance.hasAliveServerWithinDistance(this.node, distance);
        return ret;
    }
}
