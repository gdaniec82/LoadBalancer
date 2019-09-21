import algorithms.Balanceable;
import resources.Host;
import resources.Request;

import java.util.List;

public class LoadBalancer {

    private List<Host> hosts;
    private Balanceable balancer;

    public LoadBalancer(List<Host> hosts, Balanceable balancer) {
        this.hosts = hosts;
        this.balancer = balancer;
    }

    public void handleRequest(Request request) {
        balancer.allocateHost(hosts).handleRequest(request);
    }
}
