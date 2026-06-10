package minitf.core;

public abstract class NetworkResource extends CloudResource {
    private String cidrBlock;

    public NetworkResource(String resourceId, String name, String provider, String cidrBlock) {
        super(resourceId, name, provider);
        if (cidrBlock == null || !cidrBlock.matches("^\\d{1,3}(\\.\\d{1,3}){3}/\\d{1,2}$")) {
            throw new IllegalArgumentException("Invalid CIDR block: " + cidrBlock);
        }
        this.cidrBlock = cidrBlock;
    }

    public String getCidrBlock() {
        return cidrBlock;
    }

    protected void setCidrBlock(String cidrBlock) {
        this.cidrBlock = cidrBlock;
    }
}
