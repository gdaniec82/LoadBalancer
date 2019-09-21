package algorithms;

import exceptions.NoHostException;
import resources.Host;

import java.util.*;

public class TresholdBalancer implements Balanceable {

    private float treshold;

    public TresholdBalancer(float treshold) {
        this.treshold = treshold;
    }

    public Host allocateHost(List<Host> hosts) {
        Objects.requireNonNull(hosts);

        Optional<Host> hostBelowTreshold = hosts.stream().filter(h -> h.getCurrentLoad() < treshold).findFirst();
        Optional<Host> hostWithMinLoad = hosts.stream().min(Comparator.comparing(Host::getCurrentLoad));
        return hostBelowTreshold.orElse(hostWithMinLoad.orElseThrow(NoHostException::new));
    }
}
