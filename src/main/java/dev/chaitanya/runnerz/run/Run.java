package dev.chaitanya.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run (
    @Id
    Integer id,
    LocalDateTime startedOn,

    LocalDateTime completedOn,
    @NotEmpty
    String title,
    @Positive
    Integer miles,
    
    Location location,
    @Version
    Integer version
){
    public Run{
        if(completedOn.isBefore(startedOn)){
            throw new IllegalArgumentException("Start time should be before end time");
        }
    }
}
