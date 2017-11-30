package network.client;

import graph.Node;

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
}
