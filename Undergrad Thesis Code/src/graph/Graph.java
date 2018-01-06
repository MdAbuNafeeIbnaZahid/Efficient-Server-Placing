package graph;

import graph.graph_traversal.CheckingDistance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nafee on 11/29/17.
 */
public abstract class Graph implements Serializable
{
    protected int nodeCnt = 0;
    protected int edgeCnt = 0;
    protected List<Node> nodes = new ArrayList<Node>();


    protected void addEdge(Node uNode, Node vNode)
    {
        if ( uNode == null )
        {
            throw new IllegalArgumentException(" uNode can't be null ");
        }

        if ( vNode == null )
        {
            throw new IllegalArgumentException("vNode can't be null");
        }

        addEdge(uNode.getNodeIdx(), vNode.getNodeIdx());
    }


    // Here we are not allowing multigraph.
    // This method will return false if there was previously an edge between u & v
    // Will return true if a new edge is added
    protected void addEdge( int u, int v )
    {
        assert (u != v) : "u = " + u + ", v = " + v ;
        assert ( u >= 0 && u < nodeCnt) : "u = " + u + ", nodeCnt = " + nodeCnt;
        assert (v >= 0 && v < nodeCnt) : "v = " + v + ", nodeCnt = " + nodeCnt;

        Node uNode = nodes.get(u);
        assert (uNode != null) : "uNode = " + uNode;

        Node vNode = nodes.get(v);
        assert (vNode != null) : "vNode = " + vNode;

        assert ( ! uNode.equals(vNode) ) : "uNode = " + uNode + ", vNode = " + vNode;

        assert ! areAdjacent(u, v) : u + " & " + v + " previously had an edge. We are not allowing multiedge";

        uNode.addNeighbor(vNode);
        vNode.addNeighbor(uNode);

        edgeCnt++;

        assert areAdjacent(u, v) : " Adding edge between  " + u + " & " + v + " failed ";
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




        int subgraphSizeContainingZeroVertex = allNodesConnectedWithNode0.size();
        assert subgraphSizeContainingZeroVertex >= 1;
        assert subgraphSizeContainingZeroVertex <= nodeCnt : " A subgraph can't contain more vertices than" +
                " the parent graph. ";


        return subgraphSizeContainingZeroVertex == nodeCnt;
    }

    protected void makeNodes(int nodeCnt)
    {
        assert nodeCnt >= 0 : " nodeCnt of a graph can't be smaller than 0 ";
        assert nodes.size() == 0 : " There should  be no node in the graph before making nodes ";


        this.nodeCnt = nodeCnt;
        nodes = new ArrayList<Node>();

        // Here we are assuming 0 based indexing of vertices
        for (int a = 0; a < nodeCnt; a++ )
        {
            Node node = new Node(a);
            nodes.add(node);
        }

        assert nodes.size() == nodeCnt : " list of nodes must have the same number of nodes as nodeCnt ";
    }

    public Node getNode (int nodeIdx)
    {
        if ( nodeIdx < 0 || nodeIdx >= nodeCnt )
        {
            throw new IllegalArgumentException(" nodeIdx is out of range ");
        }

        assert nodes.size() == nodeCnt : " Graph must have nodeCnt number of nodes ";

        Node retNode = nodes.get(nodeIdx);
        assert retNode != null : " No node found in nodeIdx ";
        assert retNode.getNodeIdx() == nodeIdx : " node found in nodeIdx doesn't have expected idx ";

        return retNode;
    }


    

}
