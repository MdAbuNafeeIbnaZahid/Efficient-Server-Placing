package simulation;

import network.Network;

/**
 * Created by nafee on 12/1/17.
 */
public class SingleSimulation
{
    Network network;
    int serverDropCnt;
    int serverRange;

    // clientCntWithinServerRange method is set by the simulation method
    private int clientCntWithinServerRange = -1;

    public int getClientCntWithinServerRange()
    {
        if ( clientCntWithinServerRange < 0 )
        {
            throw new RuntimeException(" Simulation is not run yet ");
        }
        return clientCntWithinServerRange;
    }


    public SingleSimulation(Network network, int serverDropCnt, int serverRange) {

        if (network == null)
        {
            throw  new IllegalArgumentException("Network cant be null");
        }

        if ( serverDropCnt < 0 || serverDropCnt > network.getServerCnt() )
        {
            throw new IllegalArgumentException( "serverDropCnt = " + serverDropCnt
            + ", network.getServerCnt() = " + network.getServerCnt() );
        }

        if ( serverRange < 0 )
        {
            throw new IllegalArgumentException("server range cant be negative");
        }

        this.network = network;
        this.serverDropCnt = serverDropCnt;
        this.serverRange = serverRange;
    }

    // This method sets the value of clientCntWithinServerRange
    public void simulate()
    {
        assert (this.network != null) : network;
        assert (serverDropCnt <= network.getServerCnt()) ;
        assert ( serverRange >= 0 );



        network.randomlyDropServers(this.serverDropCnt);

        clientCntWithinServerRange = network.getClientCntWithServerInRange(serverRange);
        assert ( clientCntWithinServerRange >= 0 ) : clientCntWithinServerRange;
    }
}
