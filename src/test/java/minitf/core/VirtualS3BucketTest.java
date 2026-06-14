package minitf.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VirtualS3BucketTest {

    @Test
    void apply_generatesCorrectArn() {
        VirtualS3Bucket bucket = new VirtualS3Bucket("s3-001", "my-app-bucket", 100, "us-east-1", true);
        ApplyResult result = bucket.apply();

        assertEquals("arn:aws:s3:::my-app-bucket", bucket.getArn());
        assertTrue(result.isSuccess());
    }

    @Test
    void destroy_clearsArnAndDisablesVersioning() {
        VirtualS3Bucket bucket = new VirtualS3Bucket("s3-001", "my-app-bucket", 100, "us-east-1", true);
        bucket.apply();
        DestroyResult result = bucket.destroy();

        assertNull(bucket.getArn());
        assertFalse(bucket.isVersioning());
        assertTrue(result.isSuccess());
    }
}
