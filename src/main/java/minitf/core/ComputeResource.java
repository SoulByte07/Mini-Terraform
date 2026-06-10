package minitf.core;

public abstract class ComputeResource extends CloudResource {
    private String instanceType;
    private String status;

    public ComputeResource(String resourceId, String name, String provider, String instanceType) {
        super(resourceId, name, provider);
        this.instanceType = instanceType;
        this.status = "stopped";
    }

    public String getInstanceType() {
        return instanceType;
    }

    public String getStatus() {
        return status;
    }

    protected void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    protected void setStatus(String status) {
        this.status = status;
    }
}
