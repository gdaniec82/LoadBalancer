package resources;

public class Host {

    private String hostName;
    private float currentLoad;

    public Host(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public float getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(float currentLoad) {
        this.currentLoad = currentLoad;
    }

    public void handleRequest(Request req) {
        System.out.println("Handling request: " + hostName);
    }
}
