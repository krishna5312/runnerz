package dev.chaitanya.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run (

    Integer id,
    LocalDateTime startedOn,

    LocalDateTime completedOn,
    @NotEmpty
    String title,
    @Positive
    Integer miles,
    
    Location location
){
    public Run{
        if(completedOn.isBefore(startedOn)){
            throw new IllegalArgumentException("Start time should be before end time");
        }
    }
}
