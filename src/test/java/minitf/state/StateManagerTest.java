package minitf.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StateManagerTest {

    @Test
    public void testSaveLoadRoundTrip() {
        // Create a StateManager instance
        StateManager stateManager = new StateManager();

        // Create some mock resources
        EC2Instance ec2Instance = new EC2Instance("i-1234567890abcdef0", "t2.micro", "running");
        VPC vpc = new VPC("vpc-abcdef1234567890", "10.0.0.1/16");
        S3Bucket s3Bucket = new S3Bucket("my-test-bucket", "us-west-2");
        
        // Add resources to the state manager
        stateManager.addEC2Instance(ec2Instance);
        stateManager.addVPC(vpc);
        stateManager.addS3Bucket(s3Bucket);

        // Save the state to a file
        String filePath = "test_state.json";
        stateManager.saveState(filePath);

        // Load the state from the file
        StateManager loadedStateManager = StateManager.loadState(filePath);

        // Verify that the loaded resources match the original ones
        assertEquals(ec2Instance, loadedStateManager.getEC2Instance("i-1234567890abcdef0"));
        assertEquals(vpc, loadedStateManager.getVPC("vpc-abcdef1234567890"));
        assertEquals(s3Bucket, loadedStateManager.getS3Bucket("my-test-bucket"));
    }
}
