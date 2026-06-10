package minitf.core;

public abstract class StorageResource extends CloudResource {
    private int sizeGb;
    private String region;

    public StorageResource(String resourceId, String name, String provider, int sizeGb, String region) {
        super(resourceId, name, provider);
        this.sizeGb = sizeGb;
        this.region = region;
    }

    public int getSizeGb() {
        return sizeGb;
    }

    public String getRegion() {
        return region;
    }
}
