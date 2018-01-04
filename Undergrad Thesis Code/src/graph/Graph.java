package graph;

import graph.graph_traversal.CheckingDistance;
import helper_util.MyUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nafee on 11/29/17.
 */
public abstract class Graph implements Serializable
{
    protected int nodeCnt;
    protected int edgeCnt;
    protected List<Node> nodes = new ArrayList<Node>();

    // Here we are not allowing multigraph.
    // This method will return false if there was previously an edge between u & v
    // Will return true if a new edge is added
    boolean addEdge( int u, int v )
    {
        assert (u != v) : "u = " + u + ", v = " + v ;
        assert ( u >= 0 && u < nodeCnt) : "u = " + u + ", nodeCnt = " + nodeCnt;
        assert (v >= 0 && v < nodeCnt) : "v = " + v + ", nodeCnt = " + nodeCnt;

        Node uNode = nodes.get(u);
        assert (uNode != null) : "uNode = " + uNode;

        Node vNode = nodes.get(v);
        assert (vNode != null) : "vNode = " + vNode;

        assert ( ! uNode.equals(vNode) ) : "uNode = " + uNode + ", vNode = " + vNode;

        if ( areAdjacent(u, v) )
        {
            return false;
        }

        uNode.addNeighbor(vNode);
        vNode.addNeighbor(uNode);

        return true;
    }


    boolean areAdjacent( int u, int v ) {
        assert ( u >= 0 && u < nodeCnt) : "u = " + u + ", nodeCnt = " + nodeCnt;
        assert ( v >= 0 && v < nodeCnt) : "v = " + v + ", nodeCnt = " + nodeCnt;

        Node uNode = nodes.get(u);
        assert (uNode != null);


        Node vNode = nodes.get(v);
        assert vNode != null;

        assert (uNode.isAdjacent(vNode) == vNode.isAdjacent(uNode)) :
                "uNode.isAdjacent(vNode) = " + uNode.isAdjacent(vNode) +
                ", vNode.isAdjacent(uNode) = " + vNode.isAdjacent(uNode);

        return uNode.isAdjacent(vNode);
    }

    protected void makeRandomEdges( int edgeCnt )
    {
        if ( edgeCnt < 0 )
        {
            throw new IllegalArgumentException();
        }
        List<Edge> edges = Edge.getRandomEdges(nodeCnt, edgeCnt);
        for ( Edge edge : edges )
        {
            addEdge(edge.uNumber, edge.vNumber);
        }
    }



    public List<Node> getNodes()
    {
        return nodes;
    }

    public int getNodeCnt() {
        return nodeCnt;
    }

    public int getEdgeCnt() {
        return edgeCnt;
    }

    public boolean isConnected()
    {
        if (nodeCnt < 0 || edgeCnt < 0)
        {
            throw new RuntimeException("nodeCnt = " + nodeCnt +
                    ", edgeCnt = " + edgeCnt);
        }

        if ( nodeCnt == 0 )
        {
            return true;
        }

        Node node0 = nodes.get(0);

        CheckingDistance checkingDistance = new CheckingDistance();
        List<Node> allNodesConnectedWithNode0 = checkingDistance.getListOfNodesWithinDistance(node0, Integer.MAX_VALUE);

        int subgraphContainingZeroVertexCnt = allNodesConnectedWithNode0.size();
        assert subgraphContainingZeroVertexCnt >= 1;

        return subgraphContainingZeroVertexCnt == nodeCnt;
    }
}
