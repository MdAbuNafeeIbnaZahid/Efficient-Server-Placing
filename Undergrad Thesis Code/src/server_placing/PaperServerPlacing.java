package server_placing;

import graph.graph_traversal.CheckingDistance;
import graph.Graph;
import graph.Node;
import jdk.jshell.spi.ExecutionControl;
import network.Network;
import server_placing.ServerPlacing;

import java.util.List;
import java.util.ArrayList;
import java.util.*;


class Pair {
     public int f, s;
    
    Pair() {}
    Pair(int _f, int _s) { f = _f; s = _s; }
}



public class PaperServerPlacing implements ServerPlacing
{
    
    List<Node> servers;

    public void placeServers(Network network, int serverCnt)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    List<Node> hybridPlacing(Graph graph, int serverCount, int serverRange) 
    {
        int N = graph.getNodeCnt();
        List<Integer> Plm = new ArrayList<Integer>(Collections.nCopies(N, 0));
        List<Pair> Ls = new ArrayList<Pair>();
        for(int lm = 0; lm < N; lm++) {
            Ls.add(new Pair(graph.getNode(lm).getDegree(), lm));
        }
        Collections.sort(Ls, new Comparator<Pair>() {
            public int compare(Pair left, Pair right) {
                return right.f - left.f;
            }
        });
        
        Set<Pair> cur = new HashSet<Pair>();
        for(int i = 0; i < serverCount; i++) {
            int idx = Ls.get(i).s;
            Plm.set(idx, new CheckingDistance().getListOfNodesWithinDistance(graph.getNode(idx), serverRange).size());
            cur.add(new Pair(Plm.get(idx), idx));
        }
        
        int failLimit = 10, consecutiveFail = 0;
        for(int i = serverCount+1; i < N; i++) {
            int idx = Ls.get(i).s;
            Plm.set(idx, new CheckingDistance().getListOfNodesWithinDistance(graph.getNode(idx), serverRange).size());
            Pair m = Collections.min(cur, new Comparator<Pair>() {
                public int compare(Pair left, Pair right) {
                    return left.f - right.f;
                }
            });
            if(m.f < Plm.get(idx)) {
                cur.remove(m);
                cur.add(new Pair(Plm.get(idx), idx));
                consecutiveFail = 0;
            } else {
                consecutiveFail++;
            }
            if(consecutiveFail == failLimit) break;
        }
        List<Node> ret = new ArrayList<Node>();
        for(Pair x: cur) ret.add(graph.getNode(x.s));
        return ret;
    }
    
    boolean checkValidPlacement(Graph graph, Set<Node> servers, int serverRange, int minServerCntReqWithinRange)
    {
        List<Node> all = graph.getNodes();
        CheckingDistance ck = new CheckingDistance();
        for(Node u: all) {
            List<Node> canGo = ck.getListOfNodesWithinDistance(u, serverRange);
            int cnt = 0;
            for(Node v: canGo) {
                if(servers.contains(v)) cnt++;
            }
            if(cnt < minServerCntReqWithinRange) return false;
        }
        return true;
    }

    public List<Node> getNodeListForServerPlacing(Graph graph, int serverRange, int minServerCntReqWithinRange)
    {
        int N = graph.getNodeCnt();
        int lo = -1, hi = N;
        while(lo+1 < hi) {
            int mid = (lo+hi) / 2;
            servers = hybridPlacing(graph, mid, serverRange);
            if(checkValidPlacement(graph, new HashSet<Node>(servers), serverRange, minServerCntReqWithinRange)) hi = mid;
            else lo = mid;
        }
        servers = hybridPlacing(graph, hi, serverRange);
        return servers;
    }

    public int getServerCntForGoodConnectivity(Graph graph, int serverRange, int minServerCntReqWithinRange)
    {
        servers = getNodeListForServerPlacing(graph, serverRange, minServerCntReqWithinRange);
        return servers.size();
    }
}
