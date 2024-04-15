package dev.chaitanya.runnerz.user;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("consumers")
public class UserControllerWithHttpClient {

    private UserHttpClient userHttpClient;

    UserControllerWithHttpClient(UserHttpClient userHttpClient){
        this.userHttpClient = userHttpClient;
    }

    @GetMapping
    public List<User> getUsers(){
        return userHttpClient.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserWithId(@PathVariable int id){
        try {
            return new ResponseEntity<>(userHttpClient.findById(String.valueOf(id)), HttpStatus.ACCEPTED);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
