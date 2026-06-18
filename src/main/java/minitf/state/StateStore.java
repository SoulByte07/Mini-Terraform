package minitf.state;

import minitf.core.CloudResource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StateStore {
    private final Map<String, CloudResource> resources;

    public StateStore() {
        this.resources = new ConcurrentHashMap<>();
    }

    public void put(CloudResource resource) {
        resources.put(resource.getResourceId(), resource);
    }

    public CloudResource get(String resourceId) {
        return resources.get(resourceId);
    }

    public void remove(String resourceId) {
        resources.remove(resourceId);
    }

    public Map<String, CloudResource> getAll() {
        return new ConcurrentHashMap<>(resources);
    }

    public boolean contains(String resourceId) {
        return resources.containsKey(resourceId);
    }

    public void clear() {
        resources.clear();
    }

    public int size() {
        return resources.size();
    }
}
