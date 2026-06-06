package minitf.core;

public class PlanResult {
    private final String resourceId;
    private final ActionType action;

    public PlanResult(String resourceId, ActionType action) {
        this.resourceId = resourceId;
        this.action = action;
    }

    public String getResourceId() {
        return resourceId;
    }

    public ActionType getAction() {
        return action;
    }
}
