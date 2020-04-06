package com.gabrielflorio.operaplan;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
public abstract class TaskOrEmployee {
    private long id;

    // Shadow variables
    @InverseRelationShadowVariable(sourceVariableName = "previousTaskOrEmployee")
    protected Task nextTask;

    public TaskOrEmployee() {
    }

    public TaskOrEmployee(long id) {
        this.id = id;
    }

    public Task getNextTask() {
        return nextTask;
    }

    public void setNextTask(Task nextTask) {
        this.nextTask = nextTask;
    }

    /**
     * @return sometimes null
     */
    public abstract Integer getEndTime();

    /**
     * @return sometimes null
     */
    public abstract Employee getEmployee();

    public long getId() {
        return id;
    }
}
