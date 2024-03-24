package dev.chaitanya.runnerz.run;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    public List<Run> findAll(){
        return runs;
    }

    @PostConstruct
    private void init(){
        runs.add(new Run(1, LocalDateTime.now(),LocalDateTime.now().plus(1, ChronoUnit.HOURS),"Monday Run",5, Location.OUTDOOR));
        runs.add(new Run(1, LocalDateTime.now(),LocalDateTime.now().plus(30, ChronoUnit.MINUTES),"Wednesday Run",2, Location.OUTDOOR));
    }
}
