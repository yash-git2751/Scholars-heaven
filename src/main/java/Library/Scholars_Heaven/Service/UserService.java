package Library.Scholars_Heaven.Service;

import Library.Scholars_Heaven.Entity.User;
import Library.Scholars_Heaven.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    /**
     * Adds a new user to the database.
     * Checks if the email already exists before saving.
     *
     * @param user The user to be added
     * @return The saved user
     * @throws IllegalArgumentException If the email already exits
     */
    public User addUser(User user){
        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new IllegalArgumentException("Email Already Exits");
        }
        return userRepo.save(user);
    }


    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user.
     * @return The user if found.
     */
    public Optional<User> getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

}
