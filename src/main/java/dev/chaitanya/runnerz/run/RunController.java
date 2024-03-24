package dev.chaitanya.runnerz.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello Runners!!!!";
    }
}
