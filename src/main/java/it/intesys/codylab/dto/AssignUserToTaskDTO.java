package it.intesys.codylab.dto;

public class AssignUserToTaskDTO {
    private Long userId;
    private Long taskId;

    public AssignUserToTaskDTO() {
        // Default constructor
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
