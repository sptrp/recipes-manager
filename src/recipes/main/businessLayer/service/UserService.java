package recipes.main.businessLayer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.main.businessLayer.model.User;
import recipes.main.persistenceLayer.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public void registerUser(User user) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            userRepository.save(user);
        }
    }

    public User findUser(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
