package network;

import helper_util.CollectionFromIterable;
import graph.Graph;
import graph.Node;
import network.client.Client;
import network.server.SERVER_STATUS;
import network.server.Server;
import server_placing.ServerPlacing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * Created by nafee on 11/30/17.
 */
public class Network {

    Graph graph;
    List<Server> servers = new ArrayList<Server>();
    List<Client> clients = new ArrayList<Client>();
    ServerPlacing serverPlacing;

    public void addNewServerInNetwork(Node node)
    {
        Server server = new Server();
        servers.add(server);
        attachNodeWithServer(node, server);
    }

    public Iterable<Node> getNodes()
    {
        return graph.getNodes();
    }

    public int getNodeCnt()
    {
        return graph.getNodeCnt();
    }

    public int getServerCnt()
    {
        return servers.size();
    }

    public boolean containsServer()
    {
        return servers != null;
    }

//    private void addServersRandomly( int serverCnt )
//    {
//        assert servers.size() == 0 : servers.size();
//        assert serverCnt >= 0 : serverCnt;
//        assert serverCnt <= graph.getNodeCnt() : "serverCnt = " + serverCnt +
//                ", graph.getNodeCnt() = " + graph.getNodeCnt();
//
//        List<Node> nodes = CollectionFromIterable.<Node>makeList(graph.getNodes());
//        Collections.shuffle( nodes );
//        nodes = nodes.subList(0, serverCnt);
//        for (Node node : nodes)
//        {
//            Server server = new Server();
//
//            servers.add(server);
//
//            attachNodeWithServer(node, server);
//        }
//    }

    private void addClientsRandomly(int clientCnt)
    {
        assert clients.size() == 0 : clients.size();
        assert clientCnt >= 0 : clientCnt;
        assert clientCnt <= graph.getNodeCnt() : "clientCnt = " + clientCnt +
                ", graph.getNodeCnt() = " + graph.getNodeCnt();


        List<Node> nodes = CollectionFromIterable.<Node>makeList(graph.getNodes());
        Collections.shuffle( nodes );
        nodes = nodes.subList(0, clientCnt);
        for (Node node : nodes)
        {
            Client client = new Client();

            clients.add(client);

            attachNodeWithClient(node, client);
        }
    }



    public Network(int nodeCnt, int edgeCnt, int serverCnt, int clientCnt,
                   ServerPlacing serverPlacing)
    {
        this.graph = new Graph( nodeCnt, edgeCnt );
        this.serverPlacing = serverPlacing;

        addClientsRandomly(clientCnt);
        assert clients.size() == clientCnt;

        serverPlacing.placeServers(this, serverCnt);
        assert servers.size() == serverCnt;

    }

    public void attachNodeWithServer(Node node, Server server)
    {
        assert (node != null) : node;
        assert (server != null) : server;

        node.setServer(server);
        server.setNode(node);
    }

    private void attachNodeWithClient(Node node, Client client)
    {
        assert (node != null) : node;
        assert (client != null) : client;

        node.setClient(client);
        client.setNode(node);
    }

    public void randomlyDropServers( int dropCnt )
    {
        if ( dropCnt > servers.size() || dropCnt < 0 )
        {
            throw new IllegalArgumentException("dropCnt = " + dropCnt);
        }

        List<Server> serversToDrop = new ArrayList<Server>( servers );
        Collections.shuffle(serversToDrop);
        serversToDrop = serversToDrop.subList(0, dropCnt);

        for (Server server : serversToDrop)
        {
            server.setStatus(SERVER_STATUS.DEAD);
        }
    }

    public int getClientCntWithServerInRange(int range)
    {
        int ret = 0;
        if (range < 0)
        {
            throw new IllegalArgumentException("range cant be negative");
        }

        for (Client client : clients)
        {
            boolean connectivity = client.hasAliveServerWithinDistance(range);

            ret += ( connectivity ? 1 : 0 );
        }

        return ret;
    }
}
