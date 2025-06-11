package it.intesys.codylab.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;

public class WorkingHours {

    private final Duration duration;

    @JsonCreator
    public WorkingHours(@JsonProperty("duration") Duration duration) {
        this.duration = duration;
    }

    public static WorkingHours of(Duration duration) {
        return new WorkingHours(duration);
    }

    public Duration getDuration() {
        return duration;
    }
}