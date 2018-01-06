package graph;



import network.client.Client;
import network.server.Server;

import java.io.Serializable;
import java.util.*;

/**
 * Created by nafee on 11/29/17.
 */
public class Node implements Serializable {

    private int nodeIdx;
    private Set<Node> adjacentNodes = new HashSet<Node>();
    private Client client = null;
    private Server server = null;


    public Node(int nodeIdx)
    {
        if (nodeIdx < 0)
        {
            throw new IllegalArgumentException();
        }
        this.nodeIdx = nodeIdx;
    }

    public void setClient(Client client)
    {
        if ( client == null )
        {
            throw new IllegalArgumentException();
        }

        if ( this.client != null )
        {
            throw new Error("This node already has a client"); // can't change a client once it is set
        }

        this.client = client;
    }

    public void setServer(Server server)
    {
        if (server == null)
        {
            throw new IllegalArgumentException();
        }

        if (this.server != null)
        {
            throw new Error("This node already has a server");
        }

        this.server = server;
    }

    // Here we are not allowing multigraph
    // returns false if neighbor is already in the set adjacentNodes
    // returns true after inserting neighbor in the set adjacentNodes
    boolean addNeighbor( Node neighbor )
    {
        assert (adjacentNodes != null) : "adjacentNodes = " + adjacentNodes;
        assert (neighbor != null) : "neighbor = " + neighbor;
        assert (!neighbor.equals(this)) : "this = " + this + ", neighbor = " + neighbor;

        if ( adjacentNodes.contains(neighbor) )
        {
            return false;
        }

        adjacentNodes.add(neighbor);
        return true;
    }

    public int getNodeIdx() {
        return nodeIdx;
    }

    public Client getClient() {
        return client;
    }

    public Server getServer() {
        return server;
    }

    boolean isAdjacent(Node possibleAdjacentNode)
    {
        return adjacentNodes.contains( possibleAdjacentNode );
    }

    public boolean hasServer()
    {
        return (server != null);
    }

    public boolean hasAliveServer()
    {
        if ( !hasServer() )
        {
            return false;
        }

        return server.isAlive();
    }

    public boolean hasClient()
    {
        return (client != null);
    }

    public Iterable getAdjacents()
    {
        return adjacentNodes;
    }

    public int getAdjacentCount()
    {
        return adjacentNodes.size();
    }

    public boolean doesHaveSpecificNeighborCnt(int neighborCount)
    {
        return (getAdjacentCount() == neighborCount);
    }
}
