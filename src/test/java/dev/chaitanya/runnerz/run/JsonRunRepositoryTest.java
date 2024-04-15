package dev.chaitanya.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JsonRunRepository.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class JsonRunRepositoryTest {

    @Autowired
    private JsonRunRepository repository;

    @BeforeEach
    void setUp() {
        repository.createRun(new Run(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1),"Monday Run",5, Location.OUTDOOR,null));
        repository.createRun(new Run(2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),"Wednesday Run",2, Location.OUTDOOR,null));
    }

    @Test
    void testFindAll() {
        assertEquals(2,repository.findAll().size());
    }

    @Test
    void shouldFindRunWithValidId() {
        var run = repository.findById(1).get();
        assertNotNull(run);
        assertEquals("Monday Run", run.title());
        assertEquals(5, run.miles());
    }

    @Test
    void shouldNotFindRunWithInvalidId() {
        NoSuchElementException notFoundException = assertThrows(
                NoSuchElementException.class,
                () -> repository.findById(4).get()
        );

        assertEquals("No value present", notFoundException.getMessage());
    }

    @Test
    void shouldCreateNewRun() {
        repository.createRun(new Run(3, LocalDateTime.now(), LocalDateTime.now().plusHours(1),"Monday Run",5, Location.OUTDOOR,null));
        List<Run> runs = repository.findAll();
        assertEquals(3, runs.size());
    }

    @Test
    void shouldUpdateRun() {
        repository.updateRun(new Run(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1),"Monday Run",5, Location.INDOOR,null),1);
        var run = repository.findById(1).get();
        assertEquals("Monday Run", run.title());
        assertEquals(5, run.miles());
        assertEquals(Location.INDOOR, run.location());
    }

    @Test
    void shouldDeleteRun() {
        repository.deleteRun(1);
        List<Run> runs = repository.findAll();
        assertEquals(1, runs.size());
    }
}