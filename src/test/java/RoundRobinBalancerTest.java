import algorithms.Balanceable;
import algorithms.RoundRobinBalancer;
import exceptions.NoHostException;
import org.junit.Test;
import resources.Host;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RoundRobinBalancerTest {

    private Balanceable balancerUnderTest = new RoundRobinBalancer();

    @Test(expected = NullPointerException.class)
    public void nullHosts() {
        balancerUnderTest.allocateHost(null);
    }

    @Test(expected = NoHostException.class)
    public void noHosts() {
        balancerUnderTest.allocateHost(Collections.emptyList());
    }

    @Test
    public void whenNoHostAllocatedYet_ThenAllocateFirstHost() {
        Host google = new Host("google");
        google.setCurrentLoad(0.1f);

        Host allocatedHost = balancerUnderTest.allocateHost(Arrays.asList(new Host[]{google}));
        assertEquals("google", allocatedHost.getHostName());
    }

    @Test
    public void whenFirstHostAllocated_ThenAllocateRequestToSecondHost() {
        Host google = new Host("google");
        google.setCurrentLoad(0.1f);
        Host yahoo = new Host("yahoo");
        yahoo.setCurrentLoad(0.1f);
        List<Host> hosts = Arrays.asList(new Host[]{google, yahoo});

        balancerUnderTest.allocateHost(hosts);
        Host allocatedHost = balancerUnderTest.allocateHost(hosts);
        assertEquals("yahoo", allocatedHost.getHostName());
    }

    @Test
    public void whenBothHostsAllocated_ThenAllocateFirstHostAgain() {
        Host google = new Host("google");
        google.setCurrentLoad(0.1f);
        Host yahoo = new Host("yahoo");
        yahoo.setCurrentLoad(0.1f);
        List<Host> hosts = Arrays.asList(new Host[]{google, yahoo});

        balancerUnderTest.allocateHost(hosts);
        balancerUnderTest.allocateHost(hosts);
        Host allocatedHost = balancerUnderTest.allocateHost(hosts);
        assertEquals("google", allocatedHost.getHostName());
    }
}
