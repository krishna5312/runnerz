package dev.chaitanya.runnerz.run;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("JsonRunRepository")
public class JsonRunRepository implements RunRepository{
    private final JdbcClient jdbcClient;

    JsonRunRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }
    @Override
    public List<Run> findAll() {
        return jdbcClient.sql("select * from run").query(Run.class).list();
    }

    @Override
    public Optional<Run> findById(int id) {
        return jdbcClient.sql("select * from run where id=:id").param(id).query(Run.class).optional();
    }

    @Override
    public void createRun(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create run " + run.title());

    }

    @Override
    public void updateRun(Run run, int id) {
        var updated = jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
                .params(List.of(run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    @Override
    public void deleteRun(int id) {
        var updated = jdbcClient.sql("delete from run where id = :id")
                .param(id)
                .update();

        Assert.state(updated == 1, "Failed to delete run " + id);

    }

    @Override
    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::createRun);
    }

    @Override
    public int count() {
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }
}
