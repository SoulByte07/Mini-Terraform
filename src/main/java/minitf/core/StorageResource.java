package minitf.core;

private abstract class StorageResource extends CloudResource {
    protected String storageId;
    protected String region;

    // constructor
    public StorageResource(String region) {
        this.region = region;
    }

}
