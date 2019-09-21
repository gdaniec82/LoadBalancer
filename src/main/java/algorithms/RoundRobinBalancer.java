package algorithms;

import exceptions.NoHostException;
import resources.Host;

import java.util.List;
import java.util.Objects;

public class RoundRobinBalancer implements Balanceable {

    private int hostIndex = 0;

    public Host allocateHost(List<Host> hosts) {
        Objects.requireNonNull(hosts);
        if (hosts.size() == 0) throw new NoHostException();

        Host allocatedHost = hosts.get(hostIndex);
        calculateNewHostIndex(hosts.size()-1);
        return allocatedHost;
    }

    private void calculateNewHostIndex(int maxIndex) {
        hostIndex = hostIndex == maxIndex ? hostIndex = 0 : ++hostIndex;
    }
}
