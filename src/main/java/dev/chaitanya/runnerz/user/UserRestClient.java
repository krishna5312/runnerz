package dev.chaitanya.runnerz.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserRestClient {
    private final RestClient restClient;

    public UserRestClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }
    
    List<User> findAll(){
       return restClient.get().uri("/users").retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    User findUserById(int id){
        try {
            return restClient.get().uri("users/{id}", id).retrieve().body(User.class);
        }catch(Exception ex){
            throw new IllegalArgumentException("The given User is not present "+ id);
        }
    }
}
