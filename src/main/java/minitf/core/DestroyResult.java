package minitf.core;

public class DestroyResult {
    private final String resourceId;
    private final boolean success;
    private final String message;

    public DestroyResult(String resourceId, boolean success, String message) {
        this.resourceId = resourceId;
        this.success = success;
        this.message = message;
    }

    public String getResourceId() {
        return resourceId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
