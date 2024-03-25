package dev.chaitanya.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRunValueFound extends Exception{
    public NoRunValueFound(String s) {
        super(s);
    }
}
