package it.objectmethod.demo.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import it.objectmethod.demo.spring.models.UserObject;
import it.objectmethod.demo.spring.repository.UserRepository;

@Service
public class UsersService {

    @Autowired
    private UserRepository ur;

    @Autowired
    private JwtService jwt;

    public @ResponseBody Iterable<UserObject> getAllUsers() {
        return ur.findAll();
    }

    public void clearList() {
        ur.deleteAll();
    }

    public void removeUser(Long id) {
        ur.deleteById(id);
    }

    public UserObject getOneUser(Long id) {
        return ur.findById(id).orElse(null);
    }

    public void editUser(Long id, String username, String password) {
        UserObject user = ur.findById(id).orElse(null);
        if (user != null) {
            String hashedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setUsername(username);
            user.setPassword(hashedPassword);
            ur.save(user);
        }
    }

    public String login(String username, String password) {
        // MD5 hashing happens in SQL query itself (see UserRepository)
        UserObject user = ur.login(username, password);
        if (user != null) {
            return jwt.createJWTToken(user);
        } else {
            return null;
        }
    }

    public UserObject register(String username, String password) {
        UserObject existingUser = ur.findByUsername(username);
        if (existingUser != null) {
            return null;  // username already exists
        }

        // 🔹 Hash the password before saving
        String hashedPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        UserObject newUser = new UserObject();
        newUser.setUsername(username);
        newUser.setPassword(hashedPassword);
        newUser.setRole("user");

        ur.save(newUser);
        return newUser;
    }
}
