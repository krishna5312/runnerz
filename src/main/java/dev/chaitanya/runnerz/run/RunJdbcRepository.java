package dev.chaitanya.runnerz.run;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface RunJdbcRepository extends ListCrudRepository<Run,Integer> {
}
