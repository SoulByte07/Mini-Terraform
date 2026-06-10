package minitf.core;

private class VirtualEC2 extends ComputeResource {
    public VirtualEC2(String id, String name, String region) {
        super(id, name, region);
    }
    private String amiId;
    private String publicIP;

    // constuctor
    public VirtualEC2(String id, String name, String region, String amiId, String publicIP) {
        super(id, name, region);
        this.amiId = amiId;
        this.publicIP = publicIP;
    }

    @Override
    public void apply() {
        this.publicIP = "10.0.0.%d".formatted(hashCode() % 256);
        this.setStatus("running");
    }

    @Override
    public void destroy() {
        this.publicIP = null;
        this.setStatus("stop");
    }

    @Override
    public void destoy(boolean force) {
        this.publicIP = null;
        this.setStatus("terminated");
    }
}
