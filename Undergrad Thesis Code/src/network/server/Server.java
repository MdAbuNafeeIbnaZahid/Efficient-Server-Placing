package network.server;

import graph.Node;

/**
 * Created by nafee on 11/29/17.
 */
public class Server {
    Node node;
    SERVER_STATUS status = SERVER_STATUS.ALIVE;

    public boolean isAlive()
    {
        return status.equals(SERVER_STATUS.ALIVE);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node)
    {
        if (node == null)
        {
            throw new IllegalArgumentException();
        }

        if (this.node != null)
        {
            throw new Error(" node for this server is already set ");
        }

        this.node = node;
    }

    public SERVER_STATUS getStatus() {
        return status;
    }

    public void setStatus(SERVER_STATUS status) {
        this.status = status;
    }
}
