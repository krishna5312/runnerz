package dev.chaitanya.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    public List<Run> findAll(){
        return runs;
    }

    @PostConstruct
    private void init(){
        runs.add(new Run(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1),"Monday Run",5, Location.OUTDOOR));
        runs.add(new Run(2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),"Wednesday Run",2, Location.OUTDOOR));
    }

    public Optional<Run> findById(int id) {
        return runs.stream().filter(o->o.id().equals(id)).findFirst();
    }

    void createRun(Run run){
        runs.add(run);
    }

    void updateRun(Run run, int id){
        Optional<Run> runToBeUpdated = findById(id);
        runToBeUpdated.ifPresent(value -> runs.set(runs.indexOf(value), run));
    }

    void deleteRun(int id){
        runs.removeIf(run -> run.id().equals(id));
    }
}
