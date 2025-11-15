package it.objectmethod.demo.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.objectmethod.demo.spring.models.UserObject;
import it.objectmethod.demo.spring.services.UsersService;

@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend access
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersService us;

    // ✅ Get all users
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<UserObject> getAllUsers() {
        return us.getAllUsers();
    }

    // ✅ Login API
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> login(@RequestBody UserObject userObject) {
        try {
            String username = userObject.getUsername();
            String password = userObject.getPassword();

            if (username == null || password == null) {
                return ResponseEntity.badRequest().body("Username or password missing");
            }

            String token = us.login(username, password);
            if (token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            } else {
                return ResponseEntity.ok(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error during login");
        }
    }

    // ✅ Register API
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody UserObject userObject) {
        String username = userObject.getUsername();
        String password = userObject.getPassword();

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Username or password missing");
        }

        UserObject user = us.register(username, password);
        if (user == null) {
            return ResponseEntity.badRequest().body("User already exists or invalid request");
        } else {
            return ResponseEntity.ok("User registered successfully");
        }
    }

    // ✅ Get one user
    @GetMapping("/get/{index}")
    public ResponseEntity<?> getOneUser(@PathVariable Long index) {
        Object user = us.getOneUser(index);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    // ✅ Delete user
    @DeleteMapping("/remove/{index}")
    public ResponseEntity<String> removeUser(@PathVariable Long index) {
        us.removeUser(index);
        return ResponseEntity.ok("User removed successfully");
    }

    // ✅ Edit user
    @PutMapping("/edit/{index}/{username}/{password}")
    public ResponseEntity<String> editUser(@PathVariable Long index, @PathVariable String username,
                                           @PathVariable String password) {
        us.editUser(index, username, password);
        return ResponseEntity.ok("User updated successfully");
    }

    // ✅ Clear users
    @PostMapping("/clear")
    public ResponseEntity<String> clearList() {
        us.clearList();
        return ResponseEntity.ok("User list cleared");
    }
}
