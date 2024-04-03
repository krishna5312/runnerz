package dev.chaitanya.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private InMemoryRunRepository runRepository;

    public RunController( InMemoryRunRepository runRepository){
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
        runRepository.createRun(run);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateRun(@Valid @RequestBody Run run, @PathVariable int id){
        runRepository.updateRun(run,id);
    }

    @DeleteMapping("/{id}")
    void deleteRun(@PathVariable int id){
        runRepository.deleteRun(id);
    }
}
