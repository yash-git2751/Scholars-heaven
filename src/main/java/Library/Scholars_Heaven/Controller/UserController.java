package Library.Scholars_Heaven.Controller;

import Library.Scholars_Heaven.Entity.Book;
import Library.Scholars_Heaven.Entity.User;
import Library.Scholars_Heaven.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint to add a new user
      * @param user The user details
     * @return The saved user or an error message if email already exits.
     */

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            User savedUser = userService.addUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllusers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        String response = userService.deleteUserById(id);
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message", response);
        if(response.contains("not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User updatedUser){
        Optional<User> user = userService.updateUser(id, updatedUser);
        if (user.isPresent()){
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No User found.",HttpStatus.NOT_FOUND);
        }
    }
}