package br.com.kenzie.learningSpring.controller;

import br.com.kenzie.learningSpring.dto.CreditDepositDto;
import br.com.kenzie.learningSpring.dto.UserDto;
import br.com.kenzie.learningSpring.model.User;
import br.com.kenzie.learningSpring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody final UserDto userData){

        final User createdUser = userService.createUser(userData);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<User>> readUsers(){

                final List<User> allUsers = userService.readUser();
                return  new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> retriveUser(@Valid @PathVariable final String id)  {
        final User user = userService.retriveUser(Long.parseLong(id));
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser (@Valid @RequestBody final UserDto userData, @PathVariable final String id )  {
        final User updatedUser = userService.updatedUser(userData, Long.parseLong(id));
        return new  ResponseEntity<User>(updatedUser, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateUser(@Valid @PathVariable final String id)  {

        userService.deleteUser(Long.parseLong(id));

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }
    @PostMapping("/{id}/deposit")
    public ResponseEntity<User> depositUser (@Valid @RequestBody CreditDepositDto value, @PathVariable final String id)
            throws  Exception
    {
        final User depositedUser = userService.createDeposit( value, Long.parseLong(id));
        return new ResponseEntity<User>(depositedUser, HttpStatus.CREATED);

    }
}
