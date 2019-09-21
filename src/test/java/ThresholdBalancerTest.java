import algorithms.Balanceable;
import algorithms.TresholdBalancer;
import exceptions.NoHostException;
import org.junit.Test;
import resources.Host;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ThresholdBalancerTest {

    private Balanceable balancerUnderTest = new TresholdBalancer(0.75f);

    @Test(expected = NullPointerException.class)
    public void nullHosts() {
        balancerUnderTest.allocateHost(null);
    }

    @Test(expected = NoHostException.class)
    public void noHosts() {
        balancerUnderTest.allocateHost(Collections.emptyList());
    }

    @Test
    public void whenAllHostsWithLoadBelowTreshold_thenAllocateFirstHost() {
        Host google = new Host("google");
        google.setCurrentLoad(0.1f);
        Host yahoo = new Host("yahoo");
        yahoo.setCurrentLoad(0);

        Host allocatedHost = balancerUnderTest.allocateHost(Arrays.asList(new Host[]{google, yahoo}));
        assertEquals("google", allocatedHost.getHostName());
    }

    @Test
    public void whenFirstHostWithLoadAboveTreshold_thenAllocateSecondHost() {
        Host google = new Host("google");
        google.setCurrentLoad(0.76f);
        Host yahoo = new Host("yahoo");
        yahoo.setCurrentLoad(0);

        Host allocatedHost = balancerUnderTest.allocateHost(Arrays.asList(new Host[]{google, yahoo}));
        assertEquals("yahoo", allocatedHost.getHostName());
    }

    @Test
    public void whenAllHostsWithLoadAboveTreshold_thenAllocateHostWithLowestLoad() {
        Host google = new Host("google");
        google.setCurrentLoad(0.95f);
        Host yahoo = new Host("yahoo");
        yahoo.setCurrentLoad(0.9f);
        Host amazon = new Host("amazon");
        yahoo.setCurrentLoad(0.77f);

        Host allocatedHost = balancerUnderTest.allocateHost(Arrays.asList(new Host[]{google, yahoo, amazon}));
        assertEquals("amazon", allocatedHost.getHostName());
    }
}
