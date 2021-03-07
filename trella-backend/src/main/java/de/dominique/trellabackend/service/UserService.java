package de.dominique.trellabackend.service;

import de.dominique.trellabackend.model.User;
import de.dominique.trellabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsUserWithMail(final String email) {
        return userRepository.findByEmail(email) != null;
    }

    public boolean register(final User user) {
        if (!existsUserWithMail(user.getEmail())) {
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
