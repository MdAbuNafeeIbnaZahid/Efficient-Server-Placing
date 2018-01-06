package helper_util;

/**
 * Created by nafee on 1/4/18.
 */
public class Validator {
    public enum NodeOrEdge
    {
        NODE("NODE"), EDGE("EDGE");

        String name;

        NodeOrEdge(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "NodeOrEdge{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


    public static void validateGraphConstructorInput(int nodeCnt, int edgeCnt)
    {
        validateNodeCnt(nodeCnt);
        validateNodeOrEdgeCnt(edgeCnt, NodeOrEdge.EDGE);
        if ( edgeCnt > MyUtil.nc2(nodeCnt) )
        {
            throw new IllegalArgumentException(" Too much edges ");
        }
    }

    private static void validateNodeOrEdgeCnt(int nodeOrEdgeCnt, NodeOrEdge nodeOrEdge)
    {
        if ( nodeOrEdgeCnt < 0 )
        {
            throw new IllegalArgumentException( nodeOrEdge + " of graph can't be negative ");
        }
    }

    public static void validateNodeCnt(int nodeCnt)
    {
        validateNodeOrEdgeCnt(nodeCnt, NodeOrEdge.NODE);
    }

    public static void validateServerRange(int serverRange)
    {
        if ( serverRange < 0 )
        {

        }
    }

}
