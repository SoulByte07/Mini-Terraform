package minitf.core;

import minitf.core.PlanResult;
import minitf.core.ApplyResult;
public interface Provisionable {
    PlanResult plan();
    ApplyResult apply();
    DestroyResult destroy();
    DestroyResult destroy(boolean force);
}
