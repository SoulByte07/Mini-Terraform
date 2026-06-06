package minitf.core;

public class ApplyResult {
    private final String resourceId;
    private final boolean success;
    private final String message;

    public ApplyResult(String resourceId, boolean success, String message) {
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
