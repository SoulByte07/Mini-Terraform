package minitf.core;
import java.util.List;

/*
  - Fields: `private List<String> subnetIds`
  - `apply()`: provision subnets (mock IDs like `"subnet-%03d".formatted(hashCode() % 1000)`)
  - `destroy()`: clear subnets
*/

public class VirtualVPC extends CloudResource {
    private List<String> subnetIds;

    @Override
    public void apply() {
        // Mock provisioning of subnets
        subnetIds = List.of(
            "subnet-%03d".formatted(hashCode() % 1000),
            "subnet-%03d".formatted((hashCode() + 1) % 1000)
        );
        System.out.println("Provisioned VPC with subnets: " + subnetIds);
    }

    @Override
    public void destroy() {
        // Clear subnets
        System.out.println("Destroying VPC and clearing subnets: " + subnetIds);
        subnetIds = null;
    }
}
