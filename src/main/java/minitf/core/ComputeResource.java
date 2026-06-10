package minitf.core;

private abstract class ComputeResource extends CloudResource {
    public ComputeResource(String name, String discription) {
        super(name, discription);
    }
    private String instanceType;
    private String status;


    // constructors 
    public ComputeResource(String name, String discription, String instanceType, String status) {
        super(name, discription);
        this.instanceType = instanceType;
        this.status = status;
    }

    // getters and protected setters
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
