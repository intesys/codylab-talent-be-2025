package it.intesys.codylab.dto;

import java.time.Duration;

public class WorkingHours {
    private final Duration duration;

    public WorkingHours(Duration duration) {
        this.duration = duration;
    }

    public static WorkingHours of(Duration duration) {
        return new WorkingHours(duration);
    }

    public Duration getDuration() {
        return duration;
    }
}