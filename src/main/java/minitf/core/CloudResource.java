// Represents a cloud resource with its details
private final String resourceId;
private final String resourceName;
private final String provider;

// Constructor
public CloudResource(String resourceId, String resourceName, String provider) {
    this.resourceId = resourceId;
    this.resourceName = resourceName;
    this.provider = provider;
}

// Getters
public String getResourceId() {
    return resourceId;
}

public String getResourceName() {
    return resourceName;
}

public String getProvider() {
    return provider;
}


// Override `equals()` and `hashCode()` based on `resourceId`
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

// Override `toString()` for better readability
@Override
public String toString() {
    return "CloudResource{" +
            "resourceId='" + resourceId + '\'' +
            ", resourceName='" + resourceName + '\'' +
            ", provider='" + provider + '\'' +
            '}';
}






















