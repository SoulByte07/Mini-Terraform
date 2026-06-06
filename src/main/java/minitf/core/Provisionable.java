package minitf.core;

import minitf.core.PlanResult;
import minitf.core.ApplyResult;
import minitf.core.DistroyResult;

public interface Provisionable {
    PlanResult plan();
    ApplyResult apply();
    DistroyResult distroy();
}
