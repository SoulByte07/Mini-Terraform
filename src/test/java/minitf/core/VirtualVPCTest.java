package minitf.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VirtualVPCTest {

    @Test
    void validCidr_createsVpc() {
        VirtualVPC vpc = new VirtualVPC("vpc-001", "main-vpc", "10.0.0.0/16");
        assertEquals("10.0.0.0/16", vpc.getCidrBlock());
    }

    @Test
    void invalidCidr_throwsException() {
        assertThrows(IllegalArgumentException.class,
            () -> new VirtualVPC("vpc-002", "bad-vpc", "192.168.1.0/33"));
    }

    @Test
    void nullCidr_throwsException() {
        assertThrows(IllegalArgumentException.class,
            () -> new VirtualVPC("vpc-003", "null-vpc", null));
    }

    @Test
    void apply_provisionsSubnets() {
        VirtualVPC vpc = new VirtualVPC("vpc-001", "main-vpc", "10.0.0.0/16");
        vpc.apply();

        assertNotNull(vpc.getSubnetIds());
        assertFalse(vpc.getSubnetIds().isEmpty());
    }

    @Test
    void destroy_clearsSubnets() {
        VirtualVPC vpc = new VirtualVPC("vpc-001", "main-vpc", "10.0.0.0/16");
        vpc.apply();
        vpc.destroy();

        assertNull(vpc.getSubnetIds());
    }
}
