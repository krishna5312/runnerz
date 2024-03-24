package dev.chaitanya.runnerz.run;

import java.time.LocalDateTime;

public record Run (

    Integer id,
    LocalDateTime startedOn,

    LocalDateTime endedOn,

    String title,

    Integer miles,
    
    Location location
){}
