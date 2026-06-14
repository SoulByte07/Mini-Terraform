package minitf.core;

public abstract class NetworkResource extends CloudResource {
    private String cidrBlock;

    public NetworkResource(String resourceId, String name, String provider, String cidrBlock) {
        super(resourceId, name, provider);
        if (cidrBlock == null || !cidrBlock.matches("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})/(\\d{1,2})$")) {
            throw new IllegalArgumentException("Invalid CIDR block: " + cidrBlock);
        }
        String[] parts = cidrBlock.split("/");
        for (String octet : parts[0].split("\\.")) {
            if (Integer.parseInt(octet) > 255) {
                throw new IllegalArgumentException("Invalid CIDR block: " + cidrBlock);
            }
        }
        int prefix = Integer.parseInt(parts[1]);
        if (prefix < 0 || prefix > 32) {
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
