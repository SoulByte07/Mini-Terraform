package minitf.core;

public class VirtualS3Bucket extends StorageResource {
    private boolean versioning;
    private String arn;

    public VirtualS3Bucket(String resourceId, String name, int sizeGb, String region, boolean versioning) {
        super(resourceId, name, "aws", sizeGb, region);
        this.versioning = versioning;
    }

    public boolean isVersioning() {
        return versioning;
    }

    public String getArn() {
        return arn;
    }

    @Override
    public ApplyResult apply() {
        arn = "arn:aws:s3:::" + getName();
        versioning = true;
        return new ApplyResult(getResourceId(), true, "S3 bucket " + getResourceId() + " created at " + arn);
    }

    @Override
    public DestroyResult destroy() {
        arn = null;
        versioning = false;
        return new DestroyResult(getResourceId(), true, "S3 bucket " + getResourceId() + " destroyed");
    }

    @Override
    public DestroyResult destroy(boolean force) {
        arn = null;
        versioning = false;
        return new DestroyResult(getResourceId(), true, "S3 bucket " + getResourceId() + " force-destroyed");
    }
}
