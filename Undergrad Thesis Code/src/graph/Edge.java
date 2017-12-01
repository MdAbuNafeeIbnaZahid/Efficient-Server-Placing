package graph;

import helper_util.MyUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nafee on 11/30/17.
 */
public class Edge
{
    int uNumber;
    int vNumber;

    public Edge(int uNumber, int vNumber)
    {
        if ( uNumber < 0 || vNumber < 0 )
        {
            throw new IllegalArgumentException("uNumber = " + uNumber
            + ", vNumber = " + vNumber);
        }

        this.uNumber = uNumber;
        this.vNumber = vNumber;
    }

    // We assume that vertices are numbered from 0 to nodeCnt-1
    private static List<Edge> getAllPossibleEdge( int nodeCnt )
    {
        List<Edge> ret = new ArrayList<Edge>();
        for (int a = 0; a < nodeCnt; a++)
        {
            for (int b = a+1; b < nodeCnt; b++)
            {
                Edge edge = new Edge(a, b);
                ret.add(edge);
            }
        }
        return ret;
    }

    // We assume that vertices are numbered from 0 to nodeCnt-1
    public static List<Edge> getRandomEdges(int nodeCnt, int edgeCnt)
    {
        if ( edgeCnt > MyUtil.nc2(nodeCnt))
        {
            throw new IllegalArgumentException();
        }
        List<Edge> allPossibleEdges = getAllPossibleEdge(nodeCnt);
        Collections.shuffle(allPossibleEdges);

        assert ( edgeCnt <= allPossibleEdges.size() ) : "edgeCnt = " + edgeCnt +
                ", allPossibleEdges.size() = " + allPossibleEdges.size() ;
        List<Edge> ret = allPossibleEdges.subList(0, edgeCnt);

        return ret;
    }
}
