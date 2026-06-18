package minitf.core;

import java.util.HashMap;
import java.util.Map;

public class StateStore {
    private final Map<String, CloudResource> resources;

    public StateStore() {
        this.resources = new HashMap<>();
    }

    public void put(CloudResource resource) {
        resources.put(resource.getId(), resource);
    }

    public CloudResource get(String resourceId) {
        return resources.get(resourceId);
    }

    public void remove(String resourceId) {
        resources.remove(resourceId);
    }

    public Map<String, CloudResource> getAll() {
        return new HashMap<>(resources);
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
