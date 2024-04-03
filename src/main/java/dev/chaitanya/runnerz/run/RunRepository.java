package dev.chaitanya.runnerz.run;

import java.util.List;
import java.util.Optional;

public interface RunRepository {
    List<Run> findAll();

    Optional<Run> findById(int id);

    void createRun(Run run);

    void updateRun(Run run, int id);

    void deleteRun(int id);

    void saveAll(List<Run> runs);

    int count();
}
