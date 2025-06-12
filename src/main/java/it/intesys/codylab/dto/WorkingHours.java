package it.intesys.codylab.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Duration;

public class WorkingHours {

    @Schema(
            description = "Durata in formato ISO-8601 (es. PT1H30M per 1 ora e 30 minuti)",
            example = "PT1H30M"
    )
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