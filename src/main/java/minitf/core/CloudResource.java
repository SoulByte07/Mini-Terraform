package minitf.core;

public abstract class CloudResource implements Provisionable {
    private final String resourceId;
    private String name;
    private final String provider;

    // constructor
    public CloudResource(String resourceId, String name, String provider) {
        this.resourceId = resourceId;
        this.name = name;
        this.provider = provider;
    }

    // getters and setters
    public String getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }

    public String getProvider() {
        return provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloudResource that = (CloudResource) o;
        return resourceId.equals(that.resourceId);
    }

    @Override
    public int hashCode() {
        return resourceId.hashCode();
    }

    @Override
    public String toString() {
        return "CloudResource{" +
                "resourceId='" + resourceId + '\'' +
                ", name='" + name + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }

    @Override
    public PlanResult plan() {
        return new PlanResult(resourceId, ActionType.NOOP);
    }
}
