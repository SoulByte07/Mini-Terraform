package minitf.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VirtualEC2Test {

    @Test
    public void testApply() {
        VirtualEC2 ec2 = new VirtualEC2("t2.micro", "ami-12345678");
        ApplyResult result = ec2.apply();
        assertTrue(result.isSuccess());
        assertEquals("VirtualEC2-1", result.getResourceId());
    }

    @Test 
    public void testDestroy() {
        VirtualEC2 ec2 = new VirtualEC2("t2.micro", "ami-12345678");
        ec2.apply(); 
        DestroyResult result = ec2.destroy();
        assertTrue(result.isSuccess());
        assertEquals("VirtualEC2-1", result.getResourceId());
    }
}
