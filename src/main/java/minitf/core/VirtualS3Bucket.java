package minitf.core;

public class VirtualS3Bucket extends StorageResource {
    public VirtualS3Bucket(String region) {
        super(region);
        super(StorageSize);
    }

    @Override
    public void apply() {
        // Mock provisioning of S3 bucket
        storageId = "bucket-%03d".formatted(hashCode() % 1000);
        System.out.println("Provisioned S3 bucket: " + storageId + " in region: " + region);
    }

    @Override
    public void destroy() {
        // Clear bucket
        System.out.println("Destroying S3 bucket: " + storageId);
        storageId = null;
        region = null;
    }
}
