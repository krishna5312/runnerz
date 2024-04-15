package dev.chaitanya.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryRunRepository implements RunRepository{

    private List<Run> runs = new ArrayList<>();

    @Override
    public List<Run> findAll(){
        return runs;
    }

    @PostConstruct
    private void init(){
        runs.add(new Run(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1),"Monday Run",5, Location.OUTDOOR,null));
        runs.add(new Run(2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),"Wednesday Run",2, Location.OUTDOOR,null));
    }

    @Override
    public Optional<Run> findById(int id) {
        return runs.stream().filter(o->o.id() == id).findFirst();
    }

    @Override
    public void createRun(Run run){
        runs.add(run);
    }

    @Override
    public void updateRun(Run run, int id){
        Optional<Run> runToBeUpdated = findById(id);
        runToBeUpdated.ifPresent(value -> runs.set(runs.indexOf(value), run));
    }

    @Override
    public void deleteRun(int id){
        runs.removeIf(run -> run.id()==id);
    }

    @Override
    public void saveAll(List<Run> runs) {
        runs.stream().forEach(run -> createRun(run));
    }

    @Override
    public int count() {
        return findAll().size();
    }
}
