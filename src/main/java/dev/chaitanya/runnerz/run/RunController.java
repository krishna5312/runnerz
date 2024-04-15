package dev.chaitanya.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private RunJdbcRepository runRepository;

    public RunController(RunJdbcRepository runRepository){
        this.runRepository = runRepository;
    }

    @GetMapping
    public List<Run> getAllRuns(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run getRunById(@PathVariable int id) throws NoRunValueFound {
        Optional<Run> run= runRepository.findById(id);
        if(run.isEmpty()){
            throw new NoRunValueFound("No Run with the Given Id is found");
        }
        return run.get();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void createRun(@Valid @RequestBody Run run){
        runRepository.save(run);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateRun(@Valid @RequestBody Run run, @PathVariable int id){
        Optional<Run> value = runRepository.findById(id);
         value.ifPresent(val->   runRepository.save(run));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteRun(@PathVariable int id){
        Optional<Run> run = runRepository.findById(id);
        run.ifPresent(value -> runRepository.delete(value));
    }
}
