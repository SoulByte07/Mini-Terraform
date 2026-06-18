package minitf.state;

import minitf.core.VirtualEC2;
import minitf.core.VirtualVPC;
import minitf.core.VirtualS3Bucket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class StateManagerTest {

    private static final String TEST_STATE_FILE = "test-terraform-state.json";

    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(Path.of(TEST_STATE_FILE));
    }

    @Test
    void saveAndLoad_roundTripsResources() throws IOException {
        VirtualEC2 ec2 = new VirtualEC2("i-001", "web-server", "ami-1234", "t2.micro");
        ec2.apply();
        VirtualVPC vpc = new VirtualVPC("vpc-001", "main-vpc", "10.0.0.0/16");
        vpc.apply();
        VirtualS3Bucket s3 = new VirtualS3Bucket("s3-001", "my-app-bucket", 100, "us-east-1", true);
        s3.apply();

        StateStore original = new StateStore();
        original.put(ec2);
        original.put(vpc);
        original.put(s3);

        StateManager manager = new StateManager(TEST_STATE_FILE);
        manager.save(original);

        StateStore loaded = manager.load();

        assertEquals(3, loaded.size());
        assertEquals(ec2, loaded.get("i-001"));
        assertEquals(vpc, loaded.get("vpc-001"));
        assertEquals(s3, loaded.get("s3-001"));
    }

    @Test
    void load_returnsEmptyStoreWhenFileMissing() throws IOException {
        StateManager manager = new StateManager(TEST_STATE_FILE);
        StateStore store = manager.load();
        assertEquals(0, store.size());
    }
}
