package algorithms;

import resources.Host;

import java.util.List;

public interface Balanceable {

    Host allocateHost(List<Host> hosts);
}
