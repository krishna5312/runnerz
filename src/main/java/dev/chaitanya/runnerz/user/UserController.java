package dev.chaitanya.runnerz.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserRestClient userRestClient;

    UserController(UserRestClient userRestClient){
        this.userRestClient=userRestClient;
    }

    @GetMapping
    public List<User> getUsers(){
        return userRestClient.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUsetById(@PathVariable int id){
        try {
            return new ResponseEntity<>(userRestClient.findUserById(id),HttpStatus.ACCEPTED);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
