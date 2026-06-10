package minitf.core;

private abstract class NetworkResource extends CloudResource {
    public NetworkResource(String name, String discription) {
        super(name, discription);
    }
    private String cidrBlock;
    private String status;

    // Validation: reject invalid CIDR (e.g., `192.168.1.0/33` or missing `/`)
    if (cidrBlock == null || !cidrBlock.matches("^\\d{1,3}(\\.\\d{1,3}){3}/\\d{1,2}$")) {
        throw new IllegalArgumentException("Invalid CIDR block: " + cidrBlock);
    }


    // constructors 
    public NetworkResource(String name, String discription, String cidrBlock, String status) {
        super(name, discription);
        this.cidrBlock = cidrBlock;
        this.status = status;
    }

    // getters and protected setters
    public String getCidrBlock() {
        return cidrBlock;
    }
    public String getStatus() {
        return status;
    }

    protected void setCidrBlock(String cidrBlock) {
        this.cidrBlock = cidrBlock;
    }
    
    protected void setStatus(String status) {
        this.status = status;
    }
}
