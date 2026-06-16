package minitf.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VirtualEC2Test {

    @Test
    void applySetsPublicIpAndRunningStatus() {
        VirtualEC2 ec2 = new VirtualEC2("i-001", "web-server", "ami-1234", "t2.micro");
        ApplyResult result = ec2.apply();

        assertNotNull(ec2.getPublicIp());
        assertTrue(ec2.getPublicIp().startsWith("10.0.0."));
        assertEquals("running", ec2.getStatus());
        assertTrue(result.isSuccess());
    }

    @Test
    void destroyClearsIpAndSetsStopped() {
        VirtualEC2 ec2 = new VirtualEC2("i-001", "web-server", "ami-1234", "t2.micro");
        ec2.apply();
        DestroyResult result = ec2.destroy();

        assertNull(ec2.getPublicIp());
        assertEquals("stopped", ec2.getStatus());
        assertTrue(result.isSuccess());
    }

    @Test
    void destroyForce_terminates() {
        VirtualEC2 ec2 = new VirtualEC2("i-001", "web-server", "ami-1234", "t2.micro");
        ec2.apply();
        DestroyResult result = ec2.destroy(true);

        assertNull(ec2.getPublicIp());
        assertEquals("terminated", ec2.getStatus());
        assertTrue(result.isSuccess());
    }
}
