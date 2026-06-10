package minitf.core;

import java.util.List;

public class VirtualVPC extends NetworkResource {
    private List<String> subnetIds;

    public VirtualVPC(String resourceId, String name, String cidrBlock) {
        super(resourceId, name, "aws", cidrBlock);
    }

    public List<String> getSubnetIds() {
        return subnetIds;
    }

    @Override
    public ApplyResult apply() {
        subnetIds = List.of(
            "subnet-%03d".formatted(hashCode() % 1000),
            "subnet-%03d".formatted((hashCode() + 1) % 1000)
        );
        return new ApplyResult(getResourceId(), true, "VPC " + getResourceId() + " provisioned with subnets " + subnetIds);
    }

    @Override
    public DestroyResult destroy() {
        subnetIds = null;
        return new DestroyResult(getResourceId(), true, "VPC " + getResourceId() + " destroyed");
    }

    @Override
    public DestroyResult destroy(boolean force) {
        subnetIds = null;
        return new DestroyResult(getResourceId(), true, "VPC " + getResourceId() + " force-destroyed");
    }
}
