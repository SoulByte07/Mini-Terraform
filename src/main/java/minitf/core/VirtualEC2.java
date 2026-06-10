package minitf.core;

public class VirtualEC2 extends ComputeResource {
    private String amiId;
    private String publicIp;

    public VirtualEC2(String resourceId, String name, String amiId, String instanceType) {
        super(resourceId, name, "aws", instanceType);
        this.amiId = amiId;
    }

    public String getAmiId() {
        return amiId;
    }

    public String getPublicIp() {
        return publicIp;
    }

    @Override
    public ApplyResult apply() {
        publicIp = "10.0.0.%d".formatted(hashCode() % 256);
        setStatus("running");
        return new ApplyResult(getResourceId(), true, "EC2 instance " + getResourceId() + " is now running at " + publicIp);
    }

    @Override
    public DestroyResult destroy() {
        publicIp = null;
        setStatus("stopped");
        return new DestroyResult(getResourceId(), true, "EC2 instance " + getResourceId() + " stopped");
    }

    @Override
    public DestroyResult destroy(boolean force) {
        publicIp = null;
        setStatus("terminated");
        return new DestroyResult(getResourceId(), true, "EC2 instance " + getResourceId() + " force-destroyed");
    }
}
