package it.intesys.codylab.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Table("slots")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Slot(Long id, Long taskId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.taskId = taskId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Slot() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}


