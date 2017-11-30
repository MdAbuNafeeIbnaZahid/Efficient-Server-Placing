package graph;

import helper_util.MyUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nafee on 11/29/17.
 */
public class Graph
{
    int nodeCnt;
    int edgeCnt;
    List<Node> nodes = new ArrayList<Node>();

    // Here we are not allowing multigraph.
    // This method will return false if there was previously an edge between u & v
    // Will return true if a new edge is added
    boolean addEdge( int u, int v )
    {
        assert (u != v) : "u = " + u + ", v = " + v ;
        assert (u <= nodeCnt) : "u = " + u + ", nodeCnt = " + nodeCnt;
        assert (v <= nodeCnt) : "v = " + v + ", nodeCnt = " + nodeCnt;

        Node uNode = nodes.get(u);
        assert (uNode != null) : "uNode = " + uNode;

        Node vNode = nodes.get(v);
        assert (vNode != null) : "vNode = " + vNode;

        assert (uNode.equals(vNode)) : "uNode = " + uNode + ", vNode = " + vNode;

        if ( areAdjacent(u, v) )
        {
            return false;
        }

        uNode.addNeighbor(vNode);
        vNode.addNeighbor(uNode);

        return true;
    }


    boolean areAdjacent( int u, int v ) {
        assert (u <= nodeCnt) : "u = " + u + ", nodeCnt = " + nodeCnt;
        assert (v <= nodeCnt) : "v = " + v + ", nodeCnt = " + nodeCnt;

        Node uNode = nodes.get(u);
        assert (uNode != null);


        Node vNode = nodes.get(v);
        assert vNode != null;

        assert (uNode.isAdjacent(vNode) == vNode.isAdjacent(uNode)) :
                "uNode.isAdjacent(vNode) = " + uNode.isAdjacent(vNode) +
                ", vNode.isAdjacent(uNode) = " + vNode.isAdjacent(uNode);

        return uNode.isAdjacent(vNode);
    }

    private void makeRandomEdges( int edgeCnt )
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

    public Graph(int nodeCnt, int edgeCnt)
    {
        if (nodeCnt < 0 || edgeCnt < 0)
        {
            throw new IllegalArgumentException();
        }

        if ( edgeCnt > MyUtil.nc2(nodeCnt))
        {
            throw new IllegalArgumentException();
        }

        this.nodeCnt = nodeCnt;
        nodes = new ArrayList<Node>();
        for (int a = 1; a <= nodeCnt; a++ )
        {
            Node node = new Node(a);
            nodes.add(node);
        }

        this.edgeCnt = edgeCnt;
        makeRandomEdges(edgeCnt);
    }

    public Iterable<Node> getNodes()
    {
        return nodes;
    }

    public int getNodeCnt() {
        return nodeCnt;
    }

    public int getEdgeCnt() {
        return edgeCnt;
    }
}